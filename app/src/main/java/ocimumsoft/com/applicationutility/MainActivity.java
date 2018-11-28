package ocimumsoft.com.applicationutility;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ocimumsoft.com.mylibrary.RuntimePermissionsActivity;
import ocimumsoft.com.mylibrary.VolleyRequest.VolleyApiClient;
import ocimumsoft.com.mylibrary.VolleyRequest.VolleyCallback;

public class MainActivity extends RuntimePermissionsActivity {
    private static final int REQUEST_PERMISSIONS = 20;
    private static final String TAG = MainActivity.class.getSimpleName();
    final String sip_amount = "250000";
    final String interest_rate = "12";
    final String period = "10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyApiClient varRequest = new VolleyApiClient(MainActivity.this);
                varRequest.VolleyStringRequest(TAG + "-google", "http://www.google.com", new VolleyCallback() {
                    @Override
                    public void onResponse(String result) {
                        ((TextView) findViewById(R.id.tv_hello)).setText(result);
                    }
                });
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RuntimePermissions", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MainActivity.super.requestAppPermissions(new
                                                String[]{Manifest.permission.READ_CONTACTS,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.ACCESS_FINE_LOCATION}, R.string
                                                .runtime_permissions_txt
                                        , REQUEST_PERMISSIONS);
                            }
                        }).show();
            }
        });

        VolleyApiClient varRequest = new VolleyApiClient(MainActivity.this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("sip_amount", sip_amount);
        params.put("interest_rate", interest_rate);
        params.put("period", period);
        params.put("key", "1f72650b-2c60-4f86-8690-bf27df23a310");
        varRequest.VolleyStringRequest(TAG + "-SIPCalc", "https://www.advisorkhoj.com/api/calc/getSIPCalcResult", params, new VolleyCallback() {
            @Override
            public void onResponse(String result) {
                ((TextView) findViewById(R.id.tv_hello)).setText(result);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
