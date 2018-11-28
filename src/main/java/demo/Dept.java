package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="depttable")
public class Dept {

		/*@Id
		@GeneratedValue(strategy=GenerationType.AUTO,generator="mygen")
		@SequenceGenerator(name="mygen",sequenceName="mysequence",initialValue=100,allocationSize=5)
		private int deptno;*/
		
		@Id
		//@GeneratedValue(strategy=GenerationType.TABLE,generator="mytabgen")
		//@TableGenerator(name="mytabgen",table="pks",pkColumnName="pkcolname",valueColumnName="currvalue")
		@Column(name="deptnocol")
		private int deptno;
	
		@Column(name="dnamecol")
		private String dname;
		
		@Column(name="loccol")
		private String loc;

		public int getDeptno() {
			return deptno;
		}

		public void setDeptno(int deptno) {
			this.deptno = deptno;
		}

		public String getDname() {
			return dname;
		}

		public void setDname(String dname) {
			this.dname = dname;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}
	
}
