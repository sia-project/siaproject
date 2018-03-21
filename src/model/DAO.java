package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import security.BCrypt;
import security.Encryption;

public class DAO {
	
	private static final String INSERT_SQL = null;
	private static final String SELECT_SQL = null;
	private static final String UPDATE_SQL = null;
	private static final String DELETE_SQL = null;
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
				user = new Utilisateur(uId, civilite, nom, prenom, mail, cle);
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
	
	/**			prepStmt.executeUpdate();

	 * Update last connection dateTime of the user 
	 * @param userIdparameterIndex, x
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
	
	
	
	public static String createProduit (Produit p) {
		Connection con = Connect.get();
		String pid=null; // 
		
		PreparedStatement prepStmt=null;
		try {
			prepStmt=con.prepareStatement(INSERT_SQL);
			int i=1;
			prepStmt.setInt(i++, p.getProdId());
			prepStmt.setString(i++, p.getLibelle());
			prepStmt.setString(i++, p.getMarque());
			prepStmt.setString(i++, p.getDescription());
			prepStmt.setDouble(i++, p.getPoids());
			prepStmt.setDouble(i++, p.getPrixHT());
			prepStmt.setString(i++, p.getTypeTVA());
			prepStmt.setInt(i++, p.getFamilleProduitId());			
			prepStmt.setInt(i++, p.getGammeProduitId());
			
			

			prepStmt.executeUpdate();
			
		}catch (Exception e) {
			
		}finally {
			
		}
		return pid;
	}
	
	public static Produit findById(int id) {
		Produit p=null; // 		
		Connection con = Connect.get();
		ResultSet rs=null; // 
		
		PreparedStatement prepStmt=null;
		try {
			prepStmt=con.prepareStatement(SELECT_SQL);
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
				p.setPlaceRayon(rs.getString(9));
				p.setTypeTVA(rs.getString(10));
				p.setDestination(rs.getString(11));
				p.setFamilleProduitId(rs.getInt(12));
				p.setGammeProduitId(rs.getInt(13));
			}
					
		}catch (Exception e) {
			
		}finally {
			
		}
		return p;
		
	}
	
	public void updateProduit(Produit p) {
		Connection con=null;
		PreparedStatement prepStmt =null ;
		try {
			con=Connect.get();
			prepStmt=con.prepareStatement(UPDATE_SQL);
			int i=1;
			prepStmt.setInt(i++, p.getProdId());
			prepStmt.setString(i++, p.getLibelle());
			prepStmt.setString(i++, p.getMarque());
			prepStmt.setString(i++, p.getDescription());
			prepStmt.setDouble(i++, p.getPoids());
			prepStmt.setDouble(i++, p.getPrixHT());
			prepStmt.setString(i++, p.getTypeTVA());
			prepStmt.setInt(i++, p.getFamilleProduitId());			
			prepStmt.setInt(i++, p.getGammeProduitId());
						
			prepStmt.setInt(i++, p.getProdId());
			int rowCount=prepStmt.executeUpdate();
			if (rowCount==0) {
				throw new Exception (
						"Update error : Produit ID :"+p.getProdId());
			}
			

		} catch (Exception e) {
			
		}finally {
			
		}
	}
	
	
	
	public void deleteProduit(int id) throws Exception {
		Connection con=Connect.get();
		PreparedStatement prepStmt=null;
		try {
			prepStmt=con.prepareStatement(DELETE_SQL);
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			
			

		} catch (Exception e) {
			
		}finally {
			
		}
	}
	
	

}

