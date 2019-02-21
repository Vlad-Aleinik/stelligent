package com.snap.prediction;

public class PathPredictorDirected extends PathPredictor {
	private static long initialCheckFrequency = 500;
	private static long maxCheckFrequency = 5000;
	private static long minCheckFrequency = 10;
	private double errorRate = 30;

	@Override
	public Position getPrediction(final Position previous,
			final Position current, final Position predicted) {
		if (current == null) {
			throw new IllegalArgumentException("no current position");
		}

		double oldTimer = current.getTime() - previous.getTime();

		if (previous == null || predicted == null || oldTimer == 0) {
			return new Position(current.getX(), current.getY(),
					current.getTime() + initialCheckFrequency);
		}

		double predictionX = current.getX();
		double predictionY = current.getY();
		double distanceX = current.getX() - previous.getX();
		double distanceY = current.getY() - previous.getY();
		double derivationX = current.getX() - predicted.getX();
		double derivationY = current.getY() - predicted.getY();

		double newTimer;
		if (System.currentTimeMillis() >= predicted.getTime()) {
			double derivation = Math.sqrt(Math.pow(derivationX, 2)
					+ Math.pow(derivationY, 2));

			double errorSpeed = derivation / oldTimer;
			newTimer = errorSpeed != 0 ? errorRate / errorSpeed : predicted
					.getTime() - current.getTime();
			if (newTimer > maxCheckFrequency) {
				newTimer = maxCheckFrequency;
				errorRate = Math.min(10, errorRate - 1);
			}
			if (newTimer < minCheckFrequency) {
				newTimer = minCheckFrequency;
				errorRate += Math.min(300, errorRate - 1);
			}

		} else {
			newTimer = System.currentTimeMillis() - current.getTime();
		}

		predictionX += (distanceX * newTimer) / oldTimer;
		predictionY += (distanceY * newTimer) / oldTimer;
		double newTimeToCheck = current.getTime() + newTimer;
		long newLongTimeTocheck = new Double(newTimeToCheck).longValue();
		return new Position(predictionX, predictionY, newLongTimeTocheck);
	}
}
