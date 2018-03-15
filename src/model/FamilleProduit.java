package model;

public class familleProduit {
	private int familleProduitId;
	private String libelle;
	private String description;
	
	public familleProduit(int familleProduitId, String libelle, String description) {
		this.familleProduitId = familleProduitId;
		this.libelle = libelle;
		this.description = description;
	}
	public int getFamilleProduitId() {
		return familleProduitId;
	}
	public void setFamilleProduitId(int familleProduitId) {
		this.familleProduitId = familleProduitId;
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
