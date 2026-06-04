package PracticeQuestions.TicTacToe;
import java.util.*;

interface IObserver{
    void update(String msg);
}

class PushNotification implements IObserver{
    @Override
    public void update(String msg){
        System.out.println( "[PUSH NOTIFICATION] : " +  msg);
    }
}

class ScoreBoardNotification implements IObserver{
    @Override
    public void update(String msg){
        System.out.println( "[SCORE BOARD UPDATE] : "  + msg);
    }
}

enum Symbol{
    X('X') ,
    O('O') ,
    EMPTY('-');

    private final char mark;
    Symbol(char mark){
        this.mark = mark;
    }

    public char getSymbol(){
        return mark;
    }
}

interface Player{
    Symbol getSymbol();
    int getScore();
    String getName();
    void incrementScore();
}

class RealPlayer implements Player{
    private int score = 0;
    private final String name;
    private final int id;
    private final Symbol smb;

    public RealPlayer(int id, String name, Symbol smb) {
        this.id = id;
        this.name = name;
        this.smb = smb;
    }

    @Override
    public Symbol getSymbol(){
        return smb;
    }
    @Override
    public int getScore(){
        return score;
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void incrementScore(){
        score++;
    }
    public int getplayerid(){
        return id;
    }
}

class Board{
    private final Symbol grid[][];
    private final int size;

