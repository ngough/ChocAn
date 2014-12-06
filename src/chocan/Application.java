package chocan;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Application {

	
	public static void main(String[] args) throws IOException {
		UserInterface ui = new UserInterface();
		ui.printLoginPage();
		ui.login();
		ui.executeCommand();
	}

}
