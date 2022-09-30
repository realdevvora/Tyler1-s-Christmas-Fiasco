package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*	
	ICS4U1 CPT: TYLER1'S CHRISTMAS FIASCO
	Dev Vora
	Mr. Conway
	
	Program Description:
	- The user will play as Tyler1 (a livestreamer), and will attempt to solve puzzles and complete challenges in order to save Christmas
	- In the first room, the user will be required to click each button the same number of times as it appears in the passcode (ex. 1234, they would click lock 1 one time, lock 2 two times and etc.) the passcode is created in the program, stored in a file, and then read through the program again
	- In the second room, the user will have to click on the CheckBoxes in order to form a list of names in alphabetical order. This list is then compared with a sorted array, found by using an insertion sort
	- In the third room, the user will play versus the computer in a game of Tic Tac Toe. They will have to click on the spaces and form 3 identical 'x' terms on the grid in order to win a round. They will play up until either them or the computer gets 3 wins.
	- In the fourth room, they user will control a sprite of Tyler1 with the arrow keys, and they can shoot by clicking with the mouse. They will be trying to shoot at Nelson, which is the large cat at the top of the screen, who will need to be shot once to begin the game. Nelson will have 3 phases throughout the game. Additionally, the user is on a timer to defeat Nelson in 30 seconds, or else Tyler will not be able to save Christmas.
		- 1: He will be the colour red, where he appears from the top of the screen and moves down to collide with Tyler1, the user will have to control Tyler1 in order to avoid him, as well as shoot him in order to make him lose hitpoints. Once he has been shot 30 times, he will go to the next phase
		- 2: He will turn green, where he passively sits in the center of the screen and doesn't move, he will have 20 hitpoints in this stage.
		- 3: He will turn blue, move left and right across the top of the screen and shoot litter. Tyler has to avoid this litter, while also shooting treats at Nelson. He has 30 hitpoints in this stage as well.
		- Once all stages are complete and the timer is not over, Christmas will be saved. 
	- The controls for this program are using your mouse throughout the program, and arrow keys only in the final stage
		
	Program Details:
		In the first room:
			- I created the four digit number by using a DecimalFormat for a randomly generated number, setting it to max 4 digits long, and then storing it in a file. Then I read that value from the file and displayed it on the screen using a label.
			- I also added four buttons corresponding to the four digits in the lock code, which are to be pressed the same number of times as the value of the digit in the lock code
			- The buttons would store the value in a global variable for each button clicked (ex. lock 1 was counter1)
			- They can click "OK" at any time in order to check if the number of times which they clicked was correct, in which case it would compare the integer value of each letter in the file to the respective counter
			- They could also click "CLEAR" if they wanted to clear all of their selections in case they mis-clicked
			- If they got this correct, they would go to the next screen otherwise, they would lose
		
		In the second room:
			- There was a FlowPane containing CheckBoxes, a TextArea and buttons
			- The user was expected to click on the checkboxes in the alphabetical order
			- This would then display the order on the textarea
			- If all boxes were selected, the OK button would enable and the user would be able to check if they got it correct, this would be determined by comparing it to an insertion sort function which has determined the value of the sorted array already
			- If they clicked CLEAR, all selections as well as the textarea would be cleared
			- The way we would store the values in the order that it was clicked, is by temporarily appending it to an arraylist, and then removing it from the arraylist if it is unchecked. These values will then be put onto the TextField
			- If they got the correct order, they would be taken to the next room or else, they would lose
			
		In the third room:
			- The user would be put into a room where there is a 3x3 board in the center, and their score in the top left, as well as the opponent's score in the top right.
			- They would be able to click on any square in the 3x3 grid as long as it is not already overlapped
			- They would play until someone would win a round, and then the grid would be cleared and it would display who won the round
			- If it tied, nobody would get a win and the board clears again
			- Whoever doesn't play last in the previous round, will play first in the next round (ex. X had last move in round 1, O would have first move in round 2)
			- This would go on until someone ends up with 3 wins.
			- If they end up with 3 wins, they would go to the final round, whereas if the opponent ended up with 3 wins, they would lose the game.
			
		Final Room:
			- The user would play as Tyler1, and be able to move his head left and right across the screen
			- They can shoot treats towards the mouse position, and the treats would rotate to the direction of the mouse
				- Programming this portion involved a little bit of trigonometry for the rotation angles, as well as understanding ratios for the speed
			- When they first hit Nelson, he will turn red, the 30 second timer will start, and he will begin moving from the top of the screen down towards the player.
			- If he collides he would take away health from the player, and go to the top again.
			- If he hits the bottom of the screen, he would go to the top again.
			- If the player shoots him 30 times, he would change to green and sit idle in the top-center of the screen.
			- After the player shoots green Nelson 20 times, he would change to blue and start moving back and forth across the screen.
			- Blue Nelson would also shoot litter from his position, down the screen. The player has to avoid this litter, as well as keep shooting Nelson
			- If blue Nelson is hit 30 times, the player would win the game
			- If either the timer runs out, or the player runs out of lives, the player would lose the game.
			
		Post Game:
			- The player will be asked to enter their name if they win, which will be stored in a file with several other names, along with the time which each person spent on the final room
			- Then once the player clicks ok, the game will finish
 */
public class Main extends Application implements EventHandler<ActionEvent>{
	
	// These will be used in the start() function (root will also be used throughout the program)
	private Pane root = new Pane();
	private Scene scene = new Scene(root,1200,700);
	private ImageView ivBg;
	
	// this is for the title screen
	private boolean titleScreen;
	
	// these will be used in the first room
	private Button btn1, btn2, btn3, btn4;
	private int counter1, counter2, counter3, counter4;
	private String passcode;
	
