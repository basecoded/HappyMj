package com.s4game.server.start;

import java.io.File;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.server.share.ApplicationInit;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月6日 上午10:28:32
 *
 */

public class ServerStart {

    public static final Logger LOG = LoggerFactory.getLogger(ServerStart.class);
	
	public static void main(String[] args) {
		
		ApplicationInit.init();

		rememberPid();
		
		LOG.info("server started success.");
	}
	
	private static void rememberPid() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		String value = runtimeMXBean.getName();
		try {
			String pid = value.substring(0, value.indexOf('@'));
			File file = new File(ClassLoader.getSystemResource("").getPath(), "pid");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter localFileWriter = new FileWriter(file);
			localFileWriter.write(pid);
			localFileWriter.flush();
			localFileWriter.close();
			
		} catch (Exception exception) {
		    
			LOG.error("create pid file error.", exception);
			throw new RuntimeException("", exception);
		}
	}

}
