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
public class Subtitles extends Node {
    private static int nextId = 1;
    private int langId;
    private String languageName;
    private List<Drama> dramas;

    public Subtitles(String languageName) {
        super(AttrType.SUBTITLES);
        this.langId = nextId++;
        this.languageName = languageName;
        dramas = new ArrayList<>();
    }

    public void addDrama(Drama drama) {
        dramas.add(drama);
    }
}