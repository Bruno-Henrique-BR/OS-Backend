package com.os.linesystem.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.os.linesystem.dtos.TecnicoDTO;
import com.os.linesystem.enums.Perfil;

@Entity
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Os> os = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}
	 
	public Tecnico(TecnicoDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.dataCriacao = obj.getDataCriacao();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toSet());
		addPerfil(Perfil.CLIENTE);
	}

	public List<Os> getChamados() {
		return os;
	}

	public void setChamados(List<Os> os) {
		this.os = os;
	}

}
