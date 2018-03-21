package model;

import java.util.Date;

public class FraisDePort {
	
	private int fraisPortId;
	private Date dateDebut;
	private Date dateFin;
	private double montant;
	
	public FraisDePort(int fraisPortId, Date dateDebut, Date dateFin, double montant) {
		super();
		this.fraisPortId = fraisPortId;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.montant = montant;
	}
	public int getFraisPortId() {
		return fraisPortId;
	}
	public void setFraisPortId(int fraisPortId) {
		this.fraisPortId = fraisPortId;
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
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
}
