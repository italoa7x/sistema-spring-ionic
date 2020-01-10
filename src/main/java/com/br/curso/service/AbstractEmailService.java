package com.br.curso.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.br.curso.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessage(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação de pedido! N: " + pedido.getId());
		sm.setText(pedido.toString());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}
}
