package com.modelo.entidade;

import java.util.Date;

public class Professor extends Pessoa {

	public Professor(String nome, String email, Date nascimento) {
		setNome(nome);
		setEmail(email);
		setNascimento(nascimento);
	}

	public Professor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Pessoa pessoa) {
		Professor professor = (Professor) pessoa;
		return this.getNome().compareTo(professor.getNome());
	}

}
