package org.amfoss.templeapp.settings;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import java.util.regex.Pattern;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.home.UserModel;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 4/4/2020
*/
public class SettingsActivity extends PreferenceActivity {
    private SharedPreferences prefs;

    EditTextPreference addMemberPreference;
    CheckBoxPreference isAdminPreference;
    EditTextPreference getAccessPreference;

    boolean isAdmin;

    SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup root = getRootView();
        Toolbar toolbar = (Toolbar) View.inflate(this, R.layout.toolbar, null);
        toolbar.setTitle("Settings");
        root.addView(toolbar, 0);

        addPreferencesFromResource(R.xml.preference_menu);

        settingsViewModel = new SettingsViewModel();
        addPreferences();
    }

    private void addPreferences() {
        addMemberPreference = (EditTextPreference) findPreference(PreferenceKeys.KEY_ADD_MEMBER);
        isAdminPreference = (CheckBoxPreference) findPreference(PreferenceKeys.KEY_IS_ADMIN);
        getAccessPreference = (EditTextPreference) findPreference(PreferenceKeys.KEY_GET_ACCESS);

        UserModel user = new UserModel();
        String dbUserName = user.getDbUserName();
        getAccessPreference.setDefaultValue(dbUserName);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        addMemberPreference.setOnPreferenceChangeListener(preferenceChangeListener());
        getAccessPreference.setOnPreferenceChangeListener(preferenceChangeListener());
        isAdminPreference.setOnPreferenceChangeListener(preferenceChangeListener());

        isAdmin = prefs.getBoolean(PreferenceKeys.KEY_IS_ADMIN, true);

        addMemberPreference.setEnabled(isAdmin);
        getAccessPreference.setEnabled(!isAdmin);

        checkNullEditTextPreference(addMemberPreference);
        checkNullEditTextPreference(getAccessPreference);
    }

    private ViewGroup getRootView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return (ViewGroup) findViewById(android.R.id.list).getParent().getParent().getParent();
        } else {
            return (ViewGroup) findViewById(android.R.id.list).getParent();
        }
    }

    private Preference.OnPreferenceChangeListener preferenceChangeListener() {
        return (preference, newValue) -> {
            switch (preference.getKey()) {
                case PreferenceKeys.KEY_ADD_MEMBER:
                    settingsViewModel.addMember(newValue.toString(), getApplicationContext());
                    return false;
                case PreferenceKeys.KEY_IS_ADMIN:
                    boolean isRequire = (Boolean) newValue;
                    addMemberPreference.setEnabled(isRequire);
                    getAccessPreference.setEnabled(!isRequire);
                    settingsViewModel.toggleAdmin(isRequire, getApplicationContext());
                    return true;
                case PreferenceKeys.KEY_GET_ACCESS:
                    settingsViewModel.SearchForMember(newValue.toString(), getApplicationContext());
                    return false;
            }
            return true;
        };
    }

    private void checkNullEditTextPreference(EditTextPreference editTextPreference) {
        editTextPreference
                .getEditText()
                .addTextChangedListener(
                        new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                Dialog dlg = editTextPreference.getDialog();
                                if (dlg instanceof android.app.AlertDialog) {
                                    android.app.AlertDialog alertDlg = (android.app.AlertDialog) dlg;
                                    Button positiveButton = alertDlg.getButton(AlertDialog.BUTTON_POSITIVE);

                                    if (isValid(editTextPreference.getEditText().getText().toString().trim())) {
                                        positiveButton.setEnabled(true);
                                    } else {
                                        positiveButton.setEnabled(false);
                                    }
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {}
                        });
    }

    public static boolean isValid(String email) {
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\."
                        + "[a-zA-Z0-9_+&*-]+)*@"
                        + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                        + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }
}
