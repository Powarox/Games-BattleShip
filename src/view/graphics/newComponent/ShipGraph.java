package view.graphics.newComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ShipGraph extends JLabel{

    private int width,height;
    private Point originP;

    private int round,realWidth,realHeight;


    public ShipGraph(String name, Point point,int caseHeight, int length, boolean direction){
        super(name);

        this.originP = point; //Origin point of the Ship
        this.height = caseHeight*length;//For a vertical ship
        this.width = caseHeight;

        this.changeDirection(direction);

        /* For the DRAG&DROP but not finished
        this.setTransferHandler(new TransferHandler("text"));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });*/
        this.repaint();
    }


    public void changeDirection(boolean bool){
        if (bool){//HORIZONTAL
            this.setBounds(this.originP.x, this.originP.y, this.height, this.width);
            this.round = this.width-2;
            this.realHeight = this.width-2;
            this.realWidth = this.height-2;
        }else{//VERTICAL
            this.setBounds(this.originP.x, this.originP.y, this.width, this.height);
            this.round = this.width-2;
            this.realHeight = this.height -2;
            this.realWidth = this.width-2;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255, 14,0));
        g.drawRoundRect(this.originP.x+1,this.originP.y+1,this.realWidth,this.realHeight,this.round,this.round);
    }
}
