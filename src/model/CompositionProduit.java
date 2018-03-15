package model;

public class CompositionProduit {
	private int produitComposeId;
	private int produitComposantId;
	public int getProduitComposeId() {
		return produitComposeId;
	}
	public void setProduitComposeId(int produitComposeId) {
		this.produitComposeId = produitComposeId;
	}
	public int getProduitComposantId() {
		return produitComposantId;
	}
	public void setProduitComposantId(int produitComposantId) {
		this.produitComposantId = produitComposantId;
	}
	public CompositionProduit(int produitComposeId, int produitComposantId) {
		this.produitComposeId = produitComposeId;
		this.produitComposantId = produitComposantId;
	}
}
