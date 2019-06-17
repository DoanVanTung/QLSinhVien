package Model;

public class Certificate {
	int masv;
	String mamh;
	String tensv;
	String tenmh;
	float diem_1;
	float diem_2;

	public int getMasv() {
		return masv;
	}

	public void setMasv(int masv) {
		this.masv = masv;
	}

	public String getMamh() {
		return mamh;
	}

	public void setMamh(String mamh) {
		this.mamh = mamh;
	}

	public String getTensv() {
		return tensv;
	}

	public void setTensv(String tensv) {
		this.tensv = tensv;
	}

	public String getTenmh() {
		return tenmh;
	}

	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
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

	public Certificate(String mamh,int masv, String tensv, String tenmh, float diem_1, float diem_2) {
		super();
		this.mamh = mamh;
		this.masv = masv;
		this.tensv = tensv;
		this.tenmh = tenmh;
		this.diem_1 = diem_1;
		this.diem_2 = diem_2;
	}

	public Certificate() {
		super();
	}

}
