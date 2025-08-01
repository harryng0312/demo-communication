## 1. Create kind config:
- ref [kind-config.xml](https://kind.sigs.k8s.io/docs/user/configuration)
```sh
$ kind create cluster --name multi-node --config=kind-config.yaml
$ kind create cluster --name multi-node --config=kind-cluster-config.yaml
```
- install nginx-ingress-controller:
```shell
$ helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
$ helm repo add nginx-stable https://helm.nginx.com/stable
$ helm repo update
$ helm install ingress-nginx ingress-nginx/ingress-nginx --atomic \
--namespace nginx-ingress \
--set controller.ingressClassResource.name=nginx \
-f 3_network/nginx-ingress-deploy-cfg.yaml
$ helm install nginx-ingress nginx-stable/nginx-ingress --set rbac.create=true -f 3_network/nginx-ingress-deploy-cfg.yaml
$ helm install nginx-ingress nginx-stable/nginx-ingress -f 3_network/nginx-ingress-deploy-cfg.yaml

# $ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
$ kubectl apply -f https://projectcontour.io/quickstart/contour.yaml
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
$ kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s
```

- taint nodes:
```shell
$ kubectl taint 
```
## 2. Create network:
### 2.1. Create Ingress:
```shell
$ kubectl taint nodes multi-node-control-plane key=value:NoSchedule
$ kubectl taint nodes multi-node-control-plane key-
```
### 2.2. Install Linkerd:
```shell
$ linkerd check --pre
$ linkerd install --crds | kubectl apply -f -
$ linkerd install | kubectl apply -f -
$ linkerd inject deployment.yaml| kubectl apply -f -
$ linkerd check
$ linkerd viz install | kubectl apply -f -
```
- linkerd inject
```shell
$ linkerd inject deployment.yaml| kubectl apply -f -
$ linkerd viz stat deploy
$ kubectl get -n demo-communication deploy -o yaml \
  | linkerd inject - \
  | kubectl apply -f -
```
- restart:
```shell
$ kubectl rollout restart sts/ws -n demo-communication
```