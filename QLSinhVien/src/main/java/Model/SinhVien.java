package Model;

public class SinhVien {
	private int maSinhvien;
	private String tenSinhvien;
	private int tuoi;
	public SinhVien(int maSinhvien, String tenSinhvien, int tuoi) {
		super();
		this.maSinhvien = maSinhvien;
		this.tenSinhvien = tenSinhvien;
		this.tuoi = tuoi;
	}
	public int getMaSinhvien() {
		return maSinhvien;
	}
	public void setMaSinhvien(int maSinhvien) {
		this.maSinhvien = maSinhvien;
	}
	public String getTenSinhvien() {
		return tenSinhvien;
	}
	public void setTenSinhvien(String tenSinhvien) {
		this.tenSinhvien = tenSinhvien;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
}
