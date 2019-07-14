package mjy.graphics.gui;

/**
 * This class implements a pane which can be used to section off different parts of the gui.
 * 
 * @since 14/07/2019
 * @author Matt Young
 *
 */
public class Pane {

	/**
	 * Used to specify the location and dimensions of the pane.
	 */
	private int x = 0, y = 0, width, height;
	
	/**
	 * Creates a pane with the specified dimensions at the x and y coordinates provided.
	 * 
	 * @param x X Coordinate of the upper-left corner.
	 * @param y Y Coordinate of the upper-left corner.
	 * @param width Width of the pane.
	 * @param height Height of the pane.
	 */
	public Pane(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates a pane with the specified dimensions. The upper-left corner will be placed at the default coordinates of (0, 0).
	 * 
	 * @param width Width of the pane.
	 * @param height Height of the pane.
	 */
	public Pane(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
}
