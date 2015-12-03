package com.tao.videoplayer.tools;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import com.tao.videoplayer.tools.IntervalMedia;
import com.tao.videoplayer.main.PlayMain;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

public class IntervalMedia implements ActionListener{
	static JFileChooser chooser;
	static File file;
	static String[] arya ;
	static File dir2;
	
	public JFrame jf = new JFrame("Interval Setting");
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new IntervalMedia().createUI();
    }
    public void createUI(){
    	
    	
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(dir2);
        
		int log = chooser.showOpenDialog(null);
		if (log == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();	
			dir2=chooser.getCurrentDirectory();
			}
		
		JToolBar jtb = new JToolBar();
        String[] s = { "Total-time","Start-time", "Stop-time"};
        int size = s.length;
        JButton[] button = new JButton[size];
        for(int i = 0; i < size; i++){
            button[i] = new JButton(s[i]);
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            jtb.add(button[i]);
        }
        jf.add(jtb, "North");
        jf.setSize(350, 150);
        jf.setLocation(400, 200);
        jf.dispose();
        jf.setVisible(true);
        
        if (log == JFileChooser.CANCEL_OPTION) {
			jf.dispose();
			}
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	 String s = e.getActionCommand();
    	 String name1;
    	 String name2;
    	
        if(s.equals("Start-time")){
        	Locale.setDefault(new Locale("USA"));//Locale.setDefault(Locale.ENGLISH)//设置为英语
            name1 = JOptionPane.showInputDialog("Start-time Point:");
            if(name1 != null){
               // System.out.println("起始时间：" + name1);
                
                arya = name1.split("[:：,，-]");//[:：,，]
               
            }
        }
        else if(s.equals("Stop-time")){
        	Locale.setDefault(new Locale("USA"));
        	 name2 = JOptionPane.showInputDialog("Stop-time Point:");
             if(name2 != null){
                // System.out.println("停止时间：" + name2);
                 
                 int starthour=Integer.parseInt(arya[0])*3600;
                 int startminutes=Integer.parseInt(arya[1])*60;
                 int startsecond=Integer.parseInt(arya[2]);
                 
                 String time1=String.valueOf(starthour+startminutes+startsecond);
                 
                 String[] aryt = name2.split("[:：,，-]");
                
                 int stophour=Integer.parseInt(aryt[0])*60;
                 int stopminutes=Integer.parseInt(aryt[1])*60;
                 int stopsecond=Integer.parseInt(aryt[2]);
                 
                 String time2=String.valueOf(stophour+stopminutes+stopsecond);
                
                 jf.dispose();
               PlayMain.f().setTitle(file.getName()+"-"+"Omnipotent Player");
	    	   PlayMain.f().getMediaPlayer().playMedia(file.getAbsolutePath().trim(),":start-time="+time1, ":stop-time="+time2);	
	    	  
             }
         }else if(s.equals("Total-time")){
        	 Encoder encoder = new Encoder();
        	 Locale.setDefault(new Locale("USA"));
        	 try {
				JOptionPane.showMessageDialog(null,"Total time:\n"+PlayMain.formatTime(encoder.getInfo(file).getDuration()));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InputFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EncoderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         }
      }

}
