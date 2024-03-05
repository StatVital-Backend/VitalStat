package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.dtos.request.ChildRequest;
import com.statvital.StatVital.dtos.request.DeleteChildReq;
import com.statvital.StatVital.dtos.request.SearchChildReq;

import java.util.Optional;

public interface ChildService {
    void registerChild(ChildRequest childRequest);

    void deleteProfile(DeleteChildReq deleteRequest);

    Optional<Child> findChild (String name);
}
