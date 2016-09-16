package com.leo.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="用户名或密码错误!")
public class UserPasswordException extends RuntimeException {
	/**
	 */
	private static final long serialVersionUID = -3068920360878862351L;

}
