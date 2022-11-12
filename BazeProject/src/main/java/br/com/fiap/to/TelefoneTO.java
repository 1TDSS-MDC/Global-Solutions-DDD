package br.com.fiap.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TelefoneTO {

	private int id;
	private String celular;
	private int ddd;
	private int ddi;
	private int idUsuario;
	
	/**
	 * Construtor vazio
	 */
	public TelefoneTO() {}

	/**
	 * Construtor com parametros
	 * @param
	 */
	
	public TelefoneTO(int id, String celular, int ddd, int ddi, int idUsuario) {
		this.id = id;
		this.celular = celular;
		this.ddd = ddd;
		this.ddi = ddi;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getDdi() {
		return ddi;
	}

	public void setDdi(int ddi) {
		this.ddi = ddi;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}