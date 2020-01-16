package com.br.curso.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.br.curso.domain.Cliente;
import com.br.curso.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired(required = false)
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepareMemeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMemeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mime, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado, Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mime;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cli, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cli, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cli, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cli.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitaçaõ de nova senha");
		sm.setText("Nova senha: " + newPass);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}
}
