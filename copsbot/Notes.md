For now I have commented out the spring-boot-starter-security from the pom.xml. This is so I don't have to authenticate to do anything.

To run, you can run from within IntelliJ (note that my configuration uses the dev profile), or you can run with this command:

mvn spring-boot:run (note that this will use the default profile)



Current state:
turned security off
To get a user:
curl http://localhost:8080/api/users/me
got this:
{"id":{"id":"20c53fe9-07c2-4186-a72a-d6e6cc46a518"},"email":"someemail","password":"somepass","roles":["OFFICER"]}

To create a user:
curl -H "Content-Type: application/json" --request POST --data '{"email" : "some@email.com", "password" : "somepass"}' http://localhost:8080/api/users
returns this:
{"id":{"id":"3961f8c0-47b9-4eb9-8c09-83adf559dc2d"},"email":"some@email.com","password":"somepass","roles":["OFFICER"]}




Next steps:
UserRestControllerTest - test the POST method

ActiveProfiles? - what does this do?.... activates the test profile.... is this needed for unit tests?
