package com.example.EIRLS_Banger.Repositories;


import com.example.EIRLS_Banger.Models.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {
//    boolean existsByAccessoryId(Integer accessoryId);
}
