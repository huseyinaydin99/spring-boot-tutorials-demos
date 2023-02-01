package tr.com.huseyinaydin.model;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class Image {
	private Integer id;
	private String name;
	private String imagePath;
	private String base64ImageStringData;

	public Image() {
	}

	public Image(String name, String base64ImageStringData) {
		this.name = name;
		this.base64ImageStringData = base64ImageStringData;
	}

	public Image(String name, String imagePath, String base64ImageStringData) {
		this.name = name;
		this.imagePath = imagePath;
		this.base64ImageStringData = base64ImageStringData;
	}

	public Image(Integer id, String name, String imagePath, String base64ImageStringData) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.base64ImageStringData = base64ImageStringData;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getBase64ImageStringData() {
		return base64ImageStringData;
	}

	public void setBase64ImageStringData(String base64ImageStringData) {
		this.base64ImageStringData = base64ImageStringData;
	}

}