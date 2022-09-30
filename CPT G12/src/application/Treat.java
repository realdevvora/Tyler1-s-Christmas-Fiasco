package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// extending from parent class imageview
public class Treat extends ImageView {
	private Image img;
	private double width, height, xDir, yDir;
	
	public Treat() {
		// calling superclass
		super();
		
		// setting image for the treat
		img = new Image("file:treat.png");
		super.setImage(img);
		
		// setting width and height
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
	
	// this function will handle the rotating and moving of the treat on the screen, depending on where the user clicked
	public void setEnd(double mousePosX, double mousePosY) {
		
		// if they clicked more horizontal than vertical on the screen
		if (Math.abs(super.getX() - mousePosX) > Math.abs(super.getY()-mousePosY)) { 
			// if they clicked more left
			if (super.getX() - mousePosX > 0) {  
				// we will set the xdirectional movement to -10
				xDir = -10;
				
				// we will set the yDirection to the xDirection multiplied by the ratio of Tyler's y-coordinate minus the Y mouse position, divided by Tyler's x-coordinate minus the mouse X position
				// the reason why we are doing this is because we want the bullet to move horizontally at the same speed, but since we are aiming more horizontal, the vertical speed should be lower, or else it would look awkward moving on the screen.
				// the ratio will help us find how vertical the user is actually clicking, and give us a comparison of that to the horizontal
				// at most, the yDir will be equal to xDir (45 degrees, both x and y components will be equal magnitude) and at least, yDir will be 0
				yDir = (super.getY()-mousePosY)/(super.getX() - mousePosX) * xDir;
				
				// then we will set the angle, which will be in the range of 225 to 315 degrees inclusive (using the same ratio of Y to X we determined above)
				// Math.atan() returns the value in radians, so we would need to convert that into degrees by multiplying it by 180 / pi
				super.setRotate(270 + Math.atan((super.getY()-mousePosY)/(super.getX() - mousePosX))* 180/Math.PI);
			}
			
			// if they clicked right
			else {
				// we will set the xdirectional movement to positive 10
				xDir = 10;
				
				// we will set the yDirection to the xDirection multiplied by the ratio of Tyler's y-coordinate minus the Y mouse position, divided by Tyler's x-coordinate minus the mouse X position
				// the reason why we are doing this is because we want the bullet to move horizontally at the same speed, but since we are aiming more horizontal, the vertical speed should be lower, or else it would look awkward moving on the screen.
				// the ratio will help us find how vertical the user is actually clicking, and give us a comparison of that to the horizontal
				// at most, the yDir will be equal to xDir (45 degrees, both x and y components will be equal magnitude) and at least, yDir will be 0
				yDir = (super.getY()-mousePosY)/(super.getX() - mousePosX) * xDir;
				
				// then we will set the angle, which will be in the range of 45 to 135 degrees inclusive (using the same ratio of Y to X we determined above)
				super.setRotate(90 + Math.atan((super.getY()-mousePosY)/(super.getX() - mousePosX))* 180/Math.PI);
			}
		} 
		// if they clicked more vertical than horizontal
		// the code for this section will be a little more awkward, since the coordinate system for java is different
		// in this case, negative will be up and positive will be down, and also some signs in the rotation of the sprite are flipped
		else { 
			// if they clicked up
			if (super.getY()-mousePosY > 0) { 
				
				// we will set the yDir to up
				yDir = -10; 
				
				// xDir will be determined by finding the ratio of the X component to the Y component and multiplying that by the yDir
				xDir = (super.getX() - mousePosX) / (super.getY()-mousePosY) * yDir;
				
				// then we will set the angle which could be 315 to 405 degrees (405 degrees is the same as 45 degrees) determined by the same ratio as above
				// we would need to subtract instead of adding the tangent angle, because its technically in the flip direction (negative is up), so we would need to reverse the sign from the horizontal
				super.setRotate(360 - Math.atan((super.getX() - mousePosX) / (super.getY()-mousePosY))*180/Math.PI);
			}
			// if they clicked down
			else { 
				
				// set the yDir to down
				yDir = 10; 
				
				// xDir will be determined by finding the ratio of the X component to the Y component and multiplying that by the yDir
				xDir = (super.getX() - mousePosX) / (super.getY()-mousePosY) * yDir;
				
				// then we will set the angle which could be 135 to 225 degrees determined by the same ratio as above
				// and flip the sign of addition to subtraction
				super.setRotate(180 - Math.atan((super.getX() - mousePosX) / (super.getY()-mousePosY))*180/Math.PI);
			}
		}
	}
	
	// moving using the xDir and yDir we figured out from above
	public void move() {
		super.setX(super.getX()+xDir);
		super.setY(super.getY()+yDir);
	}
	
	// checking if the treat is off the screen, and returning true or false depending on the scenario
	public boolean isOffScreen(double w, double h) {
		if (super.getX() + width <= 0 || super.getX() >= w) {
			return true;
		}
		else if (super.getY() + height <= 0 || super.getY()>= h) {
			return true;
		}
		return false;
	}
}
