public abstract class Shape {
	private int side;
	protected MeasuringUnit unit;
	public Shape (MeasuringUnit unit, int side) throws CheckedRuntimeException{
		if(unit==null)throw new ShapeException("value of MeasuringUnit cannot be null",new IllegalArgumentException());
		if(side<0)throw new ShapeException("the side of a shape cannot be negative",new IllegalArgumentException());
		this.unit = unit;
		this.side = side;
	}

	public int getSide(){
		return this.side;
	}

	public MeasuringUnit getMeasuringUnit() {
		return this.unit;
	}

	public abstract int getArea() throws CheckedRuntimeException;
}
