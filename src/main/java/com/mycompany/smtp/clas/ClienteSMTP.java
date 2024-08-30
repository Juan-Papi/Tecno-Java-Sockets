/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smtp.clas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author rodri
 */
public class ClienteSMTP {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String servidor = "mail.ficct.uagrm.edu.bo";
        //String servidor = "www.tecnoweb.org.bo";
        //String servidor="172.20.172.254";
        // String user_receptor = "grupo01sa@ficct.uagrm.edu.bo";
        String user_receptor = "grupo021sa@ficct.uagrm.edu.bo";
        String user_emisor = "evansbv@gmail.com";
        String line;
        String comando = "";
        int puerto = 25;

        try {
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine());

                comando = "EHLO " + servidor + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada));

                comando = "MAIL FROM: " + user_emisor + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "RCPT TO: " + user_receptor + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "DATA\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada));

                comando = "Subject: DEMO X\r\n"
                        + "\r\n"
                        + "Probando el envio de mensajes.\r\n"
                        + ".\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());
            }

            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : No se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//Permite Leer multiples lineas del Protocolo SMTP
    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
