package model;

public class CommandeMultipleUtilisateur {
	private int cmdId;
	private int uId;
	
	public CommandeMultipleUtilisateur(int cmdId, int uId) {
		this.cmdId = cmdId;
		this.uId = uId;
	}

	public int getCmdId() {
		return cmdId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}
	
	
}
