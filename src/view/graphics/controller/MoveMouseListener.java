package view.graphics.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MoveMouseListener implements MouseListener, MouseMotionListener {
    private JFrame target;
    private Point startDrag;
    private Point startLoc;

    private int heightD,widthD;
    private Dimension dim;

    public MoveMouseListener(JFrame target) {
        this.target = target;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.heightD = (int) screenSize.getHeight();
        this.widthD = (int) screenSize.getWidth();
        this.dim = null;
    }

    private Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.target.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),(int) (target_location.getY() + cursor.getY()));
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        this.startDrag = this.getScreenLocation(e);
        this.startLoc = this.target.getLocation();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        Point offset = new Point((int) current.getX() - (int) startDrag.getX(),(int) current.getY() - (int) startDrag.getY());

        if (this.dim == null){
            this.dim = this.target.getSize();
        }


        int posi = (int) (this.startLoc.getX() + offset.getX());

        if (posi<0){//If the window get ouf the screen
            posi = 0;
        }
        if (posi + this.dim.getWidth()> widthD){
            posi = widthD - (int) this.dim.getWidth();
        }


        int posj =(int) (this.startLoc.getY() + offset.getY());

        if(posj<0){//If the window get ouf the screen
            posj=0;
        }
        if(posj + this.dim.getHeight() > heightD){
            posj=heightD - (int)this.dim.getHeight();
        }


        Point new_location = new Point(posi, posj);
        target.setLocation(new_location);
    }

    public void mouseMoved(MouseEvent e) {
    }
}