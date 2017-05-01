package anshul.software_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HomeActivity extends AppCompatActivity {

    public Intent dashboard;
    public Button submit, new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ImageView image = (ImageView) findViewById(R.id.image_logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        image.startAnimation(animation);
    }

    public void openDonorList(View view) {
        final String username = ((EditText) findViewById(R.id.username_field)).getText().toString();
        final String password = ((EditText) findViewById(R.id.password_field)).getText().toString();
        submit = (Button) findViewById(R.id.submit_button);
        new_user = (Button) findViewById(R.id.user_button);

        dashboard = new Intent(this, Dashboard.class);

        //The URL to which GET request is sent
        String REGISTER_URL = ("http://dheerajprojects.gear.host/web_server.php?username='" + Uri.encode(username) + "'&password=\"" + Uri.encode(password) + "\"");

        //Check if any of the fields are empty
        if (username.length() == 0 || password.length() == 0) {
            Toast.makeText(HomeActivity.this, "The fields have not been completed!", Toast.LENGTH_SHORT).show();
        } else {
            //Contact the server to add the data from the user
            StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Check if the entered password is correct
                            if (response.equals("Success")) {
                                dashboard.putExtra("Username", username);
                                dashboard.putExtra("Password", password);
                                startActivity(dashboard);
                                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                                submit.setEnabled(true);
                                new_user.setEnabled(true);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong combination of username & password!", Toast.LENGTH_LONG).show();
                                submit.setEnabled(true);
                                new_user.setEnabled(true);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            submit.setEnabled(true);
                            new_user.setEnabled(true);
                        }
                    });

            //Add the server request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            //Disable the button until further activity
            submit.setEnabled(false);
            new_user.setEnabled(false);
        }
    }

    public void openCreateList(View view) {
        Intent i = new Intent(this, DonorRegistration.class);
        startActivity(i);
    }

    public void openAboutus(View view) {
        Intent i = new Intent(this, AboutUs.class);
        startActivity(i);
    }
}
