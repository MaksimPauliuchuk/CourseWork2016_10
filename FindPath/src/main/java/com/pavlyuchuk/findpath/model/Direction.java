package com.pavlyuchuk.findpath.model;

public class Direction
{
    private int id;
    private String name;

    public Direction()
    {

    }

    public Direction(int id, String name)
    {
        this.id = id;
        this.name = name;
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

}
