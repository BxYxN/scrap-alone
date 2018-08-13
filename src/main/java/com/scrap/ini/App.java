package com.scrap.ini;
import com.jaunt.JauntException;
import com.scrap.services.DefaultScrap;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        DefaultScrap def = new DefaultScrap();
        try {
			def.example();
		} catch (JauntException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
