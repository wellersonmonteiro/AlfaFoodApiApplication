package com.example.alfafoodapi.Notificador;

import com.example.alfafoodapi.Cliente;

public class NotificadorEmail {
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("Notificando %s através do email %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
