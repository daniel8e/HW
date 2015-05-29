package ex5.models;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;

public class Galaxy implements IRenderable {
	
	private GLU glu;
	private boolean isLightSpheres = true;
	
	//Consts
	private float _shrinkConst = 1f; // this will help normalize galaxies size if needed
	
	private int _slices = 16;
	private int _stacks = 16;
	
	// Texture paths, resource folder
	private String PATH_TEX_SUN;
	private String PATH_TEX_MERCURY;
	private String PATH_TEX_VENUS;
	private String PATH_TEX_EARTH;
	private String PATH_TEX_MOON;
	private String PATH_TEX_MARS;
	private String PATH_TEX_JUPITER;
	private String PATH_TEX_SATURN;
	private String PATH_TEX_SATURN_RINGS;
	private String PATH_TEX_URANUS;
	private String PATH_TEX_NEPTUNE;
	private String PATH_TEX_PLUTO;
	
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
	
	// Arbitrary sizes for our exercise
	private float SIZE_SUN = 6f;
	private float SIZE_MERCURY = 0.2439f;
	private float SIZE_VENUS = 0.6051f;
	private float SIZE_EARTH = 6.731f;
	private float SIZE_MOON = 1.738f;
	private float SIZE_MARS = 3.396f;
	private float SIZE_JUPITER = 7.1492f;
	private float SIZE_SATURN = 6.0258f;
	private float SIZE_URANUS = 2.5559f;
	private float SIZE_NEPTUNE = 2.4764f;
	private float SIZE_PLUTO = 1.195f;
	
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
	private float INCLINATION_MERCURY = 7;
	private float INCLINATION_VENUS = 3.4f;
	private float INCLINATION_EARTH = 0;
	private float INCLINATION_MOON = 5.1f;
	private float INCLINATION_MARS = 1.9f;
	private float INCLINATION_JUPITER = 1.3f;
	private float INCLINATION_SATURN = 2.5f;
	private float INCLINATION_URANUS = 0.8f;
	private float INCLINATION_NEPTUNE = 1.8f;
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
	    
        gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
	    glu.gluSphere(planet, 1.0D, 16, 16);
	    glu.gluDeleteQuadric(planet);
	    gl.glPopMatrix();
	    gl.glPopMatrix();
	}

	void DrawCircle(GL gl, double cx, double cy, double r, double num_segments)
	{
		double theta = 2 * 3.1415926 / num_segments;
		double tangetial_factor = Math.tan(theta);//calculate the tangential factor

		double radial_factor = Math.cos(theta);//calculate the radial factor

		double x = r;//we start at angle = 0

		double y = 0;

		gl.glBegin(GL.GL_LINE_LOOP);
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
        
	    gl.glRotated(inclination, 0.0D, 0.0D, 1.0D);
	    gl.glRotated(0, 0.0D, 1.0D, 0.0D);
	    gl.glTranslated(distanceFromSun, 0.0D, 0.0D);
	    gl.glRotated(0, 0.0D, 1.0D, 0.0D);
	    
	    gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);
	    glu.gluSphere(planet, 0.2, 48, 48);
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
		textureInit(TEX_SUN, PATH_TEX_SUN);
		textureInit(TEX_MERCURY, PATH_TEX_MERCURY);
		textureInit(TEX_VENUS, PATH_TEX_VENUS);
		textureInit(TEX_EARTH, PATH_TEX_EARTH);
		textureInit(TEX_MOON, PATH_TEX_MOON);
		textureInit(TEX_MARS, PATH_TEX_MARS);
		textureInit(TEX_JUPITER, PATH_TEX_JUPITER);
		textureInit(TEX_SATURN, PATH_TEX_SATURN);
		textureInit(TEX_SATURN_RINGS, PATH_TEX_SATURN_RINGS);
		textureInit(TEX_URANUS, PATH_TEX_URANUS);
		textureInit(TEX_NEPTUNE, PATH_TEX_NEPTUNE);
		textureInit(TEX_PLUTO, PATH_TEX_PLUTO);
	}
	
	private void textureInit(Texture tex, String fileName)
	{
		// Texture, still crashing on stream is null
//		try {
//            InputStream stream = Galaxy.class.getClassLoader().getResourceAsStream(fileName);
//            TextureData data = TextureIO.newTextureData(stream, false, "png");
//            tex = TextureIO.newTexture(data);
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
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
