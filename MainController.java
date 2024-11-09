package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable,MainControllerObserver{
	private Main mainApp;
	private Stage primaryStage;
	private boolean notifying = false;
	private User user;

	
	@FXML
	private Label topBarLabel;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnSignUp;
	
	@FXML
	private AnchorPane welcomeHome;
	@FXML
	private VBox homeVbox;
	@FXML
	private Button btnLogout;
	
	@FXML
	private TreeView subjectTreeView;
	@FXML 
	private ListView<HBox> flashCardView;
	
	
	 private List<MainControllerObserver> observers = new ArrayList<>();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addObserver(this);
		Helper.hidePane(homeVbox);
		btnLogout.setVisible(false);
		
		
		//for the development purpose
		User user1=new User("min1","12345678");
		User user2=new User("min2","12345678");
		Main.database.addUser(user1);
		Main.database.addUser(user2);
		user1.addSubject("Biology");
		user1.addSubject("Chemistry");
		user2.addSubject("Pysics");
		user2.addSubject("Mathematics");
		Subject pysics=user2.getSubject(1);
		pysics.addTopic("vector");
		pysics.addTopic("dynamics");
		pysics.addTopic("kinematics");
		pysics.addTopic("termodynamics");
		Subject biology=user1.getSubject(1);
		biology.addTopic("cell theory");
		biology.addTopic("genetics");
		
		Topic cellTheoryTopic = biology.getTopicByName("cell theory");

		// Add 10 flash cards to the "cell theory" topic
		for (int i = 1; i <= 10; i++) {
		    String question = "Cell Theory Question " + i;
		    String answer = "Cell Theory Answer " + i;
		    String explanation = "Cell Theory Explanation " + i;

		    FlashCard flashCard = new FlashCard(question, answer, explanation);
		    cellTheoryTopic.addFlashCard(flashCard);
		}

		// Get the "genetics" topic from the Biology subject
		Topic geneticsTopic = biology.getTopicByName("genetics");

		// Add 10 flash cards to the "genetics" topic
		for (int i = 1; i <= 10; i++) {
		    String question = "Genetics Question " + i;
		    String answer = "Genetics Answer " + i;
		    String explanation = "Genetics Explanation " + i;

		    FlashCard flashCard = new FlashCard(question, answer, explanation);
		    geneticsTopic.addFlashCard(flashCard);
		}
	}
	public <T> void showModal(String fxmlFile,String title, Class<T> controllerClass) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
			Parent root = loader.load();
			
			T controller= loader.getController();
			
			Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setUserData(this);
            
            Scene modalScene = new Scene(root);
            setupController(controller,dialogStage);
            dialogStage.setScene(modalScene);
            dialogStage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	private void setupController(Object controller, Stage dialogStage) {
	    if (controller instanceof ModalController) { 
	        ((ModalController) controller).setStage(dialogStage);
	    }
	    if (controller instanceof MainControllerObserver ) {
	    	MainControllerObserver observer = (MainControllerObserver) controller;
	        observers.clear();
	    	addObserver(this);
            addObserver(observer);
            
        }
	}
	public void setMainApp(Main mainApp) {
		this.mainApp=mainApp;
	}
	public void setStage(Stage stage) {
		this.primaryStage=stage;
	}
	public void showLoginPage(ActionEvent event) {
	       
		showModal("scene/login.fxml", "LOGIN PAGE", LoginController.class);
		notifyButtonPressed("btnLogin"); // Notify observers about button press
	}
	public void showSignUpPage(ActionEvent event) {
	       
		showModal("scene/login.fxml", "SIGNUP PAGE", LoginController.class);
		notifyButtonPressed("btnSignUp"); // Notify observers about button press
	}
	// Method to add an observer
	public void addObserver(MainControllerObserver observer) {
	        observers.add(observer);
	}
    // Method to notify observers about button press
    private void notifyButtonPressed(String buttonId) {
        for (MainControllerObserver observer : observers) {
            observer.onButtonPressed(buttonId);
        }
    }

    // Method to notify observers about user login
    public void notifyUserLoggedIn(User user) {
    	if (!notifying) {
            notifying = true;
            for (MainControllerObserver observer : observers) {
                
                observer.onUserLoggedIn(user);
                
            }
            notifying = false;
        }
    }
    
    @Override
	public void onButtonPressed(String buttonId) {
		// TODO Auto-generated method stub
    	System.out.println(buttonId+"on main controller");
	}
	@Override
	public void onUserLoggedIn(User user) {
		System.out.println("login called");
		Helper.hidePane(welcomeHome);
		Helper.showPane(homeVbox);
		btnLogout.setVisible(true);
		this.user=user;
		initializeUser();
	}
	
	public void initializeUser() {
		TreeItem<String> rootItem =new TreeItem<String>("SUBJECTS",
				new ImageView(new Image(getClass().getResourceAsStream("img/book-icon.png"))));
		
		for(Subject subject:user.getSubjects()) {
			TreeItem<String> subjectItem =new TreeItem<String>(subject.getSubjectName());
			for(Topic topic:subject.getTopics()) {
				TreeItem<String> topicItem =new TreeItem<String>(topic.getTopicName());
				subjectItem.getChildren().add(topicItem);
			}
			rootItem.getChildren().add(subjectItem);
		}
		
		
		subjectTreeView.setRoot(rootItem);
		subjectOnMouthClick();
	}
	
	public void subjectOnMouthClick() {
		subjectTreeView.setOnMouseClicked(event -> {
			TreeItem<String> selectedItem = (TreeItem<String>)subjectTreeView.getSelectionModel().getSelectedItem();
			if (selectedItem != null && isTopicItem(selectedItem)) {
				Topic selectedTopic = getTopicFromTreeItem(user,selectedItem);
				Subject subject=user.getSubjectByName(selectedItem.getParent().getValue());
				printFlashCards(subject,selectedTopic);
			}else if(selectedItem != null && isSubjectItem(selectedItem)){
				
			}
		});
	}
	private boolean isSubjectItem(TreeItem<String> treeItem) {
		
		return treeItem.getParent() != null && treeItem.getParent().getValue() == "SUBJECTS";
	}
	private boolean isTopicItem(TreeItem<String> treeItem) {
		// Check if the given TreeItem is a leaf (no children), indicating it's a topic item
		return treeItem.isLeaf() && treeItem.getParent() != null && treeItem.getParent().getValue() != "SUBJECTS";
	}
	private Topic getTopicFromTreeItem(User user,TreeItem<String> topicItem) { 
		String topicName = topicItem.getValue();
		
		// Retrieve the Subject object associated with the parent TreeItem
		TreeItem<String> subjectItem = topicItem.getParent();
		String subjectName = subjectItem.getValue();
		Subject subject = user.getSubjectByName(subjectName);
		
		// Retrieve the Topic object from the Subject based on the topicName
		Topic topic = subject.getTopicByName(topicName); 
		
		return topic;
	}
	
	
	

	public void printFlashCards(Subject subject,Topic topic){
		topBarLabel.setText(topic.getTopicName());
		flashCardView.getItems().clear();
		for(FlashCard flashCard: topic.getFlashCards()) {
			HBox flashFx=flashHbox(subject,topic,flashCard);
			flashCardView.getItems().add(flashFx);
			//if count == 0 do some thing
			
		}
		if(flashCardView.getItems().size()==0) {
			
			HBox message=new HBox(new Label("Empty Topic"));
			flashCardView.getItems().add(message);
		}
	}

	public HBox flashHbox(Subject subject,Topic topic,FlashCard flashCard) {
        HBox flashFx = new HBox(2); // Spacing between elements
        flashFx.setAlignment(Pos.CENTER_LEFT);
        flashFx.setMinHeight(35.0);
        
     // Print id
        String style="-fx-padding:2px 4px;-fx-font-size:25px;-fx-text-fill: black;";
        String formatedId=String.format("%-3d", ((Integer) flashCard.getId()));
        Label id = createStyledLabel(formatedId, style);
        
        // Print question
        style= "-fx-text-fill: white;-fx-padding:2px 4px;-fx-hgrow:ALWAYS;-fx-font-size:25px;-fx-text-fill: black;";
        String trancatedQuestion=flashCard.getQuestion().length()>90?flashCard.getQuestion().substring(0,90)+"...":flashCard.getQuestion();
        String formatedQuestion=String.format("%-95s", trancatedQuestion);
        
        Label question = createStyledLabel(formatedQuestion,style);
        question.setMinHeight(35.0);
        HBox.setHgrow(question, Priority.ALWAYS);
        // Edit button
        Button btnEdit = createStyledButton("Edit");
        btnEdit.setUserData(new FlashCardUserData(subject.getId(), topic.getId(), flashCard.getId() ));
        btnEdit.setOnAction(this::handleEditFlashCard);
        setButtonWidth(btnEdit);
        // Delete button
        Button btnDelete = createStyledButton("Delete");
        btnDelete.setUserData(new FlashCardUserData(subject.getId(), topic.getId(), flashCard.getId() ));
        btnDelete.setOnAction(this::handleDeleteFlashCard);
        // Set 100% width for buttons
        
        setButtonWidth(btnDelete);
        
        // Add elements to HBox
        flashFx.getChildren().addAll(id, question, btnEdit, btnDelete);
        
        return flashFx;
    }
	private Label createStyledLabel(String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        return label;
    }
	
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;-fx-font-size:25px;"); // Green background color
        return button;
    }

    private void setButtonWidth(Button button) {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        button.setMaxWidth(Double.MAX_VALUE);
    }
	
    private void handleEditFlashCard(ActionEvent event) {
        FlashCardUserData userData = (FlashCardUserData) ((Button) event.getSource()).getUserData();
        int subjectId = userData.getSubjectId();
        int topicId = userData.getTopicId();
        int flashCardId = userData.getFlashCardId();
        System.out.println("Edit button clicked for Subject ID: " + subjectId + ", Topic ID: " + topicId + ", Flash Card ID: " + flashCardId);
        
    }

    private void handleDeleteFlashCard(ActionEvent event) {
        FlashCardUserData userData = (FlashCardUserData) ((Button) event.getSource()).getUserData();
        int subjectId = userData.getSubjectId();
        int topicId = userData.getTopicId();
        int flashCardId = userData.getFlashCardId();
        System.out.println("Delete button clicked for Subject ID: " + subjectId + ", Topic ID: " + topicId + ", Flash Card ID: " + flashCardId);
        
    }
	
	public void logoutUser() {
		subjectTreeView.getRoot().getChildren().clear();
		Helper.hidePane(homeVbox);
		Helper.showPane(welcomeHome);
		this.user=null;
		btnLogout.setVisible(false);
		
	}
	

	
}
