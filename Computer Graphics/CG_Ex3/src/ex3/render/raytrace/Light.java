/*
 Computer Graphics - Exercise 3
 Matan Gidnian	200846905
 Aviad Hahami	302188347
 */
package ex3.render.raytrace;

/**
 * Abstract class, Implements IInitable,Used to represent light in scene
 * 
 *
 */
public abstract class Light implements IInitable {

	protected Vec g_color;

	public abstract Vec getLightIntensity(Point3D point);

	public static boolean isLight(String inputString) {

		return (inputString.equals("dir-light") || inputString.equals("spot-light") || inputString.equals("omni-light")) ? true : false;
	}
}
