package com.snap.prediction;

public class Position extends Point {
	private long time;

	public Position(double x, double y) {
		super(x, y);
		time = System.currentTimeMillis();
	}

	public Position(double x, double y, long time) {
		super(x, y);
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	Position minus(Position position) {
		return new Position(getX() - position.getX(), getY() - position.getY(),
				getTime() - position.getTime());
	}

	double magnitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)
				+ Math.pow(getTime(), 2));
	}

	public Position divide(double divider) {
		return new Position(getX() / divider, getY() / divider, new Double(
				getTime() / divider).longValue());
	}

	public Position plus(Position position) {
		return new Position(position.getX() + getX(), position.getY() + getY(),
				position.getTime() + getTime());
	}

	public String toString() {
		return getX() + "x" + getY() + "x" + getTime();
	}

}
