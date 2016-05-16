package com.pavlyuchuk.findpath.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Route
{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Stop> stops = new ArrayList<Stop>();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bus_id")
	private Bus bus;

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

	public void addStop(Stop stop)
	{
		stops.add(stop);
		stop.getRoutes().add(this);
	}

	public void removeStop(Stop stop)
	{
		stops.remove(stop);
		stop.getRoutes().remove(this);
	}

	public final Bus getBus()
	{
		return bus;
	}

	public final void setBus(Bus bus)
	{
		this.bus = bus;
	}
}
