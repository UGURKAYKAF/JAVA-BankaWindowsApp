/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author ART3MIS
 */
public class Login {
    JTextField email;
    JPasswordField password ;
    JButton btn_send;
    Login(){
        DataBaseConnection db = new DataBaseConnection();
        FrameSettings frame = new FrameSettings("GİRİŞ YAP",300,300);
        frame.setLayout(null);

        JLabel lbl_email = new JLabel("E-Mail");
        JLabel lbl_password = new JLabel("Şifre");
        email = new JTextField();
        password = new JPasswordField();
        btn_send = new JButton("Giriş Yap");

        lbl_email.setBounds(60, 50, 50, 50);
        lbl_password.setBounds(60, 100, 50, 50);
        email.setBounds(100,60,120,30);
        password.setBounds(100,110,120,30);
        btn_send.setBounds(100,150,120,30);

        frame.add(lbl_email);
        frame.add(lbl_password);
        frame.add(email);
        frame.add(password);
        frame.add(btn_send);

        btn_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn_send){
                    try{
                        String sifre = String.valueOf(password.getPassword());
                        boolean baglan = db.login(email.getText(),sifre);
                        boolean baglandeneme = baglan ? true : false; 
                        if(baglandeneme){                            
                            new AccountContent(); 
                            frame.setVisible(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "E-Mail ya da Şifreniz hatalı");
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
