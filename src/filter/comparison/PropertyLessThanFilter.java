package filter.comparison;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class PropertyLessThanFilter implements Filter {
    private final String property;
    private final String value;

    public PropertyLessThanFilter(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String propValue = resource.get(property);
        if (propValue == null) return false;
        try {
            double propNum = Double.parseDouble(propValue);
            double valueNum = Double.parseDouble(value);
            return propNum < valueNum;
        } catch (NumberFormatException _) {
            return propValue.compareToIgnoreCase(value) < 0;
        }
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
