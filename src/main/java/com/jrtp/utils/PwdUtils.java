package com.jrtp.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PwdUtils {
	public static String generatePwd() {
		String pwd = RandomStringUtils.random(6, "ABCDEFGHIJKLMNOPQRSTWXYZabcdefghijklmnopqrstwxyz123456789");
		return pwd;
	}

}
