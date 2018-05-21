package com.example.arunn.silfraagri.widgets;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.example.arunn.silfraagri.R;
import com.example.arunn.silfraagri.activities.Weather_activity;
import com.example.arunn.silfraagri.activities.SplashActivity;
import com.example.arunn.silfraagri.utils.UnitConvertor;
import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class DashClockWeatherExtension extends DashClockExtension {
    private static final Uri URI_BASE = Uri.parse("content://cz.martykan.forecastie.authority");
    private static final String UPDATE_URI_PATH_SEGMENT = "dashclock/update";

    @Override
    protected void onInitialize(boolean isReconnect) {
        super.onInitialize(isReconnect);


        removeAllWatchContentUris();
        addWatchContentUris(new String[]{getUpdateUri().toString()});
    }

    @Override
    protected void onUpdateData(int reason) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String result = sp.getString("lastToday", "{}");
        try {
            JSONObject reader = new JSONObject(result);

            // Temperature
            float temperature = UnitConvertor.convertTemperature(Float.parseFloat(reader.optJSONObject("main").getString("temp").toString()), sp);
            if (sp.getBoolean("temperatureInteger", false)) {
                temperature = Math.round(temperature);
            }

            // Wind
            double wind;
            try {
                wind = Double.parseDouble(reader.optJSONObject("wind").getString("speed").toString());
            } catch (Exception e) {
                e.printStackTrace();
                wind = 0;
            }
            wind = UnitConvertor.convertWind(wind, sp);

            // Pressure
            double pressure = UnitConvertor.convertPressure((float) Double.parseDouble(reader.optJSONObject("main").getString("pressure").toString()), sp);

            Weather_activity.initMappings();
            publishUpdate(new ExtensionData()
                    .visible(true)
                    .icon(R.drawable.ic_cloud_white_18dp)
                    .status(getString(R.string.dash_clock_status, new DecimalFormat("0.#").format(temperature), localize(sp, "unit", "°C")))
                    .expandedTitle(getString(R.string.dash_clock_expanded_title, new DecimalFormat("0.#").format(temperature), localize(sp, "unit", "°C"), reader.optJSONArray("weather").getJSONObject(0).getString("description")))
                    .expandedBody(getString(R.string.dash_clock_expanded_body, reader.getString("name"), reader.optJSONObject("sys").getString("country"),
                            new DecimalFormat("0.0").format(wind), localize(sp, "speedUnit", "m/s"),
                            new DecimalFormat("0.0").format(pressure), localize(sp, "pressureUnit", "hPa"),
                            reader.optJSONObject("main").getString("humidity")))
                    .clickIntent(new Intent(this, SplashActivity.class)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String localize(SharedPreferences sp, String preferenceKey, String defaultValueKey) {
        return Weather_activity.localize(sp, this, preferenceKey, defaultValueKey);
    }

    public static void updateDashClock(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.notifyChange(getUpdateUri(), null);
    }

    private static Uri getUpdateUri() {
        return Uri.withAppendedPath(URI_BASE, UPDATE_URI_PATH_SEGMENT);
    }

}
