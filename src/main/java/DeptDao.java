import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.jandex.Main;

import demo.Dept;
import util.HibernateUtil;

public class DeptDao {

	private static SessionFactory sf=HibernateUtil.getSessionFactory();
	
	public static void main(String[] args) {
		DeptDao dao= new DeptDao();
		
		for(int i=10;i<100;i+=10){
			Dept d=new Dept();
			d.setDeptno(i);
			d.setDname("IT");
			if(i%20==0)
				d.setLoc("pune");
			else
				d.setLoc("Hyd");
			
			dao.insert(d);
		}
		//dao.list();
		/*dao.delete(10);
		dao.update(20, "Finance", "Mumbai");*/
		sf.close();
	}

	public void insert(Dept d){
		Session session=null;
		Transaction tx=null;
		try {
			session=sf.openSession();
			tx=session.beginTransaction();
			// d is transient
			System.out.println(session.save(d));
			//d is persistent
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception+
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	//Delete operation
	
	public void delete(int deptno){
		Session session=null;
		Transaction tx=null;
		try {
			session=sf.openSession();
			tx=session.beginTransaction();
			//Dept d=new Dept();
			//d.setDeptno(deptno);
			Dept d1=session.get(Dept.class, deptno);
			//d1.getDname();
			System.out.println("get"+d1);
			if(d1 != null){
				session.delete(d1);
				tx.commit();
			}
			else
			{				
				System.out.println("dno not found");
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception+
			tx.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public void update(int deptno,String newdname,String newloc){
		Session session=null;
		Transaction tx=null;
		try {
			session=sf.openSession();
			tx=session.beginTransaction();
			
			Dept d= session.get(Dept.class,deptno);

			if(d!=null)
			{
				d.setDname(newdname);
				d.setLoc(newloc);
			}
			
			tx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception+
			tx.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public void list(){
		Session session=null;
		//Transaction tx=null;
		try {
			session=sf.openSession();
			
			Query<Dept> query=session.createQuery("select d from Dept d");
			List<Dept> list=query.list();
			for(Dept d:list){
				System.out.println(d.getDname());
			}
			
			Query<Dept> query1=session.createQuery("select d from Dept d where deptno>40");
			List<Dept> list1=query1.list();
			for(Dept d:list1){
				System.out.println(d.getDeptno());
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception+
			System.out.println(e);
		}
		finally{
			session.close();
		}
	}
	}
