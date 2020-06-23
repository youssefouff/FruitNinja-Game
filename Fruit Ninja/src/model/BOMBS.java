package model;

import javafx.scene.image.ImageView;

public abstract class BOMBS {
	 private ImageView bomb;
	 private boolean sliced;
	 private int dimX,dimY;
	 
	 public BOMBS() {
		 sliced = false;
	}

	public ImageView getBomb() {
		return bomb;
	}

	public void setBomb(ImageView bomb) {
		this.bomb = bomb;
	}

	public boolean isSliced() {
		return sliced;
	}

	public void setSliced(boolean sliced) {
		this.sliced = sliced;
	}

	public int getDimX() {
		return dimX;
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public int getDimY() {
		return dimY;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
	 
}

