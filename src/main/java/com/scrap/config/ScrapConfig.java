package  com.scrap.config;


import com.google.common.collect.ImmutableMap;

public final class ScrapConfig {



   public static final ImmutableMap<String, String[]> YAPO =
            new ImmutableMap.Builder<String, String[]>()
                    .put("HOST", new String[]{"https://www.yapo.cl/"})
                    .put("CATEGORIAS",new String[]{"comprar"} )
                    .put("REGIONES",new String[]{"region_metropolitana"} )
                    .build();
    
}