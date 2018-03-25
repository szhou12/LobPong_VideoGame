import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Reward {
	
	protected int centerX;
	protected int centerY;

	public Reward(int x, int y) {
		centerX = x;
		centerY = y;
	}

	protected int width = 32; // image size
	protected int height = 32;

	public Rectangle getRect() {
		return new Rectangle(centerX-width/2, centerY-height/2, width, height);
	}

	protected int seqnum = 0;
	protected int seqdir = +1;

	Image[] images = {
			ImageUtils.loadImage("graphics/star-coin-1.png"),
			ImageUtils.loadImage("graphics/star-coin-2.png"),
			ImageUtils.loadImage("graphics/star-coin-3.png"),
			ImageUtils.loadImage("graphics/star-coin-4.png"),
			ImageUtils.loadImage("graphics/star-coin-5.png"),
	};

	public void draw(Graphics g) {
		int x = centerX - 16;
		int y = centerY - 16;
		g.drawImage(images[seqnum], x, y, null);
	}

	public void update() {
		seqnum += seqdir;
		if (seqnum == images.length-1 || seqnum == 0) {
			seqdir *= -1;
		}
	}

}
