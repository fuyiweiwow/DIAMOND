/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.util.HashSet;
import java.util.Set;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.palette.cp.CreasePattern;
import diamond.model.palette.cp.simplifier.CollinearCPSimplifier;
import diamond.model.palette.cp.simplifier.DuplicatedCPSimplifier;

/**
 * @author long_
 *
 */
public class OriModel {
    private Set<OriFace> faces = new HashSet<OriFace>();
    private Set<OriVertex> vertices = new HashSet<OriVertex>();
    private Set<OriHalfEdge> auxLines = new HashSet<OriHalfEdge>();
    private boolean isFolded = false;

    public OriModel(CreasePattern cp) {
        CollinearCPSimplifier.simplify(cp);
        DuplicatedCPSimplifier.simplify(cp);
        buildVertices(cp);
        for (OriVertex vertex : vertices) {
            vertex.setFoldability();
        }
        buildFaces();
    }

    private void buildFaces() {
        for (OriVertex vertex : vertices) {
            vertex.setFoldability();
            for (OriHalfEdge he : vertex.getHalfEdges()) {
                if (toBeSkipped(he)) {
                    continue;
                }
                OriFace face = new OriFace();
                faces.add(face);
                OriHalfEdge walkHe = he;
                do {
                    face.addHalfEdge(walkHe);
                    walkHe.setFace(face);
                    walkHe = walkHe.getEv().getPrevEdge(walkHe.getPair());
                } while (walkHe.getSv() != vertex);
                face.makeHalfedgeLoop();
                face.setOutline(0.5);
            }
        }
    }

    private boolean toBeSkipped(OriHalfEdge he) {
        if (he.getFace() != null) {
            return true;
        }
        LineType type = he.getType();
        if (type == LineType.CUT && isFolded) {
            return true;
        }
        return false;
    }

    private void buildVertices(CreasePattern cp) {
        for (OriLine line : cp.getLines()) {
            OriVertex v0 = new OriVertex(line.p0);
            OriVertex v1 = new OriVertex(line.p1);
            v0 = add(v0);
            v1 = add(v1);
            OriHalfEdge he0 = new OriHalfEdge(v0, v1, line.getType());
            OriHalfEdge he1 = new OriHalfEdge(v1, v0, line.getType());
            he0.makePair(he1);
            switch (line.getType()) {
            case AUX:
                auxLines.add(he0);
                continue;
            case MOUNTAIN:
            case VALLEY:
                isFolded = true;
                break;
            default:
                break;
            }
            v0.addEdge(he0);
            v1.addEdge(he1);
        }
    }

    private OriVertex add(OriVertex v) {
        for (OriVertex oriVertex : vertices) {
            if (DistanceUtil.Distance(v, oriVertex) < Constants.EPS) {
                return oriVertex;
            }
        }
        vertices.add(v);
        return v;
    }

    public Set<OriFace> getFaces() {
        return this.faces;
    }

    public Set<OriVertex> getVertices() {
        return this.vertices;
    }

    public Set<OriHalfEdge> getAuxLines() {
        return this.auxLines;
    }

}
