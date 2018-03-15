package model;

public class LigneCommande {
	private int cmdId;
	private int prodId;
	private int quantite;
	
	public LigneCommande(int cmdId, int prodId, int quantite) {
		this.cmdId = cmdId;
		this.prodId = prodId;
		this.quantite = quantite;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
