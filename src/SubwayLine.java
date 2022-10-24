import java.util.LinkedList;

public class SubwayLine {
	private String lineName = "";
	private LinkedList<SubwayStation> stationsOnLine = new LinkedList<SubwayStation>();
	private SubwayStation upLineLast; // iterator 가 나을려나?
	private SubwayStation downLineLast;

	public SubwayLine(String lineName) {
		if (lineName.length() > 2)
			this.lineName = lineName;
		else
			System.out.println("[ERROR] 노선 이름은 2글자 이상입니다.");
	}

	public String getLineName() {
		return lineName;
	}

	public void setStationsOnLine(SubwayStation station, int idx) {
		stationsOnLine.add(idx, station);
		upLineLast = stationsOnLine.getFirst();
		downLineLast = stationsOnLine.getLast();
	}

	public void deleteStationsOnLine(SubwayStation station) {
		int idx = stationsOnLine.indexOf(station);
		stationsOnLine.remove(idx);
		upLineLast = stationsOnLine.getFirst();
		downLineLast = stationsOnLine.getLast();
	}

	public void setUpLineLast(SubwayStation upLineLast) {
		this.upLineLast = upLineLast;
		stationsOnLine.addFirst(upLineLast);
	}

	public void setDownLineLast(SubwayStation downLineLast) {
		this.downLineLast = downLineLast;
		stationsOnLine.addLast(downLineLast);
	}

	public void showStationsOnLine() {
		for (SubwayStation station : stationsOnLine) {
			System.out.println("[INFO] " + station.getStationName());
		}
	}

	public boolean findStationOnLine(String station) {
		for (SubwayStation s : stationsOnLine) {
			if (s.getStationName().equals(station)) {
				return true;
			}
		}
		return false;
	}
}
