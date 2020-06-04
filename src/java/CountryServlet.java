import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet(urlPatterns = {"/CountryServlet"}) //Servlet Name
public class CountryServlet extends HttpServlet {
 // VARIABLE DECLARATION 
    String name;
    String capital;
    String population;
    String nativename;
    String topLevelDomain;
    String alpha2Code;
    String alpha3Code;
    String callingCodes;
    String region; 
    String subregion;
    String demonym;
    String area; 
    String timezones;
    String numericCode;
    String code;
    String currencyname;
    String languagename;
    String languagenamenative;
    String acronym;
    String regionname;
    String flag;
    String symbol;
     String cioc;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        //Reading Response From Website
         String search = request.getParameter("search");
                 
         HttpClient httpclient = HttpClients.createDefault(); 
         HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/name/"+search+"?fullText=true"); 
         HttpResponse responses = httpclient.execute(httpGet);
         int status = responses.getStatusLine().getStatusCode();
         if (status >= 200 && status < 300) {
         HttpEntity entity = responses.getEntity();
         if(entity != null){ 
         String responsesString = EntityUtils.toString(entity);
         //Initialzing JSON parser 
         JSONParser parser = new JSONParser(); 
         // Parsing result to JSON object
         Object jsonObj = parser.parse(responsesString);
         // Storing JSON object to Array
         JSONArray array = (JSONArray) jsonObj;
         JSONObject Obj = (JSONObject) array.get(0);
         //Stroing Value to variables
         name = Obj.get("name").toString();
         JSONArray  topLevelDomain1 = (JSONArray) Obj.get("topLevelDomain");
         topLevelDomain = topLevelDomain1.get(0).toString();
         alpha2Code   = Obj.get("alpha2Code").toString();
         alpha3Code   = Obj.get("alpha3Code").toString(); 
         flag = Obj.get("flag").toString(); 
         JSONArray  callingCodes1 = (JSONArray) Obj.get("callingCodes");
         callingCodes  =  callingCodes1.get(0).toString();
         capital =  Obj.get("capital").toString();
         region =  Obj.get("region").toString();
         subregion =  Obj.get("subregion").toString();
         population =  Obj.get("population").toString();
         demonym =  Obj.get("demonym").toString();
         area =  Obj.get("area").toString();
          cioc =  Obj.get("cioc").toString();
         JSONArray  timezones1 = (JSONArray) Obj.get("timezones");
         timezones = timezones1.get(0).toString();
         nativename = Obj.get("nativeName").toString();
         numericCode = Obj.get("numericCode").toString();
         JSONArray  currency = (JSONArray) Obj.get("currencies");
         JSONObject  info1 =  (JSONObject) currency.get(0);
         code = info1.get("code").toString();
         currencyname = info1.get("name").toString();
         symbol = info1.get("symbol").toString();
         JSONArray  language = (JSONArray) Obj.get("languages");
         JSONObject  info2 =  (JSONObject) language.get(0);
         languagename = info2.get("name").toString();
         languagenamenative = info2.get("nativeName").toString();
        JSONArray  regionalBlocs = (JSONArray) Obj.get("regionalBlocs");
        JSONObject  info3 =  (JSONObject) regionalBlocs.get(0);
        acronym = info3.get("acronym").toString();
        regionname =info3.get("name").toString();
        }
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Writing the Website page Back to Website
            out.println("<!DOCTYPE html>");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\""+"xmlns:h=\"http://xmlns.jcp.org/jsf/html\"" +"xmlns:ui=\"http://xmlns.jcp.org/jsf/facelets\">");
            out.println("<head>");
            out.println("<title>Country of the World</title>");  
            out.println("<link href=resources/styles.css rel=stylesheet type=text/css></link>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></link>");
            out.println("</head>");
            out.println("<body id=\"page3\">");
            out.println("<ul>"+"<li><a class=\"active\" href=\"faces/index.xhtml\">Home</a></li>" +"<li><a class=\"active\" href=\"faces/about.xhtml\">About</a></li>"+"<li><a class=\"active\" href=\"faces/ContactUs.xhtml\">Feedback</a></li>"+"</ul>");
            out.println(" <u2>\n" +"<form class=\"example\" action=\"CountryServlet\" style=\"margin:auto;max-width:300px\">\n" +
            "<input type=\"text\" placeholder=\"Search A Country\" name=\"search\"></input>\n" +
            "<button id=\"button\" > <i class=\"fa fa-search\"></i></button>\n" +
            "</form>\n"+"</u2>");
            out.println("<h1 align=\"center\">"+ name +"</h1>");
            out.println("<br/>");
            out.println("<div>");
            out.println("<div align=\"center\"> <img src=\""+flag+"\"style=\"height:200px;\"></div>");
            out.println("</div>");           
            out.println("<div id=\"page3name\">");
            out.println("<h2>Name</h2>");
            out.println("<p> Name: "+name+" <br>Native name: "+nativename+"<br>Alternative Names: "+cioc+"</p>");
            out.println("</div>");
            out.println("<br/>");            
            out.println("<div id=\"page3geo\">");
            out.println("<h2>Geography</h2>");
            out.println("<p>Region: "+region+" <br> Sub Region: "+subregion+ " <br> Region name::  "+regionname+"<br> Capital: "+capital+"<br> Region: "+region+"<br>Population:   " +population+
                    "<br> Demonym:  "+demonym+"<br> Area: "  +area+"km²" +"<br> Time Zone: " +timezones+" <br> Currency name:  " +currencyname+"<br> Language name:  " +languagename+
                    "<br> Language Name Native: "+languagenamenative+ "<br> Acronym:   "+acronym+"</p>");
            out.println("</div>");
            out.println("<br/>");
            out.println("<br/>");            
            out.println("<div id=\"page3codes\">");
            out.println("<h2>Code</h2>");
            out.println("<p>ISO 3166-1 Numeric Codes:   "+numericCode+"<br> Currency Code and Symbol:  " +code+ " , "+  symbol+"<br> Alpha2code:  "+alpha2Code+"<br> Alpha3code:  "+alpha3Code+" <br> Top Level Domain: "+topLevelDomain+"<br> Calling Code: "+ callingCodes +"</p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>"); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    try {
                        processRequest(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(CountryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    try {
                        processRequest(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(CountryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
