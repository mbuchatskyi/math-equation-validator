package mbuchatskyi.database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface EquationDAO {
	boolean insert(String equation) throws SQLException;
	
	BigDecimal checkIfRootValid(String equation);
	
	boolean insertWithRoot(String equation, BigDecimal root);
	
	List<String> getEquationsByRoot(BigDecimal root);
	
	List<String> getAllEquationsWithNRoots(int n);
}
