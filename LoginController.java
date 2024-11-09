package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable, ModalController,MainControllerObserver{
	private Stage stage;
	
	@FXML
	private GridPane loginGrid;
	@FXML
	private GridPane registerGrid;
	@FXML
	private VBox signupVbox;
	@FXML
	private VBox loginVbox;
	@FXML
	private TextField loginUsername;
	@FXML
	private TextField registerUsername;
	@FXML
	private TextField loginPassword;
	@FXML
	private TextField registerPassword;
	@FXML
	private TextField registerPasswordConfirm;
	@FXML 
	private Button btnSignUp;
	@FXML
	private Button btnLogin;
	@FXML
	private Pane errorMessagePane;
	@FXML
	private Label errorMessageLabel;
	



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	@Override
	public void onButtonPressed(String buttonId) {
		System.out.println(buttonId);
		if("btnLogin".equals(buttonId)) {
			hideSignUp();
		}else if("btnSignUp".equals(buttonId)) {
			hideLogin();
		}
		
	}
	@Override
    public void onUserLoggedIn(User user) {
        // Handle user login, e.g., send the user to MainController
        ((MainController) stage.getUserData()).notifyUserLoggedIn(user);
    }
	@Override
	public void setStage(Stage stage) {
		this.stage=stage;
		
	}
	public void hideLogin() {
		Helper.hidePane(loginVbox);
		stage.setTitle("WELCOME TO SIGN UP PAGE");
	}
	public void hideSignUp() {
		Helper.hidePane(signupVbox);
		stage.setTitle("WELCOME TO LOGIN PAGE");
	}
	public void changeToSignUp(ActionEvent event) {
		hideLogin();
		Helper.showPane(signupVbox);
	}
	public void changeToLogin(ActionEvent event) {
		hideSignUp();
		Helper.showPane(loginVbox);
	}
	public void handleLogin(ActionEvent event) {
		String username=loginUsername.getText();
		String password=loginPassword.getText();
		
		
		User user=Main.database.getUser(username, password);
		if(user!=null) {
			stage.close();
			loginUsername.setText("");
			loginPassword.setText("");
			onUserLoggedIn(user);
			
		}else {
			showErrorMessage("Login failed. Invalid username or password. ",loginVbox);
			System.out.println("Login failed. Invalid username or password. ");
			
		}
	}
	public void handleSignUp(ActionEvent event) {
		String username=registerUsername.getText();
		String password=registerPassword.getText();
		String passwordConfirm=registerPasswordConfirm.getText();
		
		
		if(Main.database.isUserPresent(username)) {
			showErrorMessage("username is already present",signupVbox);
			System.out.println("username is already present");
			return;
		}
		
		if(password.length()<8) {
			showErrorMessage("password length should be greater than 8 characters!",loginVbox);
			System.out.println("password length should be greater than 8 characters!");
			return;
		}
		if(!(password.equals(passwordConfirm))) {
			showErrorMessage("password confirmation failed!",loginVbox);
			System.out.println("password confirmation failed!  "+password+"   "+passwordConfirm);
			
			return;
		}
		
		User user=new User(username,password);
		Main.database.addUser(user);
		stage.close();
		registerUsername.setText("");
		registerPassword.setText("");
		registerPasswordConfirm.setText("");
		onUserLoggedIn(user);
		
	}
	public void showErrorMessage(String message,Pane parent) {
		//TODO show error message using timer
	}

	

}
