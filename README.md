# tarmac-challenge

Dear reviewer: 
Regarding the Database:
I used mysql database. You need to have a local mysql db connection. Springboot application will create the schema and table. Its default values are for 
hostname: localhost
port:3306
username:root
password: root
To configure username and password, please modify the file \src\main\resources\application.properties 
spring.datasource.username=root
spring.datasource.password=root

The REST service is built on Spring Boot. To have it running you can use a command line standing in path where you have downloaded the project in folder "employees_service" run "mvn spring-boot:root".
Service has an endpoint that can be used to insert data into de database. 
Once the service is started you can use Postman or similar to use the endpoints. Use the following url: http://localhost:8080/employee/saveAll with a POST method, and paste in the body the json data provided or any array of objects in json format with the given structure. 
With the url http://localhost:8080/employee/saveEmployee POST method you can save 1 employee. On both methods, picture_url is validated and completed in case is not set or incomplete.
There is also a method to delete an employee by name that can be used with the following endpoint:  http://localhost:8080/employee/<Name to delete> with DELETE method
and another endpoint to retrieve data of a particular employee by name: 
http://localhost:8080/employee/<Name to delete> with GET method

The other methods are used by the page to get all employees, employees filtered by role or city, and by role and city, and to list all the different roles and cities from employees. 

The web page is under "tarmac-challenge-page" directory. This is vanilla js.
Once spring boot service is up and running you can open in browser index.html file to see the page. Page is able to fetch data from database through the REST service. 
I added the environment.js file where you will be able to configure different api url i case it´s required.

Hope its according to your expectations. 

Thanks and Regards! It's been a really good challenge.

Paula Burgos

