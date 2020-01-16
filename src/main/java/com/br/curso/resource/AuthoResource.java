package com.br.curso.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.curso.security.JWTUtil;
import com.br.curso.security.UserSS;
import com.br.curso.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthoResource {
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse http){
		UserSS authenticated = UserService.authenticated();
		String token = jwtUtil.generateToken(authenticated.getUsername());
		http.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
