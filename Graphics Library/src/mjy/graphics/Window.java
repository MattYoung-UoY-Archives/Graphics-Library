package mjy.graphics;

import java.awt.Color;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * This class implements a window in which the graphics can be rendered.
 * 
 * @since 16/07/2019
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
	
	private Color background;
	
	/**
	 * Creates the window.
	 * 
	 * @param width Width of the window in pixels.
	 * @param height Height of the window in pixels.
	 */
	public Window(int width, int height, Color background) {
		this.width = width;
		this.height = height;
		this.background = background;
		createWindow();
	}
	
	/**
	 * Creates the window.
	 */
	private void createWindow() {
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
	 * Prepares the screen for the next frame.
	 * Should be called before every frame.
	 */
	private void prepare() {
		GL11.glClearColor(background.getRed()/255f, background.getGreen()/255f, background.getBlue()/255f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Updates the window with the current contents of the panes.
	 */
	public void update() {
		//Clears the screen.
		prepare();
		
		//Renders the panes.
		renderer.render(panes);
		
		//Sets the VSYNC and updates the window.
		Display.sync(60);
		Display.update();
	}
	
	/**
	 * Sets the list of panes to be used.
	 * 
	 * @param panes List of panes to be used.
	 */
	public void setPanes(List<Pane> panes) {
		this.panes = panes;
	}
	
	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		panes.forEach(p -> p.cleanUp());
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
