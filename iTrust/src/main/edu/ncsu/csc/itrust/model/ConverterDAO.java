package edu.ncsu.csc.itrust.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * This class pulls the JDBC driver information from Tomcat's context.xml file
 * in WebRoot/META-INF/context.xml. This is done only for convenience - so that
 * you only have to pull your JDBC info from one place (context.xml)<br />
 * <br />
 * The tangled mess you see here is SAX, the XML-parser and XPath, an XML
 * querying language. Note that this class is only ever constructed once since
 * DAOFactory only constructs up to 2 instances of itself.<br />
 * <br />
 * Also, you'll notice that we're using a "BasicDataSource" to obtain
 * connections instead of the usual DriverManager. That's because we're using
 * Tomcat's built-in database pooling mechanism. It's purely for performance in
 * this case.
 */
public class ConverterDAO {


	private static String getAttribute(Document document, String attribute) throws XPathExpressionException {
		return (String) XPathFactory.newInstance().newXPath().compile("/Context/Resource/" + attribute)
				.evaluate(document.getDocumentElement(), XPathConstants.STRING);
	}

	private static Document parseXML(BufferedReader reader) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(reader));
	}

	public static DataSource getDataSource() {
		FileReader f = null;
		BufferedReader r = null;
		BasicDataSource ds = null;
		try {
			f = new FileReader("WebRoot/META-INF/context.xml");
			r = new BufferedReader(f);
			Document document = parseXML(r);
			ds = new BasicDataSource();
			ds.setDriverClassName(getAttribute(document, "@driverClassName"));
			ds.setUsername(getAttribute(document, "@username"));
			ds.setPassword(getAttribute(document, "@password"));
			ds.setUrl(getAttribute(document, "@url"));
			ds.setMaxTotal(15);
			
			ds.setPoolPreparedStatements(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				r.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			finally{
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		return ds;
	}
	
}
