package pl.project.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroup() {
        List<Group> groupList = new ArrayList<>();
        groupRepository.findAll().forEach(groupList::add);
        return groupList;
    }

    public Group getGroup(Integer id) {
        Group result = groupRepository.findById(id).get();
        return result;
    }


    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    public void updateGroup(Integer id, Group group) {
        groupRepository.save(group);
    }


    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }
}
