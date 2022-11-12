package br.com.fiap.to;

public class BicicletaEletricaTO {

	private int id;
	private int bateria;
	private String motor;
	private String aro;
	private int quadro;
	private String peso;
	private String cor;
	private int idUsuario;
	

	//Construtor com parametros
	public BicicletaEletricaTO(int id, int bateria, String motor, String aro, int quadro, String peso, String cor , int idUsuario) {
		this.id = id;
		this.bateria = bateria;
		this.motor = motor;
		this.aro = aro;
		this.quadro = quadro;
		this.peso = peso;
		this.cor = cor;
		this.idUsuario = idUsuario;
	}

	//Construtor vazio
	public BicicletaEletricaTO() {}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBateria() {
		return bateria;
	}

	public void setBateria(int bateria) {
		this.bateria = bateria;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
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
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setCor(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}