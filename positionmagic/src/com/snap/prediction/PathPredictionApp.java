package com.snap.prediction;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class PathPredictionApp extends JFrame {
	public static final int CANVAS_WIDTH = 1500;
	public static final int CANVAS_HEIGHT = 1000;

	private final List<PathPredictionView> lines = new ArrayList<PathPredictionView>();
	private PathPredictionView currentLine;

	/** Constructor to set up the GUI */
	public PathPredictionApp() {
		DrawCanvas canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Begin a new line
				currentLine = new PathPredictionView();
				lines.add(currentLine);
				currentLine.addPoint(e.getX(), e.getY());
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				currentLine.addPoint(e.getX(), e.getY());
				repaint(); // invoke paintComponent()
			}
		});

		setContentPane(canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Paint");
		pack();
		setVisible(true);
	}

	/** DrawCanvas (inner class) is a JPanel used for custom drawing */
	private class DrawCanvas extends JPanel {
		@Override
		protected void paintComponent(Graphics g) { // called back via repaint()
			super.paintComponent(g);
			for (PathPredictionView line : lines) {
				line.draw(g);
			}
		}
	}

	/** The entry main method */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			// Run the GUI codes on the Event-Dispatching thread for thread
			// safety
			@Override
			public void run() {
				new PathPredictionApp(); // Let the constructor do the job
			}
		});
	}
}