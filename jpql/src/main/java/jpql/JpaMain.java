package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(78);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select " +
                                "case when m.age <= 10 then '학생요금' " +
                                "     when m.age >= 60 then '경로요금' " +
                                "     else '일반요금' " +
                                "end " +
                            "from Member m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String str : result ) {
                System.out.println("str = " + str);
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);

        } finally {
            em.close();
        }
        emf.close();
    }
}
