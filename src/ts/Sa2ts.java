package ts;

import sa.*;


public class Sa2ts extends SaDepthFirstVisitor<Void> {
    private Ts tableGlobale = new Ts();
    private Ts tableLocaleCourante;
    private int index = 0;
    private enum Context {GLOBAL, PARAM, VARIABLE};
    private Context context;
    public Ts getTableGlobale() {
        return tableGlobale;
    }
    public Sa2ts(SaNode root) {
        context = Context.GLOBAL;
        root.accept(this);
        context = Context.GLOBAL;

    }

    @Override
    public Void visit(SaDecVar node) {
        defaultIn(node);
        switch (context) {
            case GLOBAL:
                decVarGlob(node);
                break;
            case PARAM:
                Param(node);
                break;
            case VARIABLE:
                decVarLoc(node);
                break;
            default:
                Throw.exception("");
        }
        defaultOut(node);
        return null;
    }


    @Override
    public Void visit(SaDecTab node) {
        defaultIn(node);
        if (context != Context.GLOBAL) Throw.exception("");
        String nom = node.getNom();
        int taille = node.getTaille();

        if (varExists(nom, tableGlobale)) Throw.exception("La variable existe deja");
        if (taille < 2) Throw.exception("");
        node.tsItem = tableGlobale.addVar(nom, taille);
        defaultOut(node);
        return null;
    }


    @Override
    public Void visit(SaDecFonc node) {
        defaultIn(node);
        Ts localTable = new Ts();
        tableLocaleCourante = localTable;
        String nom = node.getNom();
        int nbArgs = node.getParametres() == null ? 0 : node.getParametres().length();
        context = Context.GLOBAL;
        if (fxAlreadyExists(node.getNom())) Throw.exception("la Fx " + node.getNom() + " est déjà défini");
        context = Context.PARAM;
        if (node.getParametres() != null) node.getParametres().accept(this);
        context = Context.VARIABLE;
        if (node.getVariable() != null) node.getVariable().accept(this);
        node.tsItem = tableGlobale.addFct(nom, nbArgs, localTable, node);
        if (node.getCorps() != null) node.getCorps().accept(this);
        context = Context.GLOBAL;
        tableLocaleCourante = tableGlobale.getTableLocale(node.getNom());
        defaultOut(node);
        return null;
    }




    @Override
    public Void visit(SaVarSimple node) {
        defaultIn(node);
        boolean isGlobal = false;
        if (varDoesNotExists(node.getNom(), tableLocaleCourante)) {
            if (varDoesNotExists(node.getNom(), tableGlobale)) {
                throwVarNotDeclared(node.getNom());
            } else {
                isGlobal = true;
            }
        }
        TsItemVar tsItemVar;
        if (isGlobal) {
            tsItemVar = tableGlobale.getVar(node.getNom());
        } else {
            tsItemVar = tableLocaleCourante.getVar(node.getNom());
        }
        if (tsItemVar.taille > 1) {
            Throw.exception("");
        }
        node.tsItem = tsItemVar;
        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaVarIndicee node){
        defaultIn(node);

        if (varExists(node.getNom(), tableLocaleCourante)) Throw.exception(node.getNom() +
                " existe déjà");
        if (varDoesNotExists(node.getNom(), tableGlobale)) throwVarNotDeclared(node.getNom());

        if (tableGlobale.getVar(node.getNom()).taille < 2) Throw.exception("");

        node.getIndice().accept(this) ;
        node.tsItem = tableGlobale.getVar(node.getNom());

        defaultOut(node);
        return null;
    }

    @Override
    public Void visit(SaAppel node){

        defaultIn(node);

        String nom = node.getNom();
        int nbArgsCalled = node.getArguments() == null ? 0 : node.getArguments().length();

        System.out.println(nbArgsCalled+"argument ");
        node.tsItem = tableGlobale.getFct(nom) ;
        if(fxDoNotExist(nom)){
            Throw.exception("La fx " +nom+ "n'existe pas");
        } else{
            if(nbArgsCalled != node.tsItem.getNbArgs()){
                Throw.exception("");
            }
            if(nbArgsCalled > 0){
                node.getArguments().accept(this) ;
            }
        }

        defaultOut(node);
        return null;
    }


    @Override
    public void defaultIn(SaNode node) {
        for (int i = 0; i < index; i++) {
            System.out.print(" ");
        }
        System.out.println("<" + node.getClass().getSimpleName() + ">");
        index++;
    }

    @Override
    public void defaultOut(SaNode node) {
        index--;
        for (int i = 0; i < index; i++) {
            System.out.print(" ");
        }
        System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    private boolean fxAlreadyExists(String name) {
        return tableGlobale.getFct(name) != null;
    }

    private boolean varExists(String nom, Ts table) {
        return table.getVar(nom) != null;
    }

    private void throwVarDecTwice(String nom) {
        Throw.exception( nom + " est déclare plusieurs fois");
    }

    private boolean fxDoNotExist(String nom) {
        return !fxAlreadyExists(nom);
    }

    private boolean varDoesNotExists(String nom, Ts table) {
        return !varExists(nom, table);
    }

    private void throwVarNotDeclared(String varName) {
        Throw.exception( varName + " n'a pas été déclarée");
    }

    private void Param(SaDecVar node) {
        if (varExists(node.getNom(), tableLocaleCourante)) throwVarDecTwice(node.getNom());
        node.tsItem = tableLocaleCourante.addParam(node.getNom());
    }

    private void decVarLoc(SaDecVar node) {
        if (varExists(node.getNom(), tableLocaleCourante)) throwVarDecTwice(node.getNom());
        node.tsItem = tableLocaleCourante.addVar(node.getNom(), 1);
    }

    private void decVarGlob(SaDecVar node) {
        if (varExists(node.getNom(), tableGlobale)) throwVarDecTwice(node.getNom());
        node.tsItem = tableGlobale.addVar(node.getNom(), 1);
    }

    private static class Throw extends RuntimeException {
        private Throw(String message) {
            super(message);
        }
        public static void exception(String message) {
            throw new Throw(message);
        }
    }
}
