package be.afelio.software_academy.spring_boot.chinook.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.software_academy.spring_boot.chinook.persistence.entities.TrackEntity;

public interface TrackRepository extends JpaRepository<TrackEntity, Integer> {

	List<TrackEntity> findAllByAlbumTitleIgnoreCase(String title);

}
