package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Production {

    public Long id;

    public String name;
    public String country;
    public String address;

    public Production(String name, String country, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

}
