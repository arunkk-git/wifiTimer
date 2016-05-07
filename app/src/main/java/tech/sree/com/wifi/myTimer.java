package tech.sree.com.wifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class myTimer extends AppCompatActivity {
    TimePicker timerON ,timerOFF ;
    EditText password;
    String  Pass_word = "ARUN123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle("Set Time");
        timerON = (TimePicker) findViewById(R.id.wifiON);
        timerOFF = (TimePicker) findViewById(R.id.wifiOFF);
        password = (EditText) findViewById(R.id.password);
    }
    public void getTimeData(View V){
        if (true || password.getText().toString().equals(Pass_word))
        getAlarmTimeInfo();
        else
        Toast.makeText(this,"Wrong ENter !!! Need to check password",Toast.LENGTH_LONG).show();

    }

    public void getAlarmTimeInfo(){
        ArrayList<Integer> arrayList =  new ArrayList<Integer>();
        StringBuffer stringBuffer = new StringBuffer();
        Calendar c = Calendar.getInstance();

        stringBuffer.append("timerOFF ");
        int hour = 0;
        int minute = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timerOFF.getHour();
            minute = timerOFF.getMinute();
        }else {
            hour = timerOFF.getCurrentHour();
            minute = timerOFF.getCurrentMinute();
        }
        if (hour <= c.get(Calendar.HOUR ) && minute <= c.get(Calendar.MINUTE ) ){
            Toast.makeText(this,"Set Proper Future Time !!!",Toast.LENGTH_LONG).show();
        }

        arrayList.add(hour);
        arrayList.add(minute);
        stringBuffer.append("" + hour + ":" + minute);
        stringBuffer.append(" timerON ");
        hour = 0;
        minute = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timerON.getHour();
            minute = timerON.getMinute();
        }else {
            hour = timerON.getCurrentHour();
            minute = timerON.getCurrentMinute();
        }

        if (hour < c.get(Calendar.HOUR ) && minute <= c.get(Calendar.MINUTE ) ){
            Toast.makeText(this,"Set Proper Future Time !!!",Toast.LENGTH_LONG).show();
        }
        arrayList.add(hour);
        arrayList.add(minute);
        MainActivity.getInstance().setNewListViewInfo(arrayList);
        finish();

    }
}
