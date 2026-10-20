/**This is a battleship game code written by Jaiminsinh Chauhan
 * It's the Version 1.0.
 * It has the size limit of 10 with options to how many ship needs to be added by the user.
 * The number of chances a user can get is the twice the amount of ships on the grip.
 */
import java.util.Scanner;

public class BattleShip {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Grid Size(max10): ");
        int size = scan.nextInt();
        size++; //cuz the array count starts at 0, not 1;

        /**This is for the relative size entered by the user. */
        int[][] grid = new int[size][size];
        char[][] map = new char[size][size];

        //For detailing purpose on the map.
        char[] alpha = {'0','A','B','C','D','E','F','G','H','I','J'};
        char[] beta = {'0','a','b','c','d','e','f','g','h','i','j'};

        /**We are first initilizing all the arrays to 0 to havea better grid output */
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = '0';
            }
        }

        /** Ask user how many ships they want to add */
        System.out.print("How many Ships do you want to add ? ");
        int num = scan.nextInt();
        scan.nextLine(); //to clear the buffer in the scanner.
        for (int i = 1; i <= num; i++) {
            /**Randomly generating the locations of the battleships and dettermine them with 1 */
            int longitude = (int) (Math.random() * (size-1)) + 1; //size is subtracted by 1 because we increased it by 1 in line 13
            int latitude = (int) (Math.random() * (size-1)) + 1; //multiplied with size to have a variable length related to the grid size

            /**If the location already has a ship it will subtract the count by 1 so we don't have 2 ships at 1 place. */
            if(grid[longitude][latitude] == 1){
                i--;
            }else{
                grid[longitude][latitude] = 1;
            }
        }

        /**To make the grid more detailed */
        for(int j = 0; j < size; j++){
            grid[0][j] = j;
            grid[j][0] = j;
            map[0][j] = alpha[j];
            map[j][0] = beta[j];
        }

        /**To print the Map on the console for better visualizations. */
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("  " + map[i][j] + "  ");
            }
            System.out.println();
        }

        /**Lets give user some insights about how many ships are remaining and how many shoots thay have. */
        int chances = num * 2;
        int remainingChance = chances;
        System.out.println("No. of chance: " + chances);
        System.out.println("Ships on the Map: " + num);        

        /**Now we shoot the ships in the map and ask the user for the coordinates, where they want to shoot.
         * The maximun chances they get are dependent on the number of ships. X2 chances.
         */

        for(int i = 0; i < chances; i++){
            
            /**We take one single input and determine the location of the hit. */
            System.out.print("Enter the CoOrdinates to Hit(aA,bB..): ");
            String input = scan.nextLine();
            char firstStrike = input.charAt(0);
            char secondStrike = input.charAt(1);
            int atkCoOr1 = 0,atkCoOr2 = 0;
            for(int run = 1; run < size; run++){
                if(firstStrike == beta[run]){
                    atkCoOr1 = run;
                }
                if(secondStrike == alpha[run]){
                    atkCoOr2 = run;
                }
            }

            /**Adding details to the map according to the hit and miss */
            if(grid[atkCoOr1][atkCoOr2] == 1){
                map[atkCoOr1][atkCoOr2] = 'X';
                System.out.println("Hit!Hit!Hit!");
                num--;
            }else{
                System.out.println("Miss!Miss!Miss!");
                map[atkCoOr1][atkCoOr2] = 'O';
            }

            /**Updated map with the hit and miss details */
            for (int k = 0; k < size; k++) {
            for (int j = 0; j < size; j++) {
                System.out.print("  " + map[k][j] + "  ");
            }
            System.out.println();
        }

            /** Display the stats */
            System.out.println("No. of chance Remaining: " + (remainingChance -= 1));
            System.out.println("Ships on the Map: " + num);

            /** Havng game end if all the ships are destroyed in the map */
            if (num == 0) {
                System.out.println("hurray!! You have won the Game.");
                break;
            }
        }

        //Have a End Message
        System.out.printf("\n\n\n\n-----Game Over-----\nSee you next time. :D");

        scan.close();
    }
}