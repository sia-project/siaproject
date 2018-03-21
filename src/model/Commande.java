package model;

import java.util.Date;

public class Commande {
	private int cmdId;
	private Date dateCmd;
	private String etatCmd;
	private String modeLivraison;
	private String moyenPriseCmd;
	private int adrLivId;
	private int adrFactId;
	private int userId;
	private int fraisPortId;

	public Commande(int cmdId, Date dateCmd, String etatCmd, String modeLivraison, String moyenPriseCmd, int adrLivId,
			int adrFactId, int userId, int fraisPortId) {
		this.cmdId = cmdId;
		this.dateCmd = dateCmd;
		this.etatCmd = etatCmd;
		this.modeLivraison = modeLivraison;
		this.moyenPriseCmd = moyenPriseCmd;
		this.adrLivId = adrLivId;
		this.adrFactId = adrFactId;
		this.userId = userId;
		this.fraisPortId = fraisPortId;
	}
	public int getAdrLivId() {
		return adrLivId;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
	public Date getDateCmd() {
		return dateCmd;
	}
	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}
	public String getEtatCmd() {
		return etatCmd;
	}
	public void setEtatCmd(String etatCmd) {
		this.etatCmd = etatCmd;
	}
	public String getModeLivraison() {
		return modeLivraison;
	}
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	public String getMoyenPriseCmd() {
		return moyenPriseCmd;
	}
	public void setMoyenPriseCmd(String moyenPriseCmd) {
		this.moyenPriseCmd = moyenPriseCmd;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFraisPortId() {
		return fraisPortId;
	}
	public void setFraisPortId(int fraisPortId) {
		this.fraisPortId = fraisPortId;
	}
	public void setAdrLivId(int adrLivId) {
		this.adrLivId = adrLivId;
	}
	public int getAdrFactId() {
		return adrFactId;
	}
	public void setAdrFactId(int adrFactId) {
		this.adrFactId = adrFactId;
	}
}
