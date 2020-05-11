package com.lzw.framework.config.cloud;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 15
 * @Description: 自定义拦截器
 */
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    // 实现该方法，添加请求头信息
    @Override
    public void apply(RequestTemplate template) {
        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("template header Authorization [{}]", template.headers().get(AUTHORIZATION_HEADER));
            }
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication instanceof AbstractAuthenticationToken) {

            AbstractAuthenticationToken authenticationToken = (AbstractAuthenticationToken) authentication;
            Object details = authenticationToken.getDetails();
            if (details == null || !(details instanceof OAuth2AuthenticationDetails)) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("template header Authorization is null. details [{}] ", details);
                }
                return;
            }

            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authenticationToken.getDetails();
            String tokenType = oAuth2AuthenticationDetails.getTokenType();
            String tokenValue = oAuth2AuthenticationDetails.getTokenValue();

            if (OAuth2AccessToken.BEARER_TYPE.equalsIgnoreCase(tokenType) && tokenValue != null) {
                template.header("Authorization", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, tokenValue));

                template.header("Authorization_Permit", "Authorization_Permit_" + tokenValue);
            } else {
                template.header("Authorization_Permit", "Authorization_Permit");
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("finally template header Authorization [{}]", template.headers().get(AUTHORIZATION_HEADER));
        }
    }
}
