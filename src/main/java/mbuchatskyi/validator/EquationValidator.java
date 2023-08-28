package mbuchatskyi.validator;

public class EquationValidator {
	private static EquationValidator instance;
	
	public static EquationValidator getInstance() {
		if (instance == null) {
			instance = new EquationValidator();
		}
		
		return instance;
	}
	
	public boolean validate(String equation) {
		if (!checkParenthesis(equation)) {
			return false;
		}
		
		// remove all parenthesis
		equation = equation.replace("(", "").replace(")", "");

		
		return true;
	} 

	private boolean checkParenthesis(String equation) {
		int index = 0;

		for (int i = 0; i < equation.length(); i++) {
			if (Character.toString(equation.charAt(i)).equals("(")) {
				index++;
			}

			if (Character.toString(equation.charAt(i)).equals(")")) {
				index--;
			}

			if (index < 0) {
				return false;
			}
		}

		return index == 0;
	}
	
	private boolean isCharOperator(char ch) {
		 switch (ch) {
	        case '*':
	        case '/':
	        case '+':
	        case '-':
	            return true;
	        default:
	            return false;
	    }
	}
	
}
