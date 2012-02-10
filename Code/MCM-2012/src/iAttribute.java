
public interface iAttribute {


    /**
     * Get a string name for this attribute: describing associated variable,
     * domain, and possibly assigned value.
     **/
    String toSting();

    /**
     * Get a string describing the attribute domain (basically type).
     **/
    String getDomainDescription();

    /**
     * Get a list of valid domain values.
     **/
    Object[] getDomain();

    /**
     * Returns the current attribute value.
     **/
    Object getValue();

    /**
     * Returns attributes this one depends on.
     **/
    iAttribute[] getDependencies();

    /**
     * Add a dependency.
     **/
    void addDependency(iAttribute newAttr);

    /**
     * Assign the value.
     **/
    void assignValue(Object newVal);

    /**
     * Set the domain.
     **/
    void assignDomain(Object[] newDom);

}
