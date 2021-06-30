import java.awt.Canvas;

import javax.swing.JFrame;

public class Window {

	JFrame frame;
	Canvas canvas;

	Keyboard keyboard;

	public Window(String title, int width, int height) {
		keyboard = new Keyboard();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.addKeyListener(keyboard);
		frame.add(canvas);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}