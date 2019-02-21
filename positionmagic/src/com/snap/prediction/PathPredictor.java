package com.snap.prediction;

abstract public class PathPredictor {
	abstract Position getPrediction(final Position previous,
			final Position current, final Position predicted);

}
