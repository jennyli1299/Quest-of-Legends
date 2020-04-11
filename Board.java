import java.util.Arrays;
// import java.util.Scanner;

public class Board {
    private Lane[] gameBoard;
    private int n;
    private int h;

    public Board(int n, int h) {
        this.n = n;
        this.h = h;
        gameBoard = new Lane[n];
        for (int l = 0; l < n; l++) {
            gameBoard[l] = new Lane(l+1, h);
        }
    }

    // public boolean valid(String dir) { // check valid move & move
    //     // String[] directions = new String[] {"w", "A", "S", "D"};
    //     int r = p[0];
    //     int c = p[1];
    //     switch (dir) {
    //         case "W":
    //             r--;
    //             break;
    //         case "A":
    //             c--;
    //             break;
    //         case "S":
    //             r++;
    //             break;
    //         case "D":
    //             c++;
    //         case "w":
    //             r--;
    //             break;
    //         case "a":
    //             c--;
    //             break;
    //         case "s":
    //             r++;
    //             break;
    //         case "d":
    //             c++;
    //     }
    //     if (r < 0 || c < 0 || r >= n || c >= n) {
    //         System.out.println("You cannot move in that direction! It's off the map!");
    //         return false;
    //     }
    //     if (gameBoard[r][c].getType().equals("Nonaccessible")) {
    //         System.out.println("You cannot move in that direction! It's blocked and nonaccessible!");
    //         return false;
    //     }
    //     else {
    //         // gameBoard[p[0]][p[1]].leaveTile();
    //         // p[0] = r;
    //         // p[1] = c;
    //         // gameBoard[r][c].setLocTile();
    //         return true;
    //     }
    // }
    // public Tile move(String dir) { // check valid move & move
    //     int r = p[0];
    //     int c = p[1];
    //     switch (dir) {
    //         case "W":
    //             r--;
    //             break;
    //         case "A":
    //             c--;
    //             break;
    //         case "S":
    //             r++;
    //             break;
    //         case "D":
    //             c++;
    //         case "w":
    //             r--;
    //             break;
    //         case "a":
    //             c--;
    //             break;
    //         case "s":
    //             r++;
    //             break;
    //         case "d":
    //             c++;
    //     }
    //     gameBoard[p[0]][p[1]].leaveTile();
    //     p[0] = r;
    //     p[1] = c;
    //     gameBoard[r][c].setLocTile();
    //     return gameBoard[r][c];
    // }

    public int getn() {
        return n;
    }
    // public String getTileType() {
    //     return gameBoard[p[0]][p[1]].getType();
    // }
    public Lane[] getMap() {
        return this.gameBoard;
    }

    public String toString() {
        String map = "";
        String[] lane0 = gameBoard[0].toString().lines().toArray(String[]::new);
        String[] lane1 = gameBoard[1].toString().lines().toArray(String[]::new);
        String[] lane2 = gameBoard[2].toString().lines().toArray(String[]::new);
        Tile inaccessible = new Tile();
        String[] in = inaccessible.toString().lines().toArray(String[]::new);
        String[] x = new String[] {in[0], in[1], in[2], ""};
        for (int i = 0; i < h*4; i++) {
            if (i%4 == 3) continue;
            if (i%4 == 1) {
                map = map + Integer.toString((int)(i/4)) + "  ";
            }
            else map = map + "   ";
            map = map + lane0[i] + x[i%4] + "   " + lane1[i] + x[i%4] + "   " + lane2[i] + "\n";
            if (i%4 == 2) {
                map = map + "\n";
            }
        }
        return map;
    }

    /** public void resetBoard() {
        this.gameBoard = new Board(8);
    } */

    public static void main(String[] args) {
        Board hello = new Board(3, 8);

        System.out.println(hello);

        String[] lane2 = hello.gameBoard[1].toString().lines().toArray(String[]::new);
        System.out.println(Arrays.toString(lane2));

        Lane[] map = hello.getMap();

        // for (int r = 0; r < hello.getn(); r++) {
        //     for (int c = 0; c < hello.getn(); c++) {
        //         System.out.println(map[r][c].getType());
        //     }
        // }

        // Scanner m = new Scanner(System.in);
        // boolean exit = false;
        // do {
        //     System.out.println("Where would you like to move your team of Heroes? W: North, A: West, D: East, S: South, and I -> Show Team Stats");
        //     String dir = m.nextLine();
        //     if (dir.equals("I") || dir.equals("i")) {
        //         // PRINT TEAM INFO
        //     }
        //     if (dir.equals("W") || dir.equals("A") || dir.equals("D") || dir.equals("S") || dir.equals("w") || dir.equals("a") || dir.equals("d") || dir.equals("s")) {
        //         boolean valid = hello.valid(dir);
        //         if (valid) {
        //             hello.move(dir);
        //             System.out.println(hello);
        //         }
        //         // hello.move(dir);
        //     }
        //     else if (dir.equals("e")) exit = true;
        //     else {
        //         System.out.println("Invalid Input!");
        //     }
        // }
        // while (!exit);
        // m.close();
    }
}