package com.example.e_commerceapp.services;

import com.example.e_commerceapp.models.AccessTokenResponse;
import com.example.e_commerceapp.models.STKPushRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MpesaService {
    private static final String BASE_URL = "https://sandbox.safaricom.co.ke/";
    private static final String CONSUMER_KEY = "miw5VWlBWu4AMiMpgH6GsXt9UkXr04Sra6lC0YlpWUtBKyMg";
    private static final String CONSUMER_SECRET = "HCBdkzV7zEHqJyMIGIZkL0fAxWSSTBBkA3Ie1UOBEwj998KYIMcI7dG4niOo62U5";

    private static Retrofit retrofit = null;

    public interface MpesaApi {
        @retrofit2.http.GET("oauth/v1/generate?grant_type=client_credentials")
        Call<AccessTokenResponse> generateAccessToken(@retrofit2.http.Header("Authorization") String authorization);

        @retrofit2.http.POST("mpesa/stkpush/v1/processrequest")
        Call<Void> sendSTKPush(@retrofit2.http.Header("Authorization") String token, @retrofit2.http.Body STKPushRequest request);
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void generateAccessToken(Callback<AccessTokenResponse> callback) {
        MpesaApi api = getRetrofitInstance().create(MpesaApi.class);
        String credentials = Credentials.basic(CONSUMER_KEY, CONSUMER_SECRET);

        Call<AccessTokenResponse> call = api.generateAccessToken(credentials);
        call.enqueue(callback);
    }

    public static void sendSTKPush(String accessToken, STKPushRequest request, Callback<Void> callback) {
        MpesaApi api = getRetrofitInstance().create(MpesaApi.class);
        Call<Void> call = api.sendSTKPush(accessToken, request);
        call.enqueue(callback);
    }

    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    public static String generatePassword(String businessShortCode, String passkey, String timestamp) {
        String input = businessShortCode + passkey + timestamp;
        return android.util.Base64.encodeToString(input.getBytes(), android.util.Base64.NO_WRAP);
    }
}
