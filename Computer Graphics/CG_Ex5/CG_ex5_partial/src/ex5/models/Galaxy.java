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
	
	// Texture consts
	private Texture TEX_SUN;
	private Texture TEX_MERCURY;
	private Texture TEX_VEVUS;
	private Texture TEX_EARTH;
	private Texture TEX_MOON;
	private Texture TEX_MARS;
	private Texture TEX_JUPITER;
	private Texture TEX_SATURN;
	private Texture TEX_URANUS;
	private Texture TEX_NEPTUNE;
	private Texture TEX_PLUTO;
	
	// radiuses - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/
	private float RADIUS_SUN = 696.342f;
	private float RADIUS_MERCURY = 2.439f;
	private float RADIUS_VEVUS = 6.051f;
	private float RADIUS_EARTH = 6.731f;
	private float RADIUS_MOON = 1.738f;
	private float RADIUS_MARS = 3.396f;
	private float RADIUS_JUPITER = 71.492f;
	private float RADIUS_SATURN = 60.258f;
	private float RADIUS_URANUS = 25.559f;
	private float RADIUS_NEPTUNE = 24.764f;
	private float RADIUS_PLUTO = 1.195f;
	
	// inclination - NASA fact sheet - http://nssdc.gsfc.nasa.gov/planetary/factsheet/
	private float INCLINATION_MERCURY = 7;
	private float INCLINATION_VEVUS = 3.4f;
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
        // Clear screen.
        //gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        prepareLightning(gl);
        
        letThereBeSun(gl);
//        letThereBeMercury(gl);
//        letThereBeVenus(gl);
//        letThereBeEarth(gl);
//        letThereBeMars(gl);
//        letThereBeJupiter(gl);
//        letThereBeSaturn(gl);
//        letThereBeUranus(gl);
//        letThereBeNeptune(gl);
//        letThereBePluto(gl);
//        letThereBeSpecial(gl);
        
       // gl.glEnd();

        // Restore old state.
        gl.glPopMatrix();
	}
	
	private void materializer(GL gl)
	{
		// Set material properties.
		float[] rgba = {1f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
	}
	
	private void letThereBeSun(GL gl)
	{   
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric sun = glu.gluNewQuadric();
        glu.gluQuadricTexture(sun, true);
        glu.gluQuadricDrawStyle(sun, GLU.GLU_FILL);
        glu.gluQuadricNormals(sun, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(sun, GLU.GLU_OUTSIDE);
        glu.gluSphere(sun, RADIUS_SUN * 0.001f, _slices, _stacks);
        glu.gluDeleteQuadric(sun);

        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeMercury(GL gl)
	{
		float radius = 6.371f * _shrinkConst;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric mercury = glu.gluNewQuadric();
        glu.gluQuadricTexture(mercury, true);
        glu.gluQuadricDrawStyle(mercury, GLU.GLU_FILL);
        glu.gluQuadricNormals(mercury, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(mercury, GLU.GLU_OUTSIDE);
        glu.gluSphere(mercury, radius, _slices, _stacks);
        glu.gluDeleteQuadric(mercury);

        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeVenus(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeEarth(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw moon
        letThereBeMoon(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeMoon(GL gl)
	{
		
	}
	
	private void letThereBeMars(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeJupiter(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeSaturn(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw rings
        giveMeSomeRingsWithThis(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void giveMeSomeRingsWithThis(GL gl)
	{
		
	}
	
	private void letThereBeUranus(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw rings
        giveMeSomeRingsWithThis(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeNeptune(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw rings
        giveMeSomeRingsWithThis(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBePluto(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw rings
        giveMeSomeRingsWithThis(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void letThereBeSpecial(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        materializer(gl);
        
        // Apply texture.
//        TEX_EARTH.enable();
//        TEX_EARTH.bind();
        
		// (possible styles: FILL, LINE, POINT).
        GLUquadric earth = glu.gluNewQuadric();
        glu.gluQuadricTexture(earth, true);
        glu.gluQuadricDrawStyle(earth, GLU.GLU_FILL);
        glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
        glu.gluSphere(earth, radius, slices, stacks);
        glu.gluDeleteQuadric(earth);

        //Draw rings
        giveMeSomeRingsWithThis(gl);
        
        // Save old state.
        gl.glPushMatrix();
	}
	
	private void prepareLightning(GL gl)
	{
		// Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-30, 0, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};

        // Set light parameters.
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL.GL_LIGHT1);
        gl.glEnable(GL.GL_LIGHTING);
	}
	
	@Override
	public void init(GL gl) {
		glu = new GLU();
		
		// Texture, still crashing on stream is null
//		try {
//            InputStream stream = Galaxy.class.getClassLoader().getResourceAsStream("EARTH_TEXTURE.png");
//            TextureData data = TextureIO.newTextureData(stream, false, "png");
//            TEX_EARTH = TextureIO.newTexture(data);
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
