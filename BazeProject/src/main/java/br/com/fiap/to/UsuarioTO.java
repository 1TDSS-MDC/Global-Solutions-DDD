package br.com.fiap.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioTO {

	/**
	 * Variaveis
	 */
	private int id;
	private String nome;
	private int idade;
	private String cpf;
	private String rg;
	private String genero;
	private String email;
	private String login;
	private String senha;
	private String peso;
	private String altura;
	private String status;
	private EnderecoTO enderecoTo;
	private TelefoneTO telefoneTo;
	
	//Construtor vazio
	public UsuarioTO() {}

	/**
	 * Construtor com parametros
	 */
	public UsuarioTO(int id, String nome, int idade, String cpf, String rg, String genero, String email,String login, String senha,
			String peso, String altura, String status) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.rg = rg;
		this.genero = genero;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.peso = peso;
		this.altura = altura;
		this.status = status;
	}
	
	/**
	 * Construtor que recebe apenas os parametros de id, login e senha do usuario
	 * @param id
	 * @param login
	 * @param senha
	 */
	public UsuarioTO(int id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}
	
	
	/**
	 * Construtores com parametros referenciando as classes endereco e telefone
	 * @param enderecoTo
	 * @param telefoneTo
	 */
	public UsuarioTO(EnderecoTO enderecoTo, TelefoneTO telefoneTo) {
		super();
		this.enderecoTo = enderecoTo;
		this.telefoneTo = telefoneTo;
	}

	/**
	 * Getters and Setters 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public EnderecoTO getEnderecoTo() {
		return enderecoTo;
	}

	public void setEnderecoTo(EnderecoTO enderecoTo) {
		this.enderecoTo = enderecoTo;
	}

	public TelefoneTO getTelefoneTo() {
		return telefoneTo;
	}

	public void setTelefoneTo(TelefoneTO telefoneTo) {
		this.telefoneTo = telefoneTo;
	}

	/**
	 * Anotação override
	 */
	@Override
	public String toString() {
		return login + " " + senha;
	}
}
