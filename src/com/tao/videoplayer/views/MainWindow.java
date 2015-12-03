package com.tao.videoplayer.views;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.binding.internal.libvlc_marquee_position_e;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.tao.videoplayer.tools.IntervalMedia;
import com.tao.videoplayer.main.PlayMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.FlowLayout;
import java.awt.Robot;

import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;

import com.tao.videoplayer.tools.NodePad;
import com.tao.videoplayer.tools.ScreenShot;
import com.tao.videoplayer.tools.NetStream;

import javax.swing.JLabel;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JRadioButtonMenuItem;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static EmbeddedMediaPlayerComponent playerComponent;
	private JPanel panel;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	private JPanel panelchild;
	private JPanel controlPanel;
	private JProgressBar progress;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenVideo;
	private JMenuItem mntmOpenSubtitle;
	private JMenuItem mntmExit;
	static JSlider slider;
	private JButton btnFullscreen;
	private JMenu mnTools;
	private JMenuItem mntmSreenshot;
	private JMenu mnHelp;
	private JMenuItem mntmNode;
	private JMenuItem mntmAbout;
	public static JLabel label;
	public static JLabel label1;
	private JMenu mnSetting;
	private JMenu mnSpeed;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem mntmNormalSpeed;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenu mnVideoZoom;
	private JButton btnNewButton;
	private JMenu mnVideoCrop;
	private JRadioButtonMenuItem rdbtnmntmDefault;
	private JRadioButtonMenuItem radioButtonMenuItem;
	private JRadioButtonMenuItem radioButtonMenuItem_1;
	private JRadioButtonMenuItem radioButtonMenuItem_2;
	private JRadioButtonMenuItem radioButtonMenuItem_3;
	private JRadioButtonMenuItem radioButtonMenuItem_4;
	private JRadioButtonMenuItem radioButtonMenuItem_5;
	private JRadioButtonMenuItem radioButtonMenuItem_6;
	private JRadioButtonMenuItem radioButtonMenuItem_7;
	private JRadioButtonMenuItem radioButtonMenuItem_8;
	private JRadioButtonMenuItem radioButtonMenuItem_9;
	private JRadioButtonMenuItem rdbtnmntmQuarter;
	private JRadioButtonMenuItem rdbtnmntmHalf;
	private JRadioButtonMenuItem rdbtnmntmOriginal;
	private JRadioButtonMenuItem rdbtnmntmDouble;
	private JRadioButtonMenuItem rdbtnmntmDefault_1;
    static EmbeddedMediaPlayer mediaPlayer;
	private JMenu mnVideoAspectRatio;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem;
	private JRadioButtonMenuItem rdbtnmntmNewRadioItem_1;
	private JRadioButtonMenuItem radioButtonMenuItem_10;
	private JRadioButtonMenuItem radioButtonMenuItem_11;
	private JRadioButtonMenuItem radioButtonMenuItem_12;
	private JRadioButtonMenuItem radioButtonMenuItem_13;
	private JRadioButtonMenuItem radioButtonMenuItem_14;
	private JRadioButtonMenuItem radioButtonMenuItem_15;
	private JRadioButtonMenuItem radioButtonMenuItem_16;
	private JMenuItem mntmOpenNetworkStream;
	private JMenu mnOpenRecentMedia;
	private JMenuItem mntmClear;
	static List<String> list ;
	private JMenuItem mntmPlayIntervalMedia;
	private JMenu mnPlayback;
	private JMenuItem mntmJumpForward;
	private JMenuItem mntmJumpBackward;
	private JMenuItem mntmPlay;
	private JMenuItem mntmStop;
	public static JButton button;
	public JButton button_1;
	static Timer timer;
	public static JSlider sli(){
		  return slider;
	}
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\Omnipotent Player.png"));
		setTitle("Omnipotent Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 800, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Media");
		mnFile.setMnemonic(KeyEvent.VK_M);
		menuBar.add(mnFile);
		
		mntmOpenVideo = new JMenuItem("Open File...");
		mntmOpenVideo.setIcon(new ImageIcon("images/file.png"));
		mnFile.add(mntmOpenVideo);
		//mnFile.addSeparator();//加分割线
		
		mntmOpenSubtitle = new JMenuItem("Open Subtitle...");
		mnFile.add(mntmOpenSubtitle);
		
		mntmOpenNetworkStream = new JMenuItem("Open Network Stream...");
		mntmOpenNetworkStream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NetStream.main(null);
			}
		});
		mnFile.add(mntmOpenNetworkStream);
		
		mnOpenRecentMedia = new JMenu("Open Recent Media");
		mnFile.add(mnOpenRecentMedia);
		
		mntmPlayIntervalMedia = new JMenuItem("Play Interval Media...");
		mntmPlayIntervalMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntervalMedia.main(null);
			}
		});
		mnFile.add(mntmPlayIntervalMedia);
		
		//mnFile.addSeparator();//加分割线
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("images/exit.png"));
		mnFile.add(mntmExit);
		
		mnPlayback = new JMenu("Playback");
		menuBar.add(mnPlayback);
		
		mnSpeed = new JMenu("Speed");
		mnPlayback.add(mnSpeed);
		
		menuItem = new JMenuItem("×2");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setRate(2);
			}
		});
		
		menuItem_1 = new JMenuItem("×4");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setRate(4);
			}
		});
		mnSpeed.add(menuItem_1);
		mnSpeed.add(menuItem);
		
		mntmNormalSpeed = new JMenuItem("Normal Speed");
		mntmNormalSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setRate(1);
			}
		});
		mnSpeed.add(mntmNormalSpeed);
		
		menuItem_2 = new JMenuItem("/2");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setRate(0.5F);
			}
		});
		mnSpeed.add(menuItem_2);
		
		menuItem_3 = new JMenuItem("/4");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setRate(0.25F);
			}
		});
		mnSpeed.add(menuItem_3);
		
		mnPlayback.addSeparator();//分割线
		mntmJumpForward = new JMenuItem("Jump Forward");
		mntmJumpForward.setIcon(new ImageIcon("images/skip-forward.png"));
		mntmJumpForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayMain.p().skip(10*1000);
			}
		});
		mnPlayback.add(mntmJumpForward);
		
		mntmJumpBackward = new JMenuItem("Jump Backward");
		mntmJumpBackward.setIcon(new ImageIcon("images/skip-back.png"));
		mntmJumpBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PlayMain.p().setTime(PlayMain.getTime()-10*1000);
			}
		});
		mnPlayback.add(mntmJumpBackward);
		
		mnPlayback.addSeparator();//分割线
		mntmPlay = new JMenuItem("Play");
		mntmPlay.setIcon(new ImageIcon("images/play.png"));
		mntmPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayMain.play();
			}
		});
		mnPlayback.add(mntmPlay);
		
		mntmStop = new JMenuItem("Stop");
		mntmStop.setIcon(new ImageIcon("images/stop.png"));
		mntmStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayMain.stop();
			}
		});
		mnPlayback.add(mntmStop);
		
		mnSetting = new JMenu("Setting");
		mnSetting.setMnemonic(KeyEvent.VK_S);
		menuBar.add(mnSetting);
		
		mnVideoZoom = new JMenu("Video Zoom");
		mnSetting.add(mnVideoZoom);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		rdbtnmntmQuarter = new JRadioButtonMenuItem("1:4 Quarter");
		rdbtnmntmQuarter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state=e.getStateChange();
				if(state==ItemEvent.SELECTED){
				getMediaPlayer().setScale(0.25F);
				}else{
					getMediaPlayer().setScale(0.0F);
				}
			}
		});
		mnVideoZoom.add(rdbtnmntmQuarter);
		buttonGroup.add(rdbtnmntmQuarter);
		
		rdbtnmntmHalf = new JRadioButtonMenuItem("1:2 Half");
		rdbtnmntmHalf.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state=e.getStateChange();
				if(state==ItemEvent.SELECTED){
					getMediaPlayer().setScale(0.5F);
				}else{
					getMediaPlayer().setScale(0.0F);
				}
				
			}
		});
		mnVideoZoom.add(rdbtnmntmHalf);
		buttonGroup.add(rdbtnmntmHalf);
		
		rdbtnmntmOriginal = new JRadioButtonMenuItem("1:1 Original");
		rdbtnmntmOriginal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state=e.getStateChange();
				if(state==ItemEvent.SELECTED){
					getMediaPlayer().setScale(1);
				}else{
					getMediaPlayer().setScale(0.0F);
				}
				
			}
		});
		mnVideoZoom.add(rdbtnmntmOriginal);
		buttonGroup.add(rdbtnmntmOriginal);
		
		rdbtnmntmDouble = new JRadioButtonMenuItem("2:1 Double");
		rdbtnmntmDouble.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state=e.getStateChange();
				if(state==ItemEvent.SELECTED){
					getMediaPlayer().setScale(2);
				}else{
					getMediaPlayer().setScale(0.0F);
				}
				
			}
		});
		mnVideoZoom.add(rdbtnmntmDouble);
		buttonGroup.add(rdbtnmntmDouble);
		
		rdbtnmntmDefault_1 = new JRadioButtonMenuItem("Default");
		rdbtnmntmDefault_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				getMediaPlayer().setScale(0.0F);
			}
		});
		mnVideoZoom.add(rdbtnmntmDefault_1);
		buttonGroup.add(rdbtnmntmDefault_1);
		
		mnVideoAspectRatio = new JMenu("Video Aspect Ratio");
		mnSetting.add(mnVideoAspectRatio);
		
		ButtonGroup buttonGroup2=new ButtonGroup();
		rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Default");
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio(null);
			}
		});
		mnVideoAspectRatio.add(rdbtnmntmNewRadioItem);
		buttonGroup2.add(rdbtnmntmNewRadioItem);
		
		rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("16:9");
		rdbtnmntmNewRadioItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("16:9");
			}
		});
		mnVideoAspectRatio.add(rdbtnmntmNewRadioItem_1);
		buttonGroup2.add(rdbtnmntmNewRadioItem_1);
		
		radioButtonMenuItem_10 = new JRadioButtonMenuItem("4:3");
		radioButtonMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("4:3");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_10);
		buttonGroup2.add(radioButtonMenuItem_10);
		
		radioButtonMenuItem_11 = new JRadioButtonMenuItem("1:1");
		radioButtonMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("1:1");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_11);
		buttonGroup2.add(radioButtonMenuItem_11);
		
		radioButtonMenuItem_12 = new JRadioButtonMenuItem("16:10");
		radioButtonMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("16:10");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_12);
		buttonGroup2.add(radioButtonMenuItem_12);
		
		radioButtonMenuItem_13 = new JRadioButtonMenuItem("2.21:1");
		radioButtonMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("221:100");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_13);
		buttonGroup2.add(radioButtonMenuItem_13);
		
		radioButtonMenuItem_14 = new JRadioButtonMenuItem("2.35:1");
		radioButtonMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("235:100");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_14);
		buttonGroup2.add(radioButtonMenuItem_14);
		
		radioButtonMenuItem_15 = new JRadioButtonMenuItem("2.39:1");
		radioButtonMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("239:100");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_15);
		buttonGroup2.add(radioButtonMenuItem_15);
		
		radioButtonMenuItem_16 = new JRadioButtonMenuItem("5:4");
		radioButtonMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setAspectRatio("5:4");
			}
		});
		mnVideoAspectRatio.add(radioButtonMenuItem_16);
		buttonGroup2.add(radioButtonMenuItem_16);
		
		mnVideoCrop = new JMenu("Video Crop");
		mnSetting.add(mnVideoCrop);
		ButtonGroup bGroup=new ButtonGroup();
		rdbtnmntmDefault = new JRadioButtonMenuItem("Default");
		rdbtnmntmDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry(null);
			}
		});
		mnVideoCrop.add(rdbtnmntmDefault);
		bGroup.add(rdbtnmntmDefault);
		
		radioButtonMenuItem = new JRadioButtonMenuItem("16:10");
		radioButtonMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("16:10");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem);
		bGroup.add(radioButtonMenuItem);
		
		radioButtonMenuItem_1 = new JRadioButtonMenuItem("16:9");
		radioButtonMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("16:9");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_1);
		bGroup.add(radioButtonMenuItem_1);
		
		radioButtonMenuItem_2 = new JRadioButtonMenuItem("4:3");
		radioButtonMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("4:3");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_2);
		bGroup.add(radioButtonMenuItem_2);
		
		radioButtonMenuItem_3 = new JRadioButtonMenuItem("1.85:1");
		radioButtonMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("185:100");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_3);
		bGroup.add(radioButtonMenuItem_3);
		
		radioButtonMenuItem_4 = new JRadioButtonMenuItem("2.21:1");
		radioButtonMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("221:100");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_4);
		bGroup.add(radioButtonMenuItem_4);
		
		radioButtonMenuItem_5 = new JRadioButtonMenuItem("2.35:1");
		radioButtonMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("235:100");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_5);
		bGroup.add(radioButtonMenuItem_5);
		
		radioButtonMenuItem_6 = new JRadioButtonMenuItem("2.39:1");
		radioButtonMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("239:100");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_6);
		bGroup.add(radioButtonMenuItem_6);
		
		radioButtonMenuItem_7 = new JRadioButtonMenuItem("5:3");
		radioButtonMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("5:3");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_7);
		bGroup.add(radioButtonMenuItem_7);
		
		radioButtonMenuItem_8 = new JRadioButtonMenuItem("5:4");
		radioButtonMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("5:4");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_8);
		bGroup.add(radioButtonMenuItem_8);
		
		radioButtonMenuItem_9 = new JRadioButtonMenuItem("1:1");
		radioButtonMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMediaPlayer().setCropGeometry("1:1");
			}
		});
		mnVideoCrop.add(radioButtonMenuItem_9);
		bGroup.add(radioButtonMenuItem_9);
		
		mnTools = new JMenu("Tools");
		mnTools.setMnemonic(KeyEvent.VK_T);
		menuBar.add(mnTools);
		
		mntmSreenshot = new JMenuItem("SreenShot");
		mntmSreenshot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenShot.main(null);
			}
		});
		mnTools.add(mntmSreenshot);
		
		mntmNode = new JMenuItem("Node");
		mntmNode.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				NodePad.main(null);
			}
		});
		mnTools.add(mntmNode);
		
		mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.setIcon(new ImageIcon("images/info.png"));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Omnipotent Player v1.0.0.0\n\0\0\0Copyright© 2015~");  
			}
		});
		mnHelp.add(mntmAbout);
		
		mntmOpenVideo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					PlayMain.openVideo();
				if(PlayMain.chooser.getDialogTitle()!=" "){
					String path=PlayMain.chooser.getSelectedFile().getAbsolutePath();
					list= Arrays.asList(path);
				}else {
					list= Arrays.asList("");
				}
				
				if (!list.isEmpty()){
					int i;
				for(String filepath:list){
					
					//System.out.println(filepath);
					JMenuItem [] mntm = {new JMenuItem()};
					for (i=0;i<mntm.length;i++){
					mnOpenRecentMedia.add(mntm[i]);
					mntm[i].setText(" "+filepath);
					mntm[i].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							PlayMain.playMedi(filepath);
							
						   }
					   });
					
						
					}
					 mnOpenRecentMedia.add(mntmClear);
					
					}
				}
			}
		});
		
		mntmClear = new JMenuItem("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//list.clear();
				mnOpenRecentMedia.removeAll();
			}
		});
		
		mntmOpenSubtitle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayMain.openSubtitle();
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayMain.exit();
			}
		});
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel videopane = new JPanel();
		contentPane.add(videopane, BorderLayout.CENTER);
		videopane.setLayout(new BorderLayout(0, 0));
		
	
		playerComponent = new EmbeddedMediaPlayerComponent();
		
		playerComponent.getMediaPlayer().setEnableKeyInputHandling(false);
        playerComponent.getMediaPlayer().setEnableMouseInputHandling(false);
        
       // Canvas videoSurface =playerComponent.getVideoSurface();
		 
        playerComponent.getVideoSurface().addMouseListener(new MouseAdapter() {
        	javax.swing.Timer mouseTimer;
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1&& e.getButton() == MouseEvent.BUTTON1) {
					
					    mouseTimer = new javax.swing.Timer(350, new ActionListener() {
					   public void actionPerformed(ActionEvent evt) {
						   mouseTimer.stop();
						   PlayMain.pause();
						 //  System.out.println("Single");
					       
					      }
					   });
					   mouseTimer.restart();
					   } else if (e.getClickCount() == 2 && mouseTimer.isRunning()&& e.getButton() == MouseEvent.BUTTON1) {
					  
						   mouseTimer.stop(); 
					      // System.out.println("Double");
					   
					   if(btnFullscreen.isSelected()){
							mediaPlayer.setFullScreen(false);
							contentPane.setBackground(new Color(240, 240, 240));
							menuBar.setVisible(true);
							panel.setVisible(true);	
						    playerComponent.setCursorEnabled(true);
							btnFullscreen.doClick(1);
							btnFullscreen.setFocusable(false);
							}
							else{
								btnFullscreen.doClick(1);
								menuBar.setVisible(true);
								panel.setVisible(true);	
								try {
									Robot robot2=new Robot();
									robot2.mousePress(InputEvent.BUTTON2_MASK);
									robot2.delay(100);
									robot2.mouseRelease(InputEvent.BUTTON2_MASK);
						
								} catch (AWTException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
					   }
				
				
			  }
			
		});
        
        playerComponent.getVideoSurface().requestFocusInWindow();
		
		
