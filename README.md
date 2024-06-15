# My Connected API
The aim of this project is to provide a test web
application using Spring Boot and MongoDB that can
be used within a Kubernetes cluster.

It was created to experiment with using MongoDB
within a Kubernetes cluster as described in 
[my Medium article](https://medium.com/@martin.hodges/my-experience-adding-a-mongodb-no-sql-database-to-my-kubernetes-cluster-f43fe72fa0ba), where you can find more
details.

## Profiles
The application has been created with two Spring Boot profiles: `connected` and `local-cluster`

### connected
This assumes you are running your code in your IDE and are
connecting to the MongoDB running externally, either in a
docker image or in a Kubernetes cluster. The connection
details are described in application-connected.yml.

### local-cluster
In this case, your application is running inside a Kubernetes
cluster and connects to the MongoDB cluster running in the
same cluster. It assumes that the MongoDB cluster has been
created in the `mongo` namespace.

You should add your own profiles for other scenarios.

## What does it do?
As a bit of fun, the API provides a set of basic API endpoints to allow you to
manage fishes in fish tanks.

The features include the ability to:
* Manage a set of fish tanks (`GET`, `POST`, `PUT`, `DELETE`)
* Create and manage fishes (`GET`, `POST`, `PUT`, `DELETE`)
* Add and remove fish to/from a tank (`PUT`, `DELETE`) (not yet implemented)

The REST API end points are at:

    /api/v1/fish-tanks
    /api/v1/fish-tanks/{id}
    /api/v1/fish-tanks/{id}/fishes/{id}
    /api/v1/fish-types
    /api/v1/fish-types/{id}
    /api/v1/fishes
    /api/v1/fishes/{id}

## Additional documentation
You can find more documentation about the project in
[my medium article](https://medium.com/@martin.hodges/my-experience-adding-a-mongodb-no-sql-database-to-my-kubernetes-cluster-f43fe72fa0ba).
You will also find references in this article to other
articles that explain how to set up a local Kubernetes 
cluster using Kind.

## Setting up a local Kind Kubernetes cluster

The files to set up a 4 node cluster are included in this repository
(1 master and 3 workers).

From the project root, assuming you have Kind and Helm installed,
create a base-level KInd cluster with:
```
 kind create cluster --config kind/kind-config.yml
```
Once this has been created, set up the required Helm repository:
```
helm repo add mongodb https://mongodb.github.io/helm-charts
helm repo update
```
Now install the MongoDB operator:
```
kubectl create namespace mongo
helm install community-operator mongodb/community-operator -n mongo
kubectl get pods -n mongo
```
Once this is up and running, start a 3 node MongoDB cluster with
(remember to add your password):
```
kubectl create secret generic my-user-password -n mongo --from-literal="password=<your password>"
```
Check you entered it correctly with:
```
kubectl get secrets -n mongo my-user-password -o jsonpath={.data.password} | base64 -d; echo
```
Now request a MongoDB cluster to be created with:
```
kubectl apply -f k8s/my-mongo-db.yml
```
Wait for all three instances to be ready.
***This can take over 5 minutes***.

Once it is up and running, you need to connect to it
with a MongoDB client. Firt port forward the primary
pod (you will need to find out which one this is by
looking at the logs):
```
kubectl port-forward my-mongo-db-0 -n mongo 27017:27017
```

This could be your IDE or a UI such as compass.

Once you have a MongoDB shell, enter the following to
create your application user (remember to put in the
password you want to use):
```
use aquarium
db.createUser( { user: "my-app-user",
              pwd: "<password>",
              roles: [ {db: "aquarium", role: "dbOwner"} ] } )
```

## Running this application
You can run this application within your IDE using the `connected`
profile mentioned earlier.

Alternatively, you can run it in your Kubernetes cluster with
the `local-cluster` profile

In this profile, the application runs in the Kubernetes cluster
as a JAR file in a Docker image. It connects to the cluster database.

You can build this and create the image with:
```
gradle jar
docker build -t aquarium .
```
You can now upload it to your Kind cluster with:
```
kind load docker-image aquarium
```
Now deploy it to the cluster with:
```
kubectl apply -f k8s/deployment.yml
kubectl get pods
```
It should start within a few seconds.

## Testing the APIs
The API will be available on http://localhost:30080
and can be tested with curl:
```
curl localhost:30080/api/v1/fishes -H "Content-Type: application/json" -d '{"type": "guppy2"}' 
curl localhost:30080/api/v1/fish-tanks -H "Content-Type: application/json" -d '{"name": "big one"}' 
curl localhost:30080/api/v1/fishes
curl localhost:30080/api/v1/fish-tanks
```