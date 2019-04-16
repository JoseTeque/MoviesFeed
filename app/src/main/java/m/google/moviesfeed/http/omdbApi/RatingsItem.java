package m.google.moviesfeed.http.omdbApi;


import com.google.gson.annotations.SerializedName;


public class RatingsItem{

	@SerializedName("Value")
	private String value;

	@SerializedName("Source")
	private String source;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	@Override
 	public String toString(){
		return 
			"RatingsItem{" + 
			"value = '" + value + '\'' + 
			",source = '" + source + '\'' + 
			"}";
		}
}