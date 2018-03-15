package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sun.xml.internal.ws.wsdl.SDDocumentResolver;

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
}
