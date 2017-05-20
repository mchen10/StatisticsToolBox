import java.awt.CardLayout;

import javax.swing.JPanel;

public class DisplayBackground extends JPanel {
	
	private CardLayout layout;
	
	private RegressionDisplay regression;
	
	public DisplayBackground() {
		super();
		
		layout = new CardLayout();
		setLayout(layout);
		
		regression = new RegressionDisplay();
		this.addMouseListener(regression);
		this.addMouseMotionListener(regression);
		regression.setFocusable(true);
		regression.requestFocusInWindow();
		
		this.add(regression, "1");
		
	}
}