package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        /*
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        */
        int seed =10;
        //int seed = Integer.parseInt(args[0]);

        MemoryGame game = new MemoryGame(40, 40, seed);
        //game.startGame();
       // game.generateRandomString(10);
        //game.flashSequence("asdfasdf");
        //String res = game.solicitNCharsInput(10);
        //System.out.println(res);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        //Font font = new Font("Monaco", Font.BOLD, 30);
       // StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        int size = CHARACTERS.length;
        String randomString = "";
        for (int i = 0; i < n; i++ ){
            int x = rand.nextInt(size);
            char randomChar = CHARACTERS[x];
            randomString += Character.toString(randomChar);
        }
        System.out.println(randomString);
        //System.out.println(randomString == null);
        return randomString;

    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen

        StdDraw.enableDoubleBuffering();
        //Clear Canvas
        StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
        //Set the Font and Show the full test in the middle of the world
        if (!gameOver) {
            Font smallFont = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(smallFont);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(width/2, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);
        }
        Font font = new Font("Arial",Font.ITALIC, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.text(width / 2,height/2, s);
        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < letters.length(); i++){
            char x = letters.charAt(i);
            String y = Character.toString(x);
            //Show each Charter in the middle of the screen for ~1s
            drawFrame(y);
            StdDraw.pause(1000);
            //clear and insert 0.5s break for next char
           drawFrame("");
            StdDraw.pause(500);
        }
    }


    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        drawFrame("");
        String res ="";

        // While Loop
        while (res.length() < n){
            if (!StdDraw.hasNextKeyTyped()){
                //keep everything as it is
            } else{
                char current = StdDraw.nextKeyTyped();
                res += current;
                drawFrame(res.toString());
            }
        }
        return res;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts

        //TODO: Establish Game loop
        round = 1;
        gameOver = false;
        playerTurn = false;

        while(!gameOver) {
            playerTurn = false;
            drawFrame("Round " + Integer.toString(round));
            StdDraw.pause(2000);
            String randomString = generateRandomString(5);
            flashSequence(randomString);
            drawFrame("Please Type In Your Answer");
            playerTurn = true;
            StdDraw.pause(2000);
            String playerAnswer = solicitNCharsInput(5);
            if (!playerAnswer.equals(randomString)){
                gameOver = true;
                drawFrame("Game Over, You Made It to Round " + Integer.toString(round));
                StdDraw.pause(2000);

            }else{
                drawFrame("You win!");
                StdDraw.pause(2000);
                round += 1;
            }

        }


    }

}
