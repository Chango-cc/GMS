package edu.gdou.equipmentmanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ereservation {
    private String eno;
    private String etype;
    private String estartrent;
    private String eendrent;
    private String userid;
    private String matchid;

}
