package ntu.tw.alon21034.spheremodelcontroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vince on 16/6/13.
 */
public class SphereModelController implements View.OnTouchListener, SensorEventListener {

    public interface OnTouchSphereModelListener {
        void onScaleAndTranslate(float[] viewPoint);


    }

    private OnTouchSphereModelListener mListener;
    private SphereControllerWrapper sphere;

    private float[] rotationMatrix;

    public SphereModelController(Context context, OnTouchSphereModelListener listener) {
        mListener = listener;

        sphere = new SphereControllerWrapper();
        rotationMatrix = new float[9];
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR || event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private class SphereControllerWrapper {
        Point3D initialViewPoint;
        Point3D viewPoint;

        public SphereControllerWrapper() {
            initialViewPoint = new Point3D();
            viewPoint = new Point3D();
        }

    }

    private class Point3D {

        public float[] plain;
        public float[] sphere;

        public Point3D() {
            plain = new float[2];
            sphere = new float[3];
        }

        public Point3D(float x, float y) {
            this();
            plain[0] = x;
            plain[1] = y;
            getSphereCoordFromPlainCoord(plain, sphere);
        }

        public Point3D(float x, float y, float z) {
            this();
            sphere[0] = x;
            sphere[1] = y;
            sphere[2] = z;
            getPlainCoordFromSphereCoord(plain, sphere);
        }

        private void getPlainCoordFromSphereCoord(float[] p, float[] s) {

        }

        private void getSphereCoordFromPlainCoord(float[] s, float[] p) {

        }
    }
}
