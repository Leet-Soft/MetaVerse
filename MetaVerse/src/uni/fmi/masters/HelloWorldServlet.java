package uni.fmi.masters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import uni.fmi.masters.beans.UserBean;

/**
 * Servlet implementation class HelloWorldServlet
 */
//@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<UserBean> friends = new ArrayList<>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        friends.add(new UserBean("Georgi", "goshohubaveca@abv.bg"));
        friends.add(new UserBean("Mariika", "mimiskronata@abv.bg"));
        friends.add(new UserBean("Ivancho", "ivankartofa@abv.bg"));
        friends.add(new UserBean("Ivelina", "velkavelikata@abv.bg"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
				
			case "friends":
				friends(request, response);
				break;
				
			case "search":
				search(request, response);
				break;
			
			default:
				response.getWriter().append("Unknown action!");
		}
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String phrase = request.getParameter("phrase");
		
		ArrayList<UserBean> results = new ArrayList<>();
				
		for(UserBean user : friends) {
			if(user.getUsername().toLowerCase().contains(phrase.toLowerCase())
					||
				user.getEmail().toLowerCase().contains(phrase.toLowerCase())) {
				results.add(user);
			}
		}
		
		JSONArray array = new JSONArray();
		array.put(results);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(array.toString());		
	}

	private void friends(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setAttribute("friends", friends);
		
		//response.sendRedirect("friends.jsp");//Редирект потребителя бива пренасочен към нова страница и в url-a пише новата страница
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("friends.jsp");//прехвърляме потребителят към друга страница без той да знае къде се намира
		dispatcher.forward(request, response);
		
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
