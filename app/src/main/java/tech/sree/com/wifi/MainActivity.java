package tech.sree.com.wifi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import tech.sree.com.wifi.Utils.myListViewAdaptor;

public class MainActivity extends AppCompatActivity {
    private ListView listView =  null;
    private String[] titles = null;
    private  String[] description =  null;
    private int[] logo = {R.drawable.wifi1,};
    private myListViewAdaptor adaptor =  null;
private String defaultPassWord = "WIFI";
    /* ----------------------------SharedPreferences----------------------------*/
    private SharedPreferences mySharedpreferences = null ;
    private String MyPREFERENCES = "mySettigns";
    final String offTimeH = "offTimeHour", offTimeM = "offTimeMinute";
    final String onTimeH = "onTimeHour", onTimeM = "onTimeMinute";
    final String passWord="passWord";
    /* ----------------------------SharedPreferences----------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/* ----------------------------SharedPreferences----------------------------*/
        mySharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
/* ----------------------------SharedPreferences----------------------------*/

        setTitle("Wifi scheduler");
        setNewPassWord(defaultPassWord,false);
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

    boolean setAlaramTimer = false ;

    private ArrayList<Integer> OffOnTime= null; //= new ArrayList<Integer>(Arrays.asList(0,0,0,0));

    public  ArrayList<Integer> getOffOnTime() {

        return  OffOnTime ;
    }

    private void setOffOnTime(ArrayList<Integer> offONTime) {
        setAlaramTimer = true ;
        if(OffOnTime ==  null)
            OffOnTime= new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        OffOnTime = offONTime ;
    }
    public void setNewListViewInfo(ArrayList<Integer> arrayList) {
       // Log.d("ARUN", "setNewListViewInfo");

        setMyTimeSettings(arrayList);
        setOffOnTime(arrayList);

        View v = listView.getChildAt(0 -
                listView.getFirstVisiblePosition());

        arrayList = getMyTimeSettings();

        TextView timeDuriation = (TextView) v.findViewById(R.id.description);
        timeDuriation.setText("From : " + arrayList.get(0) + " : " + arrayList.get(1) +
                "  To  " + arrayList.get(2) + " : " + arrayList.get(3));
        ImageButton alaram = (ImageButton) findViewById(R.id.alaram);
        alaram.setImageResource(R.drawable.alaramon);
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
        switch (item.getItemId()) {

            case R.id.action_help:
                Intent intent_help = new Intent(this, OptionSelDialog.class);
                intent_help.putExtra("data", getString(R.string.Help));
                startActivity(intent_help);
                return true;

            case R.id.action_about:
                Intent intent_about = new Intent(this, OptionSelDialog.class);
                intent_about.putExtra("data",getString(R.string.About));
                startActivity(intent_about);
                return true;

            case R.id.action_password:
                Intent intent_pwd = new Intent(this, PassWord.class);
                intent_pwd.putExtra("data",getString(R.string.About));
                startActivity(intent_pwd);
                return true;

            case R.id.action_rate:

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
    public static MainActivity instance;
    public static MainActivity getInstance() {return instance;}
    private static void setInstance(MainActivity instance) {MainActivity.instance = instance;}

    private static String presentPassword;
    public  void setNewPassWord(String newpwd, boolean fromMySetting /* 0 -> onCreate 1 -> savedSettings */){
Log.d("ARUN","setNewPassWord :  "+fromMySetting+"  newpwd  = "+newpwd);
        if( fromMySetting) {
/* ----------------------------SharedPreferences----------------------------*/
            SharedPreferences.Editor editor = mySharedpreferences.edit();
            editor.putString(passWord, newpwd);
            editor.commit();
        /* ----------------------------SharedPreferences----------------------------*/
            presentPassword = newpwd;
        }else {
            String passwd = mySharedpreferences.getString(passWord, defaultPassWord);
            Log.d("ARUN","mySharedpreferences passwd "+passwd);
            presentPassword = passwd;
        }
    }

    public  String getpresentPassword(){
        /* ----------------------------SharedPreferences----------------------------*/
        String passwd = mySharedpreferences.getString(passWord, "WIFI");
        if(passwd != "WIFI")
            return passwd;
        else
        /* ----------------------------SharedPreferences----------------------------*/
            return presentPassword ;
    }
    /* ----------------------------SharedPreferences----------------------------*/
    public SharedPreferences getMygetSharedPreferences(){

        return mySharedpreferences;
    }
    /* ----------------------------SharedPreferences----------------------------*/
    public void setMyTimeSettings( ArrayList<Integer> myarrayList) {

        SharedPreferences.Editor editor = mySharedpreferences.edit();
        editor.putInt(offTimeH, myarrayList.get(0));
        editor.putInt(offTimeM, myarrayList.get(1));
        editor.putInt(onTimeH, myarrayList.get(2));
        editor.putInt(onTimeM, myarrayList.get(3));
        editor.commit();

    }
    public  ArrayList<Integer> getMyTimeSettings(){

        ArrayList<Integer> myarrayList = new ArrayList<Integer>(Arrays.asList(0,0,0,0));

        myarrayList.set(0, mySharedpreferences.getInt(offTimeH, 0));
        myarrayList.set(1, mySharedpreferences.getInt(offTimeM,0));
        myarrayList.set(2, mySharedpreferences.getInt(onTimeH,0));
        myarrayList.set(3, mySharedpreferences.getInt(onTimeM, 0));

        return myarrayList;

    }
/* ----------------------------SharedPreferences----------------------------*/

}