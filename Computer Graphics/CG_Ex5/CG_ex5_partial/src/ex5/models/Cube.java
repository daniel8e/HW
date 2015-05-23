package ex5.models;

import javax.media.opengl.GL;

public class Cube implements IRenderable {
    private boolean isLightSpheres = true;

    @Override
    public void render(GL gl) {
        int x = 0;
        int y = -10;
        int s = 10;

        gl.glPushMatrix();

        gl.glTranslated(x, y, 0);
        gl.glScaled(s, s, 0);
        gl.glBegin(GL.GL_POLYGON);
        {
            gl.glVertex2d(-1, -1);
            gl.glVertex2d(1, -1);
            gl.glVertex2d(1, 1);
            gl.glVertex2d(-1, 1);
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
