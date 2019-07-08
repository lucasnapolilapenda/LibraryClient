package com.lucas.client.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.eclipse.persistence.mappings.structures.ArrayMapping;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class ClientRequest {

    Client client = Client.create();
    String postUrl = "http://localhost:8080/LibraryServerRest_war/service/book/posting";

    public void postRequestBook() throws MalformedURLException {
        WebResource webResource = client.resource ( postUrl );
        Book book = new ClientRequest ().preparationBook ( false );
        ObjectMapper mapper = new ObjectMapper();
        String inputData = null;
        try {
            inputData = mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace ( );
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper ( );
            Book bookMapper = objectMapper.readValue ( inputData, Book.class );


        } catch (IOException e) {
            e.printStackTrace ( );
        }

        exceptionManagerPost ( webResource, inputData );
        ClientEntry.showMenu ( );
    }

    public void help() throws MalformedURLException {
        System.out.println ( "***** Welcome to Solo's Library System Version RestJAson *****" );
        System.out.println (    "\n In this System you can search, add and delete books. " +
                "\n Additionally, you can work remotely with Rest services, you " +
                "\n Perhaps you are wondering why we change the technology every 2 weeks...:) " +
                "\n Thanks for using Lucas Software and enjoy it" );

        System.out.println ( "Please, type any KEY to come back to the Menu" );
        Scanner sc = new Scanner ( System.in );
        sc.next ( );
        ClientEntry.showMenu ( );
    }


    public void exceptionManagerPost(WebResource webResource, String inputData) {
        ClientResponse response = webResource.type ( "application/json" ).post ( ClientResponse.class, inputData );
        if (response.getStatus ( ) != 200) {
            throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
        }

        String result = response.getEntity ( String.class );
        System.out.println ( "Response from the Server: " );
        System.out.println ( result );

    }

    public Book preparationBook (boolean update) {

        Scanner sc = new Scanner ( System.in );
        Book book = new Book ();

        if (update) { System.out.println ( ":::::: Module Update new Book ::::::" ); }
        else { System.out.println ( ":::::: Module Add new Book ::::::" ); }

        System.out.println ( "Please enter the Id - Int Required" );
        book.setId (Integer.valueOf (sc.nextLine ()  ) );
        System.out.println ( "Please enter the Isbn, remember https://www.isbn-international.org" );
        book.setIsbn ( sc.nextLine () );
        System.out.println ( "Please enter the Title" );
        book.setTitle ( sc.nextLine () );
        System.out.println ( "Please enter the Author" );
        book.setAuthor ( sc.nextLine () );
        System.out.println ( "Please enter the Publisher" );
        book.setPublisher ( sc.nextLine () );
        System.out.println ( "Please enter the Description" );
        book.setDescription ( sc.nextLine () );

        return book;
    }


}
