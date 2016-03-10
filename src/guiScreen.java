/** Monte Carlo Simulator
 *  Author: Janshar Teja  **
** Date: 11/29/2015 **/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class guiScreen extends montecarlosimulator {

static double avg;
static int[] freq;
int it;
boolean isInt= true;
boolean graph=false;
static String range[]= new String[10];
	public guiScreen() {
		 montecarlosimulator sim = new montecarlosimulator();
		 do {
		 String iter = JOptionPane.showInputDialog("Number Of Iterations");
			 try {
				 it = Integer.valueOf(iter);
				 isInt=true;
			 }catch (Exception e){
				 JOptionPane.showMessageDialog(null,"That wasnt a integer.");
				 isInt=false;
			 }} while (isInt==false);
			
			if(it >= 50 && it <= 1200){
				sim.iteration = it;
				graph=true;
			}else{
				JOptionPane.showMessageDialog(null," Number too large or too small to graph.");
			}
		 try {
				sim.getData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			 sim.getFrequency();
			 sim.getProbility();
			 sim.getCumulativeProbilibty();
			 sim.getRandom();
			 avg = sim.findAverage();
			freq =  sim.intervalFrequency;
			sim.getRange();
			range= sim.range;
			 
		  setSize(1020, 800);//Give it a size
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Make it go away on close
		    JPanel panel = new JPanel();//Make a panel
		    add(panel);//Add it to your frame
		    setVisible(true);//Show the frame
		    
		 
	}

	public static void main(String[] args) {
		new guiScreen();
		// TODO Auto-generated method stub
					  
	}
	 public void paint(Graphics g){
	    
		 g.drawString("Iterations = "+Integer.toString(it), 410, 120);
		g.drawRect(50, 100, 800, 550); 
		Font f = new Font("Times New Roman", Font.BOLD, 20);
             g.setFont(f);
             g.drawString(Double.toString(avg), 520, 89);
             g.drawString("Average Forecast = ", 350, 90);
         f = new Font("Times New Roman", Font.PLAIN, 16);
    	 g.setFont(f);
       if (graph == true){
        int y= 650;
        int x=60;
        for(int i=0; i<10; i++){
        	if(range[i]!= null){	
              g.setColor(Color.black);
        g.drawRect(x, y-freq[i], 30,freq[i]);
        g.drawString(range[i], x-5, y+20);
        g.setColor(Color.red);
        g.drawString(Integer.toString(freq[i]).trim(), x+5, y-freq[i]-10);
        x=x+75;}
        
        	
        }
       }

     
    }

}
