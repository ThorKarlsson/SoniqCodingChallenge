package com.soniq.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Allocation implements Comparable<Allocation> {
    private int senior;
    private int junior;
    private int score;

    @Override
    public int compareTo(Allocation a) {
        return this.score - a.score;
    }
}
