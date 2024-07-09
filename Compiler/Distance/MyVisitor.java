@SuppressWarnings("CheckReturnValue")
public class MyVisitor extends ShapesBaseVisitor<Object> {

   @Override public Object visitDistance(ShapesParser.DistanceContext ctx) {
      Double res;
        Double[] p1 = (Double[])visit(ctx.point(0));
        Double[] p2 = (Double[])visit(ctx.point(1));
        res = Math.sqrt(
                Math.pow(p1[0] - p2[0], 2) +
                Math.pow(p1[1] - p2[1], 2)
);
        System.out.println("Visitor distance: " + res);
      //return visitChildren(ctx);
      return (Object)res;
   }

   @Override public Object visitPoint(ShapesParser.PointContext ctx) {
      Double[] res = new Double[2];
        res[0] = Double.parseDouble(ctx.x.getText());
        res[1] = Double.parseDouble(ctx.y.getText());
        //return visitChildren(ctx);
      return (Object)res;
   }

}
