package com.tao.videoplayer.tools;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;

import com.tao.videoplayer.main.PlayMain;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class NetStream {
	
	static JFrame jFrame;
	private static JTextField textField;
	
	public static void main(String[] args) {
		
		jFrame=new JFrame("Network");
		jFrame.setSize(400, 300);
		jFrame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Network Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jFrame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPleaseEnterA = new JLabel("Please enter a network URL:");
		panel.add(lblPleaseEnterA, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("  http://www.example.com/stream.avi\n"
				+ "  rtp://@:1234\n"
				+ "  mms://mms.example.com/stream.asx\n"
				+ "  rtsp://server.example.org:8080/test.sdp\n"
				+ "  http://www.youtube.com/watch?v=gg64x");
		textArea.setFont(new Font("黑体",Font.PLAIN,12));
		textArea.setEditable(false);
		panel.add(textArea, BorderLayout.SOUTH);
		
		textField = new JTextField();
		JPopupMenu jPopupMenu =new JPopupMenu();//右键菜单
        
	    JMenuItem jmi1=new JMenuItem("Copy(C)");
	    JMenuItem jmi2=new JMenuItem("Paste(P)");
	    JMenuItem jmi3=new JMenuItem("Cut(T)");
	    JMenuItem jmi4=new JMenuItem("Select All(A)");
	   
	    textField.setComponentPopupMenu(jPopupMenu);
	    
	    jPopupMenu.add(jmi1);
	    jPopupMenu.add(jmi2);
	    jPopupMenu.add(jmi3);
	    jPopupMenu.add(jmi4);
	   
	    jmi1.setMnemonic(KeyEvent.VK_C);
	    jmi2.setMnemonic(KeyEvent.VK_P);
	    jmi3.setMnemonic(KeyEvent.VK_T);
	    jmi4.setMnemonic(KeyEvent.VK_A);
	    
	    jmi1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
	    jmi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
	    jmi3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
	    jmi4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
	    
	    jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textField.copy();
			}
		});
	    
	    jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textField.paste();
			}
		});
	    
	    jmi3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textField.cut();
			}
		});
	    
	    jmi4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				textField.selectAll();
			}
		});
	    
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		
		JPanel panel_1 = new JPanel();
		jFrame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//System.out.println(textField.getText());
				
				PlayMain.p().setPlaySubItems(true);
				String url = textField.getText();
				PlayMain.p().playMedia(url);
				if(url.contains("zb.allook.cn/live")){
					String menutit = url.substring(url.indexOf("live/")+5,url.indexOf("/playlist"));
					PlayMain.f().setTitle(menutit.toUpperCase()+"-Omnipotent Player");
				}
				else{
					PlayMain.f().setTitle("Omnipotent Player");
				}
				
				jFrame.dispose();
			
			}
		});
		panel_2.add(btnPlay);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		panel_2.add(btnNewButton);
		jFrame.setVisible(true);

	}

}
