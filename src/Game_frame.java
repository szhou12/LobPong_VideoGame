/*
 * Lob Pong: CSC 171 Project
 * Shuyu Zhou
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;



public class Game_frame extends JFrame {
	
	protected static final String FRAME_TITLE = "Lob Pong";
	protected static final int TIMER_DELAY =  50  ; 
	   // unit in milliseconds. This controls the speed of the paddle. The larger the number, the slower the paddle.
	protected static final int CANVAS_WIDTH = 1000;
	protected static final int CANVAS_HEIGHT = 640;
	protected static final int CANVAS_BASELINE = 638;
	public int count; // set spacePressed
	public boolean gameOver = false;
	public boolean won = false;
	public int level = 1;
	
	// Countdown class
	public int dT = 0;
	public int countdown_1 = 20;
	
	//
	List<Brick> bricks = new ArrayList<Brick>();
	
	//
	Image background = ImageUtils.loadImage("graphics/background_1.png");

	Font myFont = new Font ("Courier New", Font.BOLD, 20);
	
	public Game_frame(){
		initGraphics();
		initTimer();
		initHandlers();
		initPaddle();
		initBall();
		initReward();
		initBrick();
		initLife();
		
	}
	
	protected GraphicsCanvas canvas;
	protected GameOver gameOver_canvas;
	protected JLabel label = new JLabel("      Score: 0");
	protected JLabel label_countdown1 = new JLabel("Countdown: "+countdown_1);
	
	protected void initGraphics() {
		setTitle(FRAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		setResizable(false);
		canvas = new GraphicsCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		add(canvas, BorderLayout.CENTER);
		pack();
	}
	
	protected Timer timer;

	protected void initTimer() {
		timer = new Timer(TIMER_DELAY, new TimerHandler());
	}

	protected void initHandlers() {
		canvas.addKeyListener(new KeyHandler());
	}
	
	/**
	 * Start the game by making the frame visible and starting the
	 * animation timer.
	 */
	public void start() {
		setVisible(true);
		//timer.start();
	}
	
	Paddle pad;
	
	protected void initPaddle() {
		pad = new Paddle();
		int x = CANVAS_WIDTH/2-pad.getWidth()/16;
		int y = CANVAS_BASELINE-pad.getHeight()/2;
		pad.setLocation(x, y);
		pad.setMoving(Paddle.Direction.NONE);
		
	}
	
	protected void replacePaddle() {
		int x = CANVAS_WIDTH/2-pad.getWidth()/16;
		int y = CANVAS_BASELINE-pad.getHeight()/2;
		pad.setLocation(x, y);
		pad.setMoving(Paddle.Direction.NONE);
		
	}
	
	Ball ball;
	
	protected void initBall(){
		ball = new Ball();
		int x = CANVAS_WIDTH/2-ball.getWidth()/2;
		int y = CANVAS_BASELINE-pad.getHeight()-ball.getHeight()/2;
		ball.setLocation(x, y); // initial position of the ball
		
	}
	
	protected void replaceBall(){
		//int x = CANVAS_WIDTH/2-ball.getWidth()/2;
		int y = CANVAS_BASELINE-pad.getHeight()-ball.getHeight()/2;
		int x = pad.getX();
		ball.setLocation(x, y);
		ball.setHorizSpeed(0);
		ball.setVertSpeed(0);
		ball.start = true;
	}
	
	/**
	 * Graphic canvas panel.
	 */
	protected class GraphicsCanvas extends JPanel {
		public GraphicsCanvas() {
		    setFocusable(true);
		    //label.setBounds doesn't work because layout is set as border layout
		    
		    label_countdown1.setFont(new Font("Courier New", Font.BOLD, 20));
		    add(label_countdown1);
		    
		    label.setFont(new Font("Courier New", Font.BOLD, 20));
		    add(label);
		}
		@Override
		public void paintComponent(Graphics g) {
			//System.out.println("GraphicsCanvas.paintComponent");
			g.drawImage(background, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, null);
			pad.draw(g);
			ball.draw(g);
			drawPowerups(g);
			drawBricks(g);
			drawHearts(g);
			g.setFont (myFont);
			g.drawString("LEVEL: "+level, 900, 20);
			
		}
	}
	
	/**
	 * Game over panel.
	 */
	
	protected class GameOver extends JPanel {
		public GameOver() {
		    setFocusable(true);
		}
		
		Image image_game_over = ImageUtils.loadImage("graphics/game_over.png");
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//System.out.println("GraphicsCanvas.paintComponent");
			g.drawImage(background, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, null);
			
			int x = (int)(CANVAS_WIDTH/2-295/2);
			int y = (int)(CANVAS_HEIGHT/2-295/2);
			g.drawImage(image_game_over, x, y, null);
			
			g.setFont (myFont);
			g.drawString ("Total Scores: "+ball.score, x+65, y+220);
			
			if (won){
				g.drawString ("Congratulations! YOU WON!", x+30, y+250);
			}
			
		}
	}
	
	

	
	/**
	 * Timer callback handler. Updates everything and schedules
	 * a repaint.
	 */
	protected class TimerHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("TimerHandler.actionPerformed");
			
			/** update the position of the paddle*/
			pad.update();
			
			/** update the position of the ball. change its direction when hits the wall.*/
			ball.update(pad.getX(), pad.getY(), pad.getHeight(), pad.getWidth());
			
			/** score system to add points each time the ball hits the paddle */
			if(ball.scorepp || !gameOver){
				label.setText("      Score: "+ball.score);
				ball.scorepp = false;
			}
			
			/** bonus for ball to hit the star coins */
			updatePowerups();
			
			/** ball hits bricks */
			
