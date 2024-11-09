package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	protected Stage primaryStage;

	public static Database database=new Database();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage=primaryStage;
			FXMLLoader loader= new FXMLLoader(getClass().getResource("scene/home.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainController controller=loader.getController();
			controller.setMainApp(this);
			controller.setStage(primaryStage);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
