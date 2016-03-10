/** Monte Carlo Simulator
 *  Author: Janshar Teja  **
** Date: 11/29/2015 **/
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
public class montecarlosimulator extends JFrame {
 double dataSet[];
 int max;
 int min;
 int binRange;
 int frequency[]= new int[10];
 double probablity[] = new double[10];
 int total;
 double cumulativeProbilibty[] = new double[10];
 int intervalFrequency[] = new int[10];
 int classMark[] = new int[10];
 static int iteration=1000;
private static JLabel  avg;
 JPanel panel;
 String range[] = new String[10];
public montecarlosimulator(){
	super("Monte Carlo Simmulator");
}
/*@
 * Gets the data from file and puts it into double array called dataSet
 * 
 */
	protected  void getData() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		total = lines;
		dataSet=  new double[total];
		FileReader fr = new FileReader("data.txt");
		BufferedReader textReader = new BufferedReader(fr);
		for(int i=0; i<lines; i++){
			dataSet[i]= Double.parseDouble(textReader.readLine());
			
		}
	
			Arrays.sort(dataSet);
			
			max = (int)dataSet[dataSet.length-1];
			min	= (int)dataSet[0];
			//System.out.println(dataSet[0]);
			binRange = (int) Math.ceil((double)(max-min)/10);
			
	}
	protected  void getFrequency(){

		int sum=0;
		int q=0;
		int count=0;
		for(int i=min; i < max;){
		
			for(int j=0; j<dataSet.length; j++){	
			
			if(dataSet[j]>= i &&  dataSet[j]<=i+binRange){
				frequency[count]++;
				//System.out.println(" I = "+i+" I End ="+ i+binRange);
				
			}
		}
			classMark[q]=i+(binRange/2);
			count++;
			i=i+binRange;
			q++;
		}
		
	}
	protected  void getProbility(){
		DecimalFormat df = new DecimalFormat("#.##");    
		for(int i=0; i<10; i++){
			probablity[i]= (double)frequency[i]/total;
		
		}
		
	}
	protected  void getCumulativeProbilibty(){
	double	previousProbablity=probablity[0];
	cumulativeProbilibty[0]=previousProbablity;
	DecimalFormat df = new DecimalFormat("#.##");    
		for(int i=1; i<10; i++){
			cumulativeProbilibty[i]=probablity[i]+previousProbablity;
			previousProbablity=cumulativeProbilibty[i];
			
		}
	}
	protected  void getRandom(){
		Random rand = new Random();
		for(int i =0; i < this.iteration; i++){
			double randomNumber = rand.nextDouble();
			
			if(randomNumber > 0 && randomNumber < (cumulativeProbilibty[0])){
				intervalFrequency[0]++;
			}else {
					if(randomNumber > cumulativeProbilibty[0] && randomNumber < cumulativeProbilibty[1] ){
						intervalFrequency[1]++;
					}else {
							if(randomNumber > cumulativeProbilibty[1] && randomNumber < cumulativeProbilibty[2] ){
									intervalFrequency[2]++;
								}else {
										if(randomNumber > cumulativeProbilibty[2] && randomNumber < cumulativeProbilibty[3] ){
											intervalFrequency[3]++;
										}else {
												if(randomNumber > cumulativeProbilibty[3] && randomNumber < cumulativeProbilibty[4] ){
													intervalFrequency[4]++;		
												}else { 
													if(randomNumber > cumulativeProbilibty[4] && randomNumber < cumulativeProbilibty[5] ){
														intervalFrequency[5]++;
													}else {
														if(randomNumber > cumulativeProbilibty[5] && randomNumber < cumulativeProbilibty[6] ){
															intervalFrequency[6]++;
														}else {
															if(randomNumber > cumulativeProbilibty[6] && randomNumber < cumulativeProbilibty[7] ){
																	intervalFrequency[7]++;
																}else {
																	if(randomNumber > cumulativeProbilibty[7] && randomNumber < cumulativeProbilibty[8] ){
																		intervalFrequency[8]++;
																		}else {
																			if(randomNumber >cumulativeProbilibty[8] ){
																				intervalFrequency[9]++;
																			}
																		}}}}}}}}}
				
			}
			
		
		
	}
	protected  double findAverage(){
		int sum=min;
		int temp=0;
		int start= min+(binRange/2);
	for(int i =0 ;i<10; i++){
		sum += classMark[i]*intervalFrequency[i];
	}
	return((double)sum/iteration);
	

	
	}
	
	 protected  void getRange(){
		 for(int i=0; i<10; i++){
			 if((classMark[i]-(binRange/2)) >0){
			 range[i]=Integer.toString((int)(classMark[i]-(binRange/2)));
			 range[i]+=" - ";
			range[i]+= Integer.toString(classMark[i]+(binRange/2));
			 }
			
		 }
	 }
	public static void main(String[] args) throws FileNotFoundException {
		
		
	
}
}
