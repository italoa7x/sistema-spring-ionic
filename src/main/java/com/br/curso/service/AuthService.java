package com.br.curso.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.curso.domain.Cliente;
import com.br.curso.repository.ClienteRepository;
import com.br.curso.service.exception.ObjectNotFoundException;

@Service
public class AuthService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypte;
	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		Cliente cli = clienteRepository.findByEmail(email);
		if (cli == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		String newPass = newPassword();
		cli.setSenha(bcrypte.encode(newPass));
		clienteRepository.save(cli);
		emailService.sendNewPasswordEmail(cli, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {// gera 1 digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiscula
			return (char) (random.nextInt(26) + 65);
		} else { // gera letra minuscular
			return (char) (random.nextInt(36) + 97);
		}

	}

}
