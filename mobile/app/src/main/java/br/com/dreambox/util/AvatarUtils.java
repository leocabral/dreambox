package br.com.dreambox.util;

import android.content.Context;

import java.util.Random;

public class AvatarUtils {

    public static int getRandomResourceId(Context context) {
        int random = getRandomNumber();
        String resourceName = getAvatarFileName(random);
        return context.getResources().getIdentifier(resourceName, "drawable", context.getApplicationContext().getPackageName());
    }

    private static int getRandomNumber() {
        Random r = new Random();
        int low = 1;
        int high = 15;
        return r.nextInt(high - low) + low;
    }

    private static String getAvatarFileName(int avatarNumber) {
        return "avatar_" + String.valueOf(avatarNumber);
    }
}