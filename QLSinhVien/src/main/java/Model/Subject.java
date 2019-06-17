package Model;

public class Subject {

	private int subjectId;
	private int semesterId;
	private String subjectName;
	private int studentId;
	private String studentName;
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Subject() {
		super();
	}
	public Subject(int subjectId, int semesterId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.semesterId = semesterId;
		this.subjectName = subjectName;
	}
	public Subject(int subjectId, int semesterId, String subjectName, int studentId, String studentName) {
		super();
		this.subjectId = subjectId;
		this.semesterId = semesterId;
		this.subjectName = subjectName;
		this.studentId = studentId;
		this.studentName = studentName;
	}
}
