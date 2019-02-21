package com.snap.prediction;

public class PathPredictorVectored extends PathPredictor {

	@Override
	public Position getPrediction(final Position previous,
			final Position current, final Position predicted) {
		if (current == null) {
			throw new IllegalArgumentException("no current position");
		}

		if (previous == null || predicted == null) {
			return new Position(current.getX(), current.getY(),
					current.getTime() + 500);
		}
		Position distance = current.minus(previous);
		Position derivation = current.minus(predicted);
		double derivationMagnitude = derivation.magnitude() / 50;
		if (derivationMagnitude == 0) {
			derivationMagnitude = 1 / 50;
		}
		if (derivationMagnitude > 50) {
			derivationMagnitude = 50;
		}
		Position newDerivation = distance.divide(derivationMagnitude);
		Position prediction = current.plus(newDerivation);
		// System.out.println(current + "  " + prediction + " " +
		// (prediction.getTime() - current.getTime()));
		return prediction;
	}
}
