package com.scrap.services;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.scrap.config.ScrapConfig;
import com.scrap.util.Utiles;
import java.util.Date;

public class DefaultScrap {

    public void example() throws JauntException {

        UserAgent userAgent = new UserAgent(); // create new userAgent (headless browser)

        Utiles.createDir(Utiles.getInitDir());

        //System.out.println("SETTINGS:\n" + userAgent.settings);

        int page = 0;
        String url = "";

        for (String categoria : ScrapConfig.YAPO.get("CATEGORIAS")){

          while (page < 9){
              url = ScrapConfig.YAPO.get("HOST")[0]
                      + ScrapConfig.YAPO.get("REGIONES")[0]
                      + "/"
                      + categoria +
                      String.format(ScrapConfig.YAPO.get("PRE")[0],"336",page);

              System.out.println(url);
              userAgent.visit(url);
              Utiles.saveToFile(userAgent.doc.innerHTML(),Utiles.getInitDir() +
                      Utiles.formatDate(new Date(),"yyMMddHHmmssZ_" + page));

              page ++;
          }

        }
    }
}