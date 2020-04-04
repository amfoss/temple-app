package org.amfoss.templeapp.home;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.amfoss.templeapp.settings.PreferenceKeys;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 02/12/2019
*/
public class UserModel {

    private FirebaseUser user;
    private String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    private String userName;
    private String dbUserName;
    private String realDbUserName;
    private SharedPreferences preferences;

    public UserModel() {}

    public String getRealDbUserName(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String adminEmail = preferences.getString(PreferenceKeys.KEY_GET_ACCESS, getUserEmail());
        realDbUserName = adminEmail.substring(0, adminEmail.indexOf("@"));
        return realDbUserName;
    }

    public void setRealDbUserName(String realDbUserName) {
        this.realDbUserName = realDbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbUserName() {
        dbUserName = userEmail.substring(0, userEmail.indexOf("@"));
        return dbUserName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public FirebaseUser getUser() {
        return user;
    }
}
