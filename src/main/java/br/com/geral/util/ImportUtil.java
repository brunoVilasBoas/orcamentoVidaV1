package br.com.geral.util;

import java.io.InputStream;
import java.util.Properties;

public class ImportUtil {

	public static String retornarCaminhoCSV() {
		
		Properties props = new Properties();
		
		try (InputStream is =
        		ImportUtil.class.getClassLoader()
                          .getResourceAsStream("config.properties")) {

            if (is == null) {
            	throw new RuntimeException("config.properties não encontrado!");
            }

            props.load(is);
            
            return props.getProperty("csv.path");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
	}	
}
