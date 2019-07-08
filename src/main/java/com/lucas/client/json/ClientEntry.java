package com.lucas.client.json;
/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/SOAPClient
 * @version 1.1
 * @since 1.0
 */

import java.net.MalformedURLException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**Starting Point
 */
public class ClientEntry {
    public static void main(String[] argv) throws MalformedURLException {

        showMenu ();


    }

    /**Display the menu. This starts when the app load
     * @throws NumberFormatException manage Integer input
     * @throws InputMismatchException manage Integer input
     * @throws MalformedURLException Manage URL error
     */

    public static void showMenu() throws MalformedURLException {
        try {
            int exit = 0;
            do {
                System.out.println ( "" );
                System.out.println ( "****** Welcome to Solo's Library ******" );
                System.out.println ( "" );
                System.out.println ( "Please, Select one Option - Int Required from 0 to 6" );
                System.out.println ( "1. Help / About" );
                System.out.println ( "2. List Books" );
                System.out.println ( "3. Display Book" );
                System.out.println ( "4. Add Book" );
                System.out.println ( "5. Update Book" );
                System.out.println ( "6. Delete Book" );
                System.out.println ( "0. Quit" );
                System.out.println ( "" );

                Scanner sc = new Scanner ( System.in );
                int response = Integer.valueOf ( sc.nextLine ( ) );
                System.out.println ( response );
                switch (response) {
                    case 0:
                        //salir
                        exit = 0;
                        break;
                    case 1:
                        new ClientRequests ().help ();
                        break;
                    case 2:
                        new ClientRequests ().listAllbooks ();
                        break;
                    case 3:
                        new ClientRequests ().displaySelector ();
                        break;
                    case 4:
                        new ClientRequests ().addBook ();
                        break;
                    case 5:
                        new ClientRequests ().updateBook ();
                        break;
                    case 6:
                        new ClientRequests ().deleteBook ();
                        break;

                    default:
                        System.out.println ( );
                        System.out.println ( "Please, Select one Option" );
                        System.out.println ( );
                        break;
                }

            } while (exit != 0);
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println ( "Please, check the input, all (int) required values" );
            showMenu ();
        }
    }

}

