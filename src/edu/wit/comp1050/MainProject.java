/**
 * 
 */
package edu.wit.comp1050;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author harkousw
 *
 */
public class MainProject extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }
    
	    @Override
	    public void start(Stage primaryStage) throws Exception{
	       
	    	primaryStage.setTitle("Calendar");
	        
	        Parent startP = FXMLLoader.load(getClass().getResource("StartProgram.fxml"));
	        Scene scene = new Scene(startP);
	        
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	    }
}
