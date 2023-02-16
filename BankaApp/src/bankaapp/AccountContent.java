/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

/**
 *
 * @author ART3MIS
 */
public class AccountContent  {
    
    JPanel pnl_myAccount;
    JPanel pnl_deposit;
    JPanel pnl_withdraw;
    JPanel pnl_credit;
    JPanel pnl_eft;
    
    AccountContent() throws NullPointerException{
        DataBaseConnection db = new DataBaseConnection();
        User user = new User();
        
        FrameSettings frame = new FrameSettings("Ana Sayfa",600,600);
        frame.setLayout(new BorderLayout());

        //sidebar
        JPanel pnl_sidebar = new JPanel();
        pnl_sidebar.setLayout(null);
        pnl_sidebar.setPreferredSize(new Dimension(165,0));
        pnl_sidebar.setVisible(true);
        pnl_sidebar.setBackground(Color.pink);
        
        
        JLabel lbl_ad = new JLabel(""+User.userName);
        JLabel lbl_soyad = new JLabel(""+User.userLastName);
        JLabel lbl_tc = new JLabel(""+User.userTC);
        JButton btn_myAccount = new JButton("Hesaplarım");
        JButton btn_deposit = new JButton("Para Yatır");
        JButton btn_withdraw = new JButton("Para Çek");
        JButton btn_credit = new JButton("Kredi Çek");
        JButton btn_eft = new JButton("EFT / Havale");
        JButton btn_logout = new JButton("Çıkış Yap");

        lbl_ad.setBounds(35,20,101,30); 
        lbl_soyad.setBounds(35,40,101,30); 
        lbl_tc.setBounds(35,60,101,30); 
        btn_myAccount.setBounds(35,140,101,30);
        btn_deposit.setBounds(35,180,101,30);
        btn_withdraw.setBounds(35,220,101,30);
        btn_credit.setBounds(35,260,101,30);
        btn_eft.setBounds(35,300,101,30);
        btn_logout.setBounds(35,475,100,30);

        pnl_sidebar.add(lbl_ad);
        pnl_sidebar.add(lbl_soyad);
        pnl_sidebar.add(lbl_tc);
        pnl_sidebar.add(btn_myAccount);
        pnl_sidebar.add(btn_deposit);
        pnl_sidebar.add(btn_withdraw);
        pnl_sidebar.add(btn_credit);
        pnl_sidebar.add(btn_eft);
        pnl_sidebar.add(btn_logout);

        frame.add(pnl_sidebar,BorderLayout.WEST);
        
        /*****************************************************************************************************************/
        //main
        pnl_myAccount = new JPanel();
        pnl_myAccount.setLayout(null);
        //pnl_myAccount.setBackground(Color.blue);

        JLabel lbl_accountTypeTRY = new JLabel("Hesap türü: TRY");
        JLabel lbl_accountBalanceTRY = new JLabel("Hesabınızın Bakiyeniz: "+user.userAccountBalanceTRY);
        JLabel lbl_accountTypeUSD = new JLabel("Hesap türü: USD");
        JLabel lbl_accountBalanceUSD = new JLabel("Hesabınıznın Bakiyeniz: "+user.userAccountBalanceUSD);
        JLabel lbl_accountTypeEUR = new JLabel("Hesap türü: EUR");
        JLabel lbl_accountBalanceEUR = new JLabel("Hesabınızın Bakiyeniz: "+user.userAccountBalanceEUR);

        lbl_accountTypeTRY.setBounds(30,100,200,30);
        lbl_accountTypeUSD.setBounds(30,150,200,30);
        lbl_accountTypeEUR.setBounds(30,200,200,30);
        lbl_accountBalanceTRY.setBounds(150,100,200,30);
        lbl_accountBalanceUSD.setBounds(150,150,200,30);
        lbl_accountBalanceEUR.setBounds(150,200,200,30);

        pnl_myAccount.add(lbl_accountTypeTRY);
        pnl_myAccount.add(lbl_accountBalanceTRY);
        pnl_myAccount.add(lbl_accountTypeUSD);
        pnl_myAccount.add(lbl_accountBalanceUSD);
        pnl_myAccount.add(lbl_accountTypeEUR);
        pnl_myAccount.add(lbl_accountBalanceEUR);

        pnl_myAccount.setVisible(true);
        frame.add(pnl_myAccount);

        /*****************************************************************************************************************/
        //para yatırma panneli
        pnl_deposit = new JPanel();
        pnl_deposit.setLayout(null);
       // pnl_deposit.setBackground(Color.red);
        
        JLabel lbl_yatir = new JLabel("Hesaba Yatırmak İstediğiniz Miktar : ");
        JTextField txtField_yatir = new JTextField();
        JButton btn_yatir = new JButton("Yatır");
        
        lbl_yatir.setBounds(100,100,500,30);
        txtField_yatir.setBounds(100,150,200,30);
        btn_yatir.setBounds(100, 200, 200, 30);
        
        pnl_deposit.add(lbl_yatir);
        pnl_deposit.add(txtField_yatir);
        pnl_deposit.add(btn_yatir);
        
        txtField_yatir.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        
        btn_yatir.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn_yatir){
                   
                    try{                  
                          double yatir = Double.parseDouble(txtField_yatir.getText());
                        boolean deposit = db.deposit(User.userEmail,User.userPassword,yatir);   
                        txtField_yatir.setText("");
                        if(deposit == false){
                            System.out.println("Bağlanti basarisiz , Bagalaniyorum");
                            JOptionPane.showMessageDialog(null, "İşlemde Hata Oluştu");
                        }
                        if(deposit){ 
                            JOptionPane.showMessageDialog(null, "İşlem Başarılı");
                            System.out.println("user pass:  "+User.userAccountBalanceTRY);
                            User.userAccountBalanceTRY+=yatir ;
                            System.out.println("de: "+User.userAccountBalanceTRY);
                        }
                    }
                    catch(SQLException se){
                        se.printStackTrace();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
         
        /*****************************************************************************************************************/
        //para çekme panneli
        pnl_withdraw = new JPanel();
        pnl_withdraw.setLayout(null);
        //pnl_withdraw.setBackground(Color.cyan);
        
        JLabel lbl_cek = new JLabel("Hesab'ınızdan çekmek İstediğiniz Miktar : ");
        JTextField txtField_cek = new JTextField();
        JButton btn_cek = new JButton("Çek");
        
        lbl_cek.setBounds(100,100,500,30);
        txtField_cek.setBounds(100,150,200,30);
        btn_cek.setBounds(100, 200, 200, 30);
        
        pnl_withdraw.add(lbl_cek);
        pnl_withdraw.add(txtField_cek);
        pnl_withdraw.add(btn_cek);
        
        txtField_cek.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        
        btn_cek.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn_cek){
                    try{                  
                        double cek = Double.parseDouble(txtField_cek.getText());
                       
                        boolean deneme1 = (User.userAccountBalanceTRY<cek) ? true : false ;
                        if(deneme1){
                            JOptionPane.showMessageDialog(null, "Bakiyenizden fazla para çekemezsiniz");
                        }
                        else{
                          boolean withdraw = db.withdraw(User.userEmail,User.userPassword,cek);   
                            txtField_cek.setText("");
                        if(withdraw == false){
                            System.out.println("Bağlanti basarisiz , Bagalaniyorum");
                            JOptionPane.showMessageDialog(null, "İşlemde Hata Oluştu");
                        }
                        if(withdraw){ 
                            JOptionPane.showMessageDialog(null, "İşlem Başarılı");
                            System.out.println("user pass:  "+User.userAccountBalanceTRY);
                            User.userAccountBalanceTRY-=cek ;
                            System.out.println("de: "+User.userAccountBalanceTRY);
                        }   
                        }
                    }
                    catch(SQLException se){
                        se.printStackTrace();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        /*****************************************************************************************************************/
        //kredi paneli
        pnl_credit = new JPanel();
        pnl_credit.setLayout(null);
        //pnl_credit.setBackground(Color.GRAY);
        
        JLabel lbl_creditAmount = new JLabel("Kredi Miktarı : ");
        JTextField txtField_creditAmount = new JTextField();
        JLabel lbl_interest = new JLabel("Taksit Sayısı : ");
        JTextField txtField_interest = new JTextField();
        JButton btn_calculateCredit = new JButton("Kredi Hesapla");
        JLabel lbl_sonuc = new JLabel("Sonuç:");
        JLabel lbl_cikti = new JLabel();
        
        
        lbl_creditAmount.setBounds(30,100,200,30);
        txtField_creditAmount.setBounds(170,100,200,30);
        lbl_interest.setBounds(30, 150,200, 30);
        txtField_interest.setBounds(170, 150, 200, 30);
        btn_calculateCredit.setBounds(170,250,200,30);
        lbl_sonuc.setBounds(30,200,200,30);
        lbl_cikti.setBounds(170,200,200,30);

        pnl_credit.add(lbl_creditAmount);
        pnl_credit.add(txtField_creditAmount);
        pnl_credit.add(lbl_interest);
        pnl_credit.add(txtField_interest);
        pnl_credit.add(btn_calculateCredit);
        pnl_credit.add(lbl_sonuc);
        pnl_credit.add(lbl_cikti);

        txtField_creditAmount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        txtField_interest.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        
        btn_calculateCredit.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                double oran = Integer.parseInt(txtField_interest.getText());
                double tutar = Integer.parseInt(txtField_creditAmount.getText());
                double faiztutari = oran *  tutar;
                double toplamtutar = tutar +faiztutari;
                lbl_cikti.setText(""+toplamtutar);
                if(e.getSource()==btn_calculateCredit){
                    try{                  
                        double yatir = Double.parseDouble(lbl_cikti.getText());
                        boolean credit = db.deposit(User.userEmail,User.userPassword,yatir);   
                        txtField_interest.setText("");
                        txtField_creditAmount.setText("");
                        boolean deneme2 = (credit == false) ? true: false ;
                        if(deneme2){
                            System.out.println("Bağlanti basarisiz , Bagalaniyorum");
                            JOptionPane.showMessageDialog(null, "İşlemde Hata Oluştu");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "İşlem Başarılı");
                            System.out.println("user pass:  "+User.userAccountBalanceTRY);
                            User.userAccountBalanceTRY+=yatir ;
                            System.out.println("de: "+User.userAccountBalanceTRY);  
                        }
                            
                        /*if(credit){ 
                            
                        }*/
                    }
                    catch(SQLException se){
                        se.printStackTrace();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        /*****************************************************************************************************************/
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage();
            }
        });

        /*****************************************************************************************************************/
        //EFT
        pnl_eft = new JPanel();
        pnl_eft.setLayout(null);
        //pnl_credit.setBackground(Color.red);
        
        JLabel lbl_eftTC = new JLabel("Para göndermek istediğiniz hesab'a ait kişinin TC'si: ");
        JTextField txtField_eftTC = new JTextField();
        JLabel lbl_eftAmount = new JLabel("Para göndermek istediğiniz Miktar: ");        
        JTextField txtField_eftAmount = new JTextField();
        JButton btn_eftSend = new JButton("Gönder");
        
        lbl_eftTC.setBounds(20,40,300,30);
        txtField_eftTC.setBounds(20,80,300,30);
        lbl_eftAmount.setBounds(20,120,300,30);
        txtField_eftAmount.setBounds(20,160,300,30);
        btn_eftSend.setBounds(20,200,300,30);
        
        pnl_eft.add(lbl_eftTC);
        pnl_eft.add(txtField_eftTC);
        pnl_eft.add(lbl_eftAmount);
        pnl_eft.add(txtField_eftAmount);
        pnl_eft.add(btn_eftSend);
        
        txtField_eftTC.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        txtField_eftAmount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        
        btn_eftSend.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn_eftSend){
                    try{                  
                        double yatir = Double.parseDouble(txtField_eftAmount.getText());
                        boolean deneme3 = (User.userAccountBalanceTRY<yatir) ? true : false;
                        if(deneme3){
                            JOptionPane.showMessageDialog(null, "Bakiyenden fazlasını gönderemezsin");
                        }
                        else{
                            String tc = txtField_eftTC.getText();
                            boolean eft = db.eft(User.userEmail,User.userPassword, yatir,tc);   
                            txtField_eftAmount.setText("");
                            if(eft == false){
                                System.out.println("Bağlanti basarisiz , Bagalaniyorum");
                                JOptionPane.showMessageDialog(null, "İşlemde Hata Oluştu");
                            }
                            if(eft){ 
                                JOptionPane.showMessageDialog(null, "İşlem Başarılı");
                                System.out.println("user pass:  "+User.userAccountBalanceTRY);
                                User.userAccountBalanceTRY+=yatir ;
                                System.out.println("de: "+User.userAccountBalanceTRY);
                            } 
                        }
                    }
                    catch(SQLException se){
                        se.printStackTrace();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        /*****************************************************************************************************************/
        sayfadegistir(pnl_myAccount,btn_myAccount,frame);
        sayfadegistir(pnl_deposit,btn_deposit,frame);
        sayfadegistir(pnl_withdraw,btn_withdraw,frame);
        sayfadegistir(pnl_credit,btn_credit,frame);
        sayfadegistir(pnl_eft,btn_eft,frame);
        
        frame.setVisible(true);
    }
    private  void sakla(){
        pnl_myAccount.setVisible(false);
        pnl_deposit.setVisible(false);
        pnl_withdraw.setVisible(false);
        pnl_credit.setVisible(false);
        pnl_eft.setVisible(false);
    }
    public void sayfadegistir(JPanel panel,JButton btn, JFrame frame){

        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sakla();
                frame.add(panel,BorderLayout.CENTER);
                panel.setVisible(true);
                frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

}
