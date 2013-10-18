package test.adlib.project;

import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;
import com.mocoplex.adlib.AdlibInterstitialView;
import com.mocoplex.adlib.AdlibManager;
import com.mocoplex.adlib.AdlibRewardIcon;
import com.mocoplex.adlib.AdlibRewardLink;
import com.mocoplex.adlib.AdlibManager.AdlibVersionCheckingListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
* 중요 *
안드로이드 4.2 이상에서 WebView의 JavascriptInterface를 지원하기 위해 
프로젝트 설정의 빌드 타겟을 17이상으로 설정해야 합니다.
(최신버전 android SDK 이용 권장)
*/

// 광고 스케줄링을 위해 AdlibActivity 를 상속받은 activity 를 생성합니다.
public class AdlibTestProjectActivity extends AdlibActivity {
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initAds();
        
        // 실제 광고 호출이 될 adview 를 연결합니다. 
        this.setAdsContainer(R.id.ads);
        
        View.OnClickListener cl;
        
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				Intent in = new Intent(AdlibTestProjectActivity.this, AdlibTestProjectActivity2.class);
				startActivity(in);				
			}
        	
        };
        this.findViewById(R.id.btn1).setOnClickListener(cl);
        
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				Intent in = new Intent(AdlibTestProjectActivity.this, AdlibTestProjectActivity3.class);
				startActivity(in);				
			}
        	
        };
        this.findViewById(R.id.btn2).setOnClickListener(cl);        
        
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				Intent in = new Intent(AdlibTestProjectActivity.this, AdlibTestProjectActivity4.class);
				startActivity(in);				
			}
        	
        };
        this.findViewById(R.id.btn3).setOnClickListener(cl);        
        
        // 리워드 링크 아이콘을 사용하지 않고 버튼 클릭 이벤트 등의 별로 이벤트로 구현하고 싶으시면 아래와 같이 showRewardLink(Context ctx, String rewardLinkId) 함수를 호출하면 됩니다.
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				// rewardLinkId는 애드립 홈페이지에서 발급받은 링크 ID로 대체하세요.
				AdlibRewardLink.getInstance().showRewardLink(AdlibTestProjectActivity.this, "5184d07ae4b03c9009dfa5ae");	//  <-- 테스트 키 입니다.			
			}
        	
        };
        this.findViewById(R.id.btn4).setOnClickListener(cl);
        
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				load();
			}
        	
        };
        this.findViewById(R.id.btn5).setOnClickListener(cl);
        
        cl = new View.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				loadAPI();
			}
        	
        };
        this.findViewById(R.id.btn6).setOnClickListener(cl);
    }
    
    // AndroidManifest.xml에 권한과 activity를 추가하여야 합니다.     
    protected void initAds()
    {
    	// AdlibActivity 를 상속받은 액티비티이거나,
    	// 일반 Activity 에서는 AdlibManager 를 동적으로 생성한 후 아래 코드가 실행되어야 합니다. (AdlibTestProjectActivity4.java)

    	// Manifest 에서 <uses-permission android:name="android.permission.GET_TASKS" /> 부분 권한 추가를 확인해주세요.

    	// 광고 스케줄링 설정을 위해 아래 내용을 프로그램 실행시 한번만 실행합니다. (처음 실행되는 activity에서 한번만 호출해주세요.)    	
    	// 광고 subview 의 패키지 경로를 설정합니다. (실제로 작성된 패키지 경로로 수정해주세요.)

    	// 쓰지 않을 광고플랫폼은 삭제해주세요.
        AdlibConfig.getInstance().bindPlatform("ADAM","test.adlib.project.ads.SubAdlibAdViewAdam");
        AdlibConfig.getInstance().bindPlatform("ADMOB","test.adlib.project.ads.SubAdlibAdViewAdmob");
        AdlibConfig.getInstance().bindPlatform("CAULY","test.adlib.project.ads.SubAdlibAdViewCauly");
        AdlibConfig.getInstance().bindPlatform("TAD","test.adlib.project.ads.SubAdlibAdViewTAD");
        AdlibConfig.getInstance().bindPlatform("NAVER","test.adlib.project.ads.SubAdlibAdViewNaverAdPost");
        AdlibConfig.getInstance().bindPlatform("SHALLWEAD","test.adlib.project.ads.SubAdlibAdViewShallWeAd");
        AdlibConfig.getInstance().bindPlatform("INMOBI","test.adlib.project.ads.SubAdlibAdViewInmobi");
        AdlibConfig.getInstance().bindPlatform("MMEDIA","test.adlib.project.ads.SubAdlibAdViewMMedia");
        AdlibConfig.getInstance().bindPlatform("MOBCLIX","test.adlib.project.ads.SubAdlibAdViewMobclix");
        AdlibConfig.getInstance().bindPlatform("ADMOBECPM","test.adlib.project.ads.SubAdlibAdViewAdmobECPM");
        AdlibConfig.getInstance().bindPlatform("UPLUSAD","test.adlib.project.ads.SubAdlibAdViewUPlusAD");
        // 쓰지 않을 플랫폼은 JAR 파일 및 test.adlib.project.ads 경로에서 삭제하면 최종 바이너리 크기를 줄일 수 있습니다.        
        
        // SMART* dialog 노출 시점 선택시 / setAdlibKey 키가 호출되는 activity 가 시작 activity 이며 해당 activity가 종료되면 app 종료로 인식합니다.
        // adlibr.com 에서 발급받은 api 키를 입력합니다.
        // https://sec.adlibr.com/admin/dashboard.jsp
        AdlibConfig.getInstance().setAdlibKey("ADLIB - API - KEY");
        
    }
    
    protected void load()
    {
    	
    	// 전면광고를 호출합니다.
		loadInterstitialAd();
	
    	/*
		// optional : 전면광고의 수신 성공, 실패 이벤트 처리가 필요한 경우엔 handler를 이용하실 수 있습니다.
		loadInterstitialAd(new Handler() {
			public void handleMessage(Message message) {
	    		try
	    		{
	    			switch (message.what) {
                    case AdlibManager.DID_SUCCEED:
                        Log.d("ADLIBr", "onReceiveAd " + (String)message.obj);
                        break;
                    case AdlibManager.DID_ERROR:
                        Log.d("ADLIBr", "onFailedToReceiveAd " + (String)message.obj);
                        break;
                    case AdlibManager.INTERSTITIAL_CLOSED:
                        Log.d("ADLIBr", "onClosedAd " + (String)message.obj);
                        break;
		    		}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
		});
		*/
    }
    
    protected void loadAPI()
    {
        // 전면배너를 이용하여 다른 형태로 구성하고 싶으신 경우 사용하는 method 입니다.
        
        // getInterstitialView(width(dp), maxHeight(dp), handler);
        // width의 비율에 맞춰 height가 계산되어집니다.
        // 비율에 맞는 height가 maxHeight보다 클 경우에는 maxHeight에 맞춰 비율이 변형된 광고가 노출됩니다.
    	getInterstitialView(200, 300, new Handler() {
    		public void handleMessage(Message message) {
	    		try
	    		{
	    			switch (message.what) {
                        case AdlibManager.DID_SUCCEED:
                            AdlibInterstitialView iView = (AdlibInterstitialView)message.obj;
                            
                            // getViewHeight 함수를 통해 실제 노출될 height 값(dp)을 알 수 있습니다.
                            int viewHeihgt = iView.getViewHeight();
                            
                            LinearLayout layout = new LinearLayout(AdlibTestProjectActivity.this);
                            layout.setLayoutParams(new LayoutParams(dpToPx(200), dpToPx(viewHeihgt)));
                            layout.setGravity(Gravity.CENTER);
                            layout.addView(iView);
                            
                            AlertDialog.Builder builder = new AlertDialog.Builder(AdlibTestProjectActivity.this);
                            builder.setTitle("Title");
                            builder.setView(layout);
                            
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    
                                }
                            });
                            
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    
                                }
                            });
                            
                            AlertDialog alert = builder.create();
                            alert.show();
                            alert.getWindow().setLayout(dpToPx(230), LayoutParams.WRAP_CONTENT);
                            
                            break;
                        case AdlibManager.DID_ERROR:
                            Toast.makeText(AdlibTestProjectActivity.this, "광고수신 실패 :(", Toast.LENGTH_SHORT).show();
                            break;
		    		}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
    	});
    }
    
    @Override
	protected void onPause() {
		
		// 리워드 링크 아이콘을 사용하는 Activity 에서는 반드시 onPause 에서 pauseRewardLink(Context ctx) 를 호출해 주세요.
		AdlibRewardLink.getInstance().pauseRewardLink(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		
		// 리워드 링크 아이콘을 배치하기 위해서는 Activity 의 onResume 에서 rewardLink(Context ctx, String rewardLinkId, int x, int y, int align) 를 호출해 주세요.
		
		// x축, y축 padding은 아래와 같이 pixel 값(+)으로 직접 계산해 주셔야 합니다.
		Display mDisplay= getWindowManager().getDefaultDisplay();
    	int width = mDisplay.getWidth();
    	int h = dpToPx(90);
    	
    	// rewardLinkId는 애드립 홈페이지에서 발급받은 링크 ID로 대체하세요.
    	// align 위치를 기준으로 x축으로 x pixel, y축으로 y pixel만큼 이동한 지점에 아이콘이 배치됩니다.
    	// 아이콘 위치는 아이콘의 중심점 기준입니다.
    	// 아래의 경우 좌측 하단을 중심으로 아이콘의 중심점이 x축으로 디바이스 width의 절반만큼, y축으로 90dp만큼 이동한 위치에 아이콘이 배치됩니다.
		AdlibRewardLink.getInstance().rewardLink(this, "5184d07ae4b03c9009dfa5ae", width/2, h, AdlibRewardIcon.ALIGN_LEFT_BOTTOM);  //  <-- 테스트 키 입니다.
		super.onResume();
	}
	
	public int dpToPx(int dp) {
		return (int) (dp
				* getResources().getDisplayMetrics().density + 0.5f);
	}
	
}
