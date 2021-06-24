package com.os.linesystem.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.os.linesystem.enums.Perfil;
import com.os.linesystem.enums.Prioridade;
import com.os.linesystem.enums.Status;
import com.os.linesystem.model.Cliente;
import com.os.linesystem.model.Os;
import com.os.linesystem.model.Tecnico;
import com.os.linesystem.repositories.OsRepository;
import com.os.linesystem.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private OsRepository osRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));
		
		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
		

		Os c1 = new Os(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
		Os c2 = new Os(null, null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
		Os c3 = new Os(null, null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
		Os c4 = new Os(null, null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
		Os c5 = new Os(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
		Os c6 = new Os(null, null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		osRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
}
