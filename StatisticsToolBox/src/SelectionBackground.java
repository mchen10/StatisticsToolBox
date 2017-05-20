import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class SelectionBackground extends JPanel implements ActionListener {
	
	private JComboBox importList;
	private JComboBox displayList; 
	
	private JButton selectButton;
	private JFileChooser fileChooser;
	
	private Main main;
	
	public SelectionBackground(Main main) {
		super();
		
		this.main = main;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//select import type
		String[] importType = {"CSV", "Excel"};
		
		importList = new JComboBox(importType);
		importList.setSelectedIndex(1);
		importList.addActionListener(this);
		
		String[] displayType = {"Regression", "Histogram"};
		displayList = new JComboBox(displayType);
		displayList.setSelectedIndex(0);
		displayList.addActionListener(this);
		
		fileChooser = new JFileChooser();
		selectButton = new JButton("Choose File");
		selectButton.addActionListener(this);
		
		add(importList); 
		add(displayList);
		add(selectButton);
	}
	
	public void actionPerformed(ActionEvent arg0) {

		// TODO Auto-generated method stub
		if (arg0.getSource() == importList) {
			String imp = (String) importList.getSelectedItem();
		} else if (arg0.getSource() == displayList) {
			String display = (String) displayList.getSelectedItem();
			if (display == "Regression") {
				main.changePanel("1");
			} else if (display == "Histogram") {
				main.changePanel("2");
			}
		} else if (arg0.getSource() == selectButton) {
			int returnVal = fileChooser.showOpenDialog(SelectionBackground.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                //This is where a real application would open the file.
                
                if (importList.getSelectedItem() == "Excel") {
                	 try {
     					POIFSFileSystem pofile = new POIFSFileSystem(new FileInputStream(file));
     					HSSFWorkbook excelsheet = new HSSFWorkbook(pofile);
     					HSSFSheet sheet = excelsheet.getSheetAt(0);
     					HSSFRow row;
     					HSSFCell cell;

     					int rows = sheet.getPhysicalNumberOfRows();

     					int cols = 0; // No of columns
     					int tmp = 0;

     					for(int i = 0; i < rows; i++) {
     					    row = sheet.getRow(i);
     					    if(row != null) {
     					        tmp = sheet.getRow(i).getPhysicalNumberOfCells();
     					        if(tmp > cols) cols = tmp;
     					     }
     					}

     					double[] xdata = new double[rows];
     					double[] ydata = new double[cols];
     					
     				    for(int r = 0; r < rows; r++) {
     				        row = sheet.getRow(r);
     				        if(row != null) {
     				            for(int c = 0; c < cols; c++) {
     				                cell = row.getCell(c);
     				                if(cell != null) {
     				                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
     				                    	if (c == 0) {
     					                    	xdata[r] = cell.getNumericCellValue();
     				                    	} else {
     					                    	ydata[r] = cell.getNumericCellValue();
     				                    	}
     				                    } 
     				                }
     				            }
     				        }
     				    }
     				} catch (FileNotFoundException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				} catch (IOException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
                }
                
            } else {
            	
            }
		}
		
	}
	
	public void setData(double[] xdata, double[] ydata) {
		main.setEquation(xdata, ydata);
	}
}