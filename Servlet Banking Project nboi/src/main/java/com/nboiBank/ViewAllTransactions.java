package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/transaction")
public class ViewAllTransactions extends HttpServlet{
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 resp.setContentType("text/html");
		// for security purpose
		req.changeSessionId();
		ServletContext context=req.getServletContext();
		HttpSession session=req.getSession(false);
		PrintWriter pw = resp.getWriter();
		
		String pan_card=(String) session.getAttribute("pan_card");
		String name=(String) session.getAttribute("name");
		long ac_number=0;
		long loan_ac_number=0;
		if(req.getParameter("ac_number")!=null) {
	    ac_number=Long.parseLong((String)req.getParameter("ac_number"));
		}else {
			loan_ac_number=Long.parseLong((String)req.getParameter("loan_ac_number"));
		}
	    try {
			Connection con=(Connection) context.getAttribute("connection");
			ResultSet rs=null;
			if(ac_number!=0) {
			PreparedStatement ps=con.prepareStatement("select * from transction where ac_number=? ORDER BY transaction_date DESC LIMIT 10");
			ps.setLong(1, ac_number);
			rs=ps.executeQuery();
			}else {
				PreparedStatement ps=con.prepareStatement("select * from transction where ac_number=? ORDER BY transaction_date DESC LIMIT 10");
				ps.setLong(1, loan_ac_number);
				rs=ps.executeQuery();
			}
			String homePage="<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"UTF-8\">\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "<title>Bank Website</title>\r\n"
					+ "<style>\r\n"
					+ "  body {\r\n"
					+ "    font-family: Arial, sans-serif;\r\n"
					+ "    margin: 0;\r\n"
					+ "    padding: 0;\r\n"
					+ "  }\r\n"
					+ "  header {\r\n"
					+ "    background-color: #333;\r\n"
					+ "    color: #fff;\r\n"
					+ "    padding: 20px;\r\n"
					+ "    text-align: center;\r\n"
					+ "    display: flex;\r\n"
					+ "    justify-content: space-between;\r\n"
					+ "    align-items: flex-end; /* Align items to the bottom */\r\n"
					+ "  }\r\n"
					+ "  #user-info {\r\n"
					+ "    display: flex;\r\n"
					+ "    align-items: center;\r\n"
					+ "  }\r\n"
					+ "  #user-info p {\r\n"
					+ "    margin-right: 10px;\r\n"
					+ "  }\r\n"
					+ "  #user-info button {\r\n"
					+ "    padding: 5px 10px;\r\n"
					+ "    background-color: #ccc;\r\n"
					+ "    color: #333;\r\n"
					+ "    border: none;\r\n"
					+ "    border-radius: 5px;\r\n"
					+ "    cursor: pointer;\r\n"
					+ "  }\r\n"
					+ "  #user-info button:hover {\r\n"
					+ "    background-color: #999;\r\n"
					+ "    color: #fff;\r\n"
					+ "  }\r\n"
					+ "  footer {\r\n"
					+ "    background-color: #333;\r\n"
					+ "    color: #fff;\r\n"
					+ "    padding: 20px;\r\n"
					+ "    text-align: center;\r\n"
					+ "    position: fixed;\r\n"
					+ "    bottom: 0;\r\n"
					+ "    width: 100%;\r\n"
					+ "  }\r\n"
					+ "  #container {\r\n"
					+ "    display: flex;\r\n"
					+ "    justify-content: space-between;\r\n"
					+ "    padding: 20px;\r\n"
					+ "    margin-bottom: 60px; /* Adjusted for footer */\r\n"
					+ "  }\r\n"
					+ "  #options {\r\n"
					+ "    flex: 1;\r\n"
					+ "    margin-right: 20px;\r\n"
					+ "    padding: 20px;\r\n"
					+ "    background-color: #f4f4f4;\r\n"
					+ "    font-size: 18px; /* Increased font size */\r\n"
					+ "  }\r\n"
					+ "  #options h2 {\r\n"
					+ "    border-bottom: 2px solid #ccc;\r\n"
					+ "    padding-bottom: 10px;\r\n"
					+ "  }\r\n"
					+ "  #options ul {\r\n"
					+ "    list-style-type: none;\r\n"
					+ "    padding: 0;\r\n"
					+ "  }\r\n"
					+ "  #options ul li {\r\n"
					+ "    margin-bottom: 10px;\r\n"
					+ "  }\r\n"
					+ "  #options ul li a {\r\n"
					+ "    display: block;\r\n"
					+ "    padding: 10px;\r\n"
					+ "    text-decoration: none;\r\n"
					+ "    color: #333;\r\n"
					+ "    background-color: #ccc;\r\n"
					+ "    border: 1px solid #999;\r\n"
					+ "    border-radius: 5px;\r\n"
					+ "  }\r\n"
					+ "  #options ul li a:hover {\r\n"
					+ "    background-color: #999;\r\n"
					+ "    color: #fff;\r\n"
					+ "  }\r\n"
					+ "  #account-table {\r\n"
					+ "    flex: 2;\r\n"
					+ "    padding: 20px;\r\n"
					+ "  }\r\n"
					+ "  #account-table h2 {\r\n"
					+ "    margin-bottom: 20px;\r\n"
					+ "  }\r\n"
					+ "  table {\r\n"
					+ "    width: 100%;\r\n"
					+ "    border-collapse: collapse;\r\n"
					+ "  }\r\n"
					+ "  th, td {\r\n"
					+ "    padding: 8px;\r\n"
					+ "    border-bottom: 1px solid #ddd;\r\n"
					+ "  }\r\n"
					+ "  th {\r\n"
					+ "    background-color: #f2f2f2;\r\n"
					+ "    text-align: left;\r\n"
					+ "  }\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<header>\r\n"
					+ "  <h1>National Bank Of India</h1>\r\n"
					+ "  <div id=\"user-info\">\r\n"
					+ "    <p>Welcome"+name+" Ji</p>\r\n"
					+ "     <button onclick=\"logout()\">Logout</button>\r\n"
					+ "  </div>\r\n"
					+ "</header>\r\n"
					+ "\r\n"
					+ "<div id=\"container\">\r\n"
					+ "  <div id=\"options\">\r\n"
					+ "    <h2>Our Services</h2>\r\n"
					+ "    <ul>\r\n"
					+ "      <li><a href=\"personalDetails\">Personal Details</a></li>\r\n"
					+ "      <li><a href=\"downlodeTransHistory\">Transaction History Download</a></li>\r\n"
					+ "      <li><a href=\"fundtransction\">Fund Transfer</a></li>\r\n"
					+ "      <li><a href=\"atmWithdrawal\">ATM Withdrawal</a></li>\r\n"
					+ "      <li><a href=\"applyLoan\">Apply For Loan</a></li>\r\n"
					+ "      <li><a href=\"billpayment\">Bill Payment</a></li>\r\n" 	+ "    </ul>\r\n"
					+ "  </div>\r\n"
					+ "  <div id=\"account-table\">\r\n"
					+ "    <h2>Account Transction History</h2>\r\n"
					+ "    <table>\r\n"
					+ "      <thead>\r\n"
					+ "        <tr>\r\n"
					+ "          <th>Account Number</th>\r\n"
					+ "          <th>Credit</th>\r\n"
					+ "          <th>Debit</th>\r\n"
					+ "          <th>Transction ac</th>\r\n"
					+ "          <th>Balance</th>\r\n"
					+ "          <th>Date & Time</th>\r\n"
					+ "        </tr>\r\n"
					+ "      </thead>\r\n"
					+ "      <tbody>\r\n";
					while(rs.next()) { 
			
			        homePage=homePage+  "<tr>\r\n"
					+ "          <td>"+rs.getLong("ac_number")+"</td>\r\n"
					+ "          <td>"+rs.getLong("credit")+"</td>\r\n"
					+ "          <td>"+rs.getLong("debit")+"</td>\r\n"
					+ "          <td>"+rs.getString("from_ac")+"</td>\r\n"
					+ "          <td>"+rs.getLong("balance")+"</td>\r\n"
					+ "          <td>"+rs.getTimestamp("transaction_date")+"</td>\r\n"
					+ "        </tr>\r\n";
					}
					
					
					
					homePage=homePage+ "      </tbody>\r\n"
					+ "    </table>\r\n"
					+ "  </div>\r\n"
					+ "</div>\r\n"
					+ "\r\n"
					+ "<footer>\r\n"
					+ "  <p>&copy; 2024 Bank Website. All Rights Reserved.</p>\r\n"
					+ "</footer>\r\n"
					 +" <script>\r\n"
						+ "        function logout() {\r\n"
						+ "            window.location.href = \"logout\";\r\n"
						+ "        }\r\n"
						+ "    </script>"
					+ "</body>\r\n"
					+ "</html>\r\n";
			pw.println(homePage);
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
