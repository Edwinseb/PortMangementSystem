package mysql;

import java.nio.charset.UnmappableCharacterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import com.mysql.jdbc.PreparedStatement;

import algorithm.DateCalculate;
import userinterface.StringToDatetime;

public class SQL {
	static Connection con = null;
	// ����������
	static final String driver = "com.mysql.jdbc.Driver";
	// Ҫ���ʵ����ݿ��ַ
	static final String url = "jdbc:mysql://cdb-lgvkfdom.bj.tencentcdb.com:10094/portManagement?useSSL=false&useOldAliasMetadataBehavior=true";
	// ���ݿ��û���
	static final String username = "root";
	// ���ݿ�����
	static final String password = "tjy1998217";
	// sql���
	static java.sql.PreparedStatement ps = null;
	// ���ݿⷵ�ؽ��
	public static ResultSet rs, rs1, rs2, rs3, rs4,rs5, rs6,rs7= null;
	// ����Ա�˺�����
	public static String pwd = null;
	static String unm = null;

	// ���ݿ����ӷ���
	public static void connectSql() throws SQLException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			// System.out.println(con.isClosed());
			if (!con.isClosed()) {
				System.out.println("connection success");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ����Ա�˺ŵ�¼
	public static void queryAdmin(String user, String pass) {
		try {
			ps = con.prepareStatement("select * from adminInfo where ����Ա�˺� =? and ���� =?");
			ps.setString(1, user);
			ps.setString(2, pass);
			// ��ѯ�����
			rs = ps.executeQuery();

			// �����ѯ����ؼ�¼��������ȷ
			if (rs.next()) {
				// ��ù���Ա�˺ź�����
				unm = rs.getString(1);
				pwd = rs.getString(2);
				System.out.println("��½�ɹ�");
				// System.out.println(unm+pwd);
			} else {
				JOptionPane.showMessageDialog(null, "���벻��ȷ���޴��û�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��λ��Ϣ��ѯ
	public static void queryPort() {
		try {
			ps = con.prepareStatement("select * from portInfo");
			rs1 = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//���ݲ�λ�Ų�ѯ��λ��Ϣ
	public static void queryPort(String portNumber) {
		try {
			ps = con.prepareStatement("select * from portInfo where ��λ�� =?");
			ps.setString(1, portNumber);
			rs5 = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ��ֻ��Ϣ��ѯ
	public static void queryFreight(String freightcode) {
		try {
			ps = con.prepareStatement("select * from freightInfo where ��ֻ���� =? ");
			ps.setString(1, freightcode);
			rs2 = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ѯ���д�ֻ��Ϣ
	public static void queryFreight() {
		// TODO Auto-generated method stub
		try {
			ps = con.prepareStatement("select * from freightInfo");
			rs3 = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �����´�ֻ
	public static void insertFreight(String[] insertinfo) {
		try {
			ps = con.prepareStatement(
					"INSERT INTO `freightInfo`(`��ֻ����`, `����`, `�ִ�ۿ�ʱ��`, `����ʱ��`, `ƫ�ò�λ`) VALUES (?,?,?,?,?)");
			java.sql.Date[] date = new java.sql.Date[2];
			date[0] = StringToDatetime.transStringToDatetime(insertinfo[2]);
			for (int i = 0; i < insertinfo.length; i++) {
				if (i == 2 ) {
					ps.setDate(i+1,  (java.sql.Date) date[0]);
					System.out.println(date[0]);
					continue;
				}				
				ps.setString(i + 1, insertinfo[i]);
			}
			System.out.println(ps);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "��ֻ��ŷ�����ͻ�����ݷǷ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);

		}
	}

	// �޸Ĵ�ֻ��Ϣ
	public static void updateFreight(String val, String sid, String col) {
		// System.out.println(val+sid+col);
		java.sql.Date val1;
		int i = 0;

		try {
			// ����޸ĵ���ʱ�� ��string����ת��
			if (col == "�ִ�ۿ�ʱ��") {
				val1 = StringToDatetime.transStringToDatetime(val);
				ps = con.prepareStatement("UPDATE freightInfo SET " + col + "=?  WHERE ��ֻ����=" + sid);
				ps.setDate(1, val1);
				System.out.println(ps);
				i = 1;
			} else if (i == 0) {
				ps = con.prepareStatement("UPDATE freightInfo SET " + col + "=" + val + " WHERE ��ֻ����=" + sid);
			}
			// System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ɾ����ֻ��Ϣ
	public static void deleteFreight(String sid) {
		try {
			ps = con.prepareStatement("DELETE FROM freightInfo WHERE ��ֻ����=? ");
			ps.setString(1, sid);
			// System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ���Ҳ�λ���еļ�¼�������ٸ���λ��
	public static void queryNumberofPort() {
		try {
			ps = con.prepareStatement("SELECT COUNT(*) AS NUM FROM portInfo");
			rs4 = ps.executeQuery();
			// System.out.println(rs4.getString("NUM") + "123");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ��λ���в����¼���Զ����Ӳ�λ����
	public static void increasPortNumber(int i) {
		try {
			ps = con.prepareStatement(
					"INSERT INTO `portInfo`(`��λ��`, `�Ƿ��п�`, `��ֻ����`, `�벴λʱ��`, `���ʱ��`) VALUES (?,?,?,?,?) ");
			ps.setInt(1, i);
			ps.setString(2, "��");
			ps.setDate(3, null);
			ps.setDate(4, null);
			ps.setString(5, null);
			// System.out.println(ps);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���ٲ�λ��
	public static void decreasePortNumber(int i) {
		try {
			ps = con.prepareStatement("DELETE FROM portInfo WHERE ��λ��=?");
			ps.setInt(1, i);
			// System.out.println(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//�ֶ����䲴λ
	public static void updatePort(String portNumber, String freightNumber, String arriveTime ,String timeLong) {
		try {
			ps = con.prepareStatement("UPDATE `portInfo` SET `�Ƿ��п�`='��',`��ֻ����`=?,`�벴λʱ��`=?,`���ʱ��`=? WHERE `��λ��`=? ");
			ps.setString(1, freightNumber);
			ps.setString(2, arriveTime);
			ps.setString(3, DateCalculate.dateAdd(arriveTime, Integer.valueOf(timeLong)));
			ps.setString(4, portNumber);
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	//�Զ�������ͷ
	public static void autoDistribute() {
		try {
			//�ֱ��ѯ�ղ�λ��δ����Ĵ�ֻ
			
			ps = con.prepareStatement("select * from freightInfo left join portInfo on freightInfo.��ֻ����= portInfo.��ֻ���� where portInfo.��ֻ���� is null  ");
			rs6= ps.executeQuery();
			ps = con.prepareStatement("select ��λ�� from portInfo where ��ֻ���� is null  ");
			rs7 = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
