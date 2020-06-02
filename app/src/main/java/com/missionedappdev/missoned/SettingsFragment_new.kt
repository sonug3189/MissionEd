package com.missionedappdev.missoned

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment_new : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}