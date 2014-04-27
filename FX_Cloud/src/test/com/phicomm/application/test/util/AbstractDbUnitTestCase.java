package com.phicomm.application.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;






import junit.framework.Assert;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.xml.sax.InputSource;

@SuppressWarnings("deprecation")
public class AbstractDbUnitTestCase {
	public static IDatabaseConnection dbunitCon;
	//临时文件，用来存储数据库的备份数据
	private File tempFile;
	//临时文件，用来存储数据库的dtd文件
	private File dtdFile;
	
	@BeforeClass
	public static void init() throws DatabaseUnitException, SQLException {
		//初始化IDatabaseConnection
		dbunitCon = new DatabaseConnection(DbUtil.getConnection());
	}
	
	//根据文件名称创建DataSet对象
	protected IDataSet createDateSet(String tname) throws DataSetException, FileNotFoundException, IOException {
		InputStream is = AbstractDbUnitTestCase
					.class
					.getClassLoader().getResourceAsStream(tname+".xml");
		Assert.assertNotNull("dbunit的基本数据文件不存在",is);
		//通过dtd和传入的文件创建测试的IDataSet
		return new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is),
				new FlatDtdDataSet(new InputStreamReader(new FileInputStream(dtdFile),"utf-8"))));
	}
	
	//备份数据库的所有表
	protected void backupAllTable() throws SQLException, IOException, DataSetException {
		IDataSet ds = dbunitCon.createDataSet();
		writeBackupFile(ds);
	}
	
	//将表写到临时文件中
	private void writeBackupFile(IDataSet ds) throws IOException, DataSetException {
		tempFile = File.createTempFile("back", "xml");
		dtdFile = File.createTempFile("back","dtd");
		//写dtd
		FlatDtdDataSet.write(ds, new FileWriterWithEncoding(dtdFile,"utf-8"));
		//写数据表中的文件
		FlatXmlDataSet.write(ds, new FileWriterWithEncoding(tempFile,"utf-8"));
	}
	//备份指定表
	protected void backupCustomTable(String[] tname) throws DataSetException, IOException {
		QueryDataSet ds = new QueryDataSet(dbunitCon);
		for(String str:tname) {
			ds.addTable(str);
		}
		writeBackupFile(ds);
	}
	//备份一张表
	protected void bakcupOneTable(String tname) throws DataSetException, IOException {
		backupCustomTable(new String[]{tname});
	}
	//还原备份的表
	protected void resumeTable() throws DatabaseUnitException, SQLException, IOException {
		//创建ds的时候引入相应的dtd
		IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(
					new InputSource(new FileInputStream(tempFile)),
					new FlatDtdDataSet(new InputStreamReader(new FileInputStream(dtdFile),"utf-8"))));
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
	}
	
	//清空数据
	@AfterClass
	public static void destory() {
		try {
			if(dbunitCon!=null) dbunitCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
