package com.tao.videoplayer.tools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPopupMenu;


public class NodePad extends JFrame {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTextArea ta;
	static JFrame app;
	static JLabel jl;
	static JLabel jl2;
 
 public static JTextArea jta(){
		return ta;
	}
 
/** 
  * 新建文件和打开文件
 */
 // 各菜单的监听均采用内部类实现，子菜单的监听采用内部类的内部类
class New implements ActionListener{// 新建文件监听
 
  public void actionPerformed(ActionEvent e) {
   ta.setText("");// 将ta的Text设置为空字符串，实现新建
 }
 }

class Open implements ActionListener{// 打开文件监听
	JFileChooser jf;
  public void actionPerformed(ActionEvent e) {
	
   jf = new JFileChooser();
   FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
   jf.addChoosableFileFilter(filter);
   // 文件过滤默认仅显示TXT文件，选择所有文件才全显示
   jf.showOpenDialog(NodePad.this);// 显示打开文件对话框
    ta.setText("");	//删除打开的前一文本的内容
  
  try {
	InputStreamReader read=new InputStreamReader(new FileInputStream(jf.getSelectedFile()),"gbk");
    BufferedReader br = new BufferedReader(read);
    String S;
    for (S = br.readLine(); S != null; S = br.readLine()) // 按行读取文件   
    ta.append(S + "\n");// 写入ta，并执行换行
   br.close();
   } catch (IOException a) {
    System.out.println("Open file error!");
    a.printStackTrace();
   }

 }

}
 /**
  * 保存文件
 */
 class Save implements ActionListener{
  //注：保存文件后，如果用Windows自带的记事本打开时发现只有一行代码，
 //这是因为此文件是用记事本创建的，如果用此记事本打开显示就正常了。
    public void actionPerformed(ActionEvent e){
      JFileChooser jf = new JFileChooser();
      jf.setAcceptAllFileFilterUsed(false);//去除所有文件的支持
      FileNameExtensionFilter filter = new FileNameExtensionFilter ("文本文档(*.txt)", "txt");
      jf.addChoosableFileFilter(filter);
      jf.setAcceptAllFileFilterUsed(true); //添加所有文件的支持   
      //文件过滤默认保存为TXT文件，选择所有的文件时需加后缀   
      jf.showSaveDialog(NodePad.this);//显示保存文件对话框
     String fileName=jf.getSelectedFile().getAbsolutePath().trim();
      //获取保存文件的路径及输入的文件名
     if(fileName!=null)
      try{
       BufferedWriter bw=new BufferedWriter(new FileWriter(fileName+".txt"));
      //自动加上.txt的后缀
      PrintWriter pw=new PrintWriter(bw);
       pw.println(ta.getText());//写入文件
      bw.close();
      }
      catch (IOException a) {
       System.out.println("Save file error!");
       a.printStackTrace();
      }
     }
  }
  //退出程序监听
 class Exit implements ActionListener{ 
     public void actionPerformed(ActionEvent e)  {
     // System.exit(0);//退出程序
    	NodePad.app.dispose();
    }
  }
  
  /**
   * 文件的操作
  */
  //剪切的监听
 class Cut implements ActionListener {
	 
     public void actionPerformed(ActionEvent e){
     
      ta.cut();//剪切
    }
  }
  //复制的监听
 class Copy implements ActionListener{
	 
     public void actionPerformed(ActionEvent e){
      
      ta.copy();//复制 
    }
  }
  //粘贴的监听
 class Paste implements ActionListener{
	 
     public void actionPerformed(ActionEvent e){
     
      ta.paste();//粘贴
    }
  }
  //全选的监听
 class Seletall implements ActionListener {
	 
     public void actionPerformed(ActionEvent e){
     
      ta.selectAll();//选择全部
    }
  }
  //删除的监听
 class Delete implements ActionListener{
	 
     public void actionPerformed(ActionEvent e){
     
      ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
      //用空字符串替换选择部分，实现删除功能
    }
  }
 
 //时间/日期
class TimeDate implements ActionListener{
	 
     public void actionPerformed(ActionEvent e){
     
      ta.requestFocus();
      SimpleDateFormat currentTimeDate=new SimpleDateFormat("HH:mm yyyy/MM/dd");
      ta.insert(currentTimeDate.format(new Date()), ta.getCaretPosition());
     }
  }
  
  /** 
   * 字体的处理
  */
  //字体的监听
 class Fontz implements ActionListener{
	 
