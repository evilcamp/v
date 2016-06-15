package org.evilcamp.v.framework.listener;


import org.evilcamp.v.framework.common.FrameworkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * web容器初始化
 *
 */
public class WebInitListenter extends ContextLoaderListener {
	private static final Logger logger = LoggerFactory.getLogger(WebInitListenter.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		//ShieldContext.destroy();
		logger.info("spring contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		FrameworkContext.getRootConfigDir();

		logger.info("spring contextInitialized start.");

		logger.info("spring contextInitialized end.");
	}

}
