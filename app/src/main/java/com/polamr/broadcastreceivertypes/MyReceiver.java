package com.polamr.broadcastreceivertypes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.show("onReceive");
        String msg  = intent.getStringExtra("DATA");
        LogUtil.show("message : "+msg);
    }
}
