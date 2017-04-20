package com.lee.x.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.utils.KeyPairGenUtil;
import com.lee.x.utils.NewBase64;
import com.lee.x.utils.RasUtil;

/**
 * Created by android on 2017/4/11.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv;

    private Button btn;
    private Button btn1;
    private Button btn2;

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
    }

    @Override
    protected void initData() {

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
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


                Log.e("my","签名原串：" + content);
                Log.e("my","签名串：" + signstr);

//                System.out.println("---------------公钥校验签名------------------");
//                System.out.println("签名原串：" + content);
//                System.out.println("签名串：" + signstr);


                Log.e("my","签名原串：" + content);
                Log.e("my","签名串：" + signstr);


//                System.out.println("验签结果：" + RasUtil.doCheck(content, signstr, publickey));
//                System.out.println();


                Log.e("my","验签结果：" + RasUtil.doCheck(content, signstr, publickey));




                break;
            case R.id.btn2:

                break;
        }

    }
}
