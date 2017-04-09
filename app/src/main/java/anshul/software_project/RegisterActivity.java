package anshul.software_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button btnActivity1,btnActivity2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Intent i = new Intent(this,DonerRegistration.class);
        btnActivity1 = (Button) findViewById(R.id.Button1);
        btnActivity1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)    {

                startActivity(i);
            }
        });

        final Intent i2 = new Intent(this,BloodBankRegistration.class);
        btnActivity2 = (Button) findViewById(R.id.Button2);
        btnActivity2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)    {

                startActivity(i2);
            }
        });
    }
}