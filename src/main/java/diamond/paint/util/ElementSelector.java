package diamond.paint.util;

import java.awt.BasicStroke;
import java.awt.Color;

import diamond.paint.core.LineSetting;
import diamond.paint.core.PaintConfig;
import diamond.value.OriLine;

public class ElementSelector {

    public static Color selectColorByLineType(int lineType) {
        Color color;

        switch (lineType) {
        case OriLine.TYPE_NONE:
            color = LineSetting.LINE_COLOR_AUX;
            break;
        case OriLine.TYPE_CUT:
            color = Color.BLACK;
            break;
        case OriLine.TYPE_RIDGE:
            color = LineSetting.LINE_COLOR_RIDGE;
            break;
        case OriLine.TYPE_VALLEY:
            color = LineSetting.LINE_COLOR_VALLEY;
            break;
        default:
            color = Color.BLACK;
        }

        return color;
    }

    public static Color selectColorByPickupOrder(int order, int count) {
        if (order == count - 1) {
            return Color.GREEN;
        }

        return selectColorByLineType(PaintConfig.inputLineType);
    }

    public static Color selectLineColor(OriLine line) {

        Color color;

        if (line.selected) {
            color = LineSetting.LINE_COLOR_CANDIDATE;
        } else {
            color = selectColorByLineType(line.typeVal);
        }

        return color;

    }

    public static BasicStroke selectStroke(int lineType) {
        BasicStroke stroke;
        switch (lineType) {
        case OriLine.TYPE_NONE:
            stroke = LineSetting.STROKE_CUT;
            break;
        case OriLine.TYPE_CUT:
            stroke = LineSetting.STROKE_CUT;
            break;
        case OriLine.TYPE_RIDGE:
            stroke = LineSetting.STROKE_RIDGE;
            break;
        case OriLine.TYPE_VALLEY:
            stroke = LineSetting.STROKE_VALLEY;
            break;
        default:
            stroke = LineSetting.STROKE_CUT;
        }

        return stroke;
    }

}
