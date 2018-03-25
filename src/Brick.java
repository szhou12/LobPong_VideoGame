import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Brick {
	
	protected int centerX;
	protected int centerY;

	public Brick(int x, int y) {
		centerX = x;
		centerY = y;
	}

	protected int width = 36; // image size
	protected int height = 36;

	public Rectangle getRect() {
		return new Rectangle(centerX-width/2, centerY-height/2, width, height);
	}


	
	Image image = ImageUtils.loadImage("graphics/brick_4.png");
	

	public void draw(Graphics g) {
		int x = centerX - 18;
		int y = centerY - 18;
		g.drawImage(image, x, y, null);
	}

	public void update(Ball b) {
		checkforCollision(b);

	}

	private void checkforCollision(Ball b) {
		double ballX = b.getX();
		double ballY = b.getY();
		int ballRadius = b.getRadius();
		
		// upper boundary
		if (((ballY + ballRadius) >= (centerY - 18)) && ((ballY + ballRadius) <= (centerY - 13))){
			if(ballX > centerX - 18 && ballX < centerX + 18){
				double new_vY = -Math.abs(b.getvY());
				System.out.println("hit top brick:"+new_vY);
				b.setVertSpeed(new_vY);
			}
		}
		
//		if (ballX > centerX - 18 && ballX < centerX + 18 && ballY >= 207 && ballY <= 216){
//			double new_vY = -Math.abs(b.getvY());
//			System.out.println("hit top brick:"+new_vY);
//			b.setVertSpeed(new_vY);
//		}
		
		
		// lower boundary
		if (ballY - ballRadius > centerY + 15 && ballY - ballRadius < centerY + 20){
			if(ballX > centerX - 18 && ballX < centerX + 18){
				double new_vY = Math.abs(b.getvY());
				System.out.println("hit bottom brick:"+new_vY);
				b.setVertSpeed(new_vY);
			}
		}
		
//		if (ballX > centerX - 18 && ballX < centerX + 18){
//			if(ballY - ballRadius > centerY + 13 && ballY - ballRadius < centerY + 18){
//				double new_vY = Math.abs(b.getvY());
//				System.out.println("hit bottom brick:"+new_vY);
//				b.setVertSpeed(new_vY);
//			}
//		}
		
		// left boundary
		if (ballX + ballRadius > centerX - 18 && ballX + ballRadius < centerX){
			if(ballY > centerY - 18 && ballY < centerY + 18){
				double new_vX = -Math.abs(b.getvX());
				b.setHorizSpeed(new_vX);
			}
			
		}
		
		// right boundary
		if (ballX - ballRadius > centerX  && ballX - ballRadius < centerX + 18){
			if(ballY > centerY - 18 && ballY < centerY + 18){
				double new_vX = Math.abs(b.getvX());
				b.setHorizSpeed(new_vX);
			}
			
		}
		
	}
}
