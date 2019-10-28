package be.afelio.software_academy.spring_boot.chinook.api.dto;

import java.util.List;

import be.afelio.software_academy.spring_boot.chinook.persistence.entities.TrackEntity;

public class CreateAlbumDto {

	private String title;
	private String artistName;
	private List<TrackEntity> trackList;
	
	public CreateAlbumDto() {}

	public CreateAlbumDto(String title, String artistName, List<TrackEntity> trackList) {
		super();
		this.title = title;
		this.artistName = artistName;
		this.trackList = trackList;
	}

	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public List<TrackEntity> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<TrackEntity> trackList) {
		this.trackList = trackList;
	}
	
}