     @SuppressWarnings("rawtypes")
	JComboBox cb1;
     @SuppressWarnings("rawtypes")
	JComboBox cb2;
     @SuppressWarnings("rawtypes")
	JComboBox cb3;

     String typeface="宋体";
     int font=Font.PLAIN;
     int wordsize=16;
     
     class xuanziti implements ItemListener{//内部类，用来监听字体
    	
      public void itemStateChanged(ItemEvent a){
    	  
       if(cb1.getSelectedIndex()==0){ //根据组合框选择的项，设置字体
    	 typeface="宋体";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==1){
    	 typeface="楷体";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==2){
    	 typeface="隶书";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==3){
    	 typeface="微软雅黑";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==4){
    	 typeface="黑体";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==5){
      	 typeface="幼圆";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==6){
         typeface="方正舒体";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==7){
         typeface="方正姚体";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==8){
         typeface="仿宋";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==9){
         typeface="华文彩云";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==10){
         typeface="Arial";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==11){
         typeface="Batang";
           ta.setFont(new Font(typeface,font, wordsize));}
       else if(cb1.getSelectedIndex()==12){
           typeface="Bauhaus 93";
             ta.setFont(new Font(typeface,font, wordsize));}
      }
     }
     
     class xuanzixing implements ItemListener{ //内部类，用来监听字形
 
      public void itemStateChanged(ItemEvent a){
    	  
       if(cb2.getSelectedIndex()==0){ //根据组合框选择的项，设置字型
    	 font=Font.BOLD;  
       ta.setFont(new Font(typeface,font, wordsize));
       }
       else if(cb2.getSelectedIndex()==1){
    	 font=Font.ITALIC;
       ta.setFont(new Font(typeface,font, wordsize));
       }
       else if(cb2.getSelectedIndex()==2){
    	 font=Font.PLAIN;
       ta.setFont(new Font(typeface,font, wordsize));
       }
       
      }
     }
     class xuandaxiao implements ItemListener{//内部类，用来监听字体大小
    
      public void itemStateChanged(ItemEvent a){
      
       if(cb3.getSelectedIndex()==0){//根据组合框选择的项，设置字号
    	 wordsize=8;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if(cb3.getSelectedIndex()==1){
    	 wordsize=10;
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if(cb3.getSelectedIndex()==2){
    	 wordsize=14;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==3){
    	 wordsize=16;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==4){
    	 wordsize=18;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==5){
    	 wordsize=20;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==6){
    	 wordsize=25;  
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==7){
    	 wordsize=30;
        ta.setFont(new Font(typeface,font, wordsize));
       }
       else if (cb3.getSelectedIndex()==8){
      	 wordsize=36;
        ta.setFont(new Font(typeface,font, wordsize));
        }
       else if (cb3.getSelectedIndex()==9){
      	 wordsize=40;
        ta.setFont(new Font(typeface,font, wordsize));
        }
       else if (cb3.getSelectedIndex()==10){
         wordsize=48;
        ta.setFont(new Font(typeface,font, wordsize));
         }
       else if (cb3.getSelectedIndex()==11){
         wordsize=72;
        ta.setFont(new Font(typeface,font, wordsize));
         }
       else if (cb3.getSelectedIndex()==12){
           wordsize=63;//特号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==13){
           wordsize=42;//初号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==14){
           wordsize=27;//一号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==15){
           wordsize=21;//二号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==16){
           wordsize=16;//三号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==17){
           wordsize=14;//四号
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==18){
           wordsize=12;//小四
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==19){
           wordsize=11;//五号10.5
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==20){
           wordsize=9;//小五
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==21){
           wordsize=8;//六号7.5
          ta.setFont(new Font(typeface,font, wordsize));
           }
       else if (cb3.getSelectedIndex()==22){
           wordsize=7;//小六6.5
          ta.setFont(new Font(typeface,font, wordsize));
           }
      }
     } 
     
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e){
     
    
      JDialog jd=new JDialog(app,"设置字体",false);//创建对话框
      Container c=jd.getContentPane();
      String [] ziti={
    		  "宋体 ","楷体 ","隶书","微软雅黑",
    		  "黑体","幼圆","方正舒体","方正姚体",
    		  "仿宋","华文彩云","Arial","Batang","Bauhaus 93"
    		  };
      String [] zixing={"粗体 ","斜体 ", "常规 "};
      String [] daxiao={
    		  "8 ","10 ","14","16","18","20",
    		  "25","30","36","40","48","72","特号",
    		  "初号","一号","二号","三号","四号","小四",
    		  "五号","小五","六号","小六"
    		  };
      cb1=new JComboBox(ziti);//创建三个组合框
      cb2=new JComboBox(zixing);
      cb3=new JComboBox(daxiao);
      c.setLayout(new FlowLayout());
      c.add(cb1);//在对话框中添加三个组合框分别用来控制字体、字形、大小
      cb1.addItemListener(new xuanziti());//注册监听器
      c.add(cb2);
      cb2.addItemListener(new xuanzixing());
      c.add(cb3);
      cb3.addItemListener(new xuandaxiao());
      //jd.setLocationRelativeTo(app);
      jd.setLocation(100, 300);
      jd.setSize(300,100);
      jd.setVisible(true);

    }
  }
  
