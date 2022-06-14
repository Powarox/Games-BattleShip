package view.graphics.Panel;

import model.Ship;
import model.player.GamePlayer;
import view.graphics.controller.EventPan;
import view.graphics.newComponent.ShipGraph;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GlassPane extends JComponent {
    private ArrayList<ShipGraph> drawList;
    private ArrayList<Ship> shipsP2;
    private int section;
    private int len;
    private String txt;
    private GamePanel parent;
    private JLabel label;
    private JButton leave,replay;

    private int posXend, posYend;
    private boolean over;

    public GlassPane(int section, GamePanel pan){
        super();
        this.parent = pan;
        this.section = section;
        this.len = this.parent.getGame().getLenght().getWidth();

        this.drawList = new ArrayList<>();
        ArrayList<Ship> shipsP1 = this.parent.getGame().getShipList(this.parent.getGame().getPlayer1());
        this.shipsP2 = this.parent.getGame().getShipList(this.parent.getGame().getPlayer2());

        int posX,posY;

        for (Ship s : shipsP1){
            posX = this.section * (len + 2) + s.getOrigin().getPosj()*this.section;
            posY = this.section + s.getOrigin().getPosi()*this.section;
            ShipGraph ship = new ShipGraph(s.getName(),new Point(posX,posY),this.section,s.getShip().size(),!s.getDirection());
            this.drawList.add(ship);
        }
        this.txt = "Le joueur " + this.parent.getGame().getWinner() + " à gagné !!";
        this.label = new JLabel(String.valueOf(txt));
        label.setLayout(null);
        label.setForeground(Color.RED);

    }

    /**
     * Add a terminal menu, if we want to replay
     */
    private void isOver(){
        EventPan event = this.parent.getEvent();
        if (event != null){
            int height = this.parent.getHeight()/2;
            int width = this.parent.getWidth()/2;
            posXend = width-width/2;
            posYend = height-height/2;
            int section = height/3;
            int border = 20;
            this.replay = new JButton("Replay");
            replay.setActionCommand("replay");
            this.leave = new JButton("Quit");
            leave.setActionCommand("quit");
            replay.addActionListener(event);
            leave.addActionListener(event);

            int widthButton = (width - 2*border)/2;
            int heigthButton = section - border;

            replay.setBounds(posXend + border, posYend + 2*section + border/2,widthButton,heigthButton);
            leave.setBounds(posXend + border + widthButton, posYend + 2*section + border/2,widthButton,heigthButton);

            Color c =  new Color(209, 209, 209);
            replay.setBorder(new LineBorder(c,1,true));//Pour enlever les bordures internes au boutton
            replay.setBorderPainted(true);//Enlever L'affichage moche lorsque MouseEntered
            replay.setFocusPainted(false);//De même
            replay.setContentAreaFilled(false);
            replay.addActionListener(event);
            replay.setBackground(Color.WHITE);
            replay.setOpaque(true);
            replay.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    replay.setBackground(c);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    replay.setBackground(Color.WHITE);
                }
            });

            leave.setBorder(new LineBorder(c,1,true));//Pour enlever les bordures internes au boutton
            leave.setBorderPainted(true);//Enlever L'affichage moche lorsque MouseEntered
            leave.setFocusPainted(false);//De même
            leave.setContentAreaFilled(false);
            leave.addActionListener(event);
            leave.setBackground(Color.WHITE);
            leave.setOpaque(true);
            leave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    leave.setBackground(new Color(220,20,60));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    leave.setBackground(Color.WHITE);
                }
            });

            GamePlayer player = this.parent.getGame().getWinner();
            String txt = "Winner : " + player;

            JLabel winner = new JLabel(txt);
            winner.setLayout(null);
            winner.setForeground(Color.BLACK);
            int wt = winner.getFontMetrics(winner.getFont()).stringWidth(txt);
            int ht = winner.getFontMetrics(winner.getFont()).getHeight();
            winner.setBounds(posXend + width/2 - wt/2,posYend + section/2 - ht/2, wt+1, ht);


            String endTXT = "BattleShip design on CPOO";
            JLabel end = new JLabel(endTXT);
            end.setLayout(null);
            end.setForeground(Color.BLACK);
            wt = end.getFontMetrics(end.getFont()).stringWidth(endTXT);
            ht = end.getFontMetrics(end.getFont()).getHeight();
            end.setBounds(posXend + width/2 - wt/2,posYend + section + ht/2, wt+1, ht);


            this.add(end);
            this.add(winner);
            this.add(replay);
            this.add(leave);

            this.over = true;
        }
        else {
            System.out.println("ERROR");
        }
    }

    /**
     * Design to add new ShipGraph when a ship is dead
     */
    public void update() {
        int lenTMP = this.drawList.size();
        int posX, posY;
        for (Ship s : this.shipsP2){
            if (s.isDead()){
                posX = this.section + s.getOrigin().getPosj()*this.section;
                posY = this.section + s.getOrigin().getPosi()*this.section;
                ShipGraph ship = new ShipGraph(s.getName(),new Point(posX,posY),this.section,s.getShip().size(),!s.getDirection());
                this.drawList.add(ship);
            }
        }
        if (this.drawList.size() - lenTMP > 0){
            repaint();
        }

        if(this.parent.getGame().isTerminal()){
            isOver();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        for (ShipGraph s : this.drawList){//Paint dead ship
            s.paint(g);
        }
        if (this.over){
            g.setColor(Color.WHITE);
            g.fillRect(posXend,posYend, this.parent.getWidth()/2,this.parent.getHeight()/2);
            g.setColor(Color.BLACK);
            g.drawRect(posXend-1,posYend-1, this.parent.getWidth()/2 + 2,this.parent.getHeight()/2 + 2);
        }
    }
}
