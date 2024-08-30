/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smtp.clas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author rodri
 */
public class Cliente {

    static final String HOST = "localhost";
    // static final String HOST = "172.20.172.250";
    //static final String HOST = "www.tecnoweb.org.bo";
    static final int PUERTO = 5000;
    //static final int PUERTO = 25;

    public Cliente() {

        try {
            Socket skCliente = new Socket(HOST, PUERTO);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));

            System.out.println("C: Conectado a <" + HOST + ">");
            System.out.println("si conecto al parecer");
            System.out.println("S: " + entrada.readLine());

            skCliente.close();

            System.out.println("C: Desconectado del <" + HOST + ">");

        } catch (IOException e) {
            System.out.println("C: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //System.out.println("hola cliente");
        // TODO Auto-generated method stub
        Cliente c = new Cliente();

    }

}
