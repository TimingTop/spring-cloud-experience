package com.timing.compiler.day53;

public class LRParser {

    public static void main(String[] args) {
        ProductionManager productionManager = ProductionManager.getProductionManager();
        productionManager.initProductions();

        GrammarStateManager stateManager = GrammarStateManager.getGrammarManager();
        stateManager.buildTransitionStateMachine();

    }
}
