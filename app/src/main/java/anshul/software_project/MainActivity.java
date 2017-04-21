package anshul.software_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView image = (ImageView) findViewById(R.id.imageView3);
        final Intent i = new Intent(this, HomeActivity.class);

        new CountDownTimer(4500,150) {
            int j = 1;

            @Override
            public void onTick(long millisUntilFinished) {

                if(j <= 9) {
                    image.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_0" + j, "drawable", getPackageName())));
                }

                else{
                    image.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("_" + j, "drawable", getPackageName())));
                }

                j++;
                Log.i("custom",""+j);
            }

            @Override
            public void onFinish() {
                startActivity(i);
            }
        }.start();
    }
}
