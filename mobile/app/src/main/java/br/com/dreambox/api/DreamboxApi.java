package br.com.dreambox.api;

import android.telecom.Call;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.squareup.okhttp.OkHttpClient;
import retrofit.client.OkClient;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public class DreamboxApi {

    private static final String ENDPOINT_URL = "http://caixa-de-sonhos.appspot.com";

    private static Dreambox dreambox;

    public interface Dreambox {

        @GET("/api/dreams")
        void dreams(Callback<JsonArray> response);

        @GET("/api/dreams/{dreamId}")
        void getDream(@Path("dreamId") int dreamId, Callback<JsonObject> response);

        @POST("/api/dreams")
        @FormUrlEncoded
        void addDream(@Field("name") String title,
                      @Field("description") String description, @Field("dreamer") long dreamer, Callback<JsonObject> response);

        @GET("/api/dreams/random")
        void getRandomDream(Callback<JsonArray> response);

        @GET("/api/dreams/search/{term}")
        void getSearchDream(@Path("term") String term, Callback<JsonArray> response);

        @GET("/api/dreamers")
        void dreamers(Callback<JsonArray> response);

        @GET("/api/dreamers/{dreamerId}")
        void getDreamer(@Path("dreamerId") int dreamerId, Callback<JsonObject> response);

        @GET("/api/dreams/of/{dreamerId}")
        void getDreamOf(@Path("dreamerId") int dreamerId, Callback<JsonArray> response);

        @POST("/api/dreamers")
        @FormUrlEncoded
        void addDreamer(@Field("name") String title, @Field("last_name") String lastName,
                        @Field("birthday") String birthday, @Field("nickname") String nickname,
                        @Field("password") String password, @Field("email") String email,
                        @Field("organization") String organization, Callback<JsonObject> response);




    }

    public static Dreambox get() {
        if (dreambox == null) {
            OkHttpClient okHttpClient = new OkHttpClient();

            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(ENDPOINT_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(okHttpClient))
                    .build();

            dreambox = retrofit.create(Dreambox.class);
        }
        return dreambox;
    }

}


