package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	private int frameHeight;
	private int frameWidth;
	private ToolBar toolbar; 
	public viewRace vRace;
	
	public MainFrame(){
		super("Mor Cohen - Java Project");
		frameHeight = 700;
		frameWidth = 1000;
		
		JPanel panel = new JPanel();
		vRace = new viewRace();
		panel.add(vRace);
		toolbar = new ToolBar(this, vRace, panel);
		
		


		
		add(toolbar, BorderLayout.EAST);
		add(panel);
		
		setSize(frameWidth,frameHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

}
