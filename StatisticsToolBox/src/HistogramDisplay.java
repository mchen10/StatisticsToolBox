/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;

public class HistogramDisplay extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    protected ArrayList<Double> data = new ArrayList<Double>();
    protected int bucketSize = 5, xPos = 0, bucketPixelWidth = 50, freqPixelHeight = 10, padding = 50, lastXPos, zoomSensitivity = 1;
    protected Color barColor = Color.GREEN;
    protected int[] lastMPos = new int[2];
    public HistogramDisplay()
    {
        super();
        this.addMouseListener(this);
        this.addComponentListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }
    public void setData(double[] list)
    {
        for (Double d : list)
            data.add(d);
       
        Collections.sort(data);
        
        repaint();
    }
    @Override
    public void paintComponent(Graphics gSuper)
    {
        super.paintComponent(gSuper);
        if (data.isEmpty())
            return;
        Graphics2D g = (Graphics2D) gSuper;
        g.translate(xPos, 0);
        double min = data.get(0), max = data.get(data.size() - 1);
        int curBucket = ((int) min / bucketSize + (min < 0 ? -1 : 0)) * bucketSize, nextBucket = curBucket + bucketSize;
        int i = 0, curX = padding, maxFreq = 0;
        while (i < data.size())
        {
            int freq = 0;
            for (; i < data.size() && data.get(i) < nextBucket; i++)
                freq++;
            nextBucket += bucketSize;
            maxFreq = maxFreq > freq ? maxFreq : freq;
        }
        freqPixelHeight = (this.getHeight() - 2 * padding) / maxFreq;
        i = 0;
        curBucket = ((int) min / bucketSize + (min < 0 ? -1 : 0)) * bucketSize;
        nextBucket = curBucket + bucketSize;
        while (i < data.size())
        {
            int freq = 0;
            for (; i < data.size() && data.get(i) < nextBucket; i++)
                freq++;
            g.setColor(barColor);
            g.fillRect(curX, this.getHeight() - freq * freqPixelHeight - padding, bucketPixelWidth, freq * freqPixelHeight);
            g.setColor(Color.BLACK);
            g.drawString("[" + (nextBucket - bucketSize) + " ," + (nextBucket) + ")", curX, this.getHeight() - padding / 2);
            nextBucket += bucketSize;
            curX += bucketPixelWidth;
        }
        g.setColor(Color.BLACK);
        int fCount = 0;
        for (int y = this.getHeight() - padding; y > padding; y -= freqPixelHeight)
            g.drawString(fCount++ + "", padding / 2, y);
        g.drawLine(padding, padding, padding, this.getHeight() - padding);
        g.drawLine(padding, this.getHeight() - padding, (int) (((max - min) / bucketSize + 2) * bucketPixelWidth), this.getHeight() - padding);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        lastMPos[0] = e.getX();
        lastMPos[1] = e.getY();
        lastXPos = xPos;
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }
    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    @Override
    public void componentResized(ComponentEvent e)
    {
        this.repaint();
    }
    @Override
    public void componentMoved(ComponentEvent e)
    {
    }
    @Override
    public void componentShown(ComponentEvent e)
    {
    }
    @Override
    public void componentHidden(ComponentEvent e)
    {
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        xPos = e.getX() - lastMPos[0] + lastXPos;
        this.repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        bucketSize += e.getUnitsToScroll() * zoomSensitivity;
        bucketSize = bucketSize <= 0 ? 1 : bucketSize;
        this.repaint();
    }
}