//		JPopupMenu jPopupMenu=new JPopupMenu();
//		JMenuItem menuItem1=new JMenuItem();
//		jPopupMenu.add(menuItem1);
//		
//		addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				String outStr="";
//				if(e.getButton()==e.BUTTON1){
//					outStr="Left Key";
//				}else if(e.getButton()==e.BUTTON3){
//					outStr="Right Key";
//				}else{
//					outStr="Middle Key";
//				}
//				if(e.getClickCount()==2){
//					outStr=outStr+" double clicks";
//				}else{
//					outStr=outStr+" clicked";
//				}
//
//				System.out.println(outStr);
//				
//			}
//		});
		
//		addMouseListener(new MouseAdapter(){  //窗口的鼠标事件处理
//	        public void mousePressed( MouseEvent event ) {  //点击鼠标
//	           triggerEvent(event);  //调用triggerEvent方法处理事件
//	        } 
//
//	        public void mouseReleased( MouseEvent event ) { //释放鼠标
//	           triggerEvent(event); 
//	        } 
//
//	        private void triggerEvent(MouseEvent event) { //处理事件
//	           if (event.isPopupTrigger()) //如果是弹出菜单事件(根据平台不同可能不同)
//	              jPopupMenu.show(event.getComponent(),event.getX(),event.getY());  //显示菜单
//	        }
//	    }); 
		
		videopane.add(playerComponent);
		
		playerComponent.getVideoSurface().requestFocusInWindow();
		playerComponent.getVideoSurface().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
