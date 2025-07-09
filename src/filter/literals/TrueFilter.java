package filter.literals;

import filter.Filter;
import filter.visitor.FilterVisitor;

import java.util.Map;

public class TrueFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return true;
    }

    @Override
    public String toString() {
        return "TRUE";
    }

    @Override
    public <R> R accept(FilterVisitor<R> visitor) {
        return visitor.visitLiteral(this);
    }
}
