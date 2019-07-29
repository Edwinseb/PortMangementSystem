package userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mysql.SQL;

public class AddFreight extends JFrame implements ActionListener {
	//��������
	JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	JButton jb1,jb2;
	JLabel jlb1,jlb2,jlb3,jlb4,jlb5;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	String[] dataset;
	public AddFreight() {
		// TODO Auto-generated constructor stub
		//�����ֶ�����
		jlb1 = new JLabel("��ֻ����");
		jlb2 = new JLabel("��       ��");
		jlb3 = new JLabel("�ָ�ʱ��");
		jlb4 = new JLabel("����ʱ��");
		jlb5 = new JLabel("ƫ�ò�λ");
		//������ʼ��
		jtf1 =new JTextField(15);
		jtf2 =new JTextField(15);
		jtf3 =new JTextField(15);
		jtf4 =new JTextField(15);
		jtf5 =new JTextField(15);
		//button
		jb1 = new JButton("ȷ������");
		jb2 = new JButton("�˳�����");
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//��ʼ��jpanel
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();
		jp7=new JPanel();
		
		//�����ʵ��
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
		jp2.add(jlb2);
		jp2.add(jtf2);
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
		jp3.add(jlb3);
		jp3.add(jtf3);
		jp4.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
		jp4.add(jlb4);
		jp4.add(jtf4);
		jp5.setLayout(new FlowLayout(FlowLayout.CENTER,25,25));
		jp5.add(jlb5);
		jp5.add(jtf5);
		
		jp6.setLayout(new GridLayout(5, 1, 5, 10));
		jp6.add(jp1);
		jp6.add(jp2);
		jp6.add(jp3);
		jp6.add(jp4);
		jp6.add(jp5);
		//ȷ�Ϻ��˳���ť
		jp7.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
		jp7.add(jb1);
		jp7.add(jb2);
		
		this.setTitle("���Ӵ�ֻ");
		this.setLayout(new BorderLayout());
		this.add(jp6,BorderLayout.CENTER);
		this.add(jp7,BorderLayout.SOUTH);
		this.setSize(600,450);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand()=="ȷ������") {
			dataset = new String[5];
			dataset[0]= jtf1.getText();
			dataset[1]= jtf2.getText();
			dataset[2]= jtf3.getText();
			dataset[3]= jtf4.getText();
			dataset[4]= jtf5.getText();
			if (!dataset[0].isEmpty()&&!dataset[1].isEmpty()) {
				System.out.println(dataset[1]);
				try {
					SQL.connectSql();
					SQL.insertFreight(dataset);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "��ֻ��ŷ�����ͻ�����ݷǷ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					this.clearText();
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "��ֻ��ӳɹ�","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				this.clearText();
				
			}
			else  {
				JOptionPane.showMessageDialog(null, "�����봬ֻ��źʹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
		else if (e.getActionCommand()=="�˳�����") {
			this.dispose();
		}
	}
	
	private void clearText() {
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
		jtf4.setText("");
		jtf5.setText("");
	}
	
//	public static void main(String[] args) {
//		AddFreight add =new AddFreight();
//	}

}
