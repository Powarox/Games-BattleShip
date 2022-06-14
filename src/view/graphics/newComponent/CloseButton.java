package view.graphics.newComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseButton  {
    private JButton closeButton;

    public CloseButton(ActionListener event){
        this.closeButton = new JButton("X");
        closeButton.setBorder(null);//to take off intern border
        closeButton.setBorderPainted(false);//Take off click design bordr
        closeButton.setFocusPainted(false);//Take off click design change color
        closeButton.setContentAreaFilled(false);
        closeButton.setActionCommand("EXIT");
        closeButton.addActionListener(event);
        closeButton.setBackground(Color.WHITE);
        closeButton.setOpaque(true);
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e){
                closeButton.setBackground(Color.WHITE);
            }
        });
    }
    public JButton getCloseButton(int width){
        closeButton.setBounds(width-42,0,40,25);//Definir son emplacement et taille
        return this.closeButton;
    }
}
