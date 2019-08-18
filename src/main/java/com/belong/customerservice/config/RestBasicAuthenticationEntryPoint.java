package com.belong.customerservice.config;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Implementation class for basic authentication entry point
 * @author rajesh
 */
@Component
public class RestBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private final Logger LOGGER = LoggerFactory.getLogger(RestBasicAuthenticationEntryPoint.class);

	/**
	 * overriding defaults to return error in json format
	 * sets response as json object with auth error details
	 */
	@Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
			response.getWriter().write(new JSONObject()
			        .put("timestamp", LocalDateTime.now())
			        .put("message", "Invalid username or password")
			        .put("status", HttpServletResponse.SC_UNAUTHORIZED)
			        .toString());
		} catch (JSONException e) {
			LOGGER.error("Exception : ", e.getMessage());
		}
    }
}
