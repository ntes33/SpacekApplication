package it.digitazon.SpaceK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.digitazon.SpaceK.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource,Long>,JpaSpecificationExecutor<Resource> {

}
