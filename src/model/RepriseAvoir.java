package model;

public class RepriseAvoir {
	
	private int repriseId;
	private int numAuthRetour;
	private int cmdId;
	public RepriseAvoir(int repriseId, int numAuthRetour, int cmdId) {
		super();
		this.repriseId = repriseId;
		this.numAuthRetour = numAuthRetour;
		this.cmdId = cmdId;
	}
	public int getRepriseId() {
		return repriseId;
	}
	public void setRepriseId(int repriseId) {
		this.repriseId = repriseId;
	}
	public int getNumAuthRetour() {
		return numAuthRetour;
	}
	public void setNumAuthRetour(int numAuthRetour) {
		this.numAuthRetour = numAuthRetour;
	}
	public int getCmdId() {
		return cmdId;
	}
	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}
	
}
