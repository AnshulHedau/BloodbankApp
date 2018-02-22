package anshul.software_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> nameId;
    private final ArrayList<String> locationId;
    private final ArrayList<String> numberId;

    public CustomList(Activity context,
                      ArrayList<String> nameId, ArrayList<String> locationId, ArrayList<String> numberId) {
        super(context, R.layout.activity_custom_list, nameId);
        this.context = context;
        this.nameId = nameId;
        this.locationId = locationId;
        this.numberId = numberId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_custom_list, null, true);
        TextView nameView = (TextView) rowView.findViewById(R.id.main_name);
        TextView locationView = (TextView) rowView.findViewById(R.id.location);
        TextView numberView = (TextView) rowView.findViewById(R.id.mob_number);

        locationView.setText(locationId.get(position));
        nameView.setText(nameId.get(position));
        numberView.setText(numberId.get(position));

        return rowView;
    }

}