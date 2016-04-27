package com.pavlyuchuk.findpath.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Bus
{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    
    private String number;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route = new Route();
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direction_id")
    private Direction direction;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule = new Schedule();
    
    public Bus()
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

    public final String getNumber()
    {
        return number;
    }

    public final void setNumber(String number)
    {
        this.number = number;
    }

    public final Route getRoute()
    {
        return route;
    }

    public final void setRoute(Route route)
    {
        this.route = route;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public final Schedule getSchedule()
    {
        return schedule;
    }

    public final void setSchedule(Schedule schedule)
    {
        this.schedule = schedule;
    }


}
