package com.soniq.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CleanerRequest {
    private int[] rooms;
    private int senior;
    private int junior;
}
