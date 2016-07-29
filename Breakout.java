/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Writer;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
/** Random Colours */
 	private RandomGenerator rgen = RandomGenerator.getInstance();
 	
 	private GRect paddle;
 	private GRect brick;
 	private GOval ball;
 	private double vx, vy;
 	private int bricks_counter = 100;
 	
 	/* Others Methods */
 	private void setUpGame() {
		drawBricks();
		drawPaddle();
		//drwaAndMoveBall();
	}
 	
 	
 	private void drawBricks(){
 		double x_coordinate=1;
 		double y_coordinate=BRICK_Y_OFFSET;
 	
		 for(int i=0;i<NBRICK_ROWS;i++){
			 x_coordinate=1;
			 for(int j=0;j<NBRICKS_PER_ROW;j++){
			 brick = new GRect(x_coordinate,y_coordinate,BRICK_WIDTH,BRICK_HEIGHT);
			 if(i==0 || i==1)
				{
					brick.setFilled(true);
					brick.setFillColor(Color.RED);
					
				}
			 else if(i==2 || i==3)
			 {
				 brick.setFilled(true);
				 brick.setFillColor(Color.ORANGE);
				 
			 }
			 else if(i==4 || i==5)
			 {
				 brick.setFilled(true);
				 brick.setFillColor(Color.YELLOW);
				 
			 }
			 else if(i==6 || i==7)
			 {
				 brick.setFilled(true);
				 brick.setFillColor(Color.GREEN);
				 
			 }
			 else if(i==8 || i==9)
			 {
				 brick.setFilled(true);
				 brick.setFillColor(Color.CYAN);
				 
			 }
			 add(brick);
			 x_coordinate=x_coordinate+BRICK_SEP*NBRICK_ROWS;
			 }
			 y_coordinate=y_coordinate+BRICK_HEIGHT+2;
		 }
 	}
 	
 	private void drawPaddle() {
		
 		double x = WIDTH/2; 
		
		double y = HEIGHT - PADDLE_Y_OFFSET;
		
		paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		
		paddle.setFilled(true);
		
		add (paddle);
		
		addMouseListeners();
	}
 	
 	public void mouseMoved(MouseEvent e) {
	 	
			if(PADDLE_WIDTH - e.getX() > 0)
				paddle.setLocation(0, getHeight() - PADDLE_Y_OFFSET);		

			else
				paddle.setLocation(e.getX() - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET);	
	}
 	
 	
 	private void drwaAndMoveBall(){
 		
 		double x_coordinate = (WIDTH+PADDLE_WIDTH)/2; 
		double y_coordinate = HEIGHT-PADDLE_Y_OFFSET-10;
		boolean x_coordinate_flag = false;
		boolean y_coordinate_flag = false;
		
		while(true){
		
		
		ball = new GOval(x_coordinate, y_coordinate, BALL_RADIUS, BALL_RADIUS);
		ball.setColor(Color.BLACK);
		
		ball.setFilled(true);
		add(ball);
		
		
	    try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
	    //x direction
	    
	    if(x_coordinate >= WIDTH-10)
	    	x_coordinate_flag = true;
	    
	    else if(x_coordinate <= 0)
	    	x_coordinate_flag = false;
	    
	    
	    if(x_coordinate_flag == true)
	    	x_coordinate-=5;
	    
	    else
	    	x_coordinate+=5;
	    
	    
	    
	    // y direction...
	    if(y_coordinate <= 0){
	    	y_coordinate_flag = true;
	    }
	    
	    else if(y_coordinate >= (HEIGHT-PADDLE_Y_OFFSET-7)){
	    	y_coordinate_flag = false;
	    }
	    
	    if(y_coordinate_flag == true){
	    	y_coordinate+=5;
	    }
	    
	    else{
	    	y_coordinate-=5;
	    }
	    
	   remove(ball);		
		} 		
 	}
 		
 	private GObject getCollidingObject(){
 		if((getElementAt(ball.getX(), ball.getY())) != null) {
	         return getElementAt(ball.getX(), ball.getY());
	      }
		
		else{
	         return null;
	      }
 	}
 	
 	
private void BallVelocity() {
		
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 5.0;
		if (rgen.nextBoolean(0.5)) {
			vx = -vx; 
		}
		
	}
 	
 	
 	
 	private void startGame(){
 		waitForClick();
 		drwaAndMoveBall();
 		GObject collider = getCollidingObject();
 		
 	}
 		
 	
 	

 	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
	for(int i=0; i < NTURNS; i++) {
			setUpGame();
			startGame();
			} 
		
		
		
	}

}
