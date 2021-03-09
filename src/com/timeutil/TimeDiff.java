package com.timeutil;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TimeDiff {
	private LocalDateTime startTime, endTime;
	private long milliseconds, seconds, minutes, hours, days;

	public TimeDiff(String s, String e) {
		this(TimeFormat.parse(s), TimeFormat.parse(e));
	}

	private static void p(Object o) {
		System.out.print("\n" + o + "\n");
	}

	public TimeDiff(LocalDateTime s, LocalDateTime e) {
		s = TimeFormat.getTime(s);
		e = TimeFormat.getTime(e);
		startTime = s;
		endTime = e;
		milliseconds = Duration.between(startTime, endTime).toMillis();
		if (milliseconds >= 1000) {
			seconds = milliseconds / 1000;
			milliseconds = milliseconds % 1000;
		}
		if (seconds >= 60) {
			minutes = seconds / 60;
			seconds = seconds % 60;
		}
		if (minutes >= 60) {
			hours = minutes / 60;
			minutes = minutes % 60;
		}
		if (hours >= 24) {
			days = hours / 24;
			hours = hours % 24;
		}
		p("start : " + startTime + "\nend : " + endTime);
	}

	@Override
	public String toString() {
		return days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds, " + milliseconds
				+ " milliseconds";
	}
}
