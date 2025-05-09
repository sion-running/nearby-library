package com.example.library;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Long> {

    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(latitude)) * " +
            "cos(radians(longitude) - radians(:lng)) + " +
            "sin(radians(:lat)) * sin(radians(latitude)))) AS distance " +
            "FROM library_entity " +
            "HAVING distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<LibraryEntity> findNearbyLibraries(@Param("lat") double lat,
                                            @Param("lng") double lng,
                                            @Param("radius") double radiusKm);
}
