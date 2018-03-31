package model;

import java.util.Date;

public class Facture {
	
	private int factId;
	private Date dateFacturation;
	private Date dateEcheance;
	private double prixHt;
	private double remise;
	private int cmdId;
	
	public Facture(int factId, Date dateFacturation, Date dateEcheance, double prixHt, double remise, int cmdId) {
		super();
		this.factId = factId;
		this.dateFacturation = dateFacturation;
		this.dateEcheance = dateEcheance;
		this.prixHt = prixHt;
		this.remise = remise;
		this.cmdId = cmdId;
	}
	
	public int getFactId() {
		return factId;
	}
	public void setFactId(int factId) {
		this.factId = factId;
	}
	public Date getDateFacturation() {
		return dateFacturation;
	}
	public void setDateFacturation(Date dateFacturation) {
		this.dateFacturation = dateFacturation;
	}
	public Date getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public double getPrixHt() {
		return prixHt;
	}
	public void setPrixHt(double prixHt) {
		this.prixHt = prixHt;
	}
	public double getRemise() {
		return remise;
	}
	public void setRemise(double remise) {
		this.remise = remise;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

}
