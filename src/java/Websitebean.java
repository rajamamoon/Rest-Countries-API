
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//JSF Bean Name 
@ManagedBean(name="bean")
@SessionScoped
public class Websitebean  {
    
  PreparedStatement selectInfo;
  String Info;
  String Website;
  String Email;
    public Websitebean() throws SQLException, ClassNotFoundException {
        initDB();
       
    }
        private void initDB() throws SQLException, ClassNotFoundException {
         //initiating Connection for database
          Class.forName("org.apache.derby.jdbc.ClientDriver");
          Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Feedback;user=root;password=root");
          System.out.println("Successfully Connected to the database!");
          //Querying from Databse
          selectInfo = conn.prepareStatement("SELECT Info, website , email FROM about");
          ResultSet rs = selectInfo.executeQuery();
                //While loop for reading the result set
                while (rs.next()) {
                    //Storing the result into the variables
                   Info = rs.getString("Info");
                    Website = rs.getString("Website");
                    Email = rs.getString("Email");
                  System.out.println(Info + "\n" + Website +"\n"+ Email +"\n");

                }

           } 

   // getter for returning the values to the website
    public String getInfo() {
        return Info;
    }

    public String getWebsite() {
        return Website;
    }

    public String getEmail() {
        return Email;
    }

}