  /**
   * 状态栏和帮助功能
  */
  //状态栏的监听
 class zhuangtailan implements ActionListener{
  
     public void actionPerformed(ActionEvent e){
     
      jl2.setText("总共：Line "+ta.getLineCount());//最下面的面板中显示总的行数
    }
  }
  //帮助的监听
 class Help implements ActionListener{
  
     public void actionPerformed(ActionEvent e){
     
      JDialog jd=new JDialog(app,"帮助主题",false);//创建对话框，以对话框显示信息
      Container C=jd.getContentPane();
      C.add(new JLabel("\0\0使用方法基本同Windows记事本"));
      jd.setLocation(150, 150);
      jd.setSize(230,150);
      jd.setVisible(true);
     }
  }
  //关于记事本的监听
 class About implements ActionListener{

	 JLabel jl=new JLabel(new ImageIcon("images/windows 8.png"));
	 JLabel jl1=new JLabel(new ImageIcon("images/nodepad.png"));
     JLabel jl2=new JLabel("\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0版权所有©2015~ \0");
     JTextArea jTextArea=new JTextArea();
     
     public void actionPerformed(ActionEvent e){
    	 jTextArea.setText(
    			 "\0\0\0Microsoft Windows\n"
    			 + "\0\0\0版本6.3 (内部版本 9600)\n"
    			 + "\0\0\0© 2013 Microsoft Corporation。保留所有权利\n"
    			 + "\0\0\0Windows8.1 专业版 操作系统及其用户界面受美国和其他国家/地区的商标法\0\0\0\0\0\n"
    			 + "\0\0\0和其他待颁布或已颁布的知识产权法保护。\n\n\n"
    			 + "\0\0\0*本程序是Java高仿 Windows 8.1 记事本：\n\n"
    			 + "\0\0\0\0\0\0供Omnipotent Player程序使用，也可单独使用\n\n"
    			 + "\0\0\0\0\0\0作者：Ahmed_Tao_Yang"
    			 );
    	 jTextArea.setEditable(false);//设置为不可编辑
    	 jTextArea.setOpaque(false);//透明化
      JDialog jd=new JDialog(app,"关于\"记事本\"",false);//建立对话框
      Container c=jd.getContentPane();
      jd.setLocation(100, 150);
      c.setLayout(new BorderLayout());
      c.add(jl,BorderLayout.NORTH);
      c.add(jl1,BorderLayout.WEST);
      c.add(jTextArea,BorderLayout.CENTER);
      c.add(jl2,BorderLayout.SOUTH);
      jd.pack();
      jd.setVisible(true);
     }
  }
  
