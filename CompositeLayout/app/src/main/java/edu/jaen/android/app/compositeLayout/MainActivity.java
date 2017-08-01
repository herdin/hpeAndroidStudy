package edu.jaen.android.app.compositeLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private WebView mainWebView;
	public static String[] conArray = { "로또 잘 사기", "공짜로 점심 먹기", "비상금 만들기",
			"티안나게 연차쓰기", "직장상사에게 사랑받기", "술을 물처럼 마시기" };
	public static String[] eduArray = { "UML", "HTML5", "Android", "SQLite",
			"HybridApp", "Android 응용" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainHeader mainHeader = new MainHeader(this);
		mainHeader.init();
		mainTabRegister();

	}

	public void mainTabRegister() {

		findViewById(R.id.menuEduBut).setOnClickListener(this);
		findViewById(R.id.menuRefBut).setOnClickListener(this);
		findViewById(R.id.menuConBut).setOnClickListener(this);

	}

	private void mainContentShow(View v) {

		findViewById(R.id.main_tab_edu).setVisibility(View.INVISIBLE);
		findViewById(R.id.main_tab_ref).setVisibility(View.INVISIBLE);
		findViewById(R.id.main_tab_con).setVisibility(View.INVISIBLE);

		if (v.getId() == R.id.menuEduBut) {
			// Toast.makeText(this, "교육 페이지 선택", 1).show();
			findViewById(R.id.main_tab_edu).setVisibility(View.VISIBLE);
			loadEduInfoList();
		} else if (v.getId() == R.id.menuConBut) {
			// Toast.makeText(this, "컨설팅 페이지 선택", 1).show();
			findViewById(R.id.main_tab_con).setVisibility(View.VISIBLE);
			loadConsultList();
		} else if (v.getId() == R.id.menuRefBut) {
			// Toast.makeText(this, "참조 페이지 선택", 1).show();
			findViewById(R.id.main_tab_ref).setVisibility(View.VISIBLE);
			loadAndroidPage();
		} else {

		}

		findViewById(R.id.main_tab_edu).setBackgroundResource(
				R.drawable.menu_edu);
		findViewById(R.id.main_tab_ref).setBackgroundResource(
				R.drawable.menu_ref);
		findViewById(R.id.main_tab_con).setBackgroundResource(
				R.drawable.menu_con);

	}

	@Override
	public void onClick(View v) {
		mainContentShow(v);
	}

	private void loadConsultList() {

		ListView consultList = (ListView) findViewById(R.id.conSultInfoList);
		consultList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, conArray));

	}

	private void loadEduInfoList() {

		ListView eduInfoList = (ListView) findViewById(R.id.eduInfoList);
		eduInfoList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, eduArray));

	}

	private void loadAndroidPage() {

		mainWebView = (WebView) findViewById(R.id.mainWebView);
		mainWebView.setWebViewClient(new JaenWebViewClient());
		WebSettings set = mainWebView.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		mainWebView.loadUrl("http://www.naver.com");

	}

	private class JaenWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	}

}
