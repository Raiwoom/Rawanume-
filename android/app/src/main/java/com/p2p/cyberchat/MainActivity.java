package com.p2p.cyberchat;

import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Регистрируем кастомный WebChromeClient после загрузки моста Capacitor
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WebView webView = (WebView) bridge.getWebView();
                
                webView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onPermissionRequest(final PermissionRequest request) {
                        // ЖЁСТКИЙ ОТВЕТ: Автоматически и легально одобряем ЛЮБЫЕ запросы WebRTC 
                        // на доступ к камере, микрофону и STUN-ресурсам из кода index.html
                        request.grant(request.getResources());
                    }
                });
            }
        });
    }
}

