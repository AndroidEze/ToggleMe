package ezeezegg.toggleme.applications;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by egarcia on 7/5/16.
 */
public class VolleyApplication extends Application{
    private static VolleyApplication mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        mInstance = this;
    }


    public synchronized static VolleyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
