package com.example.jakewilson.weather.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jakewilson.weather.R;
import com.example.jakewilson.weather.adapters.WeatherAdapter;
import com.example.jakewilson.weather.events.ApiErrorEvent;
import com.example.jakewilson.weather.events.BusProvider;
import com.example.jakewilson.weather.events.LoadWeatherEvent;
import com.example.jakewilson.weather.events.WeatherLoadedEvent;
import com.example.jakewilson.weather.infinitepager.InfinitePagerAdapter;
import com.example.jakewilson.weather.models.WeatherResponse;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


public class WeatherActivity extends AppCompatActivity {
    private Bus mBus;
    private WeatherAdapter mAdapter;
    private InfinitePagerAdapter mWrapperAdapter;
    private ViewPager mViewPager;
    private ProgressDialog mProgress;
    private ShareActionProvider mShareActionProvider;
    private WeatherResponse mWeatherResponse;
    private LocationListener mLocationListener;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_pager);
        mAdapter = new WeatherAdapter(getSupportFragmentManager());
        mWrapperAdapter = new InfinitePagerAdapter(mAdapter);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWrapperAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBus().register(this);
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(this, getResources().getString(R.string.no_network), Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }
        if (mAdapter.getCount() == 0) {
            mProgress = ProgressDialog.show(this, getResources().getString(R.string.title_activity_weather_pager),
                    getResources().getString(R.string.progress), true);
            requestGPS();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getBus().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationManager != null)
            mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("QA", "landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("QA", "portrait");
        }
    }

    @Subscribe
    public void onWeatherLoaded(WeatherLoadedEvent event) {
        mProgress.dismiss();
        mWeatherResponse = event.forecast;
        mAdapter.setForecast(event.forecast);
    }

    @Subscribe
    public void onApiError(ApiErrorEvent event) {
        mProgress.dismiss();
        Toast.makeText(this, getResources().getString(R.string.no_data), Toast.LENGTH_LONG).show();
        this.finish();
    }

    private Bus getBus() {
        if (mBus == null) {
            mBus = BusProvider.getInstance();
        }
        return mBus;
    }

    public void setBus(Bus bus) {
        mBus = bus;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_pager, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        //getMenuInflater().inflate(R.layout.action_bar, menu);
        return true;
    }

    public void requestGPS() {
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (mAdapter.getCount() == 0)
                    getBus().post(new LoadWeatherEvent(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("QA", "onStatusChanged");
            }

            public void onProviderEnabled(String provider) {
                Log.d("QA", "onProviderEnabled");
            }

            public void onProviderDisabled(String provider) {
                Log.d("QA", "onProviderDisabled");
                getBus().post(new ApiErrorEvent(new Throwable()));
            }
        };

        if (checkGPSPermission()) {
           Location location =  mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location.getAccuracy() > 25){
                getBus().post(new LoadWeatherEvent(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
            }else {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.gps_permission_denied), Toast.LENGTH_LONG).show();
            this.finish();
        }

    }


    public boolean checkGPSPermission() {
        String coarse = "android.permission.ACCESS_COARSE_LOCATION";
        String fine = "android.permission.ACCESS_FINE_LOCATION";
        int c = this.checkCallingOrSelfPermission(coarse);
        int f = this.checkCallingOrSelfPermission(fine);
        return (c == PackageManager.PERMISSION_GRANTED || f == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_item_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s %s %s http://openweathermap.org/city/%s", getResources().getString(R.string.share),
                    mWeatherResponse.getCity().getName(), getResources().getString(R.string.this_week), String.valueOf(mWeatherResponse.getCity().getId())));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.title_activity_weather_pager)));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
