import com.mysql.cj.protocol.Resultset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI {


    private static String loggedName;
    private static int loggedType, loggedID;
    private static JPanel pn;
    private static JPanel log;
    static int sira = 0;
    static int orderSira = 0;
    static int stockSira = 0;

    static {
        try {
            log = loginPanel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static Connection conn;
    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ordin","root" ,"Bartu1707***" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<FastFoods> fastFoods = new ArrayList<>();
    private static ArrayList<Bevarages> beverages = new ArrayList<>();
    private static ArrayList<Computers> computers = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static ArrayList<ImageIcon> foodImages = new ArrayList<>();
    private static ArrayList<ImageIcon> beveragesImages = new ArrayList<>();
    private static ArrayList<FastFoods> basket = new ArrayList<>();
    private static ArrayList<Bevarages> basket2 = new ArrayList<>();

    static {
        try {
            orders = getOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            users = getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            fastFoods = getFastFoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            beverages = getBeverages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            computers = getComputers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static JPanel usersPanel;
    private static JPanel menuPanel;
    private static JPanel orderPanel;
    private static JPanel basketPanel;
    private static JPanel stockPanel;


    private static JPanel deskPanel;
    private static JFrame frame = new JFrame("ORDIN.NET");

    static {
        try {
            deskPanel = deskPanel();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static JPanel selectPanel;
    static {
        try {
            selectPanel = userSelectCompPanel();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        try {
            usersPanel = usersPanel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        foodImages = getFoodImages(fastFoods);
        beveragesImages = getBeveragesImages(beverages);
        menuPanel = menuPanel();
        Class.forName("com.mysql.cj.jdbc.Driver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBackground(Color.white);
        log.setBounds(750,250,400,400);
        menuPanel.setBounds(300,10,1100,1000);
        selectPanel.setBounds(300,10,1500,1000);
        deskPanel.setBounds(300,10,1300,1000);
        usersPanel.setBounds(300,10,1500,1000);
        usersPanel.setVisible(false);
        menuPanel.setVisible(false);
        selectPanel.setVisible(false);
        deskPanel.setVisible(false);
        frame.add(usersPanel);
        frame.add(selectPanel);
        frame.add(deskPanel);
        frame.add(menuPanel);
        frame.add(log);
        log.setVisible(true);
        log.setBackground(Color.lightGray);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.validate();
        frame.revalidate();
        frame.setVisible(true);

    }

    private static JPanel adminPanel() throws SQLException, IOException {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(300,1000);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton bt1 = new JRadioButton("Masalar");
        JRadioButton bt2 = new JRadioButton("Menü");
        JRadioButton bt3 = new JRadioButton("Siparişler");
        JRadioButton bt4 = new JRadioButton("UserComp");
        JRadioButton bt5 = new JRadioButton("Kullanıcılar");
        JRadioButton bt6 = new JRadioButton("Stoklar");
        bt1.setBackground(Color.white);
        bt2.setBackground(Color.white);
        bt3.setBackground(Color.white);
        bt4.setBackground(Color.white);
        bt5.setBackground(Color.white);
        bt6.setBackground(Color.white);
        Button button = new Button();
        button.setLabel("Çıkış Yap");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pn.setVisible(false);
                deskPanel.setVisible(false);
                orderPanel.setVisible(false);
                menuPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(false);
                frame.remove(stockPanel);
                log.setVisible(true);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        bg.add(bt1);
        bg.add(bt2);
        bg.add(bt3);
        bg.add(bt4);
        bg.add(bt5);
        bg.add(bt6);
        button.setBounds(90,850,100,40);
        if(loggedType ==1){
            bt1.setBounds(20,20,100,20);
            bt2.setBounds(20,50,100,20);
            bt3.setBounds(20,80,100,20);
            //bt4.setBounds(20,110,100,20);
            bt5.setBounds(20,110,100,20);
            bt6.setBounds(20,140,100,20);
            panel.add(bt1);
            panel.add(bt2);
            panel.add(bt3);
            //panel.add(bt4);
            panel.add(bt5);
            panel.add(bt6);
        }else if(loggedType == 2){
            bt3.setBounds(20,20,100,20);
            bt6.setBounds(20,50,100,20);
            panel.add(bt3);
            panel.add(bt6);
        }else{
            orderPanel = userOrderPanel(loggedID);
            orderPanel.setVisible(false);
            bt2.setBounds(20,20,100,20);
            bt3.setBounds(20,50,100,20);
            bt4.setBounds(20,80,100,20);
            panel.add(bt4);
            panel.add(bt2);
            panel.add(bt3);
        }
        panel.add(button);
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskPanel.setVisible(true);
                menuPanel.setVisible(false);
                orderPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(false);
                basketPanel.setVisible(false);
                stockPanel.setVisible(false);
                frame.remove(stockPanel);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deskPanel.setVisible(false);
                menuPanel.setVisible(true);
                orderPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(false);
                basketPanel.setVisible(false);
                stockPanel.setVisible(false);
                frame.remove(stockPanel);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(orderSira==1){
                    frame.remove(orderPanel);
                }
                try {
                    orders = getOrders();
                    if(loggedType==3){
                        orderPanel = userOrderPanel(loggedID);
                    }else{
                        orderPanel = orderPanel();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                orderSira=1;
                frame.add(orderPanel);
                frame.remove(stockPanel);
                orderPanel.setVisible(true);
                deskPanel.setVisible(false);
                menuPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(false);
                basketPanel.setVisible(false);
                stockPanel.setVisible(false);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectPanel = userSelectCompPanel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                deskPanel.setVisible(false);
                menuPanel.setVisible(false);
                orderPanel.setVisible(false);
                selectPanel.setVisible(true);
                usersPanel.setVisible(false);
                basketPanel.setVisible(false);
                stockPanel.setVisible(false);
                frame.remove(selectPanel);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshUsers();
                deskPanel.setVisible(false);
                menuPanel.setVisible(false);
                orderPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(true);
                basketPanel.setVisible(false);
                stockPanel.setVisible(false);
                frame.remove(stockPanel);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });

        bt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stockSira==1){
                    frame.remove(stockPanel);
                }
                try {
                    stockPanel = stockPanel();
                    frame.add(stockPanel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                stockSira=1;
                deskPanel.setVisible(false);
                menuPanel.setVisible(false);
                orderPanel.setVisible(false);
                selectPanel.setVisible(false);
                usersPanel.setVisible(false);
                basketPanel.setVisible(false);
                stockPanel.setVisible(true);
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        });
        panel.setVisible(true);
        return panel;
    }

    private static JPanel loginPanel() throws SQLException {
        JPanel logPanel = new JPanel();

        return logPanel;
    }

    private static JPanel signinPanel(){
        JPanel signinPanel = new JPanel();
        return signinPanel;
    }

    private static JPanel menuPanel(){
        JPanel menuPanel = new JPanel();

        return menuPanel;
    }

    private static JPanel basketPanel(ArrayList<FastFoods> foodBasket, ArrayList<Bevarages> beverageBasket){

        return basketPanel;
    }

    private static JPanel deskPanel() throws SQLException, IOException {
        JPanel deskPanel = new JPanel();

        return deskPanel;
    }

    private static JPanel userSelectCompPanel() throws IOException, SQLException {
        Statement st = conn.createStatement();
        JPanel selectPanel = new JPanel();

        return selectPanel;
    }

    private static JPanel orderPanel() throws SQLException {
        Statement st = conn.createStatement();
        JPanel orderPanel = new JPanel();
        return orderPanel;
    }

    private static void userDeskFrame(int masa) throws SQLException {
    }

    private static void deskControl(int masa) throws SQLException {
    }

    private static JPanel usersPanel() throws SQLException {
        JPanel usersPanel = new JPanel();
        return usersPanel;
    }

    private static JPanel stockPanel() throws SQLException {
        beverages = getBeverages();
        fastFoods = getFastFoods();
        JPanel stockPanel = new JPanel();
        return stockPanel;
    }

    private static JPanel userOrderPanel(int userID) throws SQLException {
        Statement st = conn.createStatement();
        JPanel userOrderPanel = new JPanel();
        return userOrderPanel;

    }

    private static ArrayList<User> getUsers() throws SQLException {
        users.clear();
        return users;
    }

    private static ArrayList<FastFoods> getFastFoods() throws SQLException {
        fastFoods.clear();
        return fastFoods;
    }

    private static ArrayList<Bevarages> getBeverages() throws SQLException {
        beverages.clear();
        return beverages;
    }

    private static ArrayList<Computers> getComputers() throws SQLException {
        computers.clear();
        return computers;
    }

    private static ArrayList<Order> getOrders() throws SQLException {
        orders.clear();
        return orders;
    }

    private static boolean checkUser(String userName, String password) throws SQLException {
        boolean check = false;

        return check;
    }

    private static ArrayList<ImageIcon> getFoodImages(ArrayList<FastFoods> fastFoods) throws IOException {


        return foodImages;
    }

    private static ArrayList<ImageIcon> getBeveragesImages(ArrayList<Bevarages> beverages) throws IOException {

        return beveragesImages;
    }

    private static ImageIcon getImage(String path) throws IOException {
        ImageIcon myPicture = new ImageIcon(path);
        return myPicture;
    }

    private static void setOrder(int id, ArrayList<FastFoods> orderFood, ArrayList<Bevarages> orderBevarage) throws SQLException {

    }

    private static void addUser(String userName, String password, int userType) throws SQLException {

    }

    private static void setActiveUser(String usr, int id) throws SQLException {

    }

    private static int getActiveUserType(String usr) throws SQLException {
        int id = 0;

        return id;
    }

    private static int getActiveUserID(String usr) throws SQLException {
        int id = 0;
        return id;
    }

    private static void refreshOrderPanel() throws SQLException {
        orderPanel.revalidate();
        orderPanel.repaint();
        frame.revalidate();
        frame.repaint();
        orderPanel();
    }

    private static void refreshUsers(){

    }
    private static ArrayList<User> getUsers() throws SQLException {
        users.clear();
        Statement st = conn.createStatement();
        String cd = "Select * from users";
        ResultSet res = st.executeQuery(cd);
        User usr;
        while(res.next()){
            usr = new User(Integer.parseInt(res.getString(1)),Integer.parseInt(res.getString(2)), (res.getString(3)),res.getString(4));
            users.add(usr);
        }
        return users;
    }

    private static ArrayList<FastFoods> getFastFoods() throws SQLException {
        fastFoods.clear();
        Statement st = conn.createStatement();
        String cd = "Select * from fastfoods";
        ResultSet res = st.executeQuery(cd);
        FastFoods fs;
        while(res.next()){
            fs = new FastFoods(Integer.parseInt(res.getString(1)), (res.getString(2)),Float.parseFloat(res.getString(3)), res.getString(4),Integer.parseInt(res.getString(5)));
            fastFoods.add(fs);
        }
        return fastFoods;
    }

    private static ArrayList<Bevarages> getBeverages() throws SQLException {
        beverages.clear();
        Statement st = conn.createStatement();
        String cd = "Select * from beverages";
        ResultSet res = st.executeQuery(cd);
        Bevarages bv;
        while(res.next()){
            bv = new Bevarages(Integer.parseInt(res.getString(1)), (res.getString(2)),Float.parseFloat(res.getString(3)),res.getString(4),Integer.parseInt(res.getString(5)));
            beverages.add(bv);
        }
        return beverages;
    }

    private static ArrayList<Computers> getComputers() throws SQLException {
        computers.clear();
        Statement st = conn.createStatement();
        String cd = "Select * from computers";
        ResultSet res = st.executeQuery(cd);
        Computers cp;
        while(res.next()){
            boolean empty = false;
            if(res.getString(3).equals("1")){
                empty = false;
            }else{
                empty = true;
            }
            cp = new Computers(Integer.parseInt(res.getString(1)), (res.getString(2)),empty,res.getString(4),res.getString(5),Double.parseDouble(res.getString(6)));
            computers.add(cp);
        }
        return computers;
    }

    private static ArrayList<Order> getOrders() throws SQLException {
        orders.clear();
        Statement st = conn.createStatement();
        String cd = "Select * from orders";
        ResultSet res = st.executeQuery(cd);
        Order or;
        while(res.next()){
            int foodID;
            int beverageID;
            int id = Integer.parseInt(res.getString(1));
            int personID = Integer.parseInt(res.getString(2));
            if(res.getString(3)==null){
                foodID = 0;
            } else{
                foodID = Integer.parseInt(res.getString(3));
            }
            if(res.getString(4)==null){
                beverageID = 0;
            } else{
                beverageID = Integer.parseInt(res.getString(4));
            }
            or = new Order(id, personID, foodID, beverageID, res.getString(5),Double.parseDouble(res.getString(6)));
            orders.add(or);
        }
        return orders;
    }
}
