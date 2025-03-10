package service;

import model.Customer;

import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    // 添加客户
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    // 更新客户
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    // 删除客户
    public void deleteCustomer(int id) {
        customerRepository.deleteCustomer(id);
    }

    // 查询客户
    public List<Customer> searchCustomers(String name, String companyName, String level, String contactPhone) {
        return customerRepository.searchCustomers(name, companyName, level, contactPhone);
    }

    // 获取所有客户
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
