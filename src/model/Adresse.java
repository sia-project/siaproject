package model;

public class Adresse {
	private int adrId;
	private String rue;
	private String cp;
	private String ville;
	private String pays;
	
	public Adresse(int adrId, String rue, String cp, String ville, String pays) {
		this.adrId = adrId;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
	}
	public Adresse(String rue, String cp, String ville, String pays) {
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
	}
	
	public int getAdrId() {
		return adrId;
	}
	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
}
