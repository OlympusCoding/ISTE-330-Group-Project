package types;

public class StatsType {
    private int faculty;
    private int students;
    private int community;

    public StatsType(int faculty, int students, int community) {
        this.faculty = faculty;
        this.students = students;
        this.community = community;
    }

    public String getFaculty() {
        return Integer.toString(faculty);
    }

    public String getStudents() {
        return Integer.toString(students);
    }

    public String getCommunity() {
        return Integer.toString(community);
    }
}
