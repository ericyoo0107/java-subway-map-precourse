import java.util.LinkedList;

public class Subway {
	private static Subway subway = new Subway();
	private LinkedList<SubwayStation> stations = new LinkedList<SubwayStation>();
	private LinkedList<SubwayLine> lines = new LinkedList<SubwayLine>();

	private Subway() {
	}

	public static Subway getInstance() {
		if (subway == null) {
			subway = new Subway();

		}
		return subway;
	}

	public static void makeDefault() {
		SubwayLine line2 = new SubwayLine("2호선");
		SubwayLine line3 = new SubwayLine("3호선");
		SubwayLine lineNew = new SubwayLine("신분당선");

		SubwayStation s1 = new SubwayStation("교대역");
		SubwayStation s2 = new SubwayStation("강남역");
		SubwayStation s3 = new SubwayStation("역삼역");
		SubwayStation s4 = new SubwayStation("남부터미널역");
		SubwayStation s5 = new SubwayStation("양재역");
		SubwayStation s6 = new SubwayStation("양재시민의숲역");
		SubwayStation s7 = new SubwayStation("매봉역");

		if (subway != null) {
			subway.addSubwayLine(line2);
			subway.addSubwayLine(line3);
			subway.addSubwayLine(lineNew);

			subway.addSubwayStation(s1);
			subway.addSubwayStation(s2);
			subway.addSubwayStation(s3);
			subway.addSubwayStation(s4);
			subway.addSubwayStation(s5);
			subway.addSubwayStation(s6);
			subway.addSubwayStation(s7);

			subway.setStationOnLine(line2, s1, 0);
			subway.setStationOnLine(line2, s2, 1);
			subway.setStationOnLine(line2, s3, 2);

			subway.setStationOnLine(line3, s1, 0);
			subway.setStationOnLine(line3, s4, 1);
			subway.setStationOnLine(line3, s5, 2);
			subway.setStationOnLine(line3, s7, 2);

			subway.setStationOnLine(lineNew, s2, 0);
			subway.setStationOnLine(lineNew, s5, 1);
			subway.setStationOnLine(lineNew, s6, 2);
		}
	}

	// 역이름을 입력받아서 역 list의 index를 반환
	public int findStationIdx(String subwayStation) {
		int idx = stations.size();
		for (int i = idx - 1; i >= 0; i--) {
			if (stations.get(i).getStationName().equals(subwayStation)) {
				return i;
			}
		}
		return -1; //station이 없는경우
	}

	//지하철 역 추가
	public void addSubwayStation(SubwayStation subwaystation) {
		if (stations != null) {
			if (findStationIdx(subwaystation.getStationName()) != -1) {
				System.out.println("[ERROR] 이미 등록된 역 이름입니다.");
				return;
			}
			stations.add(subwaystation);
			System.out.println("[INFO] 지하철 역이 등록되었습니다.");
		}
	}

	//지하철 역 삭제
	public void deleteSubwayStation(String subwayStation) {
		if (stations != null) {
			int idx = findStationIdx(subwayStation);
			if (idx == -1) {
				System.out.println("[ERROR] 존재하지 않는 역 이름입니다.");
				return;
			}
			stations.remove(idx);
		}
	}

	public void showSubwayStations() {
		if (stations != null) {
			for (SubwayStation station : stations) {
				System.out.println("[INFO] " + station.getStationName());
			}
		}
	}

	public int findLineIdx(String subwayLine) {
		int idx = lines.size();
		for (int i = idx - 1; i >= 0; i--) {
			if (lines.get(i).getLineName().equals(subwayLine)) {
				return i;
			}
		}
		return -1; //station이 없는경우
	}

	//지하철 노선 등록
	public boolean addSubwayLine(SubwayLine subwayLine) {
		if (lines != null) {
			if (findLineIdx(subwayLine.getLineName()) != -1) {
				System.out.println("[ERROR] 이미 등록된 호선 이름입니다.");
				return false;
			}
		}
		lines.add(subwayLine);
		System.out.println("[INFO] 호선이 등록되었습니다.");
		return true;
	}

	//지하철 노선 삭제
	public void deleteSubwayLine(String subwayLine) {
		if (lines != null) {
			int idx = findLineIdx(subwayLine);
			if (idx != -1) {
				lines.remove(idx);
				return;
			}
		}
		System.out.println("[ERROR] 존재하지 않는 호선 이름입니다.");
	}

	//지하철 노선 등록할때 상행선도 같이 추가
	public void addUpLine(String station, SubwayLine line) {
		int stationIdx = findStationIdx(station);
		if (stationIdx != -1) {
			//입력 받은 역이 이미 있는 역일때
			line.setUpLineLast(stations.get(stationIdx));
			stations.get(stationIdx).addStationLine(line.getLineName());
			return;
		}
		SubwayStation stationNew = new SubwayStation(station);
		line.setUpLineLast(stationNew);
		stations.add(stationNew);
		stationNew.addStationLine(line.getLineName());
	}

	//지하철 노선 등록할때 하행선도 같이 추가
	public void addDownLine(String station, SubwayLine line) {
		int stationIdx = findStationIdx(station);
		if (stationIdx != -1) {
			//입력 받은 역이 이미 있는 역일때
			line.setDownLineLast(stations.get(stationIdx));
			stations.get(stationIdx).addStationLine(line.getLineName());
			return;
		}
		SubwayStation stationNew = new SubwayStation(station);
		line.setDownLineLast(stationNew);
		stations.add(stationNew);
		stationNew.addStationLine(line.getLineName());
	}

	public void showSubwayLines() {
		if (stations != null) {
			for (SubwayLine line : lines) {
				System.out.println("[INFO] " + line.getLineName());
			}
		}
	}

	//지하철 노선에 역추가
	public void addStationOnLine(String line, String station, int idx) {
		int idxLine = findLineIdx(line);
		int idxStation = findStationIdx(station);

		if (idxLine == -1 || idxStation == -1) {
			System.out.println("[ERROR] 잘못된 입력입니다.");
			return;
		}
		lines.get(idxLine).setStationsOnLine(stations.get(idxStation), idx);
		stations.get(idxStation).addStationLine(line);
		System.out.println("[INFO] 구간이 등록되었습니다.");
	}

	//지하철 노선에 역삭제
	public void deleteStationOnLine(String line, String station) {
		int idxLine = findLineIdx(line);
		int idxStation = findStationIdx(station);
		if (idxLine == -1 || idxStation == -1 || !lines.get(idxLine).findStationOnLine(station)) {
			System.out.println("[ERROR] 잘못된 입력입니다.");
			return;
		}
		lines.get(idxLine).deleteStationsOnLine(stations.get(idxStation));
		stations.get(idxStation).deleteStationLine(line);
	}

	public void setStationOnLine(SubwayLine line, SubwayStation station, int idx) {
		line.setStationsOnLine(station, idx);
		station.addStationLine(line.getLineName());
	}

	public void showAllElement() {
		for (int i = 0; i < lines.size(); i++) {
			SubwayLine line = lines.get(i);
			System.out.println("[INFO] " + line.getLineName());
			System.out.println("[INFO] ---");
			line.showStationsOnLine();
			System.out.println();
		}
	}
}
