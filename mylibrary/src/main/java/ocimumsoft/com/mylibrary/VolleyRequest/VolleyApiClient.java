package ocimumsoft.com.mylibrary.VolleyRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.Objects;

import ocimumsoft.com.mylibrary.R;

public class VolleyApiClient {
    private Context context;
    private RequestQueue mRequestQueue;
    ProgressDialog progress;

    public VolleyApiClient(Context context) {
        // mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuf if you need
        this.context = context;
        progress = ProgressDialog.show(context, null, null, true);
        progress.setContentView(R.layout.layout_progressdialog);
        Objects.requireNonNull(progress.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.show();
    }

    public void VolleyStringRequest(final String TAG, String Url, final VolleyCallback callback) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        Log.d(TAG, "onResponse:- " + Response);
                        // To dismiss the dialog
                        progress.dismiss();
                        callback.onResponse(Response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                // To dismiss the dialog
                progress.dismiss();
                callback.onResponse("VolleyError" + e.getMessage());
                Log.d(TAG, "VolleyError:- " + e.getMessage());
            }
        });
        // Add the request to the RequestQueue.
        //mRequestQueue.add(stringRequest);
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest, TAG);
    }

    public void VolleyStringRequest(final String TAG, String Url, final Map<String, String> paramsss, final VolleyCallback callback) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // response
                            Log.d(TAG, "SIPCal Response:- " + response);
                            // To dismiss the dialog
                            progress.dismiss();
                            callback.onResponse(response);

                        } catch (NullPointerException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // To dismiss the dialog
                        progress.dismiss();
                        callback.onResponse("VolleyError" + error.getMessage());
                        Log.d(TAG, "VolleyError:- " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return paramsss;
            }

        };
        VolleySingleton.getInstance(context).addToRequestQueue(postRequest, TAG);
    }

}
