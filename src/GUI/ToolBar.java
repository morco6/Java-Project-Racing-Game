package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JTextField;

import factory.CarRaceBuilder;
import factory.RaceBuilder;
import factory.RacingClassesFinder;
import factory.factoryArena;
import factory.iBuilder;
import game.arenas.Arena;
import game.arenas.iArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.iRacer;
import game.racers.decorator.ColoredRacer;
import game.racers.decorator.WheeledRacer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utilities.EnumContainer.Color;
import utilities.EnumContainer.RacerEvent;

import java.util.Observable;

public class ToolBar extends JPanel {
	private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private int hgap;
    private int vgap;
    private JLabel resolutionScreen, chooseArena, arenaLength, maxRacersNumber, chooseRacer, chooseColor, racerName, maxSpeed, accelerationT;
    private JButton buildArenaB, addRacerB, startRaceB, showInfoB, decorateB, buildCarRaceB;
    private JComboBox<String> cbox1, cbox2, cboxClone;
    private JComboBox<Color> cbox3;
    private JTextField tfield1, tfield2, tfield3, tfield4, tfield5;
    private decorationDialog decorationD;
    private static factoryArena factory;
    
    public static int cloneNum = 0;
    
    private static Arena arena;
    private static RaceBuilder builder = RaceBuilder.getInstance();
    private static ArrayList<Racer> racers;
    private static boolean activeRace;
    private static boolean raceCompleted;
    private static double [] arrToSort;
    
    private Racer cloneR;
    
	private static void resetRace() {
		racers = null;
		arena = null;
		raceCompleted = false;
	} 
   
    public ToolBar(JFrame frame, viewRace vRace, JPanel panel){
		
    	activeRace = false;
    	raceCompleted = false;
		hgap = 3;
        vgap = 1;
		
		Dimension dim = getPreferredSize(); 
		dim.width = 200;
		setPreferredSize(dim);
		
        setBorder(BorderFactory.createEmptyBorder(hgap, hgap, hgap, hgap));
        setLayout(new GridLayout(3, 0, hgap, vgap));

        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("Arena"));
        chooseArena = new JLabel("Choose Arena:");
		
        cbox1 = new JComboBox<>();
        cbox1.addItem("Air");
        cbox1.addItem("Land");
        cbox1.addItem("Navy");
        /*
        cbox1 = new JComboBox<>();
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList())
			cbox1.addItem(string);
        */
		arenaLength = new JLabel("Arena Length:");
        tfield1 = new JTextField(10); 
        tfield1.setText("1000");
        tfield1.setFont(new Font("Arial", Font.PLAIN, 12));
        maxRacersNumber = new JLabel("Max Racers Number:");
        tfield2 = new JTextField(10); 
        tfield2.setText("8");
        tfield2.setFont(new Font("Arial", Font.PLAIN, 12));
        buildArenaB = new JButton("Build Arena");
        
        buildCarRaceB = new JButton("Build Car Race");
        
        topPanel.add(chooseArena);
        topPanel.add(cbox1);
        topPanel.add(arenaLength); 
        topPanel.add(tfield1); 
        topPanel.add(maxRacersNumber); 
        topPanel.add(tfield2); 
        topPanel.add(buildArenaB);
        topPanel.add(buildCarRaceB);

        centerPanel = new JPanel(); 
        centerPanel.setBorder(BorderFactory.createTitledBorder("Racer"));
        centerPanel.setLayout(new GridLayout(7, 0, 0, 10));
        chooseRacer = new JLabel("Choose Racer:");
        
