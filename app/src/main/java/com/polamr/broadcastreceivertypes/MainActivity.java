package com.polamr.broadcastreceivertypes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private IntentFilter receiveFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.registerCustomBR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                intent.putExtra("DATA", "send registerCustom broadcasr receiver messgae");
                intent.setAction("com.raja.POLAMR");
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.registerInternalBR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        findViewById(R.id.registerSystemBR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(mTimeReceiver, new IntentFilter(Intent.ACTION_TIME_CHANGED));
            }
        });
    }

    /**
     * Create and dispatch an Intent via the LocalBroadcastManager.
     * Called from a Button with android:onClick="send"
     */
    public void send() {
        LogUtil.show("MainActivity.send()");
        Intent sendableIntent = new Intent(getClass().getName());
        LocalBroadcastManager.getInstance(this).
                sendBroadcast(sendableIntent);
    }
    private BroadcastReceiver handler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.show( "BroadcastReceiver() {...}.onReceive()");
            //Toast.makeText(MainActivity.this, "Message received", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onPause() {
        // Unregister since the activity is paused.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(handler);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiveFilter = new IntentFilter(getClass().getName());
        LocalBroadcastManager.getInstance(this).registerReceiver(handler, receiveFilter);
    }

    private BroadcastReceiver mTimeReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            int bLevel = arg1.getIntExtra("level", 0);
            LogUtil.show("Level"+ bLevel);

        }
    };
}
