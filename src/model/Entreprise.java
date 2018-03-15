package model;

public class Entreprise {
	
	private int entId;
	private String raisonSociale;
	private String nom;
	private String siret;
	private String ape;
	private String activitePrincipale;
	private int nbEmployes;
	private int adrId;
	
	
	
	public Entreprise(int entId, String raisonSociale, String nom, String siret, String ape, String activitePrincipale,
			int nbEmployes, int adrId) {
		this.entId = entId;
		this.raisonSociale = raisonSociale;
		this.nom = nom;
		this.siret = siret;
		this.ape = ape;
		this.activitePrincipale = activitePrincipale;
		this.nbEmployes = nbEmployes;
		this.adrId = adrId;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getApe() {
		return ape;
	}
	public void setApe(String ape) {
		this.ape = ape;
	}
	public String getActivitePrincipale() {
		return activitePrincipale;
	}
	public void setActivitePrincipale(String activitePrincipale) {
		this.activitePrincipale = activitePrincipale;
	}
	public int getNbEmployes() {
		return nbEmployes;
	}
	public void setNbEmployes(int nbEmployes) {
		this.nbEmployes = nbEmployes;
	}
	public int getAdrId() {
		return adrId;
	}
	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}
}
