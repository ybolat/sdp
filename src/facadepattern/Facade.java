package facadepattern;

import java.sql.SQLException;

public class Facade {
	
	private final FacadeServer facadeServer;

	public Facade(FacadeServer facadeServer){
		this.facadeServer = facadeServer;
	}

	public void start() throws SQLException {
		facadeServer.main();
	}

}
