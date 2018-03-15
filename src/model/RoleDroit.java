package model;

public class RoleDroit {
	private int roleId;
	private int droitId;
	
	public RoleDroit(int roleId, int droitId) {
		this.roleId = roleId;
		this.droitId = droitId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDroitId() {
		return droitId;
	}
	public void setDroitId(int droitId) {
		this.droitId = droitId;
	}
}
