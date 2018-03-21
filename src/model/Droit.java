package model;

public class Droit {
	private int droitId;
	private String libelle;
	private String description;
		
	public Droit(int droitId, String libelle, String description) {
		this.droitId = droitId;
		this.libelle = libelle;
		this.description = description;
	}
	public int getDroitId() {
		return droitId;
	}
	public void setDroitId(int droitId) {
		this.droitId = droitId;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