    Board(int size){
        this.size = size;
        grid = new Symbol[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public int getSize(){
        return size;
    }

    public Symbol getCell(int r, int c) {
        return grid[r][c];
    }

    public boolean isEmptyCell(int r , int c){
        return grid[r][c] == Symbol.EMPTY;
    }

    public void markCell(int r , int c , Symbol smb){
        if(r<0 || c<0 || r>=size || c >= size || !isEmptyCell(r,c) ) return;
        grid[r][c] = smb;
    }

    public void displayBoard(){
        System.out.println("GameBoard status");
        for(int i=0; i<size ; i++){
            for(int j=0; j<size ; j++){
                System.out.print(grid[i][j].getSymbol());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void reset() {
        for(int i=0; i<size ; i++){
            for(int j=0; j<size ; j++){
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }
}

interface Rules{
    boolean CheckWin(Board b , Symbol smb );
    boolean CheckDraw(Board b );
    boolean isValid(Board b , int row,  int col);
}

class StandardRules implements Rules{

    @Override
    public boolean CheckWin(Board b , Symbol smb ) {
        int size = b.getSize();
        //horizontal check
        for(int i=0; i<size; i++){
            boolean valid = true;
            for(int j=0; j<size ; j++){
                if(b.getCell(i , j) != smb){
                    valid = false;
                    break;
                }
            }
            if(valid) return true;
        }

        //vertical check
        for(int j=0; j<size ; j++){
            boolean valid = true;
            for(int i=0; i<size; i++){
                if(b.getCell(i , j) != smb){
                    valid = false;
                    break;
                }
            }
            if(valid) return true;
        }

        //diagonal check
        boolean valid1 = true;
        boolean valid2 = true;
        //top-left to btm-right
        for(int i=0; i<size ; i++){
            if(b.getCell(i , i) != smb) {
                valid1 = false;
                break;
            }
        }
        //top-right to btm-left
        for(int i=0; i<size ; i++){
            if(b.getCell(i , size-i-1) != smb) {
                valid2 = false;
                break;
            }
        }
        return valid1 || valid2;
    }

    @Override
    public boolean CheckDraw(Board b ) {
        int size = b.getSize();
        //check if no cell is empty
        for(int i=0; i<size; i++){
            for(int j=0; j<b.getSize() ; j++){
                if(b.getCell(i , j) == Symbol.EMPTY) return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(Board b , int r, int c ) {
        int size = b.getSize();
        if(r<0 || c<0 || r>=size || c >= size || b.getCell(r,c) != Symbol.EMPTY ) return false;
        return true;
    }
}

class TicTacToeGame{
    private final  Board gameBoard;
    private final Deque<Player> players;
    private final Rules rules;
    private final ArrayList<IObserver> observers;
    private final Scanner sc;

    TicTacToeGame(Rules rules , int size , Scanner sc) {
        this.sc = sc;
        this.rules = rules;
        gameBoard = new Board(size);
        players = new ArrayDeque<>();
        observers = new ArrayList<>();
    }

    public void addObserver(IObserver obs){
        observers.add(obs);
    }

    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    public void addPlayer(Player p){
        players.addLast(p);
    }

    public void notify(String msg){
        for(IObserver o : observers){
            o.update(msg);
        }
    }

    public boolean gameOver(){
        int size = gameBoard.getSize();
        if ( rules.CheckDraw(gameBoard) ){
            notify("Its a Draw Match! Better Luck Next Time");
            return true;
        };
        return false;
    }

    public void startGame(){

        Scanner sc = new Scanner(System.in);

        boolean continuePlay = true;

        while(continuePlay){

            play();

            notifyScores();

            System.out.println("\nPlay another round? (Y/N)");

            String choice = sc.next();

            if(choice.equalsIgnoreCase("N")){
                continuePlay = false;
            }
            else{
                gameBoard.reset();
            }
        }

        notify("===== FINAL SCORES =====");
        notifyScores();
    }

    private void notifyScores(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n===== SCOREBOARD =====\n");

        for(Player p : players){
            sb.append(p.getName())
                    .append(" : ")
                    .append(p.getScore())
                    .append("\n");
        }

        notify(sb.toString());
    }

    public void play(){
        if(players.size() < 2) {
            System.out.println("We need atleast two players to proceed with the game");
            return;
        }

        notify("-----------THE GAME HAS STARTED----------");

        while(!gameOver()) {
            //using try catch block to handle the invlaid inputs
            try {
                int r = sc.nextInt();
                int c = sc.nextInt();


                Player currentPlayer = players.getFirst();
                Symbol currsmb = currentPlayer.getSymbol();

                System.out.println(currentPlayer.getName() + " , please choose you cell's row and colum ");

                if(!rules.isValid(gameBoard , r , c )) {
                    System.out.println(currentPlayer.getName() + " , you have entered an invalid cell ");
                    continue;
                }

                gameBoard.markCell(r , c , currsmb);
                notify(currentPlayer.getName() + "(" + currsmb.getSymbol() + ") has played (" + r + ", " + c + ")");
                gameBoard.displayBoard();

                if(rules.CheckWin(gameBoard , currsmb)){
                    notify(currsmb.getSymbol() + " has won the game. CONGRAGULATIONS , "+ currentPlayer.getName() + "!!!");
                    currentPlayer.incrementScore();
                    notify("UPDATED SCORES");
                    break;
                }

                //players turn is over put him to the back of the deque
                players.pollFirst();
                players.addLast(currentPlayer);

            }catch(InputMismatchException e){

                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }
        }
    }
}

enum Game{
    STANDARD,
    AI
}

class GameFactory{
    public static TicTacToeGame creategame(Game gm , Rules rules , int size , Scanner sc){
        if(gm == Game.STANDARD) return new TicTacToeGame(rules , size  , sc );
        System.out.println("Other version yet to be realeased");
        return null;
    }
}

public class Main{
    public static void main(String arg[]){

        Rules rules = new StandardRules();

        Scanner sc = new Scanner(System.in);
        TicTacToeGame game = GameFactory.creategame(Game.STANDARD , rules , 3 , sc);

        System.out.println("Enter the first player name ");
        String name1 = sc.nextLine();
        System.out.println("Enter the second player name ");
        String name2 = sc.nextLine();

        //adding players to sytart the game
        Player p1 = new RealPlayer(1 , name1 , Symbol.X);
        Player p2 = new RealPlayer(2 , name2  , Symbol.O);
        game.addPlayer(p1);
        game.addPlayer(p2);

        //add if anyobserever to the game
        IObserver pushNote = new PushNotification();
        IObserver scoreNote = new ScoreBoardNotification();
        game.addObserver(pushNote);
//        game.addObserver(scoreNote);

        game.startGame();
    }
}