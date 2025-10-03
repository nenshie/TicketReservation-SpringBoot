package com.nevena.repository.views;

public interface OccupiedSeatView {
    Long getSeatId();
    Integer getRowNumber();
    Integer getSeatNumber();
    Boolean getIsTaken();
}
