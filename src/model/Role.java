package model;

public class Role {
	private int roleId;
	private String libelle;
	private String description;
		
	public Role(int roleId, String libelle, String description) {
		this.roleId = roleId;
		this.libelle = libelle;
		this.description = description;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
