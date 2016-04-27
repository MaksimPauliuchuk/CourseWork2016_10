package com.pavlyuchuk.findpath.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Schedule
{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> monday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> tuesday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> wednesday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> thursday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> friday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> saturday = new ArrayList<Trip>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Trip> sunday = new ArrayList<Trip>();

    public Schedule()
    {

    }

    public final int getId()
    {
        return id;
    }

    public final void setId(int id)
    {
        this.id = id;
    }

    public final List<Trip> getMonday()
    {
        return monday;
    }

    public final void setMonday(List<Trip> monday)
    {
        this.monday = monday;
    }

    public final List<Trip> getTuesday()
    {
        return tuesday;
    }

    public final void setTuesday(List<Trip> tuesday)
    {
        this.tuesday = tuesday;
    }

    public final List<Trip> getWednesday()
    {
        return wednesday;
    }

    public final void setWednesday(List<Trip> wednesday)
    {
        this.wednesday = wednesday;
    }

    public final List<Trip> getThursday()
    {
        return thursday;
    }

    public final void setThursday(List<Trip> thursday)
    {
        this.thursday = thursday;
    }

    public final List<Trip> getFriday()
    {
        return friday;
    }

    public final void setFriday(List<Trip> friday)
    {
        this.friday = friday;
    }

    public final List<Trip> getSaturday()
    {
        return saturday;
    }

    public final void setSaturday(List<Trip> saturday)
    {
        this.saturday = saturday;
    }

    public final List<Trip> getSunday()
    {
        return sunday;
    }

    public final void setSunday(List<Trip> sunday)
    {
        this.sunday = sunday;
    }

}
