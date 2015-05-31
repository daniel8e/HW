/*
 * Exercise 5 - Computer Graphics
 * Matan Gidnian	200846905
 * Nitsan Bracha 	300590155
 */
package ex5.models;

import ex5.models.GalaxyModels.Earth;
import ex5.models.GalaxyModels.Planet;
import ex5.models.GalaxyModels.Saturn;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import java.util.ArrayList;

public class Galaxy implements IRenderable {
	
	// Texture Copyrighted - http://planetpixelemporium.com/
	// Texture paths, resource folder
	private String PATH_TEX_SUN = "sunmap.png";
	private String PATH_TEX_MERCURY = "mercurymap.png";
	private String PATH_TEX_VENUS = "venusmap.png";
	private String PATH_TEX_EARTH = "earthmap1k.png";
	private String PATH_TEX_MARS = "mars_1k_color.png";
	private String PATH_TEX_JUPITER = "jupitermap.png";
	private String PATH_TEX_SATURN = "saturnmap.png";
	private String PATH_TEX_URANUS = "uranusmap.png";
	private String PATH_TEX_NEPTUNE = "neptunemap.png";
	private String PATH_TEX_PLUTO = "plutomap1k.png";
	
	// from ex05, Don�t implement the sizes with the true physical proportions (orbit and
	// plants� radiuses) the true proportions are hard to visualize, but make sure that what is bigger
	// and farther, stays that way.
	// See Arbitrary sizes for our exercise
	
	// Arbitrary sizes for our exercise - order by size
	public static final float SIZE_SUN = 1f;

	// Significant size jump
	private float SIZE_JUPITER = 0.43f * SIZE_SUN;
	private float SIZE_SATURN = 0.38f * SIZE_SUN;

	// Significant size jump
	private float SIZE_URANUS = 0.31f * SIZE_SUN;
	private float SIZE_NEPTUNE = 0.3f * SIZE_SUN;

	// Significant size jump
	private float SIZE_EARTH = 0.1959f * SIZE_SUN;
	private float SIZE_VENUS = 0.1857f * SIZE_SUN;
	private float SIZE_MARS = 0.1755f * SIZE_SUN;
	private float SIZE_MERCURY = 0.1653f * SIZE_SUN;
	private float SIZE_PLUTO = 0.133f * SIZE_SUN;

	//Distances from sun
	private float DISTANCE_MERCURY = 1.5f;
	private float DISTANCE_VENUS = 3f;
	private float DISTANCE_EARTH = 4f;
	private float DISTANCE_MARS = 5.2f;
	private float DISTANCE_JUPITER = 6.3f;
	private float DISTANCE_SATURN = 7.5f;
	private float DISTANCE_URANUS = 8.8f;
	private float DISTANCE_NEPTUNE = 9.8f;
	private float DISTANCE_PLUTO = 11.2f;

	// inclination - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/ (old, but interesting !)
	// inclination = using data from ex.05 instead
	private float INCLINATION_MERCURY = 7;
	private float INCLINATION_VENUS = 3.39f;
	private float INCLINATION_EARTH = 0;
	private float INCLINATION_MARS = 1.85f;
	private float INCLINATION_JUPITER = 1.3f;
	private float INCLINATION_SATURN = 2.49f;
	private float INCLINATION_URANUS = 0.77f;
	private float INCLINATION_NEPTUNE = 1.77f;
	private float INCLINATION_PLUTO = 17.2f;
	
	float SHINE_ALL_DIRECTIONS = 1;
    float[] lightSourceDown = {-4, -10, 0, SHINE_ALL_DIRECTIONS};
    float[] lightSourceUp = {0, 10, 0, SHINE_ALL_DIRECTIONS};
    float[] lightSourceSide = {15, 4, 0, SHINE_ALL_DIRECTIONS};
    float[] colorMatrixWhite = {1f, 1f, 1f, 1f};
    //float[] colorMatrixRed = {0.9f, 0f, 0f, 0.9f}; - Wont be used as it looks bad, should be used for "cooler" effect
    
	private GLU glu;
	private boolean isLightSpheres = true;
	private ArrayList<Planet> allPlanets = new ArrayList<Planet>();
	
	@Override
	public void render(GL gl) 
	{
		letThereBeLight(gl);

		for (Planet planet : allPlanets) {
			planet.render(gl);
		}
		
		if (isLightSpheres) 
		{
			boolean lightFlag = gl.glIsEnabled(GL.GL_LIGHTING);
			showLightSpheres(gl);
		    if (lightFlag)
		      gl.glEnable(GL.GL_LIGHTING);
		}
	}
	
