package com.snap.prediction;

import java.util.ArrayList;
import java.util.List;

public class Path {
	final private List<Position> positions = new ArrayList<Position>();

	public List<Position> getPositions() {
		return positions;
	}

	public void add(Position position) {
		positions.add(position);
	}

	public int size() {
		return positions.size();
	}

	public Position getPosition(int index) {
		return positions.get(index);
	}

	public void add(int x, int y) {
		Position born = new Position(x, y);
		add(born);
	}

	public Position getLast() {
		return size() == 0 ? null : positions.get(size() - 1);
	}

	public Position getPrevious() {
		return size() < 2 ? null : positions.get(size() - 2);
	}

	public Position get(int index) {
		return positions.get(index);
	}

}
