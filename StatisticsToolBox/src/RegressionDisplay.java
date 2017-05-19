import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RegressionDisplay extends JPanel {

	//graph variables
	private int scale = 0;
	private int centerX = 0, centerY = 0;
	
	public RegressionDisplay() {
		super();
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(49, 24, 601, 401);
		g.setColor(Color.WHITE);
		g.fillRect(50, 25, 600, 400);
		
		
	}
}