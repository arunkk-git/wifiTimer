package tech.sree.com.wifi.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.sree.com.wifi.MainActivity;
import tech.sree.com.wifi.R;
import tech.sree.com.wifi.myTimer;

/**
 * Created by ananth on 5/4/2016.
 */
class myViewHolder{
    ImageView r_imageView;
    TextView r_title;
    TextView r_desc;
    ImageButton r_button;

    public myViewHolder(View V ) {

        r_imageView = (ImageView) V.findViewById(R.id.imageView);
        r_title  = (TextView) V.findViewById(R.id.title);
        r_desc = (TextView) V.findViewById(R.id.description);
        r_button = (ImageButton) V.findViewById(R.id.alaram);

    }
}
public class myListViewAdaptor extends ArrayAdapter<String> {

    Context context;
    String[] myTitle;
    String[] myDesc;
    int [] myImages ;
    myViewHolder viewHolder;

    public     myListViewAdaptor(Context context, String[] titles,String[] desc,int[] imgs){

        super(context,R.layout.single_row,R.id.title,titles);
        this.context = context;
        this.myTitle = titles;
        this.myDesc = desc;
        this.myImages = imgs;
    }

    //int getCount1= 0,rowCount = 0;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //  getCount1++;

        View row =  convertView ;
        if(null == row) {
            //    rowCount++;
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.single_row, parent, false);
            viewHolder =  new myViewHolder(row);
            row.setTag(viewHolder);
        }
        else {
            viewHolder = (myViewHolder) row.getTag();
            viewHolder.r_imageView.setImageResource(myImages[0]);
            viewHolder.r_title.setText("  " + "  " + myTitle[position]);

            ArrayList<Integer> myarray_list = MainActivity.getInstance().getMyTimeSettings();

            if (myarray_list != null)
            {
                viewHolder.r_desc.setText("From : " + " " +  myarray_list.toString());
             //   Log.d("ARUN", "From : " + " " +  myarray_list.toString());
            }
            else {
               // Log.d("ARUN", " in list view mySharedpreferences is null");
                viewHolder.r_desc.setText("  " + "  " + myDesc[position]);
            }
        }
        viewHolder.r_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context ,myTimer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //      Toast.makeText(context, "Button Clicked+" + position, Toast.LENGTH_LONG).show();
            }
        });
        return row;
    }
}
