package sol.yackeen.skill4skill.extra_classes;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gmgn on 8/3/2016.
 */
public class myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        getHash();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
   public void getHash(){

       try {
           PackageInfo info = getPackageManager().getPackageInfo(
                   "sol.yackeen.skill4skill",
                   PackageManager.GET_SIGNATURES);
           for (Signature signature : info.signatures) {
               MessageDigest md = MessageDigest.getInstance("SHA");
               md.update(signature.toByteArray());
               Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
           }
       } catch (PackageManager.NameNotFoundException e) {

       } catch (NoSuchAlgorithmException e) {

       }
}}
