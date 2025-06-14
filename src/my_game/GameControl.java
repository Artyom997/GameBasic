package my_game;
import my_base.InVicinity;
import my_base.MyContent;
import my_game.MyCharacter1.MyDirection;
//import my_game.MyCharacter1;
//import my_game.MyCharacter1.MyDirection;
//import my_game.MyContent;
//import my_game.InVicinity;
import ui_elements.ScreenPoint;

public class GameControl {
	private MyContent content = new MyContent();
    MyCharacter1 char1 = content.character(1);
    MyCharacter1 char2 = content.character(2);
    // Returns the distance between two characters

	public GameControl() {
	}

	public void gameStep() {
		System.out.println("game step!!!!!!!!!!!!!!!!!!!!!!!!!");
		meleeControl(char1,char2);
		
		//update life bar
		//update score	
	}

	public void checkGameOver() {
		
	}
    public static boolean InVicinity(ScreenPoint p1, ScreenPoint p2) {
		System.out.println("check2!!!!!!!!!!!!!!!!!!!!!!!!!");
        int meleeRadius = 90; // Default collision radius
        double dx = p1.x - p2.x;
        double distance = Math.sqrt(dx * dx);
		System.out.println("check3!!!!!!!!!!!!!!!!!!!!!!!!!");
        return distance <= meleeRadius;
	
	}

    public static void meleeControl(MyCharacter1 char1, MyCharacter1 char2) {

		// Move according to policy
		System.out.println("check1!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(char1.getLocation(1).getX());
		System.out.println("char2 location="+char2.getLocation(2).getX());
		if (GameControl.InVicinity(char1.getLocation(1), char2.getLocation(2))&&
		(char1.getPolicy() == MyDirection.RIGHT||
		char2.getPolicy() == MyDirection.LEFT||
		char1.getPolicy() == MyDirection.STOP||char2.getPolicy() == MyDirection.STOP))
		{
			System.out.println("In Melee Range!!!!!!!!!!!!!!!!!!!!!!!!!");
			char1.setSpeed(0);
			char2.setSpeed(0);
			if(char1.getPolicy() == MyDirection.LEFT || char2.getPolicy() == MyDirection.RIGHT) {
				char1.setSpeed(2);
				char2.setSpeed(2);
			}
			else 
			{char1.setSpeed(2);
			char2.setSpeed(2);}
    	}
		System.out.println("check4!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}