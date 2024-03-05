package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.repository.ChildRepository;
import com.statvital.StatVital.dtos.request.ChildRequest;
import com.statvital.StatVital.dtos.request.DeleteChildReq;
import com.statvital.StatVital.dtos.request.SearchChildReq;
import com.statvital.StatVital.exceptions.ChildNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.statvital.StatVital.utils.Mapper.mapChild;

@Service
@AllArgsConstructor
public class ChildServiceImpl implements ChildService{

    public final ChildRepository childRepository;
    @Override
    public void registerChild(ChildRequest childRequest) {
        Child child = mapChild(childRequest);
        Child savedChild = childRepository.save(child);
        System.out.println(savedChild);
    }

    @Override
    public void deleteProfile(DeleteChildReq deleteRequest) {
        Child child = getChild(deleteRequest.getChildName());
        childRepository.delete(child);
    }

    @Override
    public Optional<Child> findChild(String name) {
        return childRepository.findChildByName(name);

    }



    private Child getChild(String childName) {
        Optional<Child> child = childRepository.findChildByName(childName);
        if(child.isEmpty()){
            throw new ChildNotFound("Child Not Found");
        }

        return child.get();
    }
}
