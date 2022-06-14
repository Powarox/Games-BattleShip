package view.graphics.controller;

import view.graphics.newComponent.CaseButton;
import view.graphics.newComponent.ReadyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CaseMouseListener implements MouseListener, MouseMotionListener {

    private CaseButton lastHover;
    private CaseButton lastClick;
    private ReadyButton hitButton;


    public CaseMouseListener(ReadyButton btt){
        this.hitButton = btt;
    }

    @Override
    public void mouseReleased(MouseEvent e){

    }
    @Override
    public void mousePressed(MouseEvent e){//CLICK EVENT
        Object button = e.getSource();

        if(button instanceof JButton){
            if (!((CaseButton)button).isHit()){
                if (lastClick != null){
                    lastClick.setChoosed(false);
                }
                CaseButton btt = (CaseButton) button;
                lastClick = btt;
                this.hitButton.setButton(lastClick);
                btt.setChoosed(true);
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e){//If we enter in the case
        Object button = e.getSource();
        if (button instanceof JButton){
            if (lastHover != null && lastClick == null){
                lastHover.setBackground(Color.WHITE);
            }
            if (!((CaseButton)button).isHit()){
                if(lastClick != null && lastClick.equals(button) && lastClick.isChoosed()){
                    lastClick.setBackground(Color.GREEN);
                }else{
                    lastHover = (CaseButton) button;
                    lastHover.setBackground(Color.ORANGE);
                }
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e){//If we get out the case
         Object button = e.getSource();
         if (button instanceof JButton){
             ((CaseButton) button).setBackground(Color.WHITE);
             if (lastClick != null) {
                 lastClick.setBackground(Color.GREEN);
             }
         }


    }
    @Override
    public void mouseClicked(MouseEvent e){

    }
    @Override
    public void mouseDragged(MouseEvent e){

    }
    @Override
    public void mouseMoved(MouseEvent e){

    }
}
