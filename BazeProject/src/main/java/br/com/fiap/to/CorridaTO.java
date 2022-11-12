package br.com.fiap.to;

public class CorridaTO {
	
	private int id;
	private String km;
	private String tempo;
	private int idUsuario;
	
	public CorridaTO() {}

	public CorridaTO(int id, String km, String tempo, int idUsuario) {
		this.id = id;
		this.km = km;
		this.tempo = tempo;
		this.idUsuario = idUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
