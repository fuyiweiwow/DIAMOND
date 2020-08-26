/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.HashSet;

import diamond.model.cyborg.geom.d0.Wex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public final class Step extends StepBase {
    private HashSet<AbstractSegment> segments = new HashSet<>();
    private HashSet<Wex> wexes = new HashSet<>();

    @Deprecated
    public Step() {
    }

    public void update() {
        new Folder(this);
        setCyborg();
    }

    private void setCyborg() {
        setSegments();
        setWexes();
    }

    private void setSegments() {
        this.segments.clear();
        for (Face face : faces) {
            this.segments.addAll(face.getCreases());
            this.segments.addAll(edges);
        }
    }

    private void setWexes() {
        this.wexes.clear();
        for (AbstractSegment segment : segments) {
            wexes.add(segment.getW0());
            wexes.add(segment.getW1());
        }
        for (Face face : faces) {
            for (Wex v : face.getWexes()) {
                wexes.add(v);
                v.apply(face.getMirror());
            }
        }
    }

    public HashSet<Wex> getVertices() {
        return wexes;
    }

    public HashSet<AbstractSegment> getSegments() {
        return segments;
    }

    public void remove(Edge edge) {
        edges.remove(edge);
    }

    public void add(Edge edge) {
        edges.add(edge);
    }

    public void link(Face f0, Face f1, Wex v0, Wex v1) {
        Edge edge = new Edge(f1, f0, v0, v1);
        add(edge);
    }
}
