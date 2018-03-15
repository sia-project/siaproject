package model;

import java.util.Date;

public class Livraison {
	private int livraisonId;
	private Date dateColisage;
	private Date dateExpedition;
	private int delaisLivraison;
	private int transporteurId;
	private int cmdId;
	
	public Livraison(int livraisonId, Date dateColisage, Date dateExpedition, int delaisLivraison, int transporteurId,
			int cmdId) {
		this.livraisonId = livraisonId;
		this.dateColisage = dateColisage;
		this.dateExpedition = dateExpedition;
		this.delaisLivraison = delaisLivraison;
		this.transporteurId = transporteurId;
		this.cmdId = cmdId;
	}
	public int getLivraisonId() {
		return livraisonId;
	}
	public void setLivraisonId(int livraisonId) {
		this.livraisonId = livraisonId;
	}
	public Date getDateColisage() {
		return dateColisage;
	}
	public void setDateColisage(Date dateColisage) {
		this.dateColisage = dateColisage;
	}
	public Date getDateExpedition() {
		return dateExpedition;
	}
	public void setDateExpedition(Date dateExpedition) {
		this.dateExpedition = dateExpedition;
	}
	public int getDelaisLivraison() {
		return delaisLivraison;
	}
	public void setDelaisLivraison(int delaisLivraison) {
		this.delaisLivraison = delaisLivraison;
	}
	public int getTransporteurId() {
		return transporteurId;
	}
	public void setTransporteurId(int transporteurId) {
		this.transporteurId = transporteurId;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
}
