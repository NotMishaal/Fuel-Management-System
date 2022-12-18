package model.staff;

public class Staff {
    private String staffID;
    private String name;

    public Staff(String staffID, String name) {
        this.staffID = staffID;
        this.name = name;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
