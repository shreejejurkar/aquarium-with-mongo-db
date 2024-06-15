# Step 1: Start the Minikube control-plane node
```
minikube start --cpus=2 --memory=2048 --profile=control-plane
```

# Step 2: Start the Minikube worker nodes
```
minikube start --cpus=1 --memory=1024 --profile=worker1
```

```
minikube start --cpus=1 --memory=1024 --profile=worker2
```

```
minikube start --cpus=1 --memory=1024 --profile=worker3
```

# Step 3: Configure kubectl to use the control-plane context
```
kubectl config use-context minikube
```

# Step 4: Verify the cluster nodes

```
kubectl get nodes
```
