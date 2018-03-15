package model;

public class Stock {
	private int stockId;
	private String typeStock;

	public Stock(int stockId, String typeStock) {
		this.stockId = stockId;
		this.typeStock = typeStock;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getTypeStock() {
		return typeStock;
	}
	public void setTypeStock(String typeStock) {
		this.typeStock = typeStock;
	}
	
	
}
