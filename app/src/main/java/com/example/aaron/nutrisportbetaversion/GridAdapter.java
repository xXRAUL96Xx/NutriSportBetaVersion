package com.example.aaron.nutrisportbetaversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by aaron on 07/05/2018.
 */

public class GridAdapter extends BaseAdapter {
    Context context;
    private final String [] values;
    private final int [] images;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, String[] values, int[] images) {
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(convertview == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            ImageView imageView = view.findViewById(R.id.imageviewTitle1);
            TextView textView = view.findViewById(R.id.textview);
            imageView.setImageResource(images[i]);
            textView.setText(values[i]);
        }
        return view;
    }
}
