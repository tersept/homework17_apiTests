package apitests;

public class UserNameJobData {
    private String name;
    private String job;


    public UserNameJobData(String name, String job) {
        this.name = name;
        this.job = job;
    }


    public UserNameJobData setName(String name) {
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
