package view.graphics.newComponent;

import model.Case;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CaseButton extends JButton {

    private Case obj;
    private boolean choosed;//Pour le graphics
    private int width;

    public CaseButton(Case obj,int width){
        super();
        this.width = width;
        this.choosed = false;
        this.obj = obj;
        Color c =  new Color(249, 249, 249);
        this.setBorder(new LineBorder(c,1,true));//Pour enlever les bordures internes au boutton
        this.setBorderPainted(true);//Enlever L'affichage moche lorsque MouseEntered
        this.setFocusPainted(false);//De même
        this.setContentAreaFilled(false);
        this.setActionCommand("EXIT");
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
    }

    public boolean isChoosed(){
        return this.choosed;
    }

    /**
     * Change the background if the user click on a case.
     * @param bool
     */
    public void setChoosed(boolean bool){

        if (bool){
            this.setBackground(Color.GREEN);
        }
        else {
            this.setBackground(Color.WHITE);

        }
        this.choosed = bool;
    }

    public boolean isHit(){
        return this.obj.isHit();
    }

    public Case getCase(){
        return this.obj;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.obj.isHit()){//If the Case has been hit
            g.setColor(Color.BLUE);
            if (this.obj.isShip()){//If there is a ship on the case
                g.setColor(Color.RED);
            }
            g.fillOval(this.getWidth()/4,this.getHeight()/4,this.getWidth()/2,this.getHeight()/2);
        }
    }
}

