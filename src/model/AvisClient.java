package model;

import java.util.Date;

public class AvisClient {

	private int avisId;
	private double note;
	private Date dateAvis;
	private String commentaire;
	private int uId;
	private int pId;
	public AvisClient(int avisId, double note, Date dateAvis, String commentaire, int uId, int pId) {
		this.avisId = avisId;
		this.note = note;
		this.dateAvis = dateAvis;
		this.commentaire = commentaire;
		this.uId = uId;
		this.pId = pId;
	}
	public int getAvisId() {
		return avisId;
	}
	public void setAvisId(int avisId) {
		this.avisId = avisId;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public Date getDateAvis() {
		return dateAvis;
	}
	public void setDateAvis(Date dateAvis) {
		this.dateAvis = dateAvis;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
}
