package com.tao.videoplayer.tools;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ScreenShot {
	
	static ScreenShotWindow ssw;

 public static void main(String[] args) {

  EventQueue.invokeLater(new Runnable() { 
   @Override
   public void run() {
    try {
    ssw=new ScreenShotWindow();
     ssw.setVisible(true);
    } catch (AWTException e) {
     e.printStackTrace();
    }
   }
  });
 }
}
/*
 * 截图窗口
 */
class ScreenShotWindow extends JWindow{ 
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int orgx, orgy, endx, endy;
    private BufferedImage image=null;
    private BufferedImage tempImage=null;
    private BufferedImage saveImage=null;

    private ToolsWindow tools=null;

 public ScreenShotWindow() throws AWTException{
   //获取屏幕尺寸
   Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
   this.setBounds(0, 0, d.width, d.height);

   //截取屏幕
   Robot robot = new Robot();
   image = robot.createScreenCapture(new Rectangle(0, 0, d.width,d.height));

   this.addMouseListener(new MouseAdapter() {
    @Override
   public void mousePressed(MouseEvent e) {
    //鼠标松开时记录结束点坐标，并隐藏操作窗口
             orgx = e.getX();
             orgy = e.getY();

             if(tools!=null){
              tools.setVisible(false);
             }
   }
   @Override
   public void mouseReleased(MouseEvent e) {
    //鼠标松开时，显示操作窗口
    if(tools==null){
     tools=new ToolsWindow(ScreenShotWindow.this,e.getX(),e.getY());
    }else{
     tools.setLocation(e.getX(),e.getY());
    }
    tools.setVisible(true);
    tools.toFront();
   }
  });

   this.addMouseMotionListener(new MouseMotionAdapter() {

   @Override
   public void mouseDragged(MouseEvent e) {
    //鼠标拖动时，记录坐标并重绘窗口
                endx = e.getX();
                endy = e.getY();

                //临时图像，用于缓冲屏幕区域放置屏幕闪烁
                Image tempImage2=createImage(ScreenShotWindow.this.getWidth(),ScreenShotWindow.this.getHeight());
                Graphics g =tempImage2.getGraphics();
                g.drawImage(tempImage, 0, 0, null);
                int x = Math.min(orgx, endx);
                int y = Math.min(orgy, endy);
                int width = Math.abs(endx - orgx)+1;
                int height = Math.abs(endy - orgy)+1;
                // 加上1防止width或height0
                g.setColor(Color.BLUE);
                g.drawRect(x-1, y-1, width+1, height+1);
                //减1加1都了防止图片矩形框覆盖掉
                saveImage = image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y, null);

                ScreenShotWindow.this.getGraphics().drawImage(tempImage2,0,0,ScreenShotWindow.this);
   }
  });
 }

    @Override
    public void paint(Graphics g) {
        RescaleOp ro = new RescaleOp(0.8f, 0, null);
        tempImage = ro.filter(image, null);
        g.drawImage(tempImage, 0, 0, this);
    }
    //保存图像到文件
 public void saveImage() throws IOException {
  JFileChooser jfc=new JFileChooser();
  jfc.setDialogTitle("保存");
  jfc.setAcceptAllFileFilterUsed(false);//去除所有文件的支持
//  jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//默认是选择单个文件，可以设置选择多文件、文件夹
  //文件过滤器，用户过滤可选择文件
  
  
  FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG file(*.jpg/.jpeg)", ".jpg",".jpeg");
  //也可以用jfc.setFileFilter(filter);
  FileNameExtensionFilter filter1 = new FileNameExtensionFilter("PNG file(*.png)", ".png");
 //也可以用jfc.setFileFilter(filter1);
  FileNameExtensionFilter filter2 = new FileNameExtensionFilter("BMP file(*.bmp)", "bmp");
 //也可以用jfc.setFileFilter(filter2);
  FileNameExtensionFilter filter3 = new FileNameExtensionFilter("GIF file(*.gif)", "gif");
  //也可以用jfc.setFileFilter(filter3);

  jfc.addChoosableFileFilter(filter);
  jfc.addChoosableFileFilter(filter1);
  jfc.addChoosableFileFilter(filter2);
  jfc.addChoosableFileFilter(filter3);
  
  //初始化一个默认文件（此文件会生成到桌面上）
  SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
     String fileName = sdf.format(new Date());
     File filePath = FileSystemView.getFileSystemView().getHomeDirectory();
    
    File defaultFile = new File(filePath + File.separator + fileName+ ".jpg");
    jfc.setSelectedFile(defaultFile);  
    
//    boolean fil=filter.accept(defaultFile);
//    System.out.println(fil+":jpg");//测试是否接受文件

    
  int flag = jfc.showSaveDialog(this);
  if(flag==JFileChooser.APPROVE_OPTION){
   File file=jfc.getSelectedFile();
   String path=file.getPath();
//   检查文件后缀，放置用户忘记输入后缀或者输入不正确的后缀
//   if(!(path.endsWith(".jpg")||path.endsWith(".JPG"))){
//    path+=".jpg";
//   }
   
//   if(file.getName()==null){
//	   JOptionPane.showMessageDialog(null, "文件名称不能为空！", "标题",JOptionPane.ERROR_MESSAGE);
//   }
   //写入文件
   if(file.getName().toLowerCase().endsWith(".jpg")&&file.getName()!=null){
	   ImageIO.write(saveImage,"jpg",new File(path));
	   System.out.println(jfc.getSelectedFile().toString());
	   ScreenShot.ssw.setVisible(false);
	   }
   else if(file.getName().toLowerCase().endsWith(".jpeg")&&file.getName()!=null){
	   ImageIO.write(saveImage,"jpeg",new File(path));
	   System.out.println(jfc.getSelectedFile().toString());
	   ScreenShot.ssw.setVisible(false);
	   }
   else if(file.getName().toLowerCase().endsWith(".png")&&file.getName()!=null){
	   ImageIO.write(saveImage,"png",new File(path));
	   System.out.println(jfc.getSelectedFile().toString());
	   ScreenShot.ssw.setVisible(false);
	   }
   else if(file.getName().toLowerCase().endsWith(".bmp")&&file.getName()!=null){
	   ImageIO.write(saveImage,"bmp",new File(path));
	   System.out.println(jfc.getSelectedFile().toString());
	   ScreenShot.ssw.setVisible(false);
	   }
   else if(file.getName().toLowerCase().endsWith(".gif")&&file.getName()!=null){
	   ImageIO.write(saveImage,"gif",new File(path));
	   System.out.println(jfc.getSelectedFile().toString());
	   ScreenShot.ssw.setVisible(false);
	   }
  
  }else if(flag==JFileChooser.CANCEL_OPTION){
	  ScreenShot.ssw.dispose();
	  dispose();
  	 }  
   }
 }

/*
 * 操作窗口
 */
class ToolsWindow extends JWindow{
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private ScreenShotWindow parent;

 public ToolsWindow(ScreenShotWindow parent,int x,int y) {
  this.parent=parent;
  this.init();
  this.setLocation(x, y);
  this.pack();
  this.setVisible(true);
 }

 private void init(){

  this.setLayout(new BorderLayout());
  JToolBar toolBar=new JToolBar("截图工具");
  
  //保存按钮
  JButton saveButton=new JButton(new ImageIcon("images/save.png"));
  saveButton.addActionListener(new ActionListener() { 
   @Override
   public void actionPerformed(ActionEvent e) {
    try {
     parent.saveImage();
     dispose();
    } catch (IOException e1) {
     e1.printStackTrace();
    }
   }
  });
  toolBar.add(saveButton);

  //关闭按钮
  JButton closeButton=new JButton(new ImageIcon("images/close.png"));
  closeButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
	   
	  ScreenShot.ssw.dispose();
	  dispose();
  //  System.exit(0);
   }
  });
  toolBar.add(closeButton);

  this.add(toolBar,BorderLayout.NORTH);
 }
}

