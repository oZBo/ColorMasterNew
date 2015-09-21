package com.braincollaboration.colormaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.model.LibraryColorObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by oZBo on 26.01.2015.
 */
public class ColorsGridViewAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView colorName;
        CircleImageView color;
    }

    private ArrayList<LibraryColorObject> colorsList;
    private Context mContext;

    public ColorsGridViewAdapter(Context mContext, ArrayList<LibraryColorObject> colorsList) {
        this.mContext = mContext;
        this.colorsList = colorsList;
    }

    @Override
    public int getCount() {
        return colorsList.size();
    }

    @Override
    public LibraryColorObject getItem(int position) {
        return colorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.tutorial_gridview_item, parent, false);
            holder = new ViewHolder();
            holder.colorName = (TextView) convertView.findViewById(R.id.grid_item_text);
            holder.color = (CircleImageView) convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.colorName.setText(colorsList.get(position).getColorName());
        holder.color.setImageResource(colorsList.get(position).getColor());
        return convertView;
    }
}
