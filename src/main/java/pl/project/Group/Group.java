package pl.project.Group;

import pl.project.User.User;

import javax.persistence.*;

@Entity
@Table(name = "groups", schema = "public", catalog = "d9h3r67ca39jah")
public class Group {
    private int id;
    private String name;
    private User founder;

    public Group() {
    }

    public Group(int id, String name, User founder) {
        this.id = id;
        this.name = name;
        this.founder = founder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group that = (Group) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "founder_id", referencedColumnName = "id")
    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }
}
