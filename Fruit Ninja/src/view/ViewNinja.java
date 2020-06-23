package view;


import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LEVELS;
import model.LevelPicker;
import model.NinjaButton;
import model.NinjaSubScene;
import model.SmallInfoLabel;
import model.WATERMELON;
import sun.util.logging.PlatformLogger.Level;

public class ViewNinja {
	private AnchorPane mainPane;
	private Scene mainScene;
	private AnchorPane levelPane;
	private Scene levelScene;
    public Stage mainStage;
    public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	List<NinjaButton> menuButtons;
    List<LevelPicker> levelsList;
    //private LEVELS choosenLevel;
    
    
    private NinjaSubScene newgameSubScene;
    
    public static Image cursorming = new Image("view/resources/sword2.png");
    
    public ViewNinja() {
    	menuButtons = new ArrayList<>();
    	mainPane = new AnchorPane();
    	mainScene = new Scene(mainPane,800,600);
    	levelPane = new AnchorPane();
    	levelScene = new Scene(levelPane,800,600);
    	mainStage = new Stage();
    	mainStage.setScene(mainScene);
    	createButtons();
    	createBackground();
    	createLevelBackground();
    	levelSceneElements();
    	mainPane.setCursor(new ImageCursor(cursorming));
    	levelPane.setCursor(new ImageCursor(cursorming));
    	
    }
    
    public Stage getMainStage() {
    	return mainStage;
    }
    
    private void levelSceneElements() {
    	SmallInfoLabel choose = new SmallInfoLabel("CHOOSE YOUR LEVEL");
    	choose.setLayoutX(150);
    	choose.setLayoutY(10);
    	levelPane.getChildren().add(choose);
    	ImageView backButton = new ImageView("view/resources/buttons/flatDark23.png");
    	backButton.setLayoutX(10);
    	backButton.setLayoutY(15);
    	backButton.setFitHeight(50);
    	backButton.setFitWidth(50);
    	backButton.setOnMouseEntered(e->{
    		backButton.setEffect(new DropShadow());
	     });
    	backButton.setOnMouseExited(e->{
    		backButton.setEffect(null);
	     });
    	backButton.setOnMouseClicked(e->{
			 mainStage.setScene(mainScene);
		});
		levelPane.getChildren().add(backButton);
		ImageView easy = new ImageView("view/resources/levels/easy.png");
		easy.setLayoutX(10);
		easy.setLayoutY(160);
		easy.setFitHeight(200);
		easy.setFitWidth(250);
		easy.setOnMouseEntered(e->{
			easy.setEffect(new DropShadow());
	     });
		easy.setOnMouseExited(e->{
			easy.setEffect(null);
	     });
		easy.setOnMouseClicked(e->{
			GameView gameManager = new GameView();
    		gameManager.createEasyGame(mainStage);
		});
		SmallInfoLabel easyLabel = new SmallInfoLabel("EASY");
		easyLabel.setLayoutX(easy.getLayoutX()-120);
		easyLabel.setLayoutY(easy.getLayoutY()+200);
		levelPane.getChildren().addAll(easy,easyLabel);	
		ImageView medium = new ImageView("view/resources/levels/medium.png");
		medium.setLayoutX(280);
		medium.setLayoutY(160);
		medium.setFitHeight(200);
		medium.setFitWidth(200);
		medium.setOnMouseEntered(e->{
			medium.setEffect(new DropShadow());
	     });
		medium.setOnMouseExited(e->{
			medium.setEffect(null);
	     });
		medium.setOnMouseClicked(e->{
			GameView gameManager = new GameView();
    		gameManager.createMediumGame(mainStage);
		});
		SmallInfoLabel mediumLabel = new SmallInfoLabel("MEDIUM");
		mediumLabel.setLayoutX(medium.getLayoutX()-130);
		mediumLabel.setLayoutY(medium.getLayoutY()+200);
		levelPane.getChildren().addAll(medium,mediumLabel);
		ImageView hard = new ImageView("view/resources/levels/hard.png");
		hard.setLayoutX(520);
		hard.setLayoutY(160);
		hard.setFitHeight(200);
		hard.setFitWidth(200);
		hard.setOnMouseEntered(e->{
			hard.setEffect(new DropShadow());
	     });
		hard.setOnMouseExited(e->{
			hard.setEffect(null);
	     });
		hard.setOnMouseClicked(e->{
			GameView gameManager = new GameView();
    		gameManager.createaHardGame(mainStage);
		});
		SmallInfoLabel hardLabel = new SmallInfoLabel("HARD");
		hardLabel.setLayoutX(hard.getLayoutX()-130);
		hardLabel.setLayoutY(hard.getLayoutY()+200);
		levelPane.getChildren().addAll(hard,hardLabel);
    }
    
