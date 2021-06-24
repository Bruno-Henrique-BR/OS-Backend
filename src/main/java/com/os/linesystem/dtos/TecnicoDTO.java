package com.os.linesystem.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.os.linesystem.enums.Perfil;
import com.os.linesystem.model.Tecnico;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "Campo NOME é requerido")
	private String nome;

	@CPF
	@NotNull(message = "Campo CPF é requerido")
	private String cpf;

	@Email
	@NotNull(message = "Campo E-MAIL é requerido")
	private String email;

	@NotNull(message = "Campo SENHA é requerido")
	private String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao;
	//private DateTimeFormatter dataCriacao = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 


	private Set<Integer> perfis = new HashSet<>();

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.dataCriacao = obj.getDataCriacao();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}

}
