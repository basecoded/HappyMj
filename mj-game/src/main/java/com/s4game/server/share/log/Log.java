package com.s4game.server.share.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年9月8日 上午10:34:59
 *
 */

public class Log {
    
    public static final String ERROR_LOGGER = "error_logger";
    public static final String CONFIG_LOGGER = "config_logger";

    public static final String SERVER_STATUS_LOGGER = "server_status_logger";
    
    
    
    public static Logger LOG = LoggerFactory.getLogger(ERROR_LOGGER);
    
    public static Logger PUBLIC = LoggerFactory.getLogger("public");
    
    public static Logger ROOM = LoggerFactory.getLogger("room");
    
    public static Logger CARD = LoggerFactory.getLogger("card");
    
    
    
    public static void error(String message, Object... params) {
        LOG.error(message, params);
    }

}
