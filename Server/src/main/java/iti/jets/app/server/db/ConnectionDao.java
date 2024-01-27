package iti.jets.app.server.db;

import iti.jets.app.server.models.entities.Connection;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionDao implements Dao<Connection, Integer> {
    DataSource dataSource = null;
    public ConnectionDao() {
        dataSource = DataSourceFactory.getMySQLDataSource();
    }
    @Override
    public Connection getById(Integer integer) {
        return null;
    }
    public int insert(Connection connection) {
        String query = "INSERT INTO connections (first_user_id, second_user_id, connected_since) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, connection.getFirstUserId());
            preparedStatement.setInt(2, connection.getSecondUserId());
            preparedStatement.setTimestamp(3, connection.getConnectedSince());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int update(Connection connection) {
        return 0;
    }
    @Override
    public int delete(Integer integer) {
        return 0;
    }

    public int delete(Connection connection) {
        String query = "DELETE FROM connections \n" +
                "WHERE (first_user_id = ? AND second_user_id = ?) OR (second_user_id = ? AND first_user_id = ?);";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, connection.getFirstUserId());
            preparedStatement.setInt(2, connection.getSecondUserId());
            preparedStatement.setInt(3, connection.getSecondUserId());
            preparedStatement.setInt(4, connection.getFirstUserId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> getAllConnections(int userId) {
        ArrayList<Integer> userFriendsIDs = new ArrayList<>();
        String query = "SELECT second_user_id FROM connections WHERE first_user_id = ? \n" +
                "UNION\n" +
                "SELECT first_user_id FROM connections WHERE second_user_id = ?;";
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    int userFriendId = resultSet.getInt(1);

                    userFriendsIDs.add(userFriendId);
                }
                return userFriendsIDs;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}