package be.afelio.software_academy.spring_boot.chinook.api.dto;

public class AlbumDto {

	private Integer id;
	private String title;
	private String artistName;
	private String[] genreNameList;
	
	public AlbumDto() {}

	public AlbumDto(Integer id, String name, String artistName, String[] genreNameList) {
		super();
		this.id = id;
		this.title = name;
		this.artistName = artistName;
		this.genreNameList = genreNameList;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getArtist() {
		return artistName;
	}

	public void setArtist(String artistName) {
		this.artistName = artistName;
	}

	public String[] getGenreNameList() {
		return genreNameList;
	}

	public void setGenreNameList(String[] genreNameList) {
		this.genreNameList = genreNameList;
	}
	
}
