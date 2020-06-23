package model;

import javafx.scene.image.ImageView;

public class PINEAPPLE extends FRUITS {
	ImageView whole = new ImageView("view/resources/fruits/pineapple.png");
	ImageView slice = new ImageView("view/resources/fruits/pineapple_cut.png");
	
	public PINEAPPLE() {
		super();
		whole.setFitHeight(100);
		whole.setFitWidth(100);
		slice.setFitHeight(100);
		slice.setFitWidth(100);
		setWholeFruit(whole);
		setSlicedFruit(slice);
		}

}
