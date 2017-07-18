package com.MPadilla.firebasesandbox;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    private static final String CONNECTIVITY_CHANNEL = "connectivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);

        new MethodChannel(getFlutterView(), CONNECTIVITY_CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                        if (activeNetwork == null) {
                            result.error("UNAVAILABLE","Connectivity is not available", null);
                        }

                        switch (methodCall.method) {
                            case "isConnected":
                                result.success(activeNetwork.isConnectedOrConnecting());
                                break;
                            case "isBluetooth":
                                result.success(activeNetwork.getType() == ConnectivityManager.TYPE_BLUETOOTH);
                                break;
                            case "isMobile":
                                result.success(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);
                                break;
                            case "isVPN":
                                result.success(activeNetwork.getType() == ConnectivityManager.TYPE_VPN);
                                break;
                            case "isWifi":
                                result.success(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI);
                            default:
                                result.notImplemented();
                        }
                    }
                }
        );
    }
}
