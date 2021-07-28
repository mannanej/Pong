import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {

	int x;
	int y;
	int velX;
	int velY;
	int boxVelY;
	int radius;
	int moveSpeed;
	int leftScore;
	int rightScore;
	int frameMaxX;
	int frameMaxY;

	public Ball(int x, int y) {

		this.x = x;
		this.y = y;
		this.radius = 50;
		this.moveSpeed = 10;
		this.frameMaxX = 1275;
		this.frameMaxY = 675;
		Double ran = Math.random();
		if (ran >= 0.00 && ran < 0.25) {
			this.velX = (int) (this.moveSpeed + (ran * 10));
			this.velY = this.moveSpeed;
		} else if (ran >= 0.25 && ran < 0.50) {
			this.velX = (int) ((-1) * (this.moveSpeed + (ran * 10)));
			this.velY = this.moveSpeed;
		} else if (ran >= 0.50 && ran < 0.75) {
			this.velX = (int) (this.moveSpeed + (ran * 10));
			this.velY = (-1) * this.moveSpeed;
		} else {
			this.velX = (int) ((-1) * (this.moveSpeed + (ran * 10)));
			this.velY = (-1) * this.moveSpeed;
		}
	}

	public void drawOn(Graphics g) {

		g = (Graphics2D) g.create();
		g.setColor(Color.WHITE);
		g.fillOval(this.x, this.y, this.radius, this.radius);
	}

	public void update() {

		this.x += this.velX;
		this.y += this.velY;
		if ((this.y - (this.radius - 45)) <= 0 || (this.y + (this.radius + 40)) >= this.frameMaxY) {
			this.velY = (-1) * this.velY;
		}
		if ((this.x - (this.radius - 45)) <= 0 || (this.x + (this.radius + 20)) >= this.frameMaxX) {
			this.velX = (-1) * this.velX;
		}
		if (this.x >= 1275 || this.x <= 0) {
			this.stop();
			this.x = (int) Math.min(Math.max(this.x, 0), this.frameMaxX);
			this.velX = 0;
		}
		if (this.y >= 675 || this.y <= 0) {
			this.y = (int) Math.min(Math.max(this.y, 0), this.frameMaxY);
			this.velY = (-1) * this.velY;
		}
	}

	public void normalizeVelocity() {

		double vectorLength = Math.sqrt(this.velX * this.velX + this.velY * this.velY);

		this.velX = (int) (this.velX / vectorLength * this.moveSpeed * 2);
		this.velY = (int) (this.velY / vectorLength * this.moveSpeed * 2);
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

	public void stop() {

		this.velX = 0;
		this.velY = 0;
	}

	public boolean overlapsWithLeft(Boxes leftBox) {

		if (this.x - this.radius / 8 > 0 && this.x - this.radius / 8 <= leftBox.x + leftBox.width
				&& this.y > leftBox.y - 30 && this.y < leftBox.y + leftBox.height) {
			this.boxVelY += leftBox.velY;
			return true;
		}
		return false;
	}

	public boolean overlapsWithRight(Boxes rightBox) {

		if (this.x + this.radius >= rightBox.x && this.x + this.radius <= this.frameMaxX && this.y > rightBox.y - 30
				&& this.y < rightBox.y + rightBox.height) {
			this.boxVelY += rightBox.velY;
			return true;
		}
		return false;
	}

	public boolean scoreLeft() {

		if (this.x + this.radius >= this.frameMaxX - this.radius / 2) {
			return true;
		}
		return false;
	}

	public boolean scoreRight() {

		if (this.x - this.radius / 8 <= 0) {
			return true;
		}
		return false;
	}

	public void bounceBack() {

		this.velX = (-1) * this.velX;
	}
}
