package com.hecai.service.mq.config.swagger;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * swagger只开放给dev、test环境
 */
public class SwaggerEnableCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String active = context.getEnvironment().getProperty("spring.profiles.active");
		return "dev".equals(active) || "test".equals(active);
	}

}