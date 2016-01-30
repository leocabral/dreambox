package br.com.dreambox.api;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class DreamboxApi {

    private static final String ENDPOINT_URL = "";

    private static Dreambox dreambox;

    public interface Dreambox {

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


