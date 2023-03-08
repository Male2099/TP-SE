package TP12SubClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import models.Country;
import orms.CountryORM;
import utils.DbManager;

public class CountryListing extends GenralNeededMethod{

    public static void main(String[] args) throws SQLException {
        create_database(DB);
        DbManager.getInstance(DB, "root", null);

        int opt;
        do{
            System.out.println("""
                    1. List all countries
                    2. Add a new country
                    3. Delelte country by ID
                    0. Exit country listing
                    """);
            System.out.print("Choose option: ");
            opt=validInt();

            if(!superUser && (opt==2||opt==3)) opt=4;
            else if(opt==4) opt=5;

            switch (opt){
                case 0: break;
                case 1:
                    listAllCountry();
                    break;
                case 2:
                    addNewCountry();
                    break;
                case 3:
                    deleteCountryByID();
                    break;
                case 4:
                    System.out.println(">Permission denied");
                    break;
                default: {System.out.println("choose available optoin!\n-"); opt=5;};
            }

            if(opt!=5){
                System.out.println("-\nPress enter to continue...");
                sc.nextLine();
            }
        }while(opt!=0);
    }


    private static void listAllCountry(){
        CountryORM countryORM=new CountryORM();
        //if no country in databse 
        if(countryORM.listAll().size()==0){System.out.println("-\nNo country to display!"); return;}

        System.out.println("----- Countries -------");
        System.out.printf("%-6s %s", "ID", "Country\n");
        for(var country : countryORM.listAll()){
            System.out.printf("%-6d %s\n",country.getId(),country.getCountry());
        }
    }


    private static void addNewCountry(){
        CountryORM countryORM=new CountryORM();
        System.out.print("Enter country name: ");
        Country country=new Country(0, sc.nextLine());
        if(country.getCountry().equals("")){System.out.println(">>>Country name can not be empty!!!");return;}
        countryORM.add(country);
        System.out.println("-\n-New country added, ID: "+country.getId());
    }

    
    // implement method to delete country by ID
    private static boolean delete(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
                var stmt = conn.createStatement();) {
            stmt.executeUpdate("use "+DB);
            stmt.executeUpdate("delete from countries where id=" + id);
            CountryORM countryORM=new CountryORM();
            var ch = countryORM.rawQueryList("id=" + id);
            if (ch.size() == 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void deleteCountryByID(){
        CountryORM countryORM=new CountryORM();
        //when no country
        if(countryORM.listAll().size()==0){System.out.println("-\n>No country to delete!"); return;}
        System.out.println("Please select country to delete");
        for(var country : countryORM.listAll()){
            System.out.println( country.getId()+". "+ country.getCountry());
        }

        System.out.print("Enter: ");
        var foundCountries=countryORM.rawQueryList("id="+validInt());


        if(foundCountries.size()>0 && delete(foundCountries.get(0).getId())){
            System.out.println("-\nCountry \""+ foundCountries.get(0).getCountry() +"\" deleted");
        }else System.out.println("Country not found");
    }
}
