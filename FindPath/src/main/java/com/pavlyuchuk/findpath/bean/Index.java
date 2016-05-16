package com.pavlyuchuk.findpath.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavlyuchuk.findpath.model.Bus;
import com.pavlyuchuk.findpath.model.Route;
import com.pavlyuchuk.findpath.model.Schedule;
import com.pavlyuchuk.findpath.model.Stop;
import com.pavlyuchuk.findpath.model.Trip;

@Service("app")
@ManagedBean(name = "app")
@ApplicationScoped
public class Index
{
	@Autowired
	private SessionFactory sessionFactory;

	private MapModel simpleModel;

	private MapModel busModel;

	private Marker marker;

	private List<Route> routes;

	private List<Stop> stops;

	private List<Bus> buses;

	private Bus selectedBus;

	private List<String> nameStops = new ArrayList<>();

	private List<List<List<Stop>>> routeGraph;

	private String stopFrom;

	private String stopTo;

	private String findStop;

	private List<StopTemp> findedStops;

	private List<Route> withoutTransfer;

	private List<List<Route>> withOneTransfer;

	// -----------------------------------------

	public void test()
	{

	}

	public void findPath()
	{
		List<Stop> from = new ArrayList<>();
		List<Stop> to = new ArrayList<>();

		for (Stop stop : stops)
		{
			if (stop.getName().equals(stopFrom))
			{
				from.add(stop);
			}
			if (stop.getName().equals(stopTo))
			{
				to.add(stop);
			}
		}

		withoutTransfer = new ArrayList<>();
		withOneTransfer = new ArrayList<>();

		for (Stop f : from)
		{
			for (Stop t : to)
			{
				withoutTransfer.addAll(fidnPathWithoutTransfer(f, t));
				withOneTransfer.addAll(findPathWithOneTransfer(f, t));
			}
		}
	}

	public void findeStop()
	{
		Session session = getSessionFactory().openSession();

		Calendar now = Calendar.getInstance();

		List<Stop> from = new ArrayList<>();

		List<StopTemp> stopInner = new ArrayList<>();

		for (Stop stop : stops)
		{
			if (stop.getName().equals(findStop))
			{
				from.add(stop);
			}
		}

		for (Stop stop : from)
		{
			StopTemp stopTemp = new StopTemp();
			stopTemp.setName(stop.getName());

			for (Route route : stop.getRoutes())
			{
				BusTemp busTemp = new BusTemp();
				busTemp.setName(route.getBus().getNumber());
				busTemp.setDirection(route.getBus().getDirection().getName());

				int index = -1;

				for (int i = 0; i < route.getStops().size(); i++)
				{
					if (route.getStops().get(i).getId() == stop.getId())
					{
						index = i;
						break;
					}
				}

				Schedule schedule = (Schedule) session
						.createQuery("select schedule from Schedule schedule where schedule.id= :id")
						.setInteger("id", route.getBus().getSchedule().getId()).uniqueResult();

				for (Trip trip : schedule.getMonday())
				{
					if (index < trip.getTrip().size())
					{
						Calendar cal = trip.getTrip().get(index);
						if (cal.get(Calendar.HOUR_OF_DAY) > now.get(Calendar.HOUR_OF_DAY)
								&& cal.get(Calendar.MINUTE) > now.get(Calendar.MINUTE))
						{
							busTemp.setTime(cal);
							break;
						}
					}
				}
				stopTemp.getBuses().add(busTemp);
			}
			stopInner.add(stopTemp);
		}
		session.close();

		findedStops = stopInner;
	}

	@SuppressWarnings("unchecked")
	private List<Route> fidnPathWithoutTransfer(Stop from, Stop to)
	{
		List<Route> findedRoutes = new ArrayList<>();

		List<Route> tempRoute = new ArrayList<>();
		tempRoute.addAll(intersect(from.getRoutes(), to.getRoutes()));

		for (Route route : tempRoute)
		{
			if (route.getStops().indexOf(from) < route.getStops().indexOf(to))
			{
				findedRoutes.add(route);
			}
		}
		return findedRoutes;
	}

