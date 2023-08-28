package mbuchatskyi.database.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mbuchatskyi.database.connection.DBManager;
import mbuchatskyi.database.dao.EquationDAO;

public class EquationDAOImpl implements EquationDAO {
	private static final String MYSQL_INSERT_EQUATION = "INSERT INTO `equation` \r\n" + "VALUES (?)";

	private static final String MYSQL_INSERT_EQUATION_WITH_ROOT = "INSERT INTO `equation_root` \r\n" + "VALUES (?,?)";

	private static final String MYSQL_SELECT_EQUATIONS_BY_ROOT = "SELECT equation_id FROM equation_root\r\n"
			+ "WHERE ROOT = ";
	
	private static final String MYSQL_SELECT_EQUATIONS_BY_N_ROOTS = "SELECT equation_id FROM equation_root\r\n"
			+ "group by equation_id\r\n"
			+ "having count(*) = ";
	
	@Override
	public boolean insert(String equation) throws SQLException {
		try (Connection cn = DBManager.getInstance().getConnection();
				PreparedStatement ps = cn.prepareStatement(MYSQL_INSERT_EQUATION)) {
			ps.setString(1, equation);
			return (ps.executeUpdate() != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public BigDecimal checkIfRootValid(String equation) {
		try (Connection cn = DBManager.getInstance().getConnection(); Statement statement = cn.createStatement()) {
			ResultSet rs = statement.executeQuery("SELECT " + equation + " AMOUNT");
			rs.next();
			return rs.getBigDecimal(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new BigDecimal(-1);
	}

	@Override
	public boolean insertWithRoot(String equation, BigDecimal root) {
		try (Connection cn = DBManager.getInstance().getConnection();
				PreparedStatement ps = cn.prepareStatement(MYSQL_INSERT_EQUATION_WITH_ROOT)) {
			ps.setString(1, equation);
			ps.setBigDecimal(2, root);
			return (ps.executeUpdate() != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<String> getEquationsByRoot(BigDecimal root) {
		List<String> resultSet = new ArrayList<>();

		try (Connection cn = DBManager.getInstance().getConnection(); Statement statement = cn.createStatement()) {
			ResultSet rs = statement.executeQuery(MYSQL_SELECT_EQUATIONS_BY_ROOT + root);
			while (rs.next()) {
				resultSet.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	@Override
	public List<String> getAllEquationsWithNRoots(int n) {
		List<String> result = new ArrayList<>();

		try (Connection cn = DBManager.getInstance().getConnection(); Statement statement = cn.createStatement()) {
			ResultSet rs = statement.executeQuery(MYSQL_SELECT_EQUATIONS_BY_N_ROOTS + n);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
