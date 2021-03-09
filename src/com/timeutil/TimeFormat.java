package com.timeutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface TimeFormat {
	String PATTERN = "dd MM yyyy HH mm ss SSS";
	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

	public static LocalDateTime parse(String t) {
		return LocalDateTime.parse(t, FORMATTER);
	}

	public static LocalDateTime getTime(LocalDateTime t) {
		String ts = t.format(FORMATTER);
		return parse(ts);
	}
}
