package uni.fmi.masters;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import uni.fmi.masters.entities.UserEntity;
import uni.fmi.masters.repo.JPAUserRepository;

/**
 * Servlet implementation class HelloWorldServlet
 */
//@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<UserEntity> friends = new ArrayList<>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldServlet() {
        super();
        friends.add(new UserEntity("Georgi", "goshohubaveca@abv.bg"));
        friends.add(new UserEntity("Mariika", "mimiskronata@abv.bg"));
        friends.add(new UserEntity("Ivancho", "ivankartofa@abv.bg"));
        friends.add(new UserEntity("Ivelina", "velkavelikata@abv.bg"));
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
		
		ArrayList<UserEntity> results = new ArrayList<>();
				
		for(UserEntity user : friends) {
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
			
			UserEntity user = new UserEntity(username, hashMe(password), email);
			
			JPAUserRepository repo = new JPAUserRepository();
			
			if(repo.registerUser(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				try {
					response.sendRedirect("profile.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				
			}			
			
		}		
		
	}

	private boolean registerUser(UserEntity user) {
		//отваряне на конекция
		Connection con = null;
		
		try {
			con = getConnection();		
			
			//работа с базата
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("INSERT INTO USER ");
			sb.append("(USERNAME, PASSWORD, EMAIL) ");
			sb.append("VALUES (?, ?, ?)");
			
	//		String query = "INSERT INTO USER " 
	//							+ "(USERNAME, PASSWORD, EMAIL)"
	//							+ "VALUES (?, ?, ?)";
				
			PreparedStatement pst = con.prepareStatement(sb.toString());
			pst.setString(1, user.getUsername());
			pst.setString(2, hashMe(user.getPassword()));
			pst.setString(3, user.getEmail());
			
			if(pst.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//затваряне на конекция
			
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}		
		
		return false;		
	}

	private String hashMe(String password) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(password.getBytes());
			
			byte[] arr = md.digest();
			
			StringBuilder hash = new StringBuilder();
			
			for(int i = 0; i < arr.length; i++) {
				hash.append((char)arr[i]);
			}
			
			return hash.toString();
			
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}		
				
		return null;
	}

	private Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			
			return DriverManager.getConnection("jdbc:h2:~/MetaVerseDB", "sa", "");
			
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
		
		return null;		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JPAUserRepository repo = new JPAUserRepository();
		
		UserEntity user = repo.loginUser(username, hashMe(password));
		
		if(user != null) {
			request.getSession().setAttribute("user", user);
			
			response.sendRedirect("home.jsp");	
		}else {
			request.setAttribute("error", "Login not successfull");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");			
			dispatcher.forward(request, response);
		}
		
	}

	private boolean loginUser(String username, String password) {
		
		Connection con = null;
		
		try {
			con = getConnection();
			String query = "SELECT * from USER WHERE username = ? AND password = ?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, hashMe(password));
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.first()) {
				return true;
			}			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(con != null)				
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return false;
	}

}
