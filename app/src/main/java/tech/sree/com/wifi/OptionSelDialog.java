package tech.sree.com.wifi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OptionSelDialog extends AppCompatActivity {
public   TextView optionsTextView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Wifi-Controller");

        Log.d("ARUN", "OptionSelDialog OnCreate");
        setContentView(R.layout.content_option_sel_dialog);
        String textinfo = getIntent().getExtras().getString("data");
        optionsTextView = (TextView) findViewById(R.id.Option);

        optionsTextView.setText(textinfo);
    }

    public void exitDialog(View V){
        finish();
    }


}
