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

import com.sun.jndi.toolkit.dir.LazySearchEnumerationImpl;

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
	 * Get user  secure key by his email
	 * @param email user email
	 * @return String 
	 */
	public static String getUserKeyByMail(String email) {
		String key=null;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("SELECT cle FROM UTILISATEUR WHERE adrMail = ?");
			req.setString(1, email);
			ResultSet rs = req.executeQuery();
			while(rs.next()) {
				key = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return key;
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
	public static void updateProduit(Produit p) {
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



	public static void deleteProduit(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM PRODUIT WHERE ProdId = ?");
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



	public static int createGammeProduit (int gammeProduitId ,String  libelle, String description) {
		Connection con = Connect.get();
		int lastIdProductGamme=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdProductGamme = getLastIdProductGamme();
			lastIdProductGamme++;
			prepStmt = con.prepareStatement("INSERT INTO gammeProduit (gammeProduitId, libelle, description) VALUES (?,?,?)");
			prepStmt.setInt(1, gammeProduitId);
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

	public static void createFamille (FamilleProduit f) {
		Connection con = Connect.get();
		String pid=null; 
		int lastIdFamilleProduit;
		PreparedStatement prepStmt=null;
		try {
			lastIdFamilleProduit = getLastIdFamille();
			lastIdFamilleProduit++;
			int id = getLastIdFamille();
			prepStmt=con.prepareStatement("insert into familleproduit (familleProduitId,libelle,description) VALUES (?,?,?)");
			prepStmt.setInt(1, lastIdFamilleProduit);
			prepStmt.setString(2, f.getLibelle());
			prepStmt.setString(3, f.getDescription());

			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}
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

	public static void deleteFamille(int familleProduitId) throws Exception {
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

	public static void updateFamille(FamilleProduit f) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement("update familleproduit set libelle = ?, description = ? where familleProduitId = ?");
			prepStmt.setString(1, f.getLibelle());
			prepStmt.setString(2, f.getDescription());
			prepStmt.setInt(3, f.getFamilleProduitId());

			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : FamilleProduit ID :"+ f.getFamilleProduitId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Collection<Produit> getSetProduit(){
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

	public static Collection<FamilleProduit> getSetFamille(){
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

	public static Collection<GammeProduit> getSetGamme(){
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
	// Commande 
	private static int getLastIdCommande() {
		int lastIdCommandeInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(cmdId) AS lastId FROM COMMANDE");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdCommandeInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdCommandeInserted;
	}



	public static int createCommande (Commande c) {
		Connection con = Connect.get();
		int lastIdCommande=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdCommande = getLastIdCommande();
			lastIdCommande++;
			prepStmt = con.prepareStatement("INSERT INTO Commande (cmdId ,dateCmd ,etatCmd ,modeLivraison ,moyenPriseCmd ,adrLivId ,adrFactId ,userId ,fraisPortId ) VALUES (?,?,?,?,?,?,?,?,?)");
			prepStmt.setInt(1, lastIdCommande);
			prepStmt.setDate(2, new java.sql.Date(c.getDateCmd().getTime()));
			prepStmt.setString(3, c.getEtatCmd());
			prepStmt.setString(4, c.getModeLivraison());
			prepStmt.setString(5, c.getMoyenPriseCmd());
			prepStmt.setDouble(6, c.getAdrLivId());
			prepStmt.setInt(7, c.getAdrFactId());
			prepStmt.setInt(8, c.getUserId());			
			prepStmt.setInt(9, c.getFraisPortId());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdCommande++;
	}

	public void updateCommande(Commande c) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE COMMANDE SET "
					+ 										   " dateCmd = ? ,"
					+ 										   " etatCmd = ? ,"
					+ 										   " modeLivraison = ? ,"
					+ 										   " moyenPriseCmd = ? ,"
					+ 										   " adrLivId = ? ,"
					+ 										   " adrFactId = ? ,"
					+ 										   " userId = ? ,"
					+ 										   " fraisPortId = ? ,"

				+ "WHERE cmdId = ? ");
			prepStmt.setDate(1, new java.sql.Date(c.getDateCmd().getTime()));
			prepStmt.setString(2, c.getEtatCmd());
			prepStmt.setString(3, c.getModeLivraison());
			prepStmt.setString(4, c.getMoyenPriseCmd());
			prepStmt.setDouble(5, c.getAdrLivId());
			prepStmt.setInt(6, c.getAdrFactId());
			prepStmt.setInt(7, c.getUserId());			
			prepStmt.setInt(8, c.getFraisPortId());
			prepStmt.setInt(9, c.getCmdId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Commande ID :"+c.getCmdId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}



	public void deleteCommande(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM COMMANDE WHERE cmdId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//facture 
	private static int getLastIdFacture() {
		int lastIdFactureInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(factId ) AS lastId FROM FACTURE");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdFactureInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdFactureInserted;
	}

	public static int createFacture (Facture f) {
		Connection con = Connect.get();
		int lastIdFacture=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdFacture = getLastIdFacture();
			lastIdFacture++;
			prepStmt = con.prepareStatement("INSERT INTO Facture (factId ,dateFacturation ,dateEcheance ,prixHT ,remise ,cmdId) VALUES (?,?,?,?,?,?)");
			prepStmt.setInt(1, lastIdFacture);
			prepStmt.setDate(2, new java.sql.Date(f.getDateFacturation().getTime()));
			prepStmt.setDate(3, new java.sql.Date(f.getDateEcheance().getTime()));
			prepStmt.setDouble(4, f.getPrixHt());
			prepStmt.setDouble(5, f.getRemise());
			prepStmt.setInt(6, f.getCmdId());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdFacture++;
	}

	public static Facture findFactureById(int id) {
		Facture f=null; 		
		Connection con = Connect.get();
		ResultSet rs=null; 
		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT factId,"
					+ 										   " dateFacturation,"
					+ 										   " dateEcheance,"
					+ 										   " remise,"
					+ 										   " cmdId,"
					+                						   " FROM FACTURE WHERE factId = id");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			while(rs.next()) {
				f=new Facture(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public void updateFacture(Facture f) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE FACTURE SET "
					+ 										   " dateFacturation = ? ,"
					+ 										   " dateEcheance = ? ,"
					+ 										   " remise = ? ,"
					+ 										   " cmdId = ? ,"
					+ "WHERE factId = ? ");
			prepStmt.setDate(1, new java.sql.Date(f.getDateFacturation().getTime()));
			prepStmt.setDate(2, new java.sql.Date(f.getDateEcheance().getTime()));
			prepStmt.setDouble(3, f.getRemise());
			prepStmt.setInt(4, f.getCmdId());
			prepStmt.setInt(5, f.getFactId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Facture ID :"+f.getFactId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void deleteFacture (int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM FACTURE WHERE getFactId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();

		} catch (Exception e) {

		}
	}

	//Livraison 
	private static int getLastIdLivraison() {
		int lastIdLivraisonInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(livraisonId ) AS lastId FROM LIVRAISON");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdLivraisonInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdLivraisonInserted;
	}


	/**
	 * Create LIVRAISON in database
	 * @param l the livraison
	 * @return the id of the livraison
	 */
	public static int createLivraison (Livraison l) {
		Connection con = Connect.get();
		int lastIdLivraison=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdLivraison = getLastIdLivraison();
			lastIdLivraison++;
			prepStmt = con.prepareStatement("INSERT INTO PRODUIT (livraisonId , dateColisage , dateExpedition , delaisLivraison , transporteurId , cmdId) VALUES (?,?,?,?,?,?)");
			prepStmt.setInt(1,lastIdLivraison);
			prepStmt.setDate(2, new java.sql.Date(l.getDateColisage().getTime()));
			prepStmt.setDate(3, new java.sql.Date(l.getDateExpedition().getTime()));
			prepStmt.setInt(4, l.getDelaisLivraison());
			prepStmt.setInt(5, l.getTransporteurId());
			prepStmt.setInt(6, l.getCmdId());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdLivraison++;
	}

	public static Livraison findLivraisonById(int id) {
		Livraison l=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT"
					+ 										   " livraisonId,"
					+ 										   " dateColisage,"
					+ 										   " dateExpedition,"
					+ 										   " delaisLivraison,"
					+ 										   " transporteurId,"
					+ 										   " cmdId,"
					+                						   " FROM LIVRAISON WHERE livraisonId = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				l=new Livraison(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	public void updateLivraison(Livraison l) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE LIVRAISON SET"
					+ 										   " dateColisage = ? ,"
					+ 										   " dateExpedition = ? ,"
					+ 										   " delaisLivraison = ? ,"
					+ 										   " transporteurId = ? ,"
					+ 										   " cmdId = ?"
					+ "WHERE livraisonId = ? ");
			prepStmt.setDate(1, new java.sql.Date(l.getDateColisage().getTime()));
			prepStmt.setDate(2, new java.sql.Date(l.getDateExpedition().getTime()));
			prepStmt.setInt(3, l.getDelaisLivraison());
			prepStmt.setInt(4, l.getTransporteurId());
			prepStmt.setInt(5, l.getCmdId());
			prepStmt.setInt(6, l.getLivraisonId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Livraison ID :"+l.getLivraisonId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void deleteLivraison(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("DELETE FROM LIVRAISON WHERE livraisonId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	//Boutique 
	private static int getLastIdBoutique() {
		int lastIdBoutiqueInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(btqId) AS lastId FROM boutique");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdBoutiqueInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdBoutiqueInserted;
	}


	/**
	 * Create boutique in database
	 * @param b the boutique
	 * @return the id of the product
	 */
	public static int createBoutique (Boutique b) {
		Connection con = Connect.get();
		int lastIdBoutique=0;
		PreparedStatement prepStmt=null;
		try {
			lastIdBoutique = getLastIdBoutique();
			lastIdBoutique++;
			prepStmt = con.prepareStatement("INSERT INTO BOUTIQUE (btqId , description , adrId , stockId) VALUES (?,?,?,?)");
			prepStmt.setInt(1, lastIdBoutique);
			prepStmt.setString(2, b.getDescription());
			prepStmt.setInt(3, b.getAdrId());
			prepStmt.setInt(4, b.getStockId());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdBoutique++;
	}

	public static Boutique findBoutiqueById(int id) {
		Boutique b=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " btqId,"
					+ 										   " description,"
					+ 										   " adrId,"
					+ 										   " stockId,"
					+                						   " FROM BOUTIQUE WHERE btqId = ?");		
			prepStmt.setInt(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				b=new Boutique(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	public void updateBoutique (Boutique b) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE BOUTIQUE SET "
					+ 										   "description = ? ,"
					+ 										   " adrId = ? ,"
					+ 										   " stockId = ? ,"
					+ "WHERE btqId = ? ");
			prepStmt.setString(1, b.getDescription());
			prepStmt.setInt(2, b.getAdrId());
			prepStmt.setInt(3, b.getStockId());
			prepStmt.setInt(4, b.getBtqId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Boutique ID :"+b.getBtqId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Frais de port 
	private static int getLastIdFraisDePort() {
		int lastIdFraisdePortInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(fraisPortId) AS lastId FROM fraisDePort");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdFraisdePortInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdFraisdePortInserted;
	}


	/**
	 * Create fraisDePort in database
	 * @param fdp the fraisDePort
	 * @return the id of the product
	 */
	public static int createFraisDePort (FraisDePort fdp) {
		Connection con = Connect.get();
		int lastIdFraisdePort=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdFraisdePort = getLastIdFraisDePort();
			lastIdFraisdePort++;
			prepStmt = con.prepareStatement("INSERT INTO fraisDePort (fraisPortId, dateDebut , dateFin , montant ) VALUES (?,?,?,?)");
			prepStmt.setInt(1, lastIdFraisdePort);
			prepStmt.setDate(2, new java.sql.Date(fdp.getDateDebut().getTime()));
			prepStmt.setDate(3, new java.sql.Date(fdp.getDateFin().getTime()));
			prepStmt.setDouble(4, fdp.getMontant());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}
		return 	lastIdFraisdePort++;
	}

	public static FraisDePort findFDPById(int id) {
		FraisDePort fdp=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT"

				+ 										   " fraisPortId,"
				+ 										   " dateDebut,"
				+ 										   " dateFin,"
				+ 										   " montant,"
				+                						   " FROM fraisDePort WHERE fraisPortId = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				fdp=new FraisDePort(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getDouble(5));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return fdp;

	}

	public void updateFraisDePort(FraisDePort fdp) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE fraisDePort SET "
					+ 										   " dateDebut = ? ,"
					+ 										   " dateFin = ? ,"
					+ 										   " montant = ? ,"

				+ "WHERE fraisPortId = ? ");
			prepStmt.setDate(1, new java.sql.Date(fdp.getDateDebut().getTime()));
			prepStmt.setDate(2, new java.sql.Date(fdp.getDateFin().getTime()));
			prepStmt.setDouble(3, fdp.getMontant());
			prepStmt.setInt(5, fdp.getFraisPortId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : FraisDePort ID :"+fdp.getFraisPortId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}



	public void deleteFraisDePort (int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM fraisDePort WHERE fraisPortId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();



		} catch (Exception e) {

		}finally {

		}
	}

	//Stock 
	private static int getLastIdStock() {
		int lastIdStockInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(stockId ) AS lastId FROM stock");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdStockInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdStockInserted;
	}


	/**
	 * Create stock in database
	 * @param s the stock
	 * @return the id of the stock
	 */
	public static int createStock (Stock s) {
		Connection con = Connect.get();
		int lastIdStock =0;

		PreparedStatement prepStmt=null;
		try {
			lastIdStock = getLastIdStock();
			lastIdStock++;
			prepStmt = con.prepareStatement("INSERT INTO stock (stockId  , typeStock ) VALUES (?,?)");
			prepStmt.setInt(1, s.getStockId());
			prepStmt.setString(2, s.getTypeStock());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdStock++;
	}

	public static Stock findStockById(int id) {
		Stock s=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT"
					+ 										   " stockId ,"
					+ 										   " typeStock"		  
					+                						   " FROM stock WHERE stockId  = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				s=new Stock(rs.getInt(1),rs.getString(2));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public void updateStock (Stock s) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE stock  SET "
					+ 										   " stockId  = ? ,"
					+ 										   "typeStock = ? ,"

				+ "WHERE stockId  = ? ");
			prepStmt.setInt(1, s.getStockId());
			prepStmt.setString(2, s.getTypeStock());

			prepStmt.setInt(3, s.getStockId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Stock ID :"+s.getStockId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}



	public void deleteStock(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM STOCK WHERE stockId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();



		} catch (Exception e) {

		}finally {

		}
	}

	//Role 
	private static int getLastIdRole() {
		int lastIdRoleInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(roleId ) AS lastId FROM role");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdRoleInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdRoleInserted;
	}


	/**
	 * Create role in database
	 * @param r the role
	 * @return the id of the role
	 */
	public static int createRole (Role r) {
		Connection con = Connect.get();
		int lastIdRole=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdRole = getLastIdRole();
			lastIdRole++;
			prepStmt = con.prepareStatement("INSERT INTO role (roleId ,libelle ,description) VALUES (?,?,?)");
			prepStmt.setInt(1, r.getRoleId());
			prepStmt.setString(2, r.getLibelle());
			prepStmt.setString(3, r.getDescription());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdRole++;
	}

	public static Role findRoleById(int id) {
		Role r=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT"
					+ 										   " roleId,"
					+ 										   " libelle,"
					+ 										   " description,"
					+                						   " FROM ROLE WHERE roleId = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				r=new Role(rs.getInt(1),rs.getString(2),rs.getString(3));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return r;

	}

	public void updateRole (Role r) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE ROLE SET "
					+ 										   " libelle= ? ,"
					+ 										   " description = ? ,"
					+ "WHERE roleId = ? ");
			prepStmt.setInt(1, r.getRoleId());
			prepStmt.setString(2, r.getLibelle());
			prepStmt.setString(3, r.getDescription());
			prepStmt.setInt(4, r.getRoleId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : ROLE ID :"+r.getRoleId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void deleteRole(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM ROLE WHERE roleId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	//DROIT  
	private static int getLastIdDroit() {
		int lastIdDroitInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(droitId ) AS lastId FROM Droit");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdDroitInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdDroitInserted;
	}


	/**
	 * Create droit in database
	 * @param d the droit
	 * @return the id of the droit
	 */
	public static int createDroit (Droit d) {
		Connection con = Connect.get();
		int lastIdDroit=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdDroit = getLastIdDroit();
			lastIdDroit++;
			prepStmt = con.prepareStatement("INSERT INTO Droit (droitId ,libelle , description) VALUES (?,?,?)");
			prepStmt.setInt(1, lastIdDroit);
			prepStmt.setString(2, d.getLibelle());
			prepStmt.setString(3, d.getDescription());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}
		return 	lastIdDroit++;
	}

	public static Droit findDroitById(int id) {
		Droit d=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " droitId ,"
					+ 										   " libelle,"
					+ 										   " description,"
					+                						   " FROM Droit WHERE droitId = id");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				d=new Droit(rs.getInt(1),rs.getString(2),rs.getString(3));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	public void updateDroit (Droit d) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE Droit SET "
					+ 										   " libelle = ? ,"
					+ 										   " description = ? ,"
					+ "WHERE droitId = ? ");
			prepStmt.setString(1, d.getLibelle());
			prepStmt.setString(2, d.getDescription());
			prepStmt.setInt(3, d.getDroitId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Droit ID :"+d.getDroitId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}

	public void deleteDroit(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM Droit WHERE droitId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {

		}finally {

		}
	}

	// Adresse 

	private static int getLastIdAdresse() {
		int lastIdAdresseInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(adrId  ) AS lastId FROM adresse ");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdAdresseInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdAdresseInserted;
	}


	/**
	 * Create adersse in database
	 * @param a the adresse
	 * @return the id of the adresse
	 */
	public static int createAdresse (Adresse a) {
		Connection con = Connect.get();
		int lastIdAdresse=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdAdresse = getLastIdAdresse();
			lastIdAdresse++;
			prepStmt = con.prepareStatement("INSERT INTO Adresse (adrId  ,rue , cp ,ville ,pays) VALUES (?,?,?,?,?)");
			prepStmt.setInt(1, a.getAdrId());
			prepStmt.setString(2, a.getRue());
			prepStmt.setString(3, a.getCp());
			prepStmt.setString(4, a.getVille());
			prepStmt.setString(5, a.getPays());

			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdAdresse++;
	}

	public static Adresse findAdresseById(int id) {
		Adresse a=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " adrId ,"
					+ 										   " rue,"
					+ 										   " cp,"
					+ 										   " ville,"
					+ 										   " pays,"
					+                						   " FROM Adresse WHERE adrId = id");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				a=new Adresse(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public void updateAdresse (Adresse a) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE Adresse SET "
					+ 										   " rue = ? ,"
					+ 										   " cp = ? ,"
					+ 										   " ville = ? ,"
					+ 										   " pays = ?"
					+ "WHERE adrId = ? ");
			prepStmt.setString(1, a.getRue());
			prepStmt.setString(2, a.getCp());
			prepStmt.setString(3, a.getVille());
			prepStmt.setString(4, a.getPays());

			prepStmt.setInt(5, a.getAdrId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Adresse ID :"+a.getAdrId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAdresse(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM Addresse WHERE adrId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {

		}
	}


	//Ligne Commande  
	private static int getLastIdLigneCommande() {
		int lastIdLigneCommandeInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(cmdId  ) AS lastId FROM ligneCommande ");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdLigneCommandeInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdLigneCommandeInserted;
	}


	/**
	 * Create droit in database
	 * @param d the droit
	 * @return the id of the droit
	 */
	public static int createLigneCommande (LigneCommande lc) {
		Connection con = Connect.get();
		int lastIdLigneCommande=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdLigneCommande = getLastIdLigneCommande();
			lastIdLigneCommande++;
			prepStmt = con.prepareStatement("INSERT INTO lignecommande (cmdId  ,prodId  , quantite ) VALUES (?,?,?)");
			prepStmt.setInt(1,lastIdLigneCommande);
			prepStmt.setInt(2, lc.getProdId());
			prepStmt.setInt(3, lc.getQuantite());
			prepStmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
		return 	lastIdLigneCommande++;
	}

	public static LigneCommande findLigneCommandeById(int id) {
		LigneCommande lc=null;		
		Connection con = Connect.get();
		ResultSet rs=null; 
		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT ,"
					+ 										   " cmdId  ,"
					+ 										   " prodId ,"
					+ 										   " quantite ,"
					+                						   " FROM ligneCommande WHERE cmdId = id");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				lc=new LigneCommande(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return lc;
	}

	public void updateLigneCommande (LigneCommande lc) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE ligneCommande SET "
					+ 										   " cmdId  = ? ,"
					+ 										   " prodId  = ? ,"
					+ 										   " quantite  = ? ,"
					+ "WHERE cmdId = ? ");
			prepStmt.setInt(1, lc.getCmdId());
			prepStmt.setInt(2, lc.getProdId());
			prepStmt.setInt(3, lc.getQuantite());

			prepStmt.setInt(4, lc.getCmdId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : LigneCommande ID :"+lc.getCmdId());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}
	}



	public void deleteLigneCommande (int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM ligneCommande WHERE cmdId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();



		} catch (Exception e) {

		}finally {

		}
	}

	// Entreprise 
	private static int getLastIdEntreprise() {
		int lastIdEntrepriseInserted = 0;
		try {
			Connection con = Connect.get();
			PreparedStatement req = con.prepareStatement("select MAX(entId ) AS lastId FROM entreprise");
			ResultSet rs = req.executeQuery();
			while (rs.next()) {
				lastIdEntrepriseInserted = rs.getInt("lastId");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}

		return lastIdEntrepriseInserted;
	}


	/**
	 * Create entreprise in database
	 * @param e the entreprise
	 * @return the id of the entreprise
	 */
	public static void createEntreprise (Entreprise e) {
		Connection con = Connect.get();
		int lastIdEntreprise=0;

		PreparedStatement prepStmt=null;
		try {
			lastIdEntreprise = getLastIdEntreprise();
			lastIdEntreprise++;
			prepStmt = con.prepareStatement("INSERT INTO PRODUIT (entId , raisonSociale , nom , siret, ape, acitivitePrincipale, nbEmployes, adrId) VALUES (?,?,?,?,?,?,?,?)");
			prepStmt.setInt(1, e.getEntId());
			prepStmt.setString(2, e.getRaisonSociale());
			prepStmt.setString(3, e.getNom());
			prepStmt.setString(4, e.getSiret());
			prepStmt.setString(5, e.getApe());
			prepStmt.setString(6, e.getActivitePrincipale());
			prepStmt.setInt(7, e.getNbEmployes());
			prepStmt.setInt(8, e.getAdrId());			
			prepStmt.executeUpdate();

		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Entreprise findById(int id) {
		Entreprise e=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 

		PreparedStatement prepStmt=null;
		try {
			prepStmt = con.prepareStatement("SELECT"
					+ 										   " entId,"
					+ 										   " raisonSociale,"
					+ 										   " nom,"
					+ 										   " siret,"
					+ 										   " ape,"
					+ 										   " acitivitePrincipale,"
					+ 										   " nbEmployes,"
					+ 										   " adrId"
					+                						   " FROM ENTREPRISE WHERE entId = ?");		
			prepStmt.setLong(1, id);
			rs=prepStmt.executeQuery();
			if(rs.next()) {
				e=new Entreprise(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
			}

		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;

	}

	public void updateEntreprise (Entreprise e) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt  = con.prepareStatement("UPDATE ENTREPRISE SET "		
					+ 										   " raisonSociale = ? ,"
					+ 										   " nom = ? ,"
					+ 										   " siret = ? ,"
					+ 										   " ape = ? ,"
					+ 										   " acitivitePrincipale = ? ,"
					+ 										   " nbEmployes = ? ,"
					+ 										   " adrId = ? ,"
					+ "WHERE entId = ? ");
			prepStmt.setString(1, e.getRaisonSociale());
			prepStmt.setString(2, e.getNom());
			prepStmt.setString(3, e.getSiret());
			prepStmt.setString(4, e.getApe());
			prepStmt.setString(5, e.getActivitePrincipale());
			prepStmt.setInt(6, e.getNbEmployes());
			prepStmt.setInt(7, e.getAdrId());			
			prepStmt.setInt(8, e.getEntId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Entreprise ID :"+e.getEntId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



	public void deleteEntreprise(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {

			prepStmt = con.prepareStatement("DELETE FROM Entreprise WHERE entId = ?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
		} catch (Exception e) {

		}finally {

		}
	}
}