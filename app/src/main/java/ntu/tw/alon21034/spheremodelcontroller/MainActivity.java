package ntu.tw.alon21034.spheremodelcontroller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends Activity implements SphereModelController.OnTouchSphereModelListener {

    private Point screenSize;

    private ImageView image;
    private Bitmap bitmap;
    private Canvas canvas;

    private SphereModelController listener;
    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get screen's size
        Display display = getWindowManager().getDefaultDisplay();
        screenSize = new Point();
        display.getSize(screenSize);

        image = (ImageView) findViewById(R.id.main_imageview);
        bitmap = Bitmap.createBitmap(screenSize.x, screenSize.y, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        image.setImageBitmap(bitmap);

        // (TODO) if api level >= 18, change to use TYPE_GAME_ROTATION_VECTOR
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        listener = new SphereModelController(this, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, rotationVectorSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }

    @Override
    public void onScaleAndTranslate(float[] viewPoint) {

    }
}
