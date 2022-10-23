import java.util.ArrayList;

public class SubwayStation {
	private String stationName="";
	private ArrayList<String> stationLine = new ArrayList<String>(); //이름만 가지고 있는게 합리적인거 같음

	public SubwayStation(String stationName) {
		if (stationName.length() > 2)
			this.stationName = stationName;
		else
			System.out.println("[ERROR] 역이름은 2글자 이상입니다.");
	}

	public String getStationName() {
		return stationName;
	}

	public void addStationLine(String line)
	{
		this.stationLine.add(line);
	}

	public void deleteStationLine(String line)
	{
		int idx = stationLine.indexOf(line);
		this.stationLine.remove(idx);
	}
}
