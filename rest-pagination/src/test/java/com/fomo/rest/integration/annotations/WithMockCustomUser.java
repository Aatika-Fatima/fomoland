package com.fomo.rest.integration.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory= WithMockCustomUserSecurityContext.class)
public @interface WithMockCustomUser {
	String username() default "1234567"; 
	String name() default "Aatika Fatima";
}
