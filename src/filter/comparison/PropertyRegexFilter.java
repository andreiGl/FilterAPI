package filter.comparison;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;
import java.util.regex.Pattern;

public class PropertyRegexFilter implements Filter {
    private final String property;
    private final Pattern pattern;

    public PropertyRegexFilter(String property, String regex) {
        this.property = property;
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String propValue = resource.get(property);
        return propValue != null && pattern.matcher(propValue).matches();
    }

    @Override
    public String toString() {
        return String.format("%s(%s, /%s/)", getClass().getSimpleName(), property, pattern.pattern());
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitComparison(this);
    }
}
