import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	
	private JPanel main;
	
	private SelectionBackground selection;
	private DisplayBackground display;
	
	public Main() {
		super("Toolbox");
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    main = new JPanel();
	    
	    main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
	    
	    selection = new SelectionBackground();
	    display = new DisplayBackground();
	    
	    main.add(selection);
	    main.add(display);
	    
	    add(main);
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		Main frame = new Main();
		
	}
}
