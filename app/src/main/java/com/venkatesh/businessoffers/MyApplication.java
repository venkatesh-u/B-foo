package com.venkatesh.businessoffers;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.venkatesh.businessoffers.storage.PreferencesData;
import com.venkatesh.businessoffers.utilities.UtilsServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

//import com.crashlytics.android.ndk.CrashlyticsNdk;

/*@ReportsCrashes(formKey = "", // will not be used
		mailTo = "ramesh.korada@amzur.com",
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.crash_toast_text)*/
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks{

	static boolean applicationOnPause = false;
	private static MyApplication instance;

	private static final String PROPERTY_ID = "UA-67554021-1";
	public static int GENERAL_TRACKER = 0;
    public static Context applicationContext;
	public static final String TAG = MyApplication.class.getSimpleName();
//	private static DataBase db;
//	private static Pubnub mPubnub;

	public static Activity mCurrentActivity = null;



	public Activity getCurrentActivity(){
		return mCurrentActivity;
	}
	public void setCurrentActivity(Activity mCurrentActivity){
		MyApplication.mCurrentActivity = mCurrentActivity;
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {
		applicationOnPause = true;
	}

	@Override
	public void onActivityPaused(Activity activity) {
		applicationOnPause = false;
	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}

	public enum TrackerName {

		APP_TRACKER, GLOBAL_TRACKER, ECOMMERCE_TRACKER,

	}

	@SuppressWarnings("rawtypes")
	public static WebService service;
	public HashMap mTrackers = new HashMap();
	public static Retrofit mRetrofit;

	private static OkHttpClient httpClient = new OkHttpClient();

	@Override
	public void onCreate() {
		// The following line triggers the initialization of ACRA
		super.onCreate();
		if(!BuildConfig.BUILD_TYPE.equals("debug"))
//			Fabric.with(this, new Crashlytics());


//		MultiDex.install(this);
		PreferencesData.initPrefs(this);
		//Fabric.with(this, new Crashlytics());

		//ACRA.init(this);
		instance = this;
//		db = new DataBase(this);
//		initImageLoader(getApplicationContext());
		registerActivityLifecycleCallbacks(this);
		applicationContext=getApplicationContext();
		//Hoko.setup(this,"a858828013ac824027e15787a435dc21f110ca4b");


	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

//	public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//		ConnectivityReceiver.connectivityReceiverListener = listener;
//	}

	public static  void getNetworkError()
	{

	}

	public static synchronized boolean getApplicationStatus(){
		return applicationOnPause;
	}

	public static synchronized Retrofit getRetrofit()
	{
		if(mRetrofit==null)
		{
			httpClient.interceptors().add(new Interceptor() {
				@Override
				public Response intercept(Chain chain) throws IOException {
					Request original = chain.request();
					Request request = original.newBuilder()
							.header("device-type", "ANDROID")
//							.header("device-id", Settings.Secure.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID))
//							.header("authentication-token", PreferencesData.getToken(MyApplication.getInstance()))
							//.header("refresh_token",PreferencesData.getString(MyApplication.getInstance(),PreferencesData.PREF_REFRESH_TOKEN))
							.method(original.method(), original.body())
							.build();
					return chain.proceed(request);
				}
			});

			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			httpClient.interceptors().add(interceptor);
			httpClient.setReadTimeout(1, TimeUnit.MINUTES);
			httpClient.setWriteTimeout(1, TimeUnit.MINUTES);

           			  mRetrofit = new Retrofit.Builder()
							 .baseUrl(UtilsServer.URL_API)
							 .addConverterFactory(new GsonStringConverterFactory())
							  .addConverterFactory(GsonConverterFactory.create())
							  .client(httpClient)
							  .build();


		}
		return mRetrofit;
	}

	public static synchronized WebService getSerivce()
	{
		if (service == null) {
			service = getRetrofit().create(WebService.class);
		}
		return service;
	}

	public static synchronized MyApplication getInstance() {
		return instance;
	}
//
//	public static synchronized Pubnub getPub() {
//		if (mPubnub == null)
//			mPubnub = new Pubnub(Constants.PUBLISH_KEY, Constants.SUBSCRIBE_KEY);
//		return mPubnub;
//	}

//	public static DataBase openDb() {
//
//		MyApplication.db.opendb();
//		return MyApplication.db;
//	}

//	public static void closeDb() {
//		MyApplication.db.closeDB();
//	}

//	public ImageLoader getNostraImage() {
//		String CACHE_DIR = Environment.getExternalStorageDirectory()
//				.getAbsolutePath() + "/.temp_tmp";
//		new File(CACHE_DIR).mkdirs();
//
//		File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
//				CACHE_DIR);
//
//		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.avatar)
//				.showImageOnFail(R.drawable.avatar).cacheOnDisk(true)
//				.imageScaleType(ImageScaleType.EXACTLY)
//				.bitmapConfig(Bitmap.Config.RGB_565).build();
//		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
//				getBaseContext()).defaultDisplayImageOptions(defaultOptions)
//				.diskCache(new UnlimitedDiscCache(cacheDir))
//				.memoryCache(new WeakMemoryCache());
//		ImageLoaderConfiguration config = builder.build();
//
//		ImageLoader loader = ImageLoader.getInstance();
//		loader.init(config);
//		return loader;
//	}

//	public void initImageLoader(Context context) {
//		// This configuration tuning is custom. You can tune every option, you
//		// may tune some of them,
//		// or you can create default configuration by
//		// ImageLoaderConfiguration.createDefault(this);
//		// method.
//
//		String CACHE_DIR = Environment.getExternalStorageDirectory()
//				.getAbsolutePath() + "/.temp_tmp";
//		new File(CACHE_DIR).mkdirs();
//
//		File cacheDir = StorageUtils.getOwnCacheDirectory(getBaseContext(),
//				CACHE_DIR);
//		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
//				context);
//		config.threadPriority(Thread.NORM_PRIORITY - 2);
//		config.denyCacheImageMultipleSizesInMemory();
//		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
//		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
//		config.tasksProcessingOrder(QueueProcessingType.LIFO);
//		config.writeDebugLogs(); // Remove for release app
//		config.diskCache(new UnlimitedDiscCache(cacheDir));
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.ic_launcher)
//				.showImageForEmptyUri(R.drawable.avatar)
//				.showImageOnFail(R.drawable.avatar).cacheInMemory(true)
//				.cacheOnDisk(true).considerExifParams(true)
//				.bitmapConfig(Bitmap.Config.RGB_565)
//				.imageScaleType(ImageScaleType.EXACTLY).build();
//
//		config.defaultDisplayImageOptions(options);
//		// Initialize ImageLoader with configuration.
//		ImageLoader.getInstance().init(config.build());
//
//	}
//
//	public void deleteCache() {
//		getNostraImage().clearDiskCache();
//		getNostraImage().clearMemoryCache();
//	}

	/*@SuppressWarnings("unchecked")
	public synchronized Tracker getTracker(TrackerName appTracker) {
		if (!mTrackers.containsKey(appTracker)) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			Tracker t = (appTracker == TrackerName.APP_TRACKER) ? analytics
					.newTracker(PROPERTY_ID)
					: (appTracker == TrackerName.GLOBAL_TRACKER) ? analytics
							.newTracker(R.xml.global_tracker) : analytics
							.newTracker(R.xml.ecommerce_tracker);
			mTrackers.put(appTracker, t);
		}
		return (Tracker) mTrackers.get(appTracker);
	}
	public synchronized Tracker getDefaultTracker()
	{
		GoogleAnalytics analytics=GoogleAnalytics.getInstance(this);
		Tracker t=analytics.newTracker(PROPERTY_ID);
		return t;
	}
*/

	public static boolean isOnline(){
		boolean flag;
		ConnectivityManager connectivityManager= (ConnectivityManager) applicationContext.getSystemService(Service.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
		flag = !(networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected());
	/*	//Log.i("netstatus","connected "+networkInfo.isConnected());*/
		return flag;
	}


//	public static void getOnlineUsers(Callback callback) {
//		if(mPubnub==null)
//			mPubnub=getPub();
//		mPubnub.hereNow(ChatService.eteki_channel, callback);
//
//	}

	/**
	 * Subscribe to the channel requested */
	/**
	 * Subscribe to channel, when subscribe connection is established, in connectCallback, subscribe
	 *   to presence, set login time with setStateLogin and update hereNow information.
	 * When a message is received, in successCallback, get the ChatMessage information from the
	 *   received JSONObject and finally put it into the listview's ChatAdapter.
	 * Chat adapter calls notifyDatasetChanged() which updates UI, meaning must run on UI thread.
	 */
//	public void subscribeToChannel(String channel){
//		Callback subscribeCallback = new Callback() {
//			@Override
//			public void successCallback(final String channel, Object message) {
//				if (message instanceof JSONObject) {
//					try {
//						final JSONObject jsonObj = (JSONObject) message;
//
//						String name = jsonObj.getString(Constants.JSON_USER);
//						final String msg = jsonObj.getString(Constants.JSON_MSG);
//						final String chatName = jsonObj.getString(Constants.JSON_CHATNAME);
//
//						//long time   = json.getLong(Constants.JSON_TIME);
//						if (name.equals(PreferencesData.getInt(PreferencesData.PREF_USER_ID) + ""))
//							return; // Ignore own messages
//						final ChatMessage chatMsg = new ChatMessage(name, msg, 0, false,null);
//						final Activity act = getCurrentActivity();
//						if (act != null) {
//							act.runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//
//									ActivityManager am = (ActivityManager) act.getSystemService(ACTIVITY_SERVICE);
//									List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
//									ComponentName componentInfo = null;
//									if (taskInfo != null && taskInfo.size() > 0)
//										componentInfo = taskInfo.get(0).topActivity;
//									String str = "";
//									if (componentInfo != null)
//										str = componentInfo.getClassName();
//
//
//									if (!str.equalsIgnoreCase(MyChatActivityCopy.class.getCanonicalName())) {
//
//										try {
//											Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//											Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//											r.play();
//										} catch (Exception e) {
//											e.printStackTrace();
//										}
//										if(PreferencesData.getBool(PreferencesData.PREF_LOGIN)&&PreferencesData.getBool(PreferencesData.PREF_ON_OFFLINE)) {
//											PreferencesData.putBool(true, PreferencesData.PREF_CHAT_MSG);
//											Snackbar.make(act.findViewById(android.R.id.content), "Message from :" + chatName, Snackbar.LENGTH_LONG).setAction("VIEW", new View.OnClickListener() {
//												@Override
//												public void onClick(View v) {
//													Intent in = new Intent(getCurrentActivity(), MyChatActivityCopy.class);
//													//in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//													in.putExtra("from", true);
//													in.putExtra("channel", channel);
//													in.putExtra("name", chatName);
//													//  mPubNub.unsubscribe(channel);
//													//stopSelf();
//													getCurrentActivity().startActivity(in);
//												}
//											}).show();
//										}
//									}else {
//										Intent in = new Intent();
//										in.setAction(ChatService.CHAT_ACTION);
//										in.putExtra("from", true);
//										in.putExtra("channel", channel);
//										in.putExtra("name", chatName);
//										in.putExtra("msg", jsonObj.toString());
//										sendBroadcast(in);
//									}
//								}
//							});
//						}
//					}catch(JSONException e){
//						e.printStackTrace();
//					}
//				}
//
//				Log.d(TAG, "Channel: " + channel + " Msg: " + message.toString());
//			}
//
//			@Override
//			public void connectCallback(String channel, Object message) {
//				Log.d(TAG, "Connected! " + message.toString());
//				//hereNow(false);
//
//				// setStateLogin();
//			}
//		};

//		try {
//			mPubnub.subscribe(channel, subscribeCallback);
//
//		} catch (PubnubException e){ e.printStackTrace();
//		}
//
	}

