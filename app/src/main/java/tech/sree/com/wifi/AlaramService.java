package tech.sree.com.wifi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ananth on 5/4/2016.
 */
public class AlaramService {
    Context context;
    private  int OnTimeHour = 0 ,OnTimeMinuts = 0 , OffTimeHour = 0, OffTimeMinuts = 0 ;


    public AlaramService(Context context,ArrayList<Integer> timerInfo,String toContorl){
        context = context ;
        OnTimeHour = timerInfo.get(0) ;
        OnTimeMinuts = timerInfo.get(1) ;
        OffTimeHour = timerInfo.get(2);
        OffTimeMinuts = timerInfo.get(3) ;
        set_AlarmtoContorl(toContorl);

    }

    public void set_AlarmtoContorl(String toContorl) {
        int hour = 0;
        int minute = 0;

        Calendar futureDateON = Calendar.getInstance();
        Calendar futureDateOFF = Calendar.getInstance();


        futureDateON.set(Calendar.HOUR_OF_DAY, OnTimeHour);
        futureDateON.set(Calendar.MINUTE, OnTimeMinuts);
        futureDateON.set(Calendar.SECOND,10);

        futureDateOFF.set(Calendar.HOUR_OF_DAY, OffTimeHour);
        futureDateOFF.set(Calendar.MINUTE, OffTimeMinuts);
        futureDateOFF.set(Calendar.SECOND,5);

        if (toContorl == "WIFI") {
            Log.d("ARUN","WIFI toContorl");

            Intent intentON = new Intent(MainActivity.getInstance(), WifiController.class);
            intentON.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentON.putExtra("ON", "Enable_Wifi_Control");

            PendingIntent piON = PendingIntent.getActivity(MainActivity.getInstance(), 2, intentON, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager amON = (AlarmManager) MainActivity.getInstance().getSystemService(context.ALARM_SERVICE);
//            amON.set(AlarmManager.RTC_WAKEUP, futureDateON.getTimeInMillis(), piON);
            amON.set(AlarmManager.RTC_WAKEUP, futureDateON.getTimeInMillis(), piON);

            Intent intentOFF = new Intent(MainActivity.getInstance(), WifiController.class);
            intentOFF.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentOFF.putExtra("ON", "Disable_Wifi_Control");

            PendingIntent piOFF = PendingIntent.getActivity(MainActivity.getInstance(), 3, intentOFF, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager amOFF = (AlarmManager) MainActivity.getInstance().getSystemService(context.ALARM_SERVICE);
            amOFF.set(AlarmManager.RTC_WAKEUP, futureDateOFF.getTimeInMillis(), piOFF);
        }
    }
}
