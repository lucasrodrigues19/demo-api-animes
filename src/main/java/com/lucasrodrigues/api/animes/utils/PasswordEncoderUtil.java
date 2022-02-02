package com.lucasrodrigues.api.animes.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

	public static PasswordEncoder getInstance() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
