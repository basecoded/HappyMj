package com.s4game.core.data.accessor;



/**
 * @author zeusgooogle
 * @date 2015-05-2 下午08:01:58
 */
public class AccessType {

    private static final String DIRECT = "direct";

    private static String ROLECACHE = "roleCache";

    private static String PUBLICCACHE = "publicCache";

    private static final String CONFIGURECACHE = "configure";

    public AccessType() {
    }

    public static String getDirectDbType() {
        return DIRECT;
    }

    public static String getRoleCacheDbType() {
        return ROLECACHE;
    }

    public static String getPublicCacheDbType() {
        return PUBLICCACHE;
    }

    public static String getConfigureCacheDbType() {
        return CONFIGURECACHE;
    }

    /**
     * 可以配置，是否开启缓存，默认开启。
     * 
     * 
     * 如果不开启缓存：   ROLECACHE = DIRECT
     *               PUBLICCACHE = DIRECT
     */

}
