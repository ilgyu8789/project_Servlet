package vo;

public class ListVo {

	private Long listId;
	private String listName;
	private String listHp;
	private String listTel;

	public Long getListId() {
		return listId;
	}

	public void setlistId(Long listId) {
		this.listId = listId;
	}

	public String getlistName() {
		return listName;
	}

	public void setlistName(String listName) {
		this.listName = listName;
	}

	public String getlistHp() {
		return listHp;
	}

	public void setlistHp(String listHp) {
		this.listHp = listHp;
	}

	public String getlistTel() {
		return listTel;
	}

	public void setlistTel(String listTel) {
		this.listTel = listTel;
	}

	@Override
	public String toString() {
		return "ListVo [listId=" + listId + ", listName=" + listName + ", listHp=" + listHp + ", listTel=" + listTel
				+ "]";
	}


}
