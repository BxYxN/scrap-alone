package com.scrap.util;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.scrap.config.ScrapConfig;
import com.scrap.interfaces.CallBackInterfaces;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bxyxn on 13-08-18.
 */
public class Utiles {

    public static String getHome() {
        return System.getProperty("user.home");
    }

    public static void saveToFile(String data, String name) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name + ".html"), "utf-8"))) {
            try {
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDir(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static String getInitDir() {
        return Utiles.getHome() + ScrapConfig.FOLDER_NAME;
    }

    public static String formatDate(Date fecha, String format) {
        return new SimpleDateFormat(format).format(fecha);
    }

    public static Element getFirstElement(Element elemento, String expresion) {
        try {

            if (elemento == null) {
                return null;
            }

            return elemento.findFirst(expresion);

        } catch (NotFound ex) {

            return null;
        }
    }
    
    
    public static String getAt(Element elemento, String expresion, String at){
    
        Element el = getFirstElement(elemento, expresion);
        
        if (el != null) {
            try {
                return el.getAt(at);
            } catch (NotFound ex) {
                 
            }
        }

       return "";
    }

    public static String getFirstElementValue(Element elem, String expresion) {

        Element el = getFirstElement(elem, expresion);
        
        if (el != null) {
            return el.getChildText();
        }

        return "";

    }

}
