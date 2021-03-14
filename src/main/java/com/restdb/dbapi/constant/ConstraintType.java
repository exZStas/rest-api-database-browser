package com.restdb.dbapi.constant;

public enum ConstraintType {
    CHECK("C"),
    PRIMARY_KEY("P"),
    UNIQUE_KEY("U"),
    REFERENTIAL_INTEGRITY("R"),
    VIEW_CHECK("V"),
    READ_ONLY_VIEW("O")
    ;

    private String constraintDefinition;

    ConstraintType(String constraintDefinition) {
        this.constraintDefinition = constraintDefinition;
    }

    public static ConstraintType getConstraintTypeByDefinition(String constraintDefinition) {
        for(ConstraintType constraintType: ConstraintType.values()) {
            if(constraintType.constraintDefinition.equalsIgnoreCase(constraintDefinition)) {
                return constraintType;
            }
        }

        return null;
    }
}
