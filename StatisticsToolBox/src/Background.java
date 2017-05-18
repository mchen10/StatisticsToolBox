import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Background extends JPanel implements ActionListener {
	
	public Background() {
		
		super();
		
		//select import type
		String[] importType = {"CSV", "Excel"};
		
		JComboBox importList = new JComboBox(importType);
		importList.setSelectedIndex(1);
		importList.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JComboBox temp = (JComboBox) arg0.getSource();
		String imp = (String) temp.getSelectedItem();
		
	}
	
}
