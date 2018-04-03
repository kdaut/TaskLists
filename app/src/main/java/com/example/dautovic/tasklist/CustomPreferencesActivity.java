package com.example.dautovic.tasklist;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dautovic on 31.1.2018..
 */

public class CustomPreferencesActivity extends PreferenceActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new CustomPreferencesFragment()).commit();
    }

}
