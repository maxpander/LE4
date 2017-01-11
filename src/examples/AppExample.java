package examples;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Project;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Diese Application zeigt einen ersten Ansatz zur Interaktion zwischen 
 * <li>dem Modell (Fachlogikschicht),
 * <li>dem Controller (Zwischenschicht),
 * <li>den Views (Präsentationsschicht).
 * <br>Darin sind die Attribute
 * <li>frame:Frame der Zugriffspunkt (Wurzel) der Präsentationsschicht,
 * <li>project:Project der Zugriffspunkt (Wurzel) der Fachlogikschicht. 
 * @author A.Zimmermann, HS BOCHUM, 2017
 *
 */
public class AppExample {

	// Wurzel Präsentationsschicht
	private JFrame frame;
	
	// Wurzel Fachlogikschicht
	private Project project;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppExample window = new AppExample();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppExample() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		project = AppProjectTest.init();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TestMap map = new TestMap();
		JPanel panel = map;
		map.setProject(project);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
