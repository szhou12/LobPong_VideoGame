import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


//import Game_frame.TimerHandler;

public class Ball {
	
	public boolean scorepp = false;
	public boolean falloff = false;
	public boolean start = true;
	public int score = 0;
	protected double centerX;
	protected double centerY;
	public int count = 0;

	public void setLocation(int input_x, int input_y) {
		centerX = input_x;
		centerY = input_y;
	}

	public double getX() {
		return centerX;
	}

	public double getY() {
		return centerY;
	}
	
	protected int width = 20;
	protected int height = 20;
	protected int radius = 10;
	
	public int getRadius() {
		return radius;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getRect() {
		return new Rectangle((int)(centerX-width/2), (int)(centerY-height/2), width, height);
	}
	
	protected double vX = 0;
	protected double vY = 0;
	protected double vY_init = 0;

	protected void setHorizSpeed(double v) {
		vX = v;
	}

	protected void setVertSpeed(double v) {
		vY = v;
	}
	public double getvX() {
		return vX;
	}

	public double getvY() {
		return vY;
	}
	
	

	protected double G = 9.8; // "gravity"
	
	
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image Ball = loadImage("graphics/ball_5.png");
	
	public void draw(Graphics g) {
		int x = (int)(centerX - radius);
		int y = (int)(centerY - radius);

		//g.setColor(Color.RED);
		//g.fillOval((int)(centerX - radius),(int)(centerY - radius), radius*2, radius*2);
		g.drawImage(Ball, x, y, null);
		
		
	}
	
	
	
	double timerY = 0.03;
	
	/**
	 * Called from the animation loop to update any time-dependent
	 * properties of this paddle.
	 */
	public void update(int pad_x, int pad_y, int pad_height, int pad_width) {
		

		vY += 0.4;
		
		/** For x-coordinate */
		/* check for boundaries*/
		if (centerX < radius)	{
			vX = Math.abs(vX);
		}
		if (centerX > Game_frame.CANVAS_WIDTH - width) {
			vX = -Math.abs(vX);
		}
		

		
		/** For y-coordinate */
		/* check for boundaries*/
		if (centerY < radius)	{
			vY = -vY + 0.4;
			count++;
			if (count <= 2){
				vX *= 1.2;
				vY *= 1.2;
			}
			vY = Math.abs(vY);

		}

		
		if (centerY > pad_y+height) {
			this.falloff = true;
			//restart();
		}

		if (centerX >= pad_x-pad_width/2 && centerX < pad_x && centerY > pad_y-height+5 && centerY < pad_y+height) {
			/* if the ball hits the left half of the paddle, vX keeps towards to left*/
			
			vX = -Math.abs(vX);
			vY = -Math.abs(vY) + 0.4;  
			vX *= 0.995;
			//System.out.println("vX-left: " + vX);
			
			count++;
			if (count <= 2){
				vX *= 1.2;
				vY *= 1.2;
			}
			
			
			
			this.scorepp = true;
			if(!start){ // if start = false
				this.score++;
			}
			
		} 
		
		
		if (centerX >= pad_x && centerX <= pad_x+pad_width/2 && centerY > pad_y-height+5 && centerY < pad_y+height) {
			/* if the ball hits the right half of the paddle, vX keeps towards to right*/
			
			vX = Math.abs(vX);
			vY = -Math.abs(vY)+0.4; 
			vX *= 0.995;
			//System.out.println("vX-right: " + vX);
			
			count++;
			if (count <= 2){
				vX *= 1.2;
				//System.out.println("vX: " + vX);
				vY *= 1.2;
			}
			
			
			this.scorepp = true;
			if(!start){ // if start = false
				this.score++;
			}
			
		} 
		
		
		centerX += vX;
		centerY += vY;
		//System.out.println("vY: " + vY);
		
		
		
		/* adjust y-coord */
		//timerY = 0.03;
		//vY += G * timerY;
//		vY = vY_init + G * timerY;
//		System.out.println("vY Alter: " + vY);

		
//		vY += G * timerY;
//		centerY += vY*timerY + 0.5*G*Math.pow(timerY, 2);
	
		
	}

	

	
	
}
