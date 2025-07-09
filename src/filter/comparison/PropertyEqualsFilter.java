package filter.comparison;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class PropertyEqualsFilter implements Filter {
    private final String property;
    private final String value;

    public PropertyEqualsFilter(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String propValue = resource.get(property);
        return propValue != null && propValue.equalsIgnoreCase(value);
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s)", getClass().getSimpleName(), property, value);
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitComparison(this);
    }
}
