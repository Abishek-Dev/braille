package com.example.braille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SetUp extends AppCompatActivity {
    Button b1,b2;
    TextView ipViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        b1=(Button)findViewById(R.id.buttonOn);
        b2=(Button)findViewById(R.id.buttonOFF);
        ipViewer=(TextView)findViewById(R.id.ipViewer);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNet(true);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNet(false);
            }
        });
    }

    private void switchNet(boolean b) {
        WifiManager wmgr = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
// Get List of Available Wifi Networks
        List<ScanResult> availNetworks = wmgr.getScanResults();
        if (availNetworks.size() > 0) {
            String wifis[] = new String[availNetworks.size()];
            // Get Each network detail
            for (int i=0; i<availNetworks.size();i++) {
                wifis[i] = availNetworks.get(i).toString();
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Intent panelIntent=new Intent(Settings.Panel.ACTION_WIFI);
            startActivityForResult(panelIntent, 1);
        } else {
            if(b) {
                WifiInfo wifiInfo = wmgr.getConnectionInfo();
                int ip = wifiInfo.getIpAddress();
                String ipAddress = Formatter.formatIpAddress(ip);
                ipViewer.setText(ipAddress);
            }
            wmgr.setWifiEnabled(b);
        }
    }
}