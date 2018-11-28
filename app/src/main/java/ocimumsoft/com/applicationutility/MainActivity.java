package ocimumsoft.com.applicationutility;

import android.Manifest;
import android.location.Address;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ocimumsoft.com.mylibrary.GPSTracker;
import ocimumsoft.com.mylibrary.GPSTracker2;
import ocimumsoft.com.mylibrary.RuntimePermissionsActivity;
import ocimumsoft.com.mylibrary.VolleyRequest.RequestCallback;
import ocimumsoft.com.mylibrary.VolleyRequest.RequestClient;

public class MainActivity extends RuntimePermissionsActivity {
    private static final int REQUEST_PERMISSIONS = 20;
    private static final String TAG = MainActivity.class.getSimpleName();
    final String sip_amount = "250000";
    final String interest_rate = "12";
    final String period = "10";
    private RecyclerView rv_data;
    List<RecyclerViewModel> recyclerViewModelList;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv_data = (RecyclerView) findViewById(R.id.rv_data);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setItemAnimator(new DefaultItemAnimator());
        recyclerViewModelList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, recyclerViewModelList);
        rv_data.setAdapter(recyclerViewAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestClient varRequest = new RequestClient(MainActivity.this);
                varRequest.GetStringRequest(TAG + "-google", "http://avsshoppingo.com/wp-json/wc/v2/cart/totals", new RequestCallback() {
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

        RequestClient varRequest = new RequestClient(MainActivity.this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("sip_amount", sip_amount);
        params.put("interest_rate", interest_rate);
        params.put("period", period);
        params.put("key", "1f72650b-2c60-4f86-8690-bf27df23a310");
        varRequest.PostStringRequest(TAG + "-SIPCalc", "https://www.advisorkhoj.com/api/calc/getSIPCalcResult", params, new RequestCallback() {
            @Override
            public void onResponse(String result) {
                ((TextView) findViewById(R.id.tv_hello)).setText(result);
            }
        });
        PrepareData();
    }

    public void PrepareData() {
        for (int i = 0; i < 20; i++) {
            RecyclerViewModel recyclerViewModel = new RecyclerViewModel("DataName" + i, "DataDescription" + i, R.mipmap.ic_launcher_round);
            recyclerViewModelList.add(recyclerViewModel);
        }
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
        GPSTracker mGPS = new GPSTracker(this);

        TextView text = (TextView) findViewById(R.id.tv_hello);
        /*if(mGPS.canGetLocation()){
            mGPS.getLocation();
            text.setText("Lat"+mGPS.getLatitude()+"Lon"+mGPS.getLongitude());
        }else{
            text.setText("Unabletofind");
            System.out.println("Unable");
        }*/
        // check if GPS enabled
        GPSTracker2 gpsTracker = new GPSTracker2(this);

        if (gpsTracker.getIsGPSTrackingEnabled()) {

            String stringLatitude = String.valueOf(gpsTracker.getLatitude());


            String stringLongitude = String.valueOf(gpsTracker.getLongitude());


            String country = gpsTracker.getCountryName(this);


            String city = gpsTracker.getLocality(this);


            String postalCode = gpsTracker.getPostalCode(this);


            String addressLine = gpsTracker.getAddressLine(this);


            List<Address> addresses = gpsTracker.getGeocoderAddress(this);

            text.setText("stringLatitude- " + stringLatitude + " stringLongitude- " + stringLongitude + "\ncountry- "
                    + country + " city- " + city + " postalCode-" + postalCode + "\naddressLine- " + addressLine+ "\naddresses- " + addresses.toString());

        } /*else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            //gpsTracker.showSettingsAlert();
        }*/
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
