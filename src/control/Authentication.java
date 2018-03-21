package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Connect;
import model.DAO;
import model.Utilisateur;

public class Authentication extends HttpServlet {

	@Override
	public void destroy() 
	{
		Connect.close();
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();
		Utilisateur user = null;
		String pageToSend=null;
		String loggout = request.getParameter("page");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(loggout==null ||loggout.isEmpty()) {
			user = DAO.authenticate(login,password);
			if(user!=null) {
				DAO.updateLastConnectionDateTime(user.getuId());
				session.setAttribute("user", user);
				pageToSend = "userHome.jsp";
			}
			else {
				String text = "Nom d'utilisateur et/ou mot de passe incorrect";
				request.setAttribute("text", text);
				pageToSend = "connection.jsp";
			}
		}
		else if(loggout.equals("loggout"))
		{
			HttpSession currentSession = request.getSession(true);
			currentSession.invalidate();
			pageToSend = "index.jsp";
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
