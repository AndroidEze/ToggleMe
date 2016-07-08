package ezeezegg.toggleme.helpers;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ezeezegg.toggleme.Interfaces.AsyncVolleyResponse;
import ezeezegg.toggleme.applications.VolleyApplication;

/**
 * Created by egarcia on 7/5/16.
 */
public class VolleyHelper {
    private Context context;
    private String url;
    private AsyncVolleyResponse asyncVolleyResponse;

    public VolleyHelper(Context context, String url, AsyncVolleyResponse asyncVolleyResponse) {
        this.context = context;
        this.url = url;
        this.asyncVolleyResponse = asyncVolleyResponse;
    }

    public void makeVolleyRequest(final String email, final String password) {
        StringRequest request =  new StringRequest(Request.Method.GET  , url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, "response: " + response, Toast.LENGTH_SHORT ).show();
                asyncVolleyResponse.AsyncVolleyFinish(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "ERROR: " + error, Toast.LENGTH_SHORT ).show();
                asyncVolleyResponse.AsyncVolleyError("error");
            }
        }) {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",email);
                params.put("password",password);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", email, password).getBytes(), Base64.DEFAULT)));
                params.put("username" , email );
                params.put("password" , password );
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }
}
