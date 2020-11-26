package pl.project.Group;

public class GroupDTO {
    private int id;
    private String name;
    private Integer founderId;

    public GroupDTO() {
    }

    public GroupDTO(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.founderId = group.getFounder().getId();
    }

    public GroupDTO(int id, String name, Integer founderId) {
        this.id = id;
        this.name = name;
        this.founderId = founderId;
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

    public Integer getFounderId() {
        return founderId;
    }

    public void setFounderId(Integer founderId) {
        this.founderId = founderId;
    }
}
