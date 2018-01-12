package fr.ughostephan.myapprobospice.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.octo.android.robospice.SpiceManager;
import fr.ughostephan.myapprobospice.network.service.RetrofitSpiceService;

/**
 * Created by ughostephan on 12/01/2018.
 */
public abstract class BaseSpiceFragment extends Fragment {

    private SpiceManager spiceManager = new SpiceManager(RetrofitSpiceService.class);

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        // Please review https://github.com/octo-online/robospice/issues/96 for the reason of that
        // ugly if statement.
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }
}
