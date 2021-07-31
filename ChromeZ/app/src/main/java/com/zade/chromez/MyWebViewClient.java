package com.zade.chromez;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceResponse;

import java.util.HashMap;
import java.util.Map;

public class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView view, String url) { }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.endsWith(".mp4")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "video/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
            return true;
        } else if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:")
                || url.startsWith("mms:") || url.startsWith("mmsto:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
            return true;
        } else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private Map<String, Boolean> loadedUrls = new HashMap<>();

    @SuppressWarnings("deprecation")
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        boolean ad;
        if (!loadedUrls.containsKey(url)) {
            ad = AdBlocker.isAd(url);
            loadedUrls.put(url, ad);
        } else {
            ad = loadedUrls.get(url);
        }
        return ad ? AdBlocker.createEmptyResource() :
                super.shouldInterceptRequest(view, url);
    }
}