    private void addMenuButton(NinjaButton button) {
    	button.setLayoutX(300);
    	button.setLayoutY(300 + menuButtons.size() * 70);
    	menuButtons.add(button);
    	mainPane.getChildren().add(button);
    }
    
      private void createLevelChooserSubScene() {
    	newgameSubScene = new NinjaSubScene();
    	mainPane.getChildren().add(newgameSubScene);
    	//newgameSubScene.getPane().getChildren().add(createLevelsToChoose());
    	
    }
    
    private HBox createLevelsToChoose() {
    	HBox box = new HBox();
    	box.setSpacing(10);
    	levelsList = new ArrayList<>();
    	for(LEVELS level : LEVELS.values()) {
    		LevelPicker levelToPick = new LevelPicker(level);
    		box.getChildren().add(levelToPick);
    		levelToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
    			
    			@Override
    			public void handle(MouseEvent event) {
    				for(LevelPicker level : levelsList) {
    					level.setIsCircleChoosen(false);
    				}
    				levelToPick.setIsCircleChoosen(true);
    			//	choosenLevel = levelToPick.getLevel();	
    			}	
    		});			
    	}
    	box.setLayoutX(300- (118*2));
    	box.setLayoutY(100);
    	return box;
    }

    private void createButtons() {
    	createNewGameButton();
    	//createLoadGameButton();
    	 createHighestScoreButton();
    	// createHelpButton();
    	 createExitButton();
    	 
    	
    }
    
    private void createNewGameButton() {
    	NinjaButton newGameButton = new NinjaButton("PLAY");
    	addMenuButton (newGameButton);
    	newGameButton.setOnAction(e-> {
    		mainStage.setScene(levelScene);
    		GameView gameManager = new GameView();
    		//gameManager.createNewGame(mainStage);
    		
    	});
    	//newGameButton.setOnAction(e-> newgameSubScene.moveSubScene());
    }
    
    private void createLoadGameButton() {
    	NinjaButton loadGameButton = new NinjaButton("LOAD GAME");
    	addMenuButton (loadGameButton); 	
    }
    
    private void createHighestScoreButton() {
    	NinjaButton HighestScoreButton = new NinjaButton("SCORE");
    	addMenuButton(HighestScoreButton);
 
    }
    
    private void createHelpButton() {
    	NinjaButton HelpButton = new NinjaButton("HELP");
    	addMenuButton(HelpButton);
    }
    
    private void createExitButton() {
    	NinjaButton ExitButton = new NinjaButton("EXIT");
    	addMenuButton(ExitButton);
    	 ExitButton.setOnAction(e->{
             mainStage.close();
         });
    }
    
    
    
    private void createBackground() {
    	Image backgroundImage = new Image("view/resources/Background.JPG",800,600,false,true);
    	BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
    			BackgroundPosition.DEFAULT, null);
    	mainPane.setBackground(new Background(background));
    	
    }
    private void createLevelBackground() {
    	Image backgroundImage = new Image("view/resources/background2.png",800,600,false,true);
    	BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
    			BackgroundPosition.DEFAULT, null);
    	levelPane.setBackground(new Background(background));
    }
}
