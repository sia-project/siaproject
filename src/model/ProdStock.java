package model;

public class ProdStock {
	private int prodId;
	private int stockId;
	private int qPhysique;
	private int qReserve;
	
	public ProdStock(int prodId, int stockId, int qPhysique, int qReserve) {
		this.prodId = prodId;
		this.stockId = stockId;
		this.qPhysique = qPhysique;
		this.qReserve = qReserve;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getqPhysique() {
		return qPhysique;
	}
	public void setqPhysique(int qPhysique) {
		this.qPhysique = qPhysique;
	}
	public int getqReserve() {
		return qReserve;
	}
	public void setqReserve(int qReserve) {
		this.qReserve = qReserve;
	}
	
}
