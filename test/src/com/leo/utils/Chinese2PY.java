package com.leo.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Chinese2PY {
	public static void main(String[] args) {
		Hanyu hanyu = new Hanyu();
		// ��Ӣ�Ļ�ϵ�һ������
		String str = "��Ϫ��ʯ����Hello �캮��Ҷϡ��Android ɽ·Ԫ���꣬What's up?   �մ�ʪ���¡�";
		String strPinyin = hanyu.getStringPinYin(str);
		System.out.println(strPinyin);
	}
}

class Hanyu {
	private HanyuPinyinOutputFormat format = null;
	private String[] pinyin;

	public Hanyu() {
		format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	// ת�������ַ�
	public String getCharacterPinYin(char c) {
		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}

		// ���c���Ǻ��֣�toHanyuPinyinStringArray�᷵��null
		if (pinyin == null)
			return null;

		// ֻȡһ������������Ƕ����֣���ȡ��һ������
		return pinyin[0];
	}

	// ת��һ���ַ���
	public String getStringPinYin(String str) {
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// ���str.charAt(i)�Ǻ��֣��򱣳�ԭ��
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin);
			}
		}
		return sb.toString();
	}
}