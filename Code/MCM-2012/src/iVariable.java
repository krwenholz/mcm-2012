/**
 * Defines the methods a variable (something to be constrained) must have,
 * along with the general behavior descriptions for these methods.
 **/
public interface iVariable {

    /**
     * Returns a string representation of this variable's names and various
     * states.
     **/
    String toString();

    /**
     * Returns the domain values for the requested variable attribute.
     **/
    iAttribute getDomainValue(String variableAttribute);

    /**
     * Returns the current value of the variable attribute specified.
     **/
    iAttribute getValue(String variableAtrribute);

    /**
     * Returns a list, String representation, of the attributes in this
     * variable.
     **/
    String[] getValues();

}