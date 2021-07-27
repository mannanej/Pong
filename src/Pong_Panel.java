import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	public int leftScore = 0;
	public int rightScore = 0;

	public Pong_Panel(JFrame frame) {

		this.frame = frame;
		this.frame.setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(this.WIN_WIDTH, this.WIN_HEIGHT));
		this.leftBox = new Boxes(5, 240);
		this.rightBox = new Boxes(WIN_WIDTH - 70, 240);
		this.frame.addKeyListener(new KeyList(this.leftBox, this.rightBox));
		this.ball = new Ball(WIN_WIDTH / 2, WIN_HEIGHT / 2);
	}

	@Override
	public void paintComponent(Graphics g) {

		g.setColor(Color.WHITE);
		
		Font title = new Font("Times New Roman", Font.BOLD, 100);
		g.setFont(title);
		g.drawString("Pong", (int) (this.WIN_WIDTH / 2.4), this.WIN_HEIGHT - 125);
		
		Font score = new Font("Times New Roman", Font.BOLD, 100);
		g.setFont(score);
		g.drawString("" + this.leftScore, (this.WIN_WIDTH / 2) - 100, 100);
		g.drawString("" + this.rightScore, (this.WIN_WIDTH / 2) + 5, 100);
		
		Font dottedLine = new Font("Times New Roman", Font.PLAIN, 50);
		g.setFont(dottedLine);
		for (int i = 1; i < 14; i++) {
			g.drawString("|", (int) (this.WIN_WIDTH / 2), 50 * i);
		}

		this.setBackground(Color.BLACK);
		this.leftBox.drawOn(g);
		this.rightBox.drawOn(g);
		this.ball.drawOn(g);
	}

	public void update() {

		this.leftBox.update();
		this.rightBox.update();
		this.ball.update();
		if (this.ball.overlapsWithLeft(this.leftBox) || this.ball.overlapsWithRight(this.rightBox)) {
			this.leftScore++;
			this.ball.bounceBack();
		}
	}
}