package userinterface;
/**
 * @author TAN
 */

import javax.swing.*;

import mysql.SQL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
	JButton jb1, jb2, jb3 = null;
	JRadioButton jrb1, jrb2 = null;
	JPanel jp1, jp2, jp3, jp4 = null;
	JTextField jtf = null;
	JLabel jlb1, jlb2, jlb3 = null;
	JPasswordField jpf = null;
	ButtonGroup bg = null;
	PortInfo portInfo;
	String look;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
	//��¼���湹�캯��
	public Login() {
		// �����¼�˳���ť
		jb1 = new JButton("��¼");
		jb2 = new JButton("�˳�");
		jb3 = new JButton("����");
		// ���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();

		jlb1 = new JLabel("��   ��: ");
		jlb2 = new JLabel("��   ��: ");
		// ��¼�����˺�����
		jtf = new JTextField(16);
		jpf = new JPasswordField(16);
		// ��������뵽panel��
		jp1.add(jlb1);
		jp1.add(jtf);

		jp2.add(jlb2);
		jp2.add(jpf);

		jp4.add(jb1);
		jp4.add(jb3);
		jp4.add(jb2);
		// ���뵽Jframe��
		this.add(jp1);
		this.add(jp2);
		this.add(jp4);

		setLayout(new GridLayout(3, 1, 2, 5));
		//���ô���UI���
		look =  "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(look);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("�ۿڹ���ϵͳ��¼");// ���ô�������
		setSize(500, 200);// ���ô��ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);// ���ùرմ��ں����˳�JVM
		setVisible(true);
		setLocation(200, 300);// ���ô���λ��
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "�˳�") {
			System.exit(0);
		} else if (e.getActionCommand() == "����") {
			this.clear();
		} else if (e.getActionCommand() == "��¼") {
			// ���������Ĵ���
			if (!jtf.getText().isEmpty() && !jpf.getText().isEmpty()) {
				try {
					// �������ݿ�
					SQL.connectSql();
					// ִ�й���Ա�˺ŵĲ���
					SQL.queryAdmin(jtf.getText(), jpf.getText());
					// ����˺Ų�ƥ�����������
					if (SQL.pwd == null) {
						this.clear();
					} else {
						this.adminLogin();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// ���벻�����Ĵ���
			else if (jtf.getText().isEmpty() || jpf.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "�������˺ź�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				this.clear();
			}
		}
	}

	// ���������
	public void clear() {
		jtf.setText("");
		jpf.setText("");
	}

	public void adminLogin() {
		this.clear();
		dispose();
		portInfo = new PortInfo();
	}
}
