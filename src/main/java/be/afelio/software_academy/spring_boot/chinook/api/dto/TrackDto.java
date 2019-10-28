package be.afelio.software_academy.spring_boot.chinook.api.dto;

public class TrackDto {

	private Integer id;
	private String name;
	private String albumTitle;
	private String mediaName;
	private String genreName;
	private String composer;
	private Integer millisecond;
	private Integer bytes;
	private Integer unitprice;
	
	public TrackDto() {}
	
	public TrackDto(Integer id, String name, String albumTitle, String mediaName, String genreName, String composer,
			Integer millisecond, Integer bytes, Integer unitprice) {
		super();
		this.id = id;
		this.name = name;
		this.albumTitle = albumTitle;
		this.mediaName = mediaName;
		this.genreName = genreName;
		this.composer = composer;
		this.millisecond = millisecond;
		this.bytes = bytes;
		this.unitprice = unitprice;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Integer getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(Integer millisecond) {
		this.millisecond = millisecond;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public Integer getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}
	
}
