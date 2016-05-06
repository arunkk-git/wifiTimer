package tech.sree.com.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class WifiController extends AppCompatActivity {

    static WifiManager wifiManager;
    final String WIFI_ON  = "Enable_Wifi_Control";
    final String WIFI_OFF  = "Disable_Wifi_Control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_wifi_controller);


        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Bundle bundle = getIntent().getExtras();
        String resp1 = null;

        if (bundle != null) {
            resp1 = bundle.getString("ON");
            if (resp1.equals(WIFI_ON)) {
                Log.d("ARUNKK", " WIFI COntrol ON ");
                toggleWiFi(false);
            } else if (resp1.equals(WIFI_OFF)) {
                Log.d("ARUNKK", " WIFI COntrol OFF ");
                toggleWiFi(true);
            }

            finish();
        }

    }
    public void toggleWiFi(boolean status) {
        if (status != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(status);
       }
    }



}

