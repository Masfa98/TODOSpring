package org.udg.pds.springtodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.udg.pds.springtodo.controller.exceptions.ServiceException;
import org.udg.pds.springtodo.entity.Group;
import org.udg.pds.springtodo.entity.User;
import org.udg.pds.springtodo.repository.GroupRepository;

import java.util.Optional;

public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    UserService userService;

    public Group createGroup (String nom, String descripcio){
        Group g = new Group(nom,descripcio);
        groupRepository.save(g);
        return g;
    }

    public void addUserToGroup(Long userId,Long groupId){
        User u = userService.getUser(userId);
        Group g = this.getGroup(groupId);
        g.addUser(u);
    }

    public Group getGroup(Long groupId){
        Optional<Group> gp = groupRepository.findById(groupId);
        if (gp.isPresent())
            return gp.get();
        else
            throw new ServiceException(String.format("User with id = % dos not exists", groupId));
    }

    public Long getId(Group g){
        return g.getId();
    }

}
