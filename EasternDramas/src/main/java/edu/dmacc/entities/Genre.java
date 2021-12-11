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
public class Genre extends Node {
    private static int nextId = 1;
    private int genreId;
    private String genreName;
    private List<Drama> dramas;

    public Genre(String genreName) {
        super(AttrType.GENRE);
        this.genreId = nextId++;
        this.genreName = genreName;
        dramas = new ArrayList<>();
    }

    public void addDrama(Drama drama) {
        dramas.add(drama);
    }
}
