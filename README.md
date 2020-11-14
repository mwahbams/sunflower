# sunFlower

Sunflower school is managing their registration of courses, teachers, classes and courses online

## Getting Started

This project is a Springboot Application.

#Technologies 
   - Springboot
   - SpringData Jpa
   - Lombok
   - MapStruct
   - DB (H2 or postgres)
   - Swagger
# How to run
    - navigate to sunflower folder and run :: "mvnw spring-boot:run"
    - for docker run the following :: "docker run -e ACTIVE_PROFILE=h2  --name sunflower  -p8090:8090 mwahbams/sunflower:0.1"
    
#Testing Application

  - Open Swagger and test The API :
        http://localhost:8090/swagger-ui.html
        
  - Open DB Console
    - open this Url :: http://localhost:8090/h2-console
    - use below info 
       - url: jdbc:h2:mem:h2-sunflower
       - username: admin
       - password: admin
    