### 1. Nginx ingress:
```shell
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```
### 2. Envoy proxy:
```shell
$ kubectl apply -f https://raw.githubusercontent.com/envoyproxy/gateway/latest/examples/kubernetes/grpc-routing.yaml
```