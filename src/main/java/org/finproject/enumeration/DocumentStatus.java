package org.finproject.enumeration;

public enum DocumentStatus {

    INCOMING("Incoming"),
    OUTGOING("Outgoing");

    private String status;

    DocumentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
