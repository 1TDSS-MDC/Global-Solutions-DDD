package br.com.fiap.exception;

public class IdNotFoundException extends Exception {

public IdNotFoundException() {}
	
	public IdNotFoundException(String mensagem) {
		super(mensagem);
	}
}
