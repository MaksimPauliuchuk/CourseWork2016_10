package com.pavlyuchuk.findpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.pavlyuchuk.findpath.model.Route;
import com.pavlyuchuk.findpath.model.Stop;

public class Main
{

	public static void main(String[] args)
	{
		Set<String> routeNames = new HashSet<>();

		Map<Integer, Stop> stops = new HashMap<>();
		Map<Integer, Route> routes = new HashMap<>();

		try (Scanner sc = new Scanner(new File("rasp.csv"));)
		{
			String cLine;
			String[] splitString;
			int routeId = 1;

			String routeName;
			String stopName;
			Integer stopId;

			while (sc.hasNextLine())
			{
				cLine = sc.nextLine();
				splitString = cLine.split(";");

				routeName = splitString[2];
				stopName = splitString[3];
				stopId = Integer.parseInt(splitString[4]);

				if (!routeNames.contains(routeName))
				{
					routes.put(routeId, new Route(routeId, routeName));
					routeNames.add(routeName);
					routeId++;
				}

				stops.put(stopId, new Stop(stopId, stopName));
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		for (Stop stop : stops.values())
		{
			System.out.println(stop.getId() + " " + stop.getName());
		}

		for (Route route : routes.values())
		{
			System.out.println(route.getId() + " " + route.getName());
		}
	}

}
