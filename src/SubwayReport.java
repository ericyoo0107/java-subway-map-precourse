import java.io.IOException;
import java.util.Scanner;
public class SubwayReport {
	Subway subway = Subway.getInstance();
	private Scanner scanner = new Scanner(System.in);
	public void ShowMain()
	{
		System.out.println("## 메인화면");
		System.out.println("1. 역 관리");
		System.out.println("2. 노선 관리");
		System.out.println("3. 구간 관리");
		System.out.println("4. 지하철 노선도 출력");
		System.out.println("Q. 종료");
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
	}

	public void manageStation()
	{
		System.out.println("## 역 관리 화면");
		System.out.println("1. 역 등록");
		System.out.println("2. 역 삭제");
		System.out.println("3. 역 조회");
		System.out.println("B. 돌아가기");
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
	}



	public void manageLine() {
		System.out.println("## 노선 관리 화면");
		System.out.println("1. 노선 등록");
		System.out.println("2. 노선 삭제");
		System.out.println("3. 노선 조회");
		System.out.println("B. 돌아가기");
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
	}
	public void manageSection()
	{
		System.out.println("## 구간 관리 화면");
		System.out.println("1. 구간 등록");
		System.out.println("2. 구간 삭제");
		System.out.println("B. 돌아가기");
		System.out.println();
		System.out.println("## 원하는 기능을 선택하세요.");
	}
	public void showAllElement()
	{
		System.out.println("## 지하철 노선도");
		subway.showAllElement();
	}
	public void reportMain() throws IOException {
		ShowMain();
		String mainNum = scanner.nextLine();
		switch (mainNum)
		{
			case "1":
			{
				manageStation();
				String stationNum = scanner.nextLine();
				switch (stationNum) {
					case "1":
						System.out.println("## 등록할 역 이름을 입력하세요.");
						String station = scanner.nextLine();
						subway.addSubwayStation(new SubwayStation(station));
						break;
					case "2":
						System.out.println("## 삭제할 역 이름을 입력하세요.");
						station = scanner.nextLine();
						subway.deleteSubwayStation(station);
						break;
					case "3":
						System.out.println("## 역 목록");
						subway.showSubwayStations();
						break;
					case "B":
						System.out.println("## 뒤로 돌아갑니다.");
						break;
					default:
						break;
				}
			}
			break;
			case "2": {
				manageLine();
				String lineNum = scanner.nextLine();
				switch (lineNum) {
					case "1":
						System.out.println("## 등록할 노선 이름을 입력하세요.");
						String line = scanner.nextLine();
						SubwayLine subwayLine = new SubwayLine(line);
						if(subway.addSubwayLine(subwayLine)) {
							System.out.println("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
							String upLine = scanner.nextLine();
							subway.addUpLine(upLine, subwayLine);
							System.out.println("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
							String downLine = scanner.nextLine();
							subway.addDownLine(downLine, subwayLine);
						}
						break;
					case "2":
						System.out.println("## 삭제할 노선 이름을 입력하세요.");
						line = scanner.nextLine();
						subway.deleteSubwayLine(line);
						break;
					case "3":
						System.out.println("## 노선 목록");
						subway.showSubwayLines();
						break;
					case "B":
						System.out.println("## 뒤로 돌아갑니다.");
						break;
					default:
						break;
				}
			}
			break;
			case "3": {
				manageSection();
				String sectionNum = scanner.nextLine();
				switch (sectionNum) {
					case "1":
						System.out.println("## 노선을 입력하세요.");
						String line = scanner.nextLine();
						System.out.println("## 역이름을 입력하세요.");
						String station = scanner.nextLine();
						System.out.println("## 순서를 입력하세요.");
						int idx = scanner.nextInt();
						subway.addStationOnLine(line, station, idx);
						break;
					case "2":
						System.out.println("## 삭제할 구간의 노선을 입력하세요.");
						line = scanner.nextLine();
						System.out.println("## 삭제할 구간의 역을 입력하세요.");
						station = scanner.nextLine();
						subway.deleteStationOnLine(line, station);
						break;
					case "B":
						System.out.println("## 뒤로 돌아갑니다.");
						break;
					default:
						break;
				}
			}
			break;
			case "4": {
				showAllElement();
			}
			break;
			case "Q":{
				System.exit(0);
			}
			break;
		}
	}
}