	private void letThereBeLight(GL gl)
	{
        // Set light parameters & enable each source
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightSourceDown, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, colorMatrixWhite, 0);
        gl.glEnable(GL.GL_LIGHT1);
        
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightSourceUp, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, colorMatrixWhite, 0);
        gl.glEnable(GL.GL_LIGHT0);
        
        gl.glLightfv(GL.GL_LIGHT2, GL.GL_POSITION, lightSourceSide, 0);
        gl.glLightfv(GL.GL_LIGHT2, GL.GL_AMBIENT, colorMatrixWhite, 0);
        gl.glEnable(GL.GL_LIGHT2);
	}
	
	private void showLightSpheres(GL gl)
	{
		gl.glDisable(GL.GL_LIGHTING);
	    gl.glPushMatrix();
	    
		GLUquadric lightSphere = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(lightSphere, GLU.GLU_FILL);
        glu.gluQuadricNormals(lightSphere, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(lightSphere, GLU.GLU_OUTSIDE);
	
	    gl.glTranslated(lightSourceDown[0], lightSourceDown[1], lightSourceDown[2]);
	    gl.glColor4fv(colorMatrixWhite, 0);
	    glu.gluSphere(lightSphere, 0.3D, 16, 16);
	    gl.glPopMatrix();
	    
	    gl.glPushMatrix();
	    gl.glTranslated(lightSourceUp[0], lightSourceUp[1], lightSourceUp[2]);
	    gl.glColor4fv(colorMatrixWhite, 0);
	    glu.gluSphere(lightSphere, 0.3D, 16, 16);
	    gl.glPopMatrix();
	    
	    gl.glPushMatrix();
	    gl.glTranslated(lightSourceSide[0], lightSourceSide[1], lightSourceSide[2]);
	    gl.glColor4fv(colorMatrixWhite, 0);
	    glu.gluSphere(lightSphere, 0.3D, 16, 16);
	    gl.glPopMatrix();
	    glu.gluDeleteQuadric(lightSphere);
	}
	
	@Override
	public void init(GL gl) {
		glu = new GLU();

		allPlanets.add(new Planet(glu, PATH_TEX_SUN, SIZE_SUN, 0, 0));
		allPlanets.add(new Planet(glu, PATH_TEX_JUPITER, SIZE_JUPITER, INCLINATION_JUPITER, DISTANCE_JUPITER));
		allPlanets.add(new Planet(glu, PATH_TEX_MARS, SIZE_MARS, INCLINATION_MARS, DISTANCE_MARS));
		allPlanets.add(new Planet(glu, PATH_TEX_MERCURY, SIZE_MERCURY, INCLINATION_MERCURY, DISTANCE_MERCURY));
		allPlanets.add(new Planet(glu, PATH_TEX_NEPTUNE, SIZE_NEPTUNE, INCLINATION_NEPTUNE, DISTANCE_NEPTUNE));
		allPlanets.add(new Planet(glu, PATH_TEX_PLUTO, SIZE_PLUTO, INCLINATION_PLUTO, DISTANCE_PLUTO));
		allPlanets.add(new Planet(glu, PATH_TEX_URANUS, SIZE_URANUS, INCLINATION_URANUS, DISTANCE_URANUS));
		allPlanets.add(new Planet(glu, PATH_TEX_VENUS, SIZE_VENUS, INCLINATION_VENUS, DISTANCE_VENUS));
		allPlanets.add(new Saturn(glu, PATH_TEX_SATURN, SIZE_SATURN, INCLINATION_SATURN, DISTANCE_SATURN));
		allPlanets.add(new Earth(glu, PATH_TEX_EARTH, SIZE_EARTH, INCLINATION_EARTH, DISTANCE_EARTH));

		for (Planet planet : allPlanets) {
			planet.init(gl);
		}
	}

	@Override
	public void control(int type, Object params) 
	{
		switch (type) 
		{
	        case IRenderable.TOGGLE_LIGHT_SPHERES:
	        {
	            isLightSpheres = !isLightSpheres;
	            break;
	        }
	        default:
	            System.out.println("Control type not supported: " + toString() + ", " + type);
	    }

		for (Planet planet : allPlanets) {
			planet.control(type, params);
		}
	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCamera(GL gl) {
		// TODO Auto-generated method stub
	}
	
	@Override
    public String toString() {
        return "Galaxy";
    }

}
