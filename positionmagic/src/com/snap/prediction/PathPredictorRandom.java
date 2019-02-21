package com.snap.prediction;

import java.util.Random;

public class PathPredictorRandom extends PathPredictor {
	private final Random random = new Random();
	final static int ERROR = 30;

	@Override
	public Position getPrediction(final Position previous,
			final Position current, final Position predicted) {
		// System.out.print(derivation + "x ");
		int derivation = ((random.nextInt(65536) - 32768) % 20);
		double x = current.getX() + derivation;
		derivation = ((random.nextInt(65536) - 32768) % 20);
		double y = current.getY() + derivation;
		long time = current.getTime()
				- (previous == null ? 1000 : previous.getTime());
		if (predicted != null) {
			derivation = Math.abs(new Double(current.getX() - predicted.getX())
					.intValue());
			boolean slowdown = derivation > ERROR;
			derivation = Math.abs(new Double(current.getY() - predicted.getY())
					.intValue());
			slowdown = slowdown || derivation > ERROR;
			time = slowdown ? time - 100 : time + 100;
		}
		return new Position(x, y, current.getTime() + time);
	}
}
