import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import flanagan.analysis.Regression;

public class RegressionDisplay extends JPanel implements MouseListener, MouseMotionListener{

	//graph variables
	private double scale = 0;
	private int centerX = 0, centerY = 0;
	
	//equation variables
	private double[] coeffs = new double[100];
	private int degree;
	private boolean isEquation = false;
	
	public RegressionDisplay() {
		//super();
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'), "increaseSize");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('-'), "decreaseSize");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "moveUp");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "moveDown");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "moveLeft");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "moveRight");
		
		getActionMap().put("increaseSize", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				scale += 1;
				repaint();
			}
			
		});
		
		getActionMap().put("decreaseSize", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (scale > 0) {
					scale -= 1;
				}
				repaint();
			}
			
		});
		
		getActionMap().put("moveUp", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				centerY += Math.pow(2,  scale);
				repaint();
			}
			
		});
		
		getActionMap().put("moveDown", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				centerY -= Math.pow(2,  scale);
				repaint();
			}
			
		});
		
		getActionMap().put("moveLeft", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				centerX -= Math.pow(2,  scale);
				repaint();
			}
			
		});
		
		getActionMap().put("moveRight", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				centerX += Math.pow(2,  scale);
				repaint();
			}
			
		});
		
		
	}
	
	public void createEquation(double[] xdata, double[] ydata) {
		Regression regress = new Regression(xdata, ydata);
		
		double leastMedianError = 100000000;
		double[] bestEstimates = new double[1];
		int bestdegree = -1;
		
		double[] temp;
		for (int i = 0; i < 100; i++) {
			regress.polynomial(i);
			temp = regress.getBestEstimatesErrors();
			Arrays.sort(temp);
			if (temp[temp.length / 2] < leastMedianError) {
				leastMedianError = temp[temp.length / 2];
				bestEstimates = regress.getBestEstimates();
				bestdegree = i;
			}
		}
		
		for (int i = 0; i < bestEstimates.length; i++) {
			coeffs[i] = bestEstimates[i];
		}
		degree = bestdegree;
		
		isEquation = true;
		repaint();
	}
	
	public double calculate(double x) {
		double ans = 0;
		for (int i = 0; i < degree; i++) {
			ans += Math.pow(x, i) * coeffs[i];
		}
		return ans;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
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
			g.drawString((centerY + i * interval) + "", 21, 229 - 40 * i);
			if (centerY - i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(51, 224 - 40 * i, 648, 224 - 40 * i);
			}
			g.drawString((centerY - i * interval) + "", 21, 229 + 40 * i);
			if (centerY + i * interval == 0) {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine(51, 224 + 40 * i, 648, 224 + 40 * i);
			}
		}
			
		if (isEquation) {
			double yfrom, yto = 0, xfrom, xto = 0;
			boolean first = true;
			int min = centerY - 5 * interval, max = centerY + 5 * interval;
			for(double i = centerX - 5 * interval; i < centerX + 5 * interval; i += interval){
				xfrom = xto;
				yfrom = yto;
				
				//new x = i, new y = calculate(i)
				
				xto = i;
				yto = calculate(i);
				
				int drawxto = -1, drawyto = -1, drawxfrom = -1, drawyfrom = -1;;
				if (yfrom > min && yfrom < max && yto > min && yto < max) {
					drawxto = (int)((i - (centerX - 5 * interval)) / 600 + 50);
					drawyto = (int)((yto - max) / 400 + 25);
				} else if (yfrom > min && yfrom < max) {
					double slope = (yto - yfrom) / (xto - xfrom);
					if (yto < min) {
						double interX = slope * (min - yfrom) + xfrom;
						drawxto = (int)((interX - (centerX - 5 * interval)) / 600 + 50);
						drawyto = min;
					} else if (yto > max) {
						double interX = slope * (max - yfrom) + xfrom;
						drawxto = (int)((interX - (centerX - 5 * interval)) / 600 + 50);
						drawyto = max;
					}
				} else if (yto > min && yto < max) {
					double slope = (yto - yfrom) / (xto - xfrom);
					if (yfrom < min) {
						double interX = slope * (min - yfrom) + xfrom;
						drawxfrom = (int)((interX - (centerX - 5 * interval)) / 600 + 50);
						drawyfrom = min;
					} else if (yfrom > max) {
						double interX = slope * (max - yfrom) + xfrom;
						drawxfrom = (int)((interX - (centerX - 5 * interval)) / 600 + 50);
						drawyfrom = max;
					}
				} 
				
				if (drawxfrom == -1) {
					drawxfrom = (int)((xto - (centerX - 5 * interval)) / 600 + 50);
					drawyfrom = (int)((yto - max) / 400 + 25);
				}
				
				
				if(!first)
					g.drawLine(drawxfrom, drawyfrom, drawxto, drawyto);
				else 
					first = false;
			}
		}
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private boolean mousePressed = false;
	private int startX = -1, startY = -1;
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = true;
		startX = e.getX();
		startY = e.getY();
		
		System.out.println(startX + " " + startY);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (mousePressed) {
			double changeX = (e.getX() - startX)/137.0;
			double changeY = (e.getY() - startY)/137.0;
			centerX -= changeX * Math.pow(2, scale);
			centerY += changeY * Math.pow(2,  scale);
			
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}