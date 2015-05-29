package ex5.models;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Galaxy implements IRenderable {
	
	private GLU glu;
	private boolean isLightSpheres = true;
	private boolean isAxis = true;

	//Consts
	private float _shrinkConst = 1f; // this will help normalize galaxies size if needed
	
	private int _slices = 16;
	private int _stacks = 16;
	
	// Texture paths, resource folder
	private String PATH_TEX_SUN = "sunmap.png";
	private String PATH_TEX_MERCURY = "mercurymap.png";
	private String PATH_TEX_VENUS = "venusmap.png";
	private String PATH_TEX_EARTH = "earthmap1k.png";
	private String PATH_TEX_MOON = null;
	private String PATH_TEX_MARS = "mars_1k_color.png";
	private String PATH_TEX_JUPITER = "jupitermap.png";
	private String PATH_TEX_SATURN = null;
	private String PATH_TEX_SATURN_RINGS = "saturnmap.png";
	private String PATH_TEX_URANUS = "uranusmap.png";
	private String PATH_TEX_NEPTUNE = "neptunemap.png";
	private String PATH_TEX_PLUTO = "plutomap1k.png";
	
	// Texture consts - http://planetpixelemporium.com/
	private Texture TEX_SUN;
	private Texture TEX_MERCURY;
	private Texture TEX_VENUS;
	private Texture TEX_EARTH;
	private Texture TEX_MOON;
	private Texture TEX_MARS;
	private Texture TEX_JUPITER;
	private Texture TEX_SATURN;
	private Texture TEX_SATURN_RINGS;
	private Texture TEX_URANUS;
	private Texture TEX_NEPTUNE;
	private Texture TEX_PLUTO;
	
	// radiuses - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/
	// from ex05, Don’t implement the sizes with the true physical proportions (orbit and
	// plants’ radiuses) the true proportions are hard to visualize, but make sure that what is bigger
	// and farther, stays that way.
	// See Arbitrary sizes for our exercise
	private float RADIUS_SUN = 696.342f;
	private float RADIUS_MERCURY = 2.439f;
	private float RADIUS_VENUS = 6.051f;
	private float RADIUS_EARTH = 6.731f;
	private float RADIUS_MOON = 1.738f;
	private float RADIUS_MARS = 3.396f;
	private float RADIUS_JUPITER = 71.492f;
	private float RADIUS_SATURN = 60.258f;
	private float RADIUS_URANUS = 25.559f;
	private float RADIUS_NEPTUNE = 24.764f;
	private float RADIUS_PLUTO = 1.195f;
	
	// Arbitrary sizes for our exercise - order by size
	private float SIZE_SUN = 1f;

	// Significant size jump
	private float SIZE_JUPITER = 0.43f * SIZE_SUN;
	private float SIZE_SATURN = 0.4f * SIZE_SUN;

	// Significant size jump
	private float SIZE_URANUS = 0.33f * SIZE_SUN;
	private float SIZE_NEPTUNE = 0.3f * SIZE_SUN;

	// Significant size jump
	private float SIZE_EARTH = 0.1959f * SIZE_SUN;
	private float SIZE_VENUS = 0.1857f * SIZE_SUN;
	private float SIZE_MARS = 0.1755f * SIZE_SUN;
	private float SIZE_MERCURY = 0.1653f * SIZE_SUN;
	private float SIZE_MOON = 0.154f * SIZE_SUN;
	private float SIZE_PLUTO = 0.133f * SIZE_SUN;


	//Distances from sun
	private float DISTANCE_MERCURY = 1.5f;
	private float DISTANCE_VENUS = 3f;
	private float DISTANCE_EARTH = 4f;
	private float DISTANCE_MOON = 1f; // ? should be against sun or proportional to around earth
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
	private float INCLINATION_MOON = 5.1f;
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
		float shrink = 1f;
		
		// what about stars orbits ?
		drawSun(gl, glu, TEX_SUN, SIZE_SUN, shrink);
		planetCreator(gl, glu, TEX_MERCURY, SIZE_MERCURY, INCLINATION_MERCURY, DISTANCE_MERCURY, shrink);
		planetCreator(gl, glu, TEX_VENUS, SIZE_VENUS, INCLINATION_VENUS, DISTANCE_VENUS, shrink);
		planetCreator(gl, glu, TEX_EARTH, SIZE_EARTH, INCLINATION_EARTH, DISTANCE_EARTH, shrink);
		// moon ?
		planetCreator(gl, glu, TEX_MARS, SIZE_MARS, INCLINATION_MARS, DISTANCE_MARS, shrink);
		planetCreator(gl, glu, TEX_JUPITER, SIZE_JUPITER, INCLINATION_JUPITER, DISTANCE_JUPITER, shrink);
		planetCreator(gl, glu, TEX_SATURN, SIZE_SATURN, INCLINATION_SATURN, DISTANCE_SATURN, shrink);
		// rings ?
		planetCreator(gl, glu, TEX_URANUS, SIZE_URANUS, INCLINATION_URANUS, DISTANCE_URANUS, shrink);
		planetCreator(gl, glu, TEX_NEPTUNE, SIZE_NEPTUNE, INCLINATION_NEPTUNE, DISTANCE_NEPTUNE, shrink);
		planetCreator(gl, glu, TEX_PLUTO, SIZE_PLUTO, INCLINATION_PLUTO, DISTANCE_PLUTO, shrink);
		// letThereBeSpecial(gl);

	}
	
	private void materializer(GL gl, float[] nColors, Texture tex)
	{
		float[] rgba = {1f, 1f, 1f};
		// tex.enable();
		// tex.bind();
		
		// Set material properties.
		if (nColors.length > 2)
		{
			rgba = nColors;
		}

        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
	}
	
	private void drawSun(GL gl, GLU glu, Texture tex, float radius, float shrinkParam)
	{
		gl.glPushMatrix();
	    materializer(gl, new float[1], tex);
	    
	    gl.glPushMatrix();
		GLUquadric planet = glu.gluNewQuadric();
		glu.gluQuadricTexture(planet, true);
		glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
		glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
		gl.glEnable(GL.GL_TEXTURE_2D);

		if (tex != null) tex.bind();

        gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
	    glu.gluSphere(planet, radius, 16, 16);
	    glu.gluDeleteQuadric(planet);
	    gl.glPopMatrix();
	    gl.glPopMatrix();
	}

	// http://slabode.exofire.net/circle_draw.shtml
	void drawCircle(GL gl, double cx, double cy, double r)
	{
		double num_segments = 25 * Math.sqrt(r);
		double theta = 2 * 3.1415926 / num_segments;
		double tangetial_factor = Math.tan(theta);//calculate the tangential factor

		double radial_factor = Math.cos(theta);//calculate the radial factor

		double x = r;//we start at angle = 0

		double y = 0;

		gl.glLineWidth(1);

		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glColor3d(1, 0.2, 0.2);
		for(int ii = 0; ii < num_segments; ii++) {
			gl.glVertex2d(x + cx, y + cy);//output vertex

			//calculate the tangential vector
			//remember, the radial vector is (x, y)
			//to get the tangential vector we flip those coordinates and negate one of them

			double tx = -y;
			double ty = x;

			//add the tangential vector

			x += tx * tangetial_factor;
			y += ty * tangetial_factor;

			//correct using the radial factor

			x *= radial_factor;
			y *= radial_factor;
		}
		gl.glEnd();
	}

	private void renderAxes(GL gl, double palnetSize) {
		if (!isAxis) return;
		gl.glLineWidth(2);
		boolean flag = gl.glIsEnabled(GL.GL_LIGHTING);
		gl.glDisable(GL.GL_LIGHTING);
		gl.glBegin(GL.GL_LINES);

		double radius = 1.5 * palnetSize;

		gl.glColor3d(1, 0, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(radius, 0, 0);

		gl.glColor3d(0, 1, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, radius, 0);

		gl.glColor3d(0, 0, 1);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 0, radius);

		gl.glEnd();
		if(flag)
			gl.glEnable(GL.GL_LIGHTING);
	}

	private void planetCreator(GL gl, GLU glu, Texture tex, float radius, float inclination, float distanceFromSun, float shrinkParam)
	{
		gl.glPushMatrix();
		materializer(gl, new float[1], tex);
		gl.glPushMatrix();


	    GLUquadric planet = glu.gluNewQuadric();
		glu.gluQuadricTexture(planet, true);
		glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
		glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
		gl.glEnable(GL.GL_TEXTURE_2D);

		if (tex != null) tex.bind();

		gl.glRotated(inclination, 0.0D, 0.0D, 1.0D);
	    gl.glRotated(0, 0.0D, 1.0D, 0.0D);
		gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);

		drawCircle(gl, 0, 0, distanceFromSun);

		gl.glTranslated(distanceFromSun, 0.0D, 0.0D);
		gl.glRotated(0, 0.0D, 1.0D, 0.0D);

		glu.gluSphere(planet, radius, 48, 48);
		renderAxes(gl, radius);

		glu.gluDeleteQuadric(planet);
	    
