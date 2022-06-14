package view.graphics;

import javax.swing.*;

import model.BattleShip;
import view.ListenerModel;
import view.graphics.Panel.GlassPane;
import view.graphics.controller.EventPan;
import view.graphics.Panel.GamePanel;
import view.graphics.Panel.Menu;
import view.graphics.controller.MoveMouseListener;
import view.graphics.newComponent.CloseButton;

public class GUI extends JFrame implements ListenerModel {

	private BattleShip game;
	private Menu menu;
	private GamePanel gamePanel;
	private CloseButton closeButton;
	private int width,height;

	public GUI(BattleShip game, final int width, final int height) {
		super();
		this.width = width;
		this.height = height;

		this.game = game;
		this.game.addView(this);

		//for our design
		this.setUndecorated(true);
		this.setTitle("~~ Battle Ship ~~");
		this.setSize(width,height);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//END DESIGN


		MoveMouseListener mml = new MoveMouseListener(this);//Mouse event, use to drag the window from clicking the pannel

		//CREATING THE 2 actual pannels
		this.menu = new Menu(width, height);
		this.gamePanel = new GamePanel(width,height,game);

		//Adding the mouse event to menu/gamePanel
		this.menu.addMouseListener(mml);
		this.menu.addMouseMotionListener(mml);
		this.gamePanel.addMouseListener(mml);
		this.gamePanel.addMouseMotionListener(mml);
		this.gamePanel.setVisible(false);//Setting the game Pannel invisible


		//Creating controller
		EventPan event = new EventPan(this.menu,this.gamePanel,this, this.game);

		//Adding the close button
		this.closeButton = new CloseButton(event);
		this.menu.add(closeButton.getCloseButton(width));
		this.gamePanel.add(closeButton.getCloseButton(width));

		//Add the controller to views.
		this.menu.addEvent(event);
		this.gamePanel.addEvent(event);


		this.setResizable(false);
		this.setContentPane(this.menu);
		this.setVisible(true);
		}


	/**
	 * Set a new game.
	 * @param game
	 */
		public void setGame(BattleShip game){
			this.game = game;
			this.game.addView(this);
			this.gamePanel.setGame(game);
			this.gamePanel.add(closeButton.getCloseButton(this.width));
			this.repaint();
		}

	/**
	 * Update the gamePanel, and the ship display.
	 */
	public void update(){
			((GlassPane) this.getGlassPane()).update();
			this.gamePanel.repaint();
			this.repaint();
	}
}

