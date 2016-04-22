package com.pavlyuchuk.findpath.model;

public class Bus
{
    private int id;
    private String number;
    private Route route = new Route();
    private Direction direction;
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
