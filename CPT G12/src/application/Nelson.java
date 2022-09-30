package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



// we will extend from the parent class ImageView
public class Nelson extends ImageView{
	private Image imgRed, imgBlue, imgGreen;
	private double width, height;
	private int state, speed;
	public final int RED = 1, BLUE = 2, GREEN = 3, NONE = 4;
	
	public Nelson() {
		// calling superclass
		super();
		
		// declaring images (all are the same size)
		imgRed = new Image("file:nelsonRed.png");
		imgBlue = new Image("file:nelsonBlue.png");
		imgGreen = new Image("file:nelsonGreen.png");
		
		// setting the image of the superclass
		super.setImage(imgGreen);
		
		// initializing width, height, nelson's state, and speed
		width = imgGreen.getWidth();
		height = imgGreen.getHeight();
		state = NONE;
		speed = 10;
	}
	// RETURNING WIDTH AND HEIGHT
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	
	// at the start of the room, nelson will be idle and green at the top center with one hitpoint
	public void start() {
		state = NONE;
		super.setImage(imgGreen);
	}
	
	// red nelson will run toward Tyler, he will come from the top at the location of Tyler when he hits the bottom, or go back to the top when he hits Tyler
	public void stateRed(double tylerX) {
		state = RED;
		super.setImage(imgRed);
		super.setX(tylerX);
		super.setY(-height);
	}
	
	// blue nelson will shoot litter at tyler while moving across the screen
	public void stateBlue() {
		state = BLUE;
		super.setImage(imgBlue);
	}
	
	// green nelson will stand still
	public void stateGreen() {
		state = GREEN;
		super.setImage(imgGreen);
	}
	
	// this will be used by blue nelson, to make him move in the correct direction, and speed (left or right)
	public void move(int xDir) {
		super.setX(super.getX()+xDir*speed);
	}
	
	// this will speed up blue nelson as he takes more damage
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// this will be used by red nelson, in order to make him move down
	public void moveDown() {
		super.setY(super.getY()+10);
	}
	
	// this will return what state nelson is in, for the conditionals in our animationtimer for nelson
	public int getState() {
		return state;
	}
}
