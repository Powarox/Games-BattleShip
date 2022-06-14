package view.graphics.Panel;

import model.utils.Difficulty;
import model.utils.Length;
import view.graphics.controller.EventPan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JButton;


public class Menu extends JPanel implements ActionListener{
    public final String PATH = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

    public JButton play, quit;
    public EventPan event;
    private JPanel panelMain;
    private int width, height;

    public Menu(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.setSize(width,height);
        JLabel picLabel = null;

        try{
            System.out.println(PATH);
            BufferedImage image = ImageIO.read(new File("C:/Users/firer/OneDrive/Documents/ARobin/Programmation/Developpement Java/L2 Informatique/Semestre 2/Compelement Java/Projet Bataille Navale/ressources/images/" + "image_menu2.png"));

            // Resize an image;
            Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            picLabel = new JLabel(new ImageIcon(resized));
            picLabel.setBounds(0,0,width,height);

        }catch (IOException e){
            System.out.println("Unable to find images/image_menu2.png");
            System.exit(-1);
        }

        this.setLayout(null);

        Color background = new Color(52,138,244);

        this.play = new JButton("   PLAY   ");
        this.play.setForeground(Color.WHITE);
        this.play.setBackground(background);

        this.quit = new JButton("   QUIT   ");
        quit.setForeground(new Color(44,44,44));
        quit.setBackground(background);

        play.setBounds((int) (width-150)/2, (int) (height - 2*40)/2, 150,40);
        quit.setBounds((int) width/2 - 150/2, (int) (height + 40)/2 + 20, 150,40);


        this.play.setActionCommand("play");
        this.quit.setActionCommand("exit");

        this.play.addActionListener(this);
        this.quit.addActionListener(this);

        this.play.setFocusPainted(false);
        this.quit.setFocusPainted(false);


        this.initDifficulties();
        this.add(this.play);
        this.add(this.quit);
        this.add(picLabel);


    }

    public void initDifficulties(){
        Length[] lengthValue = Length.values();
        Difficulty[] difficultiesValue = Difficulty.values();

        String[] lenghtString = new String[lengthValue.length];
        for (int i = 0; i<lengthValue.length; i++){
            lenghtString[i] = lengthValue[i].getName();
        }

        String[] difficultyString = new String[difficultiesValue.length];
        for (int i = 0; i<difficultiesValue.length; i++){
            difficultyString[i] = difficultiesValue[i].getName();
        }

        JComboBox lenghtBox = new JComboBox(lenghtString);
        lenghtBox.setActionCommand("time");
        lenghtBox.setSelectedIndex(1);


        lenghtBox.setBounds(20,this.height-40,100,20);
        lenghtBox.addActionListener(this);

        JComboBox difficultyBox = new JComboBox(difficultyString);
        difficultyBox.setBounds(this.width-140,this.height-40,100,20);
        difficultyBox.setActionCommand("difficulty");
        difficultyBox.setSelectedIndex(1);
        difficultyBox.addActionListener(this);

        this.add(lenghtBox);
        this.add(difficultyBox);
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

}
