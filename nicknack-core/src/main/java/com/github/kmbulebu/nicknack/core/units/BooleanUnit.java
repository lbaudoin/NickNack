package com.github.kmbulebu.nicknack.core.units;

import com.github.kmbulebu.nicknack.core.attributes.filters.Operator;

public class BooleanUnit implements Unit {
	
	public static final BooleanUnit INSTANCE = new BooleanUnit();

	@Override
	public String getName() {
		return "True/False";
	}

	@Override
	public Boolean evaluate(Operator operator, String operand1, String operand2) {
		final Boolean op1 = Boolean.parseBoolean(operand1);
		final Boolean op2 = Boolean.parseBoolean(operand2);
		switch(operator) {
		case EQUALS:
			return op1.equals(op2);
		default:
			return null;
			//throw new UnsupportedOperationException(operator.getName() + " operator is not supported by " + getName());
		}
	}

	@Override
	public Operator[] getSupportedOperators() {
		return new Operator[] {Operator.EQUALS};
	} 

}
