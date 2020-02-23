package com.soniq.service;

import com.soniq.exceptions.ValidationException;
import com.soniq.model.Allocation;
import com.soniq.model.CleanerRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CleanerAllocationServiceTest {

    @Test
    public void testOptimizationService() {
        CleanerAllocationService cco = new CleanerAllocationService();

        CleanerRequest cr = CleanerRequest.builder()
                .rooms(new int[] {35, 21, 17, 28})
                .junior(6)
                .senior(10)
                .build();


        List<Allocation> allocations = cco.optimizeAllocation(cr);
        assertEquals(3, allocations.get(0).getSenior());
        assertEquals(1, allocations.get(0).getJunior());
        assertEquals(1, allocations.get(1).getSenior());
        assertEquals(2, allocations.get(1).getJunior());
        assertEquals(2, allocations.get(2).getSenior());
        assertEquals(0, allocations.get(2).getJunior());
        assertEquals(1, allocations.get(3).getSenior());
        assertEquals(3, allocations.get(3).getJunior());


        cr = CleanerRequest.builder()
                .rooms(new int[] {24, 28})
                .junior(6)
                .senior(11)
                .build();

        allocations = cco.optimizeAllocation(cr);
        assertEquals(2, allocations.get(0).getSenior());
        assertEquals(1, allocations.get(0).getJunior());
        assertEquals(2, allocations.get(1).getSenior());
        assertEquals(1, allocations.get(1).getJunior());


        cr = CleanerRequest.builder()
                .rooms(new int[] {0, 49})
                .junior(3)
                .senior(17)
                .build();

        allocations = cco.optimizeAllocation(cr);
        assertEquals(0, allocations.get(0).getSenior());
        assertEquals(0, allocations.get(0).getJunior());
        assertEquals(2, allocations.get(1).getSenior());
        assertEquals(5, allocations.get(1).getJunior());
    }

    @Test(expected = ValidationException.class)
    public void testCapacityValidation() {
        CleanerAllocationService cco = new CleanerAllocationService();

        CleanerRequest cr = CleanerRequest.builder()
                .rooms(new int[] {0, 49})
                .junior(-1)
                .senior(-1)
                .build();
        cco.optimizeAllocation(cr);
    }

    @Test(expected = ValidationException.class)
    public void testArraySizeValidation() {
        CleanerAllocationService cco = new CleanerAllocationService();

        CleanerRequest cr = CleanerRequest.builder()
                .rooms(new int[101])
                .junior(10)
                .senior(5)
                .build();
        cco.optimizeAllocation(cr);
    }
}
