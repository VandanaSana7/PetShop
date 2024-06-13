package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;

import java.util.NoSuchElementException;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import com.petshop.in.exceptions.foodcategoryaddress.NoPetFoodFoundException;
import com.petshop.in.exceptions.foodcategoryaddress.PetFoodIdNotFoundException;
import com.petshop.in.model.PetFood;
import com.petshop.in.repository.PetFoodRepository;
import com.petshop.in.serviceimpl.PetFoodServicesImpl;





public class PetFoodServicesTest {

    @Mock

    private PetFoodRepository petFoodRepository;

    @InjectMocks

    private PetFoodServicesImpl petFoodServices;

    @BeforeEach

    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test

    public void testRetrieveAllPetFood() {

        List<PetFood> petFoodList = new ArrayList<>();

        petFoodList.add(new PetFood());

        when(petFoodRepository.findAll()).thenReturn(petFoodList);

        assertDoesNotThrow(() -> petFoodServices.retreiveAllPetFood());

    }

    @Test

    public void testRetrieveAllPetFood_NoPetFoodFoundException() {

        when(petFoodRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NoPetFoodFoundException.class, () -> petFoodServices.retreiveAllPetFood());

    }

//    @Test

//    public void testGetFoodById() {

//        PetFood petFood = new PetFood();

//        petFood.setId(1);

//        Optional<PetFood> optionalPetFood = Optional.of(petFood);

//        when(petFoodRepository.findById(1)).thenReturn(optionalPetFood);

//

//        assertDoesNotThrow(() -> petFoodServices.getFoodById(1));

//    }

    @Test

    public void testGetFoodById_PetFoodIdNotFoundException() {

        when(petFoodRepository.findById(1)).thenThrow(NoSuchElementException.class);

        assertThrows(PetFoodIdNotFoundException.class, () -> petFoodServices.getFoodById(1));

    }

    // Similarly, you can write tests for other methods...

}

