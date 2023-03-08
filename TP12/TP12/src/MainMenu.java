import java.sql.SQLException;

import TP12SubClass.*;
import models.User;
import orms.RoleORM;
import orms.UserORM;
import utils.DbManager;

public class MainMenu {
    public static void main(String[] args) throws SQLException {
        GenralNeededMethod.create_database(GenralNeededMethod.DB);
        System.out.println();
        boolean userAcc=false;//just use to change menu;
        //userName="root"
        //password=""
        //root is only one who can do everything other user without admin role will be restricted to insert, delete and update
        int opt=0;
        do{

            System.out.println("""
            >>>> Main Menu <<<<\n
            1. Hotel listing
            2. Country listing
            3. City listing 
            4. Image listing
            5. Users listing 
            6. Role listing""");
            System.out.print((userAcc?"7. Logout":"7. Login")+ "\n0. Exit app\n\n");

            System.out.print("Choose option: ");
            opt=GenralNeededMethod.validInt();
            System.out.println("-");
            switch (opt){
                case 0:break;
                case 1:
                    HotelListing.main(args);
                    break;
                case 2:
                    CountryListing.main(args);
                    break;
                case 3:
                    CityListing.main(args);
                    break;
                case 4:
                    ImageListing.main(args);
                    break;
                case 5:
                    UserListing.main(args);
                    break;
                case 6:
                    RoleListing.main(args);
                    break;
                case 7:
                    if(userAcc){
                        GenralNeededMethod.superUser=false; 
                        userAcc=false;
                        System.out.println("<<<<<<-Logged out->>>>>>");
                        System.out.println("-\nPress enter to continue...");
                        GenralNeededMethod.sc.nextLine();
                        break;
                    }

                    System.out.print("Username: ");
                    String userName=GenralNeededMethod.sc.nextLine();
                    System.out.print("Passsword: ");
                    String pswd=GenralNeededMethod.sc.nextLine();
                    if(varifyUser(userName, pswd)){
                        System.out.println("<<<<<<-Logged in as "+userName+"->>>>>>");
                        userAcc=true;
                    }else System.out.println(">User or password incorrect!!!");
                            System.out.println("-\nPress enter to continue...");
                            GenralNeededMethod.sc.nextLine();
                    break;
                default: System.out.println("Choose available option!");
            }

        }while(opt!=0);

        GenralNeededMethod.sc.close();
    }





    private static boolean varifyUser(String userName, String pswd) throws SQLException{
        DbManager.getInstance(GenralNeededMethod.DB, "root", null);
        UserORM userORM=new UserORM();
        models.User u=new User(0, userName, null, null, null, null, null);
        u.setPass(pswd);
        var foundUser =userORM.rawQueryList("username='"+u.getUsername()+
                                        "' AND pass='"+u.getPass()+"'");

                                        
        if(pswd.equals("")&& userName.equals("root")){
            GenralNeededMethod.superUser=true;
            return true;//root
        }
        
        
        if(foundUser.size()>0){
            RoleORM roleORM =new RoleORM();
            var checkRole=roleORM.rawQueryList("id="+foundUser.get(0).getRole().getId() +" AND role='Admin'");
            if(checkRole.size()>0) GenralNeededMethod.superUser=true;
            return true;
        }
        return false;
    }
}
