package model;

public class ClientAdresse {
	
	private int uId;
	private int adrId;
	private String typeAdresse;
	
	public ClientAdresse(int uId, int adrId, String typeAdresse) {
		super();
		this.uId = uId;
		this.adrId = adrId;
		this.typeAdresse = typeAdresse;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getAdrId() {
		return adrId;
	}
	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}
	public String getTypeAdresse() {
		return typeAdresse;
	}
	public void setTypeAdresse(String typeAdresse) {
		this.typeAdresse = typeAdresse;
	}
	
}
