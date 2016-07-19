package ezeezegg.toggleme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ezeezegg.toggleme.models.Entries;

/**
 * Created by egarcia on 7/17/16.
 */
public class EntriesAdapter extends ArrayAdapter<Entries> {
    private ArrayList<Entries> data;
    private LayoutInflater inflater;

    public EntriesAdapter(Context context, ArrayList<Entries> objects) {
        super(context, -1, objects);
        this.data = objects;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Entries entrie = data.get(position);
        return  convertView;
    }

    public static class ViewHolder {
        public TextView id;
    }
}
