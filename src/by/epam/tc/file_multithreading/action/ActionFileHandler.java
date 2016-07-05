package by.epam.tc.file_multithreading.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class ActionFileHandler {
	private static final String ACTION_GROUP = "Action group";
	private static final String SRC_RESULT_OUT_DAT = "src/result/out.dat";
	private static final String ERROR_FINDING_RESULT_FILE = "Error finding result file";
	private static final Logger log = Logger.getLogger(ActionFileHandler.class);
	private File[] files;

	public void doAction(String path) {
		File folder = new File(path);
		files = folder.listFiles();
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(SRC_RESULT_OUT_DAT));
		} catch (IOException e) {
			log.error(ERROR_FINDING_RESULT_FILE, e);
			throw new RuntimeException(ERROR_FINDING_RESULT_FILE,e);
		}
		
		ThreadGroup group = new ThreadGroup(ACTION_GROUP);
		
		for (int indexFile = 0; indexFile < files.length; indexFile++) {
			ActionThread thread = new ActionThread(files[indexFile], out,group);
			thread.start();
		}
		
		//waiting all threads
		while(group.activeCount() != 0){}
		
		out.close();
	}

}
