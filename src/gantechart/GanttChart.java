package gantechart;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class GanttChart  {
	
	JFrame frame;
	GanttChartModel gcmodel;
	JTable table;
	JScrollPane scrollPane;
	
	//����ͼ�Ĺ��캯��
	public GanttChart(GanttChartModel _gcmodel)  {
		JFrame frame = new JFrame("Gantt Chart");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		gcmodel = _gcmodel;
		//����JTable��ʾռ��
		JTable table = new JTable(gcmodel.getData(), gcmodel.getColumnNames());
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(1000,200));
		
		TableColumn column = null;
		for (int i = 0; i <= gcmodel.getEndTime(); i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i >= 1) {
		        column.setPreferredWidth(2); //third column is bigger
		    } else {
		        column.setPreferredWidth(150);
		    }
		}
		
		frame.setContentPane(scrollPane);
		//��Ⱦ������ɫ
		GanttChartRenderer gcRenderer = new GanttChartRenderer(gcmodel);
		table.setDefaultRenderer(Object.class, gcRenderer);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	
	public static void main(String args[]){
		/*JFrame frame = new JFrame("Gantt Chart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		GanttChartModel gcModel = new GanttChartModel();
		gcModel.addTask("1�Ų�λ", 1, 2);
		gcModel.addTask("2�Ų�λ", 3, 1);
		gcModel.addTask("3�Ų�λ", 1, 2);
		gcModel.addTask("4�Ų�λ", 4, 12);
		gcModel.addTask("5�Ų�λ", 5, 32);
		GanttChart gc = new GanttChart(gcModel);
		
		/*
		JTable table = new JTable(gcModel.getData(), gcModel.getColumnNames());
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(1000,500));
		
		
		TableColumn column = null;
		for (int i = 0; i <= gcModel.getEndTime(); i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i >= 1) {
		        column.setPreferredWidth(2); //third column is bigger
		    } else {
		        column.setPreferredWidth(150);
		    }
		}
		
		
		frame.setContentPane(scrollPane);
		GanttChartRenderer gcRenderer = new GanttChartRenderer(gcModel);
		table.setDefaultRenderer(Object.class, gcRenderer);
		frame.pack();
		frame.setVisible(true);
		*/
	}

}
