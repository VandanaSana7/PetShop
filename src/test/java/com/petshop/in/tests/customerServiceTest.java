package com.petshop.in.tests;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import static org.junit.jupiter.api.Assertions.assertThrows;
 
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
 
import java.util.List;
 
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
 
import org.junit.jupiter.api.Test;
 
import org.mockito.InjectMocks;
 
import org.mockito.Mock;
 
import org.mockito.MockitoAnnotations;

import com.petshop.in.enums.transaction_status;
import com.petshop.in.exceptions.customer.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customer.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customer.CustomerFirstnameLastnameNotFoundException;
import com.petshop.in.exceptions.customer.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionCustomerIdNotFoundException;
import com.petshop.in.exceptions.customer.CustomerTransactionStatusNotFoundException;
import com.petshop.in.exceptions.customer.CustomersCannotBeAddedException;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.CustomerRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.repository.TransactionRepository;
import com.petshop.in.serviceimpl.CustomerServiceImpl;
 

 
 

public class customerServiceTest {
    @Mock
 
    private CustomerRepository customerRepository;
    @Mock
 
    private TransactionRepository transactionRepository;
    @Mock
 
    private PetsRepository petsRepository;
    @InjectMocks
 
    private CustomerServiceImpl customerService;
    @BeforeEach
 
    public void setup() {
 
        MockitoAnnotations.openMocks(this);
 
    }
    @Test
 
    public void testRetrieveAllCustomers() {
 
        List<Customers> customersList = new ArrayList<>();
 
        customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
 
        customersList.add(new Customers("Jane", "Smith", "jane@example.com", "0987654321", null));
        when(customerRepository.findAll()).thenReturn(customersList);
        List<Customers> result = customerService.retreiveAllCustomers();
        assertEquals(2, result.size());
 
    }
    @Test
 
    public void testGetCustomerById_ExistingId() throws CustomerIdNotFoundException, com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException {
 
        Customers customer = new Customers("John", "Doe", "john@example.com", "1234567890", null);
 
        Optional<Customers> optionalCustomer = Optional.of(customer);
        when(customerRepository.findById(1)).thenReturn(optionalCustomer);
        Customers result = customerService.getCustomerById(1);
        assertEquals(customer, result);
 
    }
    @Test
 
    public void testGetCustomerById_NonExistingId() {
 
        when(customerRepository.findById(1)).thenReturn(Optional.empty());
        //assertThrows(CustomerIdNotFoundException.class, () -> customerService.getCustomerById(1));
 
    }
    @Test
 
    public void testGetCustomerByCity_ExistingCity() throws CustomerCityNotFoundException {
 
        List<Customers> customersList = new ArrayList<>();
 
        customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
        when(customerRepository.findByCity("New York")).thenReturn(customersList);
        List<Customers> result = customerService.getCustomerBycity("New York");
        assertEquals(1, result.size());
 
    }
    @Test
 
    public void testGetCustomerByCity_NonExistingCity() {
 
        when(customerRepository.findByCity("New York")).thenReturn(new ArrayList<>());
        assertThrows(CustomerCityNotFoundException.class, () -> customerService.getCustomerBycity("New York"));
 
    }
    @Test
 
    public void testGetCustomerByState_ExistingState() throws CustomerStateNotFoundException {
 
        List<Customers> customersList = new ArrayList<>();
 
        customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
        when(customerRepository.findByState("California")).thenReturn(customersList);
        List<Customers> result = customerService.getCustomerBystate("California");
        assertEquals(1, result.size());
 
    }
    @Test
 
    public void testGetCustomerByState_NonExistingState() {
 
        when(customerRepository.findByState("California")).thenReturn(new ArrayList<>());
        assertThrows(CustomerStateNotFoundException.class, () -> customerService.getCustomerBystate("California"));
 
    }
    @Test
 
    public void testGetCustomerByTransactionCustomerId_ExistingCustomerId() throws CustomerTransactionCustomerIdNotFoundException {
 
        List<Transactions> transactionsList = new ArrayList<>();
 
        transactionsList.add(new Transactions());
        when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(transactionsList);
        List<Transactions> result = customerService.getCustomerBytranscustid(1);
        assertEquals(1, result.size());
 
    }
    @Test
 
    public void testGetCustomerByTransactionCustomerId_NonExistingCustomerId() {
 
        when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(new ArrayList<>());
        assertThrows(CustomerTransactionCustomerIdNotFoundException.class, () -> customerService.getCustomerBytranscustid(1));
 
    }
    @Test
 
    public void testFindCustomersByTransactionStatus_ExistingStatus() throws CustomerTransactionStatusNotFoundException {
 
        List<Transactions> transactionsList = new ArrayList<>();
 
        transactionsList.add(new Transactions());
        when(transactionRepository.findByTransactionStatus(transaction_status.Success)).thenReturn(transactionsList);
        List<Transactions> result = customerService.findCustomersByTransactionStatus(transaction_status.Success);
        assertEquals(1, result.size());
 
    }

    @Test
 
    public void testFindCustomersByNotransaction() {
 
        List<Customers> customersList = new ArrayList<>();
 
        customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
        when(customerRepository.findCustomerByNotransaction()).thenReturn(customersList);
        List<Customers> result = customerService.findCustomersByNotransaction();
        assertEquals(1, result.size());
 
    }
    @Test
 
    public void testGetCustomerByfirstNameAndLastName_ExistingName() throws CustomerFirstnameLastnameNotFoundException {
 
        List<Customers> customersList = new ArrayList<>();
 
        customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
        when(customerRepository.findCustomerByfirstNameAndlastName("John", "Doe")).thenReturn(customersList);
        List<Customers> result = customerService.getCustomerByfirstNameAndLastName("John", "Doe");
        assertEquals(1, result.size());
 
    }
    @Test
 
    public void testGetCustomerByfirstNameAndLastName_NonExistingName() {
 
        when(customerRepository.findCustomerByfirstNameAndlastName("John", "Doe")).thenReturn(new ArrayList<>());
        assertThrows(CustomerFirstnameLastnameNotFoundException.class, () -> customerService.getCustomerByfirstNameAndLastName("John", "Doe"));
 
    }
    @Test
 
    public void testUpdateCustomerById_ExistingId() throws CustomerIdNotFoundException, CustomerCannotBeUpdatedException, com.petshop.in.exceptions.categorytransaction.CustomerIdNotFoundException {
 
        Customers existingCustomer = new Customers("John", "Doe", "john@example.com", "1234567890", null);
 
        Customers updatedCustomer = new Customers("John", "Smith", "john@example.com", "0987654321", null);
        when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));
      //  assertEquals("Data updated", customerService.updateCustomerById(1, updatedCustomer).getMessage());
 
    }

    @Test
 
    public void testUpdateCustomerById_InvalidData() {
 
        Customers existingCustomer = new Customers("John", "Doe", "john@example.com", "1234567890", null);
 
        Customers updatedCustomer = new Customers("123", "Smith", "john@example.com", "0987654321", null);
        when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));
        assertThrows(CustomerCannotBeUpdatedException.class, () -> customerService.updateCustomerById(1, updatedCustomer));
 
    }

    @Test
 
    public void testAddCustomers_InvalidData() {
 
        Customers customerToAdd = new Customers("123", "Doe", "john@example.com", "1234567890", null);
        assertThrows(CustomersCannotBeAddedException.class, () -> customerService.addCustomers(customerToAdd));
 
    }

    @Test
 
    public void testGetAllPetsByCustomerId_NonExistingCustomerId() {
 
        when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(new ArrayList<>());
        
 
    }
 
}
