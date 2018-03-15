package model;

public class Boutique {

	private int btqId;
	private String description;
	private int adrId;
	private int stockId;
	public Boutique(int btqId, String description, int adrId, int stockId) {
		this.btqId = btqId;
		this.description = description;
		this.adrId = adrId;
		this.stockId = stockId;
	}
	public int getBtqId() {
		return btqId;
	}
	public void setBtqId(int btqId) {
		this.btqId = btqId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAdrId() {
		return adrId;
	}
	public void setAdrId(int adrId) {
		this.adrId = adrId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	
}
