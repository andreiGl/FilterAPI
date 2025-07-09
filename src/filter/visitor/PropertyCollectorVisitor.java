package filter.visitor;

import filter.Filter;
import filter.comparison.*;
import filter.literals.*;
import filter.logical.*;
import java.util.HashSet;
import java.util.Set;

public class PropertyCollectorVisitor implements FilterVisitor<Set<String>> {
    public static final PropertyCollectorVisitor INSTANCE = new PropertyCollectorVisitor();
    private PropertyCollectorVisitor() {}
    @Override public Set<String> visitComparison(PropertyEqualsFilter f) { return Set.of(f.toString().split("[(,]", 3)[1]); }
    @Override public Set<String> visitComparison(PropertyGreaterThanFilter f) { return Set.of(f.toString().split("[(,]", 3)[1]); }
    @Override public Set<String> visitComparison(PropertyLessThanFilter f) { return Set.of(f.toString().split("[(,]", 3)[1]); }
    @Override public Set<String> visitComparison(PropertyPresentFilter f) { return Set.of(f.toString().split("[(,]", 3)[1]); }
    @Override public Set<String> visitComparison(PropertyRegexFilter f) { return Set.of(f.toString().split("[(,]", 3)[1]); }
    @Override public Set<String> visitLiteral(TrueFilter f) { return Set.of(); }
    @Override public Set<String> visitLiteral(FalseFilter f) { return Set.of(); }
    @Override public Set<String> visitLogical(AndFilter f) {
        Set<String> result = new HashSet<>();
        for (Filter child : f.getFilters()) result.addAll(child.accept(this));
        return result;
    }
    @Override public Set<String> visitLogical(OrFilter f) {
        Set<String> result = new HashSet<>();
        for (Filter child : f.getFilters()) result.addAll(child.accept(this));
        return result;
    }
    @Override public Set<String> visitLogical(NotFilter f) {
        return f.getFilter().accept(this);
    }
}

