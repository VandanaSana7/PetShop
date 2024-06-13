package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.AddressAndSuppliers.AddressIdNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.repository.AddressRepository;
import com.petshop.in.serviceimpl.AddressServiceImpl;



@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Addresses address;

    @BeforeEach
    void setUp() {
        address = new Addresses("Street1", "City1", "State1", "12345");
    }

    @Test
    void testGetAllAddresses() throws AddressIdNotFoundException {
        // Mocking behavior for findAll method
        List<Addresses> addresses = new ArrayList<>();
        addresses.add(address);
        when(addressRepository.findAll()).thenReturn(addresses);

        // Call the method under test
        List<Addresses> result = addressService.getAllAddresses();

        // Verify that findAll method of addressRepository is called
        verify(addressRepository).findAll();

        // Assert that the result is not null and contains the expected address
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }
    
    
    @Test
    void testGetAllAddresses_Negative() throws AddressIdNotFoundException {
        // Mocking behavior for findAll method returning an empty list
        when(addressRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method under test
        List<Addresses> result = addressService.getAllAddresses();

        // Verify that findAll method of addressRepository is called
        verify(addressRepository).findAll();

        // Assert that the result is not null and is an empty list
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    
    
    

    @Test
    void testGetAddressById() throws AddressIdNotFoundException {
        // Mocking behavior for findById method
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        // Call the method under test
        Addresses result = addressService.getAddressById(1);

        // Verify that findById method of addressRepository is called
        verify(addressRepository).findById(1);

        // Assert that the result is not null and matches the expected address
        assertNotNull(result);
        assertEquals(address, result);
    }
    
    
    
    
    

    @Test
    void testAddAddress() throws MismatchDataTypeException {
        // Mocking behavior for save method
        when(addressRepository.save(address)).thenReturn(address);

        // Call the method under test
        SuccessResponse result = addressService.addAddress(address);

        // Verify that save method of addressRepository is called
        verify(addressRepository).save(address);

        // Assert that the result is not null and contains the expected success message
        assertNotNull(result);
        assertEquals("Addresss Added Successfully\n" + "\n" + address, result.getMessage());
        assertEquals(HttpStatus.ACCEPTED.toString(), result.getStatus());
    }

    @Test
    void testUpdateAddress() throws MismatchDataTypeException, AddressIdNotFoundException {
        // Mocking behavior for findById method
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        // Mocking behavior for save method
        when(addressRepository.save(address)).thenReturn(address);

        // Call the method under test
        SuccessResponse result = addressService.updateAddress(1, address);

        // Verify that findById and save methods of addressRepository are called
        verify(addressRepository).findById(1);
        verify(addressRepository).save(address);

        // Assert that the result is not null and contains the expected success message
        assertNotNull(result);
        assertEquals("Addresss updated Successfully\n" + address, result.getMessage());
        assertEquals(HttpStatus.ACCEPTED.toString(), result.getStatus());
    }
}
