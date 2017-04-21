package com.lee.x.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.x.R;
import com.lee.x.utils.KeyPairGenUtil;
import com.lee.x.utils.NewBase64;
import com.lee.x.utils.RasUtil;
import com.lee.x.utils.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by android on 2017/4/11.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv;

    private Button btn;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getResLayout() {
        return R.layout.my_fragment;
    }

    @Override
    protected void findView() {
        tv = (TextView) rootView.findViewById(R.id.tv);
        btn = (Button) rootView.findViewById(R.id.btn);
        btn1 = (Button) rootView.findViewById(R.id.btn1);
        btn2 = (Button) rootView.findViewById(R.id.btn2);
        btn3 = (Button) rootView.findViewById(R.id.btn3);
    }

    @Override
    protected void initData() {

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        KeyPairGenUtil keys = new KeyPairGenUtil();
        switch (v.getId()) {

            case R.id.btn:

                String plainText = "dsdfdsfds分地方迪马股份大规模发电量圣诞发劳动模范的 ";
                byte[] cipherData = new byte[0];


                long sTime = SystemClock.currentThreadTimeMillis();
                try {
                    cipherData = keys.encryptByPublicKey(keys.getPublicKey(), plainText.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                long eTime = SystemClock.currentThreadTimeMillis();

                Log.e("lee", "加密时间" + (eTime - sTime));

                String cipher = NewBase64.encode(cipherData);

                tv.setText("加密后结果:" + cipher);
                byte[] res = new byte[0];

                long sTime1 = SystemClock.currentThreadTimeMillis();

                try {
                    res = keys.decryptBYPrivateKey(keys.getPriveteKey(), NewBase64.decode(cipher));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                long eTime1 = SystemClock.currentThreadTimeMillis();

                Log.e("lee", "解密时间" + (eTime1 - sTime1));

                String restr = new String(res);
                tv.setText("结果:" + restr);
                break;
            case R.id.btn1:


                String publickey = keys.getPublicKeyString();
                String privateKey = keys.getPriveteKeyString();

                // System.out.println("---------------私钥签名过程------------------");
                String content = "ihep_这是用于签名的原始数据";
                String signstr = RasUtil.sign(content, privateKey);
//                System.out.println("签名原串：" + content);
//                System.out.println("签名串：" + signstr);


                Log.e("my", "签名原串：" + content);
                Log.e("my", "签名串：" + signstr);

//                System.out.println("---------------公钥校验签名------------------");
//                System.out.println("签名原串：" + content);
//                System.out.println("签名串：" + signstr);


                Log.e("my", "签名原串：" + content);
                Log.e("my", "签名串：" + signstr);


//                System.out.println("验签结果：" + RasUtil.doCheck(content, signstr, publickey));
//                System.out.println();


                Log.e("my", "验签结果：" + RasUtil.doCheck(content, signstr, publickey));

                break;
            case R.id.btn2:

                http();
                break;

            case R.id.btn3:

                ras();
                break;
        }

    }

    private void ras() {

        String content = "";

        for (int i = 0; i < 1000; i++) {
            content += "大规模发电量圣诞发劳";
        }
        Log.e("my", "content长度：" + content.length());

        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();


        KeyPairGenUtil keys = new KeyPairGenUtil();
        String publickey = keys.getPublicKeyString();
        String privateKey = keys.getPriveteKeyString();

        // System.out.println("---------------私钥签名过程------------------");
        long sTime = SystemClock.currentThreadTimeMillis();
        String signStr = RasUtil.sign(content, privateKey);
        long eTime = SystemClock.currentThreadTimeMillis();
        Log.e("my", "签名耗时：" + (eTime - sTime));

        //  Log.e("my", "签名原串：" + content);
        Log.e("my", "签名串：" + signStr);


        long sTime1 = SystemClock.currentThreadTimeMillis();
        boolean f = RasUtil.doCheck(content, signStr, publickey);
        long eTime1 = SystemClock.currentThreadTimeMillis();
        Log.e("my", "验签耗时：" + (eTime1 - sTime1));
        Log.e("my", "验签结果：" + f);

    }


    /**
     * 构建基础参数treeMap
     *
     * @return
     */
    public static TreeMap<String, String> createBaseParams1() {
        TreeMap<String, String> tree = new TreeMap<>();
        tree.put("app_id", "10001");
        tree.put("format", "json");
        tree.put("sign_type", "rsa");
        tree.put("version", "2.0");
        tree.put("charset", "utf-8.0");
        tree.put("method", "auth.token.info");
        tree.put("timestamp", StringUtils.getDateFormat("yyyy-MM-dd HH:mm:ss"));
        return tree;
    }

    /**
     * 获取get url
     *
     * @param tree
     * @return
     * @throws UnsupportedEncodingException
     */
    public String createGetURIByInterface1(TreeMap<String, String> tree, String _interface) {
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(_interface);
        Iterator<String> it = tree.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = tree.get(key);
            if ("timestamp".equals(key)) {
                try {
                    value = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            urlBuffer.append(key + "=" + value);
            if (it.hasNext()) {
                urlBuffer.append("&");
            }
        }
        urlBuffer.append("&" + "sign" + "=" + getRequestTokenRSA(tree));
        return urlBuffer.toString();
    }

    private String getRequestTokenRSA(TreeMap<String, String> tree) {
        StringBuffer secretBuffer = new StringBuffer();
        Iterator<String> it = tree.keySet().iterator();
        //secretBuffer.append(Config.MD5_SECRET);
        while (it.hasNext()) {
            String key = it.next();
            String value = tree.get(key);
            secretBuffer.append(key + value);
        }

        KeyPairGenUtil keyPairGenUtil = new KeyPairGenUtil();
        String sign = RasUtil.sign(secretBuffer.toString(), keyPairGenUtil.getPriveteKeyString(), "utf-8");
        Log.e("sign", sign);

        try {
            sign = URLEncoder.encode(sign, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sign;
    }


    private void http() {

        TreeMap<String, String> tree = createBaseParams1();
        String url = createGetURIByInterface1(tree, "http://openapi.inner.evente.cn:30315?");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();


        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.build();
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().string();

                Log.e("okhttp", s);

            }
        });
    }
}
