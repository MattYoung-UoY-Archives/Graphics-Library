package mjy.graphics.gui;

/**
 * This class implements a pane which can be used to section off different parts of the gui.
 * 
 * @since 15/07/2019
 * @author Matt Young
 *
 */
public class Pane {

	/**
	 * Used to specify the location and dimensions of the pane.
	 */
	private int x, y, layer, width, height;
	
	/**
	 * The Quad used to render the graphics onto.
	 */
	private Quad quad;
	
	/**
	 * Creates a pane with the specified dimensions, at the x and y coordinates provided, on the specified layer.
	 * 
	 * @param x X Coordinate of the upper-left corner.
	 * @param y Y Coordinate of the upper-left corner.
	 * @param layer The layer of the pane. Layer 0 is the front most layer.
	 * @param width Width of the pane in pixels.
	 * @param height Height of the pane in pixels.
	 * @param screenW Width of the screen in pixels.
	 * @param screenH Height of the screen in pixels.
	 * 
	 * @throws IllegalArgumentException - if the value of layer is < 0.
	 */
	public Pane(int x, int y, int layer, int width, int height, int screenW, int screenH) {
		this.x = x;
		this.y = y;
		if(layer < 0) try {
			throw new IllegalArgumentException("The value of layer must be >= 0!\n Layer: " + layer);
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		this.layer = layer;
		this.width = width;
		this.height = height;
		this.quad = new Quad(x/(float) screenW, y/(float) screenH, width/(float) screenW, height/(float) screenH, layer);
	}
	
	/**
	 * Creates a pane with the specified dimensions. The upper-left corner will be placed at the default coordinates of (0, 0).
	 * 
	 * @param width Width of the pane.
	 * @param height Height of the pane.
	 * @param layer The layer of the pane. Layer 0 is the front most layer.
	 */
	public Pane(int layer, int width, int height, int screenW, int screenH) {
		this(0, 0, layer, width, height, screenW, screenH);
	}
	
	/**
	 * Renders the pane.
	 */
	public void render() {
		quad.render();
	}
	
	/**
	 * Frees up the resources used by the pane.
	 */
	public void cleanUp() {
		quad.cleanUp();
	}
	
}
