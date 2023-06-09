package Game;

import Board.BoardManager;
import IO.InputOutput;
import Players.ComputerPlayer;
import Players.HumanPlayerFIAL;
import Players.Player;

import java.util.Scanner;

public class FIALPlayersList {
    public Player[] players;
    private char alreadyChosenSymbol;
    private String alreadyChosenName;

    private final BoardManager boardManager;

    public FIALPlayersList(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void createMultiplayerPlayers() {
        InputOutput.println("--- Player 1: Please type in your name and the symbol that you want to use. ---");
        Player player1 = createHumanPlayer();
        InputOutput.println("--- Player 2: Please type in your name and the symbol that you want to use. ---");
        Player player2 = createHumanPlayer();
        this.players = new Player[]{player1, player2};
    }

    public void createComputerPlayerGamePlayers() {
        Player botPlayer = createComputerPlayer();
        InputOutput.println("--- Player 1: Please type in your name and the symbol that you want to use. ---");
        Player player1 = createHumanPlayer();
        this.players = new Player[]{player1, botPlayer};
    }

    public Player createComputerPlayer() {
        char botSymbol = 'B';
        String botName = "bot";
        this.alreadyChosenName = botName;
        this.alreadyChosenSymbol = botSymbol;
        return new ComputerPlayer(botName, botSymbol, this.boardManager);
    }

    public Player createHumanPlayer() {
        Scanner scanner = new Scanner(System.in);
        String name = InputOutput.printAndScanStringAndCompareWith("Type in your Name: ", this.alreadyChosenName);
        this.alreadyChosenName = name;
        InputOutput.println("");
        while (true) {
            String message = "Type in the Symbol you want to use: ";
            char chosenChar = InputOutput.printAndScanCharAndCompareWith(message, this.alreadyChosenSymbol);
                if (chosenChar == ' ') {
                    System.out.println("This Symbol is already taken. Try again.");
                }
                else {
                    this.alreadyChosenSymbol = chosenChar;
                    return new HumanPlayerFIAL(name, chosenChar, this.boardManager);
                }
        }
    }
}
