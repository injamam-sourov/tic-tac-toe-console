import java.util.Scanner;

public class ticTacToe {
    public static void main(String[] args) {
        // open scanner for user input
        Scanner input = new Scanner(System.in);
        // create board (3x3 array)
        char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        };
        // show welcome message 
        System.out.println("A game of Tic Tac Toe! Use numbers 1 though 9 to insert into cells.");
        // print board to show cell numbers
        printBoard(board);
        // clear cell numbers for next board prints
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        } 
        // initialize parameters
        int cell = 1, row = 0, col = 0, turn = 1;
        boolean invalid = false, duplicate =false, won = false; 
        char mark = 'X';
        // repeat until game is won
        while(!won) {
            // declare tie if turns exceed 9 and is not won 
            if(turn == 10) {
                System.out.println("Game Tied!");
                break;
            }
            System.out.print("Player '" + mark + "', Cell: ");
            // repeat until valid and unique input
            do {
                // get cell number from user
                cell = input.nextInt();
                // check if input is valid
                if(cell < 0 || cell > 9) {
                    System.out.print("Invalid input! Try again: ");
                    invalid = true;
                } else {
                    invalid = false;
                    // convert cell (1 to 9) to row and column
                    if(cell < 4) {
                        row = 0;
                        col = cell - 1;
                    } else if(cell < 7) {
                        row = 1;
                        col = cell - 4;
                    } else {
                        row = 2;
                        col = cell - 7;
                    }
                    // check if cell is repeated
                    if(board[row][col] != '-') {
                        System.out.print("Cell already used! Try again: ");
                        duplicate = true;
                    } else {
                        duplicate = false;
                    }
                }
            } while (invalid || duplicate);
            // write to board
            board[row][col] = mark;
            // check if game won
            won = isWon(row, col, board);
            // print board
            printBoard(board);
            // incremnt turn or notify game won
            if(won) {
                System.out.println("Congratulations! PLayer " + mark + " wins!");
            } else {
                turn++;
            }
            // swtich mark
            if(mark == 'X') {
                mark = 'O';
            } else {
                mark = 'X';
            }
        }
    }

    // print method
    public static void printBoard(char[][] board) {
        // print 2D array
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j++) {
                // add left bar for column 1
                if(j == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    // check winner method
    public static boolean isWon(int row, int col, char[][] board) {
        
        // vertical, horizontal and diagonals match
        if(board[0][col] == board[1][col] && board[0][col] == board[2][col] && board[0][col] != '-') {
            return true;
        } else if(board[row][0] == board[row][1] && board[row][0] == board[row][2] && board[row][0] != '-') {
            return true;
        } else if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '-') {
            return true;
        } else if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != '-') {
            return true;
        } else {
            return false;
        }
    }
}