	private List<List<Route>> findPathWithOneTransfer(Stop from, Stop to)
	{
		List<List<Route>> findedRoutes = new ArrayList<List<Route>>();

		for (Route routeF : from.getRoutes())
		{
			List<List<Stop>> line = routeGraph.get(routeF.getId() - 1);
			for (Route routeT : to.getRoutes())
			{
				List<Stop> intersectoin = line.get(routeT.getId() - 1);
				if (intersectoin.size() > 0)
				{
					boolean between = false;
					List<Stop> realInter = new ArrayList<>();
					for (Stop stop : intersectoin)
					{
						if ((routeF.getStops().indexOf(from) < routeF.getStops().indexOf(stop)
								&& routeT.getStops().indexOf(to) > routeT.getStops().indexOf(stop))
								&& (routeF.getStops().indexOf(stop) != (routeF.getStops().size() - 1)
										&& routeT.getStops().indexOf(stop) != 0))
						{
							between = true;
							realInter.add(stop);
						}
					}
					if (between)
					{
						List<Route> tempRoute = new ArrayList<>();
						tempRoute.add(routeF);
						tempRoute.add(routeT);
						findedRoutes.add(tempRoute);
						/*
						 * System.out.println( routeF.getBus().getNumber() + " " +
						 * routeF.getBus().getDirection().getName()); for (Stop stop : routeF.getStops()) {
						 * System.out.print(stop.getId() + " "); } System.out.println(); System.out.println(
						 * routeT.getBus().getNumber() + " " + routeT.getBus().getDirection().getName()); for (Stop stop
						 * : routeT.getStops()) { System.out.print(stop.getId() + " "); } System.out.println(); for
						 * (Stop stop : realInter) { System.out.print(stop.getId() + " "); } System.out.println();
						 * System.out.println();
						 */
					}
				}
			}
		}
		return findedRoutes;
	}

	public final List<String> completeStop(final String aQuery)
	{
		List<String> filtredName = new ArrayList<String>();

		for (String name : nameStops)
		{
			if (filtredName.size() >= 5)
			{
				break;
			}
			if (name.toLowerCase().startsWith(aQuery.toLowerCase()))
			{
				filtredName.add(name);
			}
		}

		return filtredName;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void initialization()
	{

		Session session = getSessionFactory().openSession();

		stops = session.createCriteria(Stop.class).list();
		Set<Stop> stopSet = new HashSet<>();
		stopSet.addAll(stops);
		stops.clear();
		stops.addAll(stopSet);
		stops.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);

		Set<String> nameStopSet = new HashSet<>();

		for (Stop stop : stops)
		{
			nameStopSet.add(stop.getName());
		}
		nameStops.addAll(nameStopSet);
		nameStops.sort((o1, o2) -> o1.compareTo(o2));

		routes = session.createCriteria(Route.class).list();
		Set<Route> routesSet = new HashSet<>();
		routesSet.addAll(routes);
		routes.clear();
		routes.addAll(routesSet);
		routes.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);

		routeGraph = new ArrayList<List<List<Stop>>>();

		for (int i = 0; i < routes.size(); i++)
		{
			routeGraph.add(new ArrayList<List<Stop>>());
			for (int j = 0; j < routes.size(); j++)
			{
				routeGraph.get(i).add(new ArrayList<Stop>());
				if (i != j)
				{
					routeGraph.get(i).get(j).addAll(intersect(routes.get(i).getStops(), routes.get(j).getStops()));
				}
			}
		}

