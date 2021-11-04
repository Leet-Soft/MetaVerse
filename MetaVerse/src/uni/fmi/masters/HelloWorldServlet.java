package uni.fmi.masters;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uni.fmi.masters.beans.UserBean;

/**
 * Servlet implementation class HelloWorldServlet
 */
//@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
			case "login":
				login(request, response);			
			break;
			
			case "register":
				register(request, response);
				break;		
			
			default:
				response.getWriter().append("Unknown action!");
		}
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
	
		String password = request.getParameter("register-password");
		String repeatPassword = request.getParameter("confirm-register-pass");
		
		if(!password.equals(repeatPassword)) {
			request.setAttribute("error", "Password missmatch!");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			
			String email = request.getParameter("register-email");
			String username = request.getParameter("register-user");
			
			UserBean user = new UserBean(username, password, email);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			try {
				response.sendRedirect("profile.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equalsIgnoreCase("gancho") && password.equals("AsD")) {
			response.sendRedirect("home.jsp");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			
			dispatcher.forward(request, response);
		}
		
	}

}
