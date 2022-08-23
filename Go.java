import java.awt.*;

public class Go {
    public static int gameType = 1;
    public static BallsInBins game;
    public static double[][] binPositions = new double [10][2];

    public static void main (String [] args) {
        StdDraw.setCanvasSize(800, 800);
        //selectGame();
        Preliminary();
    }

    public static void Preliminary(){
        StdDraw.clear();

        StdDraw.setPenColor(0, 0, 35);
        Font font = new Font("Garamond", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.9, "Visualizing the Birthday Paradox");
        StdDraw.text(0.5, 0.85, "with a Game of Balls in Bins.");


        font = new Font("Garamond", Font.PLAIN, 25);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.7, "How many bins would you like to play with?");

        //Five Bins Button
        StdDraw.setPenColor(200, 0, 200);
        StdDraw.filledRectangle(0.5, 0.5, 0.12, 0.06);
        font = new Font("Garamond", Font.PLAIN, 25);
        StdDraw.setFont(font);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.text(0.5, 0.5, "Five Bins");

        //Ten Bins Button
        StdDraw.setPenColor(200, 0, 200);
        StdDraw.filledRectangle(0.5, 0.3, 0.12, 0.06);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.text(0.5, 0.3, "Ten Bins");

        int wait = 0;
        while (wait < 1) {
            //Clicking button for five bins
            if ((StdDraw.mouseX() >= 0.38) && (StdDraw.mouseX() <= 0.62)) {
                if ((StdDraw.mouseY() >= 0.52) && (StdDraw.mouseY() <= 0.56)) {
                    game = new BallsInBins(5, gameType);
                    StdDraw.pause(200);
                    drawBins(5);
                    wait++;
                }
            }

            //Clicking button for ten bins
            if ((StdDraw.mouseX() >= 0.38) && (StdDraw.mouseX() <= 0.62)) {
                if ((StdDraw.mouseY() >= 0.24) && (StdDraw.mouseY() <= 0.36)) {
                    game = new BallsInBins(10, gameType);
                    StdDraw.pause(200);
                    drawBins(10);
                    wait++;
                }
            }
        }
    }


    public static void drawBins (int noBins) {
        StdDraw.clear();
        //x-position of first bin, incremented within the loops
        double x = 0.1;

        //five bins
        if (noBins == 5) {
            //incrementing the x position for drawing multiple bins
            for (int i = 0; i < noBins; i++) {
                StdDraw.setPenColor(186, 236, 245);

                //populating array holding the positions of the bins
                binPositions[i][0] = x;
                binPositions[i][1] = 0.5;

                drawBin(x, 0.5);
                x = x + 0.2;
            }
        }

        //ten bins
        else {
            //incrementing the x position for drawing multiple bins, first row y = 0.2
            for (int i = 0; i < noBins/2; i++) {
                StdDraw.setPenColor(186, 236, 245);

                //populating array holding the positions of the bins
                binPositions[i][0] = x;
                binPositions[i][1] = 0.4;

                drawBin(x, 0.4);
                x = x + 0.2;
            }

            //x-position of first bin, second row, incremented within the loops
            x = 0.1;
            //incrementing the x position for drawing multiple bins, second row y = 0.8
            for (int i = 0; i < noBins/2; i++) {
                StdDraw.setPenColor(186, 236, 245);

                //populating array holding the positions of the bins
                binPositions[i + 5][0] = x;
                binPositions[i + 5][1] = 0.65;

                drawBin(x, 0.65);
                x = x + 0.2;
            }

        }

        //Back Button + Throw ball prompt
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.text(0.25, 0.8, "Click mouse to throw a ball!");

        StdDraw.filledRectangle(0.85, 0.8, 0.06, 0.03);
        StdDraw.setPenColor(225, 225, 255);
        StdDraw.text(0.85, 0.8, "BACK");

        int wait = 0;
        while (wait < noBins+1) {
            if (StdDraw.isMousePressed()) {
                throwBall();
                wait++;
            }
        }

//Underdeveloped and Unused code for handling back button
//int wait = 0;
//        while (wait < 1) {
//            if ((StdDraw.mouseX() >= 0.79) && (StdDraw.mouseX() <= 0.91)) {
//                if ((StdDraw.mouseY() >= 0.76) && (StdDraw.mouseY() <= 0.79)) {
//                    StdDraw.pause(700);
//                    Preliminary();
//                    wait++;
//                }
//            }
// /       }

     

    }

    public static void drawBin (double x, double y) {
        double [] xPoints = new double[4];
        double [] yPoints = new double[4];

        xPoints[0] = x - 0.065;
        xPoints[1] = x + 0.065;
        xPoints[2] = x + 0.05;
        xPoints[3] = x - 0.05;

        yPoints[0] = y + 0.06;
        yPoints[1] = y + 0.06;
        yPoints[2] = y - 0.06;
        yPoints[3] = y - 0.06;


        StdDraw.filledPolygon(xPoints, yPoints);
        StdDraw.filledRectangle(x, yPoints[0], 0.068, 0.002);
    }

    public static void throwBall () {
        int a = (int) (Math.random() * 225);
        int b = (int) (Math.random() * 225);
        int c = (int) (Math.random() * 225);
        StdDraw.setPenColor(a, b, c);

        int i = game.add();
        System.out.println(i + " is the index");

        double x = binPositions[i][0] - 0.0325;
        double y = binPositions[i][1] - 0.033;

        //shifting location for the second ball
        if (game.getOccupancies()[i] == 2) x = x + 0.065;

        StdDraw.filledCircle(x, y, 0.015);
    }


    //Unused and Incomplete Method (for selecting between a normal game (1) or a power of two game (2)
    public static void selectGame(){
        StdDraw.setPenColor(0, 0, 35);
        Font font = new Font("Garamond", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.9, "Visualizing the Birthday Paradox with a Game of Balls In Bins");

        //Regular Game Button
        StdDraw.setPenColor(200, 0, 200);
        StdDraw.filledRectangle(0.2, 0.5, 0.12, 0.06);
        font = new Font("Garamond", Font.PLAIN, 25);
        StdDraw.setFont(font);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.text(0.2, 0.5, "Regular Game");

        //Power of Two Button
        StdDraw.setPenColor(200, 0, 200);
        StdDraw.filledRectangle(0.8, 0.5, 0.12, 0.06);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.text(0.8, 0.5, "Power of Two");

        int wait = 0;
        while (wait < 1) {
            //Clicking button for regular
            if ((StdDraw.mouseX() >= 0.08) && (StdDraw.mouseX() <= 0.32)) {
                if ((StdDraw.mouseY() >= 0.44) && (StdDraw.mouseY() <= 0.56)) {
                    gameType = 1;
                    StdDraw.pause(500);
                    Preliminary();
                }
            }

            //Clicking button for power of two
            if ((StdDraw.mouseX() >= 0.68) && (StdDraw.mouseX() <= 0.92)) {
                if ((StdDraw.mouseY() >= 0.44) && (StdDraw.mouseY() <= 0.56)) {
                    gameType = 2;
                    StdDraw.pause(500);
                    Preliminary();
                }
            }
        }
    }
}
