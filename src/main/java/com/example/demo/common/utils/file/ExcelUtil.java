package com.example.demo.common.utils.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

/**
 * 导出Excel文档工具类
 */
@SuppressWarnings("all")
public class ExcelUtil {
	public static final String XLS_PATY = "F://QDReport//moban.xls";

	/**
	 * 创建excel文档，
	 * 
	 * @param list        数据
	 * @param keys        list中map的key数组集合
	 * @param columnNames excel的列名
	 */
	public static Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String columnNames[]) {
		// 创建excel工作簿
		Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		// 创建第一行
		Row row = sheet.createRow((short) 0);

		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();

		// 创建两种字体
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());

		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());

		// Font f3=wb.createFont();
		// f3.setFontHeightInPoints((short) 10);
		// f3.setColor(IndexedColors.RED.getIndex());

		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);

		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);

		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
		// 设置每行每列的值
		for (short i = 1; i < list.size(); i++) {
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow((short) i);
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
		}
		return wb;
	}

	/**
	 * 创建excel文档，然后导出相应的数据
	 * 
	 * @param list   查出的数据结果。
	 * @param params 导出的各个参数（fileName文件名、sheetName表名、textName大标题、cols列名list、fields字段list）
	 * @param
	 * @param
	 */
	public static void Export(List<Map<String, Object>> list, Map<String, Object> params, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(params)) {
			return;
		}
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// resp.setContentType("application/vnd.ms-excel");
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet(params.get("sheetName").toString());
		List<String> cols = (List<String>) params.get("cols");// 表头列名
		List<String> fields = (List<String>) params.get("fields");// 表头对应属性字段
		// 设置列宽的长度
		// sheet.setDefaultColumnWidth((short)15);
		for (int i = 0; i < cols.size(); i++) {
			sheet.setColumnWidth(i, 40 * 256);
		}
		// 生成一个样式
		// HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle headerStyle2 = (HSSFCellStyle) wb.createCellStyle();
		// 样式字体居中
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		headerStyle2.setFont(font);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (30 * 20));// 大标题设置
		HSSFCell c4 = row.createCell(0);
		c4.setCellValue(new HSSFRichTextString(params.get("textName").toString()));
		c4.setCellStyle(headerStyle2);
		HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();
		// 样式字体居中
		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 12);
		headerStyle.setFont(font1);

		HSSFRow row1 = sheet.createRow(1);
		// 给表头第一行一次创建单元格

		HSSFCell cell;
		for (int i = 0; i < cols.size(); i++) {
			cell = row1.createCell((short) i);
			cell.setCellValue(new HSSFRichTextString(cols.get(i)));
			cell.setCellStyle(headerStyle2);
		}
		HSSFCellStyle cell_Style = (HSSFCellStyle) wb.createCellStyle();
		cell_Style.setWrapText(true); // 设置为自动换行
		HSSFFont cell_Font = (HSSFFont) wb.createFont();
		cell_Font.setFontName("宋体");
		cell_Font.setFontHeightInPoints((short) 10);
		cell_Style.setFont(cell_Font);
		// 向单元格里填充数据
		for (short i = 1; i <= list.size(); i++) {
			row1 = sheet.createRow(i + 1);
			HSSFCell c1;
			for (int j = 0; j < fields.size(); j++) {
				c1 = row1.createCell(j);
				c1.setCellValue(list.get(i - 1).get(fields.get(j)).toString());
				c1.setCellStyle(cell_Style);
			}
		}
		try {
			String fileName = new String((params.get("fileName") + ".xls").getBytes("UTF-8"), "iso8859-1");
			resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 导出ping日志记录
	 * 
	 * @param list
	 * @param req
	 * @param resp
	 */
	public static void ExportPing(List<Map<String, Object>> list, HttpServletRequest req, HttpServletResponse resp) {
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet("ping日志记录表");

		// 设置列宽的长度
		// sheet.setDefaultColumnWidth((short)15);
		sheet.setColumnWidth(0, 40 * 256);
		sheet.setColumnWidth(1, 30 * 256);
		sheet.setColumnWidth(2, 30 * 256);
		sheet.setColumnWidth(3, 30 * 256);
		sheet.setColumnWidth(4, 30 * 256);
		sheet.setColumnWidth(5, 30 * 256);
		sheet.setColumnWidth(6, 30 * 256);
		// 生成一个样式
		// HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle headerStyle2 = (HSSFCellStyle) wb.createCellStyle();
		// 样式字体居中
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		headerStyle2.setFont(font);
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) (30 * 20));// 大标题设置
		HSSFCell c21 = row.createCell(0);
		c21.setCellValue(new HSSFRichTextString("ping记录"));
		c21.setCellStyle(headerStyle2);
		HSSFCellStyle headerStyle = (HSSFCellStyle) wb.createCellStyle();
		// 样式字体居中
		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 12);
		headerStyle.setFont(font1);

		HSSFRow row1 = sheet.createRow(1);
		// 给表头第一行一次创建单元格
		HSSFCell cell = row1.createCell((short) 0);
		cell.setCellValue(new HSSFRichTextString("主机ip"));
		cell.setCellStyle(headerStyle2);
		cell = row1.createCell((short) 1);
		cell.setCellValue(new HSSFRichTextString("发送包数量"));
		cell.setCellStyle(headerStyle2);
		cell = row1.createCell((short) 2);
		cell.setCellValue(new HSSFRichTextString("丢失包数量"));
		cell.setCellStyle(headerStyle2);
		cell = row1.createCell((short) 3);
		cell.setCellValue(new HSSFRichTextString("丢包率(%)"));
		cell.setCellStyle(headerStyle2);
		cell = row1.createCell((short) 4);
		cell.setCellValue(new HSSFRichTextString("平均响应时间(单位：毫秒)"));
		cell.setCellStyle(headerStyle2);
		cell = row1.createCell((short) 5);
		cell.setCellValue(new HSSFRichTextString("记录时间"));
		cell.setCellStyle(headerStyle2);

		HSSFCellStyle cell_Style = (HSSFCellStyle) wb.createCellStyle();
		cell_Style.setWrapText(true); // 设置为自动换行
		HSSFFont cell_Font = (HSSFFont) wb.createFont();
		cell_Font.setFontName("宋体");
		cell_Font.setFontHeightInPoints((short) 10);
		cell_Style.setFont(cell_Font);
		// 向单元格里填充数据
		for (short i = 1; i <= list.size(); i++) {
			row1 = sheet.createRow(i + 1);
			HSSFCell c1 = row1.createCell(0);
			c1.setCellValue(list.get(i - 1).get("host_ip").toString());
			c1.setCellStyle(cell_Style);
			HSSFCell c2 = row1.createCell(1);
			c2.setCellValue(list.get(i - 1).get("send_num").toString());
			c2.setCellStyle(cell_Style);
			HSSFCell c3 = row1.createCell(2);
			c3.setCellValue(list.get(i - 1).get("lose_num").toString());
			c3.setCellStyle(cell_Style);
			HSSFCell c4 = row1.createCell(3);
			c4.setCellValue(list.get(i - 1).get("lose_percent").toString());
			c4.setCellStyle(cell_Style);
			HSSFCell c5 = row1.createCell(4);
			c5.setCellValue(list.get(i - 1).get("avg_response_time").toString());
			c5.setCellStyle(cell_Style);
			HSSFCell c6 = row1.createCell(5);
			c6.setCellValue(list.get(i - 1).get("updatetime").toString());
			c6.setCellStyle(cell_Style);
		}
		try {
			String fileName = new String("ping记录表.xls".getBytes("gbk"), "iso8859-1");
			resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 判断有无，没有新建
	 * 
	 * @param cell
	 * @param row
	 * @param i
	 * @return
	 */
	public static Cell checkCell(Cell cell, Row row, int i) {
		if (cell == null) {
			cell = row.createCell(i);
		}
		return cell;
	}

}