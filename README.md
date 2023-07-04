# MovieShop
Rest application where the user can rent movies. 
The application assumes the use of two profiles. As an administrator who has unlimited access and a user who can only perform part of the operations. 
To enable this, Spring Secure has been added that allows you to create user accounts. 
For the needs of a small demonstration application, these data are not downloaded from an additional database, only accounts are created in the InMemory database (in current memory). 
That accounts are deleted and created each time the application is launched. 
For easier navigation through the endpoints (requests for the application to execute a command, such as creating a new user, deleting movies or renting a given movie by a given user), an additional dependency was used, i.e. Swagger-ui.html
Thanks to this, after starting the application, we can run the link localhost:8080/swagger-ui.html to see all possible endpoints. 
When launching the swagger-ui website, you will be asked for a password and login. Login details for the 
---------------------------------------
Administrator:
login: admin
password: qawsed
---------------------------------------
User:
login: user
password: user1
---------------------------------------
 Users are entered using the discouraged login input model, the withDefaultPasswordEncoder method. 
 This entry is discouraged due to entering the password in plain text, i.e. we expose the data, however, for educational purposes as well as the possibility of a thorough analysis of the code, this model was chosen. 
 For a more elegant method of handling errors n no searched user, the Exceptions block was created, i.e. a way to handle reporting the type of errors by our written code. 
 Thanks to this, the user does not get information about the 404 error, which the name itself does not tell him anything, but instead receives a response from the server about the lack of the searched item.
---------------------------------------
Addition detail:
Port Number: 8080
---------------------------------------
Application.poperties:
spring.h2.console.path=/h2
spring.h2.console.enabled=true
spring.datasource.generate-unique-name=false
spring.datasource.name=movieShop
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
spring.jpa.show-sql=false
---------------------------------------
