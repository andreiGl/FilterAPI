package filter.comparison;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class PropertyPresentFilter implements Filter {
    private final String property;

    public PropertyPresentFilter(String property) {
        this.property = property;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return resource.containsKey(property);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), property);
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitComparison(this);
    }
}
