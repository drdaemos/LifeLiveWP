package ru.confluent.lifelivewp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.util.Stack;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.lights.ALight;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.math.vector.Vector3;
import rajawali.primitives.Line3D;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import ru.confluent.lifelivewp.game.Scene;

public class Renderer extends RajawaliRenderer {

    static int tick = 0;
    long gameTimer = 0;
    long fpsTimer = 0;
    static int fps = 0;
    public int width = 10;
    public int height = 20;

    Plane mPlane;
    Scene gameScene;
	public Renderer(Context context) {
		super(context);
        setFrameRate(60);
	}

	public void initScene() {
//        Camera2D camera = new Camera2D();
//        getCurrentScene().switchCamera(camera);

		ALight light = new DirectionalLight(0,0,-1f);
		light.setPower(6);
		
		getCurrentScene().addLight(light);
		getCurrentCamera().setPosition(width/2, height/2, -20);
		getCurrentCamera().setLookAt(width/2, height/2, 0);

        gameScene = new Scene(getCurrentScene(), width, height);

        //addXYZlines();
	}

    private void addXYZlines() {
        Stack<Vector3> points = new Stack<Vector3>();
        points.add(new Vector3(-7, 0, 0));
        points.add(new Vector3(7, 0, 0));

        Line3D XLine = new Line3D(points,1,Color.GREEN);
        Material material = new Material();
        material.setColor(Color.GREEN);
        XLine.setMaterial(material);

        points = new Stack<Vector3>();
        points.add(new Vector3(0, -7, 0));
        points.add(new Vector3(0, 7, 0));
        Line3D YLine = new Line3D(points,1,Color.RED);
        material = new Material();
        material.setColor(Color.RED);
        YLine.setMaterial(material);

        points = new Stack<Vector3>();
        points.add(new Vector3(0, 0, -7));
        points.add(new Vector3(0, 0, 7));
        Line3D ZLine = new Line3D(points,1,Color.BLUE);
        material = new Material();
        material.setColor(Color.BLUE);
        ZLine.setMaterial(material);

        getCurrentScene().addChild(XLine);
        getCurrentScene().addChild(YLine);
        getCurrentScene().addChild(ZLine);
    }

    public static int getTick() {
        return tick;
    }

    static void setTick(int tick) {
        Renderer.tick = tick;
    }

    public static int getFps() {
        return fps;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}

	public void onDrawFrame(GL10 glUnused) {
        tick++;
        if (System.currentTimeMillis() > gameTimer + 200) {
            gameTimer = System.currentTimeMillis();
            gameScene.update();
        }

        if (System.currentTimeMillis() > fpsTimer + 1000) {
            fpsTimer = System.currentTimeMillis();
            fps = tick;
            setTick(0);
            Log.d("fps", getFps()+ " fps");
        }
		super.onDrawFrame(glUnused);
	}
}