//				char c=e.getKeyChar();
//				if(c=='0'){
//					System.out.println("fhgdf");
//				}else{
//					System.out.println("fdg");
//		}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();
		        switch(keyCode){
		          
		        case KeyEvent.VK_UP ://音量+
		        	int value1=MainWindow.sli().getValue();
		        	MainWindow.sli().setValue(value1+1);
		        	PlayMain.setVol(MainWindow.sli().getValue());
		        	//System.out.println(MainWindow.sli().getValue());		
		        	break;                
		        case KeyEvent.VK_DOWN ://音量-
		        	int value2=MainWindow.sli().getValue();
		        	MainWindow.sli().setValue(value2-1);
		        	PlayMain.setVol(MainWindow.sli().getValue());
		        	//System.out.println(MainWindow.sli().getValue());
		            break;
		        case KeyEvent.VK_LEFT :
		            //System.out.println("按下了左方向键");
		            PlayMain.f().getMediaPlayer().setTime(PlayMain.getTime()-5*1000);//按下了左方向键
		            break;
		        case KeyEvent.VK_RIGHT:
		        	//System.out.println("按下了右方向键");
		        	PlayMain.f().getMediaPlayer().setTime(PlayMain.getTime()+5*1000);//按下了右方向键
		            break;
		        case KeyEvent.VK_SPACE: 
		        	
		        	PlayMain.f().getMediaPlayer().pause();
		        	
		        	break;
		       }
			}
		});
		
		
		
		
		
		//playerComponent.setCursorEnabled(false);//隐藏鼠标
		//实现拖拽播放
		new DropTarget(playerComponent,DnDConstants.ACTION_COPY_OR_MOVE,new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {//重写适配器的drop方法
				
				try{
					{
						if(dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){//如果拖入的文件格式受支持
							dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接受拖拽来的数据
							@SuppressWarnings("unchecked")
							List<File> list=(List<File>)(dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
//							String temp="";				
							for(File file:list){
//								String ename=PlayMain.getExtensionName(file.getAbsolutePath());
//								 if(ename.equals("txt")||ename.equals("lnk")){
//									temp+=file.getAbsolutePath()+";\n";
//									JOptionPane.showMessageDialog(null, temp);
//								}
//								 System.out.println(ename);
								setTitle(file.getName()+"-"+"Omnipotent Player");
								PlayMain.playMedi(file.getAbsolutePath().trim());
							}
							dtde.dropComplete(true);//指示拖拽操作已完成
							
						}else{
							dtde.rejectDrop();//否则拒绝拖拽来的数据
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		
		panel = new JPanel();
		videopane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		controlPanel = new JPanel();
		panel.add(controlPanel);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		button = new JButton(new ImageIcon("images/play_btn.png"));
		button.setFocusPainted(false);//去掉按钮虚线框
		button.setFocusable(false);
		button.setPreferredSize(new Dimension(32,32));//用流式布局，就要设置preferredSize
	
		button.addActionListener(new ActionListener() {
			boolean fla1=true;
			public void actionPerformed(ActionEvent e) {
				if(fla1==true){
					button.setIcon(new ImageIcon("images/pause_btn.png"));
					
					if (!PlayMain.p().isPlaying()) {
			            PlayMain.play();
			           
			            fla1=false;
			        }
			        else {
			           PlayMain.pause();//继续播放
			          
			           fla1=false;
			        }
					
					 
				}else if (fla1==false) {
					
					button.setIcon(new ImageIcon("images/play_btn.png"));
					
						PlayMain.pause();//暂停
						
						
					fla1=true;
					
				}
			}
		});
		
		controlPanel.add(button);
		
		button_1 = new JButton(new ImageIcon("images/stop_btn.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setIcon(new ImageIcon("images/play_btn.png"));
				PlayMain.stop();
				
			}
		});
		button_1.setFocusPainted(false);//去掉按钮虚线框
		button_1.setFocusable(false);
		button_1.setPreferredSize(new Dimension(20,20));//用流式布局，就要设置preferredSize
		controlPanel.add(button_1);
		
		
		btnStop = new JButton("Stop");
		btnStop.setFocusPainted(false);//去掉按钮虚线框
		btnStop.setFocusable(false);
		controlPanel.add(btnStop);
		
		btnPlay = new JButton("Play");
		btnPlay.setFocusPainted(false);//去掉按钮虚线框
		btnPlay.setFocusable(false);
		controlPanel.add(btnPlay);
		
		btnPause = new JButton("Pause");
		btnPause.setFocusPainted(false);//去掉按钮虚线框
		btnPause.setFocusable(false);
		controlPanel.add(btnPause);
		
		slider = new JSlider();
		slider.setFocusable(false);//去掉虚线框
		slider.setFocusable(false);
		slider.setValue(100);
		slider.setMaximum(150);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				PlayMain.setVol(slider.getValue());
			}
		});
		
		slider.addMouseMotionListener(new MouseAdapter(){  
		       public void mouseMoved(MouseEvent e) {  
		    	   slider.setToolTipText("Volume:"+slider.getValue());
		           }  
		       }  
		   );	
		
//		btnFullscreen = new JButton("FullScreen");
//		controlPanel.add(btnFullscreen);
//		btnFullscreen.addActionListener(new ActionListener() {
//			
//			boolean flag=true;
//			
//			@SuppressWarnings("static-access")
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JFrame frame=new PlayMain().f();		
//				
//				if(flag==true){
//										
//					frame.dispose();
//					frame.setUndecorated(true);//隐藏标题栏
//					frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);
//					frame.setVisible(true);
//					
//					btnFullscreen.setText("NormalMose");
//					flag=false;	
////					System.out.println(PlayMain.getTime());
//					menuBar.setVisible(false);
//					long time1=PlayMain.getTime();
//					
//					PlayMain.stop();
//					PlayMain.play();
//					PlayMain.setTime(time1);
//					
//					
////					btnFullscreen.setText("NormalMose");
////					flag=false;	
////					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				    
//				}
//				else if(flag==false){
//					
//					frame.dispose();
//					frame.setUndecorated(false);//显示标题栏
//					frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
//					frame.setVisible(true);
//					btnFullscreen.setText("FullSreen");
//					flag=true;	
////					System.out.println(PlayMain.getTime());
//					menuBar.setVisible(true);
//					long time2=PlayMain.getTime();
//					
//					PlayMain.stop();
//					PlayMain.play();
//					PlayMain.setTime(time2);
//					
//							
////					btnFullscreen.setText("FullSreen");
////					flag=true;	
////					frame.setExtendedState(JFrame.NORMAL);
//				}
//				
//			}
//		});
		
		btnFullscreen = new JButton(new ImageIcon("images/fullscreen.png"));
		btnFullscreen.setFocusPainted(false);//去掉按钮虚线框
		btnFullscreen.setFocusable(false);
		controlPanel.add(btnFullscreen);
		btnFullscreen.setPreferredSize(new Dimension(24,24));//用流式布局，就要设置preferredSize
		btnFullscreen.addActionListener(new ActionListener() {
			
			boolean flag=true;
			MouseMotionListener mmove;
			MouseListener mclickleave;
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new PlayMain().f();		
				
				if(flag==true){
									
					MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
					 mediaPlayer = 
							mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(frame));
					
					mediaPlayer.setFullScreen(true);//mediaPlayer.toggleFullScreen();
					
					
					btnFullscreen.setIcon(new ImageIcon("images/fullscreen_exit_alt.png"));
					
					contentPane.setBackground(Color.BLACK);
					
					PlayMain.p().setMarqueeText("FullSreen Mode");
					PlayMain.p().setMarqueeSize(26);
					PlayMain.p().setMarqueeColour(Color.WHITE);
					PlayMain.p().setMarqueeTimeout(1900);
					PlayMain.p().setMarqueePosition(libvlc_marquee_position_e.centre);
					PlayMain.p().setMarqueeOpacity(0.3f);
					PlayMain.p().enableMarquee(true);
					
					flag=false;	
					
//					 String url = "image/cursor.jpg"; //储存鼠标图片的位置
//					 Toolkit tk = Toolkit.getDefaultToolkit(); 
//					 Image image = new ImageIcon(url).getImage(); 
//					 Cursor cursor = tk.createCustomCursor(image, new Point(10, 10), "hide"); 
//					 playerComponent.setCursor(cursor);
					
					
			       
					
					mmove= new MouseMotionAdapter() {
						
						  public void mouseMoved(MouseEvent e) {
							playerComponent.setCursorEnabled(true);
							 panel.setVisible(true);
							
							//timer.purge();//从这个计时器的任务队列中移除所有已取消的任务。
							panel.setOpaque(false);
							System.gc();
							videopane.revalidate();
						    videopane.repaint();
						    //panel.updateUI();
							//videopane.updateUI();
							
							}
						};
						
					mclickleave=new MouseAdapter() {
							
//					public void mouseMoved(MouseEvent e) {
//							panel.setVisible(true);
//						    panel.revalidate();
//						    panel.repaint();	 
//					  }
					
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==1 && e.getButton() == MouseEvent.BUTTON2){
						//playerComponent.setCursorEnabled(false);
						menuBar.setVisible(false);
						panel.setVisible(false);
						System.gc();
					    videopane.revalidate();
					    videopane.repaint();
						//panel.updateUI();
						//videopane.updateUI();
						 
					   }	
						
				  }
									
				 };	
				 
				 
				  //contentPane.addMouseMotionListener(mmove);
				  //contentPane.addMouseListener(mclickleave);
				 playerComponent.addMouseListener(new MouseAdapter() {
					 public void mouseClicked(MouseEvent e) {
						 if(e.getClickCount()==1&&e.getButton()==MouseEvent.BUTTON1){
							 PlayMain.pause();
						 }
					 }
				});
				 
				 playerComponent.getVideoSurface().addMouseMotionListener(mmove);
				 playerComponent.getVideoSurface().addMouseListener(mclickleave);
				 playerComponent.getVideoSurface().requestFocusInWindow();
				
				 menuBar.setVisible(false);
				 panel.setVisible(false);
				
					timer=new Timer(true);//true说明这个timer以daemon方式运行，优先级低，程序结束timer也自动结束
					
					TimerTask task=new TimerTask() {
						
						@Override
						public void run() {
							
							try {
								Robot robot = new Robot();
							//	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
							//	robot.mouseMove(d.width/2, d.height/2);
								robot.mousePress(InputEvent.BUTTON2_MASK);//按下中键
								robot.delay(100);
							    playerComponent.setCursorEnabled(false);

								robot.mouseRelease(InputEvent.BUTTON2_MASK);//释放中键
								

							} catch (AWTException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 
							
						
						}
					};
					
					timer.schedule(task,2000,3000);
		
					
				}
				else if(flag==false){
					timer.cancel();
					playerComponent.setCursorEnabled(true);
					//System.out.println(mediaPlayer.isFullScreen());
					mediaPlayer.setFullScreen(false);
					menuBar.setVisible(true);
					btnFullscreen.setIcon(new ImageIcon("images/fullscreen.png"));
					
					contentPane.setBackground(new Color(240, 240, 240));
					
					PlayMain.p().setMarqueeText("Normal Mode");
					PlayMain.p().setMarqueeSize(26);
					PlayMain.p().setMarqueeColour(Color.WHITE);
					PlayMain.p().setMarqueeTimeout(1900);
					PlayMain.p().setMarqueePosition(libvlc_marquee_position_e.centre);
					PlayMain.p().setMarqueeOpacity(0.3f);
					PlayMain.p().enableMarquee(true);
					
					flag=true;	
					//playerComponent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					playerComponent.getVideoSurface().removeMouseMotionListener(mmove);
					playerComponent.getVideoSurface().removeMouseListener(mclickleave);
					//contentPane.removeMouseMotionListener(mmove);
					//contentPane.removeMouseListener(mclickleave);
					
					
				}
				
				
			}
		});
		
		controlPanel.add(slider);
		
		btnNewButton = new JButton(new ImageIcon("images/volume-high.png"));
		btnNewButton.setFocusPainted(false);//去掉按钮虚线框
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			boolean fla=true;
			
			public void actionPerformed(ActionEvent e) {
				if(fla==true){
					btnNewButton.setIcon(new ImageIcon("images/volume-muted.png"));
					PlayMain.setVol(0);
					fla=false;	
				}else if (fla==false) {
					btnNewButton.setIcon(new ImageIcon("images/volume-high.png"));
					PlayMain.setVol(slider.getValue());
					fla=true;
				}
			}
		});
		btnNewButton.setPreferredSize(new Dimension(24,24));//用流式布局，就要设置preferredSize
		controlPanel.add(btnNewButton);
		
		panelchild = new JPanel();
		panel.add(panelchild, BorderLayout.NORTH);
		panelchild.setLayout(new BorderLayout(0, 0));
		
		
		
		progress = new JProgressBar();	
		progress.setFocusable(false);
		progress.setStringPainted(true);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1 && e.getButton() == MouseEvent.BUTTON1){
			 	  int x = e.getX();
				  PlayMain.jumpTo((float)x/progress.getWidth());
				}
			}
			
		});
		panelchild.add(progress, BorderLayout.CENTER);
		
		label = new JLabel();
		
		label.addMouseMotionListener(new MouseAdapter(){  
		       public void mouseMoved(MouseEvent e) {  
		    	   label.setToolTipText("Elapsed time");
		           }  
		       }  
		   );	
		panelchild.add(label, BorderLayout.WEST);
		
		label1 = new JLabel();
		label1.setFocusable(false);
		label1.addMouseMotionListener(new MouseAdapter(){  
		       public void mouseMoved(MouseEvent e) {  
		    	   
		    		   label1.setToolTipText("Tatal/Remaining time"+"-"+PlayMain.formatTime(PlayMain.getLength()-PlayMain.getTime()));
		           }  
		       }  
		   );
		
		panelchild.add(label1, BorderLayout.EAST);
		
		btnPause.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {				
				PlayMain.pause();		
			}
		});
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PlayMain.play();
			}
		});
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayMain.stop();
			}
		});
	}

	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
	}
	
	public JProgressBar getProgressBar() {
		return progress;
	}
	
}
