package top.meethigher;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PlanTime {

    @Id
    String timeId;

    String startTime;

    String endTime;

    String planId;
}
