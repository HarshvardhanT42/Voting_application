class Voter {
    private String name, rollNumber, birthdate, password;

    public Voter(String name, String rollNumber, String birthdate, String password) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.birthdate = birthdate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getPassword() {
        return password;
    }
}
