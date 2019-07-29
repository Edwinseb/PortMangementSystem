package userinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import algorithm.DateCalculate;
import gantechart.GanttChart;
import gantechart.GanttChartModel;
import mysql.SQL;


public class PortInfo extends JFrame implements ActionListener {
	//�������
	private ImageIcon background=null;
	private JPanel imagepanel=null;
	private FreightInfo freightInfo;
	BackgroundPanel bgp;
	JPanel jp1, jp2,jp3,jp4,jp5,jp6,jp7,jp8,jpcard = null;
	JTextField jtf1,jtf2 =null;
	JTable table1 ,table2 = null;
	JLabel jlb1,jlb2, jlb3,backlb =null;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7, jb8,jb9 , jb10,jb11=null;
	DefaultTableModel model1,model2 = null;
	JScrollPane jsp1,jsp2 = null;
	JComponent jcom1,jcom2,jcom3,jcom4 = null;
	GridBagLayout gridBagLayout = null; 
	GridBagConstraints gridBagConstraints= null;
	CardLayout cardLayout,cl= null;
	JTabbedPane tabbedPane= null;
	String dataset[];
	int i,portNumber ;
	GanttChartModel gcModel;
	String[] port, startTime, endTime;
	long[] timelong,timestart;
	long minTime;
	//��λ��Ϣ���캯��
	public PortInfo() {
		// TODO Auto-generated constructor stub
		//�������
		jlb1 = new JLabel("��λ��Ϣ");
		jlb2 = new JLabel("��λ����");
		jlb3 = new JLabel("��ֻ����");
		
		jb1 = new JButton("���ò�λ����");
		jb2 = new JButton("�Զ����䲴λ");
		jb3 = new JButton("�ֶ����䲴λ");
		jb4 = new JButton("�޸Ĵ�ֻ����");
		jb5 = new JButton("���Ҵ�ֻ"); 
		jb6 = new JButton("ˢ��");
		jb7 = new JButton("�˳�");
		jb8 = new JButton("��λ��Ϣ");
		jb9 = new JButton("��ֻ��Ϣ");
		jb10 = new JButton( "��λ����");
		jb11 = new JButton("��λ����ͼ");
		
		jtf1 =new JTextField(4);
		jtf2 =new JTextField(8);
		
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);
		jb9.addActionListener(this);
		jb10.addActionListener(this);
		jb11.addActionListener(this);
		//���ò�λ��Ϣ���
		String [] cols1 ={"��λ��", "�Ƿ����","��ֻ����","�벴λʱ��","���ʱ��" };
		model1 = new DefaultTableModel(cols1, 20);
		table1 = new JTable(model1);
		table1.setPreferredScrollableViewportSize(new Dimension(400, 250));
		jsp1 = new JScrollPane(table1);
		//���ô�ֻ��Ϣ���
		String [] cols2 = {"����","�ִ�ۿ�ʱ��","����ʱ��","ƫ�ò�λ"};
		model2 = new DefaultTableModel(cols2,4);
		table2 = new JTable(model2);
		table2.setPreferredScrollableViewportSize(new Dimension(400, 60));
		jsp2 = new JScrollPane(table2);
		
		//�������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();
		//��λ��Ϣ����
		jp1.add(jlb1);
		jp1.add(jb6);
//		jp1.add(table1);
		jp1.add(jsp1);
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		//��ֻ��Ϣ��
		jp2.add(jtf2);
		jp2.add(jb5);
//		jp2.add(table2);
		jp2.add(jsp2);
		jp2.add(jb4);
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		//���ò�λ�����Ͳ�λ����
		jp3.add(jtf1);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		//�Զ����䲴λ
//		jp4.add(jb2);
//		//�ֶ����䲴λ
//		jp5.add(jb3);
//		//�޸Ĵ�ֻ����
//		jp6.add(jb4);
		//�˳�
		jp7.add(jb7);
		//ѡ�ť
		jp8.add(jb8);
		jp8.add(jb9);
		jp8.add(jb10);
		jp8.add(jb11);
		jp8.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
//		gridBagLayout = new GridBagLayout();
//		this.setLayout(gridBagLayout);
//		gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.fill=GridBagConstraints.BOTH;
//		gridBagConstraints.gridx=0;
//        gridBagConstraints.gridy=1;
//        gridBagConstraints.gridwidth=4;                                             
//        gridBagConstraints.gridheight=4; 
//        gridBagLayout.setConstraints(jp1, gridBagConstraints);

