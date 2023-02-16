/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaapp;
import javax.swing.*;
/**
 *
 * @author ART3MIS
 */
public class FrameSettings extends JFrame {
     FrameSettings(String title,int x, int y){
        this.setTitle(title);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
