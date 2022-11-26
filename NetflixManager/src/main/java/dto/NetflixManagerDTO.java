package dto;

import java.sql.Timestamp;

public class NetflixManagerDTO {
	private int id;
	private String title;
	private String genre;
	private Timestamp launch_date;
	
	public NetflixManagerDTO(int id, String title, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
	}
	
	public NetflixManagerDTO(int id, String title, String genre, Timestamp launch_date) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.launch_date = launch_date;
	}
	
	public NetflixManagerDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Timestamp getLaunch_date() {
		return launch_date;
	}
	public void setLaunch_date(Timestamp launch_date) {
		this.launch_date = launch_date;
	}
	
}
