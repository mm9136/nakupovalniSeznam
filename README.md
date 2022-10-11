# nakupovalniSeznam
#

## task 6:

REST KLICI example

ostranjevanje: GET http://localhost:8080/v1/artikli?offset=2&limit=3

sortiranje: GET http://localhost:8080/v1/uporabniki?order=email ASC,priimek DESC

filtriranje: GET http://localhost:8080/v1/oznake?filter=id:IN:[1,2,3]

openapi tj swagger koji se sam napravi: http://localhost:8080/api-specs/ui/

<!-- - ovo za napake je NeveljavniRegistriraniUporabnik na apiju u mappers, i koristimo ga u storitve u UporabnikZrno -->


## task 7: 

get + http:/localhost:8080/v1/artikli/1

get + http:/localhost:8081/v1/priporocil 


## task 8: healthcheck:

run EEApplication & docker postgres

in POSTMAN: http://localhost:8080/health

## mikro2:

### docker:

 docker build -t priporocilni-sistemi-master  .

 docker tag  priporocilni-sistemi-master mm9136/priporocilni-sistemi-master

 docker  push mm9136/priporocilni-sistemi-master

 docker run -p 8081:8081 priporocilni-sistemi-master
 
 in POSTAMAN run #task7 without running EEApplication

 <!-- i u postman pokrenem ono za vaje7 BEZ pokretanja EEApplication i radi -->

 http:/localhost:8081/v1/priporocila

#### azur:

check if you have installed kubernetes with kubectl
<!-- imam kubernetes vec u kompu, proverila sa komandom kubectl -->

install azur
<!-- instalis azur -->

kubernetes servis - default values (zone 1, nest nest, create..)
<!-- kubernetes servis napravi sa vaja dakle sve po default, zone 1, nest nest i create -->

<!-- kad se to napravi ides na connect -->

<!-- pre sve ides u cmd na kompu -->
az login 
<!-- onda idu prve dve komande iz azur kad kliknes na taj connet to cluster -->
 
az account set --subscription 328c66b2-47c0-4190-bf6c-0e3aeed1c01c
 
az aks get-credentials --resource-group DefaultResourceGroup-EUS --name PRPO-1
 
kubectl apply -f priporocilni-sistemi-deployment.yaml
  
kubectl get services

<!--dobijes kuber in tvoj priporocilni-sistemi i od tvoj vidis external ip i njega das umesto localhost u postman i ne pokreces eeapplicaiton pre toga
u postmanu das: -->
in POSTMAN: get + http:/52.151.239.192:8081/v1/priporocila
