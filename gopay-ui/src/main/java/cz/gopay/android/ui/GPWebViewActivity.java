package cz.gopay.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import cz.gopay.android.ui.R;

public class GPWebViewActivity extends Activity {

    private WebView webView;
    private Button cancellbutton;
    private String gwUrl;
    private LinearLayout linearLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getStringExtra("back-btn-name");
        gwUrl = intent.getStringExtra("gw-url");
        setContentView(R.layout.activity_gpwebview);
        webView = (WebView) findViewById(R.id.webView);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        cancellbutton = new Button(linearLayout.getContext());
        cancellbutton = (Button) findViewById(R.id.button);
        cancellbutton.setText(name);
        cancellbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPWebViewActivity.super.onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebViewClient(new GPWebViewClient(this));

        webView.loadUrl(gwUrl);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        webView.loadUrl(gwUrl);
    }

    public WebView getWebView() {
        return webView;
    }
}