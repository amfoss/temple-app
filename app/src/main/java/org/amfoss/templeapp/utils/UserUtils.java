package org.amfoss.templeapp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 02/12/2019
*/
public class UserUtils {

    private FirebaseUser user;
    private String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    private String userName;
    private String dbUserName;

    public UserUtils() {}

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
        return user.getDisplayName();
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public FirebaseUser getUser() {
        return user;
    }
}
