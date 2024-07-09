import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CheckReturnValue")

public class MyCSVListener extends CSVBaseListener {

    private List<List<String>> rows = new ArrayList<>();
    private List<String> currentRow = new ArrayList<>();

    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        currentRow = new ArrayList<>();
    }

    @Override
    public void exitRow(CSVParser.RowContext ctx) {
        rows.add(currentRow);
    }

    @Override
    public void enterField(CSVParser.FieldContext ctx) {
        String text = ctx.getText();
        currentRow.add(text);
    }

    public List<List<String>> getRows() {
        return rows;
    }
}
