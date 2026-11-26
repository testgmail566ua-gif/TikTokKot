package com.tiktokkot;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lp) {
        if (!lp.packageName.equals("com.zhiliaoapp.musically")) return;
        XposedHelpers.findAndHookMethod(Application.class, "onCreate",
            new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam p) {
                    final Context c = (Context) p.thisObject;
                    new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(c, "TikTokKot загружен",
                            Toast.LENGTH_LONG).show());
                }
            });
    }
    }