//	    if (tex.equals(TEX_EARTH))
//	    {
//	    	letThereBeMoon(gl);
//	    }
//	    
//	    if(tex.equals(TEX_SATURN))
//	    {
//	    	giveMeSomeRingsWithThis(gl);
//	    }


		gl.glPopMatrix();
		gl.glPopMatrix();

//		gl.glPushMatrix();
//		gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
//		gl.glPopMatrix();
	}
	
	private void letThereBeMoon(GL gl)
	{
		
	}
	
	private void giveMeSomeRingsWithThis(GL gl)
	{
		
	}
	
	private void letThereBeSpecial(GL gl)
	{
		//will be used to generate cool other stuff
	}
	
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

		TEX_SUN = textureInit(PATH_TEX_SUN);
		TEX_MERCURY = textureInit(PATH_TEX_MERCURY);
		TEX_VENUS = textureInit(PATH_TEX_VENUS);
		TEX_EARTH = textureInit(PATH_TEX_EARTH);
		TEX_MOON = textureInit(PATH_TEX_MOON);
		TEX_MARS = textureInit(PATH_TEX_MARS);
		TEX_JUPITER = textureInit(PATH_TEX_JUPITER);
		TEX_SATURN = textureInit(PATH_TEX_SATURN);
		TEX_SATURN_RINGS = textureInit(PATH_TEX_SATURN_RINGS);
		TEX_URANUS = textureInit(PATH_TEX_URANUS);
		TEX_NEPTUNE = textureInit(PATH_TEX_NEPTUNE);
		TEX_PLUTO = textureInit(PATH_TEX_PLUTO);
	}
	
	private Texture textureInit(String fileName)
	{
		if (fileName == null) {
			return null;
		}
		// Texture, still crashing on stream is null
		try {
            InputStream stream = Galaxy.class.getClassLoader().getResourceAsStream("res" + File.separator + fileName);
            TextureData data = TextureIO.newTextureData(stream, false, "png");
            return TextureIO.newTexture(data);
        }
        catch (IOException ex) {
            ex.printStackTrace();
			System.out.println(ex.getMessage());
//            System.exit(1);
        }

		return null;
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
			case IRenderable.TOGGLE_AXES:
			{
				if (params != null) isAxis = (boolean)params;
			}
	        default:
	            System.out.println("Control type not supported: " + toString() + ", " + type);
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
