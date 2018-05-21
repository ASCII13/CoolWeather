package com.example.genewong.coolweather.util;

import android.text.TextUtils;

import com.example.genewong.coolweather.db.City;
import com.example.genewong.coolweather.db.County;
import com.example.genewong.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by genewong on 16/05/2018.
 */

public class HttpUtil {
    public static void sendRequestWithOkHttp(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static boolean getProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++) {
                    JSONObject provinceObject = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean getCityResponse(int provinceId, String response) {
        if (!TextUtils.isEmpty(response)) {
           try {
               JSONArray cities = new JSONArray(response);
               for (int i = 0; i < cities.length(); i++) {
                   JSONObject cityObject = cities.getJSONObject(i);
                   City city = new City();
                   city.setCityName(cityObject.getString("name"));
                   city.setCityCode(cityObject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
               }
               return true;
           } catch (JSONException e) {
               e.printStackTrace();
           }
        }
        return false;
    }

    public static boolean getCountyResponse(int cityId, String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray counties = new JSONArray(response);

                for (int i = 0; i < counties.length(); i++) {
                    JSONObject countyObject =counties.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
