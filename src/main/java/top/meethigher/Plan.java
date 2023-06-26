package top.meethigher;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Plan {

    @Id
    String planId;

    String planName;
}
