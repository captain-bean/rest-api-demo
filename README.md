# rest api demo

Building and running:

  - Ensure docker is installed and the daemon is running
  - You may also need maven installed, but the binary included should be sufficient
  - In the top level directory, run the command "mvn spring-boot:build-image"
  - Verify that there is a "singlestone-demo" image listed in the output of "docker images"
  - Run the command "docker run -p 8080:8080 -t singlestone-demo:0.0.1-SNAPSHOT"
    - If port 8080 is in use, change the lefthand port assignment to a free port.
  - The ReST API is now accessible at localhost:8080/contacts
  
  A postman collection has been provided to make it easy to send typical rest requests. This
  is under the postman-collection folder, and is importable to a Postman application
  
  The application uses an h2 in memory database. To use a real database, we would want to
  host a database at some location, and provide the connection details to spring by
  modifying the spring application.properties file. In a more robust system, we might
  want to pass these configuration values down through the dockerfile or a kubernetes configmap
  
  We would also likely at some point want to stop using autogenerated DDL and either have SQL
  to generate our tables, keys, and indices. Or rely on a once off autogenerated DDL and use
  SQL commands to perform updates to the table structure and work off of exported snapshots
  

Design decision commentary:

  - The first major design decision was whether to start first with an OpenAPI spec
  and then generating models and API stubs based off of that. While I do think
  that is a good way to pursue API first development, I felt that it circumvented
  some of the point of this exercise so I chose to use a more typical hand written
  spring app.
  
  - I chose to separate the persistence layer to entity classes rather than incorporating
  the JPA annotations along with the model layer. This helps to keep the models less cluttered,
  and also avoids any of the weirdness associated with having an entity manager listening
  to live updates to the objects and persisting to the database.
  
  - For testing, I chose just to keep it simple and test the one more complicated repository
  operation. I would have also like to have done some contract driven testing, but that
  started to become a bit overkill for the scope of this exercise.
  
  - I decided to perform most of the "call list" processing during the query, for 
  maximum efficiency. I could have made it even more performant by using a projection
  to map directly out to a "CallListItem" from the database, but chose against this for
  time constraints.

  - I chose to use embedded types for each of the subsets of ContactEntity, because it did
  not feel worth it to have separate tables from a performance standpoint. Each of the
  subsets fit the bill as "Aggregate children", so using embedded types helps to enforce
  this pattern as well.
  
  - In general, I prefer explicitly configuring what beans are used in spring applications
  and avoid too much autoconfiguration. However, for the sake of time I left much of the
  default autoconfiguration in.
  
  - I did not provide a full scope of error handling in the rest resource for the sake of time,
  however I left some commentary in the class about what that could look like.
  
  - I chose to use an aggregated repository class that makes use of both JPA repositories and queryDSL.
  This was so that I provide a simpler interface as compared to a direct JPA repository, and also have
  easy access to QueryDSL for the more complex query that was not provided out of the box by JPA.
  
  - I chose to do some of the processing and mapping logic in the Resource class for brevity.
  If the logic exceeded a certain point, I would consider breaking out a "Service" layer
  between the resource and the repository. This would let the Resource focus its responsibility
  on just the ReST layer concerns.

  - I chose to use variable names that matched the provided JSON to keep things simpler,
  but in a production application I may have chosen more descriptive variable names and used
  annotations to ensure the JSON remained correct.
  
  - I would normally javadoc all public methods, interfaces, and classes with a short description,
  any parameters, return values, and exceptions. For the sake of time, I did not javadoc everything
  here.
