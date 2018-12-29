package com.buihanotes;

import com.buihanotes.grammars.CSVBaseListener;
import com.buihanotes.grammars.CSVParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVLoader extends CSVBaseListener {
    private static final String EMPTY = "";

    List<Map<String, String>> rows = new ArrayList<Map<String, String>>();

    List<String> header;

    List<String> currentRowlFieldValues;

    @Override
    public void exitText(CSVParser.TextContext ctx) {
        currentRowlFieldValues.add(ctx.TEXT().getText());
    }

    @Override
    public void exitString(CSVParser.StringContext ctx) {
        currentRowlFieldValues.add(ctx.STRING().getText());
    }

    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
        currentRowlFieldValues.add(EMPTY);
    }

    @Override
    public void exitHdr(CSVParser.HdrContext ctx) {
        header = new ArrayList<>();
        header.addAll(currentRowlFieldValues);
    }

    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        currentRowlFieldValues = new ArrayList<>();
    }

    @Override
    public void exitRow(CSVParser.RowContext ctx) {
        if (ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr)
            return;
        Map<String, String> mapRow = new LinkedHashMap<>();
        for(int i = 0; i < currentRowlFieldValues.size(); ++i) {
            mapRow.put(header.get(i), currentRowlFieldValues.get(i));
        }
        rows.add(mapRow);
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }
}
