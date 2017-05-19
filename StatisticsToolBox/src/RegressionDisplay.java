import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RegressionDisplay extends JPanel {

	//graph variables
	private double scale = 0;
	private int centerX = 0, centerY = 0;
	
	//equation variables
	private double[] coeffs = new double[100];
	
	public RegressionDisplay() {
		super();
		
	}
	
	public void createEquation(double[][] dataSet) {
		
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.drawRect(49, 24, 601, 401);
		g.setColor(Color.WHITE);
		g.fillRect(50, 25, 600, 400);
		
		g.setColor(Color.GRAY);
		for (int i = 49; i < 601; i+= 60) {
			g.drawLine(i, 24, i, 425);
		}
		for (int i = 24; i < 401; i += 40) {
			g.drawLine(49, i, 650, i);
		}
		
		g.setColor(Color.BLACK);
		int interval = (int) Math.pow(2, scale);
		
		g.drawString(centerX +"", 345, 12);
		if (centerX == 0) {
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(349, 26, 349, 423);
		}
		for (int i = 1; i <= 5; i++) {
			g.drawString((centerX - i * interval) + "", 342 - 60 * i, 12);
			if (centerX - i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(349 - 60 * i, 26, 349 - 60 * i, 423);
			}
			g.drawString((centerX + i * interval) + "", 345 + 60 * i, 12);
			if (centerX + i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(349 + 60 * i, 26, 349 + 60 * i, 423);
			}
		}
		
		g.drawString(centerY +"", 21, 229);
		if (centerY == 0) {
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(51, 224, 648, 224);
		}
		for (int i = 1; i <= 5; i++) {
			g.drawString((centerY - i * interval) + "", 21, 229 - 40 * i);
			if (centerY - i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(51, 224 - 40 * i, 648, 224 - 40 * i);
			}
			g.drawString((centerY + i * interval) + "", 21, 229 + 40 * i);
			if (centerY + i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(51, 224 + 40 * i, 648, 224 + 40 * i);
			}
		}
			
	}
	
	
}