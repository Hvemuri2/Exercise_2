public class Square extends Shape{
	public int side;
	public Square (MeasuringUnit unit, int side) throws CheckedRuntimeException {
		super(unit, side);
		this.side = side;
	}

	public void setSide(Integer side) throws CheckedRuntimeException {
		if(side<0)throw new ShapeException("the side of a square cannot be negative",new IllegalArgumentException());
		this.side = side;
	}

	public int getArea() throws CheckedRuntimeException {
		if((this.side*this.getSide())>Long.MAX_VALUE)throw new AreaCalculatorException("Value of operations cannot be stored in a Long",new ArithmeticException());
		return this.side * this.getSide();
	}

	public String toString(){
		return "Square: {_side: " + side + ", side: " + super.getSide()+"}";
	}
}
