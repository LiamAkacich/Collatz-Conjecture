/**
 * 
 * DBUtil.java
 * 
 */


import java.sql.*;
import java.util.ArrayList;


/**
 * Performs all CRUD database operations.
 * 
 * @version 1.0
 * @since 27/11/2022
 * @author Liam Akacich
 */
public class DBUtil {
	private static final String DATA_TABLE = "attempts";

	private static final String DB_URL = "jdbc:sqlite:CollatzConjecture.db";

	/**
	 * Connects to the SQL database specified in the DB_URL constant
	 *
	 * @throws SQLException If the connection to the database fails.
	 * @see Connection
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}


	public static boolean attemptExists(long attemptNumber) {
		boolean result = false;
		String query = String.format("SELECT * FROM %s WHERE %s.attempt_number = ?", DATA_TABLE, DATA_TABLE);

		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setLong(1, attemptNumber);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}


	public static ArrayList<Attempt> getAttempts() {
		// Declare a observable List which comprises of Employee objects
		ArrayList<Attempt> result = new ArrayList<Attempt>();

		
		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
			
			String query = String.format("SELECT * FROM %s ORDER BY %s.attempt_number", DATA_TABLE, DATA_TABLE);
			ResultSet rs = stmt.executeQuery(query);

			// loop through the result set
			while (rs.next()) {
				Attempt attempt = new Attempt(rs.getLong("attempt_number"), rs.getLong("stopping_time"), rs.getLong("highest_reached"));

				result.add(attempt);
			}
		} catch (SQLException e) { // If the username is not found.
			System.out.println(e.getMessage());
		}

		return result;
	}

	public static boolean addAttempt(long attemptNumber, long stoppingTime, long highestReached) {
		boolean result = false;

		String query = String.format("INSERT INTO %s (attempt_number, stopping_time, highest_reached) VALUES (?, ?, ?)", DATA_TABLE);

		try {
			Connection connectedDB = getConnection();
			PreparedStatement pstmt = connectedDB.prepareStatement(query);

			pstmt.setLong(1, attemptNumber);
			pstmt.setLong(2, stoppingTime);
			pstmt.setLong(3, highestReached);

			if (pstmt.executeUpdate() == 1) {
				result = true;
			}

			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	
	public static boolean addAttempt(Attempt attempt) {
		boolean result = false;

		String query = String.format("INSERT INTO %s (attempt_number, stopping_time, highest_reached) VALUES (?, ?, ?)", DATA_TABLE);

		try {
			Connection connectedDB = getConnection();
			PreparedStatement pstmt = connectedDB.prepareStatement(query);

			pstmt.setLong(1, attempt.getAttemptNumber());
			pstmt.setLong(2, attempt.getStoppingTime());
			pstmt.setLong(3, attempt.getHighestReached());

			if (pstmt.executeUpdate() == 1) {
				result = true;
			}

			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

}
