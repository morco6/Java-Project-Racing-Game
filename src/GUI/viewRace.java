package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class viewRace extends JPanel implements ActionListener{

	private Image BackGroundIMG;
	private int fHeight = 700;
	private int fWidth = 2500;
	
	private Timer timer;
	private ArrayList<Image> imageArray = new ArrayList<Image>();
	private static ArrayList<Double> racersXvalue = new ArrayList<>();
	
	public viewRace(){
		//setWidth(arenaLength);
		timer = new Timer(20,this);
		timer.start();
		Dimension dim = getPreferredSize();
        dim.width = fWidth;
		dim.height = fHeight;
		setPreferredSize(dim);
	}
	
	public void loadImageBackground(String name) {
		try {     
			BackGroundIMG = ImageIO.read(new File("src/icons/"+name+".jpg"));
		     } catch (IOException ex) {}
		repaint();
	}
	
	public void loadRacerBackground(String name, String color) {
		try {
			imageArray.add(ImageIO.read(new File("src/icons/"+name+color+".png")));
			racersXvalue.add(0.0);
			} catch (IOException ex) {}
		repaint();
	}

	
	
    @Override
    public void paintComponent(Graphics G){	
        super.paintComponent(G);
        setSize(3000,fHeight);
        setLayout(null);
        G.drawImage(BackGroundIMG, 0, 0, 3000, this.fHeight, null);
		for (Image img : imageArray){
			if(this.fWidth <= 1000){
				G.drawImage(img, 875 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((875 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1500)
					G.drawImage(img, 1600, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1000 && this.fWidth <= 1100){
				G.drawImage(img, 825 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this); 
				if((825 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1550)
					G.drawImage(img, 1650, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1100 && this.fWidth <= 1200){
				G.drawImage(img, 775 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((775 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1600)
					G.drawImage(img, 1700, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1200 && this.fWidth <= 1300){
				G.drawImage(img, 725 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((725 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1650)
					G.drawImage(img, 1750, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1300 && this.fWidth <= 1400){
				G.drawImage(img, 675 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((675 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1700)
					G.drawImage(img, 1800, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1400 && this.fWidth <= 1500){
				G.drawImage(img, 625 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((625 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1750)
					G.drawImage(img, 1850, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1500 && this.fWidth <= 1600){
				G.drawImage(img, 575 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((575 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1800)
					G.drawImage(img, 1900, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1600){
				G.drawImage(img, 600 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((600 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1750)
					G.drawImage(img, 1850, imageArray.indexOf(img)*50, 50, 50, this);
				}/*
			if(this.fWidth > 1700 && this.fWidth <= 1800){
				G.drawImage(img, 475 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((475 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1900)
					G.drawImage(img, 2000, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1800 && this.fWidth <= 1900){
				G.drawImage(img, 600 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((600 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 1750)
					G.drawImage(img, 1850, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth > 1900 && this.fWidth < 2000){
				G.drawImage(img, 305 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this);
				if((305 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 2000)
					G.drawImage(img, 2000, imageArray.indexOf(img)*50, 50, 50, this);
			}
			if(this.fWidth >= 2000){
				G.drawImage(img, 450 + racersXvalue.get(imageArray.indexOf(img)).intValue(), imageArray.indexOf(img)*50, 50, 50, this); 
				if((450 + racersXvalue.get(imageArray.indexOf(img)).intValue()) > 2000)
					G.drawImage(img, 2000, imageArray.indexOf(img)*50, 50, 50, this);
			}*/
		}
		repaint();
    }
    
    public void setHeight(int H){
    	this.fHeight = H;
    }

    public void setWidth(int W){
    	this.fWidth = W;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static synchronized void setX(int serialNumber, double newX) {
		racersXvalue.set(serialNumber-1,newX);
	}
	
	public void resetPanel() {
		imageArray = null;
		imageArray = new ArrayList<Image>();
		BackGroundIMG = null;
		racersXvalue = null;
		racersXvalue = new ArrayList<>();
	}
    
    //public void setDimF(int D){
    	//this.dimf = D;
    //}
}
