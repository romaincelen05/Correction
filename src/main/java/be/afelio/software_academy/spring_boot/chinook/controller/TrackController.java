package be.afelio.software_academy.spring_boot.chinook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDtoStatus;
import be.afelio.software_academy.spring_boot.chinook.api.dto.TrackDto;
import be.afelio.software_academy.spring_boot.chinook.persistence.ApplicationRepository;

@Controller
@RequestMapping(value="track")
public class TrackController {

	@Autowired ApplicationRepository repository;
	
	@GetMapping(value="album/{title}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<TrackDto>>> findAllByAlbumTitle(
			@PathVariable("title") String title) {
		
		ResponseDto<List<TrackDto>> dto = null;
		
		try {
			List<TrackDto> album = repository.findAllTrackDtoByAlbumTitle(title);
			
			if ( album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, " No album found");
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, album.size() + " tracks found");
				dto.setPayload(album);
			}
			
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "Unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
}
