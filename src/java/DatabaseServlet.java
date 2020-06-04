import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(urlPatterns = {"/DatabaseServlet"})
public class DatabaseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
    out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\""+"xmlns:h=\"http://xmlns.jcp.org/jsf/html\"" +"xmlns:ui=\"http://xmlns.jcp.org/jsf/facelets\">");
            out.println("<head>");
            out.println("<title>Feedback</title>");  
            out.println("<link href=resources/styles.css rel=stylesheet type=text/css></link>"); 
            out.println("</head>");
            out.println("<body id=\"page4\">");
            out.println("<ul>"+"<li><a class=\"active\" href=\"faces/index.xhtml\">Home</a></li>" +"<li><a class=\"active\" href=\"faces/about.xhtml\">About</a></li>"+"<li><a class=\"active\" href=\"faces/ContactUs.xhtml\">Contact Us</a></li>"+"</ul>");
            out.println("<br/>");
            out.println("<h1>Servlet DatabaseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter();  
            //reacieving response from the Website and storing in the variables
            String n =request.getParameter("userName");  
            String p =request.getParameter("userresponse");  
            String e =request.getParameter("userEmail");  
            String c =request.getParameter("userCountry");  
                 System.out.println(n);
                 System.out.println(p);
                  System.out.println(e);
                   System.out.println(c);
          
            try{  
                //initiating connection to the database
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Feedback;user=root;password=root");   
            PreparedStatement ps =con.prepareStatement("insert into feed values(?,?,?,?)");  //inserting into the table 
            //setting string to the values set
              ps.setString(1,n);  
              ps.setString(2,p);  
              ps.setString(3,e);  
              ps.setString(4,c);  

            int i=ps.executeUpdate();  
            if(i>0)  
               out.println("<!DOCTYPE html>");
                        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n"+"xmlns:h=\"http://xmlns.jcp.org/jsf/html\"\n" +"xmlns:ui=\"http://xmlns.jcp.org/jsf/facelets\">");
                        out.println("<head>");
                        out.println("<title>Feedback</title>");  
                        out.println("<link href=resources/styles.css rel=stylesheet type=text/css></link>"); 
                        out.println("</head>");
                        out.println("<body id=\"page\">");
                        out.println(" <ul>\n" +"<li><a class=\"active\" href=\"faces/index.xhtml\">Home</a></li>\n" +" <li><a class=\"active\" href=\"faces/about.xhtml\">About</a></li>\n" +" +\" <li><a class=\"active\" href=\"faces/ContactUs.xhtml\">Feedback</a></li>\\n\" +\" </ul>");
                        out.println("<h1 align=\"center\">REST Countries Team</h1>");
                        out.println("<h2 align=\"center\"> Thank You For your Valuable Feedback</h2>");
                        out.println("</body>");  
                            out.println("</html>"); 
            if(i<0){
                out.println("<html><body><b> Not Successfully Inserted"
                                    + "</b></body></html>"); 
            }   

            }catch (Exception e2) {System.out.println(e2);}  

            out.close();  
                }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
