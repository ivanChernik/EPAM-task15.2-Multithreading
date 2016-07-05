package by.epam.tc.file_multithreading.main;

import by.epam.tc.file_multithreading.action.ActionFileHandler;

public class TestActionHandler {

	private static final String SRC_FILES = "src/files";

	public static void main(String[] args) {
		ActionFileHandler ah = new ActionFileHandler();
		ah.doAction(SRC_FILES);

	}

}
