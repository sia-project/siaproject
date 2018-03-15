package model;

public class ProduitRepriseAvoir {
	
	private int repriseId;
	private int prodId;
	private int qteRetourne;
	public ProduitRepriseAvoir(int repriseId, int prodId, int qteRetourne) {
		this.repriseId = repriseId;
		this.prodId = prodId;
		this.qteRetourne = qteRetourne;
	}
	public int getRepriseId() {
		return repriseId;
	}
	public void setRepriseId(int repriseId) {
		this.repriseId = repriseId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getQteRetourne() {
		return qteRetourne;
	}
	public void setQteRetourne(int qteRetourne) {
		this.qteRetourne = qteRetourne;
	}
	
}
