package locationshare.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import locationshare.common.exception.ReadPropertyException;

public class LocalProperties {

	private static Properties localizeDictionary = null;
	
	private static final String PROPERTIES_FILE_NAME = "locationshare_property_file_name";
	private static final String PATH_PREFIX = "WEB-INF/classes/";
	
	public void init() throws ReadPropertyException {

		if(localizeDictionary==null) {

			try{
				Context ctx = (Context)new InitialContext();
				Context envCtx = (Context)ctx.lookup("java:comp/env");
				
				String propertyFile = (String)envCtx.lookup(PROPERTIES_FILE_NAME);
				
				
				String path  = this.getClass().getClassLoader().getResource("").getFile(); 
				path = URLDecoder.decode(path, "UTF-8"); 
				path = path.substring( 0, path.length()-PATH_PREFIX.length() );				
				path = path+propertyFile;
				
				File file = new File(path);
				
				if(!file.exists()) {

					throw new ReadPropertyException("Initializate properties failure.");
				}

				localizeDictionary = new Properties();
				FileInputStream fin = new FileInputStream(file);
				localizeDictionary.load(fin);
				fin.close();
			}
			catch (Exception ex) {
				localizeDictionary = null;
				throw new ReadPropertyException(
					"Initializate properties failure: " + ex.toString());
			}
		}

	}

	public static synchronized String getProperty(String string)
		throws ReadPropertyException {

		if ( localizeDictionary == null ){
			new LocalProperties().init();
		}

		String result = "";
		String key = "\"" + string + "\"";
		try{
			result = localizeDictionary.getProperty(key);
			result = result.substring(1, result.lastIndexOf("\""));
		}
		catch(StringIndexOutOfBoundsException ex) {
			throw new ReadPropertyException("Can not get property value for key: " + string);
		}
		catch(NullPointerException ex) {
			throw new ReadPropertyException("Can not get property value for key: " + string);
		}
		return result;
	}
	
}