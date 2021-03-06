package com.example.advancedprayertimes.ui.prayer_setting_ui_components

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.MenuItem
import androidx.preference.ListPreference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.example.advancedprayertimes.R
import com.example.advancedprayertimes.logic.AppEnvironment
import com.example.advancedprayertimes.logic.enums.EPrayerTimeMomentType
import com.example.advancedprayertimes.logic.enums.EPrayerTimeType
import com.example.advancedprayertimes.logic.enums.ESupportedAPIs
import com.example.advancedprayertimes.logic.setting_entities.PrayerTimeBeginningEndSettingsEntity
import com.example.advancedprayertimes.logic.util.DataManagementUtil.getTimeSettingsEntityKeyForSharedPreference
import com.google.gson.Gson
import java.util.*
import java.util.stream.Stream
import kotlin.streams.asSequence

class PrayerBeginningEndSettingPreferencesFragment(
    private var prayerType: EPrayerTimeType = EPrayerTimeType.Fajr,
    private var _isBeginning: Boolean = true
) : PreferenceFragmentCompat() {

    private var _preferenceChangeListener: OnSharedPreferenceChangeListener? = null
    private var apiSettingsPreferenceCategory: PreferenceCategory? = null
    private var apiSelectionListPreference: ListPreference? = null
    private var minuteAdjustmentListPreference: ListPreference? = null
    private var fajrDegreesListPreference: ListPreference? = null
    private var ishaDegreesListPreference: ListPreference? = null

    private var prayerTypeWithMomentType: AbstractMap.SimpleEntry<EPrayerTimeType, EPrayerTimeMomentType> =
        AbstractMap.SimpleEntry(
            prayerType,
            if (_isBeginning) EPrayerTimeMomentType.Beginning else EPrayerTimeMomentType.End
        )

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.prayer_beginning_end_settings_preferences, rootKey)
        apiSettingsPreferenceCategory = findPreference(PREFERENCE_CATEGORY_API_SETTINGS) ?: return
        apiSelectionListPreference = findPreference(PREFERENCE_NAME_API_SELECTION) ?: return
        minuteAdjustmentListPreference = findPreference(PREFERENCE_NAME_MINUTE_ADJUSTMENT_SELECTION) ?: return
        fajrDegreesListPreference = findPreference(PREFERENCE_NAME_FAJR_DEGREE_SELECTION) ?: return
        ishaDegreesListPreference = findPreference(PREFERENCE_NAME_ISHA_DEGREE_SELECTION) ?: return

        configureAPISelector(apiSelectionListPreference!!)
        var selectedAPI = ESupportedAPIs.Undefined
        var minuteAdjustmentValue = 0
        var fajrDegreeValue = -12.0
        var ishaDegreeValue = -12.0
        var settings = AppEnvironment.prayerSettingsByPrayerType[prayerTypeWithMomentType.key]!!
            .GetBeginningEndSettingByMomentType(_isBeginning)
        if (settings == null) {
            settings = PrayerTimeBeginningEndSettingsEntity(
                selectedAPI,
                minuteAdjustmentValue,
                fajrDegreeValue,
                ishaDegreeValue
            )
            AppEnvironment.prayerSettingsByPrayerType[prayerTypeWithMomentType.key]!!
                .SetBeginningEndSettingByMomentType(_isBeginning, settings)
        } else {
            selectedAPI = settings.api
            minuteAdjustmentValue = settings.minuteAdjustment
            if (settings.fajrCalculationDegree != null) {
                fajrDegreeValue = settings.fajrCalculationDegree!!
            }
            if (settings.ishaCalculationDegree != null) {
                ishaDegreeValue = settings.ishaCalculationDegree!!
            }
        }
        apiSelectionListPreference!!.value = selectedAPI.toString()
        minuteAdjustmentListPreference!!.value = minuteAdjustmentValue.toString()
        fajrDegreesListPreference!!.value = fajrDegreeValue.toString() + ""
        ishaDegreesListPreference!!.value = ishaDegreeValue.toString() + ""
        updatePreferenceVisibility()

        // change listener for all preference value changes
        _preferenceChangeListener =
            OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences, key: String ->
                onPreferenceChange(
                    sharedPreferences,
                    key
                )
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun onPreferenceChange(sharedPreferences: SharedPreferences, key: String) {

        val prayerSettings = AppEnvironment.prayerSettingsByPrayerType[prayerTypeWithMomentType.key]
            ?: return

        if(sharedPreferences.contains(key))
        {
            val beginningEndSettings: PrayerTimeBeginningEndSettingsEntity? = prayerSettings.GetBeginningEndSettingByMomentType(_isBeginning)

            when(key) {
                apiSelectionListPreference!!.key -> {
                    beginningEndSettings!!.api = ESupportedAPIs.valueOf(apiSelectionListPreference!!.value)
                    updatePreferenceVisibility()
                }
                minuteAdjustmentListPreference!!.key -> {
                    beginningEndSettings!!.minuteAdjustment = minuteAdjustmentListPreference!!.value.toInt()
                }
                fajrDegreesListPreference!!.key -> {
                    beginningEndSettings!!.fajrCalculationDegree = fajrDegreesListPreference!!.value.toDouble()
                }
                ishaDegreesListPreference!!.key -> {
                    beginningEndSettings!!.ishaCalculationDegree = ishaDegreesListPreference!!.value.toDouble()
                }
            }
        }

        val jsonString = Gson().toJson(prayerSettings)
        val globalSharedPreference = this.requireActivity()
            .getSharedPreferences(
                AppEnvironment.GLOBAL_SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )

        globalSharedPreference.edit().putString(
            getTimeSettingsEntityKeyForSharedPreference(prayerTypeWithMomentType.key),
            jsonString
        ).apply()
    }

    // region methods
    private fun configureAPISelector(apiSelectionListPreference: ListPreference) {
        var apiNamesArray: Array<String>? = null
        apiNamesArray = if (prayerTypeWithMomentType.key === EPrayerTimeType.Duha) {
            Stream.of(*ESupportedAPIs.values()).asSequence()
                .filter { x: ESupportedAPIs -> x === ESupportedAPIs.Undefined || x === ESupportedAPIs.Muwaqqit }
                .map { x -> x.toString() }
                .toList().toTypedArray()
        } else {
            Stream.of(*ESupportedAPIs.values()).asSequence()
                .map { obj: ESupportedAPIs -> obj.name }
                .toList().toTypedArray()
        }
        apiSelectionListPreference.entries = apiNamesArray
        apiSelectionListPreference.entryValues = apiNamesArray
    }

    private fun updatePreferenceVisibility() {
        val isDegreeAPISelected = (ESupportedAPIs.valueOf(
            apiSelectionListPreference!!.value
        ) === ESupportedAPIs.Muwaqqit
                ||
                ESupportedAPIs.valueOf(apiSelectionListPreference!!.value) === ESupportedAPIs.AlAdhan)
        val showFajrDegreeSelector =
            isDegreeAPISelected && PrayerTimeBeginningEndSettingsEntity.FAJR_DEGREE_TYPES.contains(
                prayerTypeWithMomentType
            )
        val showIshaDegreeSelector =
            isDegreeAPISelected && PrayerTimeBeginningEndSettingsEntity.ISHA_DEGREE_TYPES.contains(
                prayerTypeWithMomentType
            )
        fajrDegreesListPreference!!.isVisible = showFajrDegreeSelector
        ishaDegreesListPreference!!.isVisible = showIshaDegreeSelector
        apiSettingsPreferenceCategory!!.isVisible =
            fajrDegreesListPreference!!.isVisible || ishaDegreesListPreference!!.isVisible
    }

    // endregion methods
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences!!.unregisterOnSharedPreferenceChangeListener(
            _preferenceChangeListener
        )
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences!!.registerOnSharedPreferenceChangeListener(
            _preferenceChangeListener
        )
    }

    companion object {
        // region static fields
        private const val PREFERENCE_CATEGORY_API_SETTINGS = "apiSettingsPreferenceCategory"
        private const val PREFERENCE_NAME_API_SELECTION = "apiSelection"
        private const val PREFERENCE_NAME_MINUTE_ADJUSTMENT_SELECTION = "minuteAdjustmentSelection"
        private const val PREFERENCE_NAME_FAJR_DEGREE_SELECTION = "fajrCalculationDegree"
        private const val PREFERENCE_NAME_ISHA_DEGREE_SELECTION = "ishaCalculationDegree"
    }

}