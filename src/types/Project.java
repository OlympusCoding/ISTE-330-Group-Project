package types;

public class Project {
    private int projectID;
    private int userID;
    private String name;
    private String description;

    public Project(int userID, String name, String description) {
        this.userID = userID;
        this.name = name;
        this.description = description;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}