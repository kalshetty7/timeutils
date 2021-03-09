package timeutils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.timeutil.Timer;

public class TimeDiffTest {

	private Timer t;

	private static final int MILLIS_ERROR_OFFSET = 10;

	@BeforeEach
	private void init() {
		t = new Timer();
	}

	private void sleepInSeconds(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sleepInMillis(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 4, 5, 1, 6, 7, 15, 20 })
	public void checkSeconds(int WAIT_TIME_SECONDS) {
		t.start();
		sleepInSeconds(WAIT_TIME_SECONDS);
		t.stop();
		p("t.getTimeDiff().getSeconds() : " + t.getTimeDiff().getSeconds() + "\nWAIT_TIME_SECONDS : "
				+ WAIT_TIME_SECONDS);
		p(t);
		assertTrue(t.getTimeDiff().getSeconds() == WAIT_TIME_SECONDS);
	}

	@ParameterizedTest
	@ValueSource(ints = { 200, 300, 400, 500, 900, 100, 888, 999, 777, 555, 111, 278 })
	public void checkMilliseconds(int WAIT_TIME_MILLIS) {

		t.start();
		sleepInMillis(WAIT_TIME_MILLIS);
		t.stop();
		p("t.getTimeDiff().getMilliseconds() : " + t.getTimeDiff().getMilliseconds() + "\n" + "WAIT_TIME_MILLIS : "
				+ WAIT_TIME_MILLIS);
		assertTrue((t.getTimeDiff().getMilliseconds() >= WAIT_TIME_MILLIS)
				&& (t.getTimeDiff().getMilliseconds() <= WAIT_TIME_MILLIS + MILLIS_ERROR_OFFSET));
	}

	@Test
	public void startTwice() {
		t.start();
		assertThrows(RuntimeException.class, () -> t.start());
	}

	@Test
	public void stopTwice() {
		assertThrows(RuntimeException.class, () -> t.stop());
	}

	@Test
	public void reset() {
		verifyTimerValuesAsNull();
		t.start();
		t.stop();
		verifyTimerValuesAsNotNull();
		// now reset the timer
		t.reset();
		verifyTimerValuesAsNull();
	}

	private void verifyTimerValuesAsNull() {
		assertTrue(t.getStartTime() == null);
		assertTrue(t.getEndTime() == null);
		assertTrue(t.getTimeDiff() == null);
	}

	private void verifyTimerValuesAsNotNull() {
		assertTrue(t.getStartTime() != null);
		assertTrue(t.getEndTime() != null);
		assertTrue(t.getTimeDiff() != null);
	}

	private static void p(Object o) {
		System.out.print("\n" + o + "\n");
	}
}