		//��Ƭ����
		cardLayout =new CardLayout();
		jpcard = new JPanel(cardLayout);
		jpcard.add(jp1,"card1");
		jpcard.add(jp2,"card2");
		jpcard.add(jp3,"card3");
		cl =(CardLayout)(jpcard.getLayout());
		cl.show(jpcard, "card1");
		
		//����ͼƬ
		
//		background = new ImageIcon("../image/portbackground.jpg");
//		backlb = new JLabel(background);
//		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
//		backlb.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
		
		
		this.setLayout(new BorderLayout());
		this.add(jp8, BorderLayout.NORTH);
		this.add(jpcard,BorderLayout.CENTER);
		this.add(jp7, BorderLayout.SOUTH);
		this.setTitle("�ۿڹ���ϵͳ");
		this.setSize(800,450);
		this.setLocation(400, 500);
//		this.add(bgp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		
		BackgroundPanel back = new BackgroundPanel();
		back.setBackGround(new ImageIcon("D:\\eclipse-jy\\workplace\\PortManagement\\image\\portbackground.jpg"));
		back.paintComponent(this.getGraphics(), this);
	}
	
	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�������ѡ������ʾ
		if (e.getActionCommand()=="��λ��Ϣ") {
			cl.show(jpcard, "card1");
		}
		else if (e.getActionCommand()=="��ֻ��Ϣ") {
			cl.show(jpcard, "card2");
		}
		else if (e.getActionCommand()=="��λ����") {
			cl.show(jpcard, "card3");
		}
		else if (e.getActionCommand()=="�˳�") {
			System.exit(0);
		}
		//��λ��Ϣ���Һ�ע��jtable
		else if (e.getActionCommand()=="ˢ��") {
			try {
				dataset = new String[5];
				SQL.connectSql();
				SQL.queryPort();
				i=0;
				while (SQL.rs1.next()) {
					dataset[0] = SQL.rs1.getString(1);
					dataset[1] = SQL.rs1.getString(2);
					dataset[2] = SQL.rs1.getString(3);
					dataset[3] = SQL.rs1.getString(4);
					dataset[4] = SQL.rs1.getString(5);
					table1.setValueAt(dataset[0], i, 0);
					table1.setValueAt(dataset[1], i, 1);
					table1.setValueAt(dataset[2], i, 2);
					table1.setValueAt(dataset[3], i, 3);
					table1.setValueAt(dataset[4], i, 4);
					i++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		//��ֻ��Ϣ����
		else if (e.getActionCommand()=="���Ҵ�ֻ") {
			dataset= new String[4];
			try {
				SQL.connectSql();
				SQL.queryFreight(jtf2.getText());
				
				if (SQL.rs2.next()) {
					dataset[0] = SQL.rs2.getString(2);
					dataset[1] = SQL.rs2.getString(3);
					dataset[2] = SQL.rs2.getString(4);
					dataset[3] = SQL.rs2.getString(5);
					table2.setValueAt(dataset[0], 0, 0);
					table2.setValueAt(dataset[1], 0, 1);
					table2.setValueAt(dataset[2], 0, 2);
					table2.setValueAt(dataset[3], 0, 3);
				}
				else {
					JOptionPane.showMessageDialog(null,"û�иô���¼","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//�޸Ĵ�ֻ����ʱ�´�һ������
		else if (e.getActionCommand()=="�޸Ĵ�ֻ����") {
			freightInfo = new FreightInfo();
		}
		//���ò�λ����
		else if (e.getActionCommand()=="���ò�λ����") {
			int i = Integer.valueOf(jtf1.getText());
			if (i>0) {
				try {
					SQL.connectSql();
					SQL.queryNumberofPort();
//					System.out.println(SQL.rs4.wasNull());
					if (SQL.rs4.next()) {
						portNumber = SQL.rs4.getInt(1);
						System.out.println(portNumber);
						//������� �������ݴ���С�ڵ������в�λ��
						if (portNumber<i) {
							for (int j = portNumber+1; j < i+1; j++) {
								SQL.increasPortNumber(j);
							}
							JOptionPane.showMessageDialog(null, "�޸Ĳ�λ�����ɹ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
						}
						else if (portNumber==i) {
							JOptionPane.showMessageDialog(null, "�޸Ĳ�λ�����ɹ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
						}
						else if (portNumber>i) {
							for (int j = i+1; j < portNumber+1; j++) {
								SQL.decreasePortNumber(j);
							}
							JOptionPane.showMessageDialog(null, "�޸Ĳ�λ�����ɹ�", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} 
		else if (e.getActionCommand()=="�ֶ����䲴λ") {
			ManualDistribute manualDistribute = new ManualDistribute();
		}
		else if (e.getActionCommand()=="�Զ����䲴λ") {
			try {
				SQL.connectSql();
				SQL.autoDistribute();
				ArrayList<String> freeport= new ArrayList<String>(); 
				ArrayList<String> freefreightnumber = new ArrayList<String>();
				ArrayList<String>freefreightarrive= new ArrayList<String>() ;
				ArrayList<String>freefreightlength= new ArrayList<String>();
				i=0;
				while (SQL.rs6.next()) {
					freefreightnumber.add(SQL.rs6.getString(1));
					freefreightarrive.add(SQL.rs6.getString(3));
					freefreightlength.add(SQL.rs6.getString(4));
					i++;
				}
				i=0;
				while (SQL.rs7.next()) {
					freeport.add(SQL.rs7.getString(1));

					i++;
				}
				
				if (freeport.size()>=freefreightnumber.size()) {
					for (int i = 0; i < freefreightnumber.size(); i++) {
						System.out.println(freeport.get(i)+" "+ freefreightnumber.get(i)+" "+  freefreightarrive.get(i)+" "+ freefreightlength.get(i));
						SQL.updatePort(freeport.get(i), freefreightnumber.get(i), freefreightarrive.get(i), freefreightlength.get(i));
					}
				}
				else if (freeport.size()<freefreightnumber.size()) {
					for (int i = 0; i < freeport.size(); i++) {
						SQL.updatePort(freeport.get(i), freefreightnumber.get(i), freefreightarrive.get(i), freefreightlength.get(i));
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//��ʾ��λ���صĸ���ͼ
		else if (e.getActionCommand()=="��λ����ͼ") {
			try {
				SQL.connectSql();
				SQL.queryPort();
				gcModel = new GanttChartModel();
				ArrayList<String> port = new ArrayList<String>(50);
				ArrayList<String> startTime = new ArrayList<String>(50);
				ArrayList<String> endTime = new ArrayList<String>(50);
				
				int i,loc =0;
//				System.out.println(SQL.rs1.next());
				while (SQL.rs1.next()) {
					port.add(SQL.rs1.getString(1));
					startTime.add(SQL.rs1.getString(4));
					endTime.add(SQL.rs1.getString(5));
					
				}
				timelong = new long[port.size()];
				timestart= new long[port.size()];
				minTime = Integer.MAX_VALUE;
				System.out.println(startTime.size()+"  "+endTime.size());
				for (int j = 0; j < port.size(); j++) {
					timelong[j]= DateCalculate.dateMinus(startTime.get(j), endTime.get(j));
					//&&DateCalculate.transDate(startTime.get(j))<minTime
					if (!startTime.get(j).isEmpty()&&DateCalculate.transDate(startTime.get(j))/(24*60*60*1000)<minTime) {
						minTime=DateCalculate.transDate(startTime.get(j))/(24*60*60*1000);
						loc=j;
					}
				}
				long min = DateCalculate.transDate(startTime.get(loc));
				System.out.println(min+" "+loc);
				for (int j = 0; j < port.size(); j++) {
					if (!startTime.get(j).isEmpty()) {
						timestart[j] =  (DateCalculate.transDate(startTime.get(j))-min)/(24*60*60*1000)+1;
					}
				}
				for (int j = 0; j < port.size(); j++) {
					gcModel.addTask(port.get(j), (int)timestart[j], (int)timelong[j]);
					System.out.println(port.get(j)+" " + timestart[j]+" " +timelong[j]);
				}
				@SuppressWarnings("unused")
				GanttChart ganttChart = new GanttChart(gcModel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			
		
	}
	
}
