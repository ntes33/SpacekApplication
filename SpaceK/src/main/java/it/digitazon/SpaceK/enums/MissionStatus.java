package it.digitazon.SpaceK.enums;

public enum MissionStatus {
    PLANNED, PROGRESS, COMPLETED, CANCELLED;
/*
    public static MissionStatus fromString(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        for (MissionStatus missionStatus : MissionStatus.values()) {
            if (missionStatus.name().equalsIgnoreCase(status)) {
                return missionStatus;
            }
        }
        throw new IllegalArgumentException("Invalid mission status: " + status);
    }
    */
}

