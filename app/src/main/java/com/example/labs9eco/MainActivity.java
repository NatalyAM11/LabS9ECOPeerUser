package com.example.labs9eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.labs9eco.model.Pedido;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener {

    private ImageView cereal, helado, sandwich, hotdog;

    //UDPConnection
    private UDPConnection udp;

    //Tipo de comida
    String pedidoo;


    //turno del pedido, lo dejamos en 0 para que sea el peer de wonka el que sume lso turnos
    int turno;

    String notificacion, ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cereal= findViewById(R.id.cereal);
        helado= findViewById(R.id.helado);
        sandwich= findViewById(R.id.sandwich);
        hotdog= findViewById(R.id.hotdog);

        cereal.setOnClickListener(this);
        helado.setOnClickListener(this);
        sandwich.setOnClickListener(this);
        hotdog.setOnClickListener(this);

        //Lo mandamos nulo para darle su respectivo valor en el peer de wonka
        ip=null;

        udp=new UDPConnection();
        udp.setObserver(this);
        udp.start();
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.cereal:
                pedidoo="cereal";
                break;
            case R.id.helado:
                pedidoo="helado";
                break;
            case R.id.sandwich:
                pedidoo="sandwich";
                break;
            case R.id.hotdog:
                pedidoo="hotdog";
                break;
        }


        //Gson
        Gson gson= new Gson();

        //Hora que se realizo el pedido
        Date date= new Date();

        SimpleDateFormat dFormat= new SimpleDateFormat("HH:mm:ss");
        String hora= dFormat.format(date);

        //Creo el pedido
        Pedido pedido= new Pedido(pedidoo, hora,turno,ip);
        String json= gson.toJson(pedido);

        //Lo envio
        udp.enviar(json);
        Log.e("<<<",json);

    }


    public void onMessage(String msg) {
    this.notificacion=msg;
    
    //Cuando el mensaje llegue, manda al cliente a la otra pantalla
    if(notificacion.equals("terminado")){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }

    }
}