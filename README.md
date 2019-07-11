# LibraryClient
McGill Assignment 3. Client Side

Client Library is a project to create a client java console app to request information from a web services in json

## Installation

Project created in Maven, all the dependencies are in a .pom document.

Compile the code but first cheque the Server Url in the following class:

```
public class ClientRequest {

    Client client = Client.create();
    String urlClient = "http://localhost:8080/LibraryServerRest_war/service/book/";


```
Update URL depending on server configuration



## Contributing
Academic Project for McGill University. No contributing is requested. 

## License
[MIT](https://choosealicense.com/licenses/mit/)
