package br.com.fiap.to;

public class BicicletaTO {

	private int id;
	private String aro;
	private int quadro;
	private String peso;
	private String cor;
	private String tipo;
	private int marcha;
	private int idUsuario;
	
	//Construtor com parametros
	public BicicletaTO(int id, String aro, int quadro, String peso, String cor, String tipo, int marcha, int idUsuario) {
		this.id = id;
		this.aro = aro;
		this.quadro = quadro;
		this.peso = peso;
		this.cor = cor;
		this.tipo = tipo;
		this.marcha = marcha;
		this.idUsuario = idUsuario;
		
	}

	//Construtor vazio
	public BicicletaTO() {}

	//Getters and Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAro() {
		return aro;
	}


	public void setAro(String aro) {
		this.aro = aro;
	}


	public int getQuadro() {
		return quadro;
	}


	public void setQuadro(int quadro) {
		this.quadro = quadro;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getMarcha() {
		return marcha;
	}


	public void setMarcha(int marcha) {
		this.marcha = marcha;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}