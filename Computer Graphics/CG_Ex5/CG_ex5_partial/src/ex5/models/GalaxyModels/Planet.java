/*
 * Exercise 5 - Computer Graphics
 * Matan Gidnian	200846905
 * Nitsan Bracha 	300590155
 */
package ex5.models.GalaxyModels;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import ex5.models.Galaxy;
import ex5.models.IRenderable;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Planet implements IRenderable {

    protected Texture tex;
    protected boolean isLightSpheres;
    protected boolean isAxis;

    protected GLU glu;
    protected String pathTex;
    protected float size;
    protected float inclination;
    protected float distancesFromMainGravity;

    public Planet (GLU glu, String pathTex, float size, float inclination, float distancesFromMainGravity) {
        this.glu = glu;
        this.pathTex = pathTex;
        this.size = size;
        this.inclination = inclination;
        this.distancesFromMainGravity = distancesFromMainGravity;
    }

    @Override
    public void render(GL gl) {
        planetCreator(gl);
    }

    protected void materializer(GL gl, float[] nColors)
    {
        float[] rgba = {1f, 1f, 1f};

        // Set material properties.
        if (nColors.length > 2)
        {
            rgba = nColors;
        }

        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
    }

    protected void planetCreator(GL gl)
    {
        float radius = size;

        gl.glPushMatrix();
        materializer(gl, new float[1]);
        gl.glPushMatrix();


        GLUquadric planet = glu.gluNewQuadric();
        glu.gluQuadricTexture(planet, true);
        glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
        glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
        gl.glEnable(GL.GL_TEXTURE_2D);

        if (tex != null) {
            tex.enable();
            tex.bind();
        }



        gl.glRotated(inclination, 0.0D, 0.0D, 1.0D);
        gl.glRotated(0, 0.0D, 1.0D, 0.0D);
        gl.glRotated(-90.0D, 1.0D, 0.0D, 0.0D);

        if (tex != null) {
            tex.disable();
        }

        drawCircle(gl, 0, 0, distancesFromMainGravity);

        if (tex != null) {
            tex.enable();
        }

        gl.glTranslated(distancesFromMainGravity, 0.0D, 0.0D);
        gl.glRotated(0, 0.0D, 1.0D, 0.0D);

        glu.gluSphere(planet, radius, 48, 48);
        renderAxes(gl, radius);
        insertRings(gl);

        glu.gluDeleteQuadric(planet);

        gl.glPopMatrix();
        gl.glPopMatrix();

        if (tex != null) {
            tex.disable();
        }
    }

    protected void insertRings(GL gl) {

    }

    @Override
    public void init(GL gl) {
        //glu = new GLU();
        tex = textureInit(pathTex);
    }

    private Texture textureInit(String fileName)
    {
        if (fileName == null) {
            return null;
        }
        // Texture, still crashing on stream is null
        try {
            InputStream stream = Galaxy.class.getResourceAsStream("/res/" + fileName);
            TextureData data = TextureIO.newTextureData(stream, false, "png");
            return TextureIO.newTexture(data);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("Filename: " + fileName);
            System.out.println("fullpath: " + "res" + File.separator + fileName);
        }

        return null;
    }


    protected void renderAxes(GL gl, double planetSize) {
        if (!isAxis) return;
        gl.glLineWidth(2);
        boolean flag = gl.glIsEnabled(GL.GL_LIGHTING);
        gl.glDisable(GL.GL_LIGHTING);
        gl.glBegin(GL.GL_LINES);

        double radius = 1.5 * planetSize;

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

    @Override
    public void control(int type, Object params) {
        switch (type)
        {
            case IRenderable.TOGGLE_LIGHT_SPHERES:
            {
                isLightSpheres = !isLightSpheres;
                break;
            }
            case IRenderable.TOGGLE_AXES:
            {
                if (params != null) isAxis = (Boolean)params;
            }
            default:
                System.out.println("Control type not supported: " + toString() + ", " + type);
        }
    }

    // http://slabode.exofire.net/circle_draw.shtml
    protected void drawCircle(GL gl, double cx, double cy, double r)
    {
        gl.glPushMatrix();

        double num_segments = 25 * Math.sqrt(r);
        double theta = 2 * 3.1415926 / num_segments;
        double tangetial_factor = Math.tan(theta);//calculate the tangential factor

        double radial_factor = Math.cos(theta);//calculate the radial factor

        double x = r;//we start at angle = 0

        double y = 0;

        gl.glLineWidth(1);

        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glColor3d(1.0D, 1.0D, 1.0D);
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

        gl.glPopMatrix();
    }

    @Override
    public boolean isAnimated() {
        return false;
    }

    @Override
    public void setCamera(GL gl) {

    }
}
