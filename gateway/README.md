# **PHONEBOOK**

###Simple microservice architecture 
###with **Eureka Discovery**, **Zuul** and security **OAuth2** implementation.

###There are also several services under it
* Profiles-Service (Spring Boot)
* News-Service (.net core)

#####So first of all you need make a call to the **auth-service** 
#####to get the authorization token:
```curl -X POST \
   http://localhost:10000/phonebook/api/uaa/oauth/token \
   -H 'authorization: Basic dHJ1c3RlZC1hcHA6c2VjcmV0' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/x-www-form-urlencoded' \
   -d 'grant_type=password&username=bogdevich96%40gmail.com&password=admin'```