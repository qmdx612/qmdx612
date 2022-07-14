package cn.qb.store.entity;

public class UserAddress extends BaseEntity {
	
	private static final long serialVersionUID = 3132165641947159639L;
	
	private int id;
	private String name;
	private String province;
	private String city;
	private String area;
	private String district;
	private String address;
	private String zip;
	private String phone;
	private String tag;
	private int isDefault;
	private int uid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", name=" + name + ", province=" + province + ", city=" + city + ", area="
				+ area + ", district=" + district + ", address=" + address + ", zip=" + zip + ", phone=" + phone
				+ ", tag=" + tag + ", isDefault=" + isDefault + ", uid=" + uid + "]";
	}
}
