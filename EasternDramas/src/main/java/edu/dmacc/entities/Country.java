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
public class Country extends Node {
    private static int nextId = 1;
    private int countryId;
    private String countryName;
    private List<Drama> dramas;

    public Country(String countryName) {
        super(AttrType.COUNTRY);
        countryId = nextId++;
        this.countryName = countryName;
        dramas = new ArrayList<>();
    }

    public void addDrama(Drama drama) {
        dramas.add(drama);
    }
}