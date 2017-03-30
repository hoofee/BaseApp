package com.hoofee.everything.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.hoofee.everything.R;


public class ProgressWebView extends WebView {

	private ProgressBar progressbar;

	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar = new ProgressBar(context, null,android.R.attr.progressBarStyleHorizontal);
		progressbar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 6, 0, 0));
		addView(progressbar);
		// setWebViewClient(new WebViewClient(){});
//		setWebChromeClient(new WebChromeClient());
	}

	public ProgressBar getProgressbar(){
		return progressbar;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
