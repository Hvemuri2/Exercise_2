
public class ShapeManager implements AreaCalculator {
	public static final MeasuringUnit defaultUnit = MeasuringUnit.METERS;
	public Shape[] shapes;
	public ShapeManager (Object[][] values) throws CheckedRuntimeException {
		if(values==null)throw new ShapeException("the array of objects 'values' cannot be null",new NullPointerException());
		initShapes(values.length);
		values2Shapes(values);
	}

	public void initShapes(int length) throws CheckedRuntimeException {
		if(length<0)throw new ShapeException("length of the array 'values' cannot be null",new IllegalArgumentException());
		this.shapes =  new Shape[length];
	}

	public void setShapes(Shape[] shapes) throws CheckedRuntimeException {
			if(shapes == null){
				throw new ShapeException("cannot set null as a value in the array 'shapes'",new IllegalArgumentException());
			}
			this.shapes =  shapes;
	}

	public Shape[] getShapes() throws CheckedRuntimeException {
		if(this.shapes == null){
			throw new ShapeException("array 'shapes' cannot contain null values",new IllegalStateException());
		}
		return this.shapes;
	}

	public void values2Shapes(Object[][] values) throws CheckedRuntimeException {
		try{
			if(values==null)throw new NullPointerException();
			for (int i = 0; i < values.length; i++) {
				if(values[i]==null)throw new NullPointerException();
				Object[] value = values[i];

				switch (value.length) {
					case 2:
						shapes[i] = new Square((MeasuringUnit) value[0], (int) value[1]);
						break;
					case 3:
						shapes[i] = new Rectangle((MeasuringUnit) value[0], (int) value[1], (int) value[2]);
					default:

				}
			}
		}catch (NullPointerException n){
			throw new ShapeException("the array 'values' cannot be null or contain null values",n);
		}catch (ArrayIndexOutOfBoundsException a){
			throw new ShapeException("accessing an index that doesn't exist",a);
		}
	}
	// the default measuring unit is determined by defaultUnit
	public long convertArea(long area, MeasuringUnit ms) throws CheckedRuntimeException {
		long result = area;
		try {
			if(ms==null)throw new NullPointerException();
			if(area<0)throw new IllegalArgumentException();
			if (ms != defaultUnit) {
				if((area * 3.28)>Long.MAX_VALUE)throw new ArithmeticException();
				result = (long) (area * 3.28);
			}
		}catch (NullPointerException n){
			throw new MeasuringUnitException("measuring unit cannot be null",n);
		}catch (ArithmeticException a){ // incase the long variable 'result' is overflowed
			throw new AreaCalculatorException("Result operations are overflowing a Long",a);
		}catch (IllegalArgumentException i){
			throw new ShapeException("area cannot be negative",i);
		}
		return result;

	}

	// the default measuring unit is determined by defaultUnit, use this.getShapes() for sum of the areas
	public long getTotalArea() throws CheckedRuntimeException {
		long result = 0;
		try{
			for(Shape shape: this.getShapes()){

				if((result+shape.getArea())>Long.MAX_VALUE)throw new ArithmeticException();
				if(shape == null)throw new NullPointerException();

				result+=shape.getArea();
			}
		}catch (ArithmeticException a){
			throw new AreaCalculatorException("Result operations are overflowing a Long",a);
		}catch (NullPointerException n){
			throw new ShapeException("The array 'shapes' cannot contain null values",n);
		}
		return result;
	}

	// the default measuring unit is determined by defaultUnit, use this.getShapes() for sum of the areas
	public long getTotalArea (MeasuringUnit ms) throws CheckedRuntimeException {
		if(ms==null)throw new MeasuringUnitException("Value of MeasuringUnit cannot be null",new NullPointerException());
		long result = 0;
		try{
			for(Shape shape: this.getShapes()){
				if(shape == null)throw new NullPointerException();
				if((result+shape.getArea())>Long.MAX_VALUE)throw new ArithmeticException();
				long a = shape.getArea();
				if(shape.getMeasuringUnit()!=ms){
					a = convertArea(a,ms);
				}
				result+=a;
			}
		}catch (NullPointerException n){
			throw new ShapeException("The array shapes cannot contain null values",n);
		}catch (ArithmeticException a){
			throw new AreaCalculatorException("Result operations are overflowing a Long",a);
		}
		return result;
	}
}
