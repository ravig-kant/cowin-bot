public class Appointment {
    private String name;
    private String vaccine;
    private int minAge;
    private int numberOfDoses1;
    private int numberOfDoses2;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getNumberOfDoses1() {
        return numberOfDoses1;
    }

    public void setNumberOfDoses1(int numberOfDoses1) {
        this.numberOfDoses1 = numberOfDoses1;
    }

    public int getNumberOfDoses2() {
        return numberOfDoses2;
    }

    public void setNumberOfDoses2(int numberOfDoses2) {
        this.numberOfDoses2 = numberOfDoses2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String message(){
        StringBuilder builder = new StringBuilder("Appointment available at ")
                                    .append(name)
                                    .append(" for age ")
                                    .append( minAge != 45 ? "18 to 44 " : "45 and above ")
                                    .append("on ")
                                    .append(date)
                                    .append(". ")
                                    .append("Number of available doses of ")
                                    .append(vaccine)
                                    .append(" are [dose1 : ")
                                    .append(numberOfDoses1)
                                    .append("] and [dose2 : ")
                                    .append(numberOfDoses2)
                                    .append("].\n")
                                    .append("Register at : ")
                                    .append("https://selfregistration.cowin.gov.in/");
        return builder.toString();
    }

    public static class Builder{
        private Appointment appointment;
        public Builder(){
            appointment = new Appointment();
        }

        public Builder name(String name){
            appointment.name = name;
            return this;
        }

        public Builder vaccine(String vaccine){
            appointment.vaccine = vaccine;
            return this;
        }

        public Builder minAge(int minAge){
            appointment.minAge = minAge;
            return this;
        }

        public Builder numberOfDoses1(int numberOfDoses){
            appointment.numberOfDoses1 = numberOfDoses;
            return this;
        }

        public Builder numberOfDoses2(int numberOfDoses){
            appointment.numberOfDoses2 = numberOfDoses;
            return this;
        }

        public Builder date(String date){
            appointment.date = date;
            return this;
        }

        public Builder slot(String slot){
            return this;
        }

        public Appointment build() {
            return appointment;
        }
    }
}
