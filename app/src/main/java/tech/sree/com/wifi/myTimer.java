package tech.sree.com.wifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class myTimer extends AppCompatActivity {
    final String TIMER_INFO_ON_RESP = "TIMER_INFO_ON_RESP" ;
    final int TIMER_INFO_ON_RSP_CODE = 2 ;
    TimePicker timerON ,timerOFF ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle("Set Time");
        timerON = (TimePicker) findViewById(R.id.wifiON);
        timerOFF = (TimePicker) findViewById(R.id.wifiOFF);
    }

    public void getTimeData(View V){
        ArrayList<Integer> arrayList =  new ArrayList<Integer>();
        StringBuffer stringBuffer = new StringBuffer();

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
        arrayList.add(hour);
        arrayList.add(minute);

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
/*
        Intent intent=new Intent();
        intent.putIntegerArrayListExtra("TimeArray",arrayList);
        setResult(5, intent);
*/
        MainActivity.getInstance().setNewListViewInfo(arrayList);
        finish();

    }
}
