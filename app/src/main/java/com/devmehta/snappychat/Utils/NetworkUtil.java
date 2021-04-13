package com.devmehta.snappychat.Utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.devmehta.snappychat.R;

public class NetworkUtil extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(isConnected(context)){
            Activity activity = (Activity) context;
            activity.findViewById(R.id.network_error).setVisibility(View.GONE);
        }
        else{
            Activity activity = (Activity) context;
            activity.findViewById(R.id.network_error).setVisibility(View.VISIBLE);
        }
    }
    public boolean isConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else {
            return false;
        }
    }
}
