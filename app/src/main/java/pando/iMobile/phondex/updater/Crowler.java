/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pando.iMobile.phondex.updater;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import org.jsoup.Jsoup;

/**
 *
 * @author Khoujani
 */
public class Crowler {
    
    
    //tr-hover
    //user opinions and reviews
    public static void main (String[] args){

        try {
            
         Connection connection = null;
        Statement statement = null;
        connection = 
        DriverManager.getConnection("jdbc:sqlite:\\home\\khoujani\\Desktop\\iMobile\\crowler\\phones.db");
//        Class.forName("");
        statement = connection.createStatement();
        statement.executeUpdate(createTableAll());
        statement.executeUpdate("CREATE INDEX IF NOT EXISTS s_details ON all_phone (details);");
        statement.executeUpdate("CREATE INDEX IF NOT EXISTS s_name ON all_phone (name);");
        
        
            
            String baseURL = "https://www.gsmarena.com/";
                BufferedReader reader = new BufferedReader(new FileReader("\\home\\khoujani\\Desktop\\iMobile\\crowler\\urls.txt"));

                String url;
    while((url = reader.readLine()) != null){
        
        statement.executeUpdate(createTable(url.split("-")[0]));
        
        
        System.err.println("Download->"+url);
        //phones page
        String phones = Jsoup.connect(baseURL+url).get().html().split("review-body")[1].split("review-nav pullNeg col pushT10")[0];

        Scanner linner = new Scanner(phones);
        
        while (linner.hasNextLine()) {
            String next = linner.nextLine();
            
            
              //tr-hover
    //user opinions and reviews
        
            if (next.contains(".php")) {
                
            
        
        try {
            
        
              //row
              //ttl
              //nfo

              String details = Jsoup.connect(baseURL+next.split("\"")[1].split("\"")[0]).get().html();

            String name = details.split("<title>")[1].split("<\title>")[0];
            if (name.contains(" - ")){
                name = name.split(" - ")[0];
            }


            String image_src = details.split("specs-photo-main")[1].split("specs-spotlight-features")[0]
                      .split("src=\"")[1].split("\"")[0];
              
              
              Scanner details_scanner = new Scanner(details.split("tr-hover")[1].split("user opinions and reviews")[0]);
              
             String s = "";
             String category = "";
             String ttl = "";
             String nfo = "";
            JsonArray array = new JsonArray();
            JsonObject Main = new JsonObject();
            JsonObject property = new JsonObject();
              
               while(details_scanner.hasNextLine()){
                   
               String details_next = details_scanner.nextLine();
            
               
                   if (details_next.contains("row")) {
                    s = Jsoup.parse(details_next).text();
                    category = s;
                    
                }else if (details_next.contains("ttl") && details_next.contains("nfo")){
                     ttl = details_next.split("</td>")[0];
                    ttl=Jsoup.parse(ttl).text();
                     nfo = details_next.split("</td>")[1];
                    nfo=Jsoup.parse(nfo).text();
                    property.addProperty(ttl,nfo);
                    
                    
                } else if(details_next.contains("ttl")){
                    ttl=Jsoup.parse(details_next).text();
                    
                    
                }else if (details_next.contains("nfo")) {
                    nfo=Jsoup.parse(details_next).text();
                    property.addProperty(ttl, nfo);
                    
                }else if (details_next.contains("</table>")) {
//                    s+=Jsoup.parse(details_next).text()+"(,)";

                Main.add(category, property);
                array.add(Main);
                       
                property = new JsonObject();
                Main = new JsonObject();
                
                category = "";
                ttl = "";
                nfo = "";

                   
                }else if (details_next.contains("<h2>")){


                       System.out.println(image_src);
                    System.out.println("Phone->"+name);
                    System.out.println("Details->"+array.toString());
                    
                    
                       if (Download_image(image_src,
                               "\\home\\khoujani\\Desktop\\iMobile\\crowler\\images\\"+ url.split("-")[0],
                                       image_src.split("/")[image_src.split("/").length - 1])
                               && !statement.execute(addPhoneToAll(name,image_src, array.toString() ,url.split("-")[0])) &&
                               !statement.execute(addPhone(url.split("-")[0], name,image_src, array.toString(), url.split("-")[0]))) {
                           
                           System.out.println("*Success*\n\n");
                           
                       }else{
                           System.err.println("*Faild*\n\n");
                       }


                       
            }
          
                   
                   
               }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
//                statement.close();
//                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
           
            
        }
        
        }
    
    }
            
        statement.close();
        connection.close();
        
        } catch (Exception e) {
            
            System.err.println(""+e.getMessage());
            
        }
    
    }
   


        public static String addPhone(String table_name , String name ,String img, String details , String brand){

          System.out.println("Table->"+table_name);

           if (table_name.contains("&") || table_name.contains(";")) {
            table_name = "asus";
           }

           String name_fix = "";
          String sql = "";
          if (name.contains("'")) {

              name_fix = name.split("'")[0]+name.split("'")[1];
              sql = "INSERT INTO "+table_name+" (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";
          }else if(name.contains("`")){

              name_fix = name.split("`")[0]+name.split("`")[1];
              sql = "INSERT INTO "+table_name+" (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";

          }else if(name.contains("&")){

           name_fix = name.split("&")[0]+name.split("&")[1];
           sql = "INSERT INTO "+table_name+" (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";

          }else{
          sql = "INSERT INTO "+table_name+" (name,img, details,brand) " +
                     "VALUES('"+name+"','"+img+"', '"+details+"', '"+brand+"')";
          }



        return sql;
    }

      

      public static String addPhoneToAll(String name ,String img, String details,String brand){
          
          String name_fix = "";
          String sql = "";
          if (name.contains("'")) {
              
              name_fix = name.split("'")[0]+name.split("'")[1];
              sql = "INSERT INTO all_phone (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";
          }else if(name.contains("`")){
              
              name_fix = name.split("`")[0]+name.split("`")[1];
//              name_fix = name.replace("`", "_");
              sql = "INSERT INTO all_phone (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";
              
          }else if(name.contains("&")){
              
           name_fix = name.split("&")[0]+name.split("&")[1];
           sql = "INSERT INTO all_phone (name,img, details,brand) " +
                     "VALUES('"+name_fix+"','"+img+"', '"+details+"', '"+brand+"')";
           
          }else{
          sql = "INSERT INTO all_phone (name,img, details,brand) " +
                     "VALUES('"+name+"','"+img+"', '"+details+"', '"+brand+"')"; 
          }
          
        
        
        return sql;
    }
    
    public static String createTable(String table_name){
    
        if (table_name.contains("&") || table_name.contains(";")) {
            table_name = "asus";
        }
        String sql = "CREATE TABLE IF NOT EXISTS " + table_name +" "+
                   "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                   " name VARCHAR(255) UNIQUE, " + 
                   " img VARCHAR(255), " + 
                   " details TEXT," + 
                   " brand VARCHAR(40));"; 
        return sql;
    }
    public static String createTableAll(){
    
        String sql = "CREATE TABLE IF NOT EXISTS all_phone "+
                   "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                   " name VARCHAR(255), " + 
                   " img TEXT, " +
                   " details TEXT," + 
                   " brand VARCHAR(40));"; 
        
        return sql;
    }
    
    
       
public static boolean Download_image(String img_src, String folder ,String fileName) throws IOException {

    InputStream inputStream = null;

    OutputStream outputStream = null;

    try {

        File brand_folder = new File(folder);
        if (brand_folder.exists()) {
            
        }else{
        brand_folder.mkdirs();
        }
        
        String path = folder +"\\"+ fileName;
        


        URL url = new URL(img_src);

        // This user agent is for if the server wants real humans to visit
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

        URLConnection con = url.openConnection();

        con.setRequestProperty("User-Agent", USER_AGENT);

        int contentLength = con.getContentLength();
        System.out.println("File contentLength = " + contentLength + " bytes");


        inputStream = con.getInputStream();
        
        outputStream = new FileOutputStream(path);

        byte[] buffer = new byte[2048];

        int length;
        int downloaded = 0; 

        while ((length = inputStream.read(buffer)) != -1) 
        {
            outputStream.write(buffer, 0, length);
            downloaded+=length;

        }
        System.out.println("Downlad Status: " + (downloaded * 100) / (contentLength * 1.0) + "%");
        
    outputStream.close();
    inputStream.close();
    
        return true;
    } catch (Exception ex) {
        System.err.println(""+ex.getMessage());
    return false;
    }

}
    
    
    
    
}
