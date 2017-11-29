package com.tao.videoplayer.main;

import java.awt.EventQueue;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.tao.videoplayer.views.MainWindow;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayMain {

	static MainWindow frame;
	public static JFileChooser chooser;
	static File dir;
	static File dir3;
	
	public static MainWindow f(){
		  return frame;
	}
	
	public static EmbeddedMediaPlayer p(){
		return frame.getMediaPlayer();
	}
	
	public static File fi(){
		return chooser.getSelectedFile();
	}
	
	public static void setLookAndFeel() {
	       String lookAndFeelClassName;
	       if (RuntimeUtil.isNix()) {
	            lookAndFeelClassName = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";//GTK风格 (需要在相关的操作系统上方可实现)
	       }
	       else if (RuntimeUtil.isMac()){
	            lookAndFeelClassName = "com.sun.java.swing.plaf.mac.MacLookAndFeel";//Mac风格 (需要在相关的操作系统上方可实现)
	       }
	       else{
	    	   lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
	       }
	       try {
	            UIManager.setLookAndFeel(lookAndFeelClassName);
	       }
	       catch(Exception e) {
	            //默认失败，不重要
	       }
	   }

	
		// java -Dfile.encoding=utf-8 -jar ***.jar
	public static void main(String[] args) {
		if (RuntimeUtil.isMac()) {
			NativeLibrary.addSearchPath(
					RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
					);
		}else if (RuntimeUtil.isWindows()) {
			NativeLibrary.addSearchPath(
					RuntimeUtil.getLibVlcLibraryName(), ".\\dll"
					);//"X:\\VideoLAN\\VLC",".\\dll"
		}else if (RuntimeUtil.isNix()) {
			NativeLibrary.addSearchPath(
					RuntimeUtil.getLibVlcLibraryName(), "/usr/lib/vlc"
					);
		}
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		/*
		 * EventQueue.invokeLater(new Runnable(){}调用完毕后，会被销毁
		 */
		EventQueue.invokeLater(new Runnable() {//导致runnable的run方法在EventQueue的指派线程上被调用
			public void run() {
				try {
//					UIManager.setLookAndFeel(
//							"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
//							);//换肤的代码一定要写在窗体实例化之前
//					UIManager.setLookAndFeel
//					("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				
					setLookAndFeel();//皮肤	
					
					frame = new MainWindow();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//让frame居中显示在屏幕上
					//frame.setFocusable(true);//frame设置为可获取焦点状态
					MainWindow.playerComponent.getVideoSurface().requestFocusInWindow();
					
					
//					frame.addWindowListener(new WindowAdapter(){//窗口监听
//						public void windowClosing(WindowEvent e){
//							
//						       System.out.println("窗体执行关闭！");
//						  }
//					 });
					
					
					String options[] = {"--subsdec-encoding=GB18030"};
					frame.getMediaPlayer().prepareMedia("D:/迅雷下载/Anime Studio Webinar.mp4",options);//预备视频
//					frame.getMediaPlayer().playMedia("/Users/acely/Movies/BBC Earth.The.Biography/Earth.The.Biography.UNRATED.Ep04.2007.BluRay.720p.x264.DTS-WiKi.chs.mkv",options);
//                  frame.getMediaPlayer().toggleFullScreen();
					new SwingWorker<String, Integer>() {//使用SwingWorker线程工作模式，程序能启动一个任务线程来异步查询，并马上返回EDT线程（Swing事件调度线程），允许EDT继续执行后续的UI事件。
//                  String是doInBackground和get方法的返回类型
//                  Integer是publish和process方法要处理的数据类型						
						@Override
						protected String doInBackground() throws Exception {//doInBackground方法作为任务线程的一部分执行，它负责完成线程的基本任务，并以返回值来作为线程的执行结果。
							while (true) {
							    long total = frame.getMediaPlayer().getLength();
								long curr = frame.getMediaPlayer().getTime();
								if(total==-1){total++;}
								if(curr==-1){curr++;}
								float percent = (float)curr/total;
								if(total!=0&&(curr<=total)){					
									publish((int)(percent*100));//使用publish方法来发布要处理的中间数据
								}else if (total!=0&&(total-curr<10)){
									publish(100);
								}
								if(total==0){
									publish(0);
								}
																
								MainWindow.label.setText(formatTime(curr));	
								if(total==0){
									MainWindow.label1.setText("-:——:——");
								}else{
									MainWindow.label1.setText(formatTime(total));
								}
								
								
								//System.out.println(MainWindow.playerComponent.getVideoSurface());
//								System.out.println(total);
//								System.out.println(curr);
//								System.out.println("-"+formatTime(total-curr));
//								System.out.println(frame.getMediaPlayer().getRate());
								Thread.sleep(100);
								
							}
							
						}

						protected void process(java.util.List<Integer> chunks) {
							for (int v : chunks) {
								frame.getProgressBar().setValue(v);
							}

						};
					}.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	public static void play() {
		frame.getMediaPlayer().play();
		
	}

	public static void playMedi(String filepath) {
		frame.getMediaPlayer().playMedia(filepath);
		
	}
	
	public static void pause() {
		frame.getMediaPlayer().pause();
	}

	public static void stop() {
		frame.getMediaPlayer().stop();
		frame.setTitle("Omnipotent Player");
	}

	public static void jumpTo(float to) {
		frame.getMediaPlayer().setTime((long)(to*frame.getMediaPlayer().getLength()));
	}
	
	public static void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}

	public static long getTime() {
		return frame.getMediaPlayer().getTime();
	}
	
	public static long getLength() {
		return frame.getMediaPlayer().getLength();
	}
	
	public static void setTime(long v) {
		frame.getMediaPlayer().setTime(v);
	}
	
	public static String getExtensionName(String filename){//得到文件拓展名
		if((filename!=null)&&(filename.length()>0)){
			int dot =filename.lastIndexOf('.');
			if((dot>-1)&&(dot<(filename.length()-1))){
				return filename.substring(dot+1);
			}
		}
		return filename;
	}
	
	public static void openVideo() {
		
		chooser=new JFileChooser();
		chooser.setCurrentDirectory(dir);
       
		int log = chooser.showOpenDialog(null);
		if (log == JFileChooser.APPROVE_OPTION) {
			 File file = chooser.getSelectedFile();	
			 dir=chooser.getCurrentDirectory();
			 //System.out.println(dir);
			 frame.setTitle(file.getName()+"-"+"Omnipotent Player");
			 frame.getMediaPlayer().playMedia(file.getAbsolutePath().trim());	
		
		}else if (log == JFileChooser.CANCEL_OPTION){
			
			chooser.setDialogTitle(" ");
			//throw new NullPointerException();
		}
	}

	public static void openSubtitle() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir3);
		int log = chooser.showOpenDialog(null);
		if (log == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			 dir3=chooser.getCurrentDirectory();
			frame.getMediaPlayer().setSubTitleFile(file);
		}
	}
	
	public static String formatTime(long ms){
		int ss=1000;
		int mi=ss*60;
		int hh=mi*60;
		int dd=hh*24;
		
		long day =ms/dd;
		long hour=(ms-day*dd)/hh;
		long minute=(ms-day*dd-hour*hh)/mi;
		long second=(ms-day*dd-hour*hh-minute*mi)/ss;
		long milliSecond=ms-day*dd-hour*hh-minute*mi-second*ss;
		
//      String strDay=day<10?"0"+day:""+day;
		String strHour=hour<10?"0"+hour:""+hour;
		String strMinute=minute<10?"0"+minute:""+minute;
		String strSecond=second<10?"0"+second:""+second;
		String strMilliSecond=milliSecond<10?"0"+milliSecond:""+milliSecond;
		strMilliSecond=milliSecond<100?"0"+strMilliSecond:""+strMilliSecond;
		
		return strHour+":"+strMinute+":"+strSecond;
	}
	
	public static void exit() {
	
		//frame.getMediaPlayer().release();//用此方法会出现error.log的日志报告
		frame.dispose();
		System.exit(0);	//关闭java虚拟机				
	}
}
