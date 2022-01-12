package com.thatmg393.discordrpc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.java_websocket.client.WebSocketClient;

import java.util.Objects;

/**
 * This is a library for Discord Rich Presence
 * @author ThatMG393
 */
public class DiscordRPC
{
    private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private Context context;
    private Runnable heartbeat_updater;
    private WebSocketClient webSocketClient;

    /**
     * Initializes the RPC Library
     * @param context
     */
    public DiscordRPC(Context context)
    {
        this.context = context;
    }

    /**
     * Initializes the variables
     */
    public final void init()
    {
        heartbeat_updater = new Runnable() {
            @Override
            public void run() {

            }
        };

        login();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void login() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle("Login");

        final WebView webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("https://discord.com/login");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                webView.loadUrl(url);
                if (url.endsWith("/app"))
                {
                    webView.stopLoading();
                    view.stopLoading();
                    start();
                }
                return true;
            }
        });
        adb.setView(webView);
        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        adb.create().show();
    }

    private void start() {
        Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();
    }
}
