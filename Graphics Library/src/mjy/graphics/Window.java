package mjy.graphics;

import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

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
	
	public void createWindow() {
		ContextAttribs attribs = new ContextAttribs(3, 3).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setTitle("temp title");
		}catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, width, height);
	}
	
	public void closeWindow() {
		Display.destroy();
	}
	
	public boolean exitPressed() {
		return Display.isCloseRequested();
	}
	
}
