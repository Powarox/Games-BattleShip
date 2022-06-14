package view.graphics.newComponent;

import javax.swing.*;
import java.awt.*;

public class ReadyButton extends JButton {

    private CaseButton button;

    public ReadyButton(){
        super("Start");
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setActionCommand("start");
        this.setFocusPainted(false);
        this.setBorder(null);
    }

    public void setButton(CaseButton btt){
        this.button=btt;
    }

    public CaseButton getButton(){
        return this.button;
    }

    public void inited(){
        this.setActionCommand("hit");
        this.setText("Hit");
    }
}
