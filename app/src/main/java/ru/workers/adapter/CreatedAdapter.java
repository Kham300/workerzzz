package ru.workers.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.workers.R;
import ru.workers.model.objects.generated.Data;

public class CreatedAdapter extends ArrayAdapter<Data> {
    private Activity context;
    ArrayList<Data> data;

    public CreatedAdapter(Activity context, int resource, ArrayList<Data> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        }

        Data item = data.get(position);

        if (item != null) { // Parse the data from each object and set it.
            TextView createdId = row.findViewById(R.id.item_id);
            TextView createdName = row.findViewById(R.id.item_value);
            if (createdId != null) {
                createdId.setText(item.getTid());
            }
            if (createdName != null) {
                createdName.setText(item.getNameRu());
            }


        }



        return row;
    }


}
