/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;

import java.sql.*;
//import java.sql.ResultSet;
/**
 *
 * @author ART3MIS
 */
public class DataBaseConnection {
    String DB_URL = "jdbc:mysql://localhost/banka";
    String USER = "root";
    String PASS = "";
    
    private Connection conn;    
    
    public Connection baglan() {
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("veritabanı bağlanıtısı başarılı");
        }
        catch(SQLException se){
            System.out.println("veritabanı bağlanıtısı başarısız");
            se.printStackTrace();
        }
        catch(Exception e){
            System.out.println("veritabanı bağlanıtısı başarısız");
            e.printStackTrace();
        }
        return conn ;
    }
    
    public boolean singup(String tc, String ad, String soyad, String email, String sifre) throws SQLException{
        if(conn == null){
            System.out.println("Bağlanti basarisiz , Bagalaniyorum");
            baglan();
        }
        String query = "INSERT INTO `kullanicilar` (`tc`, `ad`, `soyad`, `email`, `sifre`) VALUES (?,?,?,?,?);";

        try(PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setString(1, tc);
                pstmt.setString(2, ad);
                pstmt.setString(3, soyad);        
                pstmt.setString(4, email);
                pstmt.setString(5, sifre);
                pstmt.executeUpdate();        
                pstmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return true;                

    }
    
    public boolean login(String email, String sifre)  throws SQLException{
        if(conn == null){
            System.out.println("Bağlanti basarisiz , Bagalaniyorum");
            baglan();
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from kullanicilar where email='"+email+"'and sifre='"+sifre+"';");    
        try{
            if(!rs.next()){
                return false;
            }
            else{
                User.userTC = rs.getString("tc");
                User.userName = rs.getString("ad");
                User.userLastName = rs.getString("soyad");
                User.userEmail = rs.getString("email");
                User.userPassword = rs.getString("sifre"); 
                User.userAccountBalanceTRY = rs.getDouble("tl_bakiye");                
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean deposit(String email, String sifre, double tl_bakiye)  throws SQLException{
        baglan();
        try{
            String query = "UPDATE kullanicilar SET `tl_bakiye` = `tl_bakiye` + ?  WHERE email = ?  and sifre = ? ";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, tl_bakiye);
                pstmt.setString(2, email);
                pstmt.setString(3, sifre);
               int i =  pstmt.executeUpdate();
               pstmt.close();
               if(!(i > 0)){
                   return false;
               }
            }
        }
        catch(SQLException ae){
            ae.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean withdraw(String email, String sifre, double tl_bakiye)  throws SQLException{
        baglan();
        try{
            String query = "UPDATE kullanicilar SET `tl_bakiye` = `tl_bakiye` - ?  WHERE email = ?  and sifre = ? ";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, tl_bakiye);
                pstmt.setString(2, email);
                pstmt.setString(3, sifre);
               int i =  pstmt.executeUpdate();
               pstmt.close();
               if(!(i > 0)){
                   return false;
               }
            }
        }
        catch(SQLException ae){
            ae.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean eft(String email, String sifre, double tl_bakiye, String tc) throws SQLException{
       baglan();
        try{
           // String query = "UPDATE kullanicilar SET `tl_bakiye` = `tl_bakiye` + ?  WHERE email = ?  and sifre = ? ";
           String query = "UPDATE kullanicilar SET `tl_bakiye` = `tl_bakiye` + ?  WHERE tc = ?";
           String query2 = "UPDATE kullanicilar SET `tl_bakiye` = `tl_bakiye` - ?  WHERE email = ?  and sifre = ? ";
           //UPDATE `kullanicilar` SET `tl_bakiye`='50' WHERE `tc`="210111381"
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, tl_bakiye);
                pstmt.setString(2, tc);
               int i =  pstmt.executeUpdate();
               pstmt.close();
               if(!(i > 0)){
                   return false;
               }
            }
            try(PreparedStatement pstmtt = conn.prepareStatement(query2)){
                pstmtt.setDouble(1, tl_bakiye);
                pstmtt.setString(2, email);
                pstmtt.setString(3, sifre);
                int i =  pstmtt.executeUpdate();
               pstmtt.close();
               if(!(i > 0)){
                   return false;
               }
            }
        }
        catch(SQLException ae){
            ae.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return true;
    }
}
