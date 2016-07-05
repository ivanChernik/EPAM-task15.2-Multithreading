package by.epam.tc.file_multithreading.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ActionThread extends Thread {

	private static final String ACTION_THREAD = "Action thread";
	private static final String ERROR_FINDING_INPUT_FILE = "Error finding input file";
	private static final String SUM_OF_SQUARES = "Sum of squares";
	private static final String ADDICTION = "Addiction";
	private static final String MULTIPLICATION = "Multiplication";
	private File inputFile;
	private Scanner scanner;
	private volatile PrintWriter out;

	private static final Logger log = Logger.getLogger(ActionThread.class);

	public ActionThread(File inputFile, PrintWriter out, ThreadGroup group) {
		super(group, ACTION_THREAD);
		this.inputFile = inputFile;
		this.out = out;
	}

	public void run() {
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			log.error(ERROR_FINDING_INPUT_FILE, e);
			throw new RuntimeException(ERROR_FINDING_INPUT_FILE, e);
		}

		if (!scanner.hasNextInt())
			return;
		int action = scanner.nextInt();

		ActionName actionName = ActionName.getActionName(action);
		switch (actionName) {
		case ADDICTION:
			doAddiction();
			break;
		case MULTIPLICATION:
			doMultiplication();
			break;
		case SUM_OF_SQUARES:
			doSumOfSquares();
			break;
		}
	}

	private void doAddiction() {
		List<Double> numbers = new ArrayList<Double>();
		while (scanner.hasNextDouble()) {
			numbers.add(scanner.nextDouble());
		}

		double resultAddiction = 0.0;

		for (Double number : numbers) {
			resultAddiction += number;
		}

		writeResult(resultAddiction, ADDICTION);

	}

	private void doMultiplication() {
		List<Double> numbers = new ArrayList<Double>();
		while (scanner.hasNextDouble()) {
			numbers.add(scanner.nextDouble());
		}

		double resultMultiplication = 1.0;

		for (Double number : numbers) {
			resultMultiplication *= number;
		}

		writeResult(resultMultiplication, MULTIPLICATION);
	}

	private void doSumOfSquares() {
		List<Double> numbers = new ArrayList<Double>();
		while (scanner.hasNextDouble()) {
			numbers.add(scanner.nextDouble());
		}

		double resultSumOfSquares = 0.0;

		for (Double number : numbers) {
			resultSumOfSquares += Math.pow(number, 2);
		}

		writeResult(resultSumOfSquares, SUM_OF_SQUARES);
	}

	private synchronized void writeResult(double result, String nameOperation) {
		out.println(nameOperation + " = " + result);
	}

}
