package com.lucas.client.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.swing.text.html.parser.DTD;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class ClientRequest {

    Client client = Client.create();
    String urlClient = "http://localhost:8080/LibraryServerRest_war/service/book/";

    public void postRequestBook() throws MalformedURLException {
        System.out.println ( ":::::: Module Create new Book ::::::" );
        String url = urlClient+"posting";
        WebResource webResource = client.resource ( url );
        Book book = new ClientRequest ().preparationBook ();
        mapperBook ( webResource, book, false );
        ClientEntry.showMenu ( );
    }

    public void updateRequestBook() throws MalformedURLException {
        System.out.println ( ":::::: Module Update new Book ::::::" );
        String url = urlClient+"updating";
        WebResource webResource = client.resource ( url );
        Book book = new ClientRequest ().preparationBook ();
        mapperBook ( webResource, book, true );
        ClientEntry.showMenu ( );
    }

    public void listRequestBook() throws MalformedURLException {
        System.out.println ( ":::::: Module List Book ::::::" );
        String url = urlClient+"list";
        WebResource webResource = client.resource ( url );
        mapperList ( webResource);
        ClientEntry.showMenu ( );
    }

    public void idRequestBook() throws MalformedURLException {
        System.out.println ( ":::::: Module Search by Id ::::::" );
        String url = urlClient+"getid";
        WebResource webResource = client.resource ( url );
        Integer id = new ClientRequest ().idInput ();
        mapperId ( webResource, id, false);
        ClientEntry.showMenu ( );
    }

    public void deleteRequestBook() throws MalformedURLException {
        System.out.println ( ":::::: Module delete by Id  ::::::" );
        String url = urlClient+"delete";
        WebResource webResource = client.resource ( url );
        Integer id = new ClientRequest ().idInput ();
        mapperId ( webResource, id, true);
        ClientEntry.showMenu ( );
    }


    public void help() throws MalformedURLException {
        System.out.println ( "***** Welcome to Solo's Library System Version RestJson *****" );
        System.out.println (    "\n In this System you can search, add and delete books. " +
                "\n Additionally, you can work remotely with Rest services, you " +
                "\n Perhaps you are wondering why we change the technology every 2 weeks...:) " +
                "\n Thanks for using Lucas Software and enjoy it" );

        System.out.println ( "Please, type any KEY to come back to the Menu" );
        Scanner sc = new Scanner ( System.in );
        sc.next ( );
        ClientEntry.showMenu ( );
    }


    public void mapperBook(WebResource webResource, Book book, Boolean put) {

        ObjectMapper mapper = new ObjectMapper();
        String inputData = null;
        ClientResponse response = null;

        try {
            inputData = mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace ( );
        }
        if (put){
            response = webResource.type ( "application/json" ).put ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }
        }
        else {
            response = webResource.type ( "application/json" ).post ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }
        }
        messageManager ( response );
    }

    public void mapperList(WebResource webResource) {

        ClientResponse response = webResource.type ( "application/json" ).get ( ClientResponse.class);
        if(response.getStatus()!=200){
            throw new RuntimeException("HTTP Error: "+ response.getStatus());
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper ( );
            Book [] arrayBook = objectMapper.readValue (response.getEntity ( String.class ),Book[].class);
            for (Book jBook : arrayBook ) {
                System.out.println ( "------------------------------------------------" );
                System.out.println ("ID: " + jBook.getId () + " / " + "Title: " + jBook.getTitle ());
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        System.out.println ( "------------------------------------------------" );
    }

    public void mapperId (WebResource webResource, Integer id, Boolean delete) {
        ObjectMapper mapper = new ObjectMapper();
        String inputData = null;
        ClientResponse response = null;

        try {
            inputData = mapper.writeValueAsString(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace ( );
        }

        if (delete) {
            response = webResource.type ( "application/json" ).delete ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }
        messageManager ( response );
        }

        else {
            response = webResource.type ( "application/json" ).put ( ClientResponse.class, inputData );
            if (response.getStatus ( ) != 200) {
                throw new RuntimeException ( "HTTP Error: " + response.getStatus ( ) );
            }

            try {
                ObjectMapper objectMapper = new ObjectMapper ( );
                Book book = objectMapper.readValue (response.getEntity ( String.class ),Book.class);
                    System.out.println ( "------------------------------------------------" );
                    System.out.println ("ID: " + book.getId () + " / " + "Title: " + book.getTitle ());

            } catch (IOException | ClientHandlerException e) {
                System.out.println ( "Record not Found" );
            }
            System.out.println ( "------------------------------------------------" );
        }
    }

    public Book preparationBook () {

        Scanner sc = new Scanner ( System.in );
        Book book = new Book ();

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

    public Integer idInput() {
        System.out.println ( "Please enter the Id - Int Required" );
        Scanner sc = new Scanner ( System.in );
        Integer id = Integer.valueOf (sc.nextLine ());
        return id;
    }

    public void messageManager (ClientResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper ( );
            Messages message = objectMapper.readValue (response.getEntity ( String.class ), Messages.class );
            System.out.println ( message.getMessage () );
            System.out.println ( message.getEstatus () );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }


}
