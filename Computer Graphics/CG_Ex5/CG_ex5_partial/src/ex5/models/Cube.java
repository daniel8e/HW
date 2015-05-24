package ex5.models;

import javax.media.opengl.GL;

public class Cube implements IRenderable {
    private boolean isLightSpheres = true;

    @Override
    public void render(GL gl) {
        gl.glPushMatrix();

        gl.glScaled(0.5, 0.5, 0.5);

        gl.glColor3f(1.0f, 1.0f, 0.0f);
        // Page 27 ppt 06
        gl.glBegin(GL.GL_QUADS);
        {
            // 1
            gl.glColor3d(0, 0, 0);
            gl.glVertex3d(1, 1, 1);
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(1, 1, -1);
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(-1, 1, -1);
            gl.glColor3d(0, 1, 1);
            gl.glVertex3d(-1, 1, 1);

            // 2
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-1, 1, 1);
            gl.glColor3d(1, 0, 1);
            gl.glVertex3d(-1, -1, -1);
            gl.glColor3d(1, 1, 0);
            gl.glVertex3d(-1, 1, -1);
            gl.glColor3d(1, 1, 1);
            gl.glVertex3d(-1, -1, 1);

            // 3 - back
            gl.glColor3d(0, 0, 0);
            gl.glVertex3d(-1, -1, -1);
            gl.glColor3d(0, 1, 1);
            gl.glVertex3d(-1, 1, -1);
            gl.glColor3d(0.5, 0.7, 0);
            gl.glVertex3d(1, 1, -1);
            gl.glColor3d(1, 0.5, 0.2);
            gl.glVertex3d(1, -1, -1);

            // 4 - front
            gl.glColor3d(1, 0.5, 0);
            gl.glVertex3d(1, 1, 1);
            gl.glColor3d(0, 0, 0);
            gl.glVertex3d(-1, -1, 1);
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(1, -1, 1);
            gl.glColor3d(0, 1, 0);
            gl.glVertex3d(-1, 1, 1);


            // 5
            gl.glColor3d(0.5, 0.05, 0.1);
            gl.glVertex3d(1, -1, -1);
            gl.glColor3d(1, 0.5, 0.11);
            gl.glVertex3d(-1, -1, -1);
            gl.glColor3d(1, 0, 1);
            gl.glVertex3d(-1, -1, 1);
            gl.glColor3d(0, 1, 1);
            gl.glVertex3d(1, -1, 1);

            // 6
            gl.glColor3d(0, 1, 1);
            gl.glVertex3d(1, 1, 1);
            gl.glColor3d(0, 0, 1);
            gl.glVertex3d(1, -1, -1);
            gl.glColor3d(1, 0, 1);
            gl.glVertex3d(1, 1, -1);
            gl.glColor3d(1, 1, 1);
            gl.glVertex3d(1, -1, 1);
        }
        gl.glEnd();
        gl.glPopMatrix();

    }

    @Override
    public void init(GL gl) {

    }

    @Override
    public void control(int type, Object params) {
        switch (type) {
            case IRenderable.TOGGLE_LIGHT_SPHERES:
            {
                isLightSpheres = ! isLightSpheres;
                break;
            }
            default:
                System.out.println("Control type not supported: " + toString() + ", " + type);
        }
    }

    @Override
    public boolean isAnimated() {
        return false;
    }

    @Override
    public void setCamera(GL gl) {

    }

    @Override
    public String toString() {
        return "Cube";
    }
}
