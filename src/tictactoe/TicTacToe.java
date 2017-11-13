package tictactoe;

import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class TicTacToe {

    public static void main(String[] args) {
        Console con = new Console("TIC-TAC-TOE", 800, 600);

        //GENERAL VARIABLES
        int intRunning;
        int intMouseX;
        int intMouseY;
        int intMouseButton;
        int intScreen = 0;
        int intWinner;

        char chrKey;

        Color clrGray = new Color(204, 204, 204);
        Color clrGreen = new Color(146, 196, 125);
        Color clrDarkGray = new Color(102, 102, 102);
        Color clrBackground = new Color(249, 203, 154);
	
        BufferedImage imgCircle;
        BufferedImage imgCross;

        String strCirclePlayer = "";
        String strCrossPlayer = "";

        //MAIN MENU SCREEN
        BufferedImage imgMainMenu = con.loadImage("src/res/MainMenu.jpg");

        //SELECTION SCREEN
        int intPieceSelection = 0;

        //GAME SCREEN
        int intVarsInit;
        int intPlaced;
        int intFilledTiles;
        int intCurrentPlayer;
        int intCount;
        int intTileSelected;
        int intTileFilled;
        int intTile0, intTile1, intTile2, intTile3, intTile4,
                intTile5, intTile6, intTile7, intTile8;

        char chrLastKey = 0;

        Color clrBlue = new Color(107, 159, 235);
        Color clrRed = new Color(224, 102, 101);

        //START THE GAME
        intRunning = 1;
        con.setBackgroundColor(clrBackground);

        //MAIN GAME LOOP
        while(intRunning != 0){
            //UPDATE INPUT VARIABLES
            intMouseX = con.currentMouseX();
            intMouseY = con.currentMouseY();
            intMouseButton = con.currentMouseButton();
            chrKey = con.currentChar();

            //CLEAR THE SCREEN
            con.setDrawColor(clrBackground);
            con.fillRect(0, 0, 800, 600);
            
            //RUN SCREEN SPECIFIC CODE
            if(intScreen == 0){
                //DRAW BACKGROUND IMAGE
                con.drawImage(imgMainMenu, 0, 0);

                //CHECK IF MOUSE IS INSIDE OF THE PLAY BUTTON
                if((intMouseX > 250 && intMouseX < 545) &&(intMouseY > 290 && intMouseY < 345)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO SELECTION SCREEN
                        intScreen = 1;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(250, 290, 545 - 250, 345 - 290);
                con.setDrawColor(Color.BLACK);
                con.drawString("Play", 375, 325);

                //CHECK IF MOUSE IS INSIDE OF THE EXIT BUTTON
                if((intMouseX > 250 && intMouseX < 545) &&(intMouseY > 370 && intMouseY < 425)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //EXIT THE GAME
                        intRunning = 0;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(250, 370, 545 - 250, 425 - 370);
                con.setDrawColor(Color.BLACK);
                con.drawString("Exit", 375, 405);
            }else if(intScreen == 1){
                //DRAW INSTRUCTIONS AT THE TOP
                con.setDrawColor(Color.BLACK);
                con.drawString("Select a piece and enter the name of the Player for it", 90, 40);
                con.drawString("Press ENTER to Accept or ESC to Cancel", 165, 70);
                
                //CHECK IF NO PIECE IS SELECTED
                if(intPieceSelection == 0){
                    //CHECK IF MOUSE IS INSIDE OF THE BLUE CIRCLE SELECTION RECTANGLE
                    if((intMouseX > 75 && intMouseX < 365) &&(intMouseY > 95 && intMouseY < 485)){
                        //CHECK IF BUTTON BEING CLICKED
                        if(intMouseButton == 1){
                            con.setDrawColor(Color.WHITE);
                            //SETTING THE BLUE CIRCLE PIECE TO BE THE CURRENTLY SELECTED ONE
                            intPieceSelection = 1;
                        }else{
                            con.setDrawColor(clrGreen);
                        }
                    }else{
                        con.setDrawColor(clrGray);
                    }
                    con.fillRect(75, 95, 365 - 75, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CIRCLE PLAYER NAME
                    con.drawString(strCirclePlayer, 375, 165);
                //CHECK IF THE BLUE CIRCLE PIECE HAS BEEN SELECTED
                }else if(intPieceSelection == 1){
                    //MAKE SURE IT DOESNT DETECT KEYS THAT ARE HELD DOWN
                    if(chrKey != chrLastKey){
                        //CHECK IF THE KEY PRESSED IS A LETTER OR NUMBER USING ASCII CHARACTERS
                        if((chrKey >= 48 && chrKey <= 57) || (chrKey >= 65 && chrKey <= 90)
                                || (chrKey >= 97 && chrKey <= 122)){
                        	//DO NOT ADD LETTER TO NAME IF IT IS LONGER THAN 10 LETTERS
                        	if(strCirclePlayer.length() < 10){
                        		strCirclePlayer += chrKey;
                        	}
                        //CHECK IF THE KEY PRESSED IS THE BACKSPACE KEY
                        }else if(chrKey == 8){
                            //TAKE AWAY THE LAST LETTER FROM THE NAME
                            int intStrLength = strCirclePlayer.length() - 1;
                            if(intStrLength < 0){
                                intStrLength = 0;
                            }
                            strCirclePlayer = strCirclePlayer.substring(0, intStrLength);
                        //CHECK IF THE KEY PRESSED IS THE ENTER KEY
                        }else if(chrKey == 10){
                            intPieceSelection = 0;
                        //CHECK IF THE KEY PRESSED IS THE ESC KEY
                        }else if(chrKey == 27){
                            intPieceSelection = 0;
                            strCirclePlayer = "";
                        }
                        //SET LAST KEY TO THIS KEY
                        chrLastKey = chrKey;
                    }
                    con.setDrawColor(Color.WHITE);
                    con.fillRect(75, 95, 365 - 75, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CIRCLE PLAYER NAME
                    con.drawString(strCirclePlayer, 375, 165);
                //CHECK IF THE RED CROSS PIECE HAS BEEN SELECTED
                }else if(intPieceSelection == 2){
                    //MAKE SURE IT DOESNT DETECT KEYS THAT ARE HELD DOWN
                    if(chrKey != chrLastKey){
                        //CHECK IF THE KEY PRESSED IS A LETTER OR NUMBER USING ASCII CHARACTERS
                        if((chrKey >= 48 && chrKey <= 57) || (chrKey >= 65 && chrKey <= 90)
                                || (chrKey >= 97 && chrKey <= 122)){
                        	//DO NOT ADD LETTER TO NAME IF IT IS LONGER THAN 10 LETTERS
                        	if(strCirclePlayer.length() < 10){
                        		strCirclePlayer += chrKey;
                        	}
                        //CHECK IF THE KEY PRESSED IS THE BACKSPACE KEY
                        }else if(chrKey == 8){
                            //TAKE AWAY THE LAST LETTER FROM THE NAME
                            int intStrLength = strCrossPlayer.length() - 1;
                            if(intStrLength < 0){
                                intStrLength = 0;
                            }
                            strCrossPlayer = strCrossPlayer.substring(0, intStrLength);
                        //CHECK IF THE KEY PRESSED IS THE ENTER KEY
                        }else if(chrKey == 10){
                            intPieceSelection = 0;
                        //CHECK IF THE KEY PRESSED IS THE ESC KEY
                        }else if(chrKey == 27){
                            intPieceSelection = 0;
                            strCirclePlayer = "";
                        }
                        //SET LAST KEY TO THIS KEY
                        chrLastKey = chrKey;
                    }
                    con.setDrawColor(Color.WHITE);
                    con.fillRect(75, 95, 365 - 75, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CIRCLE PLAYER NAME
                    con.drawString(strCrossPlayer, 375, 165);
                }
            }
            
            //PAINT EVERYTHING TO THE SCREEN
            con.repaint();
            //SLEEP 5 MILLISECONDS BETWEEN LOOPS
            con.sleep(5);
        }
        
        con.closeConsole();
    }

}
