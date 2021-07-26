import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Boxes {

	int x;
	int y;
	int velX;
	int velY;
	int width;
	int height;
	int moveSpeed;

	public Boxes(int x, int y) {

		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 100;
		this.moveSpeed = 5;
		this.velX = 0;
		this.velY = 0;
	}

	public void drawOn(Graphics g) {

		g = (Graphics2D) g.create();
		g.setColor(Color.WHITE);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	public void update() {

		this.x += this.velX;
		this.y += this.velY;
	}

	public void normalizeVelocity() {

		double vectorLength = Math.sqrt(this.velX * this.velX + this.velY * this.velY);

		this.velX = (int) (this.velX / vectorLength * this.moveSpeed * 2);
		this.velY = (int) (this.velY / vectorLength * this.moveSpeed * 2);
	}

	public void moveUp() {

		this.velX = 0;
		this.velY = (-1) * this.moveSpeed;
		this.normalizeVelocity();
	}

	public void moveDown() {

		this.velX = 0;
		this.velY = this.moveSpeed;
		this.normalizeVelocity();
	}

	public void moveLeft() {

		this.velX = (-1) * this.moveSpeed;
		this.velY = 0;
		this.normalizeVelocity();
	}

	public void moveRight() {

		this.velX = this.moveSpeed;
		this.velY = 0;
		this.normalizeVelocity();
	}

	public void stop() {

		this.velX = 0;
		this.velY = 0;
	}
}
