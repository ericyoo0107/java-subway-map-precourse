import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Subway subway = Subway.getInstance();
		SubwayReport report = new SubwayReport();
		subway.makeDefault();

		while (true) {
			report.reportMain();
		}
	}
}
