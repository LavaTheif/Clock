package clock;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listener implements MouseListener, MouseMotionListener {
	private static int x = 0;
	private static int y = 0;

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		Main.frame.setLocation(arg0.getXOnScreen() - x, arg0.getYOnScreen() - y);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
}