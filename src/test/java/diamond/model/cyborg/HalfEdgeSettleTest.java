/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.HalfEdgeSettleAction;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeSettleTest extends AbstractPaintActionTest {

    public HalfEdgeSettleTest() {
        super();
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);

    }

    private void line0() {
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, l, context);
    }

    private void line1() {
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
    }

    private void select0() {
        TestUtil.click(.0, .0, context);
    }

    private void select1() {
        TestUtil.click(200.0, 200.0, context);
    }

    private void select2() {
        TestUtil.click(-200.0, 200.0, context);
    }

    private void select3() {
        TestUtil.click(-200.0, -200.0, context);
    }

    private void select4() {
        TestUtil.click(200.0, -200.0, context);
    }

    @Test
    public void Step0() {
        line0();
        context.setPaintAction(new HalfEdgeSettleAction());
        select0();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(2, faces.size());
        TestUtil.validate(faces.get(0), 3, 0);
        TestUtil.validate(faces.get(1), 3, 0);
        select0();
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 2);
    }

    @Test
    public void Step1() {
        line0();
        line1();
        context.setPaintAction(new HalfEdgeSettleAction());
        select1();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 6, 6);
        select1();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 4, 8);
        select2();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 6, 6);
        select2();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);

        TestUtil.validate(faces.get(0), 4, 8);
    }

    @Test
    public void Step2() {
        line0();
        line1();
        context.setPaintAction(new HalfEdgeSettleAction());
        select1();
        select3();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(2, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(faces.get(1), 4, 2);
    }

    @Test
    public void Step3() {
        line0();
        line1();
        context.setPaintAction(new HalfEdgeSettleAction());
        select1();
        select2();
        select3();
        select4();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(4, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 3, 0);
        TestUtil.validate(faces.get(1), 3, 0);
        TestUtil.validate(faces.get(2), 3, 0);
        TestUtil.validate(faces.get(3), 3, 0);
        select3();
        assertEquals(3, faces.size());
        TestUtil.validate(cp, 5);
        //        TestUtil.validate(faces.get(0), 3, 0);
        //        TestUtil.validate(faces.get(1), 3, 0);
        //        TestUtil.validate(faces.get(2), 4, 2);
        select4();
        assertEquals(2, faces.size());
        TestUtil.validate(cp, 5);
        //        TestUtil.validate(faces.get(0), 3, 0);
        //        TestUtil.validate(faces.get(1), 5, 4);
        select4();
        select1();
        assertEquals(2, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(faces.get(1), 4, 2);

    }

    @Test
    public void Step4() {
        line0();
        context.setPaintAction(new HalfEdgeSettleAction());
        select0();
        context.setPaintAction(new Axiom1Action());
        line1();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(2, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(cp, 0, 0, 4, false);
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(faces.get(1), 4, 2);
    }
}