package org.amfoss.templeapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.amfoss.templeapp.Util.Constants;
import org.amfoss.templeapp.home.UserModel;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 4/4/2020
*/
public class SettingsViewModel extends ViewModel {

    private DatabaseReference userDb;
    private boolean isAccessExists = false;

    public void addMember(String memberEmail, Context context) {
        UserModel user = new UserModel();
        String dbUserName = user.getDbUserName();
        createFirebaseInstance(dbUserName);

        String memberName = memberEmail.substring(0, memberEmail.indexOf("@"));

        MemberModel memeberModel = new MemberModel(memberEmail);
        userDb
                .child(Constants.DB_PROFILE)
                .child(Constants.DB_MEMBERS)
                .child(memberName)
                .setValue(memeberModel)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "Member Added", Toast.LENGTH_SHORT).show();
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    public void SearchForMember(String adminEmail, Context context) {
        String adminUserName = adminEmail.substring(0, adminEmail.indexOf("@"));
        createFirebaseInstance(adminUserName);

        UserModel user = new UserModel();
        String userName = user.getDbUserName();

        userDb
                .child(Constants.DB_PROFILE)
                .child(Constants.DB_MEMBERS)
                .child(userName)
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {

                                    SharedPreferences preferences =
                                            PreferenceManager.getDefaultSharedPreferences(context);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString(PreferenceKeys.KEY_GET_ACCESS, adminEmail);
                                    editor.apply();

                                    Toast.makeText(context, "You have been granted access", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Toast.makeText(
                                                    context,
                                                    "Email not found. Please contact admins of your temple if you think this is a mistake",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    isAccessExists = false;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void createFirebaseInstance(String value) {
        userDb = FirebaseDatabase.getInstance().getReference(value);
    }

    public void toggleAdmin(boolean isAdmin, Context context) {
        if (isAdmin) {
            UserModel userModel = new UserModel();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PreferenceKeys.KEY_GET_ACCESS, userModel.getUserEmail());
            editor.apply();
        }
    }
}
