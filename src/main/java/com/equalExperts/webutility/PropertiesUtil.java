package com.equalExperts.webutility;

import java.util.Properties;

/**
 * Created by Thrivikram PRASAD
 */
public class PropertiesUtil {

    public static Properties props=new Properties();

    public PropertiesUtil()
    {
        PropertiesUtil.loadPropertiesFiles();
    }

    public static void loadPropertiesFiles() throws RuntimeException
    {
        try{
            if(props.size() == 0) {
                //String propertiesfile=ConstantsUtil.PROP_FILE_PATH;

                String propertiesFile="application.properties";
                //FileInputStream fs=new FileInputStream(ConstantsUtil.PROP_FILE_PATH);
                //props.load(fs);
                props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFile));
                props.putAll(System.getProperties());

            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static String getProperty(String key)
    {
        return props.getProperty(key);
    }


}
