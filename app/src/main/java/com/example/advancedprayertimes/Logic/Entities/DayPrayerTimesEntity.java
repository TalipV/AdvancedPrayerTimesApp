package com.example.advancedprayertimes.Logic.Entities;

import com.example.advancedprayertimes.Logic.Enums.EPrayerPointInTimeType;

import java.util.Date;

public class DayPrayerTimesEntity
{
    private Date _fajrTime;
    private Date _sunRiseTime;

    private Date _dhuhrTime;
    private Date _asrTime;

    private Date _maghribTime;
    private Date _ishaTime;

    public DayPrayerTimesEntity(MuwaqqitPrayerTimeDayEntity muwaqqitTime)
    {
        this(
                muwaqqitTime.getFajrTime(),
                muwaqqitTime.getSunriseTime(),
                muwaqqitTime.getDhuhrTime(),
                muwaqqitTime.getAsrMithlTime(),
                muwaqqitTime.getMaghribTime(),
                muwaqqitTime.getIshaTime()
            );
    }

    public DayPrayerTimesEntity(DiyanetPrayerTimeDayEntity diyanetTime)
    {
        this(
                diyanetTime.getFajrTime(),
                diyanetTime.getSunriseTime(),
                diyanetTime.getDhuhrTime(),
                diyanetTime.getAsrTime(),
                diyanetTime.getMaghribTime(),
                diyanetTime.getIshaTime()
            );
    }

    public DayPrayerTimesEntity(
            Date fajrTime,
            Date sunRiseTime,
            Date dhuhrTime,
            Date asrTime,
            Date maghribTime,
            Date ishaTime
    )
    {
        this._fajrTime = fajrTime;
        this._sunRiseTime = sunRiseTime;

        this._dhuhrTime = dhuhrTime;
        this._asrTime = asrTime;

        this._maghribTime = maghribTime;
        this._ishaTime = ishaTime;
    }

    public Date GetTimeByType(EPrayerPointInTimeType prayerTimeType)
    {
        switch (prayerTimeType)
        {
            case IshaEnd:
            case FajrBeginning:
                return this.getFajrTime();

            case FajrEnd:
                return this.getSunRiseTime();

            case DhuhrBeginning:
                return this.getDhuhrTime();

            case DhuhrEnd:
            case AsrBeginning:
                return this.getAsrTime();

            case AsrEnd:
            case MaghribBeginning:
                return this.getMaghribTime();

            case MaghribEnd:
            case IshaBeginning:
                return this.getIshaTime();

            default:
                return null;
        }
    }

    // #########################################################

    public Date getFajrTime()
    {
        return _fajrTime;
    }

    public void setFajrTime(Date fajrTime)
    {
        _fajrTime = fajrTime;
    }

    public Date getSunRiseTime()
    {
        return _sunRiseTime;
    }

    public void setSunRiseTime(Date sunRiseTime)
    {
        _sunRiseTime = sunRiseTime;
    }

    public Date getDhuhrTime()
    {
        return _dhuhrTime;
    }

    public void setDhuhrTime(Date dhuhrTime)
    {
        _dhuhrTime = dhuhrTime;
    }

    public Date getAsrTime()
    {
        return _asrTime;
    }

    public void setAsrTime(Date asrTime)
    {
        _asrTime = asrTime;
    }

    public Date getMaghribTime()
    {
        return _maghribTime;
    }

    public void setMaghribTime(Date maghribTime)
    {
        _maghribTime = maghribTime;
    }

    public Date getIshaTime()
    {
        return _ishaTime;
    }

    public void setIshaTime(Date ishaTime)
    {
        _ishaTime = ishaTime;
    }
}
