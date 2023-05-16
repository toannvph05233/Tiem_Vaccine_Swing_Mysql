package dao;



import entity.Vaccine;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VaccineDao {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public List<Vaccine> getAllByIdCustomer(int idCustomer) {
        try {
            String sql = "select * from vaccine where customerid = ?";
            Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCustomer);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Vaccine> vaccines = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String vacDate = resultSet.getString("vaccinDate");
                String injectDate = resultSet.getString("injectAgain");
                java.util.Date vaccinDate = format.parse(vacDate);
                java.util.Date injectAgain = format.parse(injectDate);
                Vaccine vaccine = new Vaccine(id, name, price, vaccinDate, injectAgain);
                vaccines.add(vaccine);
            }
            return vaccines;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean create(Vaccine vaccine, int customerId) {
        try {
            String sql = "INSERT INTO `vaccine`.`Vaccine` (`name`, `price`, `vaccinDate`, `injectAgain`,`customerid`) VALUES (?, ?, ?, ?, ?);";
            Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vaccine.getName());
            preparedStatement.setDouble(2, vaccine.getPrice());
            preparedStatement.setString(3, format.format(vaccine.getVaccinDate()));
            preparedStatement.setString(4, format.format(vaccine.getInjectAgain()));
            preparedStatement.setInt(5, customerId);

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean edit(Vaccine vaccine, int customerId) {
        try {
            String sql = "UPDATE `vaccine`.`Vaccine` SET `name` = ?, `price` = ?, `vaccinDate` = ?, `injectAgain` = ?, `customerid` = ? WHERE (`id` = ?);";
            Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vaccine.getName());
            preparedStatement.setDouble(2, vaccine.getPrice());
            preparedStatement.setString(3, format.format(vaccine.getVaccinDate()));
            preparedStatement.setString(4, format.format(vaccine.getInjectAgain()));
            preparedStatement.setInt(5, customerId);
            preparedStatement.setInt(6, vaccine.getId());

            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM `vaccine`.`Vaccine` WHERE (`id` = ?)";
            Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
