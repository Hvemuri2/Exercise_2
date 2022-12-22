public class Rectangle extends Shape implements AreaCalculator{
	private int otherSide;

	public Rectangle (MeasuringUnit unit, Integer side, Integer otherSide) throws CheckedRuntimeException {
		super(unit, side);
		if(otherSide<0)throw new ShapeException("the sides of a rectangle cannot be negative",new IllegalArgumentException());
		this.otherSide = otherSide;
	}

	public int getArea() throws CheckedRuntimeException {
		if(super.getSide()<0||otherSide<0)throw new ShapeException("neither of the sides of a rectangle can be negative",new IllegalArgumentException());
		if((super.getSide()*otherSide)>Long.MAX_VALUE)throw new AreaCalculatorException("Result operations are overflowing a Long",new ArithmeticException());
		return super.getSide()*otherSide;
	}

	// the default measuring unit is determined by unit
	public long convertArea(long area, MeasuringUnit ms) throws CheckedRuntimeException {
		if(ms==null)throw new MeasuringUnitException("measuring unit cannot be null",new IllegalArgumentException());
		if(area<0)throw new ShapeException("area cannot be negative",new IllegalArgumentException());

		long result = 1;
		try {
			if (ms != this.unit) {
					if(ms==MeasuringUnit.FEET){
						if((area * 3.28)>Long.MAX_VALUE)throw new ArithmeticException();
						result = (long) (area * 3.28);}
					else if(ms==MeasuringUnit.METERS){
						if((area / 3.28)>Long.MAX_VALUE)throw new ArithmeticException();
						result = (long) (area/3.28);}
			}
		}catch(ArithmeticException a){ // if the variable result is overflowed
			throw new AreaCalculatorException("result operations cannot be stored in a Long",a);
		}
		return result;

	}

	// the default measuring unit is determined by unit, use super.getSide() and otherSide for sum of the areas, only one area in this case
	public long getTotalArea() throws CheckedRuntimeException {
		long result;
		if((super.getSide()*otherSide)>Long.MAX_VALUE)throw new AreaCalculatorException("result operations cannot be stored in a Long",new ArithmeticException());
		result = (long)super.getSide() *otherSide;
		return result;
	}

	// the default measuring unit is determined by unit, use super.getSide() and otherSide for sum of the areas, only one area in this case
	public long getTotalArea (MeasuringUnit ms) throws CheckedRuntimeException {
		if(ms==null)throw new MeasuringUnitException("measuring unit cannot be null",new IllegalArgumentException());
		if((super.getSide() *otherSide)>Long.MAX_VALUE)throw new AreaCalculatorException("result operations cannot be stored in a Long",new ArithmeticException());
		long result = (long)super.getSide() *otherSide;
		if(this.unit!=ms){
			result = convertArea(result,ms);
		}
		return result;
	}

	public String toString(){
		return "Rectangle: {otherSide: " + otherSide + ", side: " + this.getSide()+"}";
	}
}
