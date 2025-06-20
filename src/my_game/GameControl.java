package my_game;
import my_base.InVicinity;
import my_base.LifeBar;
import my_base.MyContent;
import my_base.MyGame;
import my_base.MyKeyboardListener;
import my_base.MyMouseHandler;
import my_base.MyPeriodicLoop;
import my_base.PointsBar;
import my_game.MyCharacter1.MyDirection;
//import my_game.MyCharacter1;
//import my_game.MyCharacter1.MyDirection;
//import my_game.MyContent;
//import my_game.InVicinity;
import ui_elements.ScreenPoint;

public class GameControl {
	MyContent content = new MyContent();
	//private static InVicinity vicinity = new InVicinity();	
    MyCharacter1 char1 = content.character(1);
    MyCharacter1 char2 = content.character(2);
	LifeBar char1HP = content.life(1);
    LifeBar char2HP = content.life(2);
	PointsBar char1P = content.points(1);
    PointsBar char2P = content.points(2);
	static int leftBorder = 50;
	static int rightBorder = 850;


	public GameControl() {
	}

	public void gameStep(MyCharacter1 char1, MyCharacter1 char2, LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P) {
		distanceControl(char1,char2);
		borderControl(char1, char2);
		moveUpdate(char1, char2);
		commandUpdate(char1, char2);
		hitRegistration(char1, char2, char1HP, char2HP, char1P, char2P);
		checkGameOver();
	}

	public void checkGameOver() {

	}
	public static void moveUpdate(MyCharacter1 char1, MyCharacter1 char2) {
		javax.swing.SwingUtilities.invokeLater(() -> {
            char1.move(1, char1.getDirectionPolicy());
			char2.move(2, char2.getDirectionPolicy());
		});
	}
	public static void commandUpdate(MyCharacter1 char1, MyCharacter1 char2) {
		javax.swing.SwingUtilities.invokeLater(() -> {
            char1.command(1, char1.getCommandPolicy());
			char2.command(2, char2.getCommandPolicy());
		});
	}


    public static boolean InVicinity(ScreenPoint  p1, ScreenPoint p2) {
        int meleeRadius = 90; // Default collision radius
        double dx = p1.x - p2.x;
        double distance = Math.sqrt(dx * dx);
        return distance <= meleeRadius;
	}

    public static void distanceControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getDirectionPolicy() == MyDirection.RIGHT||
		char2.getDirectionPolicy() == MyDirection.LEFT||
		char1.getDirectionPolicy() == MyDirection.STOP||
		char2.getDirectionPolicy() == MyDirection.STOP))
		{
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getDirectionPolicy() == MyDirection.LEFT){
				char1.setSpeed(2);}
			else if (char2.getDirectionPolicy() == MyDirection.RIGHT){
				char2.setSpeed(2);}
			else{char1.setSpeed(0);
			char2.setSpeed(0);}
    	}
		else{char1.setSpeed(2);
			char2.setSpeed(2);}
	}
	
	public static void borderControl(MyCharacter1 char1, MyCharacter1 char2) {
		if (char1.getLocation(1).getX()<=leftBorder)
		{
			char1.setSpeed(0);
			if(char1.getDirectionPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
			}
		}
		if (char2.getLocation(2).getX()>=rightBorder)
		{
			char2.setSpeed(0);
			if(char2.getDirectionPolicy() == MyDirection.LEFT) {
				char2.setSpeed(2);
			}
		
		}
	}
	public static void hitRegistration(MyCharacter1 char1, MyCharacter1 char2,LifeBar char1HP, LifeBar char2HP, PointsBar char1P, PointsBar char2P) {
		if (InVicinity(char1.getLocation(1), char2.getLocation(2))) {
			if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch blocked!");
				char2P.increasePoints(50);
			} 

			else if (char1.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char2.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char1: Punch hit!");
				char1P.increasePoints(100);
				char2HP.decreaseLife(1);
			}

			if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() == MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch blocked!");
				char1P.increasePoints(50);
			}
			
			else if (char2.getCommandPolicy() == MyCharacter1.MyCommand.PUNCH &&
			 char1.getCommandPolicy() != MyCharacter1.MyCommand.BLOCK) {
				System.out.println("Char2: Punch hit!");
				char2P.increasePoints(100);
				char1HP.decreaseLife(1);
			}
		}
	}
}