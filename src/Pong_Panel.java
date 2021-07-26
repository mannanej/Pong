import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong_Panel extends JPanel {

	private static final long serialVersionUID = 7932841840610077942L;
	public JFrame frame;
	public Boxes leftBox;
	public Boxes rightBox;
	public Ball ball;
	private final int WIN_WIDTH = 1275;
	private final int WIN_HEIGHT = 725;
	public String leftScore;
	public String rightScore;

	public Pong_Panel(JFrame frame) {

		this.frame = frame;
		this.frame.setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(this.WIN_WIDTH, this.WIN_HEIGHT));
		this.leftBox = new Boxes(0, 240);
		this.rightBox = new Boxes(WIN_WIDTH - 45, 240);
		this.frame.addKeyListener(new KeyList(this.leftBox, this.rightBox));
		this.ball = new Ball(WIN_WIDTH / 2, WIN_HEIGHT / 2);
	}

	@Override
	public void paintComponent(Graphics g) {

		this.setBackground(Color.BLACK);
		this.leftBox.drawOn(g);
		this.rightBox.drawOn(g);
		this.ball.drawOn(g);
	}

	public void update() {

		this.leftBox.update();
		this.rightBox.update();
		if (this.ball.overlapsWithLeft(this.leftBox) || this.ball.overlapsWithRight(this.rightBox)) {
			this.ball.bounceBack();
		}
		this.ball.update();
	}
}