package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	@Autowired
	private NotificadorProprties proprties;

	private Integer porta;

	public NotificadorEmail(){
		System.out.println("NotificadosEmail real");
	}
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.println("Host: " + proprties.getHostServidor());
		System.out.println("Porta: " + proprties.getPortaServidor());
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

}
