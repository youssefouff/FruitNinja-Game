package model;

import javafx.scene.image.ImageView;

public class STRAWBERRY extends FRUITS {
	ImageView whole = new ImageView("view/resources/fruits/strawberry 1.png");
	ImageView slice = new ImageView("view/resources/fruits/Cut_Strawberry.png");
	
	public STRAWBERRY() {
		super();
		whole.setFitHeight(100);
		whole.setFitWidth(100);
		slice.setFitHeight(100);
		slice.setFitWidth(100);
		setWholeFruit(whole);
		setSlicedFruit(slice);
	}


}
