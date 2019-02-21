package com.snap.prediction;

import java.awt.Color;
import java.awt.Graphics;

/*
 * The PolyLine class model a line consisting of many points
 */
public class PolyLineView {
	private final Path path; // List of x-coord
	private final Color color;

	/** Constructor */
	public PolyLineView(Path path, Color color) {
		this.path = path;
		this.color = color;
	}

	/** Add a point to this PolyLine */
	public void addPoint(Position position) {
		path.add(position);
	}

	/** This PolyLine paints itself */
	public void draw(Graphics g) { // draw itself
		g.setColor(color);
		for (int i = 0; i < path.size() - 1; ++i) {
			Position position = path.getPosition(i);
			Position next = path.getPosition(i + 1);
			g.drawLine(new Double(position.getX()).intValue(), new Double(
					position.getY()).intValue(), new Double(next.getX())
					.intValue(), new Double(next.getY()).intValue());
		}
	}
}