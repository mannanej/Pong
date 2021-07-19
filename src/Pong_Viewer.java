import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong_Viewer {

	JFrame frame;
	Pong_Panel panel;
	KeyList listener;

	public Pong_Viewer() {

		this.frame = new JFrame();
		this.panel = new Pong_Panel(this.frame);
		this.frame.add(this.panel);
		this.frame.addKeyListener(this.listener);

		Timer t = new Timer(60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.update();
				panel.repaint();
				frame.repaint();
			}
		});

		t.start();
		this.frame.setVisible(true);
		this.frame.setTitle("Snake!");
		this.frame.setSize(1275, 675);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Pong_Viewer viewer = new Pong_Viewer();
	}
}
