
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
        logPanel.setLayout(null);
        logPanel.setSize(400,400);
        JLabel usr = new JLabel();
        usr.setText("Username:");
        JLabel psw = new JLabel();
        psw.setText("Password:");
        TextField tx1 = new TextField();
        usr.setBounds(125,55,150,20);
        tx1.setBounds(125,80,150,20);
        JPasswordField tx2 = new JPasswordField();
        psw.setBounds(125,145,150,20);
        tx2.setBounds(125,170,150,20);
        Button button = new Button();
        button.setLabel("Log in");
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(checkUser(tx1.getText(),String.valueOf(tx2.getPassword()))){
                        setActiveUser(tx1.getText(), getActiveUserType(tx1.getText()));
                        pn = adminPanel();
                        pn.setBackground(Color.white);
                        pn.setBounds(10,10,290,1000);
                        frame.add(pn);
                        frame.revalidate();
                        frame.repaint();
                        pn.setVisible(true);
                        log.setVisible(false);
                        tx1.setText("");
                        tx2.setText("");
                    }
                } catch (SQLException | IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        button.setBounds(125,220,150,50);
        Button button2 = new Button();
        button2.setLabel("Sign in");
        button2.setFont(new Font("Serif", Font.BOLD, 14));
        button2.setBounds(125,290,150,50);
        logPanel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logPanel.setVisible(false);
                signinPanel();
            }
        });
        logPanel.add(usr);
        logPanel.add(psw);
        logPanel.add(tx1);
        logPanel.add(tx2);
        logPanel.add(button);
        logPanel.setVisible(true);
        return logPanel;
    }

    private static JPanel signinPanel(){
        JPanel signinPanel = new JPanel();
        signinPanel.setLayout(null);
        signinPanel.setSize(400,400);
        JLabel usr = new JLabel();
        usr.setText("Username:");
        JLabel psw = new JLabel();
        psw.setText("Password:");
        JLabel psw2 = new JLabel();
        psw2.setText("Password Again:");
        TextField tx1 = new TextField();
        usr.setBounds(125,55,150,20);
        tx1.setBounds(125,80,150,20);
        JPasswordField tx2 = new JPasswordField();
        psw.setBounds(125,145,150,20);
        tx2.setBounds(125,170,150,20);
        JPasswordField tx3 = new JPasswordField();
        psw2.setBounds(125,235,150,20);
        tx3.setBounds(125,260,150,20);
        Button button = new Button();
        button.setLabel("Sign in");
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                for(int i = 0; i < users.size(); i++){
                    if(tx1.getText().equals(users.get(i).getUserName())){
                        check = true;
                        JOptionPane.showMessageDialog(null, "Bu kullanıcı adı daha önceden alınmış!");
                    }
                }
                if(!check){
                    String ps1 = String.valueOf(tx2.getPassword());
                    String ps2 = String.valueOf(tx3.getPassword());
                    if(!ps1.equals("")&&!ps2.equals("")&&!tx1.getText().equals("")){
                        if(Arrays.equals(tx2.getPassword(), tx3.getPassword())){
                            signinPanel.setVisible(false);
                            log.setVisible(true);
                            try {
                                addUser(tx1.getText(),String.valueOf(tx2.getPassword()),3);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Şifreler eşleşmiyor!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Değerler boş olamaz!");
                    }
                }
            }
        });
        button.setBounds(125,310,150,50);
        signinPanel.setBackground(Color.lightGray);
        signinPanel.add(usr);
        signinPanel.add(psw);
        signinPanel.add(psw2);
        signinPanel.add(tx1);
        signinPanel.add(tx2);
        signinPanel.add(tx3);
        signinPanel.add(button);
        signinPanel.setBounds(750,250,400,400);
        signinPanel.setVisible(true);
        frame.add(signinPanel);
        return signinPanel;
    }

    private static JPanel menuPanel(){
        JPanel menuPanel = new JPanel();
        ArrayList<JPanel> foodPanel = new ArrayList<>();
        ArrayList<JRadioButton> radioButtons = new ArrayList<>();
        GridLayout gr = new GridLayout();
        gr.setColumns(5);
        gr.setRows(5);
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < fastFoods.size(); i++){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(200,250);
            JLabel picLabel = new JLabel(foodImages.get(i));
            JRadioButton radioButton = new JRadioButton(fastFoods.get(i).getName());
            radioButton.setActionCommand(fastFoods.get(i).getName());
            picLabel.setBounds(0,0,200,170);
            radioButton.setBounds(0,172,200,20);
            if(fastFoods.get(i).getStock()==0){
                JLabel label = new JLabel();
                label.setText("Şu an ürün mevcut değil.");
                label.setBounds(0,172,200,20);
                panel.add(label);
            }else{
                panel.add(radioButton);
                radioButtons.add(radioButton);
            }
            panel.add(picLabel);
            foodPanel.add(panel);
            bg.add(radioButton);
            panel.setVisible(true);
            menuPanel.add(panel);
        }
        for(int i = 0; i < beverages.size(); i++){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(200,250);
            JLabel picLabel = new JLabel(beveragesImages.get(i));
            JRadioButton radioButton = new JRadioButton(beverages.get(i).getName());
            radioButton.setActionCommand(beverages.get(i).getName());
            picLabel.setBounds(0,0,200,170);
            radioButton.setBounds(0,172,200,20);
            if(beverages.get(i).getStock()==0){
                JLabel label = new JLabel();
                label.setText("Şu an ürün mevcut değil.");
                label.setBounds(0,172,200,20);
                panel.add(label);
            }else{
                panel.add(radioButton);
                radioButtons.add(radioButton);
            }
            panel.add(picLabel);
            foodPanel.add(panel);
            bg.add(radioButton);
            panel.setVisible(true);
            menuPanel.add(panel);
        }
        JPanel eklePanel = new JPanel();
        JButton ekleButon = new JButton("Sepete Ekle");
        ekleButon.setFont(new Font("Calibre",Font.BOLD,20));
        eklePanel.setLayout(null);
        eklePanel.setSize(200,250);
        ekleButon.setSize(200,100);
        ekleButon.setBounds(25,100,150,70);
        eklePanel.add(ekleButon);
        ekleButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String a = bg.getSelection().getActionCommand();
               FastFoods fs;
               Bevarages bv;
               for(int i = 0; i < fastFoods.size(); i++){
                   if(a.equals(fastFoods.get(i).getName())){
                       fs = fastFoods.get(i);
                       basket.add(fs);
                   }
               }
               for(int i = 0; i < beverages.size(); i++){
                   if(a.equals(beverages.get(i).getName())){
                       bv = beverages.get(i);
                       basket2.add(bv);
                   }
               }
               bg.clearSelection();
               if(sira==1){
                   frame.remove(basketPanel);
               }
               sira=1;
               basketPanel(basket,basket2);
               frame.revalidate();
               frame.repaint();
            }
        });
        menuPanel.add(eklePanel);
        menuPanel.setLayout(gr);
        menuPanel.setVisible(true);
        return menuPanel;
    }

    private static JPanel basketPanel(ArrayList<FastFoods> foodBasket, ArrayList<Bevarages> beverageBasket){
        basketPanel = new JPanel();
        basketPanel.setLayout(null);
        JTable basketTable;
        int size = foodBasket.size()+beverageBasket.size();

        String[][] arr = new String[size][2];
        int de = 0;
        if(!foodBasket.isEmpty()){
            for(int i = 0; i < foodBasket.size(); i++){
                String[] arg = new String[2];
                double foodPrice = foodBasket.get(i).getPrice();
                String foodName = foodBasket.get(i).getName();
                arg[0]=foodName;
                arg[1]=String.valueOf(foodPrice);
                arr[i] = arg;
                de++;
            }
        }
        if(!beverageBasket.isEmpty()){
            for(int i = 0; i < beverageBasket.size(); i++){
                String[] arg = new String[2];
                double foodPrice = beverageBasket.get(i).getPrice();
                String foodName = beverageBasket.get(i).getName();
                arg[0]=foodName;
                arg[1]=String.valueOf(foodPrice);
                arr[(de+i)] = arg;
            }
        }

        String[] columnNames = {"Yiyecek/İçecek Adı","Yiyecek/İçecek Fiyatı"};
        DefaultTableModel dtm = new DefaultTableModel(arr, columnNames);
        basketTable = new JTable(dtm);
        basketTable.setBounds(0,0,450,800);
        basketTable.setBackground(Color.yellow);
        basketTable.revalidate();
        basketTable.repaint();
        JScrollPane js = new JScrollPane(basketTable);
        js.setBounds(0,0,450,800);
        JButton onayla = new JButton();
        onayla.setText("Sipariş Ver");
        onayla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(foodBasket.isEmpty()&&beverageBasket.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Sepetinizde hiçbir ürün yok");
                }else{
                    try {
                        setOrder(loggedID,basket,basket2);
                        frame.revalidate();
                        frame.repaint();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Siparişiniz Onaylandı!");
                }
            }
        });
        JButton sil = new JButton();
        sil.setText("Seçilen ürünü sil");
        sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = basketTable.getSelectedRow();
                int column = basketTable.getSelectedColumn();
                String value = basketTable.getModel().getValueAt(row, column).toString();

                if(!basket.isEmpty()){
                    for(int i = 0; i < basket.size(); i++){
                        if(value.equals(basket.get(i).getName())){
                            basket.remove(i);
                            break;
                        }
                    }
                }

                if(!basket2.isEmpty()){
                    for(int i = 0; i < basket2.size(); i++){
                        if(value.equals(basket2.get(i).getName())){
                            basket2.remove(i);
                            break;
                        }
                    }
                }

                dtm.removeRow(row);
                JOptionPane.showMessageDialog(null, "Ürün silindi");
            }
        });
        sil.setBounds(0,800,150,50);
        basketPanel.add(sil);
        onayla.setBounds(300,800,150,50);
        basketPanel.add(onayla);
        js.setBackground(Color.red);
        basketPanel.add(js);
        basketPanel.setBounds(1450,0,450,1000);
        basketPanel.setVisible(true);
        frame.add(basketPanel);
        frame.revalidate();
        frame.repaint();
        return basketPanel;
    }

    private static JPanel deskPanel() throws SQLException, IOException {
        JPanel deskPanel = new JPanel();
        ArrayList<JRadioButton> radioButtons = new ArrayList<>();
        GridLayout gr = new GridLayout();
        gr.setColumns(5);
        gr.setRows(5);
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < 25; i++){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(100,200);
            BufferedImage myPicture = ImageIO.read(new File("Photos\\indir.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            JRadioButton radioButton = new JRadioButton(computers.get(i).getComputerName());
            picLabel.setBounds(0,0,200,150);
            radioButton.setBounds(0,150,200,50);
            panel.add(picLabel);
            panel.add(radioButton);
            radioButtons.add(radioButton);
            bg.add(radioButton);
            panel.setVisible(true);
            deskPanel.add(panel);
        }

        for(int i = 0; i < radioButtons.size(); i++){
            int a = i;
            radioButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        deskControl(a + 1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    frame.repaint();
                }
            });
        }
        deskPanel.setLayout(gr);
        deskPanel.setBackground(Color.white);
        deskPanel.setVisible(true);

        return deskPanel;
    }

    private static JPanel userSelectCompPanel() throws IOException, SQLException {
        Statement st = conn.createStatement();
        JPanel selectPanel = new JPanel();
        ArrayList<JRadioButton> radioButtons = new ArrayList<>();
        GridLayout gr = new GridLayout();
        gr.setColumns(5);
        gr.setRows(5);
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < 25; i++){
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setSize(100,200);
            BufferedImage myPicture = ImageIO.read(new File("Photos\\indir.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            JRadioButton radioButton = new JRadioButton(computers.get(i).getComputerName());
            picLabel.setBounds(0,0,200,150);
            radioButton.setBounds(0,150,200,50);
            panel.add(radioButton);
            panel.add(picLabel);
            radioButtons.add(radioButton);
            bg.add(radioButton);
            panel.setVisible(true);
            selectPanel.add(panel);
        }

        for(int i = 0; i < radioButtons.size(); i++){
            int a = i;
            radioButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cd = "update computers set isEmpty = 1 where computerID="+(a+1);
                    String up = "update computers set startTime = NOW() where computerID="+(a+1);
                    String time = "select startTime from computers where computerID="+(a+1);
                    ResultSet res = null;
                    try {
                        res = st.executeQuery(time);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        res.next();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    String start = null;
                    try {
                        start = res.getString(1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    computers.get(a).setStartTime(start);
                    if(computers.get(a).isEmpty()){
                        try {
                            String aa = "update computers set payment = 2 where computerID="+(a+1);
                            st.executeUpdate(aa);
                            st.executeUpdate(cd);
                            st.executeUpdate(up);
                            computers.get(a).setEmpty(false);
                            computers.get(a).setPayment(2);
                            userDeskFrame((a+1));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Bilgisayar şu an kullanımda");
                    }
                    frame.repaint();
                }
            });
        }
        selectPanel.setLayout(gr);
        selectPanel.setBackground(Color.blue);
        selectPanel.setBounds(300,0,1500,1000);
        selectPanel.setVisible(true);
        frame.add(selectPanel);
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);

        return selectPanel;
    }

    private static JPanel orderPanel() throws SQLException {
        Statement st = conn.createStatement();
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(null);
        JTable orderTable;
        String[][] arr = new String[orders.size()][5];
        for(int i = 0; i < orders.size(); i++){
            String[] arg = new String[5];
            int personID = orders.get(i).getOrderedPersonID();
            int foodID = orders.get(i).getOrderedFoodID();
            int beverageID = orders.get(i).getOrderedBeverageID();
            String orderTime = orders.get(i).getOrderTime();
            double payment = orders.get(i).getPayment();
            String cd1 = "Select userName from users where userID ="+personID;
            ResultSet res1 = st.executeQuery(cd1);
            res1.next();
            arg[0]= res1.getString(1);
            if(foodID!=0){
                String cd2 = "Select foodName from Fastfoods where foodID ="+foodID;
                ResultSet res2 = st.executeQuery(cd2);
                res2.next();
                arg[1]=res2.getString(1);
            }else{
                arg[1]="Yiyecek yok";
            }
            if(beverageID!=0){
                String cd3 = "Select beverageName from Beverages where beverageID ="+beverageID;
                ResultSet res3 = st.executeQuery(cd3);
                res3.next();
                arg[2]=res3.getString(1);
            }else{
                arg[2]="İçecek yok";
            }
            arg[3]=orderTime;
            arg[4]=String.valueOf(payment);
            arr[i] = arg;
        }
        String[] columnNames = {"Client Name","Food","Beverage","Time","Payment"};
        orderTable = new JTable(arr,columnNames);
        orderTable.setBounds(0,0,1000,1000);
        orderTable.setBackground(Color.yellow);
        JScrollPane js = new JScrollPane(orderTable);
        js.setBounds(0,0,1000,1000);
        orderPanel.add(js);
        orderPanel.setVisible(true);
        orderPanel.setBounds(300,10,1500,1000);
        frame.add(orderPanel);
        frame.revalidate();
        frame.repaint();
        return orderPanel;
    }

    private static void userDeskFrame(int masa) throws SQLException {
        Statement st = conn.createStatement();
        JFrame userFrame = new JFrame("Masa-"+masa);
        userFrame.setSize(200,200);
        JLabel startTime = new JLabel();
        computers = getComputers();
        String startTimeString = computers.get(masa-1).getStartTime().substring(10);
        startTime.setText("Başlangıç Zamanı:  "+startTimeString);
        startTime.setBounds(0,20,200,20);
        double ucret = 2;
        final double[] diff = new double[1];
        Button bitir = new Button();
        bitir.setLabel("Masayı Kapat");
        bitir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cd = "update computers set finishTime = NOW() where computerID="+masa;
                String fn = "select timestampdiff(minute,startTime,finishTime) from computers where computerID ="+masa;
                String nn = "update computers set finishTime = '0000-00-00 00:00:00' where computerID="+masa;
                String nn2 = "update computers set startTime = '0000-00-00 00:00:00' where computerID="+masa;
                String nn3 = "update computers set isEmpty = 0 where computerID="+masa;
                try {
                    st.executeUpdate(cd);
                    ResultSet res = st.executeQuery(fn);
                    res.next();
                    diff[0] =(Double.parseDouble(res.getString(1))/60);
                    st.executeUpdate(nn);
                    st.executeUpdate(nn2);
                    st.executeUpdate(nn3);
                    JOptionPane.showMessageDialog(null,"Masanız Kapatıldı!");
                    computers.get(masa-1).setPayment(0);
                    userFrame.dispose();
                    computers = getComputers();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        bitir.setBounds(10,100,160,30);

        JLabel ucretLabel = new JLabel();
        ucretLabel.setText("Ücret: " + (ucret+diff[0])+"₺");
        ucretLabel.setBounds(20,30,160,20);

        userFrame.add(startTime);
        userFrame.add(bitir);
        userFrame.add(ucretLabel);

        userFrame.setVisible(true);
    }

    private static void deskControl(int masa) throws SQLException {
        Statement st = conn.createStatement();
        computers = getComputers();
        JPanel deskControl = new JPanel();
        deskControl.setLayout(null);
        JLabel lb = new JLabel();
        lb.setText("MASA-"+masa);
        JLabel begin = new JLabel();
        begin.setText("Başlangıç Zamanı:");
        JLabel begined = new JLabel();
        if(computers.get(masa-1).isEmpty()){
            JOptionPane.showMessageDialog(null, "Masa şu an kullanımda değil!");
        }else{
            begined.setText(computers.get(masa-1).getStartTime().substring(10));
            JLabel pay = new JLabel();
            pay.setText("Toplam Ücret");
            JLabel payment = new JLabel();
            payment.setText(String.valueOf(computers.get(masa-1).getPayment()));
            lb.setBounds(75,0,150,25);
            begin.setBounds(0,60,150,25);
            begined.setBounds(160,60,150,25);
            pay.setBounds(0,90,150,25);
            payment.setBounds(160,90,150,25);
            deskControl.add(lb);
            deskControl.add(begin);
            deskControl.add(begined);
            deskControl.add(pay);
            deskControl.add(payment);
            deskControl.setVisible(true);
            Button onayla = new Button();
            onayla.setLabel("Tamamlandı");
            onayla.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deskControl.setVisible(false);
                }
            });
            Button bitir = new Button();
            bitir.setLabel("Masayı Kapat");
            bitir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cd = "update computers set finishTime = NOW() where computerID="+masa;
                    String fn = "select timestampdiff(minute,startTime,finishTime) from computers where computerID ="+masa;
                    String nn = "update computers set finishTime = '0000-00-00 00:00:00' where computerID="+masa;
                    String nn2 = "update computers set startTime = '0000-00-00 00:00:00' where computerID="+masa;
                    String nn3 = "update computers set isEmpty = 0 where computerID="+masa;
                    try {
                        st.executeUpdate(cd);
                        st.executeUpdate(nn);
                        st.executeUpdate(nn2);
                        st.executeUpdate(nn3);
                        JOptionPane.showMessageDialog(null,"Masanız Kapatıldı!");
                        computers = getComputers();
                        deskControl.setVisible(false);
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            });
            bitir.setBounds(120,800,100,50);
            onayla.setBounds(0,800,100,50);
            deskControl.add(bitir);
            deskControl.add(onayla);
            deskControl.setBounds(1600,10,500,1000);
        }

        frame.add(deskControl);
        frame.revalidate();
    }

    private static JPanel usersPanel() throws SQLException {
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(null);
        JTable userTable;
        String[][] arr = new String[users.size()][4];
        for(int i = 0; i < users.size(); i++){
            String[] arg = new String[4];
            int personID = users.get(i).getID();
            int userTypes = users.get(i).getUserType();
            String userType;
            if(userTypes==1){
                userType = "Admin";
            }else if(userTypes==2){
                userType = "Aşçı";
            }else{
                userType = "Müşteri";
            }
            String userName = users.get(i).getUserName();
            String userPass = users.get(i).getUserPassword();
            arg[0]=String.valueOf(personID);
            arg[1]=userType;
            arg[2]=userName;
            arg[3]=userPass;
            arr[i] = arg;
        }
        String[] columnNames = {"Kullanıcı ID","Kullanıcı Tipi","Kullanıcı Adı","Kullanıcı Şifresi"};
        userTable = new JTable(arr,columnNames);
        userTable.setBounds(0,0,1000,1000);
        userTable.setBackground(Color.yellow);
        JScrollPane js = new JScrollPane(userTable);
        js.setBounds(0,0,1000,1000);
        usersPanel.add(js);
        usersPanel.setVisible(true);
        return usersPanel;
    }

    private static JPanel stockPanel() throws SQLException {
        beverages = getBeverages();
        fastFoods = getFastFoods();
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(null);
        JTable stockTable;
        String[][] arr = new String[fastFoods.size()+ beverages.size()][2];
        int de = 0;
        for(int i = 0; i < fastFoods.size(); i++){
            String[] arg = new String[2];
            String name = fastFoods.get(i).getName();
            int stock = fastFoods.get(i).getStock();
            arg[0]=name;
            arg[1]=String.valueOf(stock);
            arr[i] = arg;
            de++;
        }
        for(int i = 0; i < beverages.size(); i++){
            String[] arg = new String[2];
            String name = beverages.get(i).getName();
            int stock = beverages.get(i).getStock();
            arg[0]=name;
            arg[1]=String.valueOf(stock);
            arr[(de+i)] = arg;
        }
        String[] columnNames = {"Yiyecek/İçecek Adı","Yiyecek/İçecek Stoğu"};
        DefaultTableModel dtm = new DefaultTableModel(arr, columnNames);
        stockTable = new JTable(dtm);
        stockTable.setBackground(Color.yellow);
        JScrollPane js = new JScrollPane(stockTable);
        stockPanel.setBounds(300,10,1000,1000);
        js.setBounds(0,0,1000,1000);
        stockPanel.add(js);
        frame.add(stockPanel);
        frame.revalidate();
        frame.repaint();
        return stockPanel;
    }

    private static JPanel userOrderPanel(int userID) throws SQLException {
        Statement st = conn.createStatement();
        JPanel userOrderPanel = new JPanel();
        userOrderPanel.setLayout(null);
        JTable orderTable;
        String counter = "select count(orderID) from orders where orderedPersonID ="+userID;
        ResultSet rs = st.executeQuery(counter);
        rs.next();
        int c = 0;
        double totalPayment = 0;
        int count = Integer.parseInt(rs.getString(1));
        String[][] arr = new String[count+1][6];
        for(int i = 0; i < orders.size(); i++){
            String[] arg = new String[6];
            if(userID==orders.get(i).getOrderedPersonID()){
                int foodID = orders.get(i).getOrderedFoodID();
                int beverageID = orders.get(i).getOrderedBeverageID();
                String orderTime = orders.get(i).getOrderTime();
                double payment = orders.get(i).getPayment();
                boolean paid = false;
                String cd1 = "Select userName from users where userID ="+userID;
                ResultSet res1 = st.executeQuery(cd1);
                res1.next();
                arg[0]= res1.getString(1);
                if(foodID!=0){
                    String cd2 = "Select foodName from Fastfoods where foodID ="+foodID;
                    ResultSet res2 = st.executeQuery(cd2);
                    res2.next();
                    arg[1]=res2.getString(1);
                }else{
                    arg[1]="Yiyecek yok";
                }
                if(beverageID!=0){
                    String cd3 = "Select beverageName from Beverages where beverageID ="+beverageID;
                    ResultSet res3 = st.executeQuery(cd3);
                    res3.next();
                    arg[2]=res3.getString(1);
                }else{
                    arg[2]="İçecek yok";
                }
                arg[3]=orderTime;
                totalPayment += payment;
                arg[4]=String.valueOf(payment);
                String cd4 = "Select paid from orders where orderID="+orders.get(i).getOrderID();
                ResultSet res4 = st.executeQuery(cd4);
                res4.next();
                String pay = String.valueOf(res4.getString(1));
                if(pay.equals("1")){
                    paid = true;
                    totalPayment = 0;
                }
                arg[5] = String.valueOf(paid);
                arr[c] = arg;
                c++;
            }
        }
        arr[c]= new String[]{"","", "", "", "", "Total Payment: "+totalPayment};
        String[] columnNames = {"Client Name","Food","Beverage","Time","Payment","Paid"};
        orderTable = new JTable(arr,columnNames);
        orderTable.setBounds(0,0,1000,1000);
        orderTable.setBackground(Color.yellow);
        JScrollPane js = new JScrollPane(orderTable);
        js.setBounds(0,0,1000,1000);
        Button payButton = new Button();
        payButton.setLabel("Tüm Borçları Öde");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sr = "update orders set paid = true where orderedPersonID="+userID;
                try {
                    st.executeUpdate(sr);
                    frame.remove(orderPanel);
                    orderPanel = userOrderPanel(loggedID);
                    frame.add(orderPanel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        payButton.setBounds(1050,0,200,100);
        userOrderPanel.add(payButton);
        userOrderPanel.add(js);
        userOrderPanel.setVisible(true);
        userOrderPanel.setBounds(300,10,1500,1000);
        frame.add(userOrderPanel);
        frame.revalidate();
        frame.repaint();
        return userOrderPanel;

    }

    public static ArrayList<User> getUsers() throws SQLException {
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

    public static ArrayList<FastFoods> getFastFoods() throws SQLException {
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

    public static ArrayList<Bevarages> getBeverages() throws SQLException {
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

    public static ArrayList<Computers> getComputers() throws SQLException {
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

    public static ArrayList<Order> getOrders() throws SQLException {
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

    private static boolean checkUser(String userName, String password) throws SQLException {
        boolean check = false;
        Statement st = conn.createStatement();
        String usr = "Select userName from users where userName='"+userName+"'";
        String pass = "Select userPassword from users where userPassword='"+password+"'";
        ResultSet res1 = st.executeQuery(usr);
        res1.next();
        String usrnm =res1.getString(1);
        ResultSet res2 = st.executeQuery(pass);
        res2.next();
        String passwrd = res2.getString(1);
        if(usrnm.equals(userName)&&passwrd.equals(password)){
            check = true;
        }else{
            JOptionPane.showMessageDialog(null, "Yanlış kullanıcı adı ya da şife!");
        }
        return check;
    }

    private static ArrayList<ImageIcon> getFoodImages(ArrayList<FastFoods> fastFoods) throws IOException {

        for(int i = 0; i < fastFoods.size(); i++){
            String path = fastFoods.get(i).getImagePath();
            path = path.replace("D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\","Photos\\");
            path = path.replace("\\", "\\\\");
            ImageIcon myPicture = getImage(path);
            foodImages.add(myPicture);
        }
        return foodImages;
    }

    private static ArrayList<ImageIcon> getBeveragesImages(ArrayList<Bevarages> beverages) throws IOException {

        for(int i = 0; i < beverages.size(); i++){
            String path = beverages.get(i).getImagePath();
            path.replace('/', '\\');
            ImageIcon myPicture = getImage(path);
            beveragesImages.add(myPicture);
        }
        return beveragesImages;
    }

    private static ImageIcon getImage(String path) throws IOException {
        ImageIcon myPicture = new ImageIcon(path);
        return myPicture;
    }

    private static void setOrder(int id, ArrayList<FastFoods> orderFood, ArrayList<Bevarages> orderBevarage) throws SQLException {
        Statement st = conn.createStatement();
        for(int i = 0; i < orderFood.size(); i++){
            String sql = "INSERT INTO orders (orderedPersonID,orderedFoodID,orderPayment,paid) " +
                    "VALUES ("+id+","+orderFood.get(i).getID()+","+orderFood.get(i).getPrice()+", false)";
            st.executeUpdate(sql);
            String dec = "update fastfoods set foodStock = foodStock-1 where foodID="+orderFood.get(i).getID();
            st.executeUpdate(dec);
        }
        for(int i = 0; i < orderBevarage.size(); i++){
            String sql = "INSERT INTO orders (orderedPersonID,orderedBeverageID,orderPayment) " +
                    "VALUES ("+id+","+orderBevarage.get(i).getID()+","+orderBevarage.get(i).getPrice()+")";
            st.executeUpdate(sql);
            String dec = "update beverages set beverageStock = beverageStock-1 where beverageID="+orderBevarage.get(i).getID();
            st.executeUpdate(dec);
        }
        basket.clear();
        basket2.clear();
    }

    private static void addUser(String userName, String password, int userType) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "INSERT INTO users (userType, userName, userPassword) " +
                "VALUES ("+userType+","+"'"+userName+"','"+password+"')";
        st.executeUpdate(sql);
    }

    private static void setActiveUser(String usr, int id) throws SQLException {
        loggedID = getActiveUserID(usr);
        loggedName = usr;
        loggedType = id;
    }

    private static int getActiveUserType(String usr) throws SQLException {
        int id;
        Statement st = conn.createStatement();
        String qu = "select userType from users where userName='"+usr+"'";
        ResultSet rs = st.executeQuery(qu);
        rs.next();
        id = Integer.parseInt(rs.getString(1));
        return id;
    }

    private static int getActiveUserID(String usr) throws SQLException {
        int id;
        Statement st = conn.createStatement();
        String qu = "select userID from users where userName='"+usr+"'";
        ResultSet rs = st.executeQuery(qu);
        rs.next();
        id = Integer.parseInt(rs.getString(1));
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
        try {
            users = getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
