package view.graphics.Panel;

import model.BattleShip;
import view.graphics.controller.EventPan;
import view.graphics.newComponent.GridGraph;
import view.graphics.newComponent.ReadyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements ActionListener{
    public final String PATH = this.getClass().getProtectionDomain().getCodeSource().getLocation() + "ressources/images/";

    private ReadyButton play;
    private EventPan event;
    private int width, height;
    private BattleShip game;
    private int section;
    private JLabel lab;

    public GamePanel(int width, int height, BattleShip game) {
        super();
        this.setLayout(null);//IMPORTANT permet de redefinir la taille et position de tout les JComponent enfant a ce JPanel
        this.setPreferredSize(new Dimension(width,height));
        this.event = null;
        this.game = game;
        this.width = width;
        this.height = height;
        this.setSize(width,height);
        this.play = new ReadyButton();
        this.repaint();
    }

    public EventPan getEvent(){
        return this.event;
    }
    public JLabel getLab(){
        return this.lab;
    }
    public ReadyButton getPlay(){
        return this.play;
    }

    public void addEvent(EventPan e){
        this.event=e;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (this.event != null){
            this.event.actionPerformed(e);
        }
    }

    public void init(){
        int len = game.getLenght().getWidth();
        char letter = '@'; //@ correspond au caractère avant le A majuscule !

        this.section = height/(len+2);

        int posXLetter = (int) (section*1.5);
        int posYLetter = section/2;
        int posXNumber = section/2;
        int posYNumber = (int)(section*1.5);

        int wt,ht;
        JLabel label;

        for (int i=0; i<len ; i++){
            letter = (char) (letter + 1);

            //LETTERS
            label = new JLabel(String.valueOf(letter));
            label.setLayout(null);
            label.setForeground(Color.RED);
            wt = label.getFontMetrics(label.getFont()).stringWidth(String.valueOf(letter));//Take back the string'width
            ht = label.getFontMetrics(label.getFont()).getHeight();//Same with heigth
            label.setBounds(posXLetter - wt/2,posYLetter - ht/2, wt+1, ht);

            this.add(label);

            //NUMBERS
            label = new JLabel(Integer.toString(i+1));
            label.setLayout(null);
            label.setForeground(new Color(0,128,0));
            wt = label.getFontMetrics(label.getFont()).stringWidth(Integer.toString(i+1));//Take back the string'width
            ht = label.getFontMetrics(label.getFont()).getHeight();//Same with heigth
            label.setBounds(posXNumber - wt/2,posYNumber - ht/2, wt+1, ht);

            this.add(label);

            posXLetter += section;
            posYNumber += section;
        }

        //Creating both GridGrapth for each player
        new GridGraph(this,section,section,section, true, this.game.getPlayer2());//For player number 1 who hit on the player2'board
        new GridGraph(this, section,(len+2)*section,section, this.game.getPlayer1());//For the number 2

        this.play.setBounds((len+2)*section/2 - (section*len)/4,(section*(len+1)) + 1,(section*len)/2,section-2);

        this.lab = new JLabel("Hello player !");
        this.lab.setLayout(null);
        this.lab.setBounds((len+2)*section + section,(section*(len+1)) + 1,(section*len)/2,section-2);

        this.add(this.lab);

        if (this.event != null){
            this.play.addActionListener(this.event);
            this.play.addMouseListener(new MouseAdapter() {//Settings hit/start button design and EVent
                @Override
                public void mouseEntered(MouseEvent e) {
                    play.setBackground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    play.setBackground(Color.WHITE);
                }
            });
        }
        else {
            System.out.println("Event not initialized !!");
        }
        this.add(this.play);
    }

    public void setGame(BattleShip game){
        this.game = game;
        this.init();
    }

    public int getSection(){
        return this.section;
    }

    public BattleShip getGame(){
        return this.game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
