package mjy.test;

import java.util.ArrayList;
import java.util.List;

import mjy.graphics.Window;
import mjy.graphics.gui.Pane;

public class Main {

	private final int WIDTH = 800, HEIGHT = 400;
	
	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		Pane mainPane = new Pane(WIDTH, HEIGHT);
		
		List<Pane> panes = new ArrayList<Pane>();
		panes.add(mainPane);
		
		Window window = new Window(WIDTH, HEIGHT, panes);
		
		window.createWindow();
		
		window.closeWindow();
		
	}
	
}
