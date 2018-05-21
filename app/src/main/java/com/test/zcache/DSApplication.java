package com.test.zcache;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


/**
 * Created by andy on 2018/5/9.
 */

public class DSApplication extends Application {

    private Context mContext;
    private static final String TAG = "APP";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("APP","DemoApplication on create");
        mContext = this;

        //支持读取本地配置
        EMASInfo einfo = Utils.parseEmasInfo(mContext);
        EmasInit emas = new EmasInit(this);
        if (einfo != null) {
            if (!TextUtils.isEmpty(einfo.AppKey)) {
                Log.i(TAG, "setAppKey:" + einfo.AppKey);
                emas.mAppkey = einfo.AppKey;
            }

            if (!TextUtils.isEmpty(einfo.AppSecret)) {
                Log.i(TAG, "setAppSecret:" + einfo.AppSecret);
                emas.mAppSecret = einfo.AppSecret;
            }
            if (einfo.IPStrategy != null && einfo.IPStrategy.size() > 0) {
                Log.i(TAG, "setIPStrategy:" + einfo.IPStrategy);
                emas.mIPStrategy = einfo.IPStrategy;
            }
            if (!TextUtils.isEmpty(einfo.ACCSDoman)) {
                Log.i(TAG, "setACCSDoman:" + einfo.ACCSDoman);
                emas.mAccsHost = einfo.ACCSDoman;
            }
            if (!TextUtils.isEmpty(einfo.HAUniversalHost)) {
                Log.i(TAG, "setHAUniversalHost:" + einfo.HAUniversalHost);
                emas.mAdashHost = einfo.HAUniversalHost;
            }
            if (!TextUtils.isEmpty(einfo.MTOPDoman)) {
                Log.i(TAG, "setMTOPDoman:" + einfo.MTOPDoman);
                emas.mMtopHost = einfo.MTOPDoman;
            }
            if (!TextUtils.isEmpty(einfo.CacheURL)) {
                Log.i(TAG, "setCacheURL:" + einfo.CacheURL);
                emas.mZcachePrefix = einfo.CacheURL;
            }
            if (!TextUtils.isEmpty(einfo.HAOSSBucketName)) {
                Log.i(TAG, "setHAOSSBucketName:" + einfo.HAOSSBucketName);
                emas.mHAOssBucket = einfo.HAOSSBucketName;
            }
            if (!TextUtils.isEmpty(einfo.HARSAPublicKey)) {
                Log.i(TAG, "setHARSAPublicKey:" + einfo.HARSAPublicKey);
                emas.mHAPubKey = einfo.HARSAPublicKey;
            }
            if (!TextUtils.isEmpty(einfo.ChannelID)) {
                Log.i(TAG, "setChannelID:" + einfo.ChannelID);
                emas.mTTid = einfo.ChannelID;
            }
            if (!TextUtils.isEmpty(einfo.StartActivity)) {
                Log.i(TAG, "setStartActivity:" + einfo.StartActivity);
                emas.mStartActivity = einfo.StartActivity;
            }
        }
        //初始化高可用
        emas.initHA();
        //初始化应用更新
        emas.initUpdate();
        //初始化Weex
        emas.initWeex();
        // 初始化推送
        emas.initPush(this);
    }
}
