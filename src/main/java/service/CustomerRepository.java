package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import util.DBConnection;

public class CustomerRepository {

    // 添加客户
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, companyName, customerLevel, contactName, contactPhone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCompanyName());
            statement.setString(3, customer.getCustomerLevel());
            statement.setString(4, customer.getContactName());
            statement.setString(5, customer.getContactPhone());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新客户
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?, companyName = ?, customerLevel = ?, contactName = ?, contactPhone = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCompanyName());
            statement.setString(3, customer.getCustomerLevel());
            statement.setString(4, customer.getContactName());
            statement.setString(5, customer.getContactPhone());
            statement.setInt(6, customer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除客户
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询客户（模糊查询）
    public List<Customer> searchCustomers(String name, String companyName, String level, String contactPhone) {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE name LIKE ? AND companyName LIKE ? AND customerLevel LIKE ? AND contactPhone LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + (name != null ? name : "") + "%");
            statement.setString(2, "%" + (companyName != null ? companyName : "") + "%");
            statement.setString(3, "%" + (level != null ? level : "") + "%");
            statement.setString(4, "%" + (contactPhone != null ? contactPhone : "") + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("companyName"),
                        resultSet.getString("contactName"),
                        resultSet.getString("contactPhone"),
                        resultSet.getString("customerLevel")
                );
                results.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // 获取所有客户
    public List<Customer> getAllCustomers() {
        List<Customer> results = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("companyName"),
                        resultSet.getString("contactName"),
                        resultSet.getString("contactPhone"),
                        resultSet.getString("customerLevel")
                );
                results.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
