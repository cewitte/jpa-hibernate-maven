package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Person;

public class Program {

	public static void main(String[] args) {
		Person p1 = new Person(null, "Mama John", "mama@cano.com");
		Person p2 = new Person(null, "Papa John", "papa@cano.com");
		Person p3 = new Person(null, "Sunny John", "son@cano.com");
		
		// Insert. Whenever an HPA operation changes state, it has to be sorrounded in a transaction.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMaven");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		
		System.out.println("p1, p2 and p3 inserted and commited.");
		
		// Delete operation.
		// Monitored entities has to be "just inserted" or recovered from the database before removal.
		// And since it changes state, also wrapped in a transaction.
		int id = 1;
		em.getTransaction().begin();
		em.remove(em.find(Person.class, id));
		em.getTransaction().commit();
		System.out.println("Person of id " + id + " removed.");
		
		
		Person p4 = em.find(Person.class, 2);
		System.out.println("Person (p4) retrieved from the database:");
		System.out.println(p4);
		
		
		// Always close resources.
		em.close();
		emf.close();
	}

}
