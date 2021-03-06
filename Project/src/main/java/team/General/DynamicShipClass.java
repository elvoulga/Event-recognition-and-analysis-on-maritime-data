package team.General;

import java.io.Serializable;



public class DynamicShipClass implements Comparable<DynamicShipClass>, Serializable {

	//private static transient DateTimeFormatter timeFormatter =
	//		DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.US).withZoneUTC(); //na valoume to timestamp se wra
	public DynamicShipClass() {
		this.ts = new Long(ts);
	}

	public DynamicShipClass(int mmsi, int status, int turn, double speed, double course, int heading, double lon, double lat, long ts, int gridId, boolean gapStart, boolean gapEnd, boolean lowStart, boolean lowEnd) {

		this.mmsi = mmsi;
		this.status = status;
		this.turn = turn;
		this.speed = speed;
		this.course = course;
		this.heading = heading;
		this.lon = lon;
		this.lat = lat;
		this.ts = ts;
		this.gridId = gridId;
		this.gapStart = gapStart;
		this.gapEnd = gapEnd;
		this.lowStart = lowStart;
		this.lowEnd = lowEnd;
	}

	public int mmsi;
	public int status;
	public int turn;
	public double speed;
	public double course;
	public int heading;
	public double lon;
	public double lat;
	public long ts;
	public int gridId;
	public boolean gapStart;
	public boolean gapEnd;
	public boolean lowStart;
	public boolean lowEnd;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mmsi).append(",");
		sb.append(status).append(",");
		sb.append(turn).append(",");
		sb.append(speed).append(",");
		sb.append(course).append(",");
		sb.append(heading).append(",");
		sb.append(lon).append(",");
		sb.append(lat).append(",");
		sb.append(ts).append(",");
		sb.append(gridId);

		return sb.toString();
	}

	public static DynamicShipClass fromString(String line, GeoUtils geo) {

		String[] tokens = line.split(",");
		if (tokens.length != 9) {
			throw new RuntimeException("Invalid record: " + line);
		}

		DynamicShipClass ship = new DynamicShipClass();

		try {
			ship.mmsi = tokens[0].length() > 0 ? Integer.parseInt(tokens[0]) : 0;
			ship.status = tokens[1].length() > 0 ? Integer.parseInt(tokens[1]) : 0;
			ship.turn = tokens[2].length() > 0 ? Integer.parseInt(tokens[2]) : 0;
			ship.speed = tokens[3].length() > 0 ? Double.parseDouble(tokens[3]) : 0.0f;
			ship.course = tokens[4].length() > 0 ? Double.parseDouble(tokens[4]) : 0.0f;
			ship.heading = tokens[5].length() > 0 ? Integer.parseInt(tokens[5]) : 0;
			ship.lon = tokens[6].length() > 0 ? Double.parseDouble(tokens[6]) : 0.0f;
			ship.lat = tokens[7].length() > 0 ? Double.parseDouble(tokens[7]) : 0.0f;
			ship.ts = tokens[8].length() > 0 ? (Long.parseLong(tokens[8])) : 0;
			ship.gridId = geo.mapToGridCell(ship.lon, ship.lat);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException("Invalid record: " + line, nfe);
		}

		return ship;
	}

	public static DynamicShipClass fromString(String line) {

		String[] tokens = line.split(",");
		if (tokens.length != 10) {
			throw new RuntimeException("Invalid record: " + line);
		}

		DynamicShipClass ship = new DynamicShipClass();

		try {
			ship.mmsi = tokens[0].length() > 0 ? Integer.parseInt(tokens[0]) : 0;
			ship.status = tokens[1].length() > 0 ? Integer.parseInt(tokens[1]) : 0;
			ship.turn = tokens[2].length() > 0 ? Integer.parseInt(tokens[2]) : 0;
			ship.speed = tokens[3].length() > 0 ? Double.parseDouble(tokens[3]) : 0.0f;
			ship.course = tokens[4].length() > 0 ? Double.parseDouble(tokens[4]) : 0.0f;
			ship.heading = tokens[5].length() > 0 ? Integer.parseInt(tokens[5]) : 0;
			ship.lon = tokens[6].length() > 0 ? Double.parseDouble(tokens[6]) : 0.0f;
			ship.lat = tokens[7].length() > 0 ? Double.parseDouble(tokens[7]) : 0.0f;
			ship.ts = tokens[8].length() > 0 ? (Long.parseLong(tokens[8])) : 0;
			ship.gridId = tokens[9].length() > 0 ? (Integer.parseInt(tokens[9])) : 0;
		} catch (NumberFormatException nfe) {
			throw new RuntimeException("Invalid record: " + line, nfe);
		}

		return ship;
	}

	public int compareTo(DynamicShipClass other) {
		return Long.compare(this.mmsi, other.mmsi);
	}


	public long getEventTime() {
		return this.ts;
	}

	public int getmmsi() {
		return mmsi;
	}

	public int getGridId() {
		return gridId;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getCourse() {
		return course;
	}

	public int getTurn() {
		return turn;
	}

	public int getStatus() {
		return status;
	}

	public void setgapStart(boolean gapStart) {
		this.gapStart = gapStart;
	}

	public void setgapEnd(boolean gapEnd) {
		this.gapEnd = gapEnd;
	}

	public boolean getgapStart() {
		return gapStart;
	}

	public boolean getgapEnd() {
		return gapEnd;
	}

	public long getTs(){
		return ts;
	}

	public double getSpeed() {
		return speed;
	}

	public int getHeading(){return heading;}

	public boolean getLowStart(){return lowStart;}

	public boolean getLowEnd(){return lowEnd;}

	public void setLowEnd(boolean lowEnd) {
		this.lowEnd = lowEnd;
	}

	public void setLowStart(boolean lowStart) {
		this.lowStart = lowStart;
	}
/*
	public double getEuclideanDistance(double longitude, double latitude) {
		if (this.isStart) {
			return GeoUtils.getEuclideanDistance((float) longitude, (float) latitude, this.startLon, this.startLat);
		} else {
			return GeoUtils.getEuclideanDistance((float) longitude, (float) latitude, this.endLon, this.endLat);
		}
	}
*/
}