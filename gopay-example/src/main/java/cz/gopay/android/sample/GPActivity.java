package cz.gopay.android.sample;

import android.content.Intent;
import android.widget.ProgressBar;

/**
 * Created by Frantisek Sichinger on 23.3.16.
 */
public interface GPActivity {
    ProgressBar getProgressBar();
    void startActivity(Intent intent);
}
