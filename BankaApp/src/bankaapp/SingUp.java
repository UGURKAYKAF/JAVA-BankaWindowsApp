/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

/**
 *
 * @author ART3MIS
 */
public class SingUp {    
    JTextField tc ;
    JTextField name ;
    JTextField surname ;
    JTextField email;
    JPasswordField password ;
    JButton btn_send;

    SingUp(){
        DataBaseConnection db = new DataBaseConnection();
        FrameSettings frame = new FrameSettings("KAYIT OL",300,400);
        frame.setLayout(null);

        JLabel lbl_tc = new JLabel("TC");
        JLabel lbl_name = new JLabel("Ad");
        JLabel lbl_surname = new JLabel("Soyad");
        JLabel lbl_email = new JLabel("E-Mail");
        JLabel lbl_password = new JLabel("Şifre");

        tc = new JTextField();
        name = new JTextField();
        surname = new JTextField();
        email = new JTextField();
        password = new JPasswordField();

        btn_send = new JButton("Kayıt Ol");

        lbl_name.setBounds(60, 50, 50, 50);
        lbl_surname.setBounds(60, 100, 50, 50);
        lbl_tc.setBounds(60, 150, 50, 50);
        lbl_email.setBounds(60, 200, 50, 50);
        lbl_password.setBounds(60, 250, 50, 50);

        name.setBounds(100,60,120,30);
        surname.setBounds(100,110,120,30);
        tc.setBounds(100,160,120,30);
        email.setBounds(100,210,120,30);
        password.setBounds(100,260,120,30);

        btn_send.setBounds(100,300,120,30);

        frame.add(lbl_name);
        frame.add(lbl_surname);
        frame.add(lbl_tc);
        frame.add(lbl_email);
        frame.add(lbl_password);

        frame.add(name);
        frame.add(surname);
        frame.add(tc);
        frame.add(email);
        frame.add(password);

        frame.add(btn_send);
        
        
        tc.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        name.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        surname.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {                
                char c = e.getKeyChar();
                if (Character.isDigit(c)){
                    e.consume();
                }
            }
        });

        btn_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn_send){
                    try{
                        String sifre = String.valueOf(password.getPassword());
                        if(tc.getText().length() == 0 || name.getText().length() == 0 || surname.getText().length() == 0 || email.getText().length() == 0 || sifre.length() == 0){
                            JOptionPane.showMessageDialog(null, "Bütün boşlukları doldurunuz.");
                        }
                        else if(tc.getText().length() != 11){
                            JOptionPane.showMessageDialog(null, "TC 11 haneli olmalıdır");
                        }
                        else{
                           
                            boolean baglan = db.singup(tc.getText(),name.getText(),surname.getText(),email.getText(),sifre);
                            if(baglan){
                               new Login();
                                System.out.println(email.getText());
                                frame.setVisible(false);   
                            }

                            

                        }   
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }       
            }
        });


        frame.setVisible(true);
    }
    
    
}
