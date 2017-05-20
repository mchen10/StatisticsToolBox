import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class SelectionBackground extends JPanel implements ActionListener {
	
	private JComboBox importList;
	private JComboBox displayList; 
	
	private JButton selectButton;
	private JFileChooser fileChooser;
	
	public SelectionBackground() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//select import type
		String[] importType = {"CSV", "Excel"};
		
		importList = new JComboBox(importType);
		importList.setSelectedIndex(1);
		importList.addActionListener(this);
		
		String[] displayType = {"Regression"};
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
		} else if (arg0.getSource() == selectButton) {
			int returnVal = fileChooser.showOpenDialog(SelectionBackground.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                //This is where a real application would open the file.
                
            } else {
            	
            }
		}
		
	}
}