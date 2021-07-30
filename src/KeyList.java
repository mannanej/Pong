import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyList implements KeyListener {

	public Pong_Panel panel;
	public Boxes leftBox;
	public Boxes rightBox;

	public KeyList(Pong_Panel panel, Boxes leftBox, Boxes rightBox) {

		this.panel = panel;
		this.leftBox = leftBox;
		this.rightBox = rightBox;
	}

	@Override
	public void keyTyped(KeyEvent e) {

		// Do Nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			this.rightBox.moveUp();
		} else if (key == KeyEvent.VK_DOWN) {
			this.rightBox.moveDown();
		} else if (key == KeyEvent.VK_W) {
			this.leftBox.moveUp();
		} else if (key == KeyEvent.VK_S) {
			this.leftBox.moveDown();
		} else if (key == KeyEvent.VK_C) {
			this.leftBox.moveCloserLeft();
			this.rightBox.moveCloserRight();
		} else if (key == KeyEvent.VK_F) {
			this.leftBox.moveFartherLeft();
			this.rightBox.moveFartherRight();
		} else if (key == KeyEvent.VK_B) {
			this.panel.ball = new Ball(1275 / 2, 725 / 2);
		} else if (key == KeyEvent.VK_I) {
			if (this.panel.instructions == true) {
				this.panel.instructions = false;
			} else {
				this.panel.instructions = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			this.rightBox.stop();
		} else if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
			this.leftBox.stop();
		} else if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (key == KeyEvent.VK_L) {
			if (this.panel.leftCPUEnabled == true) {
				this.panel.leftCPUEnabled = false;
			} else {
				this.panel.leftCPUEnabled = true;
			}
		} else if (key == KeyEvent.VK_R) {
			if (this.panel.rightCPUEnabled == true) {
				this.panel.rightCPUEnabled = false;
			} else {
				this.panel.rightCPUEnabled = true;
			}
		}
	}
}
