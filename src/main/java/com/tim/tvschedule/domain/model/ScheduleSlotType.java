package com.tim.tvschedule.domain.model;

public enum ScheduleSlotType {
    FEATURE_MOVIE(1),
    TV_SHOW_PRIMARY(2),
    TV_SHOW_SECONDARY(3);

    private final int displayOrder;

    ScheduleSlotType(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int displayOrder() {
        return displayOrder;
    }
}