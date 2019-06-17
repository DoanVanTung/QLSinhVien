package Model;

public class SinhVien {
	private int MaSV;
	private String TenSV;
	private int Age;
	public int getMaSV() {
		return MaSV;
	}
	public void setMaSV(int maSV) {
		MaSV = maSV;
	}
	public SinhVien() {
		super();
	}
	public SinhVien(int maSV, String tenSV, int age) {
		super();
		MaSV = maSV;
		TenSV = tenSV;
		Age = age;
	}
	public String getTenSV() {
		return TenSV;
	}
	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
}
