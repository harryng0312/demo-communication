### 1. Nginx ingress:
```shell
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```
### 2. Envoy proxy:
```shell
$ kubectl apply -f https://raw.githubusercontent.com/envoyproxy/gateway/latest/examples/kubernetes/grpc-routing.yaml
```
### 3. Linkerd:
- url: *https://github.com/linkerd/linkerd2/releases*
- install CRDS:
```shell
$ linkerd install --crds | kubectl apply -f -
```
- install linkerd
```shell
$ linkerd -set proxyInit.runAsRoot=true install | kubectl apply -f -
```
- install HA:
```shell
$ linkerd --set proxyInit.runAsRoot=true install --ha | kubectl apply -f -
# uninstall
$ linkerd uninstall | kubectl delete -f -
```
- install Proxy Injector *(opt)*:
```shell
$ kubectl apply -f https://raw.githubusercontent.com/linkerd/linkerd2/main/k8s/linkerd-install/linkerd-proxy-injector-rbac.yaml
$ kubectl apply -f https://raw.githubusercontent.com/linkerd/linkerd2/main/k8s/linkerd-install/linkerd-proxy-injector.yaml
```
- install VIZ *(opt)*:
```shell
$ linkerd viz install | kubectl apply -f -
# Run
$ linkerd viz dashboard --port 58585 &
```

- uninsall:
```shell
# To remove Linkerd Viz
$ linkerd viz uninstall | kubectl delete -f -
# To remove Linkerd Jaeger
$ linkerd jaeger uninstall | kubectl delete -f -
# To remove Linkerd Multicluster
$ linkerd multicluster uninstall | kubectl delete -f -
# Uninstall all
$ linkerd uninstall | kubectl delete -f -
```