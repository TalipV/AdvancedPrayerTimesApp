package com.example.advancedprayertimes.Logic.Entities.API_Entities;

import com.example.advancedprayertimes.Logic.Entities.PrayerTimePackageAbstractClass;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import kotlin.NotImplementedError;

public class MuwaqqitPrayerTimeDayEntity extends PrayerTimePackageAbstractClass
{
    // region static fields

    // endregion static fields

    // region fields

    @SerializedName("fajr_time")
    private LocalDateTime _fajrTime;

    @SerializedName("fajr_angle")
    private double _fajrAngle;

    @SerializedName("sunrise_time")
    private LocalDateTime _sunriseTime;
    @SerializedName("duha_time")
    private LocalDateTime _duhaTime;

    @SerializedName("zohr_time")
    private LocalDateTime _dhuhrTime;

    @SerializedName("mithl_time")
    private LocalDateTime _asrTime;
    @SerializedName("mithlain_time")
    private LocalDateTime _mithlaynTime;

    @SerializedName("karaha_time")
    private LocalDateTime _asrKarahaTime;
    @SerializedName("karaha_angle")
    private double _asrKarahaAngle;

    @SerializedName("sunset_time")
    private LocalDateTime _maghribTime;

    @SerializedName("esha_time")
    private LocalDateTime _ishaTime;

    @SerializedName("esha_angle")
    private double _ishaAngle;

    @SerializedName("fajr_date")
    private String _fajrDate;

    // endregion fields

    // region constructors

    public MuwaqqitPrayerTimeDayEntity(LocalDateTime fajrTime, LocalDateTime sunriseTime, LocalDateTime duhaTime, LocalDateTime dhuhrTime, LocalDateTime asrTime, LocalDateTime mithlaynTime, LocalDateTime maghribTime, LocalDateTime ishaTime, String fajrDate)
    {
        this._fajrTime = fajrTime;

        this._sunriseTime = sunriseTime;
        this._duhaTime = duhaTime;

        this._dhuhrTime = dhuhrTime;
        this._asrTime = asrTime;
        this._mithlaynTime = mithlaynTime;

        this._maghribTime = maghribTime;
        this._ishaTime = ishaTime;

        this._fajrDate = fajrDate;
    }

    // endregion constructors

    // region getter & setter

    public LocalDateTime getFajrTime()
    {
        return _fajrTime;
    }

    public void setFajrTime(LocalDateTime fajrTime)
    {
        _fajrTime = fajrTime;
    }

    public LocalDateTime getSunriseTime()
    {
        return _sunriseTime;
    }

    public void setSunriseTime(LocalDateTime sunrise_time)
    {
        _sunriseTime = sunrise_time;
    }

    public LocalDateTime getDuhaTime()
    {
        return _duhaTime;
    }

    public void setDuhaTime(LocalDateTime duhaTime)
    {
        _duhaTime = duhaTime;
    }

    public LocalDateTime getDhuhrTime()
    {
        return _dhuhrTime;
    }

    public void setDhuhrTime(LocalDateTime dhuhrTime)
    {
        _dhuhrTime = dhuhrTime;
    }

    public LocalDateTime getAsrTime()
    {
        return _asrTime;
    }

    public void setAsrTime(LocalDateTime asrTime)
    {
        _asrTime = asrTime;
    }

    public LocalDateTime getMithlaynTime()
    {
        return _mithlaynTime;
    }

    public void setMithlaynTime(LocalDateTime mithlaynTime)
    {
        _mithlaynTime = mithlaynTime;
    }

    public LocalDateTime getAsrKarahaTime()
    {
        return _asrKarahaTime;
    }

    public void setAsrKarahaTime(LocalDateTime asrKarahaTime)
    {
        _asrKarahaTime = asrKarahaTime;
    }

    public double getAsrKarahaAngle()
    {
        return _asrKarahaAngle;
    }

    public void setAsrKarahaAngle(double asrKarahaAngle)
    {
        _asrKarahaAngle = asrKarahaAngle;
    }

    public LocalDateTime getMaghribTime()
    {
        return _maghribTime;
    }

    public void setMaghribTime(LocalDateTime maghribTime)
    {
        _maghribTime = maghribTime;
    }

    @Override
    public LocalDateTime getIshtibaqAnNujumTime()
    {
        return null;
    }

    @Override
    public void setIshtibaqAnNujumTime(LocalDateTime ishtibaqAnNujumTime)
    {
        throw new NotImplementedError();
    }

    public LocalDateTime getIshaTime()
    {
        return _ishaTime;
    }

    public void setIshaTime(LocalDateTime ishaTime)
    {
        _ishaTime = ishaTime;
    }

    public String getDate()
    {
        return _fajrDate;
    }

    public void setDate(String fajrDate)
    {
        _fajrDate = fajrDate;
    }

    public double getFajrAngle()
    {
        return _fajrAngle;
    }

    public void setFajrAngle(double fajrAngle)
    {
        _fajrAngle = fajrAngle;
    }

    public double getIshaAngle()
    {
        return _ishaAngle;
    }

    public void setIshaAngle(double ishaAngle)
    {
        _ishaAngle = ishaAngle;
    }

    // endregion getter & setter

    // region overidden

    // endregion overidden

    // region methods

    // endregion methods

    // region static methods

    // endregion static methods
}
