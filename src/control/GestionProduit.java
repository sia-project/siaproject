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
		String available_quantity = request.getParameter("available_quantity"); 
		String product_weight = request.getParameter("product_weight");
		
	
		if(page!=null ||!page.isEmpty()) {
			switch(page) {
			case "addproduct.jsp" :
				Produit p = new Produit(null,product_name,product_description,Integer.parseInt(product_weight),0,0,null,null,product_categorie);
				//REVOIR FORMULAIRE AJOUT DE PRODUIT INCOMPLET 
				DAO.createProduct(p);
			
			}
		}
				
	}
}