  /**
   * 将功能实例化
  */
  public NodePad(){
  
	  super("记事本");    
     //创建菜单栏，并加入各下拉式菜单及菜单项
    JMenuBar mBar=new JMenuBar();
     JMenu[] mA
  ={new JMenu("文件(F)"),
  new JMenu("编辑(E)"),
  new JMenu("格式(O)"),
  new JMenu("查看(V)"),
  new JMenu("帮助(H)")};
     char[][] mC={{'F','E','O','V','H'},{'N','O','S','X'},{'U','T','C','P','L','D','A'},{'F'},{'S'},{'H','A'}};
//     char[][] mD={{'N','O','S'},{'Z','X','C','V','A'}};
     JMenuItem[][] mI={
  {new JMenuItem("新建(N)"),
  new JMenuItem("打开(O)"),
  new JMenuItem("保存(S)"),
  new JMenuItem("退出(X)")},
  {new JMenuItem("剪切(T)"),
  new JMenuItem("复制(C)"),
  new JMenuItem("粘贴(P)"), 
  new JMenuItem("全选(A)"),
  new JMenuItem("删除(L)"),
  new JMenuItem("时间/日期(D)")},
  {new JMenuItem("字体(F)")},
  {new JMenuItem("状态栏(S)")},
  {new JMenuItem("查看帮助(H)"),
  new JMenuItem("关于记事本(A)")}};
     
     setJMenuBar(mBar);
     
     JCheckBoxMenuItem check= new JCheckBoxMenuItem("自动换行(W)");
     mA[2].add(check);
     check.setMnemonic(KeyEvent.VK_W);
     //自动换行
     check.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			int state=e.getStateChange();
			if(state==ItemEvent.SELECTED){
			ta.setLineWrap(true);//设置内容自动换行
			}
			else {
				ta.setLineWrap(false);
			}
				
		}
	});
     
     int i,j;
     for(i=0;i<mA.length;i++)
     {
      mBar.add(mA[i]);
  //添加下拉式菜单
     mA[i].setMnemonic(mC[0][i]);
      for(j=0;j<mI[i].length;j++)
       {
        mA[i].add(mI[i][j]);
  //添加菜单项
       mI[i][j].setMnemonic(mC[i+1][j]);//设置快捷键
  //设置助记符     
       }
     }
     
     //各个菜单项注册监听器
     mI[0][0].addActionListener(new New());
     mI[0][1].addActionListener(new Open());
     mI[0][2].addActionListener(new Save());
     mI[0][3].addActionListener(new Exit());
     mI[1][0].addActionListener(new Cut());
     mI[1][1].addActionListener(new Copy());
     mI[1][2].addActionListener(new Paste());
     mI[1][3].addActionListener(new Seletall());
     mI[1][4].addActionListener(new Delete());
     mI[1][5].addActionListener(new TimeDate());
     mI[2][0].addActionListener(new Fontz());
     mI[3][0].addActionListener(new zhuangtailan());
     mI[4][0].addActionListener(new Help());
     mI[4][1].addActionListener(new About());
  }
  //构造函数NodePad中添加了菜单栏及注册了菜单的监听器
 public static void main(String args[]){
  //程序入口，主函数
 
     app=new NodePad() ;//新建带菜单栏的框架
     app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     app.setSize(500,700);
     Container c=app.getContentPane();
  //获得内容窗格
    c.setLayout(new BorderLayout());
  //设置布局管理为边界布局
    ta=new JTextArea();
    JPopupMenu jPopupMenu =new JPopupMenu();//右键菜单
        
    JMenuItem jmi1=new JMenuItem("复制(C)");
    JMenuItem jmi2=new JMenuItem("粘贴(P)");
    JMenuItem jmi3=new JMenuItem("剪切(T)");
    JMenuItem jmi4=new JMenuItem("全选(A)");
    JMenuItem jmi5=new JMenuItem("删除(D)");
    ta.setComponentPopupMenu(jPopupMenu);
    
    jPopupMenu.add(jmi1);
    jPopupMenu.add(jmi2);
    jPopupMenu.add(jmi3);
    jPopupMenu.add(jmi4);
    jPopupMenu.add(jmi5);
    
    jmi1.setMnemonic(KeyEvent.VK_C);
    jmi2.setMnemonic(KeyEvent.VK_P);
    jmi3.setMnemonic(KeyEvent.VK_T);
    jmi4.setMnemonic(KeyEvent.VK_A);
    jmi5.setMnemonic(KeyEvent.VK_D);
    
    jmi1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
    jmi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
    jmi3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
    jmi4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
   // jmi5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
   // jmi5.setAccelerator(KeyStroke.getKeyStroke('D',InputEvent.CTRL_MASK,false));
    jmi1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ta.copy();
		}
	});
    
    jmi2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ta.paste();
		}
	});
    
    jmi3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ta.cut();
		}
	});
    
    jmi4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ta.selectAll();
		}
	});
    
    jmi5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
		}
	});
  //新建文本区域
    JScrollPane sta=new JScrollPane(ta);
    jl2=new JLabel("总共：Line 0",JLabel.RIGHT);
  //新建标签
    jl2.setSize(800,20);
    c.add(sta,BorderLayout.CENTER);
  //在BorderLayout.CENTER中添加文本区域作为主编辑区 
    c.add(jl2, BorderLayout.SOUTH);
  //设置默认字体，字型，字号  
    ta.setFont(new Font("宋体",Font.PLAIN, 16));
  //在BorderLayout.SOUTH中添加标签用来查看状态
    app.setVisible(true);
  }
	
  }//类NodePad结束

