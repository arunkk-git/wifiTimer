package tech.sree.com.wifi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    String[] titles;
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
        setTitle("Wifi scheduler");
        setInstance(this);
        initListView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.listview);
        Resources resources = getResources();
        titles = resources.getStringArray(R.array.title);
        description = resources.getStringArray(R.array.description);
        adaptor = new myListViewAdaptor(this, titles, description, logo);
        listView.setAdapter(adaptor);
    }

    public void setNewListViewInfo(ArrayList<Integer> arrayList) {
        View v = listView.getChildAt(0 -
                listView.getFirstVisiblePosition());
        ImageButton alaram = (ImageButton) findViewById(R.id.alaram);
        alaram.setImageResource(R.drawable.alaramon);

        TextView someText = (TextView) v.findViewById(R.id.description);
        someText.setText("From : " + arrayList.get(0) + " : " + arrayList.get(1) +
                "  To  " + arrayList.get(2) + " : " + arrayList.get(3));

        AlaramService alaramService = new AlaramService(getInstance(), arrayList, "WIFI");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ARUN", "onOptionsItemSelected");


        switch (item.getItemId()) {

            case R.id.action_help:
                Intent intent_help = new Intent(this, OptionSelDialog.class);
                intent_help.putExtra("data", getString(R.string.Help));
                startActivity(intent_help);
                return true;

            case R.id.action_about:
                Intent intent = new Intent(this, OptionSelDialog.class);
                intent.putExtra("data",getString(R.string.About));
                startActivity(intent);
                return true;

            case R.id.action_rate:
                /*
                Intent intent_fb = new Intent(this, OptionSelDialog.class);
                intent_fb.putExtra("data", "This is intent_fb  Application");
                startActivity(intent_fb);
*/
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}