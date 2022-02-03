package com.muradshafiyev.cryptotool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGrid extends BaseAdapter {
    private Context context;
    private final String[] algo;
    private final int[] ImageId;

    public CustomGrid(Context c, String[] algo, int[] ImageId){
        context = c;
        this.ImageId = ImageId;
        this.algo = algo;
    }

    @Override
    public int getCount() {
        return algo.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(algo[i]); //position
            imageView.setImageResource(ImageId[i]);
        } else {
            grid = (View) view;
        }

        return grid;
    }
}
