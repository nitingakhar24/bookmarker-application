@echo off

echo Initializing Kubernetes cluster...

kind create cluster --config kind-config.yml

echo:
echo -----------------------------------------------------
echo:

echo Installing NGINX Ingress...

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

echo:
echo -----------------------------------------------------
echo:

echo Waiting for NGINX Ingress to be ready...

timeout /t 10 /nobreak > nul

kubectl wait --namespace ingress-nginx ^
  --for=condition=ready pod ^
  --selector=app.kubernetes.io/component=controller ^
  --timeout=180s

echo:
echo cluster creation done.