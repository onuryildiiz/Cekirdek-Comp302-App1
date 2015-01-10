package Core;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GameRules extends JFrame {
    /**
     *
     *
     *
     */
    private static final long serialVersionUID = 1L;
    static Lokum selectedLokum1;
    static Lokum selectedLokum2;
    public static Lokum temp;
    static GameBoard gameBoard;
    static SidePanel sidePanel;
    
    static boolean isDestroyed = false;
    static boolean gereksizIsDestroyed = false;
    static boolean coordinateChecker = false;
    static boolean checker = false;
    static boolean slided = false;
    static int forChecker = 0;
    static boolean specialComboVar=false;
    
    
    /**
     * @throws IOException
     * @Requires: Game has to be opened.
     * @Ensures : Adds gameBoard and SidePanel to our screen
     * @Modifies:
     */
    public GameRules() throws IOException {
        super("Chewy Lokum Legend");
        sidePanel = new SidePanel(this);
        gameBoard = new GameBoard(this);
        initGame();
        
        
    }
    
    public void initGame() throws IOException {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(gameBoard, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        requestFocus();
        setSize(875, 600);
        setLocation(getWidth() / 2 - 250, (getHeight() - 450) / 2);
        setResizable(false);
        start();
        destroy();
        SidePanel.currentScore=0;
        SidePanel.time=180;
    }
    
    static javax.swing.Timer t;
    
    public void start() {
        
        t = new javax.swing.Timer(1000, new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                GameBoard.repaintTable();
                gameOver();
                levelPassed();
                t.toString();
                slide();
                SidePanel.time--;
                
            }
            
        });
        t.start();
    }
    
    public Timer getTimer() {
        return t;
    }
    
    public static GameBoard getGameBoard() {
        return gameBoard;
    }
    
    /**
     *
     * @param selectedLokum1
     * @param selectedLokum2
     *
     * @Requires: Clicked to two lokums that are adjacent to each other
     * @Ensures : Changes two lokums places
     * @Modifies:
     */
    public static void swapLokums(Lokum selectedLokum1, Lokum selectedLokum2) {
        if (checkCoordinateLokums(selectedLokum1, selectedLokum2) == true) {
            switchLokums(selectedLokum1, selectedLokum2);
            SidePanel.moveCount--;			                           
        	System.out.println(SidePanel.moveCount);
            if ((Lokum.isSpecial(selectedLokum1)&&Lokum.isSpecial(selectedLokum2)) || selectedLokum1.getName()=="ColorBomb"||selectedLokum2.getName()=="ColorBomb") {
                specialCombos(selectedLokum1, selectedLokum2);
                
            }
            
            
            destroy();
         
            
            if (isDestroyed == false){
                GameBoard.tableBack(selectedLokum1, selectedLokum2);

            }
            
            	
          
        } 
        
       
    }
    
    public static void switchLokums(Lokum selectedLokum1, Lokum selectedLokum2) {
       
        int x = 0;
        int y = 0;
        String name = "";
        Icon icon = null;
        
        x = selectedLokum1.getXofLokum();
        y = selectedLokum1.getYofLokum();
        name = selectedLokum1.getName();
        icon = selectedLokum1.getIcon();
        
       
        selectedLokum1.setXofLokum(selectedLokum2.getXofLokum());
        selectedLokum1.setYofLokum(selectedLokum2.getYofLokum());
        selectedLokum1.setName(selectedLokum2.getName());
        selectedLokum1.setIcon(selectedLokum2.getIcon());
        selectedLokum2.setXofLokum(x);
        selectedLokum2.setYofLokum(y);
        selectedLokum2.setName(name);
        selectedLokum2.setIcon(icon);
    }
    
    /**
     *
     * @param selectedLokum1
     * @param selectedLokum2
     * @Requires: Two lokums switched
     * @Ensures : Checks whether its a appropriate move or not.
     * @Modifies: If true calls destroy method.
     */
    
    public static boolean checkCoordinateLokums(Lokum selectedLokum1,
                                               Lokum selectedLokum2) {
        
        coordinateChecker = false;
        
      
        if (selectedLokum1.getXofLokum() == selectedLokum2.getXofLokum()
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == 1)
            coordinateChecker = true;
        if (selectedLokum1.getXofLokum() == selectedLokum2.getXofLokum()
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == -1)
            coordinateChecker = true;
        if (selectedLokum1.getYofLokum() == selectedLokum2.getYofLokum()
            && selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == 1)
            coordinateChecker = true;
        if (selectedLokum1.getYofLokum() == selectedLokum2.getYofLokum()
            && selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == -1)
            coordinateChecker = true;
        if (selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == 1
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == 1)
            coordinateChecker = true;
        if (selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == 1
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == -1)
            coordinateChecker = true;
        if (selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == -1
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == 1)
            coordinateChecker = true;
        if (selectedLokum1.getXofLokum() - selectedLokum2.getXofLokum() == -1
            && selectedLokum1.getYofLokum() - selectedLokum2.getYofLokum() == -1)
            coordinateChecker = true;
        
       
        return coordinateChecker;
    }
    
    /**
     * @Requires: Two lokums switched and checkLokums returns true
     * @Ensures : Appropriate lokums will be deleted from our board.
     * @Modifies:
     */
    
    public static boolean destroy() {
        isDestroyed = false;
        
        destroyFiveVertical();
        
        destroyFiveHorizontal();
        
        destroyPackage1();
        
        destroyPackage2();
        
        destroyPackage3();
        
        destroyPackage4();
        
        destroyPackage5();
        
        destroyPackage6();
        
        destroyPackage7();
        
        destroyPackage8();
        
        destroyFourVertical();
        
        destroyFourHorizontal();
        
        destroyVertical();
        
        destroyHorizontal();
       
        if (isDestroyed == true) {
            slide();
            forChecker++;

        } else {
            forChecker = 0;
        }
        
        return isDestroyed;
    }
    
    /**
     * @Requires: lokumArray[][]!=null , ( lokumArray[][] has same Lokum 5
     * @Ensures : Appropriate lokums will be deleted from our board.
     * @Modifies:
     */
    
  public static Lokum[][] destroyVertical() {
        
        for (int k = 1; k <= GameBoard.row - 2; k++) {
            for (int l = 0; l <= GameBoard.column - 1; l++) {
                
                if (Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                      GameBoard.lokumArray[k + 1][l])
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k - 1][l])) {
                   
                    if(GameBoard.lokumArray[k-1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k - 1][l]);
                    if(GameBoard.lokumArray[k][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k][l]);
                    if(GameBoard.lokumArray[k+1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k + 1][l]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
            }
            
        }
		if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 60;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage1() {
       
        for (int i = 2; i <= 9; i++) {
            for (int j = 0; j <= 7; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i - 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i - 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 2]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j+2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 2]);
                    if(GameBoard.lokumArray[i][j+1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 1]);
                    if(GameBoard.lokumArray[i-1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 1][j]);
                    if(GameBoard.lokumArray[i-2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 2][j]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage2() {

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i + 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i + 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 2]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j+2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 2]);
                    if(GameBoard.lokumArray[i][j+2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 1]);
                    if(GameBoard.lokumArray[i+1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 1][j]);
                    if(GameBoard.lokumArray[i+2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 2][j]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage3() {

        for (int i = 0; i <= 7; i++) {
            for (int j = 2; j <= 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i + 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i + 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 2]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "Rose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j-2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 2]);
                    if(GameBoard.lokumArray[i][j-1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 1]);
                    if(GameBoard.lokumArray[i+1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 1][j]);
                    if(GameBoard.lokumArray[i+2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 2][j]);
                    
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage4() {

        for (int i = 2; i <= 9; i++) {
            for (int j = 2; j <= 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i - 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i - 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 2]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j-2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 2]);
                    if(GameBoard.lokumArray[i][j-1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 1]);
                    if(GameBoard.lokumArray[i-1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 1][j]);
                    if(GameBoard.lokumArray[i-2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 2][j]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage5() {

        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j <= 7; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i][j + 2]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i - 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i + 1][j]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j+2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 2]);
                    if(GameBoard.lokumArray[i][j+1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 1]);
                    if(GameBoard.lokumArray[i-1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 1][j]);
                    if(GameBoard.lokumArray[i+1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 1][j]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage6() {

        for (int i = 1; i <= 8; i++) {
            for (int j = 2; j <= 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i - 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i + 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 2]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i][j - 2].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 2]);
                    if(GameBoard.lokumArray[i][j - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 1]);
                    if(GameBoard.lokumArray[i-1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 1][j]);
                    if(GameBoard.lokumArray[i+1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 1][j]);
                    
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage7() {

        for (int i = 0; i <= 7; i++) {
            for (int j = 1; j <= 8; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i + 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i + 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 1]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    if(GameBoard.lokumArray[i+1][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 1][j]);
                    if(GameBoard.lokumArray[i+2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i + 2][j]);
                    if(GameBoard.lokumArray[i][j + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 1]);
                    if(GameBoard.lokumArray[i][j - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 1]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyPackage8() {

        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 8; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                      GameBoard.lokumArray[i - 1][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i - 2][j]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[i][j],
                                         GameBoard.lokumArray[i][j + 1]) == true) {
                    
                    if (GameBoard.lokumArray[i][j].getName() == "basicRose") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedRose().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedRose().getIcon());
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicPistachio") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedPistachio().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedPistachio().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedHazelnut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedHazelnut().getIcon());
                        
                    } else if (GameBoard.lokumArray[i][j].getName() == "basicCoconut") {
                        GameBoard.lokumArray[i][j].setName(WrappedLokum
                                                           .wrappedCoconut().getName());
                        GameBoard.lokumArray[i][j].setIcon(WrappedLokum
                                                           .wrappedCoconut().getIcon());
                        
                    }
                    
                    if(GameBoard.lokumArray[i][j - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j - 1]);
                    if(GameBoard.lokumArray[i][j + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[i][j + 1]);
                    if(GameBoard.lokumArray[i-2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 1][j]);
                    if(GameBoard.lokumArray[i-2][j].getName()!="ali")
                        explode(GameBoard.lokumArray[i - 2][j]);
                    
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
                
            }
            
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyHorizontal() {// System.out.println("123");
        
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j <= 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                      GameBoard.lokumArray[j][i + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i - 1]) == true) {

                    if(GameBoard.lokumArray[j][i].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i]);
                    if(GameBoard.lokumArray[j][i + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i + 1]);
                    if(GameBoard.lokumArray[j][i - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i - 1]);
                    

                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                }
            }
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 60;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyFiveVertical() {

        
        for (int k = 2; k <= GameBoard.row - 3; k++) {
            for (int l = 0; l <= GameBoard.column - 1; l++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                      GameBoard.lokumArray[k + 1][l]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k - 1][l]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k + 2][l]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k - 2][l]) == true) {
                    
                    GameBoard.lokumArray[k][l].setName(ColorBomb
                                                       .colorBomb().getName());
                    GameBoard.lokumArray[k][l].setIcon(ColorBomb.colorBomb()
                                                       .getIcon());
                    
                    if(GameBoard.lokumArray[k-1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k - 1][l]);
                    if(GameBoard.lokumArray[k+1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k + 1][l]);
                    if(GameBoard.lokumArray[k+2][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k + 2][l]);
                    if(GameBoard.lokumArray[k-2][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k - 2][l]);
                    
                    
                    
                    // slide();
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                }
            }
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyFiveHorizontal() {

        for (int i = 2; i <= 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                      GameBoard.lokumArray[j][i + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i + 2]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i - 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i - 2]) == true) {
                    
                    GameBoard.lokumArray[j][i].setName(ColorBomb.colorBomb()
                                                       .getName());
                    GameBoard.lokumArray[j][i].setIcon(ColorBomb.colorBomb()
                                                       .getIcon());
                    if(GameBoard.lokumArray[j][i + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i + 1]);
                    if(GameBoard.lokumArray[j][i - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i - 1]);
                    if(GameBoard.lokumArray[j][i + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i + 2]);
                    if(GameBoard.lokumArray[j][i - 2].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i - 2]);
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                }
            }
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 200;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyFourVertical() {

        
        for (int k = 1; k <= GameBoard.row - 3; k++) {
            for (int l = 0; l <= GameBoard.column - 1; l++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                      GameBoard.lokumArray[k + 1][l]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k - 1][l]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[k][l],
                                         GameBoard.lokumArray[k + 2][l]) == true) {
                    
                    if (GameBoard.lokumArray[k][l].getName() == "basicRose") {
                        GameBoard.lokumArray[k][l].setName(StripedLokum
                                                           .longitudinalStripedRose().getName());
                        GameBoard.lokumArray[k][l].setIcon(StripedLokum
                                                           .longitudinalStripedRose().getIcon());
                    }
                    if (GameBoard.lokumArray[k][l].getName() == "basicPistachio") {
                        GameBoard.lokumArray[k][l].setName(StripedLokum
                                                           .longitudinalStripedPistachio().getName());
                        GameBoard.lokumArray[k][l].setIcon(StripedLokum
                                                           .longitudinalStripedPistachio().getIcon());
                    }
                    if (GameBoard.lokumArray[k][l].getName() == "basicCoconut") {
                        GameBoard.lokumArray[k][l].setName(StripedLokum
                                                           .longitudinalStripedCoconut().getName());
                        GameBoard.lokumArray[k][l].setIcon(StripedLokum
                                                           .longitudinalStripedCoconut().getIcon());
                        
                    }
                    if (GameBoard.lokumArray[k][l].getName() == "basicHazelnut") {
                        GameBoard.lokumArray[k][l].setName(StripedLokum
                                                           .longitudinalStripedHazelnut().getName());
                        GameBoard.lokumArray[k][l].setIcon(StripedLokum
                                                           .longitudinalStripedHazelnut().getIcon());
                    }

                    if(GameBoard.lokumArray[k-1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k - 1][l]);
                    if(GameBoard.lokumArray[k+1][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k + 1][l]);
                    if(GameBoard.lokumArray[k+2][l].getName()!="ali")
                        explode(GameBoard.lokumArray[k + 2][l]);
                    
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    
                }
            }
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 120;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    public static Lokum[][] destroyFourHorizontal() {

        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                      GameBoard.lokumArray[j][i + 1]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i + 2]) == true
                    && Lokum.hasSameRoot(GameBoard.lokumArray[j][i],
                                         GameBoard.lokumArray[j][i - 1]) == true) {
                    
                    if (GameBoard.lokumArray[j][i].getName() == "Rose") {
                        GameBoard.lokumArray[j][i].setName(StripedLokum
                                                           .crossStripedRose().getName());
                        GameBoard.lokumArray[j][i].setIcon(StripedLokum
                                                           .crossStripedRose().getIcon());
                    }
                    if (GameBoard.lokumArray[j][i].getName() == "Pistachio") {
                        GameBoard.lokumArray[j][i].setName(StripedLokum
                                                           .crossStripedPistachio().getName());
                        GameBoard.lokumArray[j][i].setIcon(StripedLokum
                                                           .crossStripedPistachio().getIcon());
                    }
                    if (GameBoard.lokumArray[j][i].getName() == "Coconut") {
                        GameBoard.lokumArray[j][i].setName(StripedLokum
                                                           .crossStripedCoconut().getName());
                        GameBoard.lokumArray[j][i].setIcon(StripedLokum
                                                           .crossStripedCoconut().getIcon());
                        
                    }
                    if (GameBoard.lokumArray[j][i].getName() == "Hazelnut") {
                        GameBoard.lokumArray[j][i].setName(StripedLokum
                                                           .crossStripedHazelnut().getName());
                        GameBoard.lokumArray[j][i].setIcon(StripedLokum
                                                           .crossStripedHazelnut().getIcon());
                    }
                    
                    if(GameBoard.lokumArray[j][i + 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i + 1]);
                    if(GameBoard.lokumArray[j][i - 1].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i - 1]);
                    if(GameBoard.lokumArray[j][i + 2].getName()!="ali")
                        explode(GameBoard.lokumArray[j][i + 2]);
                    
                    isDestroyed = true;
                    gereksizIsDestroyed = true;
                    System.out.println("is destroyed:" + isDestroyed);
                    System.out.println("is gereksizDestroyed:"
                                       + gereksizIsDestroyed);
                }
            }
        }
        
        if (gereksizIsDestroyed == true) {
			SidePanel.currentScore = SidePanel.currentScore + 120;
			System.out.println(SidePanel.currentScore);
			gereksizIsDestroyed = false;
		}
        return GameBoard.lokumArray;
    }
    
    /**
     *
     * @Requires: Some lokums in the board is destroyed.
     * @Ensures : Checks whethere there is a null part of our array if so lokums
     *          that above of null parts will slides
     * @Modifies: It calls check lokums again for all board.
     */
    public static boolean isThereEmpty() {
        boolean returning = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (GameBoard.lokumArray[i][j].getName() == "ali")
                    returning = true;
            }
            
        }
        if (returning == false) {
            
        } else if (returning == true) {
            
        } else {
            
        }
        return returning;
    }
    
    public static void slide() {
        slided = false;
        while (isThereEmpty() == true) {
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < 10; j++) {
                    if (GameBoard.lokumArray[i][j].getName() == "ali") {
                        if (i == 0) {
                            GameBoard.replaceNewLokums();
                            destroy();
                        } else {
                            for (int k = i; k > 0; k--) {
                                if (GameBoard.lokumArray[k - 1][j].getName() != "ali") {
                                    GameBoard.lokumArray[k][j]
                                    .setName(GameBoard.lokumArray[k - 1][j]
                                             .getName());
                                    GameBoard.lokumArray[k][j]
                                    .setIcon(GameBoard.lokumArray[k - 1][j]
                                             .getIcon());
                                    GameBoard.lokumArray[k - 1][j]
                                    .setName("ali");
                                    GameBoard.lokumArray[k - 1][j]
                                    .setIcon(null);
                                    GameBoard.replaceNewLokums();
                                    slided = true;
                                    
                                }
                                
                            }
                            
                        }
                    }
                }
                
            }
        }
        
        if (slided == true)
            destroy();
        
    }
    
    /**
     * @Requires: There has been made moves in the game.
     * @Ensures : If move count is 0 the game is over and the game screen
     *          exited.
     * @Modifies:
     */
    public static boolean isGameOver() {
        if(SidePanel.moveCount ==0){
            return true;
            
            
        }
        return false;
        
    }
    
    public  void gameOver(){
        if(isGameOver()){
            int response = JOptionPane.showConfirmDialog(null, "The game is over!! Do you want to retry?", "Confirmation",
                                                         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
                System.exit(0);
            }
            else {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            GameBoard gameBoard = getGameBoard();
                            gameBoard.clearTable();
                            GameBoard.selectedLokum1 = null;
                            GameBoard.selectedLokum2 = null;
                            SidePanel.currentScore = 0;
                            SidePanel.moveCount = SidePanel.getMoveCount();
                            
                            gameBoard.fillTableWithLokum();
                            
                            initGame();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("The game is being restarted now");
                    }
                });
            }
            
            
        } else return;
        
    }
    
    public static boolean isLevelEnabled(){
		if(SidePanel.currentScore>=SidePanel.getTargetScore()){
			return true;
		}
		return false;
	}
	
	public  void levelPassed(){
		if(isLevelEnabled()){
			SidePanel.currentScore=0;
			JOptionPane.showMessageDialog(null,
				    "Congrulations You've passed the level: " + SidePanel.level,
				    "Level Passed",
				    JOptionPane.PLAIN_MESSAGE);
			GameBoard gameBoard = getGameBoard();
			gameBoard.clearTable();
			GameBoard.selectedLokum1 = null;
			GameBoard.selectedLokum2 = null;
			SidePanel.currentScore = 0;
			SidePanel.level++;
			SidePanel.moveCount = SidePanel.getMoveCount();
			SidePanel.getTargetScore();
			
			gameBoard.fillTableWithLokum();
		
			try {
				initGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	}

    
    /**
     * @Requires:
     * @Ensures :
     * @Modifies:
     */
	public static Lokum[][] explode(Lokum lokum1) {
		System.out.println("Destroyed Lokum : "
				+ lokum1.getName());
		if (Lokum.isSpecial(lokum1) == false) {

			lokum1.setName("ali");
			lokum1.setIcon(null);

		} 
		else if(SpecialLokum.isTimeBasedLokum(lokum1)){
			lokum1.setName("ali");
			lokum1.setIcon(null);
			SidePanel.time=SidePanel.time+5;
			////TIMER EKLENECEK 5 SANIYE ARTTIR
		}
		else if (SpecialLokum.isCrossStripedLokum(lokum1)) {
			

			int m = lokum1.getYofLokum();

			if (Lokum.hasSameRoot(lokum1, BasicLokum.coconut()))
				Lokum.transformLokums(lokum1, BasicLokum.coconut());

			else if (Lokum.hasSameRoot(lokum1, BasicLokum.rose()))
				Lokum.transformLokums(lokum1, BasicLokum.rose());

			else if (Lokum.hasSameRoot(lokum1, BasicLokum.pistachio()))
				Lokum.transformLokums(lokum1, BasicLokum.pistachio());

			else if (Lokum.hasSameRoot(lokum1, BasicLokum.hazelnut()))
				Lokum.transformLokums(lokum1, BasicLokum.hazelnut());
			
			for (int j = 0; j <10; j++) {
				if(GameBoard.lokumArray[j][m].getName()!="ali")
			explode(GameBoard.lokumArray[j][m]);
			
			}
		}
		else if (SpecialLokum.isLongitudinalStripedLokum(lokum1)) {

			int k = lokum1.getXofLokum();
			
			
			if (Lokum.hasSameRoot(lokum1, BasicLokum.coconut()))
				Lokum.transformLokums(lokum1, BasicLokum.coconut());
			if (Lokum.hasSameRoot(lokum1, BasicLokum.rose()))
				Lokum.transformLokums(lokum1, BasicLokum.rose());
			if (Lokum.hasSameRoot(lokum1, BasicLokum.pistachio()))
				Lokum.transformLokums(lokum1, BasicLokum.pistachio());
			if (Lokum.hasSameRoot(lokum1, BasicLokum.hazelnut()))
				Lokum.transformLokums(lokum1, BasicLokum.hazelnut());
			for (int i = 0; i < 10; i++) {
				if(GameBoard.lokumArray[k][i].getName()!="ali")
				explode(GameBoard.lokumArray[k][i]);

			}

		} else if (SpecialLokum.isWrappedLokum(lokum1)) {

			int i = lokum1.getXofLokum();
			int j = lokum1.getYofLokum();
			
//			for (int j2 = 0; j2 < 10; j2++) {
//				for (int k = 0; k < 10; k++) {
//					if(GameBoard.lokumArray[k][j2].getName().contains("Wrap")){
//						GameBoard.lokumArray[k][j2].setName("ali");
//						GameBoard.lokumArray[k][j2].setIcon(null);
//					}
//						
//				}
//			}
			GameBoard.lokumArray[j][i].setName("ali");
			GameBoard.lokumArray[j][i].setIcon(null);
			if(i<2)
				i=2;
			if(i>7)
				i=7;
			if(j<2)
				j=2;
				if(j>7)
					j=7;
				
				if(GameBoard.lokumArray[j+1][i].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j+1][i].getName()!="ali" && GameBoard.lokumArray[j+1][i].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j+1][i]);
				}
				
				if(GameBoard.lokumArray[j-1][i].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j-1][i].getName()!="ali" && GameBoard.lokumArray[j-1][i].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j-1][i]);
				}
				
			if(GameBoard.lokumArray[j+1][i+1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j+1][i+1].getName()!="ali"&& GameBoard.lokumArray[j+1][i+1].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j+1][i+1]);
			}
			
			if(GameBoard.lokumArray[j-1][i-1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j-1][i-1].getName()!="ali" && GameBoard.lokumArray[j-1][i-1].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j-1][i-1]);
			
			}
			
			if(GameBoard.lokumArray[j+1][i-1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j+1][i-1].getName()!="ali" && GameBoard.lokumArray[j+1][i-1].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j+1][i-1]);
			}
			
			if(GameBoard.lokumArray[j-1][i+1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j-1][i+1].getName()!="ali" && GameBoard.lokumArray[j-1][i+1].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j-1][i+1]);
			}
		
			if(GameBoard.lokumArray[j][i+1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j][i+1].getName()!="ali"&& GameBoard.lokumArray[j-1][i+1].getName()!="ColorBomb")
			explode(GameBoard.lokumArray[j][i+1]);
			}

			if(GameBoard.lokumArray[j][i-1].getName().contains("wrap")==false){
				if(GameBoard.lokumArray[j][i-1].getName()!="ali" && GameBoard.lokumArray[j][i-1].getName()!="ali")
					explode(GameBoard.lokumArray[j][i-1]);
			}
	

		 }
		else if(ColorBomb.isColorBomb(lokum1)==true){
			int lokumx=lokum1.getXofLokum();
			int lokumy=lokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicRose");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){
			if(GameBoard.lokumArray[i][j].getName()!="ali")
		explode(GameBoard.lokumArray[i][j]);




		}


		}

		}
		}
		return GameBoard.lokumArray;

	}



	public static boolean specialCombos(Lokum selectedLokum1, Lokum selectedLokum2) {

		boolean	specialComboVar=false;

		if(selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("basicPistachio")){
		
		int lokumx=selectedLokum1.getXofLokum();
		int lokumy=selectedLokum1.getYofLokum();
		
		GameBoard.lokumArray[lokumy][lokumx].setName("ali");
		GameBoard.lokumArray[lokumy][lokumx].setIcon(null);

		System.out.println("Color--->BasicPistachio");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("basicPistachio")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}

		}

		}


		}

		else if(selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("basicPistachio")){

		System.out.println("Color--->BasicPistachio");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Pistachio")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}


		}

		}

		}

		else if(selectedLokum1.getName().contains("ColorBomb")&& selectedLokum2.getName().contains("basicRose")){
		
			
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicRose");


		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}

		}

		}


		}

		else if(selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("basicRose")){

			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicRose");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}


		}

		}

		}

		else	 if(selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("basicCoconut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicCoconut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Coconut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}

		}

		}


		}

		else	if(selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("basicCoconut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicCoconut");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Coconut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}




		}

		}

		}

		else	if(selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("basicHazelnut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicHazelnut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}


		}

		}


		}

		else	if(selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("basicHazelnut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->BasicHazelnut");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;



		}


		}

		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("StripedPistachio")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->StripedPistachio");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Pistachio")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum2));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else	if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("StripedPistachio")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);

			
		System.out.println("Color--->StripedPistachio");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Pistachio")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum1));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("StripedRose")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->StripedRose");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum2));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}

		}




		}

		}

		else if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("StripedRose")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->StripedRose");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum1));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}


		}



		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("StripedHazelnut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->StripedHazelnut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum2));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}


		}



		}

		}

		else if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("StripedHazelnut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->StripedHazelnut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum1));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}

		}




		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("StripedCoconut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Coconut")){

		System.out.println("Color--->StripedCoconut");

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum2));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("StripedCoconut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Coconut")){

		Lokum.transformLokums(GameBoard.lokumArray[i][j], randomStripedLokumOfLokum(selectedLokum1));

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}

		}

		}




		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("wrappedPistachio")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedPistachio");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Pistachio")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}


		}



		}

		}

		else	if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("wrappedPistachio")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedPistachio");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Pistachio")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}

		}




		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("wrappedRose")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedRose");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("wrappedRose")){

			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);

		System.out.println("Color--->WrappedRose");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Rose")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("wrappedHazelnut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedHazelnut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;


		}

		}



		}

		}

		else if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("wrappedHazelnut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedHazelnut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Hazelnut")){

		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}


		}



		}

		}

		else if (selectedLokum1.getName().contains("ColorBomb") && selectedLokum2.getName().contains("wrappedCoconut")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedCoconut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("Coconut")){


		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}


		}



		}

		}

		else	if (selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("wrappedCoconut")){
			int lokumx=selectedLokum2.getXofLokum();
			int lokumy=selectedLokum2.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);


		System.out.println("Color--->WrappedCoconut");

		for (int i = 0; i < 10; i++) {

		for (int j = 0; j < 10; j++) {

		if(GameBoard.lokumArray[i][j].getName().contains("basicCoconut")){


		explode(GameBoard.lokumArray[i][j]);

		specialComboVar=true;

		}

		}




		}

		}


		else	 if(selectedLokum2.getName().contains("ColorBomb") && selectedLokum1.getName().contains("ColorBomb")){
			int lokumx=selectedLokum1.getXofLokum();
			int lokumy=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy][lokumx].setName("ali");
			GameBoard.lokumArray[lokumy][lokumx].setIcon(null);

			int lokumx1=selectedLokum1.getXofLokum();
			int lokumy1=selectedLokum1.getYofLokum();
			
			GameBoard.lokumArray[lokumy1][lokumx1].setName("ali");
			GameBoard.lokumArray[lokumy1][lokumx1].setIcon(null);


		System.out.println("Color--->Color");

		for (int i = 0; i <10; i++) {

		for (int j = 0; j < 10; j++) {
			GameBoard.lokumArray[i][j].setName("ali");
			GameBoard.lokumArray[i][j].setIcon(null);

		


		}

		}

		}
		stripedStriped(selectedLokum1,selectedLokum2);
		stripedWrapped(selectedLokum1,selectedLokum2);
		wrappedWrapped(selectedLokum1,selectedLokum2);
		
		return specialComboVar;
	}

    
    public static boolean stripedStriped(Lokum lokum1, Lokum lokum2) {
        
        if(StripedLokum.isStripedLokum(lokum1)==true && StripedLokum.isStripedLokum(lokum2)==true){
            if (SpecialLokum.isCrossStripedLokum(lokum1))
                System.out.println("lokum1 cross");
            if (SpecialLokum.isLongitudinalStripedLokum(lokum1))
                System.out.println("lokum1 long");
            if (SpecialLokum.isCrossStripedLokum(lokum2))
                System.out.println("lokum2 cross");
            if (SpecialLokum.isLongitudinalStripedLokum(lokum2))
                System.out.println("lokum2 long");
            int i = lokum2.getXofLokum();
            int j = lokum2.getYofLokum();
            int k = lokum1.getXofLokum();
            int l = lokum1.getYofLokum();
            GameBoard.lokumArray[i][j].setIcon(null);
            GameBoard.lokumArray[k][l].setIcon(null);
            GameBoard.lokumArray[i][j].setName("ali");
            GameBoard.lokumArray[k][l].setName("ali");
            for (i = 0; i <= GameBoard.row - 1; i++) {
                if (GameBoard.lokumArray[i][j].getName() != "ali"
                    && GameBoard.lokumArray[i][j].getIcon() != null) {
                    System.out.println("explode'tan once");
                    explode(GameBoard.lokumArray[i][j]);
                    System.out.println("explode'tan sonra");
                }
            }
            i = lokum1.getXofLokum();
            j = lokum1.getYofLokum();
            for (j = 0; j <= GameBoard.column - 1; j++) {
                if (GameBoard.lokumArray[i][j].getName() != "ali"
                    && GameBoard.lokumArray[i][j].getIcon() != null) {
                    explode(GameBoard.lokumArray[i][j]);
                    specialComboVar=true;
                }
            }
        }
        return specialComboVar;
    }
    
    public static boolean stripedWrapped(Lokum stripedLokum, Lokum wrappedLokum) {
        if((StripedLokum.isStripedLokum(stripedLokum)==true && StripedLokum.isWrappedLokum(wrappedLokum)==true) || (StripedLokum.isStripedLokum(wrappedLokum)==true && StripedLokum.isWrappedLokum(stripedLokum)==true)){
            int i = stripedLokum.getXofLokum();
            int j = stripedLokum.getYofLokum();
            temp = stripedLokum;
            stripedLokum = wrappedLokum;
            wrappedLokum = temp;
            if(i>7)
                i=7;
            else if(i<2)
                i=2;
            if(j>7)
                j=7;
            if(j<2)
                j=2;
            
            for (i = 0; i <= GameBoard.row - 1; i++) {
                explode(GameBoard.lokumArray[i][j - 1]);
                explode(GameBoard.lokumArray[i][j]);
                explode(GameBoard.lokumArray[i][j + 1]);
                specialComboVar=true;
            }
            i = stripedLokum.getXofLokum();
            j = stripedLokum.getYofLokum();
            for (j = 0; j <= GameBoard.column - 1; j++) {
                explode(GameBoard.lokumArray[i - 1][j]);
                // System.out.println("malak:"+ i);
                // System.out.println("malak22:"+ j);
                explode(GameBoard.lokumArray[i][j]);
                explode(GameBoard.lokumArray[i + 1][j]);
            }
        }
        return specialComboVar;
    }
    
    public static Lokum randomStripedLokumOfLokum(Lokum lokum) {
        int randomix = (int) (Math.random() * 2 + 1);
        
        if (randomix == 1) {
            if (lokum.getName() == "Coconut"
                || lokum.getName() == "crossStripedCoconut"
                || lokum.getName() == "longitudinalStripedCoconut")
                temp = StripedLokum.crossStripedCoconut();
            if (lokum.getName() == "Hazelnut"
                || lokum.getName() == "crossStripedHazelnut"
                || lokum.getName() == "longitudinalStripedHazelnut")
                temp = StripedLokum.crossStripedHazelnut();
            if (lokum.getName() == "Rose"
                || lokum.getName() == "crossStripedRose"
                || lokum.getName() == "longitudinalStripedRose")
                temp = StripedLokum.crossStripedRose();
            if (lokum.getName() == "Pistachio"
                || lokum.getName() == "crossStripedPistachio"
                || lokum.getName() == "longitudinalStripedPistachio")
                temp = StripedLokum.crossStripedPistachio();
        } else {
            if (lokum.getName() == "Coconut"
                || lokum.getName() == "crossStripedCoconut"
                || lokum.getName() == "longitudinalStripedCoconut")
                temp = StripedLokum.longitudinalStripedCoconut();
            if (lokum.getName() == "Hazelnut"
                || lokum.getName() == "crossStripedHazelnut"
                || lokum.getName() == "longitudinalStripedHazelnut")
                temp = StripedLokum.longitudinalStripedHazelnut();
            if (lokum.getName() == "Rose"
                || lokum.getName() == "crossStripedRose"
                || lokum.getName() == "longitudinalStripedRose")
                temp = StripedLokum.longitudinalStripedRose();
            if (lokum.getName() == "Pistachio"
                || lokum.getName() == "crossStripedPistachio"
                || lokum.getName() == "longitudinalStripedPistachio")
                temp = StripedLokum.longitudinalStripedPistachio();
        }
        
        return temp;
    }
    
    
    public static boolean wrappedWrapped(Lokum lokum1, Lokum lokum2) {
        if(WrappedLokum.isWrappedLokum(lokum1)==true && WrappedLokum.isWrappedLokum(lokum2)==true){
            
            int i = lokum1.getXofLokum();
            int j = lokum1.getYofLokum();
            if(i<2)
                i=2;
            if(i>7)
                i=7;
            if(j<2)
                j=2;
            if(j>7)
                j=7;
            for (int k = i - 2; k <= i + 2; k++) {
                for (int l = j - 2; l <= j + 2; l++) {
                    if (GameBoard.lokumArray[k][l].getIcon() != null)
                        Lokum.transformLokums(GameBoard.lokumArray[k][l], lokum1);
                    explode(GameBoard.lokumArray[k][l]);
                }
            }
            int k = lokum1.getXofLokum();
            int l = lokum1.getYofLokum();
            GameBoard.lokumArray[i][j].setIcon(null);
            GameBoard.lokumArray[k][l].setIcon(null);
            GameBoard.lokumArray[i][j].setName("ali");
            GameBoard.lokumArray[k][l].setName("ali");
            specialComboVar=true;
        }
        return specialComboVar;
    }
    
    

    
    public String toString() {
        String ali = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ali = ali + " " + GameBoard.lokumArray[i][j];
            }
        }
        return ali;
    }
    
    /**
     * @return
     * @Requires:
     * @Ensures :
     * @Modifies:
     */
    public static boolean repOK() {
        if (selectedLokum1 == null)
            return false;
        if (selectedLokum2 == null)
            return false;
        
        if (gameBoard == null)
            return false;
        if (sidePanel == null)
            return false;
        
        return true;
    }
    
}