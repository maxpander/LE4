package design;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTable;

import examples.AppProjectTest;
import examples.TestMap;
import model.Project;

import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JTextField;

public class AppDesign {
	// Wurzel Präsentationsschicht
		private JFrame frame;
		
		// Wurzel Fachlogikschicht
		private Project project;

	private JTable table;
	private JTextField txtMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppDesign window = new AppDesign();
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
	public AppDesign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		project = AppProjectTest.init();
		frame = new JFrame();
		frame.setTitle("GIS-Example");
		frame.setBounds(100, 100, 551, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnZoom = new JButton("Zoom");
		toolBar.add(btnZoom);
		
		JButton btnPan = new JButton("Pan");
		toolBar.add(btnPan);
		
		JButton btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnPoint);
		
		JButton btnLinestring = new JButton("Linestring");
		toolBar.add(btnLinestring);
		
		JButton btnPolygon = new JButton("Polygon");
		toolBar.add(btnPolygon);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Layer");
	        //create the child nodes
		 	DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
	        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
	        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
	        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
	        vegetableNode.add(new DefaultMutableTreeNode("Potato"));
	         
	        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
	        fruitNode.add(new DefaultMutableTreeNode("Banana"));
	        fruitNode.add(new DefaultMutableTreeNode("Mango"));
	        fruitNode.add(new DefaultMutableTreeNode("Apple"));
	        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
	        fruitNode.add(new DefaultMutableTreeNode("Orange"));
	        
	        //add the child nodes to the root node
	        root.add(vegetableNode);
	        root.add(fruitNode);
			
		
		JTree tree = new JTree(root);
		splitPane.setLeftComponent(tree);

		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setDividerLocation(250);
		splitPane.setRightComponent(splitPane_1);
		
		String[][] rowData = {
				{ "1", "Japan", "245" }, { "2", "USA", "240" }, { "3", "Italien", "220" },
				{ "4", "Spanien", "217" }, { "5", "Türkei", "215"} ,{ "6", "England", "214" },
				{ "7", "Frankreich", "190" }, {"8", "Griechenland", "185" },
				{ "9", "Deutschland", "180" }, {"10", "Portugal", "170" }
				};
		
		String[] columnNames = {
				"ID", "Land", "Durchschnittliche Fernsehdauer pro Tag in Minuten"
				};
		
		table = new JTable(rowData, columnNames);

		splitPane_1.setRightComponent(table);
		
		JPanel panel = (new JPanel(){
			public void paint(Graphics g) {
				super.paint(g);
				g.drawLine(0,  0, this.getWidth(), this.getHeight());
				g.drawLine(0,  this.getWidth(), this.getHeight(), 0);
				g.drawString("MAP", 2*this.getWidth()/3, this.getHeight()/3);
			}
		});
		panel.setBackground(Color.WHITE);
		splitPane_1.setLeftComponent(panel);
		
		txtMessage = new JTextField();
		txtMessage.setText("message");
		frame.getContentPane().add(txtMessage, BorderLayout.SOUTH);
		txtMessage.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnProject = new JMenu("Project");
		menuBar.add(mnProject);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnTool = new JMenu("Tool");
		menuBar.add(mnTool);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
}