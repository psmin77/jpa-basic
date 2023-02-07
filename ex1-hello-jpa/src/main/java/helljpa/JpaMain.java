package helljpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "1000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "2000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "3000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========= START ===========");
            Member findMember = em.find(Member.class, member.getId());

//            // 주소 변경
//            Address old = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", old.getStreet(), old.getZipcode()));
//
//            // 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
//            // 주소 히스토리 수정
//            findMember.getAddressHistory().add(new Address("old1", "street", "2000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "2000"));

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
