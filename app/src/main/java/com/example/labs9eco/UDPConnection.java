package com.example.labs9eco;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread {

    private DatagramSocket socket;


    //Observer
    private OnMessageListener observer;

    public void setObserver (OnMessageListener observer){
        this.observer=observer;
    }


    //Hilo recepcion
    public void run(){

        try {

            socket=new DatagramSocket(5000);

            //Recepción de mensajes

            //Recepción mensajes

            while(true) {
                byte []buffer= new byte[50];
                DatagramPacket packet= new DatagramPacket(buffer,buffer.length);

                Log.e(">>","Esperando datagrama");
                socket.receive(packet);


                //Convierto los bytes a String
                String mensaje= new String(packet.getData()).trim();

                observer.onMessage(mensaje);


            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //Metodo envio de datagramas
    public void enviar(String mensaje){

        new Thread(
                ()->{

                    try {
                        InetAddress ip= InetAddress.getByName("192.168.0.8");

                        DatagramPacket packet= new DatagramPacket(mensaje.getBytes(),mensaje.getBytes().length,ip,6000);
                        socket.send(packet);

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

}
