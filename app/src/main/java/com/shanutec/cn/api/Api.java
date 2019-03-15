package com.shanutec.cn.api;

import android.app.Application;


/**
 * @author 张海洋
 * @Date on 2019/03/15.
 * @org 上海..科技有限公司
 * @describe
 */
public class Api extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
     /*   if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/
    }
}
