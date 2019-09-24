package clock;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Window.Type;
import java.lang.reflect.Method;

import javax.swing.JFrame;

public class Main {
	public static JFrame frame;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		try {
			frame = new JFrame("Clock");
			frame.setType(Type.UTILITY);
			frame.setUndecorated(true);
			setOpaque(frame, false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.add(new RenderMain());
			frame.pack();
			// frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);

			frame.addMouseListener(new Listener());
			frame.addMouseMotionListener(new Listener());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setOpaque(Window window, boolean opaque) throws Exception {
		try {
			Class<?> awtUtilsClass = Class.forName("com.sun.awt.AWTUtilities");
			if (awtUtilsClass != null) {
				Method method = awtUtilsClass.getMethod("setWindowOpaque", Window.class, boolean.class);
				method.invoke(null, window, opaque);
			}
		} catch (Exception exp) {
			throw new Exception("Window opacity not supported");
		}
	}
}