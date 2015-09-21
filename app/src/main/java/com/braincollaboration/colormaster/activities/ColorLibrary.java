package com.braincollaboration.colormaster.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.braincollaboration.colormaster.R;
import com.braincollaboration.colormaster.adapters.ColorsGridViewAdapter;
import com.braincollaboration.colormaster.model.LibraryColorObject;

import java.util.ArrayList;

import cat.ppicas.customtypeface.CustomTypeface;
import cat.ppicas.customtypeface.CustomTypefaceFactory;

/**
 * Created by oZBo on 20.09.2015.
 */
public class ColorLibrary extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_library, container, false);

        ArrayList<LibraryColorObject> colorsList = new ArrayList<>();
        LibraryColorObject red = new LibraryColorObject(R.color.red, getString(R.string.color_red));
        LibraryColorObject pink = new LibraryColorObject(R.color.pink, getString(R.string.color_pink));
        LibraryColorObject purple = new LibraryColorObject(R.color.purple, getString(R.string.color_purple));
        LibraryColorObject indigo = new LibraryColorObject(R.color.indigo, getString(R.string.color_indigo));
        LibraryColorObject blue = new LibraryColorObject(R.color.blue, getString(R.string.color_blue));
        LibraryColorObject teal = new LibraryColorObject(R.color.turquoise, getString(R.string.color_turquoise));
        LibraryColorObject green = new LibraryColorObject(R.color.green, getString(R.string.color_green));
        LibraryColorObject yellow = new LibraryColorObject(R.color.yellow, getString(R.string.color_yellow));
        LibraryColorObject orange = new LibraryColorObject(R.color.orange, getString(R.string.color_orange));
        LibraryColorObject brown = new LibraryColorObject(R.color.brown, getString(R.string.color_brown));

        colorsList.add(red);
        colorsList.add(pink);
        colorsList.add(purple);
        colorsList.add(indigo);
        colorsList.add(blue);
        colorsList.add(teal);
        colorsList.add(green);
        colorsList.add(yellow);
        colorsList.add(orange);
        colorsList.add(brown);

        GridView gridView = (GridView)v.findViewById(R.id.gridview_color_library);
        ColorsGridViewAdapter adapter = new ColorsGridViewAdapter(getActivity(), colorsList);
        gridView.setAdapter(adapter);

        return v;
    }

    public static ColorLibrary newInstance(String text) {

        ColorLibrary f = new ColorLibrary();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
