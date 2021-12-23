package MAD.sequence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        int[] color = getIntent().getIntArrayExtra("colorArray");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button1.setBackgroundColor(color[0]);
        button2.setBackgroundColor(color[1]);
        button3.setBackgroundColor(color[2]);
        button4.setBackgroundColor(color[3]);
    }
}