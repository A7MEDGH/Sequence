package MAD.sequence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    TextView txtScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtScore=findViewById(R.id.txtScore);
        int Score = getIntent().getIntExtra("score",-1);
        txtScore.setText(String.valueOf("Your Score is: " + Score));
    }

    public void btnPlayAgain(View view) {
        Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
        startActivity(intent);
    }

    public void btnScore(View view) {
        Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
        startActivity(intent);
    }
}