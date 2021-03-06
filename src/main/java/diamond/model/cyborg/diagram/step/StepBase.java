/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.symbol.AbstractSymbol;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class StepBase {
    protected ArrayList<Face> faces = new ArrayList<>();
    protected HashSet<Edge> edges = new HashSet<Edge>();
    protected ArrayList<AbstractSymbol> symbols = new ArrayList<AbstractSymbol>();
    protected TransformScreen transform = new TransformScreen();
    protected Face base;

    @Deprecated
    public StepBase() {
    }

    public void add(Face face) {
        if (base == null) {
            base = face;
        }
        faces.add(face);
    }

    @Deprecated
    public TransformScreen getTransform() {
        return transform;
    }

    @Deprecated
    public void setTransform(TransformScreen transform) {
        this.transform = transform;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    @Deprecated
    public void setFaces(ArrayList<Face> faces) {
        this.faces = faces;
    }

    public ArrayList<AbstractSymbol> getSymbols() {
        return symbols;
    }

    @Deprecated

    public void setSymbols(ArrayList<AbstractSymbol> symbols) {
        this.symbols = symbols;
    }

    public Face getBase() {
        return base;
    }

    public void setBase(Face base) {
        this.base = base;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    @Deprecated
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }
}
