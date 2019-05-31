package dataStruct;

public class MonthlyRevenue {
    public String timeID;
    public double employeeExpence;
    public double otherExpence;
    public double revenue;

    public String getTimeID() {
        return timeID;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public int getEmployeeExpence() {
        return (int) employeeExpence;
    }

    public void setEmployeeExpence(double employeeExpence) {
        this.employeeExpence = employeeExpence;
    }

    public int getOtherExpence() {
        return (int) otherExpence;
    }

    public void setOtherExpence(double otherExpence) {
        this.otherExpence = otherExpence;
    }

    public int getRevenue() {
        return (int) revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
