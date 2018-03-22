package model;

public class GammeProduit {
	private int familleProduitId;
	private String libelle;
	private String description;
	public GammeProduit(int familleProduitId, String libelle, String description) {
		this.familleProduitId = familleProduitId;
		this.libelle = libelle;
		this.description = description;
	}
	public GammeProduit() {
		super();
		// TODO Auto-generated constructor stub
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