//			if (ball.getX() < 332){
//				bricks.get(0).update(ball);
//			} else if (ball.getX() > 632){
//				for (int i = 1; i <= 2; i ++){
//					bricks.get(i).update(ball);
//				}	
//			} else if (ball.getX() >= 332 && ball.getX() <= 632){
//				bricks.get(3).update(ball);
//			}
			
			for (int i = 0; i <= 3; i ++){
				bricks.get(i).update(ball);
			}
			
			
			
			/** remove a heart when the ball falls off */
			if(ball.falloff){
				count = 0;
				replacePaddle();
				replaceBall();
				updateLife();
				ball.falloff = false;
				spacePressed = false;
				
				//ball.scorepp=false;
			}
			
			dT += TIMER_DELAY;
			if (dT == 1000){
				dT = 0;
				countdown_1--;
				label_countdown1.setText("Countdown: "+countdown_1);
				
			}
			
			if (countdown_1 == 0){
				//next level
				level += 1;
				if (level == 2){
					if(gameOver==true){
						timer.stop();
					}
					countdown_1 = 30;
					count = 0;
					replacePaddle();
					replaceBall();
					ball.score += 20;
					spacePressed = false;	
				}
				
				if (level == 3){
					if(gameOver==true){
						timer.stop();
					}
					countdown_1 = 60;
					count = 0;
					replacePaddle();
					replaceBall();
					ball.score += 50;
					spacePressed = false;
				}
				
				if (level > 3){
					ball.score += 100;
					if (gameOver==false){
						won = true;
					}
					canvas.setVisible(false);
					gameOver_canvas = new GameOver();
					gameOver_canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
					add(gameOver_canvas, BorderLayout.CENTER);
					System.out.println();
				}
				
				
			}
			
			if(level >= 4){
				timer.stop();
			}
			
			canvas.repaint();
			
		}
	}
	


	/**
	 * KeyListener handles keypresses in game.
	 */
	public boolean leftPressed = false;
	public boolean rightPressed = false;
	public boolean spacePressed = false;
	protected class KeyHandler implements KeyListener {
		
		
		public void keyPressed(KeyEvent e) {
			//System.out.println("GameFrame.keyPressed: " + e);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				if(spacePressed){
					startMovingRight();
				} else if (!spacePressed){
					stopMoving();
				}
				
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				if(spacePressed){
					startMovingLeft();
				}else if (!spacePressed){
					stopMoving();
				}
				
				break;
			case KeyEvent.VK_SPACE:
				timer.start();
				
				if (count == 0){
					// launch the ball when press space bar.
					spacePressed = true;
					ball.start = false;
					launch();
					count ++;
					// count makes the space key works only once. So it won't reset the speed of the ball after the launch
				}
				
				break;
			}
		}
		public void keyReleased(KeyEvent e) {
			//System.out.println("GameFrame.keyReleased: " + e);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				if (leftPressed) {
					if (spacePressed){
						startMovingLeft();
					}
				} else {
					stopMoving();
				}
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				if (rightPressed) {
					if (spacePressed){
						startMovingRight();
					}
				} else {
					stopMoving();
				}
				break;
			}
		}
		public void keyTyped(KeyEvent e) {
		}
	}
	
	

	protected void startMovingRight() {
		//System.out.println("GameFrame.startMovingRight");
		pad.setHorizSpeed(+18);
		//pad.moving = Paddle.Direction.RIGHT;
		
		
	}

	protected void startMovingLeft() {
		//System.out.println("GameFrame.startMovingRight");
		pad.setHorizSpeed(-18);
		//pad.moving = Paddle.Direction.LEFT;
		
	}

	protected void stopMoving() {
		//System.out.println("GameFrame.stopMoving");
		pad.setHorizSpeed(0);
		//ball.setHorizSpeed(0);

	}
	
	protected void launch() {
			ball.setHorizSpeed(7.6);
			ball.setVertSpeed(-22.5);
		
	}
	
	
	/**
	 * Powerups for ball to hit.
	 */
	List<Reward> powerups = new ArrayList<Reward>();

	protected void initReward() {
		Reward p1 = new Reward(200, 200);
		Reward p2 = new Reward(500, 300);
		Reward p3 = new Reward(800, 200);
		powerups.add(p1);
		powerups.add(p2);
		powerups.add(p3);
	}
	
	protected void updatePowerups() {
		for (Reward p : powerups) {
			p.update();
		}
		checkForPowerupHit();
	}
	
	protected void drawPowerups(Graphics g) {
		for (Reward p: powerups) {
			p.draw(g);
		}
	}
	
	protected void checkForPowerupHit() {
		Rectangle ballRect = ball.getRect();
		ListIterator<Reward> iterator = powerups.listIterator();
		while (iterator.hasNext()) {
			Reward p = iterator.next();
			if (p.getRect().intersects(ballRect)) {
				System.out.println("HIT!");
				ball.scorepp = true;
				ball.score += 50;
				iterator.remove();
			}
			
		}
	}
	
	/**
	 * Bricks.
	 */

	protected void initBrick() {
		Brick b1 = new Brick(200, 235);
		Brick b2 = new Brick(482, 335);
		Brick b3 = new Brick(518, 335);
		Brick b4 = new Brick(800, 235);

		bricks.add(b1);
		bricks.add(b2);
		bricks.add(b3);
		bricks.add(b4);
	}
	

	protected void drawBricks(Graphics g) {
		for (Brick b : bricks) {
			b.draw(g);
		}
	}
	
	
	
	
	/**
	 * Life.
	 */
	List<Life> hearts = new ArrayList<Life>();

	protected void initLife() {
		Life l1 = new Life(20, 20);
		Life l2 = new Life(40, 20);
		Life l3 = new Life(60, 20);
		hearts.add(l1);
		hearts.add(l2);
		hearts.add(l3);
	}
	
	protected void drawHearts(Graphics g) {
		for (Life hp: hearts) {
			hp.draw(g);
		}
	}
	
	protected void updateLife(){
		if (!hearts.isEmpty()) {
				System.out.println("Fall!");
				hearts.remove(hearts.size()-1);
			
		}
		
		if (hearts.isEmpty()){
			gameOver = true;
			
			canvas.setVisible(false);
			gameOver_canvas = new GameOver();
			gameOver_canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			add(gameOver_canvas, BorderLayout.CENTER);
			//timer.stop();
		}
	}



	public static void main(String[] argv) {
		new Game_frame().start();
	}
	
	
	

	
	

}
