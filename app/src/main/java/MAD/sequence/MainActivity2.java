package MAD.sequence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import java.util.Random;
import java.util.stream.IntStream;

public class MainActivity2 extends AppCompatActivity {


    int[] colorArray = new int[4];
    int[] gameSequence = new int[120];
    int arrayIndex = 0;
    int sequenceCount = 4, n = 0;
    Animation anim;


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private final int b1 = 1;
    private final int b2 = 2;
    private final int b3 = 3;
    private final int b4 = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();


        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            int red = r.nextInt(256);
            int green = r.nextInt(256);
            int blue = r.nextInt(256);
            int color = Color.rgb(red, green, blue);
            final int finalColor = color;
            boolean contains = IntStream.of(colorArray).anyMatch(x -> x == finalColor);
            if (contains) {
                r = new Random();
                red = r.nextInt(256);
                green = r.nextInt(256);
                blue = r.nextInt(256);
                color = Color.rgb(red, green, blue);
            }
            colorArray[i] = color;
        }
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button1.setBackgroundColor(colorArray[0]);
        button2.setBackgroundColor(colorArray[1]);
        button3.setBackgroundColor(colorArray[2]);
        button4.setBackgroundColor(colorArray[3]);
    }

    CountDownTimer ct = new CountDownTimer(6000, 1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("colorArray", colorArray);
            intent.putExtra("gameSequence", gameSequence);
            startActivity(intent);
        }
    };
    private void oneButton() {

        n = getRandom(sequenceCount);
        switch (n) {
            case 1:
                flashButton(button1);
                gameSequence[arrayIndex++] = b1;
                break;
            case 2:
                flashButton(button2);
                gameSequence[arrayIndex++] = b2;
                break;
            case 3:
                flashButton(button3);
                gameSequence[arrayIndex++] = b3;
                break;
            case 4:
                flashButton(button4);
                gameSequence[arrayIndex++] = b4;
                break;
            default:
                break;
        }   // end switch
    }

    private void flashButton(Button button) {

        anim = new AlphaAnimation(1, 0);
        anim.setDuration(1000); //You can manage the blinking time with this parameter
        anim.setRepeatCount(0);
        button.startAnimation(anim);
    }

    private int getRandom(int maxValue) {

        return ((int) ((Math.random() * maxValue) + 1));
    }


    public void btnPlay(View view) {
       ct.start();
    }
}