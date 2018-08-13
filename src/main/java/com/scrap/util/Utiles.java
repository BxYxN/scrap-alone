package com.scrap.util;



import com.scrap.config.ScrapConfig;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bxyxn on 13-08-18.
 */
public class Utiles {

    public static String getHome(){
        return System.getProperty("user.home");
    }

    public static void saveToFile(String data, String name){
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

    public static void createDir(String path){
        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    public static String getInitDir(){
       return  Utiles.getHome() + ScrapConfig.FOLDER_NAME;
    }

    public  static String formatDate (Date fecha , String format){
        return new SimpleDateFormat(format).format(fecha);
    }
}
