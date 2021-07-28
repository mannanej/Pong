
public class LeftCPU {

	public LeftCPU() {

		// Do Nothing
	}

	public void update(Boxes leftBox, Ball ball) {

		if (leftBox.y + (leftBox.height / 2) == ball.y) {
			// Do Nothing
		} else {
			if (leftBox.y + (leftBox.height / 2) < ball.y) {
				leftBox.moveDown();
			} else {
				leftBox.moveUp();
			}
		}
	}
}
