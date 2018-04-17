/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.common.utils;

/**
 * @author xian
 * date 2018/4/17 10:25
 * desc
 */
public class CodeUtil {
	public static String getCode() {
		String code = "";
		for (int i = 0;  i < 4; i++) {
			int random = (int)(Math.random() * 91);
			if (random > 64) {
				code = (char)random + code;
			}else {
				code = random + code;
			}
		}
		return code;
	}
	
}
