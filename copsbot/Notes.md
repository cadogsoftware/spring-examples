For now I have commented out the spring-boot-starter-security from the pom.xml. This is so I don't have to authenticate to do anything.

To run, you can run from within IntelliJ (note that my configuration uses the dev profile), or you can run with this command:

mvn spring-boot:run (note that this will use the default profile)



Current state:
turned security off
To get a user:
curl http://localhost:8080/api/users/me
got this:
{"id":"bfe02d2f-7bd6-45cc-a924-ff0dadc6332e","email":"alex.foley@beverly-hills.com","roles":["OFFICER"]}

To create a user:
curl -H "Content-Type: application/json" --request POST --data '{"email" : "some@email.com", "password" : "somepass"}' http://localhost:8080/api/users
returns this:
{"id":"0663fa3d-f7a8-4361-84ca-fe6f9f798e42","email":"some@email.com","roles":["OFFICER"]}



Next steps:
ActiveProfiles? - what does this do?.... activates the test profile.... is this needed for unit tests?
