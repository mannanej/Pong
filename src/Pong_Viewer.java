import javax.swing.JFrame;

public class Pong_Viewer {

	JFrame frame;
	Pong_Panel panel;
	KeyList listener;
	boolean running;

	public Pong_Viewer() {

		this.frame = new JFrame();
		this.panel = new Pong_Panel(this.frame);
		this.frame.add(this.panel);
//		this.frame.addKeyListener(this.listener);
		this.running = true;

		this.frame.setVisible(true);
		this.frame.setTitle("Pong!");
		this.frame.setSize(1275, 675);
		this.panel.setSize(1275, 675);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run() {

		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 30.0; // 60 times per second
		double delta = 0;
		while (this.running) {
			long now = System.nanoTime();
			delta = delta + ((now - lastTime) / ns);
			lastTime = now;
			while (delta >= 1) { // Make sure update is only happening 60 times a second
				// handles all of the logic restricted time
				delta--;
				this.panel.update();
				this.panel.repaint();
				this.frame.repaint();
			}
		}
	}

	public static void main(String[] args) {

		Pong_Viewer viewer = new Pong_Viewer();
		viewer.run();
	}
}
