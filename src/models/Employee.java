package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "employee")

@NamedQueries({
	//すべての従業員データを取得
    @NamedQuery(
            name = "getAllEmployees",
            query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
            ),
    //データベースにメッセージのデータが何件入っているか」を知るためのJPQL
    //従業員情報数の全件数を取得
    @NamedQuery(
            name = "getEmployeesCount",
            query = "SELECT COUNT(e) FROM Employee AS e"
            ),
    //指定された従業員番号が既にデータベースに存在しているか
    @NamedQuery(
            name = "checkRegisteredCode",
            query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"
            ),
    //ログイン時
    //社員番号とパスワードが正しいか
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass"
            )
})

@Entity

public class Employee {

	    @Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    // unique=true
	    //一意制約　既に存在している社員番号は登録できない
	    @Column(name = "code", nullable = false, unique = true)
	    private String code;

	    @Column(name = "name",  nullable = false)
	    private String name;

	    @Column(name = "password", length = 64, nullable = false)
	    private String password;

	    @Column(name = "admin_flag",  nullable = false)
	    private Integer admin_flag;

	    @Column(name = "created_at", nullable = false)
	    private Timestamp created_at;

	    @Column(name = "updated_at", nullable = false)
	    private Timestamp updated_at;

	    @Column(name = "delete_flag", nullable = false)
	    private Integer delete_flag;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getAdmin_flag() {
			return admin_flag;
		}

		public void setAdmin_flag(Integer admin_flag) {
			this.admin_flag = admin_flag;
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

		public Integer getDelete_flag() {
			return delete_flag;
		}

		public void setDelete_flag(Integer delete_flag) {
			this.delete_flag = delete_flag;
		}


}
