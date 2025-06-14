package my_base;


import my_game.Pokimon;
//import ui_elements.ScreenPoint;
import base.Game;
import base.GameCanvas;
import base.GameContent;
import base.GameUI;
import my_game.MyCharacter1;
import my_game.MyPolygon;
import my_game.GameControl;
import my_base.InVicinity;

public class MyContent extends GameContent{
	private Pokimon pokimon;
	private MyPolygon myPolygon;
	private GameControl control;
	private MyCharacter1 ryu;
	private MyCharacter1 ermak;
	
	
	//TODO
	//Declare your own character
	

	@Override
	public void initContent() {
		pokimon = new Pokimon();
		//Create an instance of your character and set its properties with
		//initial values

		ryu = new MyCharacter1(1);
		ermak = new MyCharacter1(2);
		control = new GameControl();
		//ryu.initImage();
	}	
	
	public Pokimon pokimon() {
		return pokimon;
	}
	public GameControl control() {
		return control;
	}
	public MyCharacter1 character(int index) {
		if (index == 1) {
			return ryu;
		} 
		else
			return ermak;
		
	}

	public void addCharacter(int index) { //AVI + DAVID
		ryu.addToCanvas(index);
	}
	public void changeCharacter() {
		//TODO
		ryu.changeImage();
		//Create an instance of your character and set its properties with
		//new values.
	}
	public void changeLocation() { //David+Artyom
		//TODO
		//ryu.changeLocation();
		//Create an instance of your character and set its properties with
		//new values.
	}
	//TODO
	//create a changeCharacter method and change inside all the properties you like.
}
