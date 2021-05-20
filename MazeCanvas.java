import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.*;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Pos;
import javafx.animation.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javafx.scene.input.*;

public class MazeCanvas extends Canvas 
{
 
      //create the 2darray
      int[][] maze = new int[21][21];
      GraphicsContext gc = getGraphicsContext2D();
      
      //variables for the 4 keys
      int down;
      int up;
      int left;
      int right;
      
       
       public MazeCanvas()
      {
         //set width and height 
         setWidth(525);
         setHeight(525);
         
         //set the keypressed to the listener 
         setOnKeyPressed(new KeyListenerDown());
         
         //try catch for file
         try
         {
      
            //scan the maze file 
            Scanner fileScan = new Scanner( new File("maze.txt"));
   
            //read the maze into an arraylist
            for (int k=0;k<21;k++)
                  {     
         
                     for (int m=0;m<21;m++)
                     { 
                        maze[k][m] = fileScan.nextInt();
                     }
                  }
                 
           //print out the maze
            for (int j=0;j<21;j++)
            {     
         
               for (int i=0;i<21;i++)
               {
                //if the square is 1 - black
                if(maze[j][i] == 1)

                  {
                     
                     gc.setFill(Color.BLACK);
           
                  }
                  
                  //if not - white
                  else
                  {
                     gc.setFill(Color.WHITE);
                     
                     //if its white and in the first row, make it cyan, thats the character
                     if(j==0)
                     {
                     gc.setFill(Color.CYAN);
                     
                     //set it to 3 to keep track of the character
                     maze[j][i] = 3;
                     }
                      
                  }
          
          
               //fill in the squares
               gc.fillRect(i*25,j*25,25,25);
               
         
            }
         }   
     
     
     
    }
    
    catch(FileNotFoundException fnfe)
   {
  
   }
    
    }
    
   
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      {
        //when down is pressed, move down
         if (event.getCode() == KeyCode.DOWN)
         {   
            down =1;
         }
         
         //when right is pressed, move right
         if (event.getCode() == KeyCode.RIGHT)
         {
            right = 1; 
         }
         
         //when up is pressed, move up
         if (event.getCode() == KeyCode.UP)
         {   
            up =1;
         }
         
         //when left is pressed, move left
         if (event.getCode() == KeyCode.LEFT)
         {
            left = 1; 
         }
         
         gc.setFill(Color.CYAN);
         
         
         
         boolean stop = false;
         int played = 0;
      
      //loop through all of the squares 
       for (int j=0;j<21&&!stop;j++)
           {     
         
               for (int i=0;i<21&&!stop;i++)
               {
                
                gc.setFill(Color.CYAN);
                
                //for whichever square is associated with 3- where the character is
                if(maze[j][i] == 3)
                {
                  
                  //for down
                  if(down ==1)
                  {
                     try{
                           //if the square below is white, make it cyan, and set it to 3 
                           if (maze[j+1][i]==0)
                           {
                              
                              gc.fillRect(i*25,(j+1)*25,25,25);
                              maze[j+1][i]=3;
                              
                              //exit the for loop, turn complete 
                              stop = true;
                              
                              //turn has been played
                              played = 1;
                           }
                        }
                     
                     //if the user tries to move off of the board 
                     catch(ArrayIndexOutOfBoundsException ae)
                     {
                        //catch the error, keep the turn as not played
                        played =0;
                     
                     }
                     
                     }
                  
                  
                  //for up
                  if(up ==1)
                  {
                     try{
                     
                     //if the square above is white, make it cyan, and set it to 3 
                           if (maze[j-1][i]==0)
                           {
                              gc.fillRect(i*25,(j-1)*25,25,25);
                              maze[j-1][i]=3;
                              
                              //exit the for loop
                              stop = true;
                              //turn has been played
                              played = 1;
                     }
                     
                     }
                     
                     //if the user tries to move off the board, catch the error, set as not played
                     catch(ArrayIndexOutOfBoundsException ae)
                     {
                     
                        played =0;
                     
                     }
                  }
                  
                  //if right
                  if(right ==1)
                  {
                     
                     try{
                           //if the square to the right is white
                           if(maze[j][i+1]==0)
                           {
                              //make it cyan, and set it to 3
                              gc.fillRect((i+1)*25,j*25,25,25);
                              maze[j][i+1]=3;
                              
                              //exit for loop
                              stop=true;
                              
                              //turn played
                              played = 1;
                           }
                        }
                     
                     //if the user tries to move off the board, catch the error, set as not played
                      catch(ArrayIndexOutOfBoundsException ae)
                     {
                     
                        played =0;
                     
                     }
                     
                   }
                  
                  
                  if(left ==1)
                  {
                    
                    try{
                        //if the square to the left is white
                        if (maze[j][i-1]==0)
                        {
                           //make it cya, set it to 3
                           gc.fillRect((i-1)*25,j*25,25,25);
                           maze[j][i-1]=3;
                           
                           //exit the for loop
                           stop=true;
                           
                           //turn played
                           played = 1;
                        }
                      }
                     
                     //if the user tries to move off the board, catch the error, set as not played
                     catch(ArrayIndexOutOfBoundsException ae)
                     {
                     
                        played =0;
                     
                     }
                  
                  }
                
                //when the turn has been played
                if (played ==1)
                {
                  //set the previous location of the character back to white
                  gc.setFill(Color.WHITE);
                  gc.fillRect(i*25,j*25,25,25);
                  maze[j][i]=0;
                }
                
                //if the user makes it to the last line
                if(maze[20][i]==3)
                {
                  //winner! message
                  gc.setFill(Color.MAGENTA);
                  gc.fillRect(0,150,525,200);
                  gc.setFill(Color.BLACK);
                
                gc.fillText("You win!!",262,262);
                }
                
                //reset all variables
                
                up=0;
                down=0;
                left=0;
                right=0;
                stop = false;
                played=0;
                
               

               }
               
               
          }
               
               
                  
                  
                  
        
 }
            
      
 
   }
      
                 
}


}      
      
      
   
  