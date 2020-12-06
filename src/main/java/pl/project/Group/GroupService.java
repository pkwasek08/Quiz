package pl.project.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Group> getAllGroup() {
        List<Group> groupList = new ArrayList<>();
        groupRepository.findAll().forEach(groupList::add);
        return groupList;
    }

    public List<Group> getGroupByFounderId(Integer id){
        return groupRepository.findAllByFounderId(id);
    }

    public Group getGroup(Integer id) {
        Group result = groupRepository.findById(id).get();
        return result;
    }



    public void addGroup(GroupDTO groupDTO) {
        Group group = new Group(0, groupDTO.getName(), userRepository.findById(groupDTO.getFounderId()).get());
        groupRepository.save(group);
    }

    public void updateGroup(Integer id, GroupDTO groupDTO) {
        Group group = new Group(id, groupDTO.getName(), userRepository.findById(groupDTO.getFounderId()).get());
        groupRepository.save(group);
    }


    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }
}
