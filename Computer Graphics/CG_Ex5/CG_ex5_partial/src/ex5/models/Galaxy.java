package ex5.models;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;

public class Galaxy implements IRenderable {
	
	private GLU glu;
	private boolean isLightSpheres = true;
	
	private float _shrinkConst = 1f; // this will help normalize galaxies size if needed
	
	private Texture TEX_EARTH;
	
	@Override
	public void render(GL gl) 
	{
        // Clear screen.
        //gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        prepareLightning(gl);

        drawEarth(gl);
        //gl.glEnd();

        // Restore old state.
        gl.glPopMatrix();
	}
	
	private void setStarMaterial(GL gl)
	{
		// Set material properties.
		float[] rgba = {1f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
	}
	
	private void drawEarth(GL gl)
	{
		float radius = 6.378f * _shrinkConst;
        int slices = 16;
        int stacks = 16;
        
        setStarMaterial(gl);
        
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
