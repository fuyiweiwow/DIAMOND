/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.Stack;

import diamond.model.cyborg.geom.d0.PivotComparator;
import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class CreaseAdder {
    private Stack<SegmentCrease> tobe = new Stack<>();
    private Stack<Wex> at0 = new Stack<>();
    private Stack<Wex> at1 = new Stack<>();

    public void across(D1 s1, Face face) {
        for (SegmentCrease s0 : face.getCreases()) {
            Wex[] cp = s0.getSplitterVertices(s1);
            // doesnt across
            if (cp == null) {
                continue;
            }
            Wex cp0 = cp[0];
            Wex cp1 = cp[1];
            // Connected
            if (cp0 == cp1) {
                continue;
            }
            record(s1, s0, cp0, cp1);
        }
        split0();
        face.add(s1);
        split1(s1);
    }

    private void record(SegmentCrease s1, SegmentCrease s0, Wex cp0,
            Wex cp1) {
        // only s0 is splitted
        // Could it be?
        if (cp1 == s1.getV0() || cp1 == s1.getV1()) {
            tobe.add(s0);
            at0.add(cp1);
            return;
        }
        // only s1 is splitted
        if (cp0 == s0.getV0() || cp0 == s0.getV1()) {
            at1.add(cp0);
            return;
        }
        // Both of them are splitted
        tobe.add(s0);
        at0.add(cp0);
        at1.add(cp0);
        return;
    }

    private void split1(SegmentCrease s1) {
        at1.sort(new PivotComparator(s1.getV0()));
        while (!at1.isEmpty()) {
            Wex v = at1.pop();
            s1.split(v);
        }
    }

    private void split0() {
        while (!at0.isEmpty()) {
            SegmentCrease c = tobe.pop();
            Wex v = at0.pop();
            c.split(v);
        }
    }

}
