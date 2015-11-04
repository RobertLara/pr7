/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr7app;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author ivan
 */
public class Pr7app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FileNotFoundException, FTPDataTransferException, FTPAbortedException {
        // TODO code application logic here

        //HTTP DOWNLOAD
        URL url = new URL("http://192.168.122.200/docs/ic10-m04-WindowsServer.pdf");   //Adreça del recurs
        url.openConnection();       //Obre la connexió
        InputStream reader = url.openStream();  //Crea String d'entrada del que llegeix
        FileOutputStream writer = new FileOutputStream("robert.pdf");    //nom del fitxer de sortida
        byte[] buffer = new byte[153600];   //Mida del buffer
        int bytesRead = 0;
        while ((bytesRead = reader.read(buffer)) > 0) {
            writer.write(buffer, 0, bytesRead);     //Desem al fitxer de sortida
        }
        writer.close(); //Tanquem fitxer de sortida
        reader.close(); //Tanquem fitxer d'entrada

        //FTP Upload
        
         FTPClient client = new FTPClient();    //Instància FTP
         client.connect("srv.toca.cat",21);    //IP de Connexió
         client.login("fulano", "Platano123");  //Dades de connexió
         client.upload(new java.io.File("robert.pdf"));
         client.disconnect(true);   //Tanquem la connexió
                
    }

}
