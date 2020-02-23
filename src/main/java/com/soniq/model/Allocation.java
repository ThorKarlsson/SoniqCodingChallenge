package com.soniq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Allocation implements Comparable<Allocation> {
    private int senior;
    private int junior;

    @JsonIgnore
    private int score;

    @Override
    public int compareTo(Allocation a) {
        return this.score - a.score;
    }
}
