# nakupovalniSeznam
#

#vaje 6:
REST KLICI
ostranjevanje: GET http://localhost:8080/v1/artikli?offset=2&limit=3

sortiranje: GET http://localhost:8080/v1/uporabniki?order=email ASC,priimek DESC

filtriranje: GET http://localhost:8080/v1/oznake?filter=id:IN:[1,2,3]

openapi tj swagger koji se sam napravi: http://localhost:8080/api-specs/ui/
- ovo za napake je NeveljavniRegistriraniUporabnik na apiju u mappers, i koristimo ga u storitve u UporabnikZrno
#vaje 7: 

get + http:/localhost:8080/v1/artikli/1

 get + http:/localhost:8081/v1/priporocil mozda je i post??

#vaje8:
####healthcheck:

pokrenes eeapplication i docker postgres

 u postman pises http://localhost:8080/health

#mikro2:
###docker:
 docker build -t priporocilni-sistemi-master  .

 docker tag  priporocilni-sistemi-master mm9136/priporocilni-sistemi-master

 docker  push mm9136/priporocilni-sistemi-master

 docker run -p 8081:8081 priporocilni-sistemi-master

 i u postman pokrenem ono za vaje7 BEZ pokretanja EEApplication i radi

 http:/localhost:8081/v1/priporocila

#### azur:
imam kubernetes vec u kompu, proverila sa komandom kubectl

instalis azur

kubernetes servis napravi sa vaja dakle sve po default, zone 1, nest nest i create

kad se to napravi ides na connect

 pre sve ides u cmd na kompu
 az login 
 onda idu prve dve komande iz ayur kad kliknes na taj connet to cluster
 
az account set --subscription 328c66b2-47c0-4190-bf6c-0e3aeed1c01c
 
az aks get-credentials --resource-group DefaultResourceGroup-EUS --name PRPO-1
 
kubectl apply -f priporocilni-sistemi-deployment.yaml
  
kubectl get services

dobijes kuber in tvoj priporocilni-sistemi i od tvoj vidis external ip i njega das umesto localhost u postman i ne pokreces eeapplicaiton pre toga
u postmanu das:
  get + http:/52.151.239.192:8081/v1/priporocila
