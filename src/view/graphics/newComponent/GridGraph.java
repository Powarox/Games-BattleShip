package view.graphics.newComponent;

import model.player.GamePlayer;
import view.graphics.Panel.GamePanel;
import view.graphics.controller.CaseMouseListener;

import javax.swing.*;
import java.awt.*;

public class GridGraph {
    private Container container;
    private GamePanel parent;
    public CaseMouseListener CML;

    public GridGraph(GamePanel parent, int caseHeight, int posX, int posY,GamePlayer player){
        this(parent,caseHeight,posX,posY,false,player);
    }

    public GridGraph(GamePanel parent, int caseHeight, int posX, int posY, boolean listenable, GamePlayer player){
        this.parent = parent;
        this.container = new Container();

        int nbCase = this.parent.getGame().getLenght().getWidth();//Board'Length

        GridLayout grid = new GridLayout(nbCase,nbCase);//Setting a new Container
        this.container.setLayout(grid);

        if (listenable){//Design is click/hover a caseButton
            this.CML = new CaseMouseListener(this.parent.getPlay());
        }else {
            this.CML = null;
        }

        //Creating all Buttons
        for (int i =0; i<nbCase ; i++){
            for (int j = 0; j<nbCase; j++){
                JButton button = new CaseButton(this.parent.getGame().getCase(i,j,player),caseHeight);
                button.addMouseListener(CML);
                button.addMouseMotionListener(CML);
                this.container.add(button);
            }
        }

        this.container.setBounds(1,1,caseHeight*nbCase,caseHeight*nbCase);
        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.add(this.container);
        pan.setBounds(posX-1,posY-1,caseHeight*nbCase+2,caseHeight*nbCase+2);
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        this.parent.add(pan);
    }
}
