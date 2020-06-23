package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.BOMBS;
import model.BURGER;
import model.Clock;
import model.DANGEROUS;
import model.DONUTS;
import model.FATAL;
import model.SmallInfoLabel;
import model.WATERMELON;
import model.FRUITS;
import model.PINEAPPLE;
import model.STRAWBERRY;


public class GameView {
	boolean up1 = true;
	boolean up2  =true;
	boolean up3 = true;
	boolean up4 = true;
	boolean up5 = true;
	boolean up6 = true;
	boolean up7 = true;
	int i;
	int flag = 0;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private static final int GAME_WIDTH = 1024;
	private static final int GAME_HEIGHT = 600;
	private Stage menuStage;
	private GridPane gridpane1;
	private GridPane gridpane2;
	private final static String BACKGROUND_IMAGE = "view/resources/background2.png";	
	private AnimationTimer gameTimer;
	private final static String GAME_OVER = "view/resources/game_over.png";
	private final static String PLUS_ONE = "view/resources/plus.png";
	private ImageView[] lifes;
	Random randomPositionGenerator;
	private ImageView gameOver;
	private ImageView plusOne;
	private SmallInfoLabel scoresLabel;
	private Text bestLabel;
	private int life;
	private int scores;
	 int best;
	private final static String LIFE_IMAGE = "view/resources/life.png";
	private Clock timer = new Clock();
	public static Image cursorming = new Image("view/resources/sword2.png");
	private ImageView closeButton,restartButton;
	ArrayList<FRUITS> fruits=new ArrayList();
	private FRUITS f1;
	private FRUITS f2;
	private FRUITS f3;
	private FRUITS sf1;
	private FRUITS sf2;
	private ViewNinja stagee;
	ArrayList<BOMBS> bombs = new ArrayList();
	private BOMBS fatalBomb;
	private BOMBS dangerousBomb;
	private Formatter x;
	Media gameSound = new Media(new File("src/view/resources/audio/aha.mp3").toURI().toString());
	MediaPlayer mediaPlayerGame = new MediaPlayer(gameSound);
	public GameView() {
		fruits.add(new WATERMELON());
		fruits.add(new STRAWBERRY());
		fruits.add(new PINEAPPLE());
		fruits.add(new BURGER());
		fruits.add(new DONUTS());
		bombs.add(new FATAL());
		bombs.add(new DANGEROUS());
		initializeStage();
		randomPositionGenerator = new Random();
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane , GAME_WIDTH , GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.initStyle(StageStyle.TRANSPARENT);
		gameStage.setScene(gameScene);	
		gamePane.setCursor(new ImageCursor(cursorming));
		
		ImageView restartButton = new ImageView("view/resources/buttons/flatDark44.png");
		
	}
	
	private void initialize() {
		f1 = fruits.get(0);
		f2 = fruits.get(1);
		f3 = fruits.get(2);
		sf1 = fruits.get(3);
		sf2 = fruits.get(4);
		f1.setSliced(false);
		f2.setSliced(false);
		f3.setSliced(false);
		sf1.setSliced(false);
		sf2.setSliced(false);
		fatalBomb = bombs.get(0);
		dangerousBomb = bombs.get(1);
		
	}
	
