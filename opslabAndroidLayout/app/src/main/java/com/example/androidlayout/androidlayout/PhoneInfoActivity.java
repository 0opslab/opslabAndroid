package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);


        TelephonyManager mTm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(PhoneInfoActivity.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();


        String mtype = android.os.Build.MODEL; // 手机型号

        String result = wifiInfo.getMacAddress();//MAC地址

        String mtyb = android.os.Build.BRAND;//手机品牌


        String phoneInfo = "mtype:" + mtype + "\nresult:" + result + "\nmtyb:" + mtyb + "\n";
        phoneInfo += "android:" + android.os.Build.VERSION.SDK_INT + "\n";
        phoneInfo += "Product: " + android.os.Build.PRODUCT + "\n";

        phoneInfo += "CPU_ABI: " + android.os.Build.CPU_ABI + "\n";

        phoneInfo += "TAGS: " + android.os.Build.TAGS + "\n";

        phoneInfo += "VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + "\n";

        phoneInfo += "SDK: " + android.os.Build.VERSION.SDK + "\n";

        phoneInfo += "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + "\n";

        phoneInfo += "DEVICE: " + android.os.Build.DEVICE + "\n";

        phoneInfo += "DISPLAY: " + android.os.Build.DISPLAY + "\n";

        phoneInfo += "BRAND: " + android.os.Build.BRAND + "\n";

        phoneInfo += "BOARD: " + android.os.Build.BOARD + "\n";

        phoneInfo += "FINGERPRINT: " + android.os.Build.FINGERPRINT + "\n";

        phoneInfo += "ID: " + android.os.Build.ID + "\n";

        phoneInfo += "MANUFACTURER: " + android.os.Build.MANUFACTURER + "\n";

        phoneInfo += "USER: " + android.os.Build.USER + "\n";

        phoneInfo += "手机分辨率:" + getScreenDensity(PhoneInfoActivity.this) + "\n";

        phoneInfo += "手机内存:" + getTotalMemory(PhoneInfoActivity.this) + "\n";

//        phoneInfo += "手机运营商:"+getOperators(PhoneInfoActivity.this)+"\n";

        phoneInfo += "手机wifi地址:" + getRouteMacAddress(PhoneInfoActivity.this) + "\n";

        phoneInfo += "手机wifi-local地址:" + getLocalMacAddress(PhoneInfoActivity.this) + "\n";

        phoneInfo += "手机AppVersionName:" + getAppVersionName(PhoneInfoActivity.this) + "\n";


        TextView textView = findViewById(R.id.phoneinfo_text);

        textView.setText(phoneInfo);

    }

    /**
     * 获取分辨率
     *
     * @param context
     * @return
     */
    public static String getScreenDensity(Context context) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;
        float density = mDisplayMetrics.density;
        String dis = String.valueOf(width) + "*" + String.valueOf(height);
        return dis;
    }

    /**
     * 获取手机运行内存（RAM）
     *
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        String initial_memory = "";
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("//s+");
            String mom = arrayOfString[0].split(":")[1].split("kB")[0];
            initial_memory = Formatter.formatFileSize(context, Integer.valueOf(mom.trim()).intValue() * 1024); // 获得系统总内存，单位是MB，转换为GB
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return initial_memory;// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 获取当前可用内存
     *
     * @param context
     * @return
     */
    public static String getSystemAvaialbeMemorySize(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获得MemoryInfo对象
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        // 获得系统可用内存，保存在MemoryInfo对象上
        mActivityManager.getMemoryInfo(memoryInfo);
        long memSize = memoryInfo.availMem;
        // 字符类型转换
        String availMemStr = Formatter.formatFileSize(context, memSize);// 调用系统函数，字符串转换 long -String KB/MB
        return availMemStr;
    }

    public static String getIMEI(final Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }
        return tm.getDeviceId();
    }

    /**
     * 获取IMSI
     *
     * @param ctx
     * @return
     */
    public static String getIMSI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }
        String subscriberId = tm.getSubscriberId();

        if (judgeImsi(subscriberId)) {
            return subscriberId;
        }
        return null;

    }

    private static boolean judgeImsi(String imsi) {
        try {
            String sim = imsi.substring(0, 3);
            Long.valueOf(sim);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * 获取运营商
     *
     * @param content
     * @return
     */
    public static int getOperators(Context content) {
        TelephonyManager telManager = (TelephonyManager) content.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(content, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
        }
        String imsi = telManager.getSubscriberId();
        if (imsi != null) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                return 1;// 中国移动
            } else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
                return 2;// 中国联通
            } else if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
                return 4;// 中国电信
            }
        }
        return -1;
    }

    /**
     * 获取WiFi地址
     *
     * @param context
     * @return
     */
    public static String getRouteMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getBSSID();
    }

    /**
     * 返回网络的类型
     *
     * @param context
     * @return
     */
    public static int netState(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        return info.getType();
    }

    /**
     * 返回网络名
     *
     * @param context
     * @return
     */
    public static String getNetInfo(Context context) {
        ConnectivityManager connmanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo Info = connmanager.getActiveNetworkInfo();
        String typeName;
        if (Info == null) {
            typeName = "";
            return typeName;
        } else {
            typeName = Info.getTypeName();
            if (typeName.compareTo("MOBILE") == 0 || typeName.compareTo("mobile") == 0) {
                return "GPRS";
            }
            if (typeName.compareTo("WIFI") == 0 || typeName.compareTo("wifi") == 0) {
                return "WIFI";
            }
        }
        return typeName;
    }

    /**
     * 获取本地mac地址
     *
     * @param context
     * @return
     */
    public static String getLocalMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---  
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            int versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
