package com.liskovsoft.smartyoutubetv.injectors;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.*;
import com.liskovsoft.smartyoutubetv.helpers.AdAwayClient;
import com.liskovsoft.smartyoutubetv.helpers.RequestInterceptor;
import com.liskovsoft.smartyoutubetv.helpers.MainRequestInterceptor;

public class MyWebViewClientDecorator extends WebViewClient {
    private final WebViewClient mWebViewClient;
    private final RequestInterceptor mInterceptor;
    private final Context mContext;

    public MyWebViewClientDecorator(WebViewClient client, Context context) {
        mWebViewClient = client;
        mContext = context;
        mInterceptor = new MainRequestInterceptor(mContext);
    }

    /**
     * Block ads here. Don't use shouldOverrideUrlLoading because it's not called for internal urls.
     * @param view
     * @param url
     * @return
     */
    @Override
    @Deprecated
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (mInterceptor.test(url)) {
            return mInterceptor.intercept(url);
        }

        if (AdAwayClient.isAd(url)) {
            // block url
            return new WebResourceResponse(null, null, null);
        }

        return mWebViewClient.shouldInterceptRequest(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return mWebViewClient.shouldInterceptRequest(view, request);
    }

    /**
     * OTHER METHODS
     */

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return mWebViewClient.shouldOverrideUrlLoading(view, request);
    }

    @Override
    @Deprecated
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return mWebViewClient.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        mWebViewClient.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mWebViewClient.onPageFinished(view, url);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        mWebViewClient.onLoadResource(view, url);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        mWebViewClient.onPageCommitVisible(view, url);
    }

    @Override
    @Deprecated
    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        mWebViewClient.onTooManyRedirects(view, cancelMsg, continueMsg);
    }

    @Override
    @Deprecated
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        mWebViewClient.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        mWebViewClient.onReceivedError(view, request, error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        mWebViewClient.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        mWebViewClient.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        mWebViewClient.doUpdateVisitedHistory(view, url, isReload);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        mWebViewClient.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        mWebViewClient.onReceivedClientCertRequest(view, request);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        mWebViewClient.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return mWebViewClient.shouldOverrideKeyEvent(view, event);
    }

    @Override
    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
        mWebViewClient.onUnhandledKeyEvent(view, event);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        mWebViewClient.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        mWebViewClient.onReceivedLoginRequest(view, realm, account, args);
    }
}
