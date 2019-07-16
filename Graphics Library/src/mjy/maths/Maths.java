package mjy.maths;

import org.lwjgl.util.vector.Vector3f;

/**
 * This class implements some useful mathematical functions.
 * 
 * @since 16/07/2019
 * @author Matt Young
 */
public class Maths {
	
	/**
	 * Used to convert from normal screen coordinates to the OpenGL coordinate system.
	 * @param coord The coordinate to convert.
	 * @return The new coordinate in OpenGL coordinates.
	 */
	public static Vector3f toGLCoords(Vector3f coord) {
		Vector3f temp = new Vector3f((coord.x * 2) - 1, (coord.y * 2) - 1, coord.z);
		return new Vector3f(temp.x, -temp.y, temp.z);
	}
	
}
