package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// extending from parent class imageview
public class Tyler extends ImageView {
	
	public Image img, imgHurt;
	public double width, height;
	
	public Tyler() {
		// calling superclass
		super();
		
		// setting images (same dimensions)
		img = new Image("file:t1.png");
		imgHurt = new Image("file:t1Hurt.png");
		super.setImage(img);
		
		// setting width and height to any one of the images
		width = img.getWidth();
		height = img.getHeight();
	}
	// returning width and height
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	
	// if tyler gets hit, we will change his image
	public void hurt() {
		super.setImage(imgHurt);
	}
	
	// if tyler was hurt, we will change his image back to normal when he moves, and also move in the player's desired direction
	public void move(int xDir) {
		super.setImage(img);
		super.setX(super.getX()+xDir);
	}
}
