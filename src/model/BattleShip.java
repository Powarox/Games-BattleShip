package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import model.utils.Difficulty;
import model.utils.Length;
import model.utils.Stats;
import model.player.GamePlayer;

public class BattleShip extends ABSListenableModel{

	private Length lenght;
	private Difficulty difficulty;
	private GamePlayer player1, player2, currentPlayer;
	private HashMap<GamePlayer, PlayerBoard> board;

	public BattleShip(Length lenght, Difficulty difficulty, GamePlayer p1, GamePlayer p2) {
		this.lenght = lenght;
		this.difficulty = difficulty;
		this.player1 = p1;
		this.player2 = p2;
		this.currentPlayer = this.player1;
		this.board = new HashMap<>();

	}

	/**
	 *
	 * @return The actual current player.
	 */
	public GamePlayer currentPlayer(){
		return this.currentPlayer;
	}

	/**
	 *
	 * @param i X coordinate
	 * @param j Y coordinate
	 * @param player to Select the player'board
	 * @return Case in player'board
	 */
	public Case getCase(int i, int j, GamePlayer player){
		return this.board.get(player).getGrid()[i][j];
	}

	/**
	 *
	 * @param player Player
	 * @return all valids moves of a player
	 */
	public HashSet<Case> validsMoves(GamePlayer player){
		if (player == this.player1){
			return this.board.get(this.player2).getValidMoves();
		}else {
			return this.board.get(this.player1).getValidMoves();

		}
	}

	/**
	 *
	 * @param player Player
	 * @return The list of all the player'ship
	 */
	public ArrayList<Ship> getShipList(GamePlayer player){
		return this.board.get(player).getshipList();
	}

	/**
	 * This method init the 2 player Board
	 */
	public void initGame() {
		this.initBoard();
		this.initShips(this.player1);
		this.initShips(this.player2);
		int len1 = this.board.get(this.player1).getshipList().size();
		int len2 = this.board.get(this.player2).getshipList().size();
	}

	public Difficulty getDifficulty(){
		return this.difficulty;
	}


	/**
	 * Create all ships according to the ships length settings for a player
	 * @param player
	 */
	private void initShips(GamePlayer player) {
		int len = this.lenght.getWidth();
		Random r = new Random();
		for (int i : this.difficulty.getShips()){
			Case first,end;
			boolean bool = true;
			while (bool){
				int x = r.nextInt(len);
				int y = r.nextInt(len);
				boolean dir = r.nextBoolean();
				first = this.getCase(x,y,player);
				if (dir && x+i-1 < len){
					end = this.getCase(x+i-1,y,player);
					bool = !this.createShip(first,end,player,"test");
				}else if (y+i-1 < len){
					end = this.getCase(x,y+i-1,player);
					bool = !this.createShip(first,end,player,"test");
				}
			}

		}
	}

	/**
	 * Case i must be before than case j.
	 * @param i Case Origin
	 * @param j Case end
	 * @param grid Grid where Case i & j are supposed to be.
	 * @return if boolean the ship is valids
	 */
	private boolean validShip(Case i, Case j, Case[][] grid) {
		if (i.getPosi() == j.getPosi()){
			for (int k = i.getPosj(); k<=j.getPosj() ; k++) {
				if (grid[i.getPosi()][k].isShip()) {
					return false;
				}
			}
		}else if (i.getPosj() == j.getPosj()){
			for (int k = i.getPosi(); k<=j.getPosi() ; k++){
				if (grid[k][i.getPosj()].isShip()){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Case i < Case j
	 * @param i Case Origin
	 * @param j Case end
	 * @param player to create the ship for a specified player
	 * @param name of the ship
	 * @return boolean if the ship has been created.
	 */
	public boolean createShip(Case i, Case j, GamePlayer player, String name){
		Case[][] grid = this.board.get(player).getGrid();
		if (this.validShip(i,j,grid)){
			ArrayList<Case> caseShipList = new ArrayList<>();
			if (i.getPosj() == j.getPosj()){
				for (int k = i.getPosi(); k<=j.getPosi(); k++){
					caseShipList.add(grid[k][i.getPosj()]);
				}
			}else{
				for (int k = i.getPosj(); k<=j.getPosj(); k++){
					caseShipList.add(grid[i.getPosi()][k]);
				}
			}
			if (caseShipList.size() >= 2){
				this.board.get(player).addShip(new Ship(caseShipList,name));
				return true;
			}
		}
		return false;
	}

	/**
	 * explicit
	 * @return boolean if the game is over
	 */
	public boolean isTerminal() {
		return this.board.get(this.player1).isDead() || this.board.get(this.player2).isDead();
	}

	/**
	 * Create board of the 2 actual players
	 */
	private void initBoard() {
		this.board.put(this.player1, new PlayerBoard(new Case[lenght.getWidth()][lenght.getWidth()]));
		this.board.put(this.player2, new PlayerBoard(new Case[lenght.getWidth()][lenght.getWidth()]));
	}
	
	public Length getLenght() {
		return this.lenght;
	}

	public void setLenght(String str){this.lenght = Length.getLenght(str);}
	public void setDifficulty(String str){this.difficulty = Difficulty.getDifficulty(str);}

	public GamePlayer getPlayer1() {
		return this.player1;
	}

	public GamePlayer getPlayer2() {
		return this.player2;
	}

	/**
	 *
	 * @return the BattleShip'winner
	 */
	public GamePlayer getWinner() {
		if (this.board.get(this.player1).isDead()) {
			return this.player2;
		}
		else if(this.board.get(this.player2).isDead()) {
			return this.player1;
		}
		return null;
	}

	public Stats getScore(GamePlayer player) {
		return this.board.get(player).getStats();
	}

	/**
	 * Play a move without changing the current player, and remove the move from the valids moves.
	 * @param player The pl
	 * @param move is suppose to be already valids
	 */
	private void playAMove(GamePlayer player,Case move) {
		move.hit();
		this.board.get(player).getStats().hit(move.isShip());
		if (player == this.player1){
			this.board.get(this.player2).getValidMoves().remove(move);
		}else {
			this.board.get(this.player1).getValidMoves().remove(move);
		}
	}

	/**
	 *
	 * @param move is supposed to be valids
	 * @param player the player who is playing
	 * @return if the actual move has touch
	 */
	public boolean play(Case move, GamePlayer player) {
		this.playAMove(player, move);

		if (!move.isShip()) {
			if(player == this.player1) {
				this.currentPlayer = this.player2;
			}else {
				this.currentPlayer = this.player1;
			}
			this.update();
			return false;
		}else {
			this.update();
			return true;
		}
	}
}
