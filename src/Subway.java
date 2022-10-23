import java.util.ArrayList;
import java.util.LinkedList;
public class Subway  {
	private static Subway subway = new Subway();
	private LinkedList<SubwayStation> stations = new LinkedList<SubwayStation>();
	private LinkedList<SubwayLine> lines = new LinkedList<SubwayLine>() ;

	private Subway() {
	}

	public static Subway getInstance() {
		if (subway == null)
		{
			subway = new Subway();

		}
		return subway;
	}
	public static void makeDefault()
	{
		SubwayLine line2 =new SubwayLine("2호선");
		SubwayLine line3 =new SubwayLine("3호선");
		SubwayLine lineNew =new SubwayLine("신분당선");

		SubwayStation s1 =new SubwayStation("교대역");
		SubwayStation s2 =new SubwayStation("강남역");
		SubwayStation s3 =new SubwayStation("역삼역");
		SubwayStation s4 =new SubwayStation("남부터미널역");
		SubwayStation s5 =new SubwayStation("양재역");
		SubwayStation s6 =new SubwayStation("양재시민의숲역");
		SubwayStation s7 =new SubwayStation("매봉역");

		if(subway != null) {
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
	//지하철 역 등록
	public void addSubwayStation(SubwayStation subwaystation) {
		if(stations != null) {
			for (SubwayStation s : stations) {
				if (s.getStationName().equals(subwaystation.getStationName())) {
					System.out.println("[ERROR] 이미 등록된 역 이름입니다.");
					return;
				}
			}
			stations.add(subwaystation);
			System.out.println("[INFO] 지하철 역이 등록되었습니다.");
		}
	}

	//지하철 역 삭제
	public void deleteSubwayStation(String subwayStation) {
		if(stations != null) {
			int idx = stations.size();
			for (int i=idx-1; i>=0; i--) {
				if (stations.get(i).getStationName().equals(subwayStation)) {
					stations.remove(i);
					return;
				}
			}
			System.out.println("[ERROR] 존재하지 않는 역 이름입니다.");
		}
	}
	public void showSubwayStations()
	{
		if(stations != null) {
			for (SubwayStation station : stations) {
				System.out.println("[INFO] " + station.getStationName());
			}
		}
	}
	//지하철 노선 등록
	public void addSubwayLine(SubwayLine subwayLine) {
		if(stations != null) {
			for (SubwayLine s : lines) {
				if (s.getLineName().equals(subwayLine.getLineName())) {
					System.out.println("[ERROR] 이미 등록된 호선 이름입니다.");
					return;
				}
			}
			lines.add(subwayLine);
			System.out.println("[INFO] 호선이 등록되었습니다.");
		}
	}

	//지하철 노선 삭제
	public void deleteSubwayLine(String subwayLine) {
		if(stations != null) {
			int idx = 0;
			for (SubwayLine s : lines) {
				if (s.getLineName().equals(subwayLine)) {
					lines.remove(idx);
					return;
				}
				idx++;
			}
			System.out.println("[ERROR] 존재하지 않는 호선 이름입니다.");
		}
	}

	//지하철 노선 등록할때 상행선도 같이 추가
	public void addUpLine(String station, SubwayLine line)
	{
		for (SubwayStation s : stations) {
			if (s.getStationName().equals(station)) {
				//입력 받은 역이 이미 있는 역일때
				line.setUpLineLast(s);
				s.addStationLine(line.getLineName());
				return;
			}
		}
		SubwayStation stationNew = new SubwayStation(station);
		line.setUpLineLast(stationNew);
		stations.add(stationNew);
		stationNew.addStationLine(line.getLineName());
	}
	//지하철 노선 등록할때 하행선도 같이 추가
	public void addDownLine(String station, SubwayLine line)
	{
		for (SubwayStation s : stations) {
			if (s.getStationName().equals(station)) {
				//입력 받은 역이 이미 있는 역일때
				line.setDownLineLast(s);
				s.addStationLine(line.getLineName());
				return;
			}
		}
		SubwayStation stationNew = new SubwayStation(station);
		line.setDownLineLast(stationNew);
		stations.add(stationNew);
		stationNew.addStationLine(line.getLineName());
	}

	public void showSubwayLines()
	{
		if(stations != null) {
			for (SubwayLine line : lines) {
				System.out.println("[INFO] " + line.getLineName());
			}
		}
	}
	//지하철 노선에 역추가
	public void addStationOnLine(String line, String station, int idx)
	{
		int idxLine = -1;
		int idxStation = -1;
		int x=0;
		int y=0;
		for (SubwayStation s : stations) {
			if (s.getStationName().equals(station)) {
				idxStation = x;
			}
			x++;
		}
		for (SubwayLine s : lines) {
			if (s.getLineName().equals(line)) {
				idxLine = y;
			}
			y++;
		}
		if(idxLine == -1 || idxStation == -1)
		{
			System.out.println("[ERROR] 잘못된 입력입니다.");
			return;
		}
		lines.get(idxLine).setStationsOnLine(stations.get(idxStation),idx);
		stations.get(idxStation).addStationLine(line);
		System.out.println("[INFO] 구간이 등록되었습니다.");
	}

	//지하철 노선에 역삭제
	public void deleteStationOnLine(String line, String station)
	{
		int idxLine = -1;
		int idxStation = -1;
		int x=0;
		int y=0;
		for (SubwayStation s : stations) {
			if (s.getStationName().equals(station)) {
				idxStation = x;
			}
			x++;
		}
		for (SubwayLine s : lines) {
			if (s.getLineName().equals(line)) {
				idxLine = y;
			}
			y++;
		}
		lines.get(idxLine).deleteStationsOnLine(stations.get(idxStation));
		stations.get(idxStation).deleteStationLine(line);
	}

	public void setStationOnLine(SubwayLine line, SubwayStation station, int idx)
	{
		line.setStationsOnLine(station, idx);
		station.addStationLine(line.getLineName());
	}

	public void showAllElement()
	{
		for(SubwayLine line : lines)
		{
			System.out.println("[INFO] " + line.getLineName());
			System.out.println("[INFO] ---");
			for(SubwayStation station : stations)
			{
				System.out.println("[INFO] " + station.getStationName());
			}
			System.out.println();
		}
	}
}