        cbox2 = new JComboBox<>();
		for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) 
			cbox2.addItem(string);
        
        chooseColor = new JLabel("Choose Color:");
        cbox3 = new JComboBox<Color>();
        cbox3.addItem(Color.BLACK);
        cbox3.addItem(Color.BLUE);
        cbox3.addItem(Color.GREEN);
        cbox3.addItem(Color.RED);
        cbox3.addItem(Color.YELLOW);

        racerName = new JLabel("Racer Name:  ");
        tfield3 = new JTextField(10);
        maxSpeed = new JLabel("Max Speed:");
        tfield4 = new JTextField(10);
        accelerationT = new JLabel("Acceleration:");
        tfield5 = new JTextField(10);
        addRacerB = new JButton("Add Racer");
        addRacerB.setFont(new Font("Arial", Font.PLAIN, 12));
        decorateB = new JButton("Decorate");
        centerPanel.add(chooseRacer);   
        centerPanel.add(cbox2);
        centerPanel.add(chooseColor);   
        centerPanel.add(cbox3);
        centerPanel.add(racerName);   
        centerPanel.add(tfield3);
        centerPanel.add(maxSpeed);   
        centerPanel.add(tfield4);        
        centerPanel.add(accelerationT);   
        centerPanel.add(tfield5);
        
                
        cboxClone = new JComboBox<>();
		cboxClone.addItem("Clone of");
		cboxClone.disable();
		JButton addCloneB = new JButton("add clone");
		addCloneB.disable();
		//centerPanel.add(cloneL);
		centerPanel.add(cboxClone);
		centerPanel.add(addCloneB);
		
		
        centerPanel.add(addRacerB);
        centerPanel.add(decorateB);
        
        
        bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new GridLayout(4, 0, 0, 10));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Make Operation"));
        startRaceB = new JButton("Start Race");
        showInfoB = new JButton("Show Info");
        resolutionScreen = new JLabel("Fit to FHD screen resolution");
        bottomPanel.add(startRaceB);
        bottomPanel.add(showInfoB);
        bottomPanel.add(resolutionScreen);
        
        add(topPanel);
        add(centerPanel);
        add(bottomPanel);
        
        /*-------------------------decorator buttons----------------------------*/
		JLabel chooseRacer1 = new JLabel("Choose Racer:");
        
		JComboBox<String> cbox22 = new JComboBox<>();
		for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) 
			cbox22.addItem(string);
        
		JLabel chooseColor1 = new JLabel("Choose Color:");
		JComboBox<Color> cbox33 = new JComboBox<Color>();
        cbox33.addItem(Color.BLACK);
        cbox33.addItem(Color.BLUE);
        cbox33.addItem(Color.GREEN);
        cbox33.addItem(Color.RED);
        cbox33.addItem(Color.YELLOW);

        JLabel racerName1 = new JLabel("Racer Name:  ");
        JTextField tfield33 = new JTextField(10);
        JLabel maxSpeed1 = new JLabel("Max Speed:");
        JTextField tfield44 = new JTextField(10);
        JLabel accelerationT1 = new JLabel("Acceleration:");
        JTextField tfield55 = new JTextField(10);
		
		
		
		JLabel numberOfWheelsL = new JLabel("Number Of Wheels:  ");
        JTextField tfieldWheels = new JTextField(10);
        JButton decorateAndAddRacerB = new JButton("Add Decorated Racer");
        
        
        /*-------------------------build arena----------------------------*/
        
        buildArenaB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(activeRace){
					JOptionPane.showMessageDialog(null, "You can not build an arena during a race");
					return;
				}
				
				int arenaLength = Integer.valueOf(tfield1.getText());
				int amountOfRacers = Integer.valueOf(tfield2.getText());
				
				
				factory = new factoryArena();
				iArena iarena = factory.getArena(cbox1.getSelectedItem().toString(), arenaLength, amountOfRacers, builder);
				
				//String cls = cbox1.getSelectedItem().toString();
				String cls = iarena.arenaType();
				if (amountOfRacers >= 1 && amountOfRacers <=20 && arenaLength >= 100 && arenaLength <= 3000)
				{
					for (String str : RacingClassesFinder.getInstance().getArenasList()){
						if (str.contains(cls)){
							try {
								
								resetRace();
								vRace.resetPanel();
								arena = (Arena)(iarena);
								
								racers = new ArrayList<>();	
								Racer.setLastSerialNumber(1);
								vRace.setHeight((int)(amountOfRacers*87.5));
								vRace.setWidth(arenaLength);
								frame.setSize(arenaLength, (int)(amountOfRacers*87.5));
								vRace.loadImageBackground(cls);
							} //catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							catch(SecurityException | IllegalArgumentException e) {
								e.printStackTrace();
							}
						}
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");

				
			}
			
		});
        
        
        
        
        /*-------------------------Build Car Race----------------------------*/
        
        buildCarRaceB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(activeRace){
					JOptionPane.showMessageDialog(null, "You can not build an arena during a race");
					return;
				}
				
				int arenaLength = Integer.valueOf(tfield1.getText());
				int amountOfRacers = Integer.valueOf(tfield2.getText());
				
				
				factory = new factoryArena();
				iArena iarena = factory.getArena("Land", arenaLength, amountOfRacers, builder);
				
				String cls = iarena.arenaType();
				if (amountOfRacers >= 1 && amountOfRacers <=20 && arenaLength >= 100 && arenaLength <= 3000)
				{
					for (String str : RacingClassesFinder.getInstance().getArenasList()){
						if (str.contains(cls)){
							try {
								
								resetRace();
								vRace.resetPanel();
								arena = (Arena)(iarena);
								iBuilder carRace = new CarRaceBuilder(amountOfRacers, arena);
								arena = carRace.setRace(vRace, builder);
								vRace.setHeight((int)(amountOfRacers*87.5));
								vRace.setWidth(arenaLength);
								frame.setSize(arenaLength, (int)(amountOfRacers*87.5));
								vRace.loadImageBackground(cls);
								
								
								
							} //catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							catch(SecurityException | IllegalArgumentException e) {
								e.printStackTrace();
							}
						}
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");

				
			}
			
		});
        
        
        
        /*-------------------------build racer----------------------------*/
        
        addRacerB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				cbox2.enable();
				tfield3.enable();
				tfield4.enable();
				tfield5.enable();
				
				if (arena == null){
					JOptionPane.showMessageDialog(null, "Arena not initiated!" );
					return;
				}
				
				if (activeRace == true){
					JOptionPane.showMessageDialog(null, "Race already running.");
					return;
				}
				
				if (raceCompleted == true){
					JOptionPane.showMessageDialog(null, "Current race eneded. please build new arena.");
					return;
				}
				
				if (tfield3.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Racer name field empty!" );
					return;
				}
				
				String cls = cbox2.getSelectedItem().toString();
				String RacerName =  tfield3.getText().toString();
				double RacerSpeed = Double.valueOf(tfield4.getText().toString());
				double RacerAcceleration = Double.valueOf(tfield5.getText().toString());
				Color color = (Color)cbox3.getSelectedItem();
				
				for(Racer tmp:racers){
					if(tmp.getName().equals(RacerName)){
						JOptionPane.showMessageDialog(null, "This name is already taken!" );
						return;
					}
				}
				
				if (RacerSpeed >= 0 && RacerAcceleration >= 0){
					for (String str : RacingClassesFinder.getInstance().getRacersList()){
						if (str.contains(cls)){
							if (cls.equals("Airplane") || cls.equals("Car")  || cls.equals("Bicycle")){
								try {
									
									racers.add(builder.buildWheeledRacer(str, RacerName, RacerSpeed, RacerAcceleration, color, 0));
									racers.get(racers.size()-1).setArena(arena);
									
									if (addRacerToArena(racers.get(racers.size()-1))){
										
										int arenaLength = Integer.valueOf(tfield1.getText());
										vRace.setWidth(arenaLength);
										vRace.loadRacerBackground(cls,color.toString());
										
										cboxClone.addItem(RacerName);
										cboxClone.enable();
										addCloneB.enable();
										
										
										
									}
										//vRace.loadRacerBackground(cls,color.toString());
									
								} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
										| InstantiationException | IllegalAccessException | IllegalArgumentException
										| InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else if ( cls.equals("Helicopter")  || cls.equals("Horse") || cls.equals("RowBoat") || cls.equals("SpeedBoat") )
							{
								try {

									racers.add(builder.buildRacer(str, RacerName, RacerSpeed, RacerAcceleration, color));
									racers.get(racers.size()-1).setArena(arena);
									if ( addRacerToArena(racers.get(racers.size()-1)) )
										vRace.loadRacerBackground(cls,color.toString());
									cboxClone.addItem(RacerName);
									cboxClone.enable();
									addCloneB.enable();
									
								} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
										| InstantiationException | IllegalAccessException | IllegalArgumentException
										| InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
			}
			}
		);

        /*-------------------------start race----------------------------*/
        
        startRaceB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if (arena == null){
					JOptionPane.showMessageDialog(null, "No arena exists.");
					return;
				}
				if (!arena.hasActiveRacers()){
					JOptionPane.showMessageDialog(null, "No active Racers.");
					return;
				}
				if (activeRace){
					JOptionPane.showMessageDialog(null, "Race already running..");
					return;
				}
				if(racers != null)
					arrToSort = new double[racers.size()];
				if (arena != null || !arena.hasActiveRacers()) {
					activeRace = true;
					arena.initRace();
					Thread t = new Thread() {
						@Override
						public void run() {
							arena.startRace();
						}
					};
					t.start();
				}	
				else
					JOptionPane.showMessageDialog(null, "arena not set");
			}
		});
        
        
        /*-------------------------show information----------------------------*/
        
        showInfoB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if (arena == null){
					JOptionPane.showMessageDialog(null, "No arena exists.");
					return;
				}
				if (!arena.hasActiveRacers()){
					JOptionPane.showMessageDialog(null, "No active Racers.");
					return;
				}
	
				else {
					Thread t =new Thread() {
						@Override
						public void run() {
							racersInfo r = new racersInfo();
						}
					};
					t.start();
						
					}
			}
		});
        
        
        /*-------------------------decoration----------------------------*/
        decorationD = new decorationDialog(this);
        ActionListener acLs1 = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if (arena == null){
					JOptionPane.showMessageDialog(null, "Arena not initiated!" );
					return;
				}
				
				if (activeRace == true){
					JOptionPane.showMessageDialog(null, "Race already running.");
					return;
				}
				
				if (raceCompleted == true){
					JOptionPane.showMessageDialog(null, "Current race eneded. please build new arena.");
					return;
				}
				
				decorationD.setVisible(true);
				decorationD.setLayout(new GridLayout(7,0,0,10));
				
		        decorationD.add(chooseRacer1);   
		        decorationD.add(cbox22);
		        decorationD.add(chooseColor1);   
		        decorationD.add(cbox33);
		        decorationD.add(racerName1);   
		        decorationD.add(tfield33);
		        decorationD.add(maxSpeed1);   
		        decorationD.add(tfield44);        
		        decorationD.add(accelerationT1);   
		        decorationD.add(tfield55);
		        decorationD.add(numberOfWheelsL);
		        decorationD.add(tfieldWheels);
		        decorationD.add(decorateAndAddRacerB);
				
		        ActionListener acLs = new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						
						
						if (tfield33.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Racer name field empty!" );
							return;
						}
						
						if (tfieldWheels.getText().equals("")){
							JOptionPane.showMessageDialog(null, "number of wheels field empty!" );
							return;
						}
						
						String cls = cbox22.getSelectedItem().toString();
						String RacerName =  tfield33.getText().toString();
						double RacerSpeed = Double.valueOf(tfield44.getText().toString());
						double RacerAcceleration = Double.valueOf(tfield55.getText().toString());
						Color color = (Color)cbox33.getSelectedItem();
						int numOfWheels = Integer.valueOf(tfieldWheels.getText());
						for (Racer rr : racers){
							if(rr.getName().equals(RacerName)){
								return;
							}
						}
						
						if (RacerSpeed >= 0 && RacerAcceleration >= 0){
							for (String str : RacingClassesFinder.getInstance().getRacersList()){
								if (str.contains(cls)){
									if (cls.equals("Airplane") || cls.equals("Car")  || cls.equals("Bicycle")){
										try {
											racers.add(builder.buildWheeledRacer(str, RacerName, RacerSpeed, RacerAcceleration, color, 0));
											//iRacer iracer = new WheeledRacer(new ColoredRacer(racers.get(racers.size()-1),color), numOfWheels);
											
											iRacer iracer = new WheeledRacer(racers.get(racers.size()-1), numOfWheels);
											iracer.addAttribute();
											racers.remove(racers.get(racers.size()-1));
											racers.add((Racer)(iracer.getDecoratedRacer()));
											racers.get(racers.size()-1).setArena(arena);
											
											iracer = new ColoredRacer(racers.get(racers.size()-1), color);
											iracer.addAttribute();
											racers.remove(racers.get(racers.size()-1));
											racers.add((Racer)(iracer.getDecoratedRacer()));
											racers.get(racers.size()-1).setArena(arena);
											
											System.out.println("num of wheels: " + racers.get(racers.size()-1).getNumOfWheels());
											if (addRacerToArena(racers.get(racers.size()-1))){
												
												int arenaLength = Integer.valueOf(tfield1.getText());
												vRace.setWidth(arenaLength);
												vRace.loadRacerBackground(cls,color.toString());
												cboxClone.addItem(RacerName);
												decorationD.dispose();
												
												
											}
											
										} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
												| InstantiationException | IllegalAccessException | IllegalArgumentException
												| InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									else if ( cls.equals("Helicopter")  || cls.equals("Horse") || cls.equals("RowBoat") || cls.equals("SpeedBoat") )
									{
										try {

											racers.add(builder.buildRacer(str, RacerName, RacerSpeed, RacerAcceleration, color));
											iRacer iracer = new WheeledRacer(racers.get(racers.size()-1), numOfWheels);
											iracer.addAttribute();
											racers.remove(racers.get(racers.size()-1));
											racers.add((Racer)(iracer.getDecoratedRacer()));
											racers.get(racers.size()-1).setArena(arena);
											
											iracer = new ColoredRacer(racers.get(racers.size()-1), color);
											iracer.addAttribute();
											racers.remove(racers.get(racers.size()-1));
											racers.add((Racer)(iracer.getDecoratedRacer()));
											racers.get(racers.size()-1).setArena(arena);
											
											System.out.println("num of wheels: " + racers.get(racers.size()-1).getNumOfWheels());
											if (addRacerToArena(racers.get(racers.size()-1))){
												
												int arenaLength = Integer.valueOf(tfield1.getText());
												vRace.setWidth(arenaLength);
												vRace.loadRacerBackground(cls,color.toString());
												cboxClone.addItem(RacerName);
												decorationD.dispose();
												
											}
											
										} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
												| InstantiationException | IllegalAccessException | IllegalArgumentException
												| InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}
							
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
					}
						
						
					};
					decorateAndAddRacerB.addActionListener(acLs);
			
			}};
			decorateB.addActionListener(acLs1);
	
    
			/*-------------------------clone racer----------------------------*/
		    
		    cboxClone.addActionListener (new ActionListener () {
		        public void actionPerformed(ActionEvent e) {
		        	if(!(cboxClone.getSelectedItem().equals("Clone of"))){
						for(Racer racer : racers){
							if(cboxClone.getSelectedItem().equals(racer.getName())){
								cbox2.setSelectedItem(racer.className());
								tfield3.setText(racer.getName());
								cbox3.setSelectedItem(racer.getColor());
								String maxSpeed = (Double.valueOf(racer.getMaxSpeed())).toString();
								tfield4.setText(maxSpeed);
								String acceleration = (Double.valueOf(racer.getAcceleration())).toString();
								tfield5.setText(acceleration);
								
								cbox2.disable();
								tfield3.disable();
								tfield4.disable();
								tfield5.disable();
								
								cloneR = racer;
							}
						}
					}	
					else{
						cbox2.enable();
						tfield3.enable();
						tfield4.enable();
						tfield5.enable();
						addCloneB.disable();
					}
		        }
		    });
    
		    /*-------------------------init and upgrade clone----------------------------*/
		    addCloneB.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {

						if(cboxClone.getSelectedItem().equals("Clone of"))
							return;
						
						Color color = (Color)cbox3.getSelectedItem();
						cloneNum++;
						iRacer iracer = new ColoredRacer(cloneR.clone(builder, cloneNum), (Color)cbox3.getSelectedItem());
						iracer.addAttribute();
						racers.add((Racer)(iracer.getDecoratedRacer()));
						racers.get(racers.size()-1).setArena(arena);
						
						if (addRacerToArena(racers.get(racers.size()-1))){
							int arenaLength = Integer.valueOf(tfield1.getText());
							vRace.setWidth(arenaLength);
							vRace.loadRacerBackground(racers.get(racers.size()-1).className(), color.toString());		
							cboxClone.addItem(racers.get(racers.size()-1).getName());
						}
						
						cboxClone.setSelectedItem("Clone of");
						
				}	
		    });
    
    
    }
    
   
    
    
    private static boolean addRacerToArena(Racer newRcaer) {
		try {
			arena.addRacer(newRcaer);
			return true;
		} catch (RacerLimitException e) {
			JOptionPane.showMessageDialog(null, "[Error] " + e.getMessage());
			return false;
		} catch (RacerTypeException e) {
			JOptionPane.showMessageDialog(null, "[Error] " + e.getMessage());
			return false;
		}
	}

	
    public JButton getBuildArenaButton(){
    	return this.buildArenaB;
    }
	
    public JTextField getTextFieldArena(){
    	return this.tfield1;
    }
    
    public JTextField getTextFieldMaxRacers(){
    	return this.tfield2;
    }
    
    public JComboBox<String> getComboBoxArena(){
    	return this.cbox1;
    }
    
    public JComboBox<String> getComboBoxChooseRacers(){
    	return this.cbox2;
    }
    
    public static void setInRace(boolean inRace) {
		ToolBar.activeRace = inRace;
	}
    
	public static void setRaceEnded(boolean raceEnded) {
		ToolBar.raceCompleted = raceEnded;
	}
	
	public static Arena getArena() {
		return arena;
	}
	
	public synchronized static void update(Observable observable, Object arg)
	{
		RacerEvent event = (RacerEvent) arg;
		switch (event)
		{
		case ACTIVE:
			int i = 0;
			if(racers != null){
			for(Racer r:racers){ //array with current location of each racer
				arrToSort[i] = r.getCurrentLocation().getX();
				i++;
			}
			Arrays.sort(arrToSort); //sort array by value -> smallest to largest
			for(int z = 0; z < arrToSort.length / 2; z++) // reverse array -> largest to smallest
			{
			    double temp = arrToSort[z];
			    arrToSort[z] = arrToSort[arrToSort.length - z - 1];
			    arrToSort[arrToSort.length - z- 1] = temp;
			}
			for(int x = racers.size(); x>0; x--){
				if(arrToSort[x-1] == ((Racer)(observable)).getCurrentLocation().getX()){
					System.out.println("racer #" + ((Racer)observable).getSerialNumber() + " Is Active ==> current place: " + (x));
					break;
				}		
			}
			}break;
		case BROKENDOWN:
			System.out.println("racer #" + ((Racer)observable).getSerialNumber() + " broke after " + ((Racer)observable).timer + "ms");
			arena.getBrokenRacers().add((Racer)observable);
			arena.getActiveRacers().remove((Racer)observable);
			break;
		case DISABLED:
			System.out.println("racer #" + ((Racer)observable).getSerialNumber() + " Is Disable");
			arena.getDisabledRacers().add((Racer)observable);
			arena.getActiveRacers().remove((Racer)observable);
			break;
		case FINISHED:
			arena.getCompleatedRacers().add((Racer)observable);
			arena.getActiveRacers().remove((Racer)observable);
			System.out.println("racer #" + ((Racer)observable).getSerialNumber() + " finished after " + ((Racer)observable).timer + "ms");
			
			Arena.setActiveRacersBoolean(arena.hasActiveRacers());
			if (arena.isActiveRacersBoolean() == false){
				ToolBar.setInRace(false);
				ToolBar.setRaceEnded(true);
				System.out.println("race finished");
			}
			break;
		case REPAIRED:
			break;
		default:
			break;
		}
	}
    
}
