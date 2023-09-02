package br.com.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "usuario")
public class Usuario {

	@Id
	Long cpf;

	String nome;

	String sobrenome;

	Integer idade;

	public Usuario() {
	}

	public Usuario(Long cpf, String nome, String sobrenome, Integer idade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
