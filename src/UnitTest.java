import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UnitTest {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int number = 5;
        while(number != 0){
            System.out.println("1-test foods \n2-test beverages \n3-test users \n4-test orders");
            System.out.print("Enter a number for test files between 1-4 (0 for close): ");
            number = scan.nextInt();
            switch (number){
                case 1: testfoods();
                break;
                case 2: testbeverages();
                break;
                case 3: testusers();
                break;
                case 4: testorders();
                break;
            }
        }



    }

    public static void testfoods() throws SQLException {
        System.out.println("FOOD TEST");
        GUI.getFastFoods();
        System.out.println("Fastfoods size: " + GUI.getFastFoods().size());
        for(int i = 0; i < GUI.getFastFoods().size(); i++){
            System.out.println("Food "+(i+1)+" is "+ GUI.getFastFoods().get(i).getName());
            System.out.println("------------------------------------------------------");
        }
    }

    public static void testbeverages() throws SQLException {
        System.out.println("BEVERAGE TEST");
        GUI.getBeverages();
        System.out.println("Bevergaes size: " + GUI.getBeverages().size());
        for(int i = 0; i < GUI.getBeverages().size(); i++){
            System.out.println("Beverage "+(i+1)+" is "+ GUI.getBeverages().get(i).getName());
            System.out.println("------------------------------------------------------");
        }
    }

    public static void testusers() throws SQLException {
        System.out.println("USERS TEST");
        GUI.getUsers();
        System.out.println("Usera size: " + GUI.getUsers().size());
        for(int i = 0; i < GUI.getUsers().size(); i++){
            System.out.println("User "+(i+1)+" is "+ GUI.getUsers().get(i).getUserName() + " and password is: " + GUI.getUsers().get(i).getUserPassword());
            System.out.println("------------------------------------------------------");
        }
    }

    public static void testorders() throws SQLException {
        System.out.println("ORDERS TEST");
        GUI.getOrders();
        System.out.println("Orders size: " + GUI.getOrders().size());
        for(int i = 0; i < GUI.getOrders().size(); i++){
            System.out.println("Order "+(i+1)+" food ID: "+ GUI.getOrders().get(i).getOrderedFoodID() +" beverage ID: "+ GUI.getOrders().get(i).getOrderedBeverageID());
            System.out.println("------------------------------------------------------");
        }
    }
}
