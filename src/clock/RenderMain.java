package clock;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RenderMain extends JPanel {
	private static final long serialVersionUID = 322652833713630515L;

	public static BufferedImage img;

	public RenderMain() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				setOpaque(false);
				Timer timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						img = new BufferedImage(getPreferredSize().width, getPreferredSize().height,
								BufferedImage.TYPE_INT_ARGB);

						Graphics g_ = img.getGraphics();
						Graphics2D g = (Graphics2D) g_;

						Date d = new Date();
						double h = Math.toRadians(((d.getHours() % 12) * 30));
						double m = Math.toRadians((d.getMinutes() * 6));
						double s = Math.toRadians((d.getSeconds() * 6));

						g.setStroke(new BasicStroke(1));
						g.setColor(new Color(0, 255, 255, 10));
						g.fillOval(0, 0, 100, 100);

						g.setColor(new Color(0, 128, 128, 128));
						Rectangle rect = new Rectangle(0, 110, getPreferredSize().width, 20);
						g.fillRect(rect.x, rect.y, rect.width, rect.height);

						g.setColor(Color.BLACK);
						String timeString = getTime();
						drawCenteredString(g, timeString, rect);
						g.drawOval(0, 0, 100, 100);

						g.setStroke(new BasicStroke(3));

						g.setColor(new Color(0x000099));
						int sx = (int) (Math.sin(s) * 45);
						int sy = (int) (Math.cos(s) * 45);
						g.drawLine(50, 50, 50 + sx, 50 - sy);

						g.setColor(new Color(0x009900));
						int mx = (int) (Math.sin(m) * 35);
						int my = (int) (Math.cos(m) * 35);
						g.drawLine(50, 50, 50 + mx, 50 - my);

						g.setColor(new Color(0x990000));
						int hx = (int) (Math.sin(h) * 20);
						int hy = (int) (Math.cos(h) * 20);
						g.drawLine(50, 50, 50 + hx, 50 - hy);

						g.dispose();
						repaint();

					}
				});
				timer.start();
			}
		}).start();
	}

	protected String getTime() {
		Date d = new Date();
		int hh = d.getHours();
		int mm = d.getMinutes();
		int ss = d.getSeconds();
		int dispH = ((hh % 12) == 0 ? 12 : hh % 12);
		String timeString = format(dispH) + ":" + format(mm) + ":" + format(ss) + " " + (hh >= 12 ? "PM" : "AM");
		return timeString;
	}

	private String format(int i) {
		String s = i + "";
		if (s.length() == 1)
			return "0" + i;
		return "" + i;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 130);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(img, 0, 0, this);
		g2d.dispose();
	}

	public void drawCenteredString(Graphics g, String text, Rectangle rect) {
		Font font = new Font("", 0, 18);
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as
		// in java 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}
}