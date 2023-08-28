package mbuchatskyi;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import mbuchatskyi.database.dao.EquationDAO;
import mbuchatskyi.database.dao.impl.EquationDAOImpl;
import mbuchatskyi.validator.EquationValidator;

public class MainApplication {
	private static final EquationDAO DAO = new EquationDAOImpl();
	private static final EquationValidator VALIDATOR = EquationValidator.getInstance();
	private static final Scanner SCAN = new Scanner(System.in);
	private static String equation;

	public static void main(String[] args) {
		equation = enterEquation();
		System.out.println("LOG: Validation started.");

		while (!VALIDATOR.validate(equation)) {
			System.out.println("LOG: Validation failed. If you want to try again, please enter 'yes'.");

			if (!SCAN.nextLine().equals("yes")) {
				// If the user has entered an invalid equation and doesn't want to try again,
				// the program terminates.
				SCAN.close();
				System.out.println("LOG: The program terminated.");
				System.exit(0);
			}

			equation = enterEquation();
		}

		System.out.println("LOG: Validation success.");
		try {
			DAO.insert(equation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("LOG: Equation was added to DB.");
		
		System.out.println("Please, enter the root to your equation:");
		BigDecimal root = SCAN.nextBigDecimal();
		
		String equationModified = equation.replaceAll("x", root.toString());
		
		int index = equationModified.indexOf('=');
		String leftSubstring = equationModified.substring(0, index);
		String rightSubString = equationModified.substring(index + 1, equationModified.length());
		
		if (DAO.checkIfRootValid(rightSubString).equals(DAO.checkIfRootValid(leftSubstring))) {
			DAO.insertWithRoot(equation, root);
			System.out.println("LOG: Equation was added to DB with its root.");
		}
		
		System.out.println("You can find all equations in DB with the specified root. Please, enter the root: ");
		root = SCAN.nextBigDecimal();
		
		List<String> equations = DAO.getEquationsByRoot(root);
		
		System.out.println("LOG: All equations with the specified root:");
		for (String equation : equations) {
			System.out.println(equation);
		}
		
		System.out.println("You can find all equations in DB with the specified N of roots. Please, enter the N: ");
		int n = SCAN.nextInt();
		
		equations = DAO.getAllEquationsWithNRoots(n);
		
		System.out.println("LOG: All equations with the N roots:");
		for (String equation : equations) {
			System.out.println(equation);
		}
		
		System.out.println("The program is terminated.");
		SCAN.close();
	}

	private static String enterEquation() {
		System.out.println("Please, enter the equation. Note that the equation must be written on one line.");
		return SCAN.nextLine();
	}
}
