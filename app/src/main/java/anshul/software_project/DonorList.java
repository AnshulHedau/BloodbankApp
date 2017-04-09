package anshul.software_project;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.required;
import static android.R.attr.x;
import static android.R.id.list;

public class DonorList extends AppCompatActivity {

    //Initializing the required variables
    public String blood_type;
    public Spinner spinner;
    public JSONArray search_results_array;
    public Button submit;
    public ListView donor_list;
    public ArrayList<String> search_results = new ArrayList<String>();
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        //Change the title of the screen
        setTitle("Dashboard");

        //Initialize the spinner
        spinner = (Spinner) findViewById(R.id.blood_type_dash);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void searchDonors(View view){
        //Get the blood type from the spinner
        blood_type = spinner.getSelectedItem().toString();
        submit = (Button) findViewById(R.id.blood_search);

        //Getting the ListView from the XML File & showing the details on the ListView
        donor_list = (ListView) findViewById(R.id.donor_results);
        search_results.clear();

        //The URL to which GET request is sent
        String SEARCH_URL = ("http://dheerajprojects.gear.host/web_server.php?bloodtype='" + Uri.encode(blood_type) + "'");

        //Contact the server to add the data from the user
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SEARCH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            search_results_array = new JSONArray(response);
                            submit.setEnabled(true);

                            //Looping through the entire JSON array to get all the values
                            for(int i = 0;i < search_results_array.length(); i++) {
                                Log.i("custom", search_results_array.get(i).toString());
                                search_results.add(((JSONObject)search_results_array.get(i)).getString("Name"));
                            }

                            //Use <Actvity Name>.this in case of ASync task such as Volley
                            adapter = new ArrayAdapter<String>(DonorList.this, android.R.layout.simple_list_item_1, android.R.id.text1, search_results);

                            donor_list.setAdapter(adapter);
                        }

                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            submit.setEnabled(true);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DonorList.this,error.toString(),Toast.LENGTH_LONG).show();
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
