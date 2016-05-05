package tech.sree.com.wifi;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.sree.com.wifi.Utils.myListViewAdaptor;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    String[] titles ;
    String[] description;
    int[] logo = {R.drawable.wifi1,};
    private myListViewAdaptor adaptor;

    public static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    private static void setInstance(MainActivity instance) {
        MainActivity.instance = instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInstance(this);
        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.listview);
        Resources resources = getResources();
        titles = resources.getStringArray(R.array.title);
        description = resources.getStringArray(R.array.description);
          adaptor = new myListViewAdaptor(this, titles, description, logo);
        listView.setAdapter(adaptor);
    }

    public void setNewListViewInfo(ArrayList<Integer> arrayList){
        View v = listView.getChildAt(0 -
                listView.getFirstVisiblePosition());
        ImageButton alaram = (ImageButton) findViewById(R.id.alaram);
        alaram.setImageResource(R.drawable.alaramon);

        TextView someText = (TextView) v.findViewById(R.id.description);
        someText.setText("From : " +arrayList.get(0)+" : "+arrayList.get(1)+
                         "  To  " +arrayList.get(2)+" : "+arrayList.get(3) );

        AlaramService alaramService =  new AlaramService(getInstance(),arrayList,"WIFI");
        finish();
    }



}