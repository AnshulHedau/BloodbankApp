package anshul.software_project;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    Button go,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)    {
                Intent intent = new Intent(getBaseContext(), PayPalReg.class);
                startActivity(intent);
            }
        });
    }

    public void goback(View view)
    {
        Intent i = new Intent(getBaseContext(),HomeActivity.class);
        startActivity(i);
        finish();
    }

}
