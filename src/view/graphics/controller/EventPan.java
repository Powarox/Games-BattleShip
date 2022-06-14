package view.graphics.controller;

import model.BattleShip;
import model.Case;
import model.player.GamePlayer;
import model.player.Human;
import model.player.IA;
import model.utils.Stats;
import view.graphics.GUI;
import view.graphics.Panel.GamePanel;
import view.graphics.Panel.GlassPane;
import view.graphics.newComponent.CaseButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventPan implements ActionListener{
    private JPanel menuPan;
    private GamePanel gamePanel;
    private JFrame frame;
    private JLabel log;
    private BattleShip game;

    public EventPan(JPanel menuPanel, GamePanel gamePanel, JFrame frame, BattleShip game){
        super();
        this.game=game;
        this.menuPan = menuPanel;
        this.frame = frame;
        this.gamePanel = gamePanel;
    }

    public void endGame(){
        GamePlayer winner = this.game.getWinner();
        Stats stats = this.game.getScore(winner);
        this.log.setText("Le joueur " + winner + " a gagné avec " + stats.getRatio() + " de succés");
        this.log.setBounds(this.gamePanel.getSection(), this.log.getY(),2000, this.log.getHeight());
        this.gamePanel.getPlay().setVisible(false);
    }

    public void actionPerformed(ActionEvent e){
        String name = e.getActionCommand().toLowerCase();
        switch (name){
            case "play" ://Play Button on Menu
                this.game.initGame();
                this.menuPan.setVisible(false);
                this.gamePanel.setVisible(true);
                this.gamePanel.init();
                this.frame.setGlassPane(new GlassPane(this.gamePanel.getSection(),this.gamePanel));
                this.frame.getGlassPane().setVisible(true);
                this.frame.setContentPane(this.gamePanel);
                break;
            case "exit":
                Thread.interrupted();
                System.exit(0);
                break;
            case "time"://If the JCOMBOBOX length has been change
                JComboBox box = (JComboBox)e.getSource();
                String line = (String)box.getSelectedItem();
                System.out.println(line);
                this.game.setLenght(line);
                break;
            case "difficulty" ://If the difficulty has been change
                JComboBox box2 = (JComboBox)e.getSource();
                String line2 = (String)box2.getSelectedItem();
                System.out.println(line2);
                this.game.setDifficulty(line2);
                break;
            case "start"://If the user want to start the game, was first design to give time to user to move his ship.
                this.gamePanel.getPlay().inited();
                this.log = this.gamePanel.getLab();
                this.log.setText("Le jeu commence !! A vous de commencer");
                break;
            case "hit"://If the hit button has been clicked
                if (!this.game.isTerminal()){
                    if (this.game.currentPlayer() == this.game.getPlayer1()){//Only the Human Player can do this event, i could replace it by and instance of the human
                        CaseButton btt = this.gamePanel.getPlay().getButton();
                        if (btt != null && this.game.validsMoves(this.game.getPlayer1()).contains(btt.getCase())){//If it's a valid move
                            if (btt.getCase().isShip()) this.log.setText("Touch");
                            else this.log.setText("not touch");
                            if(!this.game.play(btt.getCase(),this.game.getPlayer1())){
                                boolean test = true;
                                while(test && !this.game.isTerminal()){
                                    Case move = this.game.getPlayer2().chooseMove(this.game);
                                    test = this.game.play(move,this.game.getPlayer2());
                                    if (move.isShip()){
                                        this.log.setText("IA has touch");
                                    }
                                }
                            }
                            if (this.game.isTerminal()) endGame();
                        }
                    }
                }else {
                    endGame();
                }
                break;
            case "replay"://If the user want to replay
                this.gamePanel.removeAll();//Reset all actual obj.
                this.game = new BattleShip(this.game.getLenght(),this.game.getDifficulty(),new Human(),new IA());
                this.game.initGame();
                ((GUI)this.frame).setGame(game);
                this.gamePanel.getPlay().setVisible(true);
                this.gamePanel.getPlay().inited();
                this.frame.setGlassPane(new GlassPane(this.gamePanel.getSection(),this.gamePanel));
                this.frame.getGlassPane().setVisible(true);
                this.frame.setContentPane(this.gamePanel);
                break;
        }

    }
}
