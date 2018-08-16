package com.scrap.services;

import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.scrap.config.ScrapConfig;
import com.scrap.dao.PersistenceManager;
import com.scrap.dao.RawPageDao;
import com.scrap.model.RawPage;
import com.scrap.util.Utiles;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import javax.persistence.EntityManager;

public class YapoService {


    public void scrapRaw() throws ResponseException{
        UserAgent userAgent = new UserAgent();
        
        int page = 0;
        String url = "";
        EntityManager entity = PersistenceManager.INSTANCE.getEntityManager();
        RawPageDao dao = new RawPageDao(entity);
        RawPage raw;
        
        for (String categoria : ScrapConfig.YAPO.get("CATEGORIAS")){
         for (String comuna : ScrapConfig.YAPO.get("COMUNAS")){
              while (page < 2){
                    url = ScrapConfig.YAPO.get("HOST")[0]
                            + ScrapConfig.YAPO.get("REGIONES")[0]
                            + "/"
                            + categoria +
                            String.format(ScrapConfig.YAPO.get("PRE")[0],
                                    comuna,page);

                    System.out.println(url);
                    userAgent.visit(url);
                    
                    raw = new RawPage();
                    raw.setIdAd(Long.valueOf(page));
                    
                    byte[] data = Base64.getEncoder().encode(
                            userAgent.doc.innerHTML().getBytes());
                    
                    raw.setRaw(data);
                    raw.setDeta("Prueba inicio");
                    dao.SaveRawYapo(raw);
                    /*Utiles.saveToFile(userAgent.doc.innerHTML(),Utiles.getInitDir() +
                            Utiles.formatDate(new Date(),"yyMMddHHmmssZ_" + page));*/

                    page ++;
            } 
         }
        }
        //Cirre de conexión.
        PersistenceManager.INSTANCE.close();
    }
    
    public void processRaw(){
        EntityManager entity = PersistenceManager.INSTANCE.getEntityManager();
        RawPageDao dao = new RawPageDao(entity);
      
        for (RawPage page :dao.getAllRaw()){
            String str = new String(Base64.getDecoder().decode(page.getRaw()), 
                 StandardCharsets.UTF_8);
         
            System.out.println(str);
        }
      
      PersistenceManager.INSTANCE.close();
    }

}