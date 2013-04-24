package com.exception;

public class ObjetoInexistente extends Exception {
	
	private String mensagem;

	public ObjetoInexistente(String mensagem){
		this.mensagem=mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
