package com.pavlyuchuk.findpath.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Trip
{
    private int id;
    private List<Calendar> trip = new ArrayList<>();
    
    public Trip()
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

    public final List<Calendar> getTrip()
    {
        return trip;
    }

    public final void setTrip(List<Calendar> trip)
    {
        this.trip = trip;
    }
    
    
}
