package com.pavlyuchuk.findpath.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavlyuchuk.findpath.model.Bus;
import com.pavlyuchuk.findpath.model.Direction;
import com.pavlyuchuk.findpath.model.Route;
import com.pavlyuchuk.findpath.model.Stop;
import com.pavlyuchuk.findpath.model.Trip;

@Service("generateDB")
@ManagedBean(name = "generateDB")
@ViewScoped
public class GenerateDBBean
{

	@Autowired
	private SessionFactory sessionFactory;

	private List<Bus> buses;

	@SuppressWarnings("unchecked")
	@Transactional
	public void test()
	{
		boolean printInFile = false;

		buses = new ArrayList<>();
		Set<Bus> busesSet = new HashSet<>();
		busesSet.addAll(getSessionFactory().getCurrentSession().createCriteria(Bus.class).list());
		buses.addAll(busesSet);
		buses.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);

		for (Bus bus2 : buses)
		{
			if (printInFile)
			{
				System.out.println(bus2.getNumber() + " " + bus2.getDirection().getName());
			}
			for (Stop stop : bus2.getRoute().getStops())
			{
				if (printInFile)
				{
					System.out.println("\t" + stop.getId() + "\t" + stop.getName());
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tMonday");
			}
			for (Trip trip : bus2.getSchedule().getMonday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}
				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tTuesday");
			}
			for (Trip trip : bus2.getSchedule().getTuesday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}

				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tWednesday");
			}
			for (Trip trip : bus2.getSchedule().getWednesday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}

				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tThursday");
			}
			for (Trip trip : bus2.getSchedule().getThursday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}
				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tFriday");
			}
			for (Trip trip : bus2.getSchedule().getFriday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}
				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tSaturday");
			}
			for (Trip trip : bus2.getSchedule().getSaturday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}
				if (printInFile)
				{
					System.out.println();
				}
			}

			if (printInFile)
			{
				System.out.println();
				System.out.println("\tSunday");
			}
			for (Trip trip : bus2.getSchedule().getSunday())
			{
				if (printInFile)
				{
					System.out.print("\t\t\t");
				}
				for (Calendar calendar : trip.getTrip())
				{
					if (calendar != null)
					{
						if (printInFile)
						{
							System.out.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
									calendar.get(Calendar.MINUTE));
						}
					}
					else
					{
						if (printInFile)
						{
							System.out.printf("--:-- ");
						}
					}
				}

				if (printInFile)
				{
					System.out.println();
				}
			}
			if (printInFile)
			{
				System.out.println();
				System.out.println();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void test1()
	{
		List<Stop> stops;

		stops = getSessionFactory().getCurrentSession().createCriteria(Stop.class).list();

		Set<Stop> stopSet = new HashSet<>();
		stopSet.addAll(stops);
		stops.clear();
		stops.addAll(stopSet);
		stops.sort((o1, o2) -> o1.getId() > o2.getId() ? 1 : -1);

		for (Stop stop : stops)
		{
			System.out.println(stop.getId() + " " + stop.getName());
			for (Route route : stop.getRoutes())
			{
				System.out.println("\t" + route.getId() + " " + route.getBus().getNumber());
			}
		}

	}

	@Transactional
	public void generate()
	{
		String path = "c:\\Program Files\\eclipse\\rasp.csv";
		boolean printInFile = true;
		Set<String> routeNames = new HashSet<>();

		Map<Integer, Stop> stops = new HashMap<>();
		Map<Integer, Direction> directions = new HashMap<>();

		List<Bus> buses = new ArrayList<>();

		try (PrintWriter pw = new PrintWriter(new File("convertedRasp.txt"));
				Scanner sc = new Scanner(new File(path)))
		{
			String cLine;
			String[] splitString;
			Integer routeId = 1;
			int countTravels;
			int countI = 0;

			String busName;
			String directionsName;
			String stopName;
			double lat;
			double len;
			String day;
			Direction direction = new Direction();
			Integer stopId;

			while (sc.hasNextLine())
			{
				cLine = sc.nextLine();
				splitString = cLine.split(";");

				countTravels = (splitString.length - 1) - 5;

				for (int i = 0; i < splitString.length; i++)
				{
					System.out.println(splitString[i]);
				}

				System.out.println(countTravels);

				ArrayList<Calendar[]> travelMatrix = new ArrayList<>();

				busName = splitString[0];
				day = splitString[1];
				directionsName = splitString[2];
				stopName = splitString[3];
				stopId = Integer.parseInt(splitString[4]);
				lat = Double.parseDouble(splitString[5].split(",")[0]);
				len = Double.parseDouble(splitString[5].split(",")[1]);
				boolean busExist = false;

				if (!routeNames.contains(directionsName))
				{
					direction = new Direction(routeId, directionsName);
					// getSessionFactory().getCurrentSession().save(direction);
					directions.put(routeId, direction);
					routeNames.add(directionsName);
					routeId++;
				}
				else
				{
					for (Direction directionIterator : directions.values())
					{
						if (directionsName.equals(directionIterator.getName()))
						{
							direction = directionIterator;
						}
					}
				}

				if (stops.get(stopId) == null)
				{
					Stop stop = new Stop(stopId, stopName, lat, len);
					// getSessionFactory().getCurrentSession().save(stop);
					stops.put(stopId, stop);
				}

				Bus bus = null;

				for (Bus bus1 : buses)
				{
					if (busName.equals(bus1.getNumber()) && directionsName.equals(bus1.getDirection().getName()))
					{
						bus = bus1;
						busExist = true;
						break;
					}
				}

				if (!busExist)
				{
					bus = new Bus();
					bus.setNumber(busName);
					bus.setDirection(direction);
					bus.getRoute().addStop(stops.get(stopId));
					bus.getRoute().setBus(bus);
				}

				Calendar[] travels = new Calendar[countTravels];

				for (int i = 6; i < splitString.length; i++)
				{
					if (splitString[i].equals("null"))
					{
						travels[i - 6] = null;
					}
					else
					{
						String[] splitTime = splitString[i].split(":");
						travels[i - 6] = Calendar.getInstance();
						travels[i - 6].set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]));
						travels[i - 6].set(Calendar.MINUTE, Integer.parseInt(splitTime[1]));
					}
				}

				travelMatrix.add(travels);

				while ((cLine = sc.nextLine()).length() != 0)
				{
					countI++;
					splitString = cLine.split(";");

					if (!busExist)
					{
						stopName = splitString[3];
						stopId = Integer.parseInt(splitString[4]);
						lat = Double.parseDouble(splitString[5].split(",")[0]);
						len = Double.parseDouble(splitString[5].split(",")[1]);

						if (stops.get(stopId) == null)
						{
							stops.put(stopId, new Stop(stopId, stopName, lat, len));
						}

						bus.getRoute().addStop(stops.get(stopId));
					}
					travels = new Calendar[countTravels];

					for (int i = 6; i < splitString.length; i++)
					{
						if (splitString[i].equals("null"))
						{
							travels[i - 6] = null;
						}
						else
						{
							String[] splitTime = splitString[i].split(":");
							travels[i - 6] = Calendar.getInstance();
							travels[i - 6].set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]));
							travels[i - 6].set(Calendar.MINUTE, Integer.parseInt(splitTime[1]));
						}
					}
					travelMatrix.add(travels);
				}
				List<Trip> trips = new ArrayList<Trip>();
				for (int j = 0; j < countTravels; j++)
				{
					Trip trip = new Trip();
					for (int i = 0; i < travelMatrix.size(); i++)
					{
						trip.getTrip().add(travelMatrix.get(i)[j]);
					}
					trips.add(trip);
				}

				if (day.equals("Р"))
				{
					bus.getSchedule().setMonday(trips);
					bus.getSchedule().setTuesday(trips);
					bus.getSchedule().setWednesday(trips);
					bus.getSchedule().setThursday(trips);
					bus.getSchedule().setFriday(trips);
				}

				if (day.equals("В"))
				{
					bus.getSchedule().setSunday(trips);
					bus.getSchedule().setSaturday(trips);
				}

				if (day.equals("Р,В"))
				{
					bus.getSchedule().setMonday(trips);
					bus.getSchedule().setTuesday(trips);
					bus.getSchedule().setWednesday(trips);
					bus.getSchedule().setThursday(trips);
					bus.getSchedule().setFriday(trips);
					bus.getSchedule().setSunday(trips);
					bus.getSchedule().setSaturday(trips);
				}

				if (!busExist)
				{
					buses.add(bus);
				}
			}

			for (Bus bus2 : buses)
			{
				if (printInFile)
				{
					pw.println(bus2.getNumber() + " " + bus2.getDirection().getName());
				}
				getSessionFactory().getCurrentSession().save(bus2.getDirection());
				for (Stop stop : bus2.getRoute().getStops())
				{
					if (printInFile)
					{
						pw.println("\t" + stop.getId() + "\t" + stop.getName());
					}
					getSessionFactory().getCurrentSession().save(stop);
				}
				getSessionFactory().getCurrentSession().save(bus2.getRoute());

				if (printInFile)
				{
					pw.println();
					pw.println("\tMonday");
				}
				for (Trip trip : bus2.getSchedule().getMonday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);
					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tTuesday");
				}
				for (Trip trip : bus2.getSchedule().getTuesday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);

					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tWednesday");
				}
				for (Trip trip : bus2.getSchedule().getWednesday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);

					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tThursday");
				}
				for (Trip trip : bus2.getSchedule().getThursday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);
					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tFriday");
				}
				for (Trip trip : bus2.getSchedule().getFriday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);
					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tSaturday");
				}
				for (Trip trip : bus2.getSchedule().getSaturday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);
					if (printInFile)
					{
						pw.println();
					}
				}

				if (printInFile)
				{
					pw.println();
					pw.println("\tSunday");
				}
				for (Trip trip : bus2.getSchedule().getSunday())
				{
					if (printInFile)
					{
						pw.print("\t\t\t");
					}
					for (Calendar calendar : trip.getTrip())
					{
						if (calendar != null)
						{
							if (printInFile)
							{
								pw.printf("%02d:%02d ", calendar.get(Calendar.HOUR_OF_DAY),
										calendar.get(Calendar.MINUTE));
							}
						}
						else
						{
							if (printInFile)
							{
								pw.printf("--:-- ");
							}
						}
					}
					getSessionFactory().getCurrentSession().save(trip);

					if (printInFile)
					{
						pw.println();
					}
				}
				if (printInFile)
				{
					pw.println();
					pw.println();
				}

				getSessionFactory().getCurrentSession().save(bus2.getSchedule());
				getSessionFactory().getCurrentSession().save(bus2);
			}

			System.out.println(countI);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public List<Bus> getBuses()
	{
		return buses;
	}

	public void setBuses(List<Bus> buses)
	{
		this.buses = buses;
	}
}