	public void createEasyGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		initialize();
		createGameElements();
		createGameLoopEasy();
		gameStage.show();
		Save();
	}
	public void createMediumGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		initialize();
		createGameElements();
		createGameLoopMedium();
		gameStage.show();
	}
	public void createaHardGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		initialize();
		createGameElements();
		createGameLoopHard();
		gameStage.show();
	}
	private void createGameElements() {
		
		mediaPlayerGame.setVolume(1);
		mediaPlayerGame.play();
		mediaPlayerGame.setCycleCount(2);
		ImageView closeButton = new ImageView("view/resources/buttons/flatDark46.png");
		closeButton.setLayoutX(10);
		closeButton.setLayoutY(15);
		closeButton.setFitHeight(50);
		closeButton.setFitWidth(50);
		closeButton.setOnMouseEntered(e->{
	            closeButton.setEffect(new DropShadow());
	     });
	       closeButton.setOnMouseExited(e->{
	            closeButton.setEffect(null);
	     });
		closeButton.setOnMouseClicked(e->{
			 gameStage.close();
		});
		ImageView restartButton = new ImageView("view/resources/buttons/flatDark44.png");
		restartButton.setLayoutX(10);
		restartButton.setLayoutY(70);
		restartButton.setFitHeight(50);
		restartButton.setFitWidth(50);
		restartButton.setOnMouseEntered(e->{
			restartButton.setEffect(new DropShadow());
	     });
		restartButton.setOnMouseExited(e->{
			restartButton.setEffect(null);
	     });
		
		//restart(restartButton);
		gamePane.getChildren().addAll(closeButton,restartButton);
		life = 2;
		scores = 0;
		//f1.getWholeFruit().setLayoutX(500);
		//f1.getWholeFruit().setLayoutY(400);
		Collections.shuffle(bombs);
		fatalBomb.getBomb().setLayoutY(850);
		dangerousBomb.getBomb().setLayoutY(800);
		setPosition(f1.getWholeFruit());
		setPosition(f2.getWholeFruit());
		setPosition(f3.getWholeFruit());
		setPosition(fatalBomb.getBomb());
		setPosition(dangerousBomb.getBomb());
		setPosition(sf1.getWholeFruit());
		setPosition(sf2.getWholeFruit());
		gamePane.getChildren().addAll(f1.getWholeFruit(),f2.getWholeFruit(),f3.getWholeFruit(),fatalBomb.getBomb(),
				dangerousBomb.getBomb(),sf1.getWholeFruit(),sf2.getWholeFruit());
		f1.getWholeFruit().setLayoutY(700);
		f2.getWholeFruit().setLayoutY(750);
		f3.getWholeFruit().setLayoutY(610);
		sf1.getWholeFruit().setLayoutY(720);
		sf2.getWholeFruit().setLayoutY(770);
		fatalBomb.getBomb().setLayoutY(850);
		dangerousBomb.getBomb().setLayoutY(800);
		gamePane.getChildren().addAll(f1.getSlicedFruit(),f2.getSlicedFruit(),f3.getSlicedFruit());
		f1.getSlicedFruit().setVisible(false);
		f2.getSlicedFruit().setVisible(false);
		f3.getSlicedFruit().setVisible(false);
		f1.getWholeFruit().setOnMouseClicked(e->{
			playSliceSouncd();
			up1=false;
			//playSliceMusic();
			f1.setSliced(true);
			f1.getSlicedFruit().setLayoutY(f1.getWholeFruit().getLayoutY());
			f1.getSlicedFruit().setLayoutX(f1.getWholeFruit().getLayoutX());
			scores++;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best++;
			Save();
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
			plusOne = new ImageView(PLUS_ONE);
			plusOne.setLayoutX(500);
			plusOne.setLayoutY(50);
			plusOne.setFitHeight(60);
			plusOne.setFitWidth(60);
			//gamePane.getChildren().add(plusOne);
		}); 
		/*gameScene.setOnMouseDragged(e->{
			double x=e.getX();
			if(x>f1.getWholeFruit().getX() && x<=f1.getWholeFruit().getX()+100) {
			System.out.println("Swiped ");
			f1.setSliced(true);
			f1.getSlicedFruit().setLayoutY(f1.getWholeFruit().getLayoutY());
			f1.getSlicedFruit().setLayoutX(f1.getWholeFruit().getLayoutX());
			scores++;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best++;
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
			up1=false;
			}
		});*/ 
		
		f2.getWholeFruit().setOnMouseClicked(e->{
			 playSliceSouncd();
			up2=false;
			f2.setSliced(true);
			f2.getSlicedFruit().setLayoutY(f2.getWholeFruit().getLayoutY());
			f2.getSlicedFruit().setLayoutX(f2.getWholeFruit().getLayoutX());
			scores++;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best++;
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
			plusOne = new ImageView(PLUS_ONE);
			plusOne.setLayoutX(500);
			plusOne.setLayoutY(50);
			plusOne.setFitHeight(60);
			plusOne.setFitWidth(60);
			//gamePane.getChildren().add(plusOne);
		});
		f3.getWholeFruit().setOnMouseClicked(e->{
			playSliceSouncd();
			up3=false;
			f3.setSliced(true);
			f3.getSlicedFruit().setLayoutY(f3.getWholeFruit().getLayoutY());
			f3.getSlicedFruit().setLayoutX(f3.getWholeFruit().getLayoutX());
			scores++;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best++;
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
			plusOne = new ImageView(PLUS_ONE);
			plusOne.setLayoutX(500);
			plusOne.setLayoutY(50);
			plusOne.setFitHeight(60);
			plusOne.setFitWidth(60);
		});
		fatalBomb.getBomb().setOnMouseClicked(e->{
			showBoom(fatalBomb.getBomb());
			showGameOver();
			playBoomMusic();
			mediaPlayerGame.stop();
		});
		dangerousBomb.getBomb().setOnMouseClicked(e->{
			Media boomSound = new Media(new File("src/view/resources/audio/BUZZZ.mp3").toURI().toString());
			MediaPlayer mediaPlayer2 = new MediaPlayer(boomSound);
			mediaPlayer2.setVolume(0.5);
			mediaPlayer2.play();
			mediaPlayer2.setCycleCount(1);
			gamePane.getChildren().remove(lifes[life]);
			life--;
			sliceDangerous(dangerousBomb.getBomb());
			dangerousBomb.getBomb().setVisible(false);
			if(life < 0 ) {
				mediaPlayer2.stop();
				playBoomMusic();
				showBoom(dangerousBomb.getBomb());
				mediaPlayerGame.stop();
				showGameOver();
			}
		});
		sf1.getWholeFruit().setOnMouseClicked(e->{
			playSpecialSliceSouncd();
			sliceSpecial(sf1.getWholeFruit());
			sf1.getWholeFruit().setVisible(false);
			scores+=2;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best+=2;
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
		});
		sf2.getWholeFruit().setOnMouseClicked(e->{
			playSpecialSliceSouncd();
			sliceSpecial(sf2.getWholeFruit());
			sf2.getWholeFruit().setVisible(false);
			scores+=2;
			String textToSet = "SCORE: ";
			scoresLabel.setText(textToSet + scores);
			best+=2;
			String textToBest = "BEST: ";
			bestLabel.setText(textToBest + best);
		});
		lifes = new ImageView[3];
		for(int i = 0; i < lifes.length; i++) {
			lifes[i]= new ImageView(LIFE_IMAGE);
			lifes[i].setFitWidth(50);
			lifes[i].setFitHeight(50);
            lifes[i].setLayoutX(870 + (i*50));
            lifes[i].setLayoutY(15);
			gamePane.getChildren().add(lifes[i]);	
		}
		scoresLabel = new SmallInfoLabel("SCORE: 0");
		scoresLabel.setLayoutX(256);
		scoresLabel.setLayoutY(0);
		bestLabel = new Text("BEST: 0");
		bestLabel.setLayoutX(450);
		bestLabel.setLayoutY(100);
		bestLabel.setFill(Color.YELLOW); 
		bestLabel.setStroke(Color.YELLOW);
		try {
			bestLabel.setFont(Font.loadFont(new FileInputStream("src/model/resources/belligerent.ttf"),30));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			bestLabel.setFont(Font.font("Arial",15));
			System.out.println("NOT FOUND");
		}
		gamePane.getChildren().addAll(scoresLabel);
		timer = new Clock();
		gamePane.getChildren().add(timer);
	}
		
	
	private void createGameLoopEasy() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {	
				moveEasy();
			}
		};
		
		gameTimer.start();
		
		PauseTransition wait = new PauseTransition(Duration.seconds(60));
        wait.setOnFinished((e) -> {
        	timeOver();
        });
        wait.play();
	}
	private void createGameLoopMedium() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {	
				moveMedium();
			}
		};
		
		gameTimer.start();
		
		PauseTransition wait = new PauseTransition(Duration.seconds(60));
        wait.setOnFinished((e) -> {
        	timeOver();
        });
        wait.play();
	}
	private void createGameLoopHard() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {	
				moveHard();
			}
		};
		
		gameTimer.start();
		
		PauseTransition wait = new PauseTransition(Duration.seconds(60));
        wait.setOnFinished((e) -> {
        	timeOver();
        });
        wait.play();
	}
	

	private void setPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(800));	
		}
	
	
	public void moveEasy() {
		if(f1.getWholeFruit().getLayoutY()<100)
			up1=false;
		if(up1==false)
			f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()+3));
		
		else
				f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()-3));
		        f1.getWholeFruit().setRotate(f1.getWholeFruit().getRotate()+4);
		if(f1.getWholeFruit().getLayoutY()==700) {
			up1=true;
			setPosition(f1.getWholeFruit());
		}
		if(f1.isSliced()==true) {
			f1.getSlicedFruit().setVisible(true);
			f1.getWholeFruit().setVisible(false);
			f1.getSlicedFruit().setLayoutY((f1.getSlicedFruit().getLayoutY()+3));
			f1.getSlicedFruit().setRotate(f1.getSlicedFruit().getRotate()+6);
			}	
			if(f1.getSlicedFruit().getLayoutY()>602) {
			    f1.getWholeFruit().setVisible(true);
				f1.setSliced(false);	
			}
			if(f1.isSliced()==false) {
				//System.out.println("HERE");
				if(f1.getWholeFruit().getLayoutY()==601 && up1==false) {
					//System.out.println("wa2a3t");
					gamePane.getChildren().remove(lifes[life]);
					life--;
					if(life < 0 ) {
						showGameOver();
					}
				}
			}
		
		if(f2.getWholeFruit().getLayoutY()<100)
			up2=false;
		if(up2==false)
			f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()+3));
		
		else
				f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()-3));
				f2.getWholeFruit().setRotate(f2.getWholeFruit().getRotate()+4);
		if(f2.getWholeFruit().getLayoutY()==750) {
			up2=true;
			setPosition(f2.getWholeFruit());
		}
		if(f2.isSliced()==true) {
			f2.getSlicedFruit().setVisible(true);
			f2.getWholeFruit().setVisible(false);
			f2.getSlicedFruit().setLayoutY((f2.getSlicedFruit().getLayoutY()+3));
			f2.getSlicedFruit().setRotate(f2.getSlicedFruit().getRotate()+6);
			}	
		if(f2.getSlicedFruit().getLayoutY()>604) {
		    f2.getWholeFruit().setVisible(true);
			f2.setSliced(false);	
		}
		if(f2.isSliced()==false) {
			if(f2.getWholeFruit().getLayoutY()==603 && up2==false) {
				gamePane.getChildren().remove(lifes[life]);
				life--;
				if(life < 0 ) {
					showGameOver();
				}
			}
		}
		
		if(f3.getWholeFruit().getLayoutY()<100)
			up3=false;
		if(up3==false)
			f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()+3));
		
		else
				f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()-3));
				f3.getWholeFruit().setRotate(f3.getWholeFruit().getRotate()+4);
		if(f3.getWholeFruit().getLayoutY()==610) {
			up3=true;
			setPosition(f3.getWholeFruit());
		//	gamePane.getChildren().remove(lifes[life]);
		//	life--;
		//	if(life<0){
			//	showGameOver();
		//	}
		}
		if(f3.isSliced()==true) {
			f3.getSlicedFruit().setVisible(true);
			f3.getWholeFruit().setVisible(false);
			f3.getSlicedFruit().setLayoutY((f3.getSlicedFruit().getLayoutY()+3));
			f3.getSlicedFruit().setRotate(f3.getSlicedFruit().getRotate()+6);
			}	
		if(f3.getSlicedFruit().getLayoutY()>609) {
		    f3.getWholeFruit().setVisible(true);
			f3.setSliced(false);	
		}
		if(f3.isSliced()==false) {
			if(f3.getWholeFruit().getLayoutY()==601 && up3==false) {
				//System.out.println("Wa23a");
				gamePane.getChildren().remove(lifes[life]);
				life--;
				if(life < 0 ) {
					showGameOver();
				}
			}
		}
		if(fatalBomb.getBomb().getLayoutY()<100)
			up4=false;
		if(up4==false)
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()+3));
		
		else
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()-3));
		    fatalBomb.getBomb().setRotate(fatalBomb.getBomb().getRotate()+4);
		if(fatalBomb.getBomb().getLayoutY()==850) {
			up4=true;
		}
		if(dangerousBomb.getBomb().getLayoutY()<100)
			up5=false;
		if(up5==false)
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()+3));
		
		else
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()-3));
		    dangerousBomb.getBomb().setRotate(dangerousBomb.getBomb().getRotate()+4);
		if(dangerousBomb.getBomb().getLayoutY()==800) {
			up5=true;
		}
		if(dangerousBomb.getBomb().getLayoutY()>GAME_HEIGHT) {
			dangerousBomb.getBomb().setVisible(true);
		}
		if(sf1.getWholeFruit().getLayoutY()<100)
			up6=false;
		if(up6==false)
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()+3));
		
		else
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()-3));
		    sf1.getWholeFruit().setRotate((sf1.getWholeFruit().getRotate()+4));
		if(sf1.getWholeFruit().getLayoutY()==720) {
			up6=true;
		}
		if(sf1.getWholeFruit().getLayoutY()>GAME_HEIGHT) {
			sf1.getWholeFruit().setVisible(true);
		}
		if(sf2.getWholeFruit().getLayoutY()<100)
			up7=false;
		if(up7==false)
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()+3));
		
		else
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()-3));
		    sf2.getWholeFruit().setRotate((sf2.getWholeFruit().getRotate()+4));
		if(sf2.getWholeFruit().getLayoutY()==770) {
			up7=true;
		}
		if(sf2.getWholeFruit().getLayoutY()>GAME_HEIGHT) {
			sf2.getWholeFruit().setVisible(true);
		}
	}
	
	public void moveMedium() {
		if(f1.getWholeFruit().getLayoutY()<100)
			up1=false;
		if(up1==false)
			f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()+4.2));
		
		else
				f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()-4.2));
		        f1.getWholeFruit().setRotate(f1.getWholeFruit().getRotate()+4);
		if(f1.getWholeFruit().getLayoutY()==700) {
			up1=true;
			setPosition(f1.getWholeFruit());
		}
		if(f1.isSliced()==true) {
			f1.getSlicedFruit().setVisible(true);
			f1.getWholeFruit().setVisible(false);
			f1.getSlicedFruit().setLayoutY((f1.getSlicedFruit().getLayoutY()+4.2));
			f1.getSlicedFruit().setRotate(f1.getSlicedFruit().getRotate()+6);
			}	
			if(f1.getSlicedFruit().getLayoutY()>602) {
			    f1.getWholeFruit().setVisible(true);
				f1.setSliced(false);	
			}
			if(f1.isSliced()==false) {
				//System.out.println("HERE");
				if(f1.getWholeFruit().getLayoutY()==600 && up1==false) {
					//System.out.println("wa2a3t");
					gamePane.getChildren().remove(lifes[life]);
					life--;
					if(life < 0 ) {
						showGameOver();
					}
				}
			}
		
		if(f2.getWholeFruit().getLayoutY()<100)
			up2=false;
		if(up2==false)
			f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()+4.2));
		
		else
				f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()-4.2));
				f2.getWholeFruit().setRotate(f2.getWholeFruit().getRotate()+4);
		if(f2.getWholeFruit().getLayoutY()==750) {
			up2=true;
			setPosition(f2.getWholeFruit());
		}
		if(f2.isSliced()==true) {
			f2.getSlicedFruit().setVisible(true);
			f2.getWholeFruit().setVisible(false);
			f2.getSlicedFruit().setLayoutY((f2.getSlicedFruit().getLayoutY()+4.2));
			f2.getSlicedFruit().setRotate(f2.getSlicedFruit().getRotate()+6);
			}	
		if(f2.getSlicedFruit().getLayoutY()>604) {
		    f2.getWholeFruit().setVisible(true);
			f2.setSliced(false);	
		}
		if(f2.isSliced()==false) {
			if(f2.getWholeFruit().getLayoutY()==603 && up2==false) {
				gamePane.getChildren().remove(lifes[life]);
				life--;
				if(life < 0 ) {
					showGameOver();
				}
			}
		}
		
		if(f3.getWholeFruit().getLayoutY()<100)
			up3=false;
		if(up3==false)
			f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()+4.2));
		
		else
				f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()-4.2));
				f3.getWholeFruit().setRotate(f3.getWholeFruit().getRotate()+4);
		if(f3.getWholeFruit().getLayoutY()==610) {
			up3=true;
			setPosition(f3.getWholeFruit());
		//	gamePane.getChildren().remove(lifes[life]);
		//	life--;
		//	if(life<0){
			//	showGameOver();
		//	}
		}
		if(f3.isSliced()==true) {
			f3.getSlicedFruit().setVisible(true);
			f3.getWholeFruit().setVisible(false);
			f3.getSlicedFruit().setLayoutY((f3.getSlicedFruit().getLayoutY()+4.2));
			f3.getSlicedFruit().setRotate(f3.getSlicedFruit().getRotate()+6);
			}	
		if(f3.getSlicedFruit().getLayoutY()>609) {
		    f3.getWholeFruit().setVisible(true);
			f3.setSliced(false);	
		}
		if(f3.isSliced()==false) {
			if(f3.getWholeFruit().getLayoutY()==601 && up3==false) {
				//System.out.println("Wa23a");
				gamePane.getChildren().remove(lifes[life]);
				life--;
				if(life < 0 ) {
					showGameOver();
				}
			}
		}
		if(fatalBomb.getBomb().getLayoutY()<100)
			up4=false;
		if(up4==false)
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()+4.2));
		
		else
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()-4.2));
		    fatalBomb.getBomb().setRotate(fatalBomb.getBomb().getRotate()+4);
		if(fatalBomb.getBomb().getLayoutY()==850) {
			up4=true;
		}
		if(dangerousBomb.getBomb().getLayoutY()<100)
			up5=false;
		if(up5==false)
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()+4.2));
		
		else
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()-4.2));
		    dangerousBomb.getBomb().setRotate(dangerousBomb.getBomb().getRotate()+4);
		if(dangerousBomb.getBomb().getLayoutY()==800) {
			up5=true;
		}
		if(dangerousBomb.getBomb().getLayoutY()>GAME_HEIGHT) {
			dangerousBomb.getBomb().setVisible(true);
		}
		if(sf1.getWholeFruit().getLayoutY()<100)
			up6=false;
		if(up6==false)
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()+4.2));
		
		else
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()-4.2));
		    sf1.getWholeFruit().setRotate((sf1.getWholeFruit().getRotate()+4));
		if(sf1.getWholeFruit().getLayoutY()==720) {
			up6=true;
		}
		if(sf1.getWholeFruit().getLayoutY()>GAME_HEIGHT) {
			sf1.getWholeFruit().setVisible(true);
		}
		if(sf2.getWholeFruit().getLayoutY()<100)
			up7=false;
		if(up7==false)
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()+4.2));
		
		else
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()-4.2));
		    sf2.getWholeFruit().setRotate((sf2.getWholeFruit().getRotate()+4));
		if(sf2.getWholeFruit().getLayoutY()==770) {
			up7=true;
		}
		if(sf2.getWholeFruit().getLayoutY()>GAME_HEIGHT) {
			sf2.getWholeFruit().setVisible(true);
		}
		
	}
	public void moveHard() {
		if(f1.getWholeFruit().getLayoutY()<100)
			up1=false;
		if(up1==false)
			f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()+6));
		
		else
				f1.getWholeFruit().setLayoutY((f1.getWholeFruit().getLayoutY()-6));
		        f1.getWholeFruit().setRotate(f1.getWholeFruit().getRotate()+6);
		if(f1.getWholeFruit().getLayoutY()==700) {
			up1=true;
			setPosition(f1.getWholeFruit());
		}
		if(f1.isSliced()==true) {
			f1.getSlicedFruit().setVisible(true);
			f1.getWholeFruit().setVisible(false);
			f1.getSlicedFruit().setLayoutY((f1.getSlicedFruit().getLayoutY()+6));
			f1.getSlicedFruit().setRotate(f1.getSlicedFruit().getRotate()+6);
			}	
			if(f1.getSlicedFruit().getLayoutY()>GAME_HEIGHT) {
			    f1.getWholeFruit().setVisible(true);
				f1.setSliced(false);	
			}
		
		if(f2.getWholeFruit().getLayoutY()<100)
			up2=false;
		if(up2==false)
			f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()+6));
		
		else
				f2.getWholeFruit().setLayoutY((f2.getWholeFruit().getLayoutY()-6));
				f2.getWholeFruit().setRotate(f2.getWholeFruit().getRotate()+4);
		if(f2.getWholeFruit().getLayoutY()==750) {
			up2=true;
			setPosition(f2.getWholeFruit());
		}
		if(f2.isSliced()==true) {
			f2.getSlicedFruit().setVisible(true);
			f2.getWholeFruit().setVisible(false);
			f2.getSlicedFruit().setLayoutY((f2.getSlicedFruit().getLayoutY()+6));
			f2.getSlicedFruit().setRotate(f2.getSlicedFruit().getRotate()+6);
			}	
			if(f2.getSlicedFruit().getLayoutY()>GAME_HEIGHT) {
			    f2.getWholeFruit().setVisible(true);
				f2.setSliced(false);	
			}
		
		if(f3.getWholeFruit().getLayoutY()<100)
			up3=false;
		if(up3==false)
			f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()+6));
		
		else
				f3.getWholeFruit().setLayoutY((f3.getWholeFruit().getLayoutY()-6));
				f3.getWholeFruit().setRotate(f3.getWholeFruit().getRotate()+4);
		if(f3.getWholeFruit().getLayoutY()==600) {
			up3=true;
			setPosition(f3.getWholeFruit());
		}
		if(f3.isSliced()==true) {
			f3.getSlicedFruit().setVisible(true);
			f3.getWholeFruit().setVisible(false);
			f3.getSlicedFruit().setLayoutY((f3.getSlicedFruit().getLayoutY()+6));
			f3.getSlicedFruit().setRotate(f3.getSlicedFruit().getRotate()+6);
			}	
			if(f3.getSlicedFruit().getLayoutY()>GAME_HEIGHT) {
			    f3.getWholeFruit().setVisible(true);
				f3.setSliced(false);	
			}
		if(fatalBomb.getBomb().getLayoutY()<100)
			up4=false;
		if(up4==false)
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()+6));
		
		else
			fatalBomb.getBomb().setLayoutY((fatalBomb.getBomb().getLayoutY()-6));
		    fatalBomb.getBomb().setRotate(fatalBomb.getBomb().getRotate()+4);
		if(fatalBomb.getBomb().getLayoutY()==850) {
			up4=true;
		}
		if(dangerousBomb.getBomb().getLayoutY()<100)
			up5=false;
		if(up5==false)
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()+6));
		
		else
			dangerousBomb.getBomb().setLayoutY((dangerousBomb.getBomb().getLayoutY()-6));
		    dangerousBomb.getBomb().setRotate(dangerousBomb.getBomb().getRotate()+4);
		if(dangerousBomb.getBomb().getLayoutY()==800) {
			up5=true;
		}
		if(sf1.getWholeFruit().getLayoutY()<100)
			up6=false;
		if(up6==false)
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()+6));
		
		else
			sf1.getWholeFruit().setLayoutY((sf1.getWholeFruit().getLayoutY()-6));
		    sf1.getWholeFruit().setRotate((sf1.getWholeFruit().getRotate()+4));
		if(sf1.getWholeFruit().getLayoutY()==720) {
			up6=true;
		}
		if(sf2.getWholeFruit().getLayoutY()<100)
			up7=false;
		if(up7==false)
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()+6));
		
		else
			sf2.getWholeFruit().setLayoutY((sf2.getWholeFruit().getLayoutY()-6));
		    sf2.getWholeFruit().setRotate((sf2.getWholeFruit().getRotate()+4));
		if(sf2.getWholeFruit().getLayoutY()==770) {
			up7=true;
		}
		
	}
	private void createBackground() {
			gridpane1 = new GridPane();
			gridpane2 = new GridPane();
			
			for(int i=0; i<12 ; i++) {
				ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
				ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
				GridPane.setConstraints(backgroundImage1, i, i);	
				GridPane.setConstraints(backgroundImage2, i, i);
				gridpane1.getChildren().add(backgroundImage1);
				gridpane2.getChildren().add(backgroundImage2);
				
			}
			
			gridpane2.setLayoutY(-1024);
			gamePane.getChildren().addAll(gridpane1 , gridpane2);
	}

	public boolean playGameOverMusic() {
		Media gameOverSound = new Media(new File("src/view/resources/audio/sadtrumpet.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(gameOverSound);
		mediaPlayer.setVolume(1);
		mediaPlayer.play();
		mediaPlayer.setCycleCount(1);
		mediaPlayerGame.stop();
		return true;
		
	}

	public void playBoomMusic() {
		Media boomSound = new Media(new File("src/view/resources/audio/boom.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(boomSound);
		mediaPlayer.setVolume(0.1);
		mediaPlayer.play();
		mediaPlayer.setCycleCount(1);
		
	}
	public void playSliceSouncd() {
		Media slicingSound = new Media(new File("src/view/resources/audio/Decapitated-SoundBible.com-1227556545.mp3").toURI().toString());
		MediaPlayer mediaPlayer3 = new MediaPlayer(slicingSound);
		mediaPlayer3.setVolume(0.5);
		mediaPlayer3.play();
		mediaPlayer3.setCycleCount(1);
	}
	public void playSpecialSliceSouncd() {
		Media slicingSound = new Media(new File("src/view/resources/audio/bonus.mp3").toURI().toString());
		MediaPlayer mediaPlayer3 = new MediaPlayer(slicingSound);
		mediaPlayer3.setVolume(0.5);
		mediaPlayer3.play();
		mediaPlayer3.setCycleCount(1);
	}
	public void showGameOver() {
		gameOver = new ImageView(GAME_OVER);
       	TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.6));
		transition.setNode(gameOver);
		transition.setToY(50);
		transition.setToZ(100);
		transition.setToX(256);
		transition.play();
		fatalBomb.getBomb().setVisible(false);
		gamePane.getChildren().addAll(gameOver);
		gamePane.getChildren().remove(timer);
		gameTimer.stop();
		playGameOverMusic();
	}
	public void timeOver() {
		ImageView timeOver = new ImageView("view/resources/timeup.png");
		timeOver.setFitHeight(400);
		timeOver.setFitWidth(400);
		Label timeup = new Label("TIME'S UP");
		timeup.setFont(Font.font("century gothic", FontWeight.BOLD, FontPosture.REGULAR, 90));
		timeup.setTextFill(Color.YELLOW);
		timeup.setLayoutX(timeOver.getLayoutX()+200);
		timeup.setLayoutY(timeOver.getLayoutY()+450);
       	TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.6));
		transition.setNode(timeOver);
		transition.setToY(120);
		transition.setToZ(100);
		transition.setToX(256);
		transition.play();
		TranslateTransition transition2 = new TranslateTransition();
		transition2.setDuration(Duration.seconds(0.6));
		transition2.setNode(timeup);
		transition2.setToY(50);
		transition2.setToZ(100);
		transition2.setToX(256);
		transition2.play();
		fatalBomb.getBomb().setVisible(false);
		gamePane.getChildren().addAll(timeOver,timeup);
		gamePane.getChildren().remove(timer);
		gameTimer.stop();
		playGameOverMusic();
	}
	public void showBoom(ImageView image) {
		ImageView explode = new ImageView("view/resources/boom.png");
		explode.setFitHeight(120);
		explode.setFitWidth(120);
		explode.setLayoutY(image.getLayoutY());
		explode.setLayoutX(image.getLayoutX());
		gamePane.getChildren().add(explode);
		ScaleTransition transs = new ScaleTransition();
		transs.setNode(explode);
		transs.setDuration(Duration.seconds(1));
		transs.setToX(3);
		transs.setToY(3);
		transs.setOnFinished(e->{
			FadeTransition fade = new FadeTransition(Duration.seconds(1),explode);
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.play();
		});
		//transs.setAutoReverse(true);
		transs.play();		
	}
	public boolean fallingFruit(ImageView image) {
		if(image.getLayoutY()>GAME_HEIGHT) {
			gamePane.getChildren().remove(lifes[life]);
			life--;
			if(life<0) {
				showGameOver();
			}
		}
		return true;
	}

	public void sliceSpecial(ImageView image) {
		ImageView wow = new ImageView("view/resources/wow.png");
		wow.setFitHeight(70);
		wow.setFitWidth(70);
		wow.setLayoutY(image.getLayoutY());
		wow.setLayoutX(image.getLayoutX());
		wow.setRotate(5);
		gamePane.getChildren().add(wow);
		ScaleTransition transs = new ScaleTransition();
		transs.setNode(wow);
		transs.setDuration(Duration.seconds(1));
		transs.setToX(3);
		transs.setToY(3);
		transs.setOnFinished(e->{
			FadeTransition fade = new FadeTransition(Duration.seconds(1),wow);
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.play();
		});
		transs.play();	
	}
	public void sliceDangerous(ImageView image) {
		ImageView ouch = new ImageView("view/resources/boom2.png");
		ouch.setFitHeight(70);
		ouch.setFitWidth(70);
		ouch.setLayoutY(image.getLayoutY());
		ouch.setLayoutX(image.getLayoutX());
		ouch.setRotate(5);
		gamePane.getChildren().add(ouch);
		ScaleTransition transs = new ScaleTransition();
		transs.setNode(ouch);
		transs.setDuration(Duration.seconds(1));
		transs.setToX(2);
		transs.setToY(2);
		transs.setOnFinished(e->{
			FadeTransition fade = new FadeTransition(Duration.seconds(1),ouch);
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.play();
		});
		transs.play();	
	}
	public void sliceShow(ImageView image) {
		ScaleTransition transs = new ScaleTransition();
		transs.setNode(image);
		transs.setDuration(Duration.seconds(1));
		transs.setToX(1);
		transs.setToY(1);
		transs.setOnFinished(e->{
			FadeTransition fade = new FadeTransition(Duration.seconds(1),image);
			fade.setFromValue(1);
			fade.setToValue(0);
			fade.play();
		});
		transs.play();	
	}

	public void Save() {
		SaveData data = new SaveData();
		data.HighScore = best;
		try {
			ResourceManager.save(data, "BestScore");
			System.out.println("HELLO");
		}
		catch(Exception e){
			System.out.println("ERROR NOT FOUND");
		}
	}
	public void Load() {
		
		try {
			SaveData data = (SaveData) ResourceManager.load("BestScore");
		    best = data.HighScore;
			}
		catch(Exception e){
			System.out.println("ERROR NOT FOUND");
		}
	}
	
}
