package model;

import java.util.Date;

public class Utilisateur {

	
	private int uId;
	private String civilite;
	private String nom;
	private String prenom;
	private Date dateNaiss;
	private String telPrincipal;
	private String telSecondaire;
	private String adrMail;
	private int entId;
	private double mtMaxCmd;
	private String mdp;
	private String cle;
	private String sel;
	private Date dateCreationCpte;
	private Date dateDerConnex;
	private Date dateDerModif;
	private int etatCompte;
	private String typeUtilisateur;
	private int roleId;
	
	
	public Utilisateur(int uId, String nom, String prenom, String adrMail, String cle) 
	{
		this.uId = uId;
		this.nom = nom;
		this.prenom = prenom;
		this.adrMail = adrMail;
		this.cle = cle;
	}
	
	public Utilisateur(String civilite, String nom, String prenom, String adrMail, String cle) 
	{
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.adrMail = adrMail;
		this.cle = cle;
	}
	
	public Utilisateur(int uId, String civilite, String nom, String prenom, Date dateNaiss, String telPrincipal,
			String telSecondaire, String adrMail, int entId, double mtMaxCmd, String mdp, String cle, String sel,
			Date dateCreationCpte, Date dateDerConnex, Date dateDerModif, int etatCompte, String typeUtilisateur,
			int roleId) {
		this.uId = uId;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.telPrincipal = telPrincipal;
		this.telSecondaire = telSecondaire;
		this.adrMail = adrMail;
		this.entId = entId;
		this.mtMaxCmd = mtMaxCmd;
		this.mdp = mdp;
		this.cle = cle;
		this.sel = sel;
		this.dateCreationCpte = dateCreationCpte;
		this.dateDerConnex = dateDerConnex;
		this.dateDerModif = dateDerModif;
		this.etatCompte = etatCompte;
		this.typeUtilisateur = typeUtilisateur;
		this.roleId = roleId;
	}
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public String getTelPrincipal() {
		return telPrincipal;
	}
	public void setTelPrincipal(String telPrincipal) {
		this.telPrincipal = telPrincipal;
	}
	public String getTelSecondaire() {
		return telSecondaire;
	}
	public void setTelSecondaire(String telSecondaire) {
		this.telSecondaire = telSecondaire;
	}
	public String getAdrMail() {
		return adrMail;
	}
	public void setAdrMail(String adrMail) {
		this.adrMail = adrMail;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public double getMtMaxCmd() {
		return mtMaxCmd;
	}
	public void setMtMaxCmd(double mtMaxCmd) {
		this.mtMaxCmd = mtMaxCmd;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getCle() {
		return cle;
	}
	public void setCle(String cle) {
		this.cle = cle;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	public Date getDateCreationCpte() {
		return dateCreationCpte;
	}
	public void setDateCreationCpte(Date dateCreationCpte) {
		this.dateCreationCpte = dateCreationCpte;
	}
	public Date getDateDerConnex() {
		return dateDerConnex;
	}
	public void setDateDerConnex(Date dateDerConnex) {
		this.dateDerConnex = dateDerConnex;
	}
	public Date getDateDerModif() {
		return dateDerModif;
	}
	public void setDateDerModif(Date dateDerModif) {
		this.dateDerModif = dateDerModif;
	}
	public int getEtatCompte() {
		return etatCompte;
	}
	public void setEtatCompte(int etatCompte) {
		this.etatCompte = etatCompte;
	}
	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
