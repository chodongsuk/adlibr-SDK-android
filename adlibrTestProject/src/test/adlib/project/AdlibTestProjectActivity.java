package test.adlib.project;

import com.mocoplex.adlib.AdlibActivity;
import com.mocoplex.adlib.AdlibConfig;
import com.mocoplex.adlib.AdlibRewardIcon;
import com.mocoplex.adlib.AdlibRewardLink;
import com.mocoplex.adlib.AdlibManager.AdlibVersionCheckingListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

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
    }
    
    // AndroidManifest.xml에 권한과 activity를 추가하여야 합니다.     
    protected void initAds()
    {
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
        // 쓰지 않을 플랫폼은 JAR 파일 및 test.adlib.project.ads 경로에서 삭제하면 최종 바이너리 크기를 줄일 수 있습니다.        
        
        // SMART* dialog 노출 시점 선택시 / setAdlibKey 키가 호출되는 activity 가 시작 activity 이며 해당 activity가 종료되면 app 종료로 인식합니다.
        // adlibr.com 에서 발급받은 api 키를 입력합니다.
        // https://sec.adlibr.com/admin/dashboard.jsp
        AdlibConfig.getInstance().setAdlibKey("ADLIB - API - KEY");        
                

         /*
         // Locale 별 다른 스케줄을 적용하신다면,
         Locale locale = this.getResources().getConfiguration().locale;
         String lc = locale.getLanguage();
         
         if(lc.equals("ko"))
         {
         // 다국어 스케줄을 설정하시려면 애드립에서 별도로 키를 생성하시고 해당 키를 적용해주세요.
         AdlibConfig.getInstance().setAdlibKey("대한민국 광고 스케줄링");
         }
         else
         {
         // 다국어 스케줄을 설정하시려면 애드립에서 별도로 키를 생성하시고 해당 키를 적용해주세요.
         AdlibConfig.getInstance().setAdlibKey("그밖의 나라");
         }
         */

        
        /* 
         * deprecated : SMART* dialog 를 통해 보다 쉽게 버전관리를 할 수 있습니다.
         */

		/*
		// 광고뷰가 없는 activity 에서는 listener 대신 아래와 같은 방법으로 설정한 현재 버전을 가져올 수 있습니다.
		// 애드립에서 설정한 버전정보를 아래와 같이 수신합니다.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
       	  	String ver = _amanager.getCurrentVersion();
          }
        }, 1000);
	 	*/        
        /*
        // 클라이언트 버전관리를 위한 리스너 추가
        // 서버에서 클라이언트 버전을 관리하여 사용자에게 업데이트를 안내할 수 있습니다. (option)
        this.setVersionCheckingListner(new AdlibVersionCheckingListener(){

			@Override
			public void gotCurrentVersion(String ver) {
				
				// 서버에서 설정한 버전정보를 수신했습니다.
				// 기존 클라이언트 버전을 확인하여 적절한 작업을 수행하세요.
				double current = 0.9;
				
				double newVersion = Double.parseDouble(ver);				
				if(current >= newVersion)
					return;
					
				
				new AlertDialog.Builder(AdlibTestProjectActivity.this)
			    .setTitle("버전 업데이트")
			    .setMessage("프로그램을 업데이트 하시겠습니까?")
					    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
					      public void onClick(DialogInterface dialog, int whichButton) {
					    	  // 마켓 또는 안내 페이지로 이동합니다.
					      }
					    })	    
					    .setNegativeButton("no", new DialogInterface.OnClickListener() {
					      public void onClick(DialogInterface dialog, int whichButton) {
					    	  // 업데이트를 하지 않습니다.
					      }
					    })	    
			    .show();
				
			}
        });
        */
        
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