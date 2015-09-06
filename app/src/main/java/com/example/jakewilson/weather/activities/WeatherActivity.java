package com.example.jakewilson.weather.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jakewilson.weather.R;
import com.example.jakewilson.weather.adapters.WeatherAdapter;
import com.example.jakewilson.weather.events.BusProvider;
import com.example.jakewilson.weather.events.LoadWeatherEvent;
import com.example.jakewilson.weather.events.WeatherLoadedEvent;
import com.example.jakewilson.weather.infinitepager.InfinitePagerAdapter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class WeatherActivity extends AppCompatActivity {
    private Bus mBus;
    private WeatherAdapter mAdapter;
    private InfinitePagerAdapter mWrapperAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_pager);
        Log.d("QA", "savedInstanceState != null");
        mAdapter = new WeatherAdapter(getSupportFragmentManager());
        mWrapperAdapter = new InfinitePagerAdapter(mAdapter);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWrapperAdapter);
    }

    @Override
    public void onResume() {
        if (this != null) {
            super.onResume();
            getBus().register(this);
            if (mAdapter != null && mAdapter.getCount() == 0)
                getBus().post(new LoadWeatherEvent());
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
        // store the data in the fragment
     //   dataFragment.setData(collectMyLoadedData());
    }

    @Subscribe
    public void onWeatherLoaded(WeatherLoadedEvent event) {
        mAdapter.setForecast(event.forecast);
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
