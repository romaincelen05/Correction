package be.afelio.software_academy.spring_boot.chinook.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.software_academy.spring_boot.chinook.persistence.entities.ArtistEntity;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {

	ArtistEntity findOneByNameIgnoreCase(String name);

}
