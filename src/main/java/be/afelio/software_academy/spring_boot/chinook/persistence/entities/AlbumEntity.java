package be.afelio.software_academy.spring_boot.chinook.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Album")
@Table(name="album")
public class AlbumEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="albumid") 
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="artistid")
	private ArtistEntity artist;
	
	@OneToMany(mappedBy="album", fetch=FetchType.EAGER)
	private List<TrackEntity> trackList;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArtistEntity getArtist() {
		return artist;
	}

	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}

	public List<TrackEntity> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<TrackEntity> trackList) {
		this.trackList = trackList;
	}
	
}
