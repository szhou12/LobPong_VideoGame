

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Paddle{
	
	// need to change later according to the size of window.
	protected int centerX;
	protected int centerY;
	
	public void setLocation(int x, int y) {
		centerX = x;
		centerY = y;
	}

	public int getX() {
		return centerX;
	}

	public int getY() {
		return centerY;
	}
	

	protected int width = 130;
	protected int height = 10;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getRect() {
		return new Rectangle(centerX-width/2, centerY-height/2, width, height);
	}

	protected int vX = 0;


	protected void setHorizSpeed(int v) {
		vX = v;
	}
	
	/**
	 * Direction a paddle is moving.
	 * LEFT, and RIGHT are relative to screen.
	 */
	public enum Direction {
		NONE, LEFT, RIGHT;
	}

	protected Direction moving = Direction.NONE;
	

	public void setMoving(Direction d) {
		moving = d;
	}
	
	
	protected Image loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	Image Paddle = loadImage("graphics/paddle_1.png");
	
	/**
	 * Called from canvas paintComponent method to draw this paddle.
	 */
	// Might want canvas rather than graphics context
	
	public void draw(Graphics g) {
		int x = centerX - width/2;
		int y = centerY - height/2;
		
//		g.setColor(Color.blue);
//		g.fill3DRect(x, y, width, height, true);
		g.drawImage(Paddle, x, y, null);
		
	}
	
	/**
	 * Called from the animation loop to update any time-dependent
	 * properties of this paddle.
	 */
	// Might want time and/or other params
	public void update() {
		centerX += vX;
		centerX = Math.min(centerX, Game_frame.CANVAS_WIDTH-width/2);
		centerX = Math.max(width/2, centerX);
		
	}
	
	
	
	
	
}
