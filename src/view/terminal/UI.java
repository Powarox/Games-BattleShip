package view.terminal;

import model.BattleShip;
import model.Case;
import model.player.GamePlayer;
import view.ListenerModel;
import view.terminal.controller.Controller;


public class UI implements ListenerModel {
    private BattleShip game;

    public UI(BattleShip game) {
        this.game = game;
        this.game.addView(this);
        update();
    }

    private void gridToString(GamePlayer player) {
        char letter = '@';
        String str = "  ";

        int length = this.game.getLenght().getWidth();

        for (int i = 0; i < length; i++) {
            letter = (char) (letter + 1);
            str = str + letter + " ";
        }

        str += "\n";
        System.out.println(str);

        str = "";
        for (int i = 0; i < length; i++) {
            str = str + Integer.toString(i) + " ";
            for (int j = 0; j < length; j++) {
                String value;
                if (this.game.getCase(i, j, player) != null && this.game.getCase(i, j, player).isHit()) {
                    value = "!";
                    if (this.game.getCase(i, j, player).isShip()) {
                        value = "X";
                    }
                } else {
                    value = "_";
                }
                str = str + value + " ";
            }
            str += "\n";
        }
        System.out.println(str);
    }


    private void gameToString() {
        System.out.println("IA'board");
        this.gridToString(this.game.getPlayer1());
        System.out.println("Human'board");
        this.gridToString(this.game.getPlayer2());
    }

    private void playAMoveString() {
        System.out.println("Enter your following move");
    }

    private void endGame() {
        GamePlayer winner = this.game.getWinner();
        System.out.println(winner + " is the WINNER.");
        System.out.println("With " + this.game.getScore(winner).getRatio() + "% of Sucess !");

    }

    @Override
    public void update() {
        gameToString();
        if (!this.game.isTerminal()) {
            if (this.game.currentPlayer() == this.game.getPlayer1()) {
                Case mv;
                do {
                    playAMoveString();
                    mv = Controller.scan(this.game);
                } while (mv.isHit());
                if (this.game.validsMoves(this.game.getPlayer1()).contains(mv)) {
                    if (mv.isShip()) System.out.println("TOUCHE");
                    else System.out.println("PAS TOUCHE");
                    if (!this.game.play(mv, this.game.getPlayer1())) {
                        boolean test = true;
                        while (test && !this.game.isTerminal()) {
                            Case move = this.game.getPlayer2().chooseMove(this.game);
                            test = this.game.play(move, this.game.getPlayer2());
                            if (move.isShip()) {
                                System.out.println("L'IA à TOUCHE !!");
                            }
                        }
                    }
                }
            }
        }
        else {
            endGame();
        }
    }
}

