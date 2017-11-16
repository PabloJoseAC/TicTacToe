package tictactoe;

import arc.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class TicTacToe {

    public static void main(String[] args) {
        Console con = new Console("TIC-TAC-TOE", 800, 600);

        //GENERAL VARIABLES
        int intRunning;
        int intMouseX;
        int intMouseY;
        int intMouseButton;
        int intSwapScreen = 2;
        int intScreen = 0;
        int intWinner = 0;

        char chrKey;
        
        Color clrGray = new Color(204, 204, 204);
        Color clrGreen = new Color(146, 196, 125);
        Color clrDarkGray = new Color(102, 102, 102);
        Color clrBackground = new Color(249, 203, 154);
	
        BufferedImage imgCircle1 = con.loadImage("src/res/circles/Circle1.png");
        BufferedImage imgCircle2 = con.loadImage("src/res/circles/Circle2.png");
        BufferedImage imgCircle3 = con.loadImage("src/res/circles/Circle3.png");
        BufferedImage imgCircle4 = con.loadImage("src/res/circles/Circle4.png");
        BufferedImage imgCross1 = con.loadImage("src/res/crosses/Cross1.png");
        BufferedImage imgCross2 = con.loadImage("src/res/crosses/Cross2.png");
        BufferedImage imgCross3 = con.loadImage("src/res/crosses/Cross3.png");
        BufferedImage imgCross4 = con.loadImage("src/res/crosses/Cross4.png");

        String strCirclePlayer = "Player 1";
        String strCrossPlayer = "Player 2";

        Font fntNormal = con.loadFont("src/res/sodorb.ttf", 24);
        Font fntMedium = con.loadFont("src/res/sodorb.ttf", 36);
        Font fntBig = con.loadFont("src/res/sodorb.ttf", 350);

        //MAIN MENU SCREEN
        BufferedImage imgMainMenu = con.loadImage("src/res/MainMenu.jpg");

        //SELECTION SCREEN
        int intPieceSelection = 0;

        //GAME SCREEN
        int intVarsInit = 0;
        int intPlaced = 0;
        int intFilledTiles = 0;
        int intCurrentPlayer = 0;
        int intCount;
        int intTileSelected = 0;
        int intTileFilled = 0;
        int intTile0 = 0, intTile1 = 0, intTile2 = 0, intTile3 = 0, intTile4 = 0,
                intTile5 = 0, intTile6 = 0, intTile7 = 0, intTile8 = 0;

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

            //ONLY SWAP SCREENS IF THE MOUSE BUTTON HAS BEEN RELEASED.
            //THIS ALSO AVOIDS ACCIDENTALY PRESSING A BUTTON ON THE NEXT SCREEN
            while(intScreen != intSwapScreen){
                intMouseButton = con.currentMouseButton();
                if(intMouseButton == 0){
                    intScreen = intSwapScreen;
                }
            }

            //SET THE DEFAULT FONT
            con.setDrawFont(fntNormal);

            //CLEAR THE SCREEN
            con.setDrawColor(clrBackground);
            con.fillRect(0, 0, 800, 600);
            
            //RUN SCREEN SPECIFIC CODE
            if(intScreen == 0){
                //DRAW BACKGROUND IMAGE
                con.drawImage(imgMainMenu, 0, 0);

                //CHECK IF MOUSE IS INSIDE OF THE PLAY BUTTON
                if((intMouseX > 250 && intMouseX < 545) && (intMouseY > 290 && intMouseY < 345)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO SELECTION SCREEN
                        intSwapScreen = 1;
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
                if((intMouseX > 250 && intMouseX < 545) && (intMouseY > 370 && intMouseY < 425)){
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
                    //CHECK IF MOUSE IS INSIDE OF THE BACK BUTTON
                    if((intMouseX > 180 && intMouseX < 380) && (intMouseY > 500 && intMouseY < 570)){
                        //CHECK IF BUTTON BEING CLICKED
                        if(intMouseButton == 1){
                            con.setDrawColor(Color.WHITE);
                            //CHANGING TO MAIN MENU SCREEN
                            intSwapScreen = 0;
                        }else{
                            con.setDrawColor(clrGreen);
                        }
                    }else{
                        con.setDrawColor(clrGray);
                    }
                    con.fillRect(180, 500, 380 - 180, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Back", 255, 543);

                    //CHECK IF BOTH PLAYERS HAVE ENTERED THEIR NAMES
                    if(!strCirclePlayer.equals("") && !strCrossPlayer.equals("")){
                        //CHECK IF MOUSE IS INSIDE OF THE CONTINUE BUTTON
                        if((intMouseX > 400 && intMouseX < 600) && (intMouseY > 500 && intMouseY < 570)){
                            //CHECK IF BUTTON BEING CLICKED
                            if(intMouseButton == 1){
                                con.setDrawColor(Color.WHITE);
                                //CHANGING TO GAME SCREEN
                                intSwapScreen = 2;
                            }else{
                                con.setDrawColor(clrGreen);
                            }
                        }else{
                            con.setDrawColor(clrGray);
                        }
                    }else{
                        con.setDrawColor(clrDarkGray);
                    }
                    con.fillRect(400, 500, 600 - 400, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Continue", 450, 543);

                    //SET NAME FONT
                    con.setDrawFont(fntMedium);

                    //CHECK IF MOUSE IS INSIDE OF THE BLUE CIRCLE SELECTION RECTANGLE
                    if((intMouseX > 75 && intMouseX < 365) && (intMouseY > 95 && intMouseY < 485)){
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
                    con.drawString(strCirclePlayer, 200 - (strCirclePlayer.length() * 7), 165);

                    //CHECK IF MOUSE IS INSIDE OF THE RED CROSS SELECTION RECTANGLE
                    if((intMouseX > 415 && intMouseX < 705) && (intMouseY > 95 && intMouseY < 485)){
                        //CHECK IF BUTTON BEING CLICKED
                        if(intMouseButton == 1){
                            con.setDrawColor(Color.WHITE);
                            //SETTING THE RED CROSS PIECE TO BE THE CURRENTLY SELECTED ONE
                            intPieceSelection = 2;
                        }else{
                            con.setDrawColor(clrGreen);
                        }
                    }else{
                        con.setDrawColor(clrGray);
                    }
                    con.fillRect(415, 95, 705 - 415, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CROSS PLAYER NAME
                    con.drawString(strCrossPlayer, 540 - (strCrossPlayer.length() * 7), 165);
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
                    //SET NAME FONT
                    con.setDrawFont(fntMedium);

                    con.setDrawColor(Color.WHITE);
                    con.fillRect(75, 95, 365 - 75, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CIRCLE PLAYER NAME
                    con.drawString(strCirclePlayer, 200 - (strCirclePlayer.length() * 7), 165);

                    //DRAW RED CROSS PIECE STUFF
                    con.setDrawColor(clrGray);
                    con.fillRect(415, 95, 705 - 415, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CROSS PLAYER NAME
                    con.drawString(strCrossPlayer, 540 - (strCrossPlayer.length() * 7), 165);
                    
                    //SET DEFAULT FONT
                    con.setDrawFont(fntNormal);

                    //DRAW BACK BUTTON
                    con.setDrawColor(clrGray);
                    con.fillRect(180, 500, 380 - 180, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Back", 255, 543);

                    //DRAW CONTINUE BUTTON
                    con.setDrawColor(clrDarkGray);
                    con.fillRect(400, 500, 600 - 400, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Continue", 450, 543);
                //CHECK IF THE RED CROSS PIECE HAS BEEN SELECTED
                }else if(intPieceSelection == 2){
                    //MAKE SURE IT DOESNT DETECT KEYS THAT ARE HELD DOWN
                    if(chrKey != chrLastKey){
                        //CHECK IF THE KEY PRESSED IS A LETTER OR NUMBER USING ASCII CHARACTERS
                        if((chrKey >= 48 && chrKey <= 57) || (chrKey >= 65 && chrKey <= 90)
                                || (chrKey >= 97 && chrKey <= 122)){
                        	//DO NOT ADD LETTER TO NAME IF IT IS LONGER THAN 10 LETTERS
                        	if(strCrossPlayer.length() < 10){
                        		strCrossPlayer += chrKey;
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
                            strCrossPlayer = "";
                        }
                        //SET LAST KEY TO THIS KEY
                        chrLastKey = chrKey;
                    }
                    //SET NAME FONT
                    con.setDrawFont(fntMedium);

                    //DRAW RED CROSS PIECE STUFF
                    con.setDrawColor(Color.WHITE);
                    con.fillRect(415, 95, 705 - 415, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CROSS PLAYER NAME
                    con.drawString(strCrossPlayer, 540 - (strCrossPlayer.length() * 7), 165);

                    //DRAW BLUE CIRCLE PIECE STUFF
                    con.setDrawColor(clrGray);
                    con.fillRect(75, 95, 365 - 75, 485 - 95);
                    con.setDrawColor(Color.BLACK);
                    //DRAW CIRCLE PLAYER NAME
                    con.drawString(strCirclePlayer, 200 - (strCirclePlayer.length() * 7), 165);
                    
                    //SET DEFAULT FONT
                    con.setDrawFont(fntNormal);

                    //DRAW BACK BUTTON
                    con.setDrawColor(clrGray);
                    con.fillRect(180, 500, 380 - 180, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Back", 255, 543);

                    //DRAW CONTINUE BUTTON
                    con.setDrawColor(clrDarkGray);
                    con.fillRect(400, 500, 600 - 400, 570 - 500);
                    con.setDrawColor(Color.BLACK);
                    con.drawString("Continue", 450, 543);
                }
                //DRAW CIRCLE AND CROSS
                con.drawImage(imgCircle3, 33, 180);
                con.drawImage(imgCross3, 373, 180);
            }else if(intScreen == 2){
                //INITIALIZE GAME VARIABLES
                if(intVarsInit == 0){
                    System.out.println("GAME ON");
                    intWinner = 0;
                    intFilledTiles = 0;
                    intCurrentPlayer = 0;
                    intTile0 = 0;
                    intTile1 = 0;
                    intTile2 = 0;
                    intTile3 = 0;
                    intTile4 = 0;
                    intTile5 = 0;
                    intTile6 = 0;
                    intTile7 = 0;
                    intTile8 = 0;

                    intVarsInit = 1;
                }
                
                //SELECT WHO'S TURN IT IS AFTER THE OTHER PLAYER'S TURN IS OVER
                if(intCurrentPlayer > 0){
                    if(intPlaced == 1){
                        if(intCurrentPlayer >= 2){
                            intCurrentPlayer = 1;
                        }else{
                            intCurrentPlayer++;
                        }
                        intPlaced = 0;
                    }
                }else{
                    intCurrentPlayer = (int) (Math.random() * 2) + 1;
                }

                //DRAW CURRENT PLAYER RECTANGLE WITH THE CORRECT PLAYER COLOR
                if(intCurrentPlayer == 1){
                    con.setDrawColor(clrBlue);
                }else{
                    con.setDrawColor(clrRed);
                }
                con.fillRoundRect(315, 20, 475 - 315, 75 - 20, 10, 10);
                //DRAW PLAYER NAME
                con.setDrawColor(Color.BLACK);
                if(intCurrentPlayer == 1){
                    con.drawString(strCirclePlayer, 395 - (strCirclePlayer.length() * 6), 55);
                }else{
                    con.drawString(strCrossPlayer, 395 - (strCrossPlayer.length() * 6), 55);
                }

                //DRAW BLACK SQUARE FOR THE BOARD
                con.setDrawColor(Color.BLACK);
                con.fillRect(135, 90, 660 - 135, 575 - 90);

                //CHECK IF MOUSE IS INSIDE OF THE EXIT BUTTON
                if((intMouseX > 20 && intMouseX < 120) && (intMouseY > 25 && intMouseY < 70)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        //RESET VARIABLES
                        intVarsInit = 0;
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO MAIN MENU SCREEN
                        intSwapScreen = 0;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(20, 25, 120 - 20, 70 - 25);
                con.setDrawColor(Color.BLACK);
                con.drawString("Exit", 45, 55);

                //LOOP THROUGH TILES
                int intDefaultX = 160;
                int intDefaultY = 100;
                int intX;
                int intY;
                int intCountX = 0;
                int intCountY = 0;
                for(intCount = 0; intCount < 9; intCount++){
                    //SET X AND Y COUNTERS
                    if(intCountX >= 3){
                        intCountX = 0;
                        intCountY++;
                    }

                    //SPECIFY NEW X AND Y FOR THIS TILE
                    intX = intDefaultX + (intCountX * (310 - 160)) + (10 * intCountX);
                    intY = intDefaultY + (intCountY * (250 - 100)) + (5 * intCountY);

                    //SET DEFAULT DRAW COLOR
                    con.setDrawColor(Color.WHITE);

                    //FIND THE CURRENT TILE
                    if(intCount == 0){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile0 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile0 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 1){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile1 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile1 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 2){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile2 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile2 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 3){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile3 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile3 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 4){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile4 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile4 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 5){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile5 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile5 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 6){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile6 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile6 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 7){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile7 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile7 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }else if(intCount == 8){
                        //CHECK IF THE TILE HAS ALREADY BEEN FILLED
                        if(intTile8 > 0){
                            //INCREASE NUMBER OF FILLED TILES
                            intFilledTiles++;
                            con.setDrawColor(clrGray);
                            intTileFilled = 1;
                        //CHECK IF MOUSE IS INSIDE OF THE TILE
                        }else if(intMouseX > intX && intMouseX < intX + (310 - 160)
                                && intMouseY > intY && intMouseY < intY + (250 - 100)){
                            //CHECK IF LEFT MOUSE BUTTON IS PRESSED
                            if(intMouseButton == 1){
                                //PLACE PIECE ON TILE
                                intTile8 = intCurrentPlayer;
                                intPlaced = 1;
                            }else{
                                con.setDrawColor(clrGreen);
                                intTileSelected = 1;
                            }
                        }
                    }
                    
                    //DRAW CURRENT TILE
                    con.fillRect(intX, intY, 310 - 160, 250 - 100);

                    //DRAW SMALL PIECE IF TILE IS BEING HOVERED OVER
                    if(intTileSelected == 1){
                        intTileSelected = 0;
                        //CHECK WHO'S PLAYER TURN IT IS
                        if(intCurrentPlayer == 1){
                            con.drawImage(imgCircle1, intX, intY + 20);
                        }else{
                            con.drawImage(imgCross1, intX + 5, intY + 20);
                        }
                    }

                    //DRAW BIG PIECE IF TILE IS FILLED
                    if(intTileFilled == 1){
                        intTileFilled = 0;
                        //CHECK WHAT PIECE IS IN THIS TILE AND DRAW IT
                        if(intCount == 0){
                            if(intTile0 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile0 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 1){
                            if(intTile1 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile1 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 2){
                            if(intTile2 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile2 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 3){
                            if(intTile3 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile3 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 4){
                            if(intTile4 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile4 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 5){
                            if(intTile5 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile5 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 6){
                            if(intTile6 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile6 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 7){
                            if(intTile7 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile7 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }else if(intCount == 8){
                            if(intTile8 == 1){
                                con.drawImage(imgCircle2, intX - 44, intY - 14);
                            }else if(intTile8 == 2){
                                con.drawImage(imgCross2, intX - 43, intY - 13);
                            }
                        }
                    }

                    //INCREASE X COUNT
                    intCountX++;
                }

                //CHECK IF A TILE HAS BEEN PLACED
                if(intPlaced == 1){
                    //LOOP THROUGH THE 2 PLAYERS CHECKING IF THERE ARE 3 OF THE SAME PIECES IN A ROW
                    for(intCount = 1; intCount <= 2; intCount++){
                        if(intTile0 == intCount && intTile1 == intCount
                                && intTile2 == intCount){
                            intWinner = intCount;
                        }else if(intTile3 == intCount && intTile4 == intCount
                                && intTile5 == intCount){
                            intWinner = intCount;
                        }else if(intTile6 == intCount && intTile7 == intCount
                                && intTile8 == intCount){
                            intWinner = intCount;
                        }else if(intTile0 == intCount && intTile3 == intCount
                                && intTile6 == intCount){
                            intWinner = intCount;
                        }else if(intTile1 == intCount && intTile4 == intCount
                                && intTile7 == intCount){
                            intWinner = intCount;
                        }else if(intTile2 == intCount && intTile5 == intCount
                                && intTile8 == intCount){
                            intWinner = intCount;
                        }else if(intTile0 == intCount && intTile4 == intCount
                                && intTile8 == intCount){
                            intWinner = intCount;
                        }else if(intTile2 == intCount && intTile4 == intCount
                                && intTile6 == intCount){
                            intWinner = intCount;
                        }
                    }
                }
                
                //IF THERE'S A WINNER, SWAP TO THE GAME OVER SCREEN
                if(intWinner > 0){
                    intSwapScreen = 4;
                    intVarsInit = 0;
                //IF THERE IS NO WINNER BUT ALL TILES ARE FILLED, GO TO THE TIE SCREEN
                }else if(intFilledTiles >= 9){
                    intSwapScreen = 3;
                    intVarsInit = 0;
                    con.sleep(1000);
                }else{
                    //RESET FILLED TILES VARIABLE
                    intFilledTiles = 0;
                }
            }else if(intScreen == 3){
                //DRAW BACKGROUND CIRCLE AND CROSS
                con.drawImage(imgCross4, 250, 95);
                con.drawImage(imgCircle4, -30, 95);
                //DRAW TIE WITH ENLARGED FONT
                con.setDrawFont(fntBig);
                con.setDrawColor(Color.WHITE);
                con.drawString("TIE", 120, 420);
                //REPAINT AHEAD OF TIME
                con.repaint();
                //GO BACK TO THE GAME SCREEN AFTER 3 SECONDS
                intSwapScreen = 2;
                con.sleep(3000);
            }else if(intScreen == 4){
                //SET MEDIUM FONT
                con.setDrawFont(fntMedium);
                //DRAW CIRCLE OR CROSS AND THE NAME OF THE PLAYER WHO WON
                con.setDrawColor(Color.BLACK);
                if(intWinner == 1){
                    con.drawImage(imgCircle4, 120, -50);
                    con.drawString(strCirclePlayer + " Wins!", 360 - ((strCirclePlayer.length() + 6) * 6), 160);
                }else if(intWinner == 2){
                    con.drawImage(imgCross4, 130, -50);
                    con.drawString(strCrossPlayer + " Wins!", 360 - ((strCirclePlayer.length() + 6) * 6), 160);
                }

                //SET DEFAULT FONT
                con.setDrawFont(fntNormal);

                //CHECK IF MOUSE IS INSIDE OF THE PLAY AGAIN BUTTON
                if((intMouseX > 240 && intMouseX < 555) && (intMouseY > 290 && intMouseY < 360)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO GAME SCREEN
                        intSwapScreen = 2;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(240, 290, 555 - 240, 360 - 290);
                con.setDrawColor(Color.BLACK);
                con.drawString("Play Again", 335, 335);

                //CHECK IF MOUSE IS INSIDE OF THE BACK TO PLAYER SELECTION BUTTON
                if((intMouseX > 240 && intMouseX < 555) && (intMouseY > 390 && intMouseY < 460)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO SELECTION SCREEN
                        intSwapScreen = 1;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(240, 390, 555 - 240, 460 - 390);
                con.setDrawColor(Color.BLACK);
                con.drawString("Back To Player Selection", 258, 435);

                //CHECK IF MOUSE IS INSIDE OF THE BACK TO MAIN MENU BUTTON
                if((intMouseX > 240 && intMouseX < 555) && (intMouseY > 490 && intMouseY < 560)){
                    //CHECK IF BUTTON BEING CLICKED
                    if(intMouseButton == 1){
                        con.setDrawColor(Color.WHITE);
                        //CHANGING TO MAIN MENU SCREEN
                        intSwapScreen = 0;
                    }else{
                        con.setDrawColor(clrGreen);
                    }
                }else{
                    con.setDrawColor(clrGray);
                }
                con.fillRect(240, 490, 555 - 240, 560 - 490);
                con.setDrawColor(Color.BLACK);
                con.drawString("Back To Main Menu", 288, 535);
            }
            
            //PAINT EVERYTHING TO THE SCREEN
            con.repaint();
            //SLEEP 5 MILLISECONDS BETWEEN LOOPS
            con.sleep(5);
        }
        
        con.closeConsole();
    }

}
