package sol.yackeen.skill4skill.extra_classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Hima on 7/23/2016.
 */
public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";

    public  static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}
/*
public static SharedPreferences getDefaultSharedPreferences(Context context) {
    return context.getSharedPreferences(getDefaultSharedPreferencesName(context),
        getDefaultSharedPreferencesMode());
}

private static String getDefaultSharedPreferencesName(Context context) {
    return context.getPackageName() + "_preferences";
}

private static int getDefaultSharedPreferencesMode() {
    return Context.MODE_PRIVATE;
}


 */