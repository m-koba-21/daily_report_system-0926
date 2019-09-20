package models;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "reports")
@NamedQueries({
	@NamedQuery(
			name = "getAllReports",
			query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
			),
	@NamedQuery(
			name = "getReportsCount",
			query = "SELECT COUNT(r) FROM Report AS r"
			)
})

@Entity
public class Report {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//employeeフィールド＝日報作成者の情報、ログインしている従業員の情報をそのまま格納

	//Report(日報)と Employee(日報作成者)の関係は１対多
	//この日報の作成者は一人、この人が作成した日報はたくさん
	@ManyToOne
	@JoinColumn(name = "employee_id" ,nullable = false)
	private Employee employee;

	//Date Timestampの違い
	//Date　年月日のみ
	//Timestamp　年月日時分秒(ミリ秒)

	//report_date いつの仕事の日報か
	@Column(name = "report_date" , nullable = false)
	private Date report_date;

	@Column(name = "title" ,length = 255 , nullable = false)
	private String title;

	//　＠Lob　テキストエリアの指定を行う
	//テキストエリアの指定をする＝改行もデータベースに保存される
	@Lob
	@Column(name = "content" , nullable = false)
	private String content;

	@Column(name = "created_at" , nullable = false)
	private Timestamp created_at;

	@Column(name = "updated_at" , nullable = false)
	private Timestamp updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}


}