package userinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import mysql.SQL;

public class FreightInfo extends JFrame implements ActionListener {
	AddFreight addfre = null; 
	JPanel jp1, jp2,jp3,jp4,jp5,jp6,jp7,jp8,jpcard = null;
	JTable table1 ,table2 = null;
	JLabel jlb1,jlb2, jlb3,backlb =null;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7, jb8,jb9 , jb10=null;
	CardLayout cardLayout,cl= null;
	DefaultTableModel model1,model2 = null;
	JScrollPane jsp1,jsp2 = null;
	int i;
	String dataset[];
	//��ֻ��Ϣ���캯��
	public FreightInfo() {
		// TODO Auto-generated constructor stub
		//�������
		jlb1 = new JLabel("���д�ֻ��Ϣ");
		
		jb1 = new JButton("��ֻ��Ϣ");
		jb2 = new JButton("���Ӵ�ֻ");
		jb3 = new JButton("ɾ����ֻ");
		jb4 = new JButton("�޸Ĵ�ֻ");
		jb5 = new JButton("ˢ��");
		jb6 = new JButton("�˳�����");
		
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		
		//���ô�ֻ��Ϣ��
		String [] cols1 = {"��ֻ����","����","�ִ�ۿ�ʱ��","����ʱ��","ƫ�ò�λ"};
		model1 = new DefaultTableModel(cols1, 18);
		table1 = new JTable(model1);
//		table1.setPreferredScrollableViewportSize(getPreferredSize());
		table1.setPreferredScrollableViewportSize(new Dimension(400, 200));
		jsp1 = new JScrollPane(table1);
		
		//�������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		
		//���˵���
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		//��ֻ�б�
		jp2.add(jlb1);
		jp2.add(jb5);
		jp2.add(jsp1);
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		//�˳�
		jp3.add(jb6);
		
		//��Ƭ����
		cardLayout =new CardLayout();
		jpcard = new JPanel(cardLayout);
		jpcard.add(jp2,"card1");
		jpcard.add(jp4,"card2");
		jpcard.add(jp5,"card3");
		jpcard.add(jp6,"card4");
		cl =(CardLayout)(jpcard.getLayout());
		cl.show(jpcard, "card1");
		
		
		//Jframe��ʼ
		this.setTitle("��ֻ����");
		this.setLayout(new BorderLayout());
		this.add(jp1,BorderLayout.NORTH);
		this.add(jpcard,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		this.setSize(800, 350);
		this.setLocation(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ʾ���д�ֻ��Ϣ
		if (e.getActionCommand()=="ˢ��") {
			
			dataset = new String[5];
			try {
				SQL.connectSql();
				SQL.queryFreight();
				i=0;
				while (SQL.rs3.next()) {
					dataset[0] = SQL.rs3.getString(1);
					dataset[1] = SQL.rs3.getString(2);
					dataset[2] = SQL.rs3.getString(3);
					dataset[3] = SQL.rs3.getString(4);
					dataset[4] = SQL.rs3.getString(5);
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
//			System.out.println(dataset);
//			System.out.println(SQL.rs3);
//			model1.fireTableDataChanged();
		}
		else if (e.getActionCommand()=="�˳�����") {
			this.dispose();
		}
		else if (e.getActionCommand()=="���Ӵ�ֻ") {
			addfre = new AddFreight();
		}
		//�޸Ĵ�ֻ��Ϣ
		else if (e.getActionCommand()=="�޸Ĵ�ֻ") {
			int t =JOptionPane.showConfirmDialog(null, "ȷ���޸���");
//			System.out.println(t);
			if (t==0) {//ȷ���޸ĺ�
				int row = table1.getSelectedRow();
				int column = table1.getSelectedColumn();
				if (column==0) {
					JOptionPane.showMessageDialog(null, "�޷��޸Ĵ�ֻ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				}
				else {
//					model1.getColumnName(column);
					String val = (String) table1.getValueAt(row, column);
					String sid = (String) table1.getValueAt(row, 0);
					String col =  model1.getColumnName(column);
					try {
						SQL.connectSql();
						SQL.updateFreight(val, sid, col);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
					table1.updateUI();
				}
			}
		}
		else if (e.getActionCommand()=="ɾ����ֻ") {
			int t = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������¼��");
			if (t==0) {
				int row = table1.getSelectedRow();
				String sid= (String) table1.getValueAt(row, 0);
				try {
					SQL.connectSql();
					SQL.deleteFreight(sid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				//��jtable�ж�Ӧ�е��������
				for (int i = 0; i < 5; i++) {
					table1.setValueAt(null, row, i);
				}
			}
		}
	}
}
