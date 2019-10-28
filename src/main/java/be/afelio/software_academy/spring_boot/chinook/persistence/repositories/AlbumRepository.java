package be.afelio.software_academy.spring_boot.chinook.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.software_academy.spring_boot.chinook.persistence.entities.AlbumEntity;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Integer> {

	List<AlbumEntity> findAllByArtistNameIgnoreCase(String artistName);
	
	AlbumEntity findOneByTitleIgnoreCase(String title);

}
