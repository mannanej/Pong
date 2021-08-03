import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong_Panel extends JPanel {

	private static final long serialVersionUID = 7932841840610077942L;
	private final int WIN_WIDTH = 1275;
	private final int WIN_HEIGHT = 725;
	public Clip hitClip;
	public Clip scoreClip;
	public LeftCPU leftCPU;
	public RightCPU rightCPU;
	public JFrame frame;
	public Boxes leftBox;
	public Boxes rightBox;
	public Ball ball;
	public boolean leftCPUEnabled = false;
	public boolean rightCPUEnabled = false;
	public int leftScore = 0;
	public int rightScore = 0;
	public boolean instructions;
	public boolean graphics = false;

	public Pong_Panel(JFrame frame) {

		this.frame = frame;
		this.frame.setBackground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(this.WIN_WIDTH, this.WIN_HEIGHT));
		this.leftBox = new Boxes(5, 240);
		this.rightBox = new Boxes(this.WIN_WIDTH - 70, 240);
		this.ball = new Ball(this.WIN_WIDTH / 2, this.WIN_HEIGHT / 2);
		this.leftCPU = new LeftCPU();
		this.rightCPU = new RightCPU();
		this.frame.addKeyListener(new KeyList(this, this.leftBox, this.rightBox));
		this.instructions = false;
		try {
			File hit = new File("src/sounds/bounce.wav");
			File score = new File("src/sounds/score.wav");
			this.hitClip = AudioSystem.getClip();
			this.scoreClip = AudioSystem.getClip();
			AudioInputStream hitInputStream = AudioSystem.getAudioInputStream(hit);
			AudioInputStream scoreInputStream = AudioSystem.getAudioInputStream(score);
			this.hitClip.open(hitInputStream);
			this.scoreClip.open(scoreInputStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		if (!this.instructions) {
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
			this.ball.drawOn(g, this);
		} else {
			g.setColor(Color.WHITE);

			Font instructions = new Font("Times New Roman", Font.BOLD, 50);
			g.setFont(instructions);
			g.drawString("Instructions:", 25, 50);

			Font controls = new Font("Times New Roman", Font.BOLD, 50);
			g.setFont(controls);
			g.drawString("Left Paddle: W-S", 25, 100);
			g.drawString("Right Paddle: Up-Down", 25, 150);
			g.drawString("Left Paddle CPU: L", 25, 200);
			g.drawString("Right Paddle CPU: R", 25, 250);
			g.drawString("Controls: I", 25, 300);
			g.drawString("Reset Ball: B", 25, 350);
			g.drawString("Move Paddles Closer: C", 25, 400);
			g.drawString("Move Paddles Farther: F", 25, 450);
			g.drawString("Exit Game: ESC", 25, 500);
		}
	}

	public void update() {

		if (!this.instructions) {
			this.leftBox.update();
			this.rightBox.update();
			this.ball.update();
			if (this.leftCPUEnabled) {
				this.leftCPU.update(this.leftBox, this.ball);
			}
			if (this.rightCPUEnabled) {
				this.rightCPU.update(this.rightBox, this.ball);
			}
			if (this.ball.overlapsWithLeft(this.leftBox) || this.ball.overlapsWithRight(this.rightBox)) {
				this.ball.bounceBack();
				this.hitClip.stop();
				this.hitClip.setFramePosition(0);
				this.hitClip.start();
			}
			if (this.ball.scoreLeft()) {
				this.leftScore++;
				this.scoreClip.stop();
				this.scoreClip.setFramePosition(0);
				this.scoreClip.start();
				this.ball = new Ball(this.WIN_WIDTH / 2, this.WIN_HEIGHT / 2);
			}
			if (this.ball.scoreRight()) {
				this.rightScore++;
				this.scoreClip.stop();
				this.scoreClip.setFramePosition(0);
				this.scoreClip.start();
				this.ball = new Ball(this.WIN_WIDTH / 2, this.WIN_HEIGHT / 2);
			}
		} else {
			// Do Nothing
		}
	}
}