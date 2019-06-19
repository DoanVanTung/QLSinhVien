package Model;

public class Certificate {
	int maSV;
	String maMH;
	String tenSV;
	String tenMH;
	float diem_1;
	float diem_2;

	public int getMaSV() {
		return maSV;
	}

	public void setMaSV(int maSV) {
		this.maSV = maSV;
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenSV() {
		return tenSV;
	}

	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public float getDiem_1() {
		return diem_1;
	}

	public void setDiem_1(float diem_1) {
		this.diem_1 = diem_1;
	}

	public float getDiem_2() {
		return diem_2;
	}

	public void setDiem_2(float diem_2) {
		this.diem_2 = diem_2;
	}

	public Certificate(String maMH, int maSV, String tenSV, String tenMH, float diem_1, float diem_2) {
		super();
		this.maMH = maMH;
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.tenMH = tenMH;
		this.diem_1 = diem_1;
		this.diem_2 = diem_2;
	}

	public Certificate() {
		super();
	}

}
