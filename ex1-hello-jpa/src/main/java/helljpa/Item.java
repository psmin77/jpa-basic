package helljpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
public class Item {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;

}
