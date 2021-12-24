package MAD.sequence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    int chooice;
    int Success;
    int current = 0;
    int[] array;
    int[] sq;
    boolean correct = false;
    TextView txtTime;


    boolean choosing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);


        int[] color = getIntent().getIntArrayExtra("colorArray");
        sq = getIntent().getIntArrayExtra("gameSequence");

        Bundle bundle = getIntent().getExtras();
        chooice = bundle.getInt("sequenceCount");
        txtTime=findViewById(R.id.txtTime);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button1.setBackgroundColor(color[0]);
        button2.setBackgroundColor(color[1]);
        button3.setBackgroundColor(color[2]);
        button4.setBackgroundColor(color[3]);

        array = new int[sq.length];
    }
        @Override
        public void onSensorChanged (SensorEvent event){


            float x = event.values[0];
            float y = event.values[1];


            if (x < -0.8f & !choosing) {

                check();
                array[current] = 1;
                checkIfCorrect();

                current++;

                if (!correct) {
                    Intent M = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(M);
                }


            } else if (x > 0.8f & !choosing) {

                check();
                array[current] = 4;
                checkIfCorrect();

                current++;
                if (!correct) {
                    Intent M = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(M);
                }


            }

            if (y > 0.35f & !choosing) {

                check();
                array[current] = 3;
                checkIfCorrect();

                current++;

                if (!correct) {
                    Intent M = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(M);
                }


            } else if (y < -0.24f & !choosing) {

                check();
                array[current] = 2;
                checkIfCorrect();
                current++;

                if (!correct) {
                    Intent M = new Intent(MainActivity3.this, MainActivity4.class);
                    startActivity(M);
                }

            }
        }
        public void check () {

            choosing = true;


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    txtTime.setText("Your choice will be saved in.." + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    txtTime.setText("Please choose the next sequence");

                    chooice--;

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            choosing = false;


                        }
                    }, 2000); // Millisecond 1000 = 1 sec

                }
            }.start();

            if (chooice == 1) {
                Success = Success + 2;
                Intent M = new Intent(MainActivity3.this, MainActivity2.class);
                M.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                M.putExtra("Success", Success);
                startActivity(M);
            }

        }
        public void checkIfCorrect () {

            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0) {


                    if (array[i] == sq[i]) {
                        correct = true;

                    } else {

                        correct = false;
                    }
                } else {
                    continue;
                }
            }
        }
        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){

        }

        /*
         * When the app is brought to the foreground - using app on screen
         */
        protected void onResume () {
            super.onResume();
            // turn on the sensor
            mSensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        /*
         * App running but not on screen - in the background
         */
        protected void onPause () {
            super.onPause();
            mSensorManager.unregisterListener(this);    // turn off listener to save power    }
        }

    }
