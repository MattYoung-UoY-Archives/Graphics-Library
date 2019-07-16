package mjy.graphics;

import java.util.List;

import org.lwjgl.opengl.GL11;

import mjy.graphics.gui.Pane;

/**
 * Calls the OpenGL methods required to render the contents of the panes to the screen.
 * 
 * @since 16/07/2019
 * @author Matt Young
 */
class Renderer {

	protected Renderer() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}

	/**
	 * Prepares the screen for the next frame.
	 * Should be called before every frame.
	 */
	protected void prepare() {
		GL11.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Renders the panes to the screen.
	 * @param panes Panes to render to the screen.
	 * 
	 * @throws NullPointerException if the panes list has not been set.
	 */
	protected void render(List<Pane> panes) {
		try {
			panes.forEach(t -> t.render());
		}catch(NullPointerException e) {
			e = new NullPointerException("The panes list of the window needs to be set before attempting to update!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
