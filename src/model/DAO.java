package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import security.BCrypt;
import security.Encryption;

public class DAO {

	private static int uId;
	private static String nom;
	private static String prenom;
	private static String cle;
	private static String mdp;
	private static String sel;
	private static String mail;


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
			PreparedStatement req = con.prepareStatement("SELECT uId, nom, prenom, mdp, cle, sel LIMIT 1"
					+"FROM UTILISATEUR"
					+"WHERE adrMail  = ? ");								
			req.setString(1, adrMail);

			ResultSet rs = req.executeQuery();
			while(rs.next()) 
			{
				uId = rs.getInt(1);
				nom = rs.getString(2);
				prenom = rs.getString(3);
				mdp = rs.getString(4);
				cle = rs.getString(5);
				sel = rs.getString(6);
				mail = adrMail;
				b = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return b;	
	}

	/**
	 * Update last connection dateTime of the user 
	 * @param userId
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
			PreparedStatement req = con.prepareStatement("SELECT COUNT(*) FROM TBL1 WHERE TBL105 = ?");
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
}
