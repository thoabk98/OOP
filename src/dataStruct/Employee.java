package dataStruct;

public class Employee {
    public int employeeID;
    public String employeeName;
    public int numOfShift;
    public int payPerShift;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getNumOfShift() {
        return numOfShift;
    }

    public void setNumOfShift(int numOfShift) {
        this.numOfShift = numOfShift;
    }

    public int getPayPerShift() {
        return payPerShift;
    }

    public void setPayPerShift(int payPerShift) {
        this.payPerShift = payPerShift;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
