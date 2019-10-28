package be.afelio.software_academy.spring_boot.chinook.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.CreateAlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.TrackDto;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.ArtistNotFoundException;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.DuplicatedAlbumException;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.InvalidCreateParametersException;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.AlbumEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.ArtistEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.TrackEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.AlbumRepository;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.ArtistRepository;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.TrackRepository;


@Component
public class ApplicationRepository {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationRepository.class);

	@Autowired AlbumRepository albumRepository;
	@Autowired TrackRepository trackRepository;
	@Autowired ArtistRepository artistRepository;
	
	
	private AlbumDto createAlbumDto(AlbumEntity entity) {
		AlbumDto album = null;
		if(entity != null) {
			album = new AlbumDto(
				entity.getId(),
				entity.getTitle(),
				entity.getArtist().getName(),
				transformTrackListIntoStringTab(entity.getTrackList())
				);
		}
		return album;
	}
	
	private String[] transformTrackListIntoStringTab(List<TrackEntity> trackList) {
		
		Set<String> list = new HashSet<>();
		
		for (TrackEntity track : trackList) {
			list.add(track.getGenre().getName());
			log.debug("");
		}
		
		
		String[] tab = new String[list.size()];
		int cpt = 0;
		
		for(String genreName: list) {
			
			tab[cpt]= genreName;
			cpt++;
		}
		return tab;
	}
	
	private TrackDto createTrackDto(TrackEntity entity) {
		TrackDto track = null;
		if(entity != null) {
			track = new TrackDto(
				entity.getId(),
				entity.getName(),
				entity.getAlbum().getTitle(),
				entity.getMedia().getName(),
				entity.getGenre().getName(),
				entity.getComposer(),
				(entity.getMillisecond()/1000),
				entity.getBytes(),
				entity.getUnitprice()
				);
		}
		return track;
	}
	
	public List<AlbumDto> findAllAlbumDto() {
		List<AlbumEntity> albumList = albumRepository.findAll();
		List<AlbumDto> listDto = new ArrayList<>();
		
        for(AlbumEntity album : albumList) {
            listDto.add(createAlbumDto(album));
        }
        return listDto;
	}

	public List<AlbumDto> findAllAlbumDtoByArtistName(String artistName) {
		
		List<AlbumEntity> albumList = albumRepository.findAllByArtistNameIgnoreCase(artistName);
		List<AlbumDto> listDto = new ArrayList<>();
		
        for(AlbumEntity album : albumList) {
            listDto.add(createAlbumDto(album));
        }
        return listDto;
	}
	
	public List<AlbumDto> findAllAlbumDtoByGenreName(String genreName) {

		List<AlbumDto> allAlbumDto = findAllAlbumDto();
		List<AlbumDto> listDto = new ArrayList<>();
		int cpt = 0;
			
        for(AlbumDto album : allAlbumDto) {
        	
        	for (cpt = 0; cpt < album.getGenreNameList().length; cpt++) {
        		
				if(genreName.equalsIgnoreCase(album.getGenreNameList()[cpt]) ) {
        		
	        		listDto.add(album);
	        	}
			}
        }
        return listDto;
	}

	public List<TrackDto> findAllTrackDtoByAlbumTitle(String title) {
		List<TrackEntity> trackList = trackRepository.findAllByAlbumTitleIgnoreCase(title);
		List<TrackDto> listDto = new ArrayList<>();
		
        for(TrackEntity track : trackList) {
            listDto.add(createTrackDto(track));
        }
        return listDto;
	}

	public void createAlbum(CreateAlbumDto createAlbumDto) {
		
		if (!validateCreateAlbumParameters(createAlbumDto)) {
			throw new InvalidCreateParametersException();
		}
		
		if (albumRepository.findOneByTitleIgnoreCase(createAlbumDto.getTitle()) != null) {
			throw new DuplicatedAlbumException();
		}
		
		ArtistEntity artist = artistRepository.findOneByNameIgnoreCase(createAlbumDto.getArtistName());
		if (artist == null) {
			throw new ArtistNotFoundException();
		}
		
		AlbumEntity album = new AlbumEntity();
		album.setTitle(createAlbumDto.getTitle());
		album.setArtist(artist);
		album.setTrackList(createAlbumDto.getTrackList()); // -> PAS FINI
		
		albumRepository.save(album);
	}
	
	private boolean validateCreateAlbumParameters(CreateAlbumDto dto) {
		String title = dto.getTitle();
		String artist = dto.getArtistName();
		List<TrackEntity> trackList = dto.getTrackList();
		
		return title != null && !title.isBlank()
			&& artist != null && !artist.isBlank()
			&& trackList != null;
	}
	
}
