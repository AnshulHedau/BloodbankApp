package anshul.software_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button btnActivity1, btnActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnActivity1 = (Button) findViewById(R.id.Button1);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        btnActivity1.startAnimation(animation);
        final Intent i = new Intent(this, DonorRegistration.class);

        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(i);
            }
        });

        final Intent i2 = new Intent(this, BloodBankRegistration.class);
        btnActivity2 = (Button) findViewById(R.id.Button2);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        btnActivity2.startAnimation(animation);
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(i2);
            }
        });
    }
}
