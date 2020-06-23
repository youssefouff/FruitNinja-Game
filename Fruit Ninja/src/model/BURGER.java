package model;

import javafx.scene.image.ImageView;

public class BURGER extends FRUITS {
	ImageView burger = new ImageView("view/resources/burger.png");
	
	public BURGER() {
		super();
		burger.setFitHeight(100);
		burger.setFitWidth(100);
		setWholeFruit(burger);
		}

}
