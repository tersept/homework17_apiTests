package apitests;

public class UsersCreat {
    private String name;
    private String job;


    public UsersCreat(String name, String job) {
        this.name = name;
        this.job = job;
    }


    public UsersCreat setName(String name) {
        this.name = name;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
