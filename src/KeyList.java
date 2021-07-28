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
			this.leftBox.moveRight();
			this.rightBox.moveLeft();
		} else if (key == KeyEvent.VK_L) {
			this.panel.leftCPUEnabled = true;
		} else if (key == KeyEvent.VK_R) {
			this.panel.rightCPUEnabled = true;
		}
	}
}
