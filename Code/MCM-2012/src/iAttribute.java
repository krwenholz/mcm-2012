
public interface iAttribute<E> {

    /**
     * Get a string describing the attribute domain (basically type).
     **/
    String getDomainDescription();

    /**
     * Get a list of valid domain values.
     **/
    <E>[] getDomain();

    /**
     * Returns the current attribute value.
     **/
    <E> getValue();

    /**
     * Returns attributes this one depends on.
     **/
    iAttribute[] getDependencies();

    /**
     * Add a dependency.
     **/
    void addDependency(iAttribute<E> newAttr);

    /**
     * Alter domain
     **/

}
