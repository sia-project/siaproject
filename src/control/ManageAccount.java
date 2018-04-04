package control;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Adresse;
import model.Connect;
import model.DAO;
import model.Entreprise;
import model.Utilisateur;

public class ManageAccount extends HttpServlet {

	private final static Pattern VALID_EMAIL_ADDRESS = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)",Pattern.CASE_INSENSITIVE); 


	@Override
	public void destroy() 
	{
		Connect.close();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();
		String pageToSend=null;
		String page = request.getParameter("page");
		boolean cbPro = (request.getParameter("cbPro")!=null);
		String civilite = request.getParameter("civilite");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String entNom = request.getParameter("entNom");
		String siret = request.getParameter("siret");
		String adresse = request.getParameter("adresse");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String mdp = request.getParameter("password");
		String mdpConfirmation = request.getParameter("passwordConfirm");
		boolean mailExists = false;

		if(page!=null ||!page.isEmpty()) {
			switch(page) {
			case "registerAccount" :

				request.setAttribute("civilite", civilite);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom", prenom);
				request.setAttribute("entNom", entNom);
				request.setAttribute("siret", siret);
				request.setAttribute("adresse", adresse);
				request.setAttribute("cp", cp);
				request.setAttribute("ville", ville);
				request.setAttribute("tel", tel);
				request.setAttribute("email", email);

				if(!email.isEmpty()) {
					mailExists = DAO.mailExists(email);
				}
				if (cbPro) {
					if(civilite.isEmpty()||nom.isEmpty()||prenom.isEmpty()||entNom.isEmpty()
							||siret.isEmpty()||adresse.isEmpty()||cp.isEmpty()||ville.isEmpty()
							||tel.isEmpty()||email.isEmpty()||mailExists||email.contains("yopmail")
							||!validateMail(email)||mdp.length()<8||!mdp.equals(mdpConfirmation)) 
					{
						request.setAttribute("text", "<ul>");
						if(civilite.isEmpty()) {
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez choisir votre civilité</li>");
						}
						if(nom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre nom</li>");
						}
						if(prenom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre prénom</li>");
						}
						if(entNom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir la dénomination sociale de votre entreprise</li>");
						}
						if(siret.isEmpty())
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir le numéro siret de votre entreprise</li>");
						}
						if(adresse.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre adresse professionelle</li>");
						}
						if(cp.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir le code postal</li>");
						}
						if(ville.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir la ville</li>");
						}
						if(tel.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir un numero de téléphone</li>");
						}
						if(mailExists) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Cette adresse mail est deja utilisée</li>");
							request.setAttribute("email", "");
						}
						if(email.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre addresse mail</li>");
						}
						if(email.contains("yopmail")||!validateMail(email)) {
							request.setAttribute("text", request.getAttribute("text")+"<li>Adresse mail invalide</li>");
							request.setAttribute("email", "");
						}
						if(mdp.length()<8) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Le mot de passe doit avoir au moins 8 caracteres</li>");
						}
						if(!mdp.equals(mdpConfirmation)) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Les mots de passe ne correspondent pas</li>");
						}
						request.setAttribute("text", request.getAttribute("text")+"</ul>");
						pageToSend = "createAccount.jsp";
					}
					else {
						int idAdr = DAO.createAdresse(new Adresse(adresse,cp, ville,"FRANCE"));
						int idEnt = DAO.createEntreprise(new Entreprise(entNom, siret,idAdr));
						DAO.createAccountPro(civilite, nom, prenom,tel, email, idEnt, mdp);
						pageToSend = "createAccountSuccess.jsp";
					}
				}
				else {
					if(civilite.isEmpty()||nom.isEmpty()||prenom.isEmpty()
							||adresse.isEmpty()||cp.isEmpty()||ville.isEmpty()
							||tel.isEmpty()||email.isEmpty()||mailExists||email.contains("yopmail")
							||!validateMail(email)||mdp.length()<8||!mdp.equals(mdpConfirmation)) 
					{
						request.setAttribute("text", "<ul>");
						if(civilite.isEmpty()) {
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez choisir votre civilité</li>");
						}
						if(nom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre nom</li>");
						}
						if(prenom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre prénom</li>");
						}
						if(entNom.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir la dénomination sociale de votre entreprise</li>");
						}
						if(siret.isEmpty())
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir le numéro siret de votre entreprise</li>");
						}
						if(adresse.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre adresse professionelle</li>");
						}
						if(cp.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir le code postal</li>");
						}
						if(ville.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir la ville</li>");
						}
						if(tel.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir un numero de téléphone</li>");
						}
						if(mailExists) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Cette adresse mail est deja utilisée</li>");
							request.setAttribute("email", "");
						}
						if(email.isEmpty()) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Veuillez saisir votre addresse mail</li>");
						}
						if(email.contains("yopmail")||!validateMail(email)) {
							request.setAttribute("text", request.getAttribute("text")+"<li>Adresse mail invalide</li>");
							request.setAttribute("email", "");
						}
						if(mdp.length()<8) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Le mot de passe doit avoir au moins 8 caracteres</li>");
						}
						if(!mdp.equals(mdpConfirmation)) 
						{
							request.setAttribute("text", request.getAttribute("text")+"<li>Les mots de passe ne correspondent pas</li>");
						}
						request.setAttribute("text", request.getAttribute("text")+"</ul>");
						pageToSend = "createAccount.jsp";
						
					}
					else 
					{
						int idAdr = DAO.createAdresse(new Adresse(adresse,cp, ville,"FRANCE"));
						int uId = DAO.createAccount(civilite,nom, prenom, tel, email, mdp);
						DAO.createClientAdresse(uId, idAdr);
						pageToSend = "createAccountSuccess.jsp";
					}
				}
				break;
			default : pageToSend="index.jsp";
			break;
			}
		}
		else {
			pageToSend="index.jsp";
		}

		request.getRequestDispatcher(pageToSend).forward(request, response);
	}

	/**
	 * Validate Mail address
	 * @param emailStr
	 * @return true if mail is verified
	 */
	private static boolean validateMail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS.matcher(emailStr);
		return matcher.find();
	}

	/**
	 * send  account verification mail 
	 * @param email the recipient mail
	 */
	private static void sendAccountValidationEmail(String email) 
	{
		try {
			Properties props = new Properties();
			props.setProperty("mail.from", "contact@lvs.com");
			Session session =  Session.getInstance(props);
			Message message = new MimeMessage(session);
			InternetAddress recipient = new InternetAddress(email);
			message.setRecipient(Message.RecipientType.TO, recipient);
			message.setSubject("Compte crï¿½e sur La Vieille Sardine");
			message.setContent(getMessageHTMLUser(email),"text/html; charset=utf-8");
			Transport.send(message);
		}
		catch(AddressException ex) {
			System.out.println("Adresse mail invalide");
			ex.printStackTrace();
		}
		catch(MessagingException ex) {
			System.out.println("Erreur dans le message");
			ex.printStackTrace();
		}
	}

	/**
	 * Creates mail content with account validation link
	 * @param email the recipient mail
	 * @return mail content
	 */
	private static String getMessageHTMLUser(String email) {

		Utilisateur user  = DAO.getUserByMail(email);

		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb.append("<head>");
		sb.append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sb.append("			<title>Votre compte a bien ï¿½tï¿½ crï¿½e</title>");
		sb.append("				<style type=\"text/css\">");
		sb.append("					body {margin: 0; padding: 0; min-width: 100%!important;}");
		sb.append("					.content {width: 100%; max-width: 600px;}");
		sb.append("				</style>");
		sb.append("</head>");
		sb.append("		<body yahoo bgcolor=\"#f6f8f1\">");
		sb.append("			<table width=\"100%\" bgcolor=\"#f6f8f1\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		sb.append("				<tr>");
		sb.append("					<td>");
		sb.append("						<table class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
		sb.append("							<tr>");
		sb.append("								<td>");
		sb.append("									Bonjour " +user.getPrenom()+ " "+user.getNom());
		sb.append("								</td>");
		sb.append("							</tr>");
		sb.append("							<tr>");
		sb.append("								<td>");
		sb.append("									Votre compte ï¿½ bien ï¿½tï¿½ crï¿½e, vous pouvez dï¿½s ï¿½ present valider votre adresse email afin d'activer votre compte en cliquant sur le le lien suivant" );
		sb.append("									<a href=\"localhost:8080/lvs/manageAccount?page=validateAccount&user=\""+user.getCle()+"\" target=\"_blank\">Votre lien</a>");
		sb.append("								</td>");
		sb.append("							</tr>");
		sb.append("						</table>");
		sb.append(" 				</td>");
		sb.append("				</tr>");
		sb.append("			</table>");
		sb.append("		</body>");
		sb.append("</html>");
		return sb.toString();
	}

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Description";
	}

}
