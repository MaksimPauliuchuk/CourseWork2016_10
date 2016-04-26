package com.pavlyuchuk.findpath.bean;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavlyuchuk.findpath.model.Schedule;
import com.pavlyuchuk.findpath.model.Stop;
import com.pavlyuchuk.findpath.model.Trip;

@Service("generateDB")
@ManagedBean(name = "generateDB")
@RequestScoped
public class GenerateDBBean
{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public String test()
    {
        Trip trip = new Trip();
        Trip trip2 = new Trip();

        for (int i = 0; i < 10; i++)
        {
            trip.getTrip().add(Calendar.getInstance());
            trip2.getTrip().add(Calendar.getInstance());
        }

        getSessionFactory().getCurrentSession().save(trip);
        getSessionFactory().getCurrentSession().save(trip2);

        Stop stop = new Stop();
        stop.setName("dasasdsa");

        getSessionFactory().getCurrentSession().save(stop);

        Schedule schedule = new Schedule();

        schedule.getMonday().add(trip);
        //schedule.getFriday().add(new Trip());
        //schedule.getSaturday().add(new Trip());
        //schedule.getSunday().add(new Trip());
        //schedule.getThursday().add(new Trip());
        schedule.getTuesday().add(trip2);
        //schedule.getWednesday().add(new Trip());

        getSessionFactory().getCurrentSession().save(schedule);

        System.out.println("Test");
        return "OK";
    }
    
    @Transactional
    public String test1()
    {
        Schedule schedule = (Schedule) getSessionFactory().getCurrentSession().get(Schedule.class, 1);

        System.out.println(schedule.getId());
        System.out.println(schedule.getMonday().get(0).getTrip().get(0));
        
        
        System.out.println("Test");
        return "OK";
    }
}
