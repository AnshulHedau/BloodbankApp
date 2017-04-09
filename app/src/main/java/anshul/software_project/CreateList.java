package anshul.software_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CreateList extends AppCompatActivity {

    //Initializing the required variables
    public String blood_type;
    public Spinner spinner;
    public Button submit;
    public Intent home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        //Change the title of the screen
        setTitle("Registration");

        //Initialize the spinner
        spinner = (Spinner) findViewById(R.id.blood_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //Function to send the data to the server and open the Home page, if successful.
    public void openHome(View view) {
        //Get all the EditText fields & Submit button
        EditText uname_value = (EditText) findViewById(R.id.username);
        EditText pass_value = (EditText) findViewById(R.id.password);
        EditText name_value = (EditText) findViewById(R.id.name);
        EditText aller_value = (EditText) findViewById(R.id.allergy);
        EditText loc_value = (EditText) findViewById(R.id.location);
        submit = (Button) findViewById(R.id.submit_button);

        home_page = new Intent(this,HomeActivity.class);

        //Get the blood type from the spinner
        blood_type = spinner.getSelectedItem().toString();

        //The URL to which GET request is sent
        String REGISTER_URL = ("http://dheerajprojects.gear.host/web_server.php?type='insert'&username='" + uname_value.getText().toString() + "'&password='" + pass_value.getText().toString() + "'&name='" + name_value.getText().toString() + "'&bloodtype='" + Uri.encode(blood_type) + "'&location='" + loc_value.getText().toString() + "'&allergies='" + aller_value.getText().toString() + "'");

        //Check if any of the fields are empty
        if (name_value.getText().length() == 0 || loc_value.getText().length() == 0 || pass_value.getText().length() == 0 || uname_value.getText().length() == 0) {
            Toast.makeText(CreateList.this, "The fields have not been completed!", Toast.LENGTH_SHORT).show();
        }

        else{
            //Contact the server to add the data from the user
            StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(home_page);
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateList.this,error.toString(),Toast.LENGTH_LONG).show();
                            submit.setEnabled(true);
                        }
                    });

            //Add the server request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            //Disable the button until further activity
            submit.setEnabled(false);

        }
    }
}




