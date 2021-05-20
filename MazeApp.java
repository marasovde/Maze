import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import java.util.*;
import java.io.*;
import javafx.scene.paint.Color;

public class MazeApp extends Application 
{
 
  
  //make the flowpane and the canvas
   FlowPane root = new FlowPane();
   MazeCanvas a = new MazeCanvas();
  
   public void start(Stage stage)
   {
      //add the canvas to the flowpane
      root.getChildren().add(a);
      
      
      
      //create the stage
      Scene scene = new Scene(root, 525, 525);
      stage.setScene(scene);
      stage.setTitle("Maze Game");
      stage.show();
      //put the focus on the drag canvas
      a.requestFocus();
   }
   
  public static void main(String[] args)
   {
      launch(args);
      
       
   }
   
   
 }