	// these will be used in the second room
	private String[] naughtyList = {"TARZANED", "TRDBLASTER", "NIGHTBLUE3", "LESU", "PROOSIA", "APA", "TEMPOS", "IMANIGHTMARE", "THEO", "MITTENS"};
	private String[] algorithmSort;
	private CheckBox[] selections;
	private ArrayList<String> sortedArr = new ArrayList<>();
	private TextArea sortedList;
	private Button okR2 = new Button("OK");
	private Button clearR2 = new Button("CLEAR");
	
	// these will be used in the third room
	private int player, winCounter, loseCounter;
	private boolean room3 = false;
	private Label playerWins, opponentWins, previousGame;
	private Square[][] board;
	
	// these will be used in the final room
	private Nelson nelson;
	private Tyler tyler;
	private ArrayList<Treat> bullets;
	private ArrayList<Litter> litter;
	private int bulletCount = -1, litterCount = -1;
	private boolean room4 = false;
	private AnimationTimer animateBullet, animateTyler, animateNelson;
	private Timeline litterClock, deadlineClock;
	private Label duration, hpRemaining;
	private boolean goLeft = false, goRight = false;
	private int hitpoints, health = 5, nelsonDir = 1, seconds = 30;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//background imageview
			ivBg = new ImageView(new Image("file: title_bg.png"));
			
			
			// this is the keyframe which will be used for the opponent's move in room3
			KeyFrame kfCPUMove = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (player == 2) {
						// we will set a random number for the row and column
						int row = (int)(Math.random()*3);
						int col = (int)(Math.random()*3);
						
						// if the tile at the indice is already occupied, we will continue getting a random number until there is no occupied tile
						while (board[row][col].getPlayer() != 0 && !boardFull()) {
							row = (int)(Math.random()*3);
							col = (int)(Math.random()*3);
						}
						// then setting it to the computer's value, and switching the player
						board[row][col].clicked(player);
						player = 1;
						
						// if the computer wins, the user loses the game
						checkWinGame();
						if (loseCounter == 3) {
							lose();
						}
					}
					
				}
			});

			// this is the timeline for the keyframe above
			Timeline tictactoe = new Timeline(kfCPUMove);
			tictactoe.setCycleCount(Timeline.INDEFINITE);
			 
			// when the user presses on a key in the final room, they will be able to move left or right
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() { 
				public void handle(KeyEvent e) {
					if (room4) {
						if (e.getCode() == KeyCode.LEFT) {
							goLeft = true;
						}
						else if (e.getCode() == KeyCode.RIGHT) {
							goRight = true;
						}
					}
					
					// if they press "ENTER" the game will start
					if (e.getCode() == KeyCode.ENTER) {
						if (titleScreen) {
							titleScreen = false;
							room1();
						}
					}
				}
			});
			
			// this will stop them from moving in the final room if they release the keys
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					if (room4) {
						if (e.getCode() == KeyCode.LEFT) {
							goLeft = false;
						}
						else if (e.getCode() == KeyCode.RIGHT) {
							goRight = false;
						}
					}
				}
			});
			
			// handles when the user clicks their mouse
			scene.setOnMousePressed(new EventHandler<MouseEvent>() { 
				public void handle(MouseEvent e) {
					// if they are in the 3rd room
					if (room3) {
						// we will loop through the 2d array of the tic tac toe board
						for (int i = 0; i < board.length; i ++) {
							for (int j = 0; j < board[i].length; j ++) {
								
								// if the mouse is clicking on a certain box
								if (e.getX() >= board[i][j].getX() && e.getX() <= board[i][j].getX() + board[i][j].getWidth() && e.getY() >= board[i][j].getY() && e.getY() <= board[i][j].getY() + board[i][j].getHeight()) {
									
									// and if the box is empty and the player is the user
									if (board[i][j].getPlayer() == 0 && player == 1) {
										
										// we will stop the tic tac toe timeline
										tictactoe.stop();
										
										// set the value of the board to player 1's image
										board[i][j].clicked(player);
										
										// reset the text of who won the last round
										previousGame.setText("");
										
										// change the player to player 2
										if (player == 1) {
											player = 2;
										}
										
										// check if the user won the game
										checkWinGame();
										
										// play the opponent's timer
										tictactoe.play();
										
										// if the user won more than 3 times, stop everything needed for this room, and move onto the next while also displaying an alert
										if (winCounter == 3) {
											tictactoe.stop();
											room3 = false;
											Alert wp = new Alert(AlertType.INFORMATION);
											wp.setTitle("TT'S CHRISTMAS FIASCO");
											wp.setHeaderText(null);
											wp.setContentText("APA: W-W-W-W-W-WWWWHAT! HOW DID YOU BEAT MY UNDEFEATED ALGORITHM\n\n"
													+ "TT: HUH? IT'S JUST A MID GAP. NOTHING PERSONAL.\n\n"
													+ "APA: NOOOOOOOOOOOOOOOOOOOOOOOOOO");
											wp.setGraphic(new ImageView(new Image("file:surprised.png")));
											wp.showAndWait();
											room4();
										}
										
										
									}
								}
							}
						}
					}
					
					// if we are in the fourth room, when the user clicks the mouse, we will add a treat at the user's position to the program, and start an animation timer
					else if (room4) {
						bulletCount++;
						bullets.add(bulletCount, new Treat());
						bullets.get(bulletCount).setX(tyler.getX() + 50);
						bullets.get(bulletCount).setY(tyler.getY());
						bullets.get(bulletCount).setEnd(e.getX(),e.getY());
						root.getChildren().add(bullets.get(bulletCount));
						animateBullet.start();
					}
					
				}
			});
			
			
			// for when the treat is created
			animateBullet = new AnimationTimer() {
				public void handle(long now) {
					
					// for each treat in the arraylist
					for (int i = 0; i < bullets.size(); i ++) {
						
						// move the treat
						bullets.get(i).move();
						
						// if its off the screen, remove it from the program and skip an interation (to avoid an error)
						if (bullets.get(i).isOffScreen(scene.getWidth(), scene.getHeight())) {
							root.getChildren().remove(bullets.get(i));
							bullets.remove(i);
							bulletCount--;
							continue;
						}
						
						// if it collides with Nelson, take away a hitpoint from him and remove the bullet from the program
						if (bullets.get(i).getBoundsInParent().intersects(nelson.getBoundsInParent())) {
							hitpoints--;
							root.getChildren().remove(bullets.get(i));
							bullets.remove(i);
							bulletCount--;
						}
					}
					
				}
			};
			
			// for tyler
			animateTyler = new AnimationTimer() {
				public void handle(long now) {
					// if the user clicked left, he will move left as long as he is not off the screen
					if (goLeft) {
						if (tyler.getX() >= 5) {
							tyler.move(-5);
						}
					}
					
					// if the user clicked right, he will move right as long as he is not off the screen
					else if (goRight) {
						if (tyler.getX() + tyler.getWidth() <= scene.getWidth() - 5) {
							tyler.move(5);
						}
					}
				}
			};
			
			// litter will be added to the program every half a second while nelson is in the blue form
			KeyFrame litterTimer = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					litterCount++;
					litter.add(litterCount, new Litter());
					litter.get(litterCount).setLocation(nelson.getX()+50, nelson.getY()+nelson.getHeight());
					root.getChildren().add(litter.get(litterCount));
				}
			});
			
			// this is the timer for the user in the final room, they will have to win in 30 seconds or else it's game over
			KeyFrame deadline = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					seconds--;
					duration.setText("REMAINING SECONDS: " + seconds);
					if (seconds == 0) {
						lose();
					}
				}
			});
			
			// the next 4 lines are separate timelines for our litter and game timer
			deadlineClock = new Timeline(deadline);
			deadlineClock.setCycleCount(Timeline.INDEFINITE);
			
			litterClock = new Timeline(litterTimer);
			litterClock.setCycleCount(Timeline.INDEFINITE);
			
			// dealing with Nelson
			animateNelson = new AnimationTimer() {
				public void handle(long now) {
					// at the start of the program, nelson will be idle with 1hp in the top center of the screen
					if (nelson.getState() == nelson.NONE) {
						nelson.setX(scene.getWidth()/2 - nelson.getWidth()/2);
						nelson.setY(0);
						
						// if he is hit, he will switch to red form, with 30 hp
						if (hitpoints <= 0) {
							nelson.stateRed(tyler.getX()-50);
							hitpoints = 30;
						}
					}
					if (nelson.getState() == nelson.RED) {
						// start the game timer and start moving nelson down
						deadlineClock.play();
						nelson.moveDown();
						
						// if nelson goes offscreen, we will send him back to the top of the screen at the user's x-position at that time
						if (nelson.getY() >= scene.getHeight()) {
							nelson.stateRed(tyler.getX() - 50);
						}
						
						// if he runs out of health, he goes back to the green form at the top center of the screen, this time with 20hp
						if (hitpoints <= 0) {
							nelson.stateGreen();
							nelson.setX(scene.getWidth()/2 - nelson.getWidth()/2);
							nelson.setY(0);
							hitpoints = 20;
						}
						
						// if he collides with tyler, tyler will change his sprite until he moves again, lose 1 hp, and nelson will go back to the top of the screen
						if (nelson.getBoundsInParent().intersects(tyler.getBoundsInParent())) {
							nelson.stateRed(tyler.getX());
							// change tyler's sprite
							tyler.hurt();
							health--;
							hpRemaining.setText("HP: " + health);
							// if tyler runs out of health, he will lose
							if (health <= 0) {
								lose();
							}
						}
					}
					
					// green state
					if (nelson.getState() == nelson.GREEN) {
						
						// if nelson reaches 0 hp, he will go to blue form with 30hp
						if (hitpoints <= 0) {
							nelson.stateBlue();
							nelson.setX(scene.getWidth()/2 - nelson.getWidth()/2);
							nelson.setY(0);
							hitpoints = 30;
						}
					}
					if (nelson.getState() == nelson.BLUE) {
						// start creating litter
						litterClock.play();
						
						// move nelson left and right across the top of the screen
						nelson.move(nelsonDir);
						
						// if he collides with the wall, flip his direction
						if (nelson.getX() <= 0 || nelson.getX() + nelson.getWidth() >= scene.getWidth()) {
							nelsonDir = -nelsonDir;
						}
						
						// for each litter in the litter arraylist
						for (int i = 0; i < litter.size(); i ++) {
							// move
							litter.get(i).move();
							
							// if it goes off screen, remove the litter from the program and skip an iteration (to avoid error)
							if (litter.get(i).getY() >= scene.getHeight()) {
								root.getChildren().remove(litter.get(i));
								litter.remove(i);
								litterCount--;
								continue;
							}
							
							// if the litter collides with the user, they loses 1hp and the litter is removed from the program
							if (litter.get(i).getBoundsInParent().intersects(tyler.getBoundsInParent())) {
								root.getChildren().remove(litter.get(i));
								// change tyler's sprite
								tyler.hurt();
								litter.remove(i);
								litterCount--;
								health --;
								// if he is out of health, the user loses the game
								hpRemaining.setText("HP: " + health);
								if (health <= 0) {
									lose();
								}
							}
							
						}
						// if nelson runs out of hp in blue form, the user wins
						if (hitpoints <= 0) {
							win();
						}
						// if nelson has 15 or less hp, he will move at speed of 20
						else if (hitpoints <= 15) {
							nelson.setSpeed(20);
						}
						// if nelson has 20 or less hp, he will move at a speed of 15
						else if (hitpoints <= 20) {
							nelson.setSpeed(15);
						}
						
					}
				}
			};
			
			// will be used in the 2nd room (instantiates the size of the verification sorted array)
			algorithmSort = new String[naughtyList.length];

			// running the title screen of the program
			title();
			
			// setting the stage
			primaryStage.setTitle("TYLER1'S CHRISMAS FIASCO");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void title() {
		// setting the background imageview to the titlescreen background
		ivBg.setImage(new Image("file:title_bg.png"));
		root.getChildren().add(ivBg);
		titleScreen = true;
	}
	
	public void room1() {
		// showing the introduction alert
		Alert intro = new Alert(AlertType.INFORMATION);
		intro.setHeaderText(null);
		intro.setTitle("TYLER1'S CHRISTMAS FIASCO");
		intro.setContentText("Tyler1 was preparing for Christmas to give out presents to the kids, when he received a notification that his trusty vehicle, Red Rocket, was stolen! Can you help him find a way to retrieve Red Rocket before he runs out of time?");
		intro.setGraphic(new ImageView(new Image("file:red_rocket_blank.png")));
		intro.showAndWait();
		
		// setting the background image
		ivBg.setImage(new Image("file:vaultBg.png"));
		// storing the password in the file
		password();
		
		// showing the initial alert for this room
		Alert room1Alert = new Alert(AlertType.INFORMATION);
		room1Alert.setHeaderText(null);
		room1Alert.setWidth(400);
		room1Alert.setHeight(300);
		room1Alert.setTitle("TYLER1'S CHRISTMAS FIASCO");
		room1Alert.setContentText("First thing Tyler has to do is check his $5 billion vault and investigate the situation. He is required to enter his super secret password which he decoded and simplified, click the four buttons correlating to the position of the number in the sequence of numbers in the password.");
		room1Alert.setGraphic(new ImageView(new Image("file:r1Icon.png")));
		room1Alert.showAndWait();
		
		// reading the randomly generated password from the file
		File f = new File("password.txt");
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			passcode = br.readLine();
			fr.close();
			br.close();
		}
		catch (Exception e) {
			// to prevent error (this will never run)
			passcode = "0000";
		}
		
		// creating gridpane
		GridPane room1Pane = new GridPane();
		room1Pane.setPrefSize(400, 400);
		room1Pane.setVgap(10);
		
		// creating the label for the password
		Label lbl = new Label(passcode);
		lbl.setFont(Font.font("Britannic Bold", FontWeight.BOLD, 30));
		
		
		// the next couple of lines will create the four buttons we will see on the screen. They will initially be in alternating colours (green, red, green red) but then when you click one of them, it would switch to the opposite colour (ex. red initially, on click switches to green, and then click again it goes back to red and so on)
		// depending on the one you click, it will store the value in a counter variable (different for each button) which will later be checked if the player clicks ok
		// button 1
		btn1 = new Button("LOCK 1");
		btn1.setPrefSize(100, 30);
		btn1.setStyle("-fx-background-color:green");
		btn1.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				if (btn1.getStyle() == "-fx-background-color:green") {
					btn1.setStyle("-fx-background-color:red");
				}
				else {
					btn1.setStyle("-fx-background-color:green");
				}
				counter1++;
			}
		});
		
		// button 2
		btn2 = new Button("LOCK 2");
		btn2.setPrefSize(100, 30);
		btn2.setStyle("-fx-background-color:red");
		btn2.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				if (btn2.getStyle() == "-fx-background-color:green") {
					btn2.setStyle("-fx-background-color:red");
				}
				else {
					btn2.setStyle("-fx-background-color:green");
				}
				counter2++;
			}
		});
		
		// button 3
		btn3 = new Button("LOCK 3");
		btn3.setPrefSize(100, 30);
		btn3.setStyle("-fx-background-color:green");
		btn3.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				if (btn3.getStyle() == "-fx-background-color:green") {
					btn3.setStyle("-fx-background-color:red");
				}
				else {
					btn3.setStyle("-fx-background-color:green");
				}
				counter3++;
			}
		});
		
		// button 4
		btn4 = new Button("LOCK 4");
		btn4.setPrefSize(100, 30);
		btn4.setStyle("-fx-background-color:red");
		btn4.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				if (btn4.getStyle() == "-fx-background-color:green") {
					btn4.setStyle("-fx-background-color:red");
				}
				else {
					btn4.setStyle("-fx-background-color:green");
				}
				counter4++;
			}
		});
		
		// if the player clicks ok, the program will check if the passcode matches the number of times the user clicked each button
		Button ok = new Button("OK");
		ok.setPrefSize(100, 30);
		ok.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				
				// then we will compare the value of each counter to its respective value in the passcode
				if (counter1 == Integer.valueOf(Character.toString(passcode.charAt(0))) && counter2 == Integer.valueOf(Character.toString(passcode.charAt(1)))  && counter3 == Integer.valueOf(Character.toString(passcode.charAt(2))) && counter4 == Integer.valueOf(Character.toString(passcode.charAt(3))) ) {
					
					// if it does match, we will go to the next room
					Alert wp = new Alert(AlertType.INFORMATION);
					wp.setTitle("TT'S CHRISTMAS FIASCO");
					wp.setHeaderText(null);
					wp.setContentText("POGGERS! You got into the vault!");
					wp.setGraphic(new ImageView(new Image("file:poggies.png")));
					wp.showAndWait();
					room2();
				}
				else { 
					// or else game over
					lose();
				}
			}
		});
		
		// when they click clear, the colours of the buttons will go back to what they looked like at the start of the program, and set all counters back to zero
		Button clear = new Button("CLEAR");
		clear.setPrefSize(100, 30);
		clear.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				btn1.setStyle("-fx-background-color:green");
				counter1=0;
				
				btn2.setStyle("-fx-background-color:red");
				counter2=0;
				
				btn3.setStyle("-fx-background-color:green");
				counter3=0;
				
				btn4.setStyle("-fx-background-color:red");
				counter4=0;
			}
		});
		
		// we will add the four combination buttons to this hbox
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		
		// we will add the OK and CLEAR buttons to this hbox
		HBox hb2 = new HBox(50);
		hb2.setAlignment(Pos.CENTER);
		
		hb.getChildren().addAll(btn1, btn2, btn3, btn4);
		hb2.getChildren().addAll(ok, clear);
		
		// center the passcode label
		GridPane.setHalignment(lbl, HPos.CENTER);
		
		// add everything to the gridpane
		room1Pane.add(lbl, 0, 0);
		room1Pane.add(hb, 0, 1);
		room1Pane.add(hb2, 0, 2);
		
		
		// center the gridpane on the screen, and add it to the root pane
		room1Pane.setLayoutX(root.getWidth()/2 - room1Pane.getPrefWidth()/2);
		room1Pane.setLayoutY(root.getHeight()/2 - room1Pane.getPrefHeight()/2);
		root.getChildren().add(room1Pane);
	}
	
	// this function will just add the string value of the randomly generated passcode to the file, it can be any number from 0 to 9999, but it will always have 4 digits (because of the decimal format)
	public void password() {
		DecimalFormat df = new DecimalFormat("0000");
		File f = new File("password.txt");
		try {
			FileWriter fr = new FileWriter(f);
			BufferedWriter br = new BufferedWriter(fr);
			
			br.write(df.format((int)(Math.random()*10000)));
			br.close();
			fr.close();
		}
		catch (Exception e) {
			
		}
		
	}
	
	public void room2() {
		// removing all elements from the last round from the root pane
		root.getChildren().clear();
		
		// showing the alert for the 2nd room
		Alert room2Alert = new Alert(AlertType.INFORMATION);
		room2Alert.setHeaderText(null);
		room2Alert.setTitle("TYLER1'S CHRISTMAS FIASCO");
		room2Alert.setWidth(400);
		room2Alert.setHeight(300);
		room2Alert.setContentText("Oh wow! The thief left behind a note with names of certain people with whom Tyler has had problematic interactions with in the past. Can you find a way to arrange these names? (Hint: alphabet)");
		room2Alert.setGraphic(new ImageView(new Image("file:r2Icon.png")));
		room2Alert.showAndWait();
		
		// setting the background image
		ivBg.setImage(new Image("file:sortingBg.jpg"));
		root.getChildren().add(ivBg);
		
		// using a flowpane for this room
		FlowPane room2Pane = new FlowPane();
		room2Pane.setPrefSize(500, 500);
		room2Pane.setPadding(new Insets(10,10,10,10));
		
		// making a checkbox array
		selections = new CheckBox[naughtyList.length];
		
		// making our textarea
		sortedList = new TextArea();
		sortedList.setPrefSize(300, 300);
		sortedList.setEditable(false);
		
		// initializing each item in the checkbox array with the names declared at the start of the program
		for (int i = 0; i < naughtyList.length; i ++) {
			selections[i] = new CheckBox(naughtyList[i]);
			selections[i].setTextFill(Color.WHITE);
			selections[i].setPrefSize(200, 30);
			selections[i].setOnAction(this);
			room2Pane.getChildren().add(selections[i]);
		}
		
		// the OK button for the 2nd room
		okR2.setDisable(true);
		okR2.setPrefSize(100, 30);
		okR2.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				// we will check if the player got everything correct
				int correct = 0;
				for (int i = 0; i < naughtyList.length; i ++) {
					if (sortedArr.get(i) == algorithmSort[i]) {
						correct++;
					}
				}
				// if they didn't, they will lose
				if (correct != naughtyList.length) {	
					lose();
				}
				else {
					// otherwise display a congratulations alert and take them to the 3rd room
					Alert wp = new Alert(AlertType.INFORMATION);
					wp.setTitle("TT'S CHRISTMAS FIASCO");
					wp.setHeaderText(null);
					wp.setContentText("Well done, smart and efficient sorting. You will now be able to investigate the suspects.");
					wp.setGraphic(new ImageView(new Image("file:5head.png")));
					wp.showAndWait();
					room3();
				}
			}
		});
		
		// if they click clear, all selections and the textfield will be cleared (along with our hidden arraylist)
		clearR2.setPrefSize(100, 30);
		clearR2.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
				for (int i = 0; i < selections.length; i ++) {
					selections[i].setSelected(false);
				}
				sortedList.clear();
				sortedArr.clear();
			}
		});
		
		// adding the OK and CLEAR buttons to an hbox
		HBox hb = new HBox(100);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(okR2, clearR2);
		
		// running the function which gives us our correct sorted array (which will be compared to the user's selections)
		InsertionSort(naughtyList);
		
		// adding the textarea and button hbox to the flowpane (we already added the checkboxes in the loop where we initialized them), and then adding the flowpane to the root pane
		room2Pane.getChildren().addAll(sortedList, hb);
		room2Pane.setLayoutX(root.getWidth()/2 - room2Pane.getPrefWidth()/2);
		room2Pane.setLayoutY(root.getHeight()/2 - room2Pane.getPrefHeight()/2);
		root.getChildren().add(room2Pane);
	}
	
	public void room3() {
		// clearing elements from the last room
		root.getChildren().clear();
		
		// displaying the alert for the 3rd room
		Alert room3Alert = new Alert(AlertType.INFORMATION);
		room3Alert.setHeaderText(null);
		room3Alert.setTitle("TYLER1'S CHRISTMAS FIASCO");
		room3Alert.setContentText("Tyler went on his way to investigate the first suspect, APA. When he reached at his house, he asked if he knew anything about Red Rocket. APA said that he would only give information if Tyler beat him in a first to 3 wins series of Tic Tac Toe. Unknown to Tyler, APA owns a $4.6 trillion Tic Tac Toe algorithm, which no one has ever beaten before. Can you pass the challenge? If you think you can, click on a square to start, you are playing as 'X'.");
		room3Alert.setGraphic(new ImageView(new Image("file:apa.jpg")));
		room3Alert.setWidth(400);
		room3Alert.setHeight(300);
		room3Alert.showAndWait();
		
		// setting our background image, and a variable which tells us that we are in room 3 (this will used by the start() function)
		ivBg.setImage(new Image("file:bgGame.jpg"));
		root.getChildren().add(ivBg);
		room3 = true;
		
		// making a label in the top left for Tyler1's wins
		playerWins = new Label("BIG T: " + winCounter);
		playerWins.setPrefSize(300, 50);
		playerWins.setAlignment(Pos.CENTER_LEFT);
		playerWins.setTextFill(Color.WHITE);
		playerWins.setFont(Font.font("Times New Roman", 24));
		
		// making a label in the top right for the opponent's (APA) wins
		opponentWins = new Label("APA: " + loseCounter);
		opponentWins.setPrefSize(300, 50);
		opponentWins.setLayoutX(scene.getWidth()-opponentWins.getPrefWidth());
		opponentWins.setAlignment(Pos.CENTER_RIGHT);
		opponentWins.setTextFill(Color.WHITE);
		opponentWins.setFont(Font.font("Comic Sans MS", 24));
		
		// this label will display only after a game is won/tied, and will disappear after the player makes their move
		previousGame = new Label();
		previousGame.setPrefSize(300, 50);
		previousGame.setLayoutX(scene.getWidth()-opponentWins.getPrefWidth());
		previousGame.setAlignment(Pos.CENTER);
		previousGame.setTextFill(Color.WHITE);
		previousGame.setFont(Font.font("Comic Sans MS", 24));
		previousGame.setLayoutX(scene.getWidth()/2-previousGame.getPrefWidth()/2);
		previousGame.setLayoutY(100);
		
		// creating the board, setting all wins to 0, and setting the user as the player
		board = new Square[3][3];
		player = 1;
		winCounter = 0;
		loseCounter = 0;
		
		// each square of the board will be an object
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				board[i][j] = new Square();
				// this will make sure that it's placed in the correct order
				board[i][j].setX(root.getWidth()/2 - 150 + (100*j));
				board[i][j].setY(root.getHeight()/2 - 150 + (100*i));
				// we will simply add this to the root pane
				root.getChildren().add(board[i][j]);
			}
		}
		// then we will add all our labels to the root pane as well
		root.getChildren().addAll(playerWins, opponentWins, previousGame);
	}
	
	// this will just check if the board is full, which will return false if one of the squares has a 0 value (not played), otherwise returns true
	public boolean boardFull() {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				if (board[i][j].getPlayer() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	// clear each square in the board (using a function from the Square() class)
	public void clearBoard() {
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				board[i][j].clear();
			}
		}
	}
	
	// if a player gets a win, we will give the player one win in their win counter, and clear the board, as well as return to prevent double counting on wins if they have multiple directions which they win from
	public void checkWinGame() {
		
		// the first 4 statements will handle diagonal win conditions for each player 
		if (1 == board[0][0].getPlayer() && board[0][0].getPlayer() == board[1][1].getPlayer() && board[1][1].getPlayer() == board[2][2].getPlayer()) {
			winCounter++;
			playerWins.setText("BIG T: " + winCounter);
			previousGame.setText("PLAYER 1 WINS");
			clearBoard();
			return;
		}
		else if (2 == board[0][0].getPlayer() && board[0][0].getPlayer() == board[1][1].getPlayer() && board[1][1].getPlayer() == board[2][2].getPlayer()) {
			loseCounter++;
			opponentWins.setText("APA: " + loseCounter);
			previousGame.setText("PLAYER 2 WINS");
			clearBoard();
			return;
		}
		else if (1 == board[2][0].getPlayer() && board[0][2].getPlayer() == board[1][1].getPlayer() && board[1][1].getPlayer() == board[2][0].getPlayer()) {
			winCounter++;
			playerWins.setText("BIG T: " + winCounter);
			previousGame.setText("PLAYER 1 WINS");
			clearBoard();
			return;
		}
		else if (2 == board[2][0].getPlayer() && board[0][2].getPlayer() == board[1][1].getPlayer() && board[1][1].getPlayer() == board[2][0].getPlayer()) {
			loseCounter++;
			opponentWins.setText("APA: " + loseCounter);
			previousGame.setText("PLAYER 2 WINS");
			clearBoard();
			return;
		}
		
		// the statements in the loops will handle if any of the players got a win on the horizontal or vertical
		for (int rows = 0; rows < 3; rows ++) {
			if (2 == board[0][rows].getPlayer() && board[0][rows].getPlayer() == board[1][rows].getPlayer() && board[1][rows].getPlayer() == board[2][rows].getPlayer()) {
				loseCounter++;
				opponentWins.setText("APA: " + loseCounter);
				previousGame.setText("PLAYER 2 WINS");
				clearBoard();
				return;
			}
			else if (1 == board[0][rows].getPlayer() && board[0][rows].getPlayer() == board[1][rows].getPlayer() && board[1][rows].getPlayer() == board[2][rows].getPlayer()) {
				winCounter++;
				playerWins.setText("BIG T: " + winCounter);
				previousGame.setText("PLAYER 1 WINS");
				clearBoard();
				return;
			}
			else if (2 == board[rows][0].getPlayer() && board[rows][0].getPlayer() == board[rows][1].getPlayer() && board[rows][1].getPlayer() == board[rows][2].getPlayer()) {
				loseCounter++;
				opponentWins.setText("APA: " + loseCounter);
				previousGame.setText("PLAYER 2 WINS");
				clearBoard();
				return;
			}
			else if (1 == board[rows][0].getPlayer() && board[rows][0].getPlayer() == board[rows][1].getPlayer() && board[rows][1].getPlayer() == board[rows][2].getPlayer()) {
				winCounter++;
				playerWins.setText("BIG T: " + winCounter);
				previousGame.setText("PLAYER 1 WINS");
				clearBoard();
				return;
			}
		}
		// if the board is full, we will just say that nobody wins and clear the board
		if (boardFull()) {
			previousGame.setText("TIE: NOBODY WINS");
			clearBoard();
		}
	}
	
	public void room4() {
		// clearing the board
		root.getChildren().clear();
		
		
		// display the final room's alert
		Alert room4Alert = new Alert(AlertType.INFORMATION);
		room4Alert.setHeaderText(null);
		room4Alert.setTitle("TYLER1'S CHRISTMAS FIASCO");
		room4Alert.setContentText("After defeating APA in a legendary series of Tic Tac Toe, APA revealed that the thief was none other than the Russian Spy himself, Nelson. Upon hearing this, Tyler began his voyage towards Nelson's $9.8 quadrillion mansion which is on Mars. It was a difficult trip without Red Rocket, but Tyler made it work in about 45 minutes. Unfortunately, this means that the time is 11:59:29, which means he only has 30 seconds left to retrieve Red Rocket, 1 second to come back to Earth, and then he has to begin distributing the presents\n\n"
				+ "Luckily, through his mastered observation skills through hours of solo queue, he has recognized patterns in Nelson's behaviour and physical colour change, different colours mean different phases\n\n"
				+ "- When Nelson is Green; he is tired and just wants to sit idle in a spot.\n"
				+ "- When Nelson is Red; he wants to go outside, so he will begin running towards Tyler and colliding with him, Tyler has to avoid him or he loses health\n"
				+ "- When Nelson is Blue; he needs to go to the washroom, and he will drop litter everywhere if Tyler does not handle him, Tyler has to avoid the litter or else he loses health\n"
				+ "- You can move Tyler left and right with the arrow keys, as well as aim your mouse and shoot treats at Nelson\n"
				+ "- You will defeat him if you get through all three forms and he gives up\n"
				+ "- You will lose if the time runs out, or Tyler runs out of health.\n\n"
				+ "Tyler will need to complete this mission in only 30 seconds, when you are ready for the task, click OK, and throw a treat at Nelson once to begin.");
		room4Alert.setGraphic(new ImageView(new Image("file:room4icon.png")));
		room4Alert.showAndWait();
		
		// setting the variable for room4 to true (used in the start() method)
		room4 = true;
		
		// creating nelson at the top center
		nelson = new Nelson();
		nelson.setX(scene.getWidth()/2 - nelson.getWidth()/2);
		
		// initializing our treat and litter arraylists
		bullets = new ArrayList<Treat>();
		litter = new ArrayList<Litter>();
		
		//setting tyler to the bottom center of the screen
		tyler = new Tyler();
		tyler.setX(scene.getWidth()/2 - tyler.getWidth()/2);
		tyler.setY(scene.getHeight() - tyler.getHeight());
		
		// setting a label displaying the number of seconds left in the game, at the top left
		duration = new Label("REMAINING SECONDS: " + seconds);
		duration.setPrefSize(300, 50);
		duration.setAlignment(Pos.CENTER_LEFT);
		duration.setTextFill(Color.WHITE);
		duration.setFont(Font.font("Times New Roman", 24));
		
		// setting a label which displays the HP which the player has left, at the top right
		hpRemaining = new Label("HP: " + health);
		hpRemaining.setPrefSize(300, 50);
		hpRemaining.setLayoutX(scene.getWidth()-hpRemaining.getPrefWidth());
		hpRemaining.setAlignment(Pos.CENTER_RIGHT);
		hpRemaining.setTextFill(Color.WHITE);
		hpRemaining.setFont(Font.font("Times New Roman", 24));
		
		// starting our animationtimers
		nelson.start();
		hitpoints = 1;
		animateNelson.start();
		animateTyler.start();
		
		// setting the background image
		ivBg.setImage(new Image("file:room4bg.jpg"));
		// adding everything to the root pane
		root.getChildren().addAll(ivBg, nelson, tyler, duration, hpRemaining);
	}
	
	
	
	public void lose() {
		// if they lose, we will stop all timers, and show an alert telling them that they lost, and leave the game
		animateNelson.stop();
		animateBullet.stop();
		animateTyler.stop();
		litterClock.stop();
		deadlineClock.stop();
		Platform.runLater(new Runnable() {
			public void run() {
				Alert loseAlert = new Alert(AlertType.INFORMATION);
				loseAlert.setHeaderText(null);
				loseAlert.setTitle("TYLER1'S CHRISTMAS FIASCO");
				loseAlert.setContentText("Uh oh, doesn't look like Tyler will have enough time to make it to save Christmas. Looks like the kids won't get their presents this year.");
				loseAlert.setGraphic(new ImageView(new Image("file:sadge.png")));
				loseAlert.setWidth(400);
				loseAlert.setHeight(300);
				loseAlert.showAndWait();
				System.exit(0);
			}
		});
	}
	
	public void win() {
		// if they win, we will stop all timers, and show an alert telling them that they won
		animateNelson.stop();
		animateBullet.stop();
		animateTyler.stop();
		litterClock.stop();
		deadlineClock.stop();
		Platform.runLater(new Runnable() {
			public void run() {
				Alert winAlert = new Alert(AlertType.INFORMATION);
				winAlert.setHeaderText(null);
				winAlert.setTitle("TYLER1'S CHRISTMAS FIASCO");
				winAlert.setContentText("Nelson is full from eating all of those treats, so he gave back Red Rocket. Now Tyler can zoom around the world handing out presents! You win!");
				winAlert.setGraphic(new ImageView(new Image("file:win.png")));
				winAlert.setWidth(400);
				winAlert.setHeight(300);
				winAlert.showAndWait();
				
				// then take the user's name from a textinputdialog
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("JG KINGDOM");
				dialog.setHeaderText(null);
				dialog.setGraphic(new ImageView(new Image("file:t1.jpg")));
				dialog.setContentText("PLEASE ENTER YOUR NAME, TO STORE IN THE HISTORY BOOKS");
				
				Optional<String> result = dialog.showAndWait();
				
				
				// store the values in a file called scores.txt
				File f = new File("scores.txt");
				try {
					if (!f.exists()) {
						f.createNewFile();
					}

					FileWriter fw = new FileWriter(f, true);
					BufferedWriter bw = new BufferedWriter(fw);
					
					bw.append(result.get());
					bw.newLine();
					bw.append(Integer.toString(seconds));
					bw.newLine();
					
					bw.close();
					fw.close();
				}
				catch (Exception e) {
					
				}
				
				// then check if we got the user's name, and then store all the scores and names from the file into an arraylist
				if (result.isPresent()) {
					// the alert
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					
					ArrayList<String> names = new ArrayList<String>();
					try {
						// create the file if it doesnt exist
						if (!f.exists()) {
							f.createNewFile();
						}
						FileReader fr = new FileReader(f);
						BufferedReader br = new BufferedReader(fr);
						
						// the string namescore will store both the name along with the score of that player
						// then this value will be appended to an arraylist
						String namescore = "";
						String str;
						int line = 0;
						while ((str = br.readLine())!= null) {
							line++;
							
							// on every even numbered line, the score of the player is stored, so we want to space out the name from the score when we display the string, then we will concatenate the values, add this to the arraylist, and reset the namescore for the next name + score combo
							if (line % 2 == 0) {
								namescore += "								";
								namescore += str;
								names.add(namescore);
								namescore = "";
							}
							else {
								// if it is on an odd line, we will just append the value to the namescore variable
								namescore += str;
							}
							
						}
						
						br.close();
						fr.close();
					}
					catch (Exception e) {
						
					}
					
					alert.setContentText("Congratulations, here is the list of people in order of who completed the last round, along with their time (in seconds):\n");
					for (int i = 0; i < names.size(); i ++) {
						// adding all of our name + score combos that we determined above
						alert.setContentText(alert.getContentText() + names.get(i) + "\n");
					}
					// displaying the alert, and then quitting the game
					alert.setGraphic(new ImageView(new Image("file:win.jpg")));
					alert.showAndWait();
					System.exit(0);
				}
				
				
			}
		});
	}
	
	// this method will run every time a checkbox is selected or deselected
	public void handle(ActionEvent e) {
		
		// the handle method will loop through each checkbox in the checkbox array
		for (int i = 0; i < selections.length; i ++) {
			// we will start by clearing the textfield
			sortedList.clear();
			
			// then we will check if the selected item is not in our arraylist, in which case we will add it to the end
			if (selections[i].isSelected() && sortedArr.contains(selections[i].getText()) == false) {
				sortedArr.add(selections[i].getText());
			}
			// if there is an item in the arraylist which is not selected anymore, we will remove that item from the arraylist
			else if (selections[i].isSelected() == false && sortedArr.contains(selections[i].getText())) {
				sortedArr.remove(sortedArr.indexOf(selections[i].getText()));
			}
			// then we will display all of these items on the textarea, in the order they were clicked
			for (int j = 0; j < sortedArr.size(); j ++) {
				sortedList.appendText(j+1 + ". " + sortedArr.get(j) + "\n");
			}
			
		}
		// while the arraylist is not full, we will keep the button disabled
		if (sortedArr.size() == naughtyList.length) {
			okR2.setDisable(false);
		}
		else {
			// as soon as it becomes full, we will enable the button
			okR2.setDisable(true);
		}
		
	}
	
	// the insertion sort method will take a string array
	public void InsertionSort(String[] data) {
		
		// we will loop through element 1 to the end of the array, and this value will be called "end"
		for (int end = 1; end < data.length; end++) {
			// setting a variable equal to the value of the array at index "end"
			String item = data[end];
			int i = end;
			
			// making sure that the loop doesnt run if i <= 0 (because reducing it by 1 would give an index error)
			// then we compare the two strings to see if the one in lowest alphabetical order is our selected item
			while (i > 0 && item.compareTo(data[i-1]) < 0) {
				// if it runs true, we would move the larger alphabetical value to the right
				data[i] = data[i-1];
				// reduce the index by 1, so we can maybe compare again to the value left of it
				i--;			
			}
			// assigning the value of the sorted item to the left side of the array
			data[i] = item;
			
		}
		//saving this to our program
		algorithmSort = data;
	}
}
