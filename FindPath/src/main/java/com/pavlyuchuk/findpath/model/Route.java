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
public class Route
{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Stop> stops = new ArrayList<Stop>();

    public Route()
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

    public final List<Stop> getStops()
    {
        return stops;
    }

    public final void setStops(List<Stop> stops)
    {
        this.stops = stops;
    }

}
