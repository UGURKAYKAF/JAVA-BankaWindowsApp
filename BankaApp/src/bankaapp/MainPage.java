/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ART3MIS
 */
public class MainPage {
    
    MainPage(){
        FrameSettings frame = new FrameSettings("Ana Sayfa",400,300);
        frame.setLayout(null);
        //frame.setBackground(Color.CYAN);

        JButton btn_login = new JButton("Giriş Yapınız");
        btn_login.setBounds(50, 75,125,75);
        JButton btn_singup = new JButton("Kayıt Olunuz");
        btn_singup.setBounds(200,75,125,75);

        frame.add(btn_login);
        frame.add(btn_singup);

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();
            }
        });

        btn_singup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SingUp();
            }
        });



        frame.setVisible(true);
    }
}
