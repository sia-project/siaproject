package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Connect;
import model.DAO;
import model.Produit;
import model.FamilleProduit;
import model.GammeProduit;


public class GestionProduit  extends HttpServlet{


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
		String product_name = request.getParameter("product_name");
		String product_description = request.getParameter("product_description");
		String product_categorie = request.getParameter("product_categorie");
		String product_price = request.getParameter("product_price"); 
		String product_weight = request.getParameter("product_weight");
		String product_rayon = request.getParameter("available_quantity");
		
		
		
		String familly_product_name = request.getParameter("familly_product_name");
		String familly_product_description = request.getParameter("familly_product_description");

		String gamme_product_name = request.getParameter("gamme_product_name");
		String gamme_product_description = request.getParameter("gamme_product_description");
		System.out.println(page);
		if(page!=null ||!page.isEmpty()) {
			switch(page) {
			case "addproduct" :
				Produit p = new Produit(product_name,null,product_description,Integer.parseInt(product_weight),Double.parseDouble(product_price),0,product_rayon,null,product_categorie);
				DAO.createProduct(p);
				break;
			case  "addfamillyproduct" :
				FamilleProduit fp = new FamilleProduit(familly_product_name,familly_product_description);
				DAO.createFamille(fp);
				break;
			case  "addgammeproduct" :
			//	GammeProduit gp = new GammeProduit(gamme_product_name,gamme_product_description);
			//	DAO.createGammeProduit(gp);
				break;
			case "addproductshow" :
				pageToSend = "addproduct.jsp";
				break;					
			case "commande" :
				//DAO.createCommande(null,null,etatCmd,modeLivraison,moyenPriseCmd,adrLivId,adrFactId,userId,fraisPortId);
			break;		
			}
			
		}
		else {
			pageToSend="index.jsp";
		}
		request.getRequestDispatcher(pageToSend).forward(request, response);
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
