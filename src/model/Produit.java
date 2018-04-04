package model;

import java.io.Serializable;

public class Produit implements Serializable {

	private int prodId;
	private String libelle;
	private String marque;
	private String description;
	private double poids;
	private double prixHT;
	private int lot;
	private String placeRayon;
	private String typeTVA;
	private String destination;
	private int familleProduitId;
	private int gammeProduitId;
	private static int id = 1;
	
	public Produit(String libelle, String marque, String description, double poids, double prixHT, int lot,
			String placeRayon, String typeTVA, String destination, int familleProduitId, int gammeProduitId) {
		super();
		this.prodId = id;
		id++;
		this.libelle = libelle;
		this.marque = marque;
		this.description = description;
		this.poids = poids;
		this.prixHT = prixHT;
		this.lot = lot;
		this.placeRayon = placeRayon;
		this.typeTVA = typeTVA;
		this.destination = destination;
		this.familleProduitId = familleProduitId;
		this.gammeProduitId = gammeProduitId;
	}

	public int getFamilleProduitId() {
		return familleProduitId;
	}

	public void setFamilleProduitId(int familleProduitId) {
		this.familleProduitId = familleProduitId;
	}

	public int getGammeProduitId() {
		return gammeProduitId;
	}

	public void setGammeProduitId(int gammeProduitId) {
		this.gammeProduitId = gammeProduitId;
	}

	public Produit(String libelle, String marque, String description, double poids, double prixHT, int lot,
			String placeRayon, String typeTVA, String destination) {
		this.prodId = id;
		id++;
		this.libelle = libelle;
		this.marque = marque;
		this.description = description;
		this.poids = poids;
		this.prixHT = prixHT;
		this.lot = lot;
		this.placeRayon = placeRayon;
		this.typeTVA = typeTVA;
		this.destination = destination;
	}
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public double getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}
	public int getLot() {
		return lot;
	}
	public void setLot(int lot) {
		this.lot = lot;
	}
	public String getPlaceRayon() {
		return placeRayon;
	}
	public void setPlaceRayon(String placeRayon) {
		this.placeRayon = placeRayon;
	}
	public String getTypeTVA() {
		return typeTVA;
	}
	public void setTypeTVA(String typeTVA) {
		this.typeTVA = typeTVA;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
