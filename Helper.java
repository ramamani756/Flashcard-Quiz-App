package application;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Helper {
	// Generic method to hide any pane
    public static void hidePane(Pane pane) {
        if (pane != null) {
            pane.setVisible(false);
            pane.setManaged(false);
        }
    }

    // Generic method to show any pane
    public static void showPane(Pane pane) {
        if (pane != null) {
            pane.setVisible(true);
            pane.setManaged(true);
        }
    }
    

	
	
}
