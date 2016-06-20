package org.evilcamp.v.framework.aop;


import org.evilcamp.v.framework.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截所有请求
 */

@Component
public class RequestInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 请求开始前放置新的requestId,用于分布式系统中跟踪请求流
		MDC.put("reqId", "ReqId=" + LogUtil.geneRequestId() + "|");
		// 流程日志初始起点
		logger.info("RequestInterceptor start.");

		// 记录请求信息
		logger.info("ip:{},x-real-ip:{},X-Forwarded-For:{},characterEncoding:{},contentType:{},session:{}," +
				"sessionMaxInactiveInterval:{}",request.getRemoteAddr(),request.getHeader("X-Real-IP"),
				request.getParameter("X-Forwarded-For"),request.getCharacterEncoding(),request.getContentType(),
				request.getSession().getId(),request.getSession().getMaxInactiveInterval());
		logger.info("url:{},method:{},queryString:{}", request.getRequestURL().toString(),request.getMethod(),request.getQueryString());

		// 拦截器结束
		logger.info("RequestInterceptor end.");
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
