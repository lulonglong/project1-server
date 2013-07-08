package locationshare.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;

	private HibernateUtil() {
	}

	static {
		Configuration cf = new Configuration();
		cf.configure();
		sf = cf.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}

	public static Session getSession() {
		Session session=sf.openSession();
		System.out.println("isopen:"+session.isOpen());
		System.out.println("isConnected:"+session.isConnected());
		return session;
	}
}
