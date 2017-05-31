package tfm.marshaller.data;

import tfm.file.FieldName;

public class TestFile {
	
	@FieldName(name="CODENAME")
	private String codename;
	
	@FieldName(name="GENDER")
	private String gender;
	
	@FieldName(name="RANK")
	private String rank;
	
	@FieldName(name="MISS_COUNT")
	private String missCount;
	
	@FieldName(name="HIT_COUNT")
	private String hitCount;

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMissCount() {
		return missCount;
	}

	public void setMissCount(String missCount) {
		this.missCount = missCount;
	}

	public String getHitCount() {
		return hitCount;
	}

	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}

}
