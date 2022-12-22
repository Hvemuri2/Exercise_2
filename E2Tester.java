/** Example of using unit tests for this assignment.  To run them on the command line, make
 * sure that the junit-cs211.jar is in the same directory.
 * junit-cs211.jar is a version of JUnit 4
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar E2Tester        # run tests
 *
 * On windows replace colons with semicolons: (: with ;)
 *  demo$ javac -cp .;junit-cs211.jar *.java   # compile everything
 *  demo$ java -cp .;junit-cs211.jar E2Tester  # run tests
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.lang.reflect.*;

public class E2Tester {
  // starting test values, be sure to add more
  public static final Object[][][] TEST_VALUES = {
          {
                  {MeasuringUnit.METERS, 1},
                  {MeasuringUnit.METERS, 1, 1},
                  {MeasuringUnit.METERS, 2},
                  {MeasuringUnit.METERS, 2, 3},
                  {MeasuringUnit.METERS, 3, 3 }},
          null,
          {null},
          {{0}},
          {},
          {
                  {MeasuringUnit.METERS, -1},
                  {MeasuringUnit.METERS, 1, -1},
                  {MeasuringUnit.METERS, -2},
                  {MeasuringUnit.METERS, -2, 3},
                  {MeasuringUnit.METERS, 3, 3 }},
          {
                  {MeasuringUnit.METERS, 2},
                  {MeasuringUnit.METERS, 2, 2},
                  {MeasuringUnit.METERS, 4},
                  {MeasuringUnit.METERS, 4, 6},
                  {MeasuringUnit.METERS, 6, 6 }},
          {
                  {MeasuringUnit.METERS, 2},
                  {MeasuringUnit.METERS, 2, 2},
                  {MeasuringUnit.METERS, 4},
                  {MeasuringUnit.METERS, 4, 6},
                  {null}},
          {
                  {MeasuringUnit.METERS, 2},
                  {MeasuringUnit.METERS, 2, 2},
                  {MeasuringUnit.FEET, 4},
                  {MeasuringUnit.METERS, 4, 6},
                  {MeasuringUnit.METERS, 6, 6 }}
  };
  // same goes for the expected results
  public static final Object[][] EXPECTED_RESULTS ={
          {
                  21l,
                  "[Square: {_side: 1, side: 1}, Rectangle: {otherSide: 1, side: 1}, Square: {_side: 2, side: 2}, Rectangle: {otherSide: 3, side: 2}, Rectangle: {otherSide: 3, side: 3}]"
          },
          {
                  0l,
                  null
          },
          {
                  0l,
                  null
          },
          {
                  0l,
                  null
          },
          {
                  0l,
                  "[]"
          },
          {
                  0l,
                  null
          },
          {
                  84l,
                  "[Square: {_side: 2, side: 2}, Rectangle: {otherSide: 2, side: 2}, Square: {_side: 4, side: 4}, Rectangle: {otherSide: 6, side: 4}, Rectangle: {otherSide: 6, side: 6}]"
          },
          {
                  274l,
                  "[Square: {_side: 2, side: 2}, Rectangle: {otherSide: 2, side: 2}, Square: {_side: 4, side: 4}, Rectangle: {otherSide: 6, side: 4}, Rectangle: {otherSide: 6, side: 6}]"
          },
          {
                  238l,
                  "[Square: {_side: 2, side: 2}, Rectangle: {otherSide: 2, side: 2}, Square: {_side: 4, side: 4}, Rectangle: {otherSide: 6, side: 4}, Rectangle: {otherSide: 6, side: 6}]"
          }
  };
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("E2Tester");
  }
  public static void testShapeManager(Object[][] values, long totalArea, String shapeState) throws CheckedRuntimeException{
    ShapeManager sm = new ShapeManager(values);
    AreaCalculator ac  = sm;
    long currentResult = ac.getTotalArea();

    assertTrue("Incorrect total area: "+currentResult+", expected: "+ totalArea,  currentResult== totalArea);
    String currentShapeState = Arrays.deepToString(sm.getShapes());
    assertTrue("Incorrect shapes state: \n"+currentShapeState+" \nExpected: \n"+ shapeState,  currentShapeState.equals(shapeState));

  }

  public static void testShapeManager_01(Object[][] values, long totalArea, String shapeState,MeasuringUnit ms) throws CheckedRuntimeException{
    ShapeManager sm = new ShapeManager(values);
    AreaCalculator ac = sm;
    long currentResult = ac.getTotalArea(ms);

    assertTrue("Incorrect total area: "+currentResult+", expected: "+ totalArea,  currentResult== totalArea);
    String currentShapeState = Arrays.deepToString(sm.getShapes());
    assertTrue("Incorrect shapes state: \n"+currentShapeState+" \nExpected: \n"+ shapeState,  currentShapeState.equals(shapeState));
  }
  // this test passes
  @Test(timeout=1000) public void shapeManagerGetTotalArea_00() {
    try{
      testShapeManager(
              E2Tester.TEST_VALUES[0],
              (long) E2Tester.EXPECTED_RESULTS[0][0],
              (String) E2Tester.EXPECTED_RESULTS[0][1]
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }
  // this test fails, note that the NPE is not masked, fix it
  @Test(expected=CheckedRuntimeException.class) public void shapeManagerGetTotalArea_01() throws CheckedRuntimeException{
    testShapeManager(
            E2Tester.TEST_VALUES[1],
            (long) E2Tester.EXPECTED_RESULTS[1][0],
            (String) E2Tester.EXPECTED_RESULTS[1][1]
    );
  }

  @Test(expected=CheckedRuntimeException.class) public void shapeManagerGetTotalArea_02() throws CheckedRuntimeException{
    testShapeManager(
            E2Tester.TEST_VALUES[2],
            (long) E2Tester.EXPECTED_RESULTS[2][0],
            (String) E2Tester.EXPECTED_RESULTS[2][1]
    );
  }

  @Test(expected=CheckedRuntimeException.class) public void shapeManagerGetTotalArea_03() throws CheckedRuntimeException{
    testShapeManager(
            E2Tester.TEST_VALUES[3],
            (long) E2Tester.EXPECTED_RESULTS[3][0],
            (String) E2Tester.EXPECTED_RESULTS[3][1]
    );
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_04(){
    try{
      testShapeManager(
              E2Tester.TEST_VALUES[4],
              (long) E2Tester.EXPECTED_RESULTS[4][0],
              (String) E2Tester.EXPECTED_RESULTS[4][1]
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  @Test(expected=CheckedRuntimeException.class) public void shapeManagerGetTotalArea_05() throws CheckedRuntimeException{
    testShapeManager(
            E2Tester.TEST_VALUES[5],
            (long) E2Tester.EXPECTED_RESULTS[5][0],
            (String) E2Tester.EXPECTED_RESULTS[5][1]
    );
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_06(){
    try{
      testShapeManager(
              E2Tester.TEST_VALUES[6],
              (long) E2Tester.EXPECTED_RESULTS[6][0],
              (String) E2Tester.EXPECTED_RESULTS[6][1]
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_Ms_00(){
    try{
      testShapeManager_01(
              E2Tester.TEST_VALUES[0],
              (long) E2Tester.EXPECTED_RESULTS[0][0],
              (String) E2Tester.EXPECTED_RESULTS[0][1],
              MeasuringUnit.METERS
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_01() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[1],
            (long) E2Tester.EXPECTED_RESULTS[1][0],
            (String) E2Tester.EXPECTED_RESULTS[1][1],
            MeasuringUnit.FEET);
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_02() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[2],
            (long) E2Tester.EXPECTED_RESULTS[2][0],
            (String) E2Tester.EXPECTED_RESULTS[2][1],
            MeasuringUnit.FEET);
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_03() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[3],
            (long) E2Tester.EXPECTED_RESULTS[3][0],
            (String) E2Tester.EXPECTED_RESULTS[3][1],
            MeasuringUnit.FEET);
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_Ms_04(){
    try{
      testShapeManager_01(
              E2Tester.TEST_VALUES[4],
              (long) E2Tester.EXPECTED_RESULTS[4][0],
              (String) E2Tester.EXPECTED_RESULTS[4][1],
              MeasuringUnit.METERS
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_05() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[5],
            (long) E2Tester.EXPECTED_RESULTS[5][0],
            (String) E2Tester.EXPECTED_RESULTS[5][1],
            MeasuringUnit.FEET);
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_Ms_06(){
    try{
      testShapeManager_01(
              E2Tester.TEST_VALUES[6],
              (long) E2Tester.EXPECTED_RESULTS[7][0],
              (String) E2Tester.EXPECTED_RESULTS[7][1],
              MeasuringUnit.FEET
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_07() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[6],
            (long) E2Tester.EXPECTED_RESULTS[5][0],
            (String) E2Tester.EXPECTED_RESULTS[5][1],
            null);
  }

  @Test(expected = CheckedRuntimeException.class)public void shapeManagerGetTotalArea_Ms_08() throws CheckedRuntimeException{
    testShapeManager_01(
            E2Tester.TEST_VALUES[7],
            (long) E2Tester.EXPECTED_RESULTS[5][0],
            (String) E2Tester.EXPECTED_RESULTS[5][1],
            MeasuringUnit.FEET);
  }

  @Test(timeout = 1000) public void shapeManagerGetTotalArea_Ms_09(){
    try{
      testShapeManager_01(
              E2Tester.TEST_VALUES[8],
              (long) E2Tester.EXPECTED_RESULTS[8][0],
              (String) E2Tester.EXPECTED_RESULTS[8][1],
              MeasuringUnit.FEET
      );
    }catch(CheckedRuntimeException e){
      System.out.println(e);
    }finally{
      // YOLO
    }
  }

  public void testRectangle_GetTotalArea(MeasuringUnit unit, Integer side, Integer otherSide,Integer Expected) throws CheckedRuntimeException {
    Rectangle r = new Rectangle(unit, side, otherSide);
    long area = r.getArea();
    long area1 = r.getTotalArea();
    assertEquals((long)Expected, area);
    assertEquals((long)Expected, area1);
  }

  public void testRectangle_GetTotalArea_Ms(MeasuringUnit unit, Integer side, Integer otherSide,Integer Expected,MeasuringUnit ms) throws CheckedRuntimeException {
    Rectangle r = new Rectangle(unit, side, otherSide);
    long area1 = r.getTotalArea(ms);
    assertEquals((long)Expected, area1);
  }

  @Test(timeout = 1000) public void RectangleGetTotalArea_00(){
    try{
      testRectangle_GetTotalArea(MeasuringUnit.METERS,4,6,24);
    }catch (CheckedRuntimeException e){
      System.out.println(e);
    }
  }

  @Test(expected = CheckedRuntimeException.class) public void RectangleGetTotalArea_01() throws CheckedRuntimeException {
    testRectangle_GetTotalArea(MeasuringUnit.METERS,1,-1,0);
  }

  @Test(timeout = 1000) public void RectangleGetTotalArea_Ms_00(){
    try{
      testRectangle_GetTotalArea_Ms(MeasuringUnit.METERS,4,6,78,MeasuringUnit.FEET);
    }catch (CheckedRuntimeException e){
      System.out.println(e);
    }
  }

  @Test(expected = CheckedRuntimeException.class) public void RectangleGetTotalArea_Ms_01() throws CheckedRuntimeException {
    testRectangle_GetTotalArea_Ms(MeasuringUnit.METERS,1,-1,0,MeasuringUnit.FEET);
  }

  @Test(timeout = 1000) public void RectangleGetTotalArea_Ms_02(){
    try{
      testRectangle_GetTotalArea_Ms(MeasuringUnit.FEET,4,6,7,MeasuringUnit.METERS);
    }catch (CheckedRuntimeException e){
      System.out.println(e);
    }
  }



}
