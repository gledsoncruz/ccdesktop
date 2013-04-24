package com.exception;


public class ClassNotImplemented extends RuntimeException {


	private String mensagem;
	
	public ClassNotImplemented(String mensagem) {

		this.mensagem=mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
