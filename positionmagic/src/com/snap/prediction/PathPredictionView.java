package com.snap.prediction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * The PolyLine class model a line consisting of many points
 */
public class PathPredictionView {
	private final PathPredictor predictor = new PathPredictorVectored();
	// private final PathPredictor predictor = new PathPredictorDirected();
	// private final PathPredictor predictor = new PathPredictorRandom();
	private final Path realPath = new Path();
	private final Path predictedPath = new Path();

	private final PolyLineView pathRealView = new PolyLineView(realPath,
			Color.GREEN);
	private final PolyLineView pathPredictedView = new PolyLineView(
			predictedPath, Color.RED);
	private final List<Boolean> isCheckPoint = new ArrayList<Boolean>();;

	private long checkTime;
	private Position previousKnown;
	private Position lastKnown;
	private Position predicted;

	/** Add a point to this PolyLine */
	public void addPoint(int x, int y) {
		realPath.add(x, y);

		if (checkTime == 0) {
			checkTime = System.currentTimeMillis() + 1;
			predictedPath.add(x, y);
			isCheckPoint.add(true);
			predicted = lastKnown = previousKnown = realPath.getLast();
			predicted.setTime(checkTime);
		} else if (System.currentTimeMillis() > checkTime) {
			predictedPath.add(x, y);
			isCheckPoint.add(true);
			previousKnown = lastKnown;
			lastKnown = realPath.getLast();
			predicted = predictor.getPrediction(previousKnown, lastKnown,
					predicted);
			predictedPath.add(predicted);
			checkTime = predicted.getTime();
		} else {
			isCheckPoint.add(false);
		}
	}

	static int check;

	/** This PolyLine paints itself */
	public void draw(Graphics g) { // draw itself
		pathRealView.draw(g);
		pathPredictedView.draw(g);
		int prevActual = -1;
		for (int i = 0; i < realPath.size() - 1; ++i) {
			if (isCheckPoint.get(i)) {
				int width = 8;
				double half = width / 2;
				int x = new Double(realPath.get(i).getX() - half).intValue();
				int y = new Double(realPath.get(i).getY() - half).intValue();
				g.setColor(Color.GREEN);
				g.fillRect(x, y, width, width); // fill
				if (i > 0 && prevActual > -1) {
					long interval = realPath.get(i).getTime()
							- realPath.get(prevActual).getTime();
					g.setColor(Color.BLACK);
					g.drawString(interval + "", x, y - 10);
				}
				prevActual = i;
			}
		}
		int pos = 20;
		g.clearRect(pos / 2, pos / 2, 200, 40);
	}
}