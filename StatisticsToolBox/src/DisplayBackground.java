import java.awt.CardLayout;

import javax.swing.JPanel;

public class DisplayBackground extends JPanel {
	
	private CardLayout layout;
	
	private RegressionDisplay regression;
	private HistogramDisplay histogram;
	
	public DisplayBackground() {
		super();
		
		layout = new CardLayout();
		setLayout(layout);
		
		regression = new RegressionDisplay();
		this.addMouseListener(regression);
		this.addMouseMotionListener(regression);
		regression.setFocusable(true);
		regression.requestFocusInWindow();
		
		histogram = new HistogramDisplay();
		
		this.add(regression, "1");
		this.add(histogram, "2");
	}
	
	public void changePanel(String next) {
		layout.show(this, next);
		requestFocus();
	}
	
	public void setData(double[] xdata, double[] ydata) {
		regression.createEquation(xdata, ydata);
		histogram.setData(ydata);
	}
}