package userinterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mysql.SQL;

@SuppressWarnings("serial")
public class ManualDistribute extends JFrame implements ActionListener {
	// ��������
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
	JButton jb1, jb2;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
	String freightNumber, portNumber,arriveTime,timeLong;

	//
	public ManualDistribute() {
		// TODO Auto-generated constructor stub
		// new��ť
		jb1 = new JButton("ȷ�Ϸ���");
		jb2 = new JButton("ȡ������");

		// ��ǩ
		jlb1 = new JLabel("���봬ֻ��");
		jlb2 = new JLabel("���벴λ��");
		jlb3 = new JLabel("��������");

		// �ı������
		jtf1 = new JTextField(8);
		jtf2 = new JTextField(8);

		// ���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();

		// ��ֻ������
		jp1.add(jlb1);
		jp1.add(jtf1);
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		// ��λ������
		jp2.add(jlb2);
		jp2.add(jtf2);
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

		// ִ�а�ť
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

		jp4.add(jp1);
		jp4.add(jp2);

		this.setLayout(new BorderLayout());
		// this.add(jlb3, BorderLayout.NORTH);
		this.add(jp3, BorderLayout.SOUTH);
		this.add(jp4, BorderLayout.CENTER);
		this.setTitle("�ֶ����䴬��");
		this.setSize(300, 200);
		this.setLocation(600, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "ȡ������") {
			this.clear();
		} else if (e.getActionCommand() == "ȷ�Ϸ���") {
			freightNumber = jtf1.getText();
			portNumber = jtf2.getText();
			try {
				SQL.connectSql();
				SQL.queryPort(portNumber);
				SQL.queryFreight(freightNumber);
				if (SQL.rs5.next()&& SQL.rs2.next()) {
					System.out.println(SQL.rs5.getString(2));
					System.out.println(SQL.rs5.getString(2)=="'��'");
//					if (SQL.rs5.getString(2)=="'��'") {
						arriveTime = SQL.rs2.getString(3);  
						timeLong = SQL.rs2.getString(4);
						SQL.updatePort(portNumber,freightNumber,arriveTime,timeLong);
//					}
//					else {
//						JOptionPane.showMessageDialog(null, "�ò�λ��ռ��","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
//					}
						JOptionPane.showMessageDialog(null, "����ɹ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				}
				else  {
					JOptionPane.showMessageDialog(null, "�ò�λ��ô�ֻ������","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void clear() {
		jtf1.setText("");
		jtf2.setText("");
	}

	public static void main(String[] args) {
		new ManualDistribute();
	}
}
