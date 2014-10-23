/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 Copyright 2014 Gabriel Franco <gabriel.franco.martinez@gmail.com>
 
 This file is part of WF Control Panel.
 
 WF Control Panel is free software: you can redistribute it and/or 
 modify it under the terms of the GNU General Public License as 
 published by the Free Software Foundation, either version 3 of the 
 License, or (at your option) any later version.
 
 WF Control Panel is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with WF Control Panel.  If not, see 
 <http://www.gnu.org/licenses/>.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package com.gaseosa.wf.data;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import com.gaseosa.wf.models.Mail;

import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;



/**
 * Communication class to connect with the server.
 * 
 * It contains methods that interact directly with the server. It's
 * functionallity oriented. It means that instead of having each function
 * calling a unique RPC function in the server, it makes several RPC calls
 * depending on the function.
 * 
 * For example, to create a new mail, two RPC functions should be called. Here,
 * those two functions are included in only one function calle "mailCreate()"
 * 
 * @author Gabriel
 * @version 0.1
 * 
 * 
 */
public class Comms
{


    /**
     * Client-side connection to server
     */
    private static XMLRPCClient client;

    /**
     * Session ID from the server
     */
    private static String       sessionID;

    /**
     * Username
     */
    private static String       username           = "";

    public static final int     NO_ERROR           = 0;
    public static final int     ERROR_NETWORK      = 1;
    public static final int     ERROR_BAD_PETITION = 2;
    public static final int     ERROR_XMLRPC       = 3;



    /**
     * 
     * Starts the communication with the server.
     * 
     * Calls the server and gets the session id for the rest of the
     * communication. If anything goes wrong returns an error.
     * 
     * @param user
     *            Username in the server
     * @param pass
     *            Password for the account
     * @return int Error status
     */
    public static int init(String user, String pass)
            throws MalformedURLException, XMLRPCException
    {
        try
        {
            username = user;

            // System.out.println("Logueando con : " + user + " y " + pass);

            // Creates the client object and logins, saving the session id
            client = new XMLRPCClient(new URL("https://api.webfaction.com/"));
            Object[] result = (Object[]) client.call("login", new Object[] {
                    (String) user, (String) pass });

            sessionID = (String) result[0];

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            throw e;
        }
        // catch (XMLRPCServerException ex)
        // {
        // ex.printStackTrace();
        // ex.getCause();
        // throw ex;
        // }
        catch (XMLRPCException e)
        {
            e.printStackTrace();
            throw e;
        }

        return NO_ERROR;
    }



    /**
     * Gets the existing mails in the server
     * 
     * @return Vector<Mail> with all the info of the mails in the current
     *         account in the server. Null if anything went wrong
     */
    public static Vector<Mail> mailListAll() throws XMLRPCException
    {

        Vector<Mail> resultado = null;

        try
        {

            Object[] ob = (Object[]) client.call("list_emails",
                    new Object[] { sessionID });

            resultado = new Vector<Mail>();

            for (int i = 0; i < ob.length; i++)
            {
                HashMap<String, String> hm = (HashMap<String, String>) ob[i];
                resultado.add(new Mail(hm.get("email_address"), hm
                        .get("targets"), 0, hm.get("autoresponder_subject"), hm
                        .get("autoresponder_message")));
            }
        }
        catch (XMLRPCException e)
        {
            e.printStackTrace();
            throw e;
        }

        return resultado;

    }



    /**
     * Changes the mail inbox password
     * 
     * @param buzon
     *            Elegido Name of the inbox
     * @param pass
     *            New password
     * @return int with possible errors
     */
    public static int mailChangePass(String buzonElegido, String pass)
            throws XMLRPCException
    {
        try
        {
            client.call("change_mailbox_password", new Object[] { sessionID,
                    buzonElegido, pass });
        }
        catch (XMLRPCException e)
        {
            e.printStackTrace();
            throw e;
        }

        return NO_ERROR;
    }



    /**
     * Deletes address and inbox of the indicated mail
     * 
     * @param inbox
     *            Inbox to delete
     * @param address
     *            Address to delete
     */
    public static int mailDelte(String inbox, String address)
            throws XMLRPCException
    {

        try
        {
            client.call("delete_mailbox", new Object[] { sessionID, inbox });
            client.call("delete_email", new Object[] { sessionID, address });
        }
        catch (XMLRPCException e)
        {
            // e.printStackTrace();
            // return ERROR_XMLRPC;
            throw e;
        }

        return NO_ERROR;

    }



    /**
     * Creates a new mail that starts to work inmediatly
     * 
     * @param address
     *            Address of the new mail
     * @param inbox
     *            Name of the new mail's inbox
     * @param pass
     *            Password of the new mail
     * @return int with error code
     */
    public static int mailCreate(String address, String inbox, String pass)
            throws XMLRPCException
    {

        try
        {
            client.call("create_mailbox", new Object[] { sessionID, inbox });

            if (!pass.equals(""))
            {
                client.call("change_mailbox_password", new Object[] {
                        sessionID, inbox, pass });
            }

            client.call("create_email", new Object[] { sessionID, address,
                    inbox });
        }
        catch (XMLRPCException e)
        {
            e.printStackTrace();
            throw e;
        }

        return NO_ERROR;
    }



    public static String getUserName()
    {
        return username;
    }
}
