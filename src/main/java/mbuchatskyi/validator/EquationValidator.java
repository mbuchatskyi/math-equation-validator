package mbuchatskyi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationValidator {
	private static EquationValidator instance;
	
	public static EquationValidator getInstance() {
		if (instance == null) {
			instance = new EquationValidator();
		}
		
		return instance;
	}
	
	public boolean validate(String equation) {
		if (!checkParenthesis(equation) || !checkXandEqualSign(equation)) {
			return false;
		}
		
		String regex = "^(?!.*[+\\-*/]{2})[\\d.\\-+*/()=x ]+$";
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(equation);
       
		return matcher.matches();
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
	
	private boolean checkXandEqualSign(String equation) {
		int counterX = 0;
		int counterEqualsSign = 0;
		
		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == 'x') 
				counterX++;
			
			if (equation.charAt(i) == '=') 
				counterEqualsSign++;
		}
		
		return (counterX > 0 && counterEqualsSign == 1);	
	}
}
