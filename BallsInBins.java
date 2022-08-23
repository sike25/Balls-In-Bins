import java.util.Random;
@SuppressWarnings("all")

/**
 * A simulation of throwing ball into bins implemented with an array of linked lists
 * The index of chosen bin should be random (1/binNumber) and independent of other outcomes
 * @author Osasikemwen Ogieva
 * May 2022
 */

public class BallsInBins {
    protected Object[] table; // array of linked lists
    private Random random = new Random();

    //number of bins, specified when game is constructed
    protected int binNumber;
    //number of balls in total
    protected int size = 0;
    //stores the each bin's occupancies corresponding to its index
    private int[] occupancies;
    //number of bins with at least one ball
    private int notEmpty = 0;
    //number of collisions that have occurred
    private int collisions = 0;
    // 1 for regular, 2 for power-of-two-choices, specified when game is constructed
    private int gameType = 1;
    // these boolean serves as a cue to stop a recursive method
    private boolean added = false;

    public BallsInBins (int capacity, int game) {
        //initialise type of game, number of games, hash table and array for storing occupancies for each bin
        gameType = game;
        binNumber = capacity;
        table = new Object[binNumber];
        occupancies = new int[binNumber];

        //add a sentinel/header ball to each list in the table
        //set its index field
        //initialize occupancy array with zeroes
        for (int i = 0; i < binNumber; ++i) {
            Ball headBall = new Ball(true);
            headBall.index = i;
            table[i] = headBall;
            occupancies[i] = 0;
        }
    }

    public int getCollisions(){
        return collisions;
    }

    public int[] getOccupancies() {
        return occupancies;
    }

    public int getNotEmpty() {
        return notEmpty;
    }

    /** Returns the number of balls held in the hashtable/ all the bins.
     * @return total ball number */
    public int size() {
        return this.size;
    }

    /** Returns true if there is no ball is in any bin. False if there is.
     * @return true if table is empty, false if it is not */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Adds a ball to the linked list in the array's index as indicated randomly.
     * 	Makes a call to addToBucket() */
    public int add() {
        //reset added to false, in case the last call to add() left it as true which it most likely did
        added = false;

        //store index in a variable so it does not change
        final int index = getIndex();
        //store the head ball from the array's index/hashCode in variable ball and set its index field
        Ball ball = (Ball) table[index];
        ball.index = index;

        //call addToBucket with the head ball and variable to be added
        //if addToBucket adds, successfully, increment size and return true
        if (addToBucket(ball)) {
            occupancies[index] = occupancies[index] + 1;
            size++;
        }

        if (occupancies[index] == 1) {
            notEmpty++;
        }

        if (occupancies[index] > 1) {
            collisions++;
        }

        return index;
    }

    /** Adds a ball to the hashcode-indicated linked list. Recursive method.
     * 	@param ball the conventionally empty header node of the randomly-indicated linked list
     * 	@return true if ball was successfully added, false if not
     */
    private boolean addToBucket(Ball ball) {
        //because ball is the sentinel ball, we start checking from its kid ball.next i.e. the start ball
        //if the node is null
        if (ball.next == null) {
            ball.next = new Ball();
            added = true;
            return true;
        }

        //if tne node is full, check the kid
        else {
            addToBucket(ball.next);
            return added;
        }
    }

    /** Removes a ball from the hashtable table */
    public void remove(int bin) {
        int index = bin;

        //the header of the linked list indicated by the hashCode, value is null.
        Ball head = (Ball) table[index];
        //the first value-containing node in the linked list indicated by the hashCode
        Ball start = ((Ball) table[index]).next;

        if (start.next == null) {
            //if the first ball has no next
            head.next = null;
        }
        else {
            //if the start ball has a next ball, make that the head ball's next
            head.next = start.next;
        }
        //decrement size.
        occupancies[index] = occupancies[index] - 1;
        size--;
    }

    /** Randomly determines the index in the array where a value x should go,
     * @return a randomly selected int which is anywhere from 0 to binNumber
     */
    protected int getIndex() {
        int toReturn;
        //if game is the power of two
        if (gameType == 2) {
            int firstIndex = random.nextInt(binNumber);
            int secondIndex = random.nextInt(binNumber);
            toReturn = Math.min(occupancies[firstIndex], occupancies[secondIndex]);
        }
        else {
            toReturn = random.nextInt(binNumber);
        }

        return toReturn;
    }

    /** Makes a copy of the hashtable with a higher capacity and replaces the old hash table with it.
     * 	Fails if the linked lists in the arrays have no empty headers.
     * 	#Rosenbaum's code
     */
    protected void increaseCapacity() {
        binNumber = binNumber * 2;

        // store the old hash table
        Object[] oldTable = table;

        // make a new hash table and initialize it
        table = new Object[binNumber];

        for (int i = 0; i < table.length; ++i) {
            table[i] = new Ball();
        }

        // reset the size to 0 since it will get incremented when we
        // add elements to the new table
        size = 0;

        // iterate over lists in oldTable and add elements to new table
        for (int i = 0; i < oldTable.length; ++i) {
            Ball ball = ((Ball) oldTable[i]).next;
            while (ball != null) {
                this.add();
                ball = ball.next;
            }
        }
    }

    /** Prints the elements in the hashtable from index 0 to index table.length-1
     * And within each index, prints the balls in the linked list from the header down */
    public void print() {
        for (int i = 0; i < table.length; ++i) {
            Ball ball = ((Ball) table[i]).next;

            System.out.println("\n At index " + i);
            while (ball != null) {
                System.out.println(ball.head);
                ball = ball.next;
            }
        }
    }

    /**Inner class specifying a ball object for the linked lists in the arrays
     */
    class Ball {
        private Ball next = null;
        private boolean head = false;
        private int index;

        //empty constructor
        public Ball() {
        }

        public Ball(boolean hd) {
            head = hd;
        }
    }
}
