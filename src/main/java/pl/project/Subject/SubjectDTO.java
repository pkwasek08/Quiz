package pl.project.Subject;

public class SubjectDTO {
    private int id;
    private String name;

    public SubjectDTO() {
    }

    public SubjectDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
