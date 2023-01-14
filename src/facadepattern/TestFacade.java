package facadepattern;

public class TestFacade {

	public static void main(String[] args) {
		
		FacadeServer scheduleServer = new FacadeServer();
		Facade facadeServer = new Facade(scheduleServer);
	}

}
