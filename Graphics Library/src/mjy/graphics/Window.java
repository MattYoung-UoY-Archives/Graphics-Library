package mjy.graphics;

import java.util.List;

import mjy.graphics.gui.Pane;

/**
 * This class implements a window in which the graphics can be rendered.
 * 
 * @since 14/07/2019
 * @author Matt Young
 */
public class Window {

	/**
	 * Used to specify the dimensions of the window.
	 */
	private int width, height;
	
	/**
	 * A list containing all of the panes displayed on the screen.
	 */
	private List<Pane> panes;
	
	/**
	 * Sets the panes to display on the screen.
	 * 
	 * @param panes Panes to display.
	 */
	public Window(int width, int height, List<Pane> panes) {
		this.width = width;
		this.height = height;
		this.panes = panes;
	}
	
}
