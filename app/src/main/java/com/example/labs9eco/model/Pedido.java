package com.example.labs9eco.model;

public class Pedido {

    String pedido;
    String hora, ip;
    int turno;


    public Pedido ( String pedido, String hora, int turno, String ip){
        this.pedido=pedido;
        this.hora=hora;
        this.turno=turno;
        this.ip=ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}