import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Life {
	protected int centerX;
	protected int centerY;

	public Life(int x, int y) {
		centerX = x;
		centerY = y;
	}

	protected int width = 20; // image size
	protected int height = 20;

	public Rectangle getRect() {
		return new Rectangle(centerX-width/2, centerY-height/2, width, height);
	}

	protected int seqnum = 0;
	protected int seqdir = +1;

	Image[] images = {
			ImageUtils.loadImage("graphics/heart_2.png"),
			ImageUtils.loadImage("graphics/heart_2.png"),
			ImageUtils.loadImage("graphics/heart_2.png"),

	};

	public void draw(Graphics g) {
		int x = centerX - 16;
		int y = centerY - 16;
		g.drawImage(images[seqnum], x, y, null);
	}

}