		buses = session.createCriteria(Bus.class).list();
		Set<Bus> busSet = new HashSet<>();
		busSet.addAll(buses);
		buses.clear();
		buses.addAll(busSet);
		buses.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);

		simpleModel = new DefaultMapModel();

		for (Stop stop : stops)
		{
			simpleModel.addOverlay(new Marker(new LatLng(stop.getLat(), stop.getLen()), stop.getName(), stop));
		}

		session.close();
	}

	public void onMarkerSelect(OverlaySelectEvent event)
	{
		Session session = getSessionFactory().openSession();
		marker = (Marker) event.getOverlay();
		Stop stop = (Stop) marker.getData();

		String s = stop.getName() + " : ";

		Calendar now = Calendar.getInstance();

		for (Route route : stop.getRoutes())
		{
			s += route.getBus().getNumber() + " - ";
			int index = -1;

			for (int i = 0; i < route.getStops().size(); i++)
			{
				if (route.getStops().get(i).getId() == stop.getId())
				{
					index = i;
					break;
				}
			}

			Schedule schedule =
					(Schedule) session.createQuery("select schedule from Schedule schedule where schedule.id= :id")
							.setInteger("id", route.getBus().getSchedule().getId()).uniqueResult();

			for (Trip trip : schedule.getMonday())
			{
				if (index < trip.getTrip().size())
				{
					Calendar cal = trip.getTrip().get(index);
					if (cal.get(Calendar.HOUR_OF_DAY) > now.get(Calendar.HOUR_OF_DAY)
							&& cal.get(Calendar.MINUTE) > now.get(Calendar.MINUTE))
					{
						s += (cal.get(Calendar.HOUR_OF_DAY) - 1) + ":" + cal.get(Calendar.MINUTE) + ", ";
						break;
					}
				}
			}

		}
		session.close();

		s = s.substring(0, s.length() - 2);

		marker.setTitle(s);
	}

	public void onMarkerSelectBus(OverlaySelectEvent event)
	{
		Session session = getSessionFactory().openSession();
		marker = (Marker) event.getOverlay();
		Stop stop = (Stop) marker.getData();

		String s = stop.getName() + " : ";

		Calendar now = Calendar.getInstance();
		Route route = selectedBus.getRoute();
		s += route.getBus().getNumber() + " - ";
		int index = -1;

		for (int i = 0; i < route.getStops().size(); i++)
		{
			if (route.getStops().get(i).getId() == stop.getId())
			{
				index = i;
				break;
			}
		}

		Schedule schedule =
				(Schedule) session.createQuery("select schedule from Schedule schedule where schedule.id= :id")
						.setInteger("id", route.getBus().getSchedule().getId()).uniqueResult();

		for (Trip trip : schedule.getMonday())
		{
			if (index < trip.getTrip().size())
			{
				Calendar cal = trip.getTrip().get(index);
				if (cal.get(Calendar.HOUR_OF_DAY) > now.get(Calendar.HOUR_OF_DAY)
						&& cal.get(Calendar.MINUTE) > now.get(Calendar.MINUTE))
				{
					s += (cal.get(Calendar.HOUR_OF_DAY) - 1) + ":" + cal.get(Calendar.MINUTE) + ", ";
					break;
				}
			}
		}

		session.close();

		s = s.substring(0, s.length() - 2);

		marker.setTitle(s);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private List intersect(final List list1, final List list2)
	{
		List temp = new ArrayList();
		temp.addAll(list1);
		temp.retainAll(list2);
		return temp;
	}

	// ------------------------------------------

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public final MapModel getSimpleModel()
	{
		return simpleModel;
	}

	public final void setSimpleModel(MapModel simpleModel)
	{
		this.simpleModel = simpleModel;
	}

	public final MapModel getBusModel()
	{
		return busModel;
	}

	public final void setBusModel(MapModel busModel)
	{
		this.busModel = busModel;
	}

	public final Marker getMarker()
	{
		return marker;
	}

	public final void setMarker(Marker marker)
	{
		this.marker = marker;
	}

	public final List<Bus> getBuses()
	{
		return buses;
	}

	public final void setBuses(List<Bus> buses)
	{
		this.buses = buses;
	}

	public final Bus getSelectedBus()
	{
		return selectedBus;
	}

	public final void setSelectedBus(Bus selectedBus)
	{
		busModel = new DefaultMapModel();

		for (Stop stop : selectedBus.getRoute().getStops())
		{
			busModel.addOverlay(new Marker(new LatLng(stop.getLat(), stop.getLen()), stop.getName(), stop));
		}

		this.selectedBus = selectedBus;
	}

	public final String getStopFrom()
	{
		return stopFrom;
	}

	public final void setStopFrom(String stopFrom)
	{
		this.stopFrom = stopFrom;
	}

	public final String getStopTo()
	{
		return stopTo;
	}

	public final void setStopTo(String stopTo)
	{
		this.stopTo = stopTo;
	}

	public final String getFindStop()
	{
		return findStop;
	}

	public final void setFindStop(String findStop)
	{
		this.findStop = findStop;
	}

	public final List<StopTemp> getFindedStops()
	{
		return findedStops;
	}

	public final void setFindedStops(List<StopTemp> findedStops)
	{
		this.findedStops = findedStops;
	}

	public final List<Route> getWithoutTransfer()
	{
		return withoutTransfer;
	}

	public final void setWithoutTransfer(List<Route> withoutTransfer)
	{
		this.withoutTransfer = withoutTransfer;
	}

	public final List<List<Route>> getWithOneTransfer()
	{
		return withOneTransfer;
	}

	public final void setWithOneTransfer(List<List<Route>> withOneTransfer)
	{
		this.withOneTransfer = withOneTransfer;
	}

	public class StopTemp
	{
		String name;

		List<BusTemp> buses = new ArrayList<>();

		public final String getName()
		{
			return name;
		}

		public final void setName(String name)
		{
			this.name = name;
		}

		public final List<BusTemp> getBuses()
		{
			return buses;
		}

		public final void setBuses(List<BusTemp> buses)
		{
			this.buses = buses;
		}
	}

	public class BusTemp
	{
		String name;

		String direction;

		Calendar time;

		public final String getName()
		{
			return name;
		}

		public final void setName(String name)
		{
			this.name = name;
		}

		public final String getDirection()
		{
			return direction;
		}

		public final void setDirection(String direction)
		{
			this.direction = direction;
		}

		public final Calendar getTime()
		{
			return time;
		}

		public final void setTime(Calendar time)
		{
			this.time = time;
		}
	}
}
