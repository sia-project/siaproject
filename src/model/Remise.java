package model;

import java.util.Date;

public class Remise {
	
	private int remise;
	private Date dateDebut;
	private Date dateFin;
	private double pourcentageRemise;
	private int prodId;
	private int familleProduitId;
	public Remise(int remise, Date dateDebut, Date dateFin, double pourcentageRemise, int prodId,
			int familleProduitId) {
		this.remise = remise;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.pourcentageRemise = pourcentageRemise;
		this.prodId = prodId;
		this.familleProduitId = familleProduitId;
	}
	public int getRemise() {
		return remise;
	}
	public void setRemise(int remise) {
		this.remise = remise;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public double getPourcentageRemise() {
		return pourcentageRemise;
	}
	public void setPourcentageRemise(double pourcentageRemise) {
		this.pourcentageRemise = pourcentageRemise;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getFamilleProduitId() {
		return familleProduitId;
	}
	public void setFamilleProduitId(int familleProduitId) {
		this.familleProduitId = familleProduitId;
	}
}
