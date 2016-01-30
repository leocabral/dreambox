package br.com.dreambox.api;

import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public class DreamboxApi {

    private static final String ENDPOINT_URL = "http://caixa-de-sonhos.appspot.com";

    private static Dreambox dreambox;

    public interface Dreambox {

        @GET("/api/dreams")
        void dreams(Callback<JsonObject> response);

        @GET("/api/dreams/{dreamId}")
        void getDreams(@Path("dreamId") int dreamId, Callback<JsonObject> response);

        @POST("/api/dreams")
        void addDream(@Field("name") String title,
                         @Field("description") String description, Callback<JsonObject> response);

        @PUT("/api/dreams/{dreamId}")
        void updateDream(@Path("dreamId") int dreamId, @Field("name") String title,
                         @Field("description") String description, Callback<JsonObject> response);

        @GET("/api/dreamers")
        void dreamers(Callback<JsonObject> response);

        @GET("/api/dreamers/{dreamerId}")
        void getDreamer(@Path("dreamerId") int dreamerId, Callback<JsonObject> response);

        @POST("/api/dreamers")
        void addDreamer(@Field("name") String title, @Field("lastName") String lastName,
                        @Field("birthday") String birthday, @Field("nickname") String nickname,
                        @Field("password") String password, @Field("email") String email,
                        @Field("organization") String organization, Callback<JsonObject> response);

        @PUT("/api/dreamers")
        void updateDreamer(Callback<JsonObject> response);

    }

    public static Dreambox get() {
        if (dreambox == null) {

            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(ENDPOINT_URL)
                    .setClient(new OkClient())
                    .build();

            dreambox = retrofit.create(Dreambox.class);
        }
        return dreambox;
    }

}


