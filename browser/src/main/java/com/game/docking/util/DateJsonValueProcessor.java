package com.game.docking.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	private String format = "yyyy-MM-dd";

	public DateJsonValueProcessor() {
		super();
	}

	public DateJsonValueProcessor(String format) {
		super();
		if (format != null && !"".equals(format)) {
			this.format = format;
		}
	}

	private Object process(Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format((Date) value);
		} else {
			return value.toString();
		}
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {

		return this.process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
