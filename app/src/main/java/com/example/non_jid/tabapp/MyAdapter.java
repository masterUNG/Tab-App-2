package com.example.non_jid.tabapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 7/22/2016 AD.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] shopStrings, phoneStrings, promoteStrings;

    public MyAdapter(Context context,
                     String[] shopStrings,
                     String[] phoneStrings,
                     String[] promoteStrings) {
        this.context = context;
        this.shopStrings = shopStrings;
        this.phoneStrings = phoneStrings;
        this.promoteStrings = promoteStrings;
    }

    @Override
    public int getCount() {
        return shopStrings.length;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        //Bind Widget
        TextView shopTextView = (TextView) view1.findViewById(R.id.textView);
        TextView phoneTextView = (TextView) view1.findViewById(R.id.textView2);
        TextView promoteTextView = (TextView) view1.findViewById(R.id.textView4);

        //Show Text
        shopTextView.setText(shopStrings[i]);
        phoneTextView.setText(phoneStrings[i]);

        String shortPromote = promoteStrings[i].substring(0, 20) + "...";
        promoteTextView.setText(shortPromote);

        return view1;
    }
}   // Main Class
