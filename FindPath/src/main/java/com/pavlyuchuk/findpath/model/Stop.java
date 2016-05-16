package com.pavlyuchuk.findpath.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Stop
{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	private String name;

	private double lat;

	private double len;

	@ManyToMany(mappedBy = "stops", fetch = FetchType.EAGER)
	private List<Route> routes = new ArrayList<>();

	public Stop()
	{

	}

	public Stop(int id, String name, double lat, double len)
	{
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.len = len;
	}

	public final int getId()
	{
		return id;
	}

	public final void setId(int id)
	{
		this.id = id;
	}

	public final String getName()
	{
		return name;
	}

	public final void setName(String name)
	{
		this.name = name;
	}

	public final double getLat()
	{
		return lat;
	}

	public final void setLat(double lat)
	{
		this.lat = lat;
	}

	public final double getLen()
	{
		return len;
	}

	public final void setLen(double len)
	{
		this.len = len;
	}

	public final List<Route> getRoutes()
	{
		return routes;
	}

	public final void setRoutes(List<Route> routes)
	{
		this.routes = routes;
	}

}
