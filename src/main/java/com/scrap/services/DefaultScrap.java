package com.scrap.services;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.scrap.config.ScrapConfig;

public class DefaultScrap {

    public void example() throws JauntException {
        UserAgent userAgent = new UserAgent(); // create new userAgent (headless browser)
        System.out.println("SETTINGS:\n" + userAgent.settings);


        for (String categoria : ScrapConfig.YAPO.get("CATEGORIAS")){

            userAgent.visit(ScrapConfig.YAPO.get("HOST")[0] + categoria );

        }


        //System.out.println(userAgent.doc.innerHTML());
    }
}