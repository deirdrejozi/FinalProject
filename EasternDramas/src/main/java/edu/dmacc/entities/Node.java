
package dmacc.drama.entities;

/**
 * @author Pamela D. Jozi
 * Date: 12/10/2021
 * Time: 11:59
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node {
    private AttrType type;

    public Node( AttrType type) {
        this.type = type;
    }

}