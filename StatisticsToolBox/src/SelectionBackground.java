import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class SelectionBackground extends JPanel implements ActionListener {
	
	private JComboBox importList;
	private JComboBox displayList; 
	
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
		
		add(importList);
		add(displayList);
	}
	
	public void actionPerformed(ActionEvent arg0) {

		// TODO Auto-generated method stub
		if (arg0.getSource() == importList) {
			String imp = (String) importList.getSelectedItem();
		} else if (arg0.getSource() == displayList) {
			String display = (String) displayList.getSelectedItem();
		}
		
	}
}

