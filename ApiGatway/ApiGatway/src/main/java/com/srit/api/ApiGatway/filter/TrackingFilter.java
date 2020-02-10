package com.srit.api.ApiGatway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Component
public class TrackingFilter extends ZuulFilter {
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER=true;
	private static final Logger logger =LoggerFactory.getLogger(TrackingFilter.class);
	@Autowired
	FilterUtils filterUtils;
	//The filterType() method is used to tell Zuul whether the filter is a pre-, route, or post filter.
	@Override
	public String filterType() {
		return filterUtils.PRE_FILTER_TYPE;
	}
	// the filterOrder method tells which order zuul send the request to diffrent filters
	@Override
	public int filterOrder() {	
		return FILTER_ORDER;
	}
	// THE ShouldFilter() method tell whether it is acive or not
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}
	private boolean isCorrelationIdPresent(){
		if (filterUtils.getCorrelationId() !=null){
			return true;
		}
		return false;
	}
	private String generateCorrelationId(){
		return java.util.UUID.randomUUID().toString();
	}

	@Override
	public Object run() throws ZuulException {
		if(isCorrelationIdPresent()) {
			filterUtils.getCorrelationId();
			logger.debug("tmx-correlation-id found in  tracking filter: {}.",filterUtils.getCorrelationId());
		}else{
			filterUtils.setCorrelationId(generateCorrelationId());
			logger.debug("tmx-correlation-id generated in tracking filter: {}.",filterUtils.getCorrelationId());
			System.out.println("After seting the correlation-id:"+filterUtils.getCorrelationId());
		}
		RequestContext ctx =
				RequestContext.getCurrentContext();
				logger.debug("Processing incoming request for {}.",
				ctx.getRequest().getRequestURI());
		return null;
	}


}
