package dmacc.drama.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pamela D. Jozi
 * Date: 12/10/2021
 * Time: 11:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Rating extends Node {
    private static int nextId = 1;
    private int rateId;
    private String rate;
    private String rateDesc;
    private List<Drama> dramas;

    public Rating(String rate, String rateDesc) {
        super(AttrType.RATING);
        this.rateId = nextId++;
        this.rate = rate;
        this.rateDesc = rateDesc;
        dramas = new ArrayList<>();
    }

    public void addDrama(Drama drama) {
        dramas.add(drama);
    }
}
