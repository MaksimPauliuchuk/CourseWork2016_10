package com.pavlyuchuk.findpath.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Trip
{

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Trip_time")
	@Column(name = "time")
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
