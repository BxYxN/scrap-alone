package  com.scrap.config;


import com.google.common.collect.ImmutableMap;

public final class ScrapConfig {

   public final static String FOLDER_NAME = "/SCRALONE/";

   public static final ImmutableMap<String, String[]> YAPO =
            new ImmutableMap.Builder<String, String[]>()
                    .put("HOST", new String[]{"https://www.yapo.cl/"})
                    .put("CATEGORIAS",new String[]{"comprar"} )
                    .put("REGIONES",new String[]{"region_metropolitana"} )
                    .put("COMUNAS",new String[]{"336"})
                    .put("PRE",new String[]{"/?cmn=%s&o=%s"} ) //?cmn=[comuna]&o=[pagina]
                    .build();
    
}