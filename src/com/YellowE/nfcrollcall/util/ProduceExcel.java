package com.YellowE.nfcrollcall.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.content.Context;

import com.YellowE.nfcrollcall.Config;
import com.YellowE.nfcrollcall.bean.RollCallRecord;
import com.YellowE.nfcrollcall.bean.Student;
import com.YellowE.nfcrollcall.dao.RollCallRecordDao;
import com.YellowE.nfcrollcall.dao.StudentDao;

public class ProduceExcel {

	public static void writeExcel(Context cxt, String courseID) {
		String fileName = Config.FILE_PATH + courseID + ".xls";
		List<Student> students = new StudentDao(cxt).selcetDataByCourseID(Integer.parseInt(courseID));
		RollCallRecordDao rcDao = new RollCallRecordDao(cxt);
		List<RollCallRecord> rcRecords = null;
		
		WritableWorkbook wwb = null;
		try {
			// 创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 第一个参数是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			
			//插入表头
			try {
				ws.addCell(new Label(0, 0, "学号"));
				ws.addCell(new Label(1, 0, "姓名"));
			} catch (RowsExceededException e2) {
				e2.printStackTrace();
			} catch (WriteException e2) {
				e2.printStackTrace();
			}
			
			// 在指定单元格插入数据
			int i = 1;
			int times = 0;
			Label l1 = null;
			Label l2 = null;
			Student student = null;

			Iterator<Student> itStu = students.iterator();
			while (itStu.hasNext()) {
				student = itStu.next();
				l1 = new Label(0, i, student.getStudentNum());
				l2 = new Label(1, i, student.getStudentName());
				
				try {
					ws.addCell(l1);
					ws.addCell(l2);
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
				
				rcRecords = rcDao.selcetData(courseID, student.getId()+"");
				
				Iterator<RollCallRecord> itRc = rcRecords.iterator();
				
				while (itRc.hasNext()) {
					RollCallRecord rcRecord = (RollCallRecord) itRc.next();
					try {
						ws.addCell(new Label(rcRecord.getRollcallTimes() + 1, i, rcRecord.getStatus()+""));
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
					if(i == 1)
						times++;
					
				}
				
				i++;

			}
			
			for (int j = 0; j < times; j++) {
				try {
					ws.addCell(new Label(j+2, 0, "第"+ (j+1) +"次"));
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}

			try {
				// 从内存中写入文件中
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

}
