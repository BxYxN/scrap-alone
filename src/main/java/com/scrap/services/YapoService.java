package com.scrap.services;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.scrap.config.ScrapConfig;
import com.scrap.dao.PersistenceManager;
import com.scrap.dao.RawPageDao;
import com.scrap.interfaces.CallBackInterfaces;
import com.scrap.model.RawPage;
import com.scrap.util.Utiles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class YapoService {

    private final Logger LOG = Logger.getLogger(YapoService.class.getName());

    public void scrapRaw() throws ResponseException {
        UserAgent userAgent = new UserAgent();

        int page = 0;
        String url = "";
        EntityManager entity = PersistenceManager.INSTANCE.getEntityManager();
        RawPageDao dao = new RawPageDao(entity);
        RawPage raw;

        for (String categoria : ScrapConfig.YAPO.get("CATEGORIAS")) {
            for (String comuna : ScrapConfig.YAPO.get("COMUNAS")) {
                while (page < 2) {
                    url = ScrapConfig.YAPO.get("HOST")[0]
                            + ScrapConfig.YAPO.get("REGIONES")[0]
                            + "/"
                            + categoria
                            + String.format(ScrapConfig.YAPO.get("PRE")[0],
                                    comuna, page);

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

                    page++;
                }
            }
        }
        //Cirre de conexión.
        PersistenceManager.INSTANCE.close();
    }

    public void processRaw() {
        EntityManager entity = PersistenceManager.INSTANCE.getEntityManager();
        RawPageDao dao = new RawPageDao(entity);

        for (RawPage page : dao.getAllRaw()) {
            String str = new String(Base64.getDecoder().decode(page.getRaw()),
                    StandardCharsets.UTF_8);

            System.out.println(str);
        }

        PersistenceManager.INSTANCE.close();
    }

    public void dd() {

        UserAgent userAgent = new UserAgent();

        String fileName = "/home/Bryan/lib/anuncio.html";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            String total = "";

            while ((line = br.readLine()) != null) {
                total = total + line;
            }

            userAgent.openContent(total);
            Element Element = userAgent.doc.findFirst("<table class=listing_thumbs>");

            iterateOverElements(Element.getChildElements(),
                    new CallBackInterfaces() {
                    @Override
                    public void ResolvChild(Element elemento) {

                        List<Element> values = elemento.getChildElements();

                        String date = Utiles.getFirstElementValue(elemento, "<span class=date>");
                        String hora = Utiles.getFirstElementValue(elemento, "<span class=hour>");
                        String url = Utiles.getAt(elemento, "<a class=redirect-to-url","href");
                        String titulo = Utiles.getFirstElementValue(elemento, "<a class=title");
                        String precio = Utiles.getFirstElementValue(elemento, "<span class=price");

                        LOG.log(Level.INFO,date + " " + hora + " " + titulo);


                    }
            });

        } catch (IOException e) {
            LOG.log(Level.SEVERE, null, e);
        } catch (ResponseException ex) {
            LOG.log(Level.SEVERE, "Sin respuesta", ex);
        } catch (NotFound ex) {
            LOG.log(Level.SEVERE, "Objeto no encontrado", ex);
        }

    }

    public void iterateOverElements(List<Element> elementos,
            CallBackInterfaces procc) {
        for (Element ele : elementos) {
            procc.ResolvChild(ele);
        }

    }

}
