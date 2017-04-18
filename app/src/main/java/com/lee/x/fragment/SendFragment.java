package com.lee.x.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lee.x.R;

/**
 * Created by android on 2017/4/17.
 */
public class SendFragment extends BaseFragment {


    WebView webView;

    @Override
    protected int getResLayout() {
        return R.layout.send_fragment;
    }

    @Override
    protected void findView() {
        webView = (WebView) rootView.findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true); ///------- 设置javascript 可用
        JavaScriptInterface JSInterface = new JavaScriptInterface(getActivity()); ////------
        webView.addJavascriptInterface(JSInterface, "JSInterface"); // 设置js接口  第一个参数事件接口实例，第二个是实例在js中的别名，这个在js中会用到

    }

    @Override
    protected void initData() {
        // 修改ua使得web端正确判断
        String ua = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(ua + "****v=1.0.1");
        String ua1 = webView.getSettings().getUserAgentString();
        Log.e("Send", "UA" + ua);
        Log.e("Send", "UA1" + ua1);


        //  Mozilla/5.0 (Linux; Android 4.4.4; HM 2A Build/KTU84Q) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36


        String s = ua.substring(ua.indexOf("("), ua.indexOf(")"));
        String[] v = s.split(";");
        Log.e("Send", "s=" + s);
        Log.e("Send", "v[1]=" + v[1]);
        Log.e("Send", "v[2]=" + v[2]);


        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView wv, String arg1) {
                super.onPageFinished(wv, arg1);
                wv.loadUrl("javascript:displaymessage('v1.0.1')");

            }

            @Override
            public void onPageStarted(WebView arg0, String arg1, Bitmap arg2) {
                //  progressBar.setVisibility(View.VISIBLE);
                //  progressBar.setProgress(0);
                super.onPageStarted(arg0, arg1, arg2);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.cancel();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView arg0, int newProgress) {

                // progressBar.setProgress(newProgress);

                super.onProgressChanged(arg0, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        webView.loadUrl("file:///android_asset/index.html");


    }


    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void jsfun(String name) {

            Toast.makeText(mContext, name+"js", Toast.LENGTH_LONG).show();

        }
    }


}
