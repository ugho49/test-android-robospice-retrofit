package fr.ughostephan.myapprobospice.activity;

import android.app.Activity;
import com.octo.android.robospice.SpiceManager;
import fr.ughostephan.myapprobospice.network.service.RetrofitSpiceService;

/**
 * Created by ughostephan on 12/01/2018.
 */
public abstract class BaseSpiceActivity extends Activity {
    private SpiceManager spiceManager = new SpiceManager(RetrofitSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
