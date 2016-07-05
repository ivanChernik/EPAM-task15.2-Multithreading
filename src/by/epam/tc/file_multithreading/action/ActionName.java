package by.epam.tc.file_multithreading.action;

public enum ActionName {
	ADDICTION, MULTIPLICATION, SUM_OF_SQUARES;

	public static ActionName getActionName(int element) {
		switch (element) {
		case 1:
			return ADDICTION;
		case 2:
			return MULTIPLICATION;
		case 3:
			return SUM_OF_SQUARES;
		default:
			throw new EnumConstantNotPresentException(ActionName.class,
					Integer.toString(element));
		}
	}
}
