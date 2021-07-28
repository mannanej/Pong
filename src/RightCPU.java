
public class RightCPU {

	public RightCPU() {

		// Do Nothing
	}

	public void update(Boxes rightBox, Ball ball) {

		if (rightBox.y + (rightBox.height / 2) == ball.y) {
			// Do Nothing
		} else {
			if (rightBox.y + (rightBox.height / 2) < ball.y) {
				rightBox.moveDown();
			} else {
				rightBox.moveUp();
			}
		}
	}
}
