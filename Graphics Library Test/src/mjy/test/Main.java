package mjy.test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mjy.graphics.Pane;
import mjy.graphics.Window;

public class Main {

	private final int WIDTH = 800, HEIGHT = 560;
	
	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		
		Color background = new Color((int) (0.6f * 255), (int) (0.6f * 255), (int) (1.0f * 255));
		Window window = new Window(WIDTH, HEIGHT, background);
		
		Pane mainPane = new Pane(1, 200, 560, WIDTH, HEIGHT);
		Pane frontPane = new Pane(300, 100, 0, 400, 100, WIDTH, HEIGHT, new Color((int) (0.5f * 255)));
		
		List<Pane> panes = new ArrayList<Pane>();
		panes.add(mainPane);
		panes.add(frontPane);
		
		window.setPanes(panes);
		
		while(!window.exitPressed()) {
			window.update();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		window.closeWindow();
		
	}
	
}
