package com.soniq.controller;

import com.soniq.model.CleanerRequest;
import com.soniq.model.Allocation;
import com.soniq.service.CleanerAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CleanCapacityController {

    private final CleanerAllocationService cleanerAllocationService;

    @Autowired
    public CleanCapacityController(CleanerAllocationService cleanerAllocationService) {
        this.cleanerAllocationService = cleanerAllocationService;
    }

    @RequestMapping(value = "/optimize", method = RequestMethod.POST)
    public List<Allocation> optimizeCleaners(@RequestBody CleanerRequest cleanerRequest) {
        return cleanerAllocationService.optimizeAllocation(cleanerRequest);
    }
}
