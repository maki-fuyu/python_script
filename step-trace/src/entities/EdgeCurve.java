package entities;

import java.util.ArrayList;
import java.util.List;

import utils.RegExp;
import utils.StringUtils;

public class EdgeCurve extends AbstractEntity {

	public static final String _EDGE_CURVE = "EDGE_CURVE";
	private List<String> outerRefs = new ArrayList<String>();
	private EdgeGeometry eg;
	private CartesianPoint cp1, cp2;
	
	// EDGE_CURVE ( 'NONE', #94, #96, #170, .T. ) ;
	// name, vertex_point1, vertex_point2, edge_geometry
	public EdgeCurve(String lineId) {
		super(lineId);
		String edgeCurveVal = linesMap.get(lineId);
		// VERTEX_POINT ( 'NONE', #191 ) 
		cp1 = new CartesianPoint(StringUtils.getCartesianPointIdFromVertexPointId(RegExp.getParameter(edgeCurveVal, 2, 5), linesMap));
		cp2 = new CartesianPoint(StringUtils.getCartesianPointIdFromVertexPointId(RegExp.getParameter(edgeCurveVal, 3, 5), linesMap));
		String edgeGeomId = RegExp.getParameter(edgeCurveVal, 4, 5);
		String edgeGeomVal = linesMap.get(edgeGeomId);
		if (edgeGeomVal.startsWith(Line._LINE)) {
			eg = new Line(edgeGeomId);
		} else {
			System.out.println("___not found edge geometry");
		}

	}
	
	public void addOuterRef(String entityId) {
		outerRefs.add(entityId);
	}
	
	@Override
	public String getEntityName() {
		return _EDGE_CURVE;
	}
	
	@Override
	public String toString() {
		return getLineId() + ", refs: " + StringUtils.join(outerRefs, ", ");
	}
	
	public EdgeGeometry getEdgeGeometry() {
		return eg;
	}

	public CartesianPoint getStartPoint() {
		return cp1;
	}

	public CartesianPoint getEndPoint() {
		return cp2;
	}
}