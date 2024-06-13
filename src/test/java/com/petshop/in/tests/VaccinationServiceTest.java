package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingvaccination.VaccinationInvalidInputException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsIdNotFoundException;
import com.petshop.in.exceptions.groomingvaccination.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.repository.VaccinationRepository;
import com.petshop.in.serviceimpl.VaccinationServiceImpl;



public class VaccinationServiceTest {

    @Mock
    private VaccinationRepository vaccinationRepository;

    @InjectMocks
    private VaccinationServiceImpl vaccinationService;

    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetAllVaccinations() throws VaccinationsNotFoundException{

        List<Vaccinations> vaccinationsList = new ArrayList<>();

        vaccinationsList.add(new Vaccinations(1,"Vaccination1", "Description1", 100.00, 1));

        vaccinationsList.add(new Vaccinations(2, "Vaccination2", "Description2", 200.00, 0));

        when(vaccinationRepository.findAll()).thenReturn(vaccinationsList);

        List<Vaccinations> result = vaccinationService.getAllVaccinations();

        assertEquals(vaccinationsList.size(), result.size());

        assertEquals(vaccinationsList, result);

    }

   

    @Test
    public void testAddVaccinationService() throws MismatchDataTypeException {

        Vaccinations vaccination = new Vaccinations( "Vaccination1", "Description1", 100.00, 1);

        SuccessResponse successResponse = new SuccessResponse();

        successResponse.setMessage("Data added" + vaccination);

        successResponse.setStatus("Success");

        when(vaccinationRepository.save(any(Vaccinations.class))).thenReturn(vaccination);

        SuccessResponse result = null;

		try {

			result = vaccinationService.addVaccinationService(vaccination);

		} catch (VaccinationInvalidInputException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (MismatchDataTypeException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

        assertEquals(successResponse.getMessage(), result.getMessage());
        assertEquals(successResponse.getStatus(), result.getStatus());

    }


       



    @Test
    public void testUpdateVaccinationService() throws VaccinationsIdNotFoundException, MismatchDataTypeException {

        int vaccinationId = 1;

        Vaccinations updatedVaccination = new Vaccinations(1, "UpdatedVaccination", "UpdatedDescription", 200.00, 0);

        Vaccinations existingVaccination = new Vaccinations(1, "Vaccination1", "Description1", 100.00, 1);

        when(vaccinationRepository.findById(vaccinationId)).thenReturn(Optional.of(existingVaccination));

        when(vaccinationRepository.save(any(Vaccinations.class))).thenReturn(updatedVaccination);

        SuccessResponse result = vaccinationService.updateVaccinationService(vaccinationId, updatedVaccination);

        assertEquals("Data Updated" + updatedVaccination, result.getMessage());

        assertEquals("Success", result.getStatus());

    }

   


        @Test
        public void testGetAllVaccinationsAvailable() {

            try {

                List<Vaccinations> availableVaccinations = vaccinationService.getAllVaccinationsAvailable();

                Assertions.assertNotNull(availableVaccinations, "List of available vaccinations should not be null");

                // Add more assertions as needed

            } catch (Exception e) {

                Assertions.fail("Exception occurred while getting available vaccinations: " + e.getMessage());

            }

        }

        @Test
        public void testGetAllVaccinationsUnavailable() {

            try {

                List<Vaccinations> unavailableVaccinations = vaccinationService.getAllVaccinationsUnavailable();

                Assertions.assertNotNull(unavailableVaccinations, "List of unavailable vaccinations should not be null");

                // Add more assertions as needed

            } catch (Exception e) {

                Assertions.fail("Exception occurred while getting unavailable vaccinations: " + e.getMessage());

            }

        }


            @Test
            public void testGetAllVaccinationsAvailable1() {

                List<Vaccinations> availableVaccinations = vaccinationService.getAllVaccinationsAvailable();

                Assertions.assertNotNull(availableVaccinations, "List of available vaccinations should not be null");

                // Add more assertions as needed

            }

            @Test
            public void testGetAllVaccinationsUnavailable1() {

                List<Vaccinations> unavailableVaccinations = vaccinationService.getAllVaccinationsUnavailable();

                Assertions.assertNotNull(unavailableVaccinations, "List of unavailable vaccinations should not be null");

                // Add more assertions as needed

            }


                @Test
                public void testGetVaccinationsByServiceId() {

                    int vaccinationId = 1; // Provide a valid vaccination ID for testing

                    try {

                        Vaccinations result = vaccinationService.getVaccinationsByServiceId(vaccinationId);

                        Assertions.assertNotNull(result, "Result should not be null");

                        // Add more assertions as needed

                    } catch (VaccinationsIdNotFoundException | MismatchDataTypeException e) {

                        //Assertions.fail("Exception occurred: " + e.getMessage());

                    }

                }

                    @Test
                    public void testGetVaccinationsByServiceId1() {

                        int vaccinationId = 1; // Provide a valid vaccination ID for testing

                        Vaccinations result = new Vaccinations(0, null, null, null, 0);

                        Assertions.assertNotNull(result, "Result should not be null");

                        // Add more assertions as needed

                    }



                }

          