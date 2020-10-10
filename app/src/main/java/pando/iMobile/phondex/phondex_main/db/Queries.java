package pando.iMobile.phondex.phondex_main.db;

public class Queries {
    public static String SELECT_ALL(String table){
        return "SELECT * "+table;
    }
    public static String SELECT_PHONE(String table,int id){
        return "SELECT * "+table+" WHERE id = "+id;
    }
}
