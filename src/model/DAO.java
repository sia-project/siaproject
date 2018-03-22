package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import security.BCrypt;
import security.Encryption;

public class DAO {
	

	private static int uId;
	private static String civilite;
	private static String nom;
	private static String prenom;
	private static String cle;
	private static String mdp;
	private static String sel;
	private static String mail;
	private static int role;
	
	private static int prodId;
	private static String libelle;
	private static String marque;
	private static String description;
	private static double poids;
	private static double prixHT;
	private static String typeTVA;
	private static int familleProduitId;
	private static int gammeProduitId;
	

	/**
	 * Authentication of user
	 * @param login
	 * @param password
	 * @return user
	 */
	public static Utilisateur authenticate(String login, String password) {
		Utilisateur user = null;
		if(getUserCredentials(login)) {
			String encryptedPasswordToTest = Encryption.encrypt(BCrypt.hashpw(password,sel), cle);
			if(encryptedPasswordToTest.equals(mdp)) 
			{
				user = new Utilisateur(uId, nom, prenom, mail, cle);
			}
		}
		return user;
	}
	
	/**
	 * Get user Credentials
	 * @param adrMail the userLogin
	 * @return true is user is found 
	 */
	public static boolean getUserCredentials(String adrMail) 
	{
		boolean b = false;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("SELECT uId, civilite, nom, prenom, mdp, cle, sel, roleId LIMIT 1"
														+"FROM UTILISATEUR"
														+"WHERE adrMail  = ? ");								
			req.setString(1, adrMail);
			
			ResultSet rs = req.executeQuery();
			while(rs.next()) 			
			{
				uId = rs.getInt(1);
				civilite = rs.getString(2);
				nom = rs.getString(3);
				prenom = rs.getString(4);
				mdp = rs.getString(5);
				cle = rs.getString(6);
				sel = rs.getString(7);
				role = rs.getInt(8);
				mail = adrMail;
				b = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return b;	
	}
	
	/**
	 * Update last connection time of a user
	 * @param userId the id of the user
	 */
	public static void updateLastConnectionDateTime(int userId) {
		java.util.Date date = new java.util.Date();
		Timestamp ts = new Timestamp(date.getTime());
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("UPDATE UTILISATEUR SET DATEDERCONNEX = ? WHERE UID = ?");
			req.setTimestamp(1, ts);
			req.setInt(2, userId);
			req.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the email in parameter already exists 
	 * @param email
	 * @return true if mail already exists
	 */
	public static boolean mailExists(String email) 
	{		
		boolean b = false;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("SELECT COUNT(*) FROM UTILISATEUR WHERE adrMail = ?");
			req.setString(1, email);
			ResultSet rs = req.executeQuery();
			rs.next();
			b = rs.getInt(1)>0;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * Creates user account 
	 * @param civilite
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param mdp
	 */
	public static void createAccount(String civilite, String nom, String prenom, String email, String mdp) {
		int lastIdUser=0;
		String salt = BCrypt.gensalt(14);
		String encryptedPassword = Encryption.encrypt(BCrypt.hashpw(mdp, salt));
		String generatedKey = Encryption.getGeneratedKey();
		java.util.Date date = new java.util.Date(); 
		Timestamp ts = new Timestamp(date.getTime());
		try {
			Connection con = Connect.get();
			lastIdUser = getLastIdUser();
			lastIdUser++;
			PreparedStatement req = con.prepareStatement("INSERT INTO UTILISATEUR (uId, civilite, nom, prenom, adrMail, mdp, cle, sel, dateCreationCompte, etatCompte) VALUES (?,?,?,?,?,?,?,?,?,?)");
			req.setInt(1, lastIdUser);
			req.setString(2, civilite);
			req.setString(3, nom);
			req.setString(4, prenom);
			req.setString(5, email);
			req.setString(6, encryptedPassword);
			req.setString(7, generatedKey);
			req.setString(8, salt);
			req.setTimestamp(9, ts);
			req.setInt(10, 0);
			req.executeUpdate();
		}catch(SQLException e) 
		{
			System.out.println("ERREUR SQL : "+e); 
		}

	}
	
	/**
	 * Get id of  last user which was created in DB
	 * @return id of last user inserted
	 */
	private static int getLastIdUser() {
		int lastIdUserInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(uId) AS lastId FROM UTILISATEUR");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdUserInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdUserInserted;
	}

	
	/**
	 * Get user by his email
	 * @param email user email
	 * @return Utilisateur
	 */
	public static Utilisateur getUserByMail(String email) {
		Utilisateur user = null;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("SELECT civilite,"
					+ 										   " nom,"
					+ 										   " prenom,"
					+ 										   " cle,"
					+                						   " FROM UTILISATEUR WHERE adrMail = ?");
			req.setString(1, email);
			ResultSet rs = req.executeQuery();
			while(rs.next()) {
				user = new Utilisateur(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						email,
						rs.getString(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
	private static int getLastIdProduct() {
		int lastIdProductInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(prodId) AS lastId FROM PRODUIT");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdProductInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdProductInserted;
	}
	
	
	/**
	 * Create product in database
	 * @param p the product
	 * @return the id of the product
	 */
	public static int createProduct (Produit p) {
		Connection con = Connect.get();
		int lastIdProduct=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdProduct = getLastIdProduct();
			lastIdProduct++;
			prepStmt = con.prepareStatement("INSERT INTO PRODUIT (ProdId, Libelle, Marque, Description, Poids, PrixHT, TypeTVA, FamilleProduitId, GammeProduitId) VALUES (?,?,?,?,?,?,?,?,?)");
			prepStmt.setInt(1, p.getProdId());
			prepStmt.setString(2, p.getLibelle());
			prepStmt.setString(3, p.getMarque());
			prepStmt.setString(4, p.getDescription());
			prepStmt.setDouble(5, p.getPoids());
			prepStmt.setDouble(6, p.getPrixHT());
			prepStmt.setString(7, p.getTypeTVA());
			prepStmt.setInt(8, p.getFamilleProduitId());			
			prepStmt.setInt(9, p.getGammeProduitId());
			prepStmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return 	lastIdProduct++;
	}
	
	public static Produit findById(int id) {
		Produit p=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 
		
		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " prodId,"
					+ 										   " libelle,"
					+ 										   " marque,"
					+ 										   " description,"
					+ 										   " poid,"
					+ 										   " prixHT,"
					+ 										   " lot,"
					+ 										   " placeRayon,"
					+ 										   " typeTVA,"
					+ 										   " destination,"
					+ 										   " familleProduit,"
					+ 										   " gammeProduitId,"

					+                						   " FROM UTILISATEUR WHERE prodId = id");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				p=new Produit();
				p.setProdId(rs.getInt(1));
				p.setLibelle(rs.getString(2));
				p.setMarque(rs.getString(3));
				p.setDescription(rs.getString(4));
				p.setPoids(rs.getDouble(5));
				p.setPrixHT(rs.getDouble(6));
				p.setLot(rs.getInt(7));
				p.setPlaceRayon(rs.getString(8));
				p.setTypeTVA(rs.getString(9));
				p.setDestination(rs.getString(10));
				p.setFamilleProduitId(rs.getInt(11));
				p.setGammeProduitId(rs.getInt(12));
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return p;
		
	}
	
	public void updateProduit(Produit p) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE UTILISATEUR SET "
					+										   " prodId = ?,"
					+ 										   " libelle = ?,"
					+ 										   " marque = ?,"
					+ 										   " description = ?,"
					+ 										   " poid = ?,"
					+ 										   " prixHT = ?,"
					+ 										   " lot = ?,"
					+ 										   " placeRayon = ?,"
					+ 										   " typeTVA = ?,"
					+ 										   " destination = ?,"
					+ 										   " familleProduit = ?,"
					+ 										   " gammeProduitId = ?,"
					+ "WHERE prodId = ? ");
			prepStmt.setInt(1, p.getProdId());
			prepStmt.setString(2, p.getLibelle());
			prepStmt.setString(3, p.getMarque());
			prepStmt.setString(4, p.getDescription());
			prepStmt.setDouble(5, p.getPoids());
			prepStmt.setDouble(6, p.getPrixHT());
			prepStmt.setString(7, p.getTypeTVA());
			prepStmt.setInt(8, p.getFamilleProduitId());			
			prepStmt.setInt(9, p.getGammeProduitId());
						
			prepStmt.setInt(10, p.getProdId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Produit ID :"+p.getProdId());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	
	public void deleteProduit(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM UTILISATEUR WHERE ProdId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			
			

		} catch (Exception e) {
			
		}finally {
			
		}
	}
	
	private static int getLastIdProductGamme() {
		int lastIdProductGammeInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(familleProduitId) AS lastId FROM GammeProduit");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdProductGammeInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdProductGammeInserted;
	}
	
	
	
	public static int createGammeProduit (int familleProduitId ,String  libelle, String description) {
		Connection con = Connect.get();
		int lastIdProductGamme=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdProductGamme = getLastIdProductGamme();
			lastIdProductGamme++;
			prepStmt = con.prepareStatement("INSERT INTO gammeProduit (gammeProduitId, libelle, description) VALUES (?,?,?)");
			prepStmt.setInt(1, familleProduitId);
			prepStmt.setString(2, libelle);
			prepStmt.setString(3, description);
			
			prepStmt.executeUpdate();
			
		}catch (Exception e) {
			
		}finally {
			
		}
		return 	lastIdProductGamme++;
		
		
		
	}
	public static GammeProduit findGPById(int id) {
		Connection con = Connect.get();
		ResultSet rs=null; // 
		GammeProduit gp=null;
		
		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " gammeProduitId,"
					+ 										   " libelle,"
					+ 										   " description,"
					
					+                						   " FROM gammeProduit WHERE gammeProduitId = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				gp = new GammeProduit();
				gp.setFamilleProduitId(rs.getInt(1));
				gp.setLibelle(rs.getString(2));
				gp.setDescription(rs.getString(3));
				
			}
					
		}catch (Exception e) {
			
		}finally {
			
		}
		return gp;
		
	}	
	
	public void deleteGammeProduit(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM gammeProduit WHERE gammeProduitId= ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	
	private static int getLastIdFamille() {
		int lastIdFamille = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(familleProduitId) AS lastId FROM familleproduit");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdFamille = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdFamille;
	}
	
	public static String createFamille (FamilleProduit f) {
		Connection con = Connect.get();
		String pid=null; 
		PreparedStatement prepStmt=null;
		try {
			int id = getLastIdFamille();
			prepStmt=con.prepareStatement("insert into familleproduit (familleProduitId,libelle,description) VALUES (?,?,?)");
			int i=1;
			prepStmt.setInt(i++, id+1);
			prepStmt.setString(i++, f.getLibelle());
			prepStmt.setString(i++, f.getDescription());

			prepStmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pid;
	}
	
	public static FamilleProduit findByIdProduit(int id) {
		FamilleProduit f =null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 
		
		PreparedStatement prepStmt=null;
		try {
			prepStmt=con.prepareStatement("select * from familleproduit f where f.familleProduitId");
			prepStmt.setInt(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				f=new FamilleProduit(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public void deleteFamille(int familleProduitId) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {
			prepStmt=con.prepareStatement("delete from familleproduit f where f.familleProduitId = ?");
			prepStmt.setInt(1, familleProduitId);
			prepStmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	public void updateFamille(int id, String libelle, String description) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement("update familleproduit set libelle = ?, description = ? where familleProduitId = ?");
			int i=1;
			prepStmt.setString(i++, libelle);
			prepStmt.setString(i++, description);
			prepStmt.setInt(i++, id);

			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : FamilleProduit ID :"+ id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Collection<Produit> getSetProduit(){
		Collection<Produit> collect = new HashSet<Produit>();
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement("select * from produit p");
			
			ResultSet res = prepStmt.executeQuery();
			
			while(res.next()) {
				collect.add(new Produit(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDouble(5), res.getDouble(6),
						res.getInt(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11), res.getInt(12)));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return collect;
	}
	
	public Collection<FamilleProduit> getSetFamille(){
		Collection<FamilleProduit> collect = new HashSet<FamilleProduit>();
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement("select * from familleproduit f");
			
			ResultSet res = prepStmt.executeQuery();
			
			while(res.next()) {
				collect.add(new FamilleProduit(res.getInt(1), res.getString(2), res.getString(3)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return collect;
	}
	
	public Collection<GammeProduit> getSetGamme(){
		Collection<GammeProduit> collect = new HashSet<GammeProduit>();
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement("select * from gammeproduit g");
			
			ResultSet res = prepStmt.executeQuery();
			
			while(res.next()) {
				collect.add(new GammeProduit(res.getInt(1), res.getString(2), res.getString(3)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return collect;
	}
}

