package hainu.com.trainorganization.application;import android.Manifest;import android.app.Activity;import android.app.Application;import android.content.Context;import android.content.SharedPreferences;import android.content.pm.PackageManager;import android.os.Build;import android.text.TextUtils;import com.hyphenate.chat.EMClient;import com.hyphenate.chat.EMOptions;import com.umeng.analytics.MobclickAgent;import java.io.BufferedReader;import java.io.FileNotFoundException;import java.io.FileReader;import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import cn.bmob.v3.Bmob;import cn.bmob.v3.BmobInstallation;import cn.smssdk.SMSSDK;/** * Created by Administrator on 2016/7/14. */public class MyApplication extends Application {	private static final String TAG = "MyApplication";	public static SharedPreferences sharedPreferences;	public static List<Activity> activityList;	@Override	public void onCreate() {		super.onCreate();		// 初始化 Bmob SDK		Bmob.initialize(this, "6b5db9dbae13dd9aa03f5cfaee94dc7f");		BmobInstallation.getCurrentInstallation(this).save();		sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);		activityList = new ArrayList<>();		//初始化环信		EMOptions options = new EMOptions();		// 默认添加好友时，是不需要验证的，改成需要验证		options.setAcceptInvitationAlways(false);		//初始化		EMClient.getInstance().init(this, options);		//在做打包混淆时，关闭debug模式，避免消耗不必要的资源		EMClient.getInstance().setDebugMode(true);		SMSSDK.initSDK(this, "1995569c07f0c", "80d4293ccf3a3e85eca815afc5fc5a53");		//友盟统计		MobclickAgent.setDebugMode(true);		MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);        /*String deviceInfo = getDeviceInfo(this);        Log.i(TAG,deviceInfo);*/	}	public static boolean checkPermission(Context context, String permission) {		boolean result = false;		if (Build.VERSION.SDK_INT >= 23) {			try {				Class<?> clazz = Class.forName("android.content.Context");				Method method = clazz.getMethod("checkSelfPermission", String.class);				int rest = (Integer) method.invoke(context, permission);				if (rest == PackageManager.PERMISSION_GRANTED) {					result = true;				} else {					result = false;				}			} catch (Exception e) {				result = false;			}		} else {			PackageManager pm = context.getPackageManager();			if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {				result = true;			}		}		return result;	}	public static String getDeviceInfo(Context context) {		try {			org.json.JSONObject json = new org.json.JSONObject();			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context					.getSystemService(Context.TELEPHONY_SERVICE);			String device_id = null;			if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {				device_id = tm.getDeviceId();			}			String mac = null;			FileReader fstream = null;			try {				fstream = new FileReader("/sys/class/net/wlan0/address");			} catch (FileNotFoundException e) {				fstream = new FileReader("/sys/class/net/eth0/address");			}			BufferedReader in = null;			if (fstream != null) {				try {					in = new BufferedReader(fstream, 1024);					mac = in.readLine();				} catch (IOException e) {				} finally {					if (fstream != null) {						try {							fstream.close();						} catch (IOException e) {							e.printStackTrace();						}					}					if (in != null) {						try {							in.close();						} catch (IOException e) {							e.printStackTrace();						}					}				}			}			json.put("mac", mac);			if (TextUtils.isEmpty(device_id)) {				device_id = mac;			}			if (TextUtils.isEmpty(device_id)) {				device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),						android.provider.Settings.Secure.ANDROID_ID);			}			json.put("device_id", device_id);			return json.toString();		} catch (Exception e) {			e.printStackTrace();		}		return null;	}}