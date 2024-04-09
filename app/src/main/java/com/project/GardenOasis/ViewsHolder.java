package com.project.GardenOasis;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**Holds parent, plant info and listview views*/
public class ViewsHolder
{
    public View parentView;
    public TextView plantInfoTextView;
    public ListView listView;

    ViewsHolder(View parentView, View plantInfoTextView, View listView)
    {
        this.parentView = (View) parentView;
        this.plantInfoTextView = (TextView) plantInfoTextView;
        this.listView = (ListView) listView;
    }
}
