package ex5.models;

import ex5.models.GalaxyModels.Earth;
import ex5.models.GalaxyModels.Planet;
import ex5.models.GalaxyModels.Saturn;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.util.ArrayList;

public class Galaxy implements IRenderable {
	
	private GLU glu;
	private boolean isLightSpheres = true;
	private ArrayList<Planet> allPlanets = new ArrayList<Planet>();

	//Consts
	private float _shrinkConst = 1f; // this will help normalize galaxies size if needed
	private int _slices = 16;
	private int _stacks = 16;

	// Texture consts - http://planetpixelemporium.com/
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
	

	
	// radiuses - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/
	// from ex05, Don’t implement the sizes with the true physical proportions (orbit and
	// plants’ radiuses) the true proportions are hard to visualize, but make sure that what is bigger
	// and farther, stays that way.
	// See Arbitrary sizes for our exercise
//	private float RADIUS_SUN = 696.342f;
//	private float RADIUS_MERCURY = 2.439f;
//	private float RADIUS_VENUS = 6.051f;
//	private float RADIUS_EARTH = 6.731f;
//	private float RADIUS_MOON = 1.738f;
//	private float RADIUS_MARS = 3.396f;
//	private float RADIUS_JUPITER = 71.492f;
//	private float RADIUS_SATURN = 60.258f;
//	private float RADIUS_URANUS = 25.559f;
//	private float RADIUS_NEPTUNE = 24.764f;
//	private float RADIUS_PLUTO = 1.195f;
	
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



	// inclination - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/
//	private float INCLINATION_MERCURY = 7;
//	private float INCLINATION_VENUS = 3.4f;
//	private float INCLINATION_EARTH = 0;
//	private float INCLINATION_MOON = 5.1f;
//	private float INCLINATION_MARS = 1.9f;
//	private float INCLINATION_JUPITER = 1.3f;
//	private float INCLINATION_SATURN = 2.5f;
//	private float INCLINATION_URANUS = 0.8f;
//	private float INCLINATION_NEPTUNE = 1.8f;
//	private float INCLINATION_PLUTO = 17.2f;
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

	
	@Override
	public void render(GL gl) 
	{
		letThereBeLight(gl);

		// what about stars orbits ?
		for (Planet planet : allPlanets) {
			planet.render(gl);
		}
		// letThereBeSpecial(gl);
	}


//
//	private void drawSun(GL gl, GLU glu, Texture tex, float radius, float shrinkParam)
//	{
//		gl.glPushMatrix();
//		materializer(gl, new float[1], tex);
//
//	    gl.glPushMatrix();
//		GLUquadric planet = glu.gluNewQuadric();
//		glu.gluQuadricTexture(planet, true);
//		glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
//		glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
//		glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
//		gl.glEnable(GL.GL_TEXTURE_2D);
//
//		if (tex != null) tex.bind();
//
//        gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
//	    glu.gluSphere(planet, radius, 16, 16);
//	    glu.gluDeleteQuadric(planet);
//	    gl.glPopMatrix();
//	    gl.glPopMatrix();
//	}
//

//	private void letThereBeMoon(GL gl)
//	{
//
//	}
//
//	private void giveMeSomeRingsWithThis(GL gl)
//	{
//
//	}
//
//	private void letThereBeSpecial(GL gl)
//	{
//		//will be used to generate cool other stuff
//	}
	
	private void letThereBeLight(GL gl)
	{
		// Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-30, 0, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};

        // Set light parameters.
        // GL_SPECULAR to other ?
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL.GL_LIGHT1);
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
