package cz.gopay.android.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Frantisek Sichinger
 */
public class GPWebViewClient extends WebViewClient {

    GPWebViewActivity context;

    public GPWebViewClient(GPWebViewActivity context) {
        this.context = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        try {
            URI uri = new URI(url);
            String domainName = uri.getHost();
            if (!domainName.contains("gopay")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome"); //prefer chrome if possible
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    intent.setPackage(null);
                    context.startActivity(intent);
                }
                context.finish();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
