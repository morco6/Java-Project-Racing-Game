package GUI;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import game.racers.Racer;

public class racersInfo extends JFrame  implements ActionListener{
	
	
	private Timer t;
	private int delay = 20;
	private JFrame pop = new JFrame("Racers information");
	
	public int shutDown() {
		t.stop();
		return JFrame.DISPOSE_ON_CLOSE;
	}
	
	public racersInfo() {
		
		/* overrides 'X' button so that we can stop timer from running */
		pop.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pop.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	t.stop();
		    	pop.setVisible(false);
		    }
		});
	
		t = new Timer(delay,this);
		t.start();
		
		int numOfRacers = ToolBar.getArena().getActiveRacers().size() + ToolBar.getArena().getCompleatedRacers().size();
		int width = 480, height = (150 + numOfRacers * 20);
		int i = 0;
		pop.setSize(width, height);
		
		String[] columns = { "Racer name", "Current speed", "Max speed", "Current x location", "Finished" };
		
		Object[][] data = new Object[numOfRacers][5];
		
		for (Racer racer : ToolBar.getArena().getCompleatedRacers()) {
			data[i][0] = racer.getName();
			data[i][1] = racer.getCurrentSpeed();
			data[i][2] = racer.getMaxSpeed();
			data[i][3] = racer.getCurrentLocation().getX();
			data[i][4] = "yes";
			i++;
		}
		for (Racer racer : ToolBar.getArena().getActiveRacers()) {
			data[i][0] = racer.getName();
			data[i][1] = racer.getCurrentSpeed();
			data[i][2] = racer.getMaxSpeed();
			data[i][3] = racer.getCurrentLocation().getX();
			data[i][4] = "No";
			i++;
		}
	
		JTable table = new JTable(data, columns);

		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(table);

		pop.add(scroll);
		
		repaint();
		pop.setVisible(true);
		pop.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		pop.getContentPane().removeAll();
		int numOfRacers = ToolBar.getArena().getActiveRacers().size() + ToolBar.getArena().getCompleatedRacers().size();
		int width = 480, height = (150 + numOfRacers * 20);
		int i = 0;
		pop.setSize(width, height);
		
		String[] columns = { "Racer name", "Current speed", "Max speed", "Current x location", "Finished" };
		
		Object[][] data = new Object[numOfRacers][5];
		
		for (Racer racer : ToolBar.getArena().getCompleatedRacers()) {
			data[i][0] = racer.getName();
			data[i][1] = racer.getCurrentSpeed();
			data[i][2] = racer.getMaxSpeed();
			data[i][3] = racer.getCurrentLocation().getX();
			data[i][4] = "yes";
			i++;
		}
		for (Racer racer : ToolBar.getArena().getActiveRacers()) {
			data[i][0] = racer.getName();
			data[i][1] = racer.getCurrentSpeed();
			data[i][2] = racer.getMaxSpeed();
			data[i][3] = racer.getCurrentLocation().getX();
			data[i][4] = "No";
			i++;
		}
	
		JTable table = new JTable(data, columns);

		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(table);

		pop.add(scroll);
		
		repaint();
		pop.setVisible(true);
		pop.setResizable(false);
		
		
		repaint();
	}		
}
