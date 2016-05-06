package tech.sree.com.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ananth on 5/6/2016.
 */
public class WifiStatusChangeNotifier extends BroadcastReceiver {
    Context context = MainActivity.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        boolean presetWifiStatus  = wifiManager.isWifiEnabled();
        Log.d("ARUN", "Wifi Status Before : " + wifiManager.isWifiEnabled());

        if (presetWifiStatus && !shouldAllowedToChangeWifiState()) {
            Log.d("ARUN","**** Forcing to OFF WI-FI Disable ");
            wifiManager.setWifiEnabled(false);
        }
        //Toast.makeText(context.getApplicationContext(), "Wifi Status Chnaged : " + wifiManager.isWifiEnabled(), Toast.LENGTH_LONG).show();
        Log.d("ARUN", "Wifi Status Chnaged : " + wifiManager.isWifiEnabled());
    }

    private boolean shouldAllowedToChangeWifiState() {
        ArrayList<Integer> OffOnTime = MainActivity.getInstance().getOffOnTime();
        if (OffOnTime == null)
            return true;
        Calendar currentTime = Calendar.getInstance();

        int offHour = OffOnTime.get(0);
        int offMinute = OffOnTime.get(1);
        int onHour = OffOnTime.get(2);
        int onMinute = OffOnTime.get(3);

        Calendar futureTimeON = Calendar.getInstance();
        futureTimeON.set(Calendar.HOUR_OF_DAY, onHour);
        futureTimeON.set(Calendar.MINUTE, onMinute);

        Calendar futureTimeOFF = Calendar.getInstance();
        futureTimeOFF.set(Calendar.HOUR_OF_DAY, offHour);
        futureTimeOFF.set(Calendar.MINUTE, offMinute);
        Log.d("ARUN", " [ " + offHour + " : " + offMinute + " to  " + onHour + " : " + onMinute + " ]");

        if (currentTime.before(futureTimeON) && currentTime.after(futureTimeOFF))
            return false;
        else
        return true;

    }
}
