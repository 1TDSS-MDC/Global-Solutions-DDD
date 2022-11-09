package br.com.fiap.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TelefoneTO {

	private int id;
	private int celular;
	private int ddd;
	private int ddi;
	
	/**
	 * Construtor vazio
	 */
	public TelefoneTO() {}

	/**
	 * Construtor com parametros
	 * @param
	 */
	
	public TelefoneTO(int id, int celular, int ddd, int ddi) {
		super();
		this.id = id;
		this.celular = celular;
		this.ddd = ddd;
		this.ddi = ddi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
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
}
