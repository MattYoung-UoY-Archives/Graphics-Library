package mjy.graphics;

import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import mjy.graphics.gui.Pane;

/**
 * This class implements a window in which the graphics can be rendered.
 * 
 * @since 15/07/2019
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
	 * An object of the renderer class used for rendering the panes.
	 */
	private Renderer renderer;
	
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
	
	/**
	 * Creates the window.
	 */
	public void createWindow() {
		//Sets the OpenGL version and profile.
		ContextAttribs attribs = new ContextAttribs(3, 3).withForwardCompatible(true).withProfileCore(true);
		try {
			//Creates the window and sets the title.
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("temp title");
		}catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, width, height);

		//Enables VSYNC
		Display.setVSyncEnabled(true);
		
		renderer = new Renderer();
	}
	
	/**
	 * Updates the window with the current contents of the panes.
	 */
	public void update() {
		//Prepares the renderer.
		renderer.prepare();
		
		//Renders the panes.
		renderer.render(panes);
		
		//Sets the VSYNC and updates the window.
		Display.sync(60);
		Display.update();
	}
	
	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		Display.destroy();
	}
	
	/**
	 * Gets if the exit button on the window has been pressed.
	 * @return True if the exit button has been pressed.
	 */
	public boolean exitPressed() {
		return Display.isCloseRequested();
	}
	
}
