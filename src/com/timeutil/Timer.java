package com.timeutil;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Timer {

	private LocalDateTime startTime, endTime;

	private boolean isStarted, isStopped;

	private TimeDiff timeDiff;

	public void start() {
		if (!isStarted) {
			startTime = LocalDateTime.now();
			isStarted = true;
		} else
			throw new RuntimeException("Already started...");

	}

	public void stop() {
		if (!isStarted)
			throw new RuntimeException("Please first start the timer.....");
		else if (!isStopped) {
			endTime = LocalDateTime.now();
			isStopped = true;
			timeDiff = new TimeDiff(startTime, endTime);
		} else
			throw new RuntimeException("Already stopped...");
	}

	public void reset() {
		isStarted = isStopped = false;
		startTime = endTime = null;
		timeDiff = null;
	}

}
