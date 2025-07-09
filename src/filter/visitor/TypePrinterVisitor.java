package filter.visitor;

import filter.comparison.*;
import filter.literals.*;
import filter.logical.*;

public class TypePrinterVisitor implements FilterVisitor<String> {
    public static final TypePrinterVisitor INSTANCE = new TypePrinterVisitor();
    private TypePrinterVisitor() {}
    @Override public String visitComparison(PropertyEqualsFilter f) { return "Equals"; }
    @Override public String visitComparison(PropertyGreaterThanFilter f) { return "GreaterThan"; }
    @Override public String visitComparison(PropertyLessThanFilter f) { return "LessThan"; }
    @Override public String visitComparison(PropertyPresentFilter f) { return "Present"; }
    @Override public String visitComparison(PropertyRegexFilter f) { return "Regex"; }
    @Override public String visitLiteral(TrueFilter f) { return "True"; }
    @Override public String visitLiteral(FalseFilter f) { return "False"; }
    @Override public String visitLogical(AndFilter f) { return "And"; }
    @Override public String visitLogical(OrFilter f) { return "Or"; }
    @Override public String visitLogical(NotFilter f) { return "Not"; }
}

