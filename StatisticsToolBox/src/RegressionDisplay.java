import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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
		
		//nData0 = 4
		
		double leastMedianError = 1000700000;
		double[] bestEstimates = new double[1];
		int bestdegree = -1;
		
		double[] temp;
		for (int i = 1; i < ydata.length - 1; i++) {
			regress.polynomial(i);
			temp = regress.getBestEstimatesErrors();
			Arrays.sort(temp);
			
			System.out.print("Coeffs: ");
			for (int j = 0; j < regress.getBestEstimates().length; j++) {
				System.out.print(regress.getBestEstimates()[j] + " ");
			}
			System.out.println();
			
			System.out.print("Errors: ");
			for (int j = 0; j < temp.length; j++) {
				System.out.print(temp[j] + " ");
			}
			System.out.println();
			if (temp.length % 2 == 0) {
				if ((temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2 < leastMedianError) {
					leastMedianError = (temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2;
					bestEstimates = regress.getBestEstimates();
					bestdegree = i;
				}
			} else {
				if (temp[temp.length / 2] < leastMedianError) {
					leastMedianError = temp[temp.length / 2];
					bestEstimates = regress.getBestEstimates();
					bestdegree = i;
				}
			}
		}

		for (int i = 0; i < bestEstimates.length; i++) {
			coeffs[i] = bestEstimates[i];
//			System.out.print(coeffs[i] + " ");
		}
//		System.out.println();
		degree = bestdegree;
		
		isEquation = true;
		repaint();
	}
	
	public double calculate(double x) {
		double ans = 0;
		System.out.println(degree);
		for (int i = 0; i <= degree; i++) {
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
			
		g.setColor(Color.BLACK);
		if (isEquation) {
			double yfrom , yto = calculate(centerX - 5 * interval), xfrom , xto = centerX - 5 * interval;
			int min = centerY - 5 * interval, max = centerY + 5 * interval;
			for(double i = centerX - 4 * interval; i <= centerX + 5 * interval; i += (interval/5.0)){
				xfrom = xto;
				yfrom = yto;
				
				//new x = i, new y = calculate(i)
				
				xto = i;
				yto = calculate(i);
				
				System.out.println("Original" + xfrom + " " + yfrom + " " + xto + " " + yto);
				System.out.println("Min: " + min + " " + "Max: " + max);
				
				int drawxto = -1, drawyto = -1, drawxfrom = -1, drawyfrom = -1;;
				if (yfrom >= min && yfrom <= max && yto >= min && yto <= max) {
					System.out.println("Hi");
					drawxto = (int)((xto - (centerX - 5 * interval)) / (10 * interval) * 600.0 + 50);
					drawyto = (int)((max - yto) / (10 * interval) * 400.0 + 25);
					drawxfrom = (int)((xfrom - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
					drawyfrom = (int)((max - yfrom) / (10 * interval) * 400.0 + 25);
				} else if (yfrom >= min && yfrom <= max) {
					double slope = (yto - yfrom) / (xto - xfrom);
					if (yto <= min) {
						double interX = (1/slope) * (min - yfrom) + xfrom;
						drawxto = (int)((interX - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyto = (int)((max - min) / (10 * interval) * 400.0 + 25);
						drawxfrom = (int)((xfrom - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyfrom = (int)((max - yfrom) / (10 * interval) * 400.0 + 25);
					} else if (yto >= max) {
						double interX = (1/slope) * (max - yfrom) + xfrom;
						drawxto = (int)((interX - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyto = (int)((max - max) / (10 * interval) * 400.0 + 25);
						drawxfrom = (int)((xfrom - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyfrom = (int)((max - yfrom) / (10 * interval) * 400.0 + 25);
					}
				} else if (yto >= min && yto <= max) {
					double slope = (yto - yfrom) / (xto - xfrom);
					if (yfrom <= min) {
						double interX = (1/slope) * (min - yto) + xto;
						drawxfrom = (int)((interX - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyfrom = (int)((max - min) / (10 * interval) * 400.0 + 25);
						drawxto = (int)((xto - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyto = (int)((max - yto) / (10 * interval) * 400.0 + 25);
					} else if (yfrom >= max) {
						double interX = (1.0/slope) * (max - yto) + xto;
						System.out.println(slope + " " + interX);
						drawxfrom = (int)((interX - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyfrom = (int)((max - max) / (10 * interval) * 400.0 + 25);
						drawxto = (int)((xto - (centerX - 5 * interval)) / (10* interval) * 600.0 + 50);
						drawyto = (int)((max - yto) / (10 * interval) * 400.0 + 25);
					}
				} 
				
				
				
				System.out.println("Draw" + drawxfrom + " " + drawyfrom + " " + drawxto + " " + drawyto);
				System.out.println();
				
//				if (drawxfrom == 50) {
//					g.setColor(Color.RED);
//					System.out.println("hi");
//				} else {
//					g.setColor(Color.BLACK);
//				}
				
				if (drawxto != -1 && drawxfrom != -1) {
					g.drawLine(drawxfrom, drawyfrom, drawxto, drawyto);
				}
			}
			
			String temp = "";
			for (int i = degree; i > 0; i--) {
				if (Double.compare(coeffs[i], 0) == 0) {
					continue;
				}
				temp += coeffs[i]+"x" + i + " + ";
			}
			if (Double.compare(coeffs[0], 0) != 0) {
				temp += coeffs[0];
			} else {
				temp = temp.substring(0, temp.length() - 2);
			}
			
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) == 'x') {
					temp2.add(i + 1);
				}
			}
			
			AttributedString current = new AttributedString(temp);
			for (int i = 0; i < temp2.size(); i++) {
				current.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, temp2.get(i), temp2.get(i) + 1);
			}
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			
			g.drawString(current.getIterator(), 335, 460);
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