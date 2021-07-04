package com.rafflypohan.movieapp.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var darkModePreference: SwitchPreference

    private lateinit var keyDarkMode: String
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.root_preferences)
        init()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        keyDarkMode = resources.getString(R.string.key_dark_mode)

        darkModePreference = findPreference<SwitchPreference>(keyDarkMode) as SwitchPreference

        darkModePreference.isChecked =
            preferenceManager.sharedPreferences.getBoolean(keyDarkMode, false)
        darkModePreference.setDefaultValue(false)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == keyDarkMode) sharedPreferences?.let {
            darkModePreference.isChecked = it.getBoolean(keyDarkMode, false)
        }

        context?.let { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }

        val darkModePreferenceState =
            PreferenceManager.getDefaultSharedPreferences(context).getBoolean(keyDarkMode, false)

        when {
            darkModePreferenceState -> {
                context?.let { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
            }

            !darkModePreferenceState -> {
                context?.let { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
            }
        }
    }

}