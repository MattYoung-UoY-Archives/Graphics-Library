package mjy.graphics.gui;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

import mjy.maths.Maths;

/**
 * Implements the OpenGL description of a quadrilateral.
 * 
 * @since 16/07/2019
 * @author Matt Young
 */
class Quad {

	/**
	 * The vao and vbo ids and the number of vertices.
	 */
	private int vaoID, vboID;
	private int vertexCount;
	
	/**
	 * The initial vertex coordinates, before transformation are applied.
	 */
	private float[] vertices = {
			1f, 0f, 0f,
			0f, 0f, 0f,
			0f, 1f, 0f,
			
			0f, 1f, 0f,
			1f, 1f, 0f,
			1f, 0f, 0f
	};
	
	/**
	 * Creates and loads the Quad.
	 * @param x The normalised x coordinate of the quad. 
	 * @param y The normalised y coordinate of the quad.
	 * @param width The normalised width of the quad.
	 * @param height The normalised height of the quad.
	 * @param layer The layer of the quad.
	 */
	public Quad(float x, float y, float width, float height, int layer) {
		
		//Applies a scale transformation.
		for(int i = 0; i < (vertices.length/3); i++) {
			vertices[(3 * i)] *= width;
			vertices[(3 * i) + 1] *= height;
		}
		
		//Applies a translation transformation.
		for(int i = 0; i < (vertices.length/3); i++) {
			vertices[(3 * i)] += x;
			vertices[(3 * i) + 1] += y;
		}
		
		//Converts the coordinates into OpenGL coordinates.
		for(int i = 0; i < (vertices.length/3); i++) {
			Vector3f newCoord = Maths.toGLCoords(new Vector3f(vertices[(3 * i)], vertices[(3 * i) + 1], vertices[(3 * i) + 2]));
			vertices[(3 * i)] = newCoord.x;
			vertices[(3 * i) + 1] = newCoord.y;
			vertices[(3 * i) + 2] = layer * 0.1f;
		}
		
		//Loads the quad.
		loadToVAO(vertices);
	}
	
	/**
	 * Loads the quad into memory.
	 * @param positions The vertex coordinates of the quad.
	 */
	private void loadToVAO(float[] positions) {
		//Gets a VAO ID.
		vaoID = createVAO();
		//Stores the vertex data in the VAO.
		storeDataInAttributeList(0, positions);
		//Unbinds the VAO.
		unbindVAO();
		//Sets the vertex count.
		vertexCount = positions.length / 3;
	}
	
	/**
	 * Creates a new VAO.
	 * @return The ID of the new VAO.
	 */
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	/**
	 * Stores float data in a VBO.
	 * @param attributeNumber The attribute number of the data.
	 * @param data The data to store.
	 */
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDatainFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Unbinds the currently bound VAO.
	 */
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	/**
	 * Puts float data into a float buffer.
	 * @param data The data to put into a buffer.
	 * @return The float buffer containing the data.
	 */
	private FloatBuffer storeDatainFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	/**
	 * Renders the quad.
	 */
	protected void render() {
		GL30.glBindVertexArray(vaoID);
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	/**
	 * Released the resources used by the quad.
	 */
	public void cleanUp() {
		GL30.glDeleteVertexArrays(vaoID);
		GL15.glDeleteBuffers(vboID);
	}

}
