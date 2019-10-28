package be.afelio.software_academy.spring_boot.chinook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.CreateAlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDtoStatus;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.ArtistNotFoundException;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.DuplicatedAlbumException;
import be.afelio.software_academy.spring_boot.chinook.api.exceptions.InvalidCreateParametersException;
import be.afelio.software_academy.spring_boot.chinook.persistence.ApplicationRepository;


@Controller
@RequestMapping(value="album")
public class AlbumController {

	@Autowired ApplicationRepository repository;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllAlbum() {
		
		ResponseDto<List<AlbumDto>> dto = null;
		
		try {
			List<AlbumDto> album = repository.findAllAlbumDto();
			
			if ( album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, " No album found");
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, album.size() + " albums found");
				dto.setPayload(album);
			}
			
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value="artist/{artistName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllAlbumByArtistName(
			@PathVariable("artistName") String artistName) {
		
		ResponseDto<List<AlbumDto>> dto = null;
		
		try {
			List<AlbumDto> album = repository.findAllAlbumDtoByArtistName(artistName);
			
			if ( album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, " No album found");
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, album.size() + " albums found");
				dto.setPayload(album);
			}
			
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value="genre/{genreName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllAlbumByGenreName(
			@PathVariable("genreName") String genreName) {
		
		ResponseDto<List<AlbumDto>> dto = null;
		
		try {
			List<AlbumDto> album = repository.findAllAlbumDtoByGenreName(genreName);
			
			if ( album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, " No album found");
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, album.size() + " albums found");
				dto.setPayload(album);
			}
			
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> createAlbum(@RequestBody CreateAlbumDto createAlbumDto) {
		ResponseDto<List<AlbumDto>> dto = null;
		
		try {
			repository.createAlbum(createAlbumDto);
			dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, "Album created");
			
		} catch(InvalidCreateParametersException e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Invalid create parameters");
			
		} catch(DuplicatedAlbumException e) {		
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Duplicated Album");
			
		} catch(ArtistNotFoundException e) {			
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Artist not found");
			
		} catch(Exception e) {			
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
		}
		return ResponseEntity.ok(dto);
	}
}
