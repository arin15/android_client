package me.kaede.mvp;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by kaede on 2015/10/26.
 */
public class MvpApplication extends Application {

	private RefWatcher refWatcher;
	private String serverUrl = "";

	public static RefWatcher getReWatcher(Context context){
		MvpApplication application = (MvpApplication) context.getApplicationContext();
		return application.getRefWatcher();
	}

	public static String getServerUrl(Context context){
		MvpApplication application = (MvpApplication) context.getApplicationContext() ;
		return application.getServerUrl();
	}

	public RefWatcher getRefWatcher() {
		return refWatcher;
	}
	public String getServerUrl() { return serverUrl; }

	@Override
	public void onCreate() {
		super.onCreate();

		refWatcher = LeakCanary.install(this);
		serverUrl = "http://192.168.9.237:8080/";
	}


}
