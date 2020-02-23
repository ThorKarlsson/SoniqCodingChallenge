package com.soniq.service;

import com.soniq.exceptions.ValidationException;
import com.soniq.model.CleanerRequest;
import com.soniq.model.Allocation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CleanerAllocationService {

    /***
     * Optimizes the allocation of cleaners per structure based on the cleaning capacity of junior and senior cleaners.
     * @param cleanerRequest
     * @return List<Allocation> returns a list of Allocations.
     * @exception ValidationException thrown if CleanerRequest configuration does not match spec.
     */
    public List<Allocation> optimizeAllocation(CleanerRequest cleanerRequest) {
        validateRequest(cleanerRequest);

        return Arrays.stream(cleanerRequest.getRooms())
                .mapToObj(room -> getOptimizationForRoom(room, 0, 0, cleanerRequest))
                .collect(Collectors.toList());
    }

    private void validateRequest(CleanerRequest cleanerRequest) {
        if(cleanerRequest.getSenior() <= 0 || cleanerRequest.getJunior() <= 0) {
            throw new ValidationException("Junior and Senior capacities must be greater than zero");
        }
        if(cleanerRequest.getRooms().length > 100) {
            throw new ValidationException("Structural array cannot exceed 100");
        }
    }

    private Allocation getOptimizationForRoom(int room, int juniors, int seniors, CleanerRequest cleanerRequest) {
        int juniorCapacity = cleanerRequest.getJunior();
        int seniorCapacity = cleanerRequest.getSenior();
        if(room <= 0) {
            return Allocation.builder()
                    .junior(juniors)
                    .senior(seniors)
                    .score(seniors == 0 ? Integer.MIN_VALUE : room)
                    .build();
        }

        Allocation all1 = getOptimizationForRoom(room - juniorCapacity, juniors + 1, seniors, cleanerRequest);
        Allocation all2 = getOptimizationForRoom(room - seniorCapacity, juniors, seniors + 1, cleanerRequest);

        return all1.compareTo(all2) >= 0 ? all1 : all2;
    }
}
