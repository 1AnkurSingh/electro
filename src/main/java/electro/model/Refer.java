package electro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Refer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int SerialNo;

    private String Name;

    private String status;




}
