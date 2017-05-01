package anshul.software_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Dashboard extends AppCompatActivity {

    public String Username;
    public String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Username = getIntent().getExtras().getString("Username");
        Password = getIntent().getExtras().getString("Password");

        ((TextView) findViewById(R.id.usernamedisplay)).setText("Hello " + Username);
    }

    public void openSearch(View view) {
        Intent donor_search = new Intent(this, DonorList.class);
        donor_search.putExtra("Username", Username);

        startActivity(donor_search);
    }

    public void openEditProfile(View view) {
        Intent profile_edit = new Intent(this, EditProfile.class);
        profile_edit.putExtra("Username", Username);
        profile_edit.putExtra("Password", Password);

        startActivity(profile_edit);
    }

    public void deleteAccount(View view) {
        String DELETE_URL = ("http://dheerajprojects.gear.host/web_server.php?delete_account&username='" + Uri.encode(Username) + "'");
        final Intent home = new Intent(this, HomeActivity.class);

        //Contact the server to add the data from the user
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DELETE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(home);

                        Toast.makeText(getApplicationContext(), "Your account has been deleted!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dashboard.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        //Add the server request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void userLogout(View view) {
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);

        finish();
    }
}
