package application;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.report.builder.style.*;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;

public class SampleReport {

	
	static StyleBuilder boldStyle = DynamicReports.stl.style().bold();
	static StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle)
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
	static StyleBuilder columnTitleStyle = DynamicReports.stl.style(
			Templates.boldCenteredStyle).setBorder(
			DynamicReports.stl.pen1Point());
	static StyleBuilder titleStyle = DynamicReports.stl.style(boldStyle)
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
	
	
	public static void showTheReports()
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Book_Store", "root", "");

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		
		//The total sales for books in the previous month
		String sql1 = "select Book_Name,sum(Selling_Price) " 
				+" from sales "
				+" where Buy_Time>=(NOW() - INTERVAL 1 MONTH) "
				+" Group by Book_Name;";

		//The top 5 customers who purchase the most purchase amount in descending order for the last three months
		String sql2 = "SELECT Customer_Name,sum(Selling_Price)"
				+"FROM sales "
				+"where Buy_Time>=(NOW() - INTERVAL 3 MONTH)"
				+"Group by Customer_Name "
				+"ORDER BY sum(Selling_Price) DESC LIMIT 5;";

		
		//The top 10 selling books for the last three months  
		String sql3 = "SELECT Book_Name,sum(Quantity) "
				+"FROM sales "
				+"where Buy_Time>=(NOW() - INTERVAL 3 MONTH)"
				+"Group by Book_Name "
				+"ORDER BY sum(Quantity) DESC LIMIT 10;";

		showReportTotalSales(sql1, conn);
		showReportTopFiveCustomers(sql2, conn);
		showReportTopTenSellingBooks(sql3, conn);
	}
	
	public static void main(String[] args) {
		showTheReports();
	}

	public static JasperReportBuilder showReportTopFiveCustomers(String sqlStmt, Connection conn) {
		JasperReportBuilder report = DynamicReports.report();
				report.columns(
				Columns.column("Customer_Name", "Customer_Name", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER)
				,Columns.column("sum(Selling_Price)", "sum(Selling_Price)",DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.CENTER))
				.title(Templates.createTitleComponent("The top 5 customers who purchase the most purchase amount in descending order for the last three months"))
				.pageFooter(Templates.footerComponent)
				.setDataSource(sqlStmt, conn)
				.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();



		try {
			report.show(false);
		} catch (DRException e) {
			e.printStackTrace();
		}
		return report;
	}
	
	
	public static JasperReportBuilder showReportTopTenSellingBooks(String sqlStmt, Connection conn) {
		JasperReportBuilder report = DynamicReports.report();

		report.columns(
				Columns.column("Book_Name", "Book_Name", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER),
				Columns.column("sum(Quantity)", "sum(Quantity)",DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.CENTER))
				.title(Templates.createTitleComponent("The top 10 selling books for the last three months"))
				.pageFooter(Templates.footerComponent)
				.setDataSource(sqlStmt, conn)
				.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();

		try {
			// show the report
			report.show(false);
		} catch (DRException e) {
			e.printStackTrace();
		}
		return report;
	}

	public static JasperReportBuilder showReportTotalSales(String sqlStmt, Connection conn) {
		JasperReportBuilder report = DynamicReports.report();

		
		report.columns(
				Columns.column("Book_Name", "Book_Name", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.CENTER),
				Columns.column("Total Selling Price", "sum(Selling_Price)", DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.CENTER))
				.title(Templates.createTitleComponent("The total sales for books in the previous month"))
				.pageFooter(Templates.footerComponent)
				.setDataSource(sqlStmt, conn)
				.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
		try {
			report.show(false);
		} catch (DRException e) {
			e.printStackTrace();
		}
		return report;
	}

	private static JasperReportBuilder createSubreport() {
		JasperReportBuilder report = report();
		report.setTemplate(Templates.reportTemplate)
				.title(cmp.text("SubData")
						.setStyle(Templates.boldCenteredStyle))
				.columns(
						col.column("Order date", "orderDate", type.dateType()),
						col.column("Quantity", "quantity", type.integerType()));

		return report;
	}
}
