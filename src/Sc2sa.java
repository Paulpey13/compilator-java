import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue ;

    public SaNode getRoot(){
        return returnValue ;
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgramme().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }



    @Override
    public void caseATypeDecVariable(ATypeDecVariable node){
        inATypeDecVariable(node);
        String nom = node.getId().getText() ;
        this.returnValue = new SaDecVar(nom) ;
        outATypeDecVariable(node);
    }

    @Override
    public void caseAIncrementeInstIncremente(AIncrementeInstIncremente node) {
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getDecVariable().apply(this);
        inAIncrementeInstIncremente(node);
        op1=(SaExp) this.returnValue;
        node.getPlusEqual().apply(this);
        op2=(SaExp) this.returnValue;
        this.returnValue=new SaExpAdd(op1,op2);
        outAIncrementeInstIncremente(node);
    }

    @Override
    public void caseATernaireInstTernaire(ATernaireInstTernaire node) {
        super.caseATernaireInstTernaire(node);
    }

    @Override
    public void caseATableauDecVariable(ATableauDecVariable node){
        inATableauDecVariable(node);
        String nom = node.getId().getText() ;
        int taille = Integer.parseInt(node.getNumber().getText()) ;
        this.returnValue = new SaDecTab(nom, taille) ;
        outATableauDecVariable(node);
    }
    @Override
    public void caseAIdExp6(AIdExp6 node){
        SaVar var = new SaVarSimple(node.getId().getText()) ;
        this.returnValue = new SaExpVar(var) ;

    }
    @Override
    public void caseANumberExp6(ANumberExp6 node){
        int val = Integer.parseInt(node.getNumber().getText()) ;
        this.returnValue = new SaExpInt(val) ;
    }
    @Override
    public void caseAListDecVariableOptProgramme(AListDecVariableOptProgramme node){
        SaLDec variables = null;
        SaLDec fonctions = null;
        inAListDecVariableOptProgramme(node);
        if(node.getListDecVariableOpt() != null) {
            node.getListDecVariableOpt().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if(node.getListDecFunction() != null) {
            node.getListDecFunction().apply(this);
            fonctions = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaProg(variables, fonctions) ;
        outAListDecVariableOptProgramme(node);
    }


    @Override
    public void caseAAffectationInstAffectation(AAffectationInstAffectation node){
        SaVar lhs = null;
        SaExp rhs = null ;
        lhs = new SaVarSimple(node.getId().getText()) ;
        node.getExp().apply(this);
        rhs = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(lhs, rhs) ;
    }

    @Override
    public void caseAAffectationTableauInstAffectation(AAffectationTableauInstAffectation node){
        SaVar lhs = null ;
        SaExp rhs = null ;
        node.getTableau().apply(this);
        lhs = (SaVar) this.returnValue;
        node.getExp().apply(this);
        rhs = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(lhs, rhs) ;
    }
    @Override
    public void caseAListInstructionsInstBloc(AListInstructionsInstBloc node){  // done
        if(node.getListInst() != null){
            SaLInst val = null ;
            node.getListInst().apply(this);
            val = (SaLInst) this.returnValue;
            this.returnValue = new SaInstBloc(val) ;
            if(val == null){
                this.returnValue = null ;
            }
        }
    }
    @Override
    public void caseAEpsilonListInst(AEpsilonListInst node){
        this.returnValue = null ;
    }
    @Override
    public void caseAReturnInstReturn(AReturnInstReturn node){ // done
        SaExp val = null ;
        node.getExp().apply(this);
        val = (SaExp) this.returnValue;
        this.returnValue = new SaInstRetour(val) ;
    }
    @Override
    public void caseASiInstSi(ASiInstSi node){
        SaExp test = null ;
        SaInst alors= null ;
        SaInst sinon = null ;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;
        node.getInstBloc().apply(this);
        alors = (SaInst) this.returnValue;
        node.getInstSiSinon().apply(this);
        sinon = (SaInst) this.returnValue;
        this.returnValue = new SaInstSi(test, alors, sinon) ;
    }
    @Override
    public void caseASinonInstSiSinon(ASinonInstSiSinon node){
        node.getInstBloc().apply(this);
    }
    @Override
    public void caseAEpsilonInstSiSinon(AEpsilonInstSiSinon node){
        this.returnValue = null ;
    }
    @Override
    public void caseATantQueInstTantQue(ATantQueInstTantQue node){
        SaExp test = null ;
        SaInst faire = null ;
        node.getExp().apply(this);
        test = (SaExp) this.returnValue;
        node.getInstBloc().apply(this);
        faire = (SaInst) this.returnValue;
        this.returnValue = new SaInstTantQue(test, faire) ;
    }
    @Override
    public void caseATableauExp6(ATableauExp6 node){
        node.getTableau().apply(this);
        SaVar tableau = (SaVar) this.returnValue ;
        this.returnValue = new SaExpVar(tableau) ;
    }
    @Override
    public void caseAWriteInstWrite(AWriteInstWrite node){
        SaExp arg;
        node.getExp().apply(this);
        arg = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(arg) ;
    }
    @Override
    public void caseADecFunctionListDecFunction(ADecFunctionListDecFunction node){
        SaDec tete = null ;
        SaLDec queue = null ;
        if(node.getDecFunction() != null) {
            node.getDecFunction().apply(this);
            tete = (SaDec) this.returnValue;
        }
        if(node.getListDecFunction() != null) {
            node.getListDecFunction().apply(this);
            queue = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(tete, queue) ;
    }
    @Override
    public void caseAListExpCallFunction(AListExpCallFunction node){
        String nom = node.getId().getText() ;
        SaLExp arguments = null ;
        node.getListExp().apply(this);
        arguments = (SaLExp) this.returnValue;
        this.returnValue = new SaAppel(nom, arguments) ;
    }
    @Override
    public void caseAListDecVariable2ListDecVariable(AListDecVariable2ListDecVariable node){
        SaDec tete = null ;
        SaLDec queue = null ;
        if(node.getDecVariable() != null) {
            node.getDecVariable().apply(this);
            tete = (SaDec) this.returnValue;
        }
        if(node.getListDecVariable2() != null) {
            node.getListDecVariable2().apply(this);
            queue = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(tete, queue) ;
    }
    @Override
    public void caseAListDecVariableOptDecFunction(AListDecVariableOptDecFunction node){
        String nom = node.getId().getText() ;
        SaLDec parametres = null ;
        SaLDec variables = null ;
        SaInst corps = null ;
        if(node.getListDecVariable() != null) {
            node.getListDecVariable().apply(this);
            parametres = (SaLDec) this.returnValue;
        }
        if(node.getListDecVariableOpt() != null) {
            node.getListDecVariableOpt().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if(node.getInstBloc() != null) {
            node.getInstBloc().apply(this);
            corps = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom, parametres, variables, corps) ;
    }
    @Override
    public void caseAListExpressionsListExp(AListExpressionsListExp node){
        SaExp tete = null ;
        SaLExp queue = null ;
        if(node.getExp() != null) {
            node.getExp().apply(this);
            tete = (SaExp) this.returnValue;
        }
        if(node.getListExp2() != null) {
            node.getListExp2().apply(this);
            queue = (SaLExp) this.returnValue;
        }
        this.returnValue = new SaLExp(tete, queue) ;
    }
    @Override
    public void caseAListExpSeparatorListExp2(AListExpSeparatorListExp2 node){
        SaExp tete = null ;
        SaLExp queue = null ;
        if(node.getExp() != null) {
            node.getExp().apply(this);
            tete = (SaExp) this.returnValue;
        }
        if(node.getListExp2() != null) {
            node.getListExp2().apply(this);
            queue = (SaLExp) this.returnValue;
        }
        this.returnValue = new SaLExp(tete, queue) ;
    }
    @Override
    public void caseAEpsilonListDecVariable(AEpsilonListDecVariable node){
        this.returnValue = null ;
    }
    @Override
    public void caseAEpsilonListDecVariable2(AEpsilonListDecVariable2 node){
        this.returnValue = null ;
    }
    @Override
    public void caseAEpsilonListDecVariableOpt(AEpsilonListDecVariableOpt node){
        this.returnValue = null ;
    }
    @Override
    public void caseAEpsilonListDecFunction(AEpsilonListDecFunction node){
        this.returnValue = null ;
    }
    @Override
    public void caseAEpsilonListExp(AEpsilonListExp node){
        this.returnValue = null ;
    }
    @Override
    public void caseAEpsilonListExp2(AEpsilonListExp2 node){
        this.returnValue = null ;
    }
    @Override
    public void caseAInstructionListListInst(AInstructionListListInst node){
        //SaLInst
        SaInst tete = null ;
        SaLInst queue = null ;
        if(node.getInstruction() != null) {
            node.getInstruction().apply(this);
            tete = (SaInst) this.returnValue;
        }
        if(node.getListInst() != null) {
            node.getListInst().apply(this);
            queue = (SaLInst) this.returnValue;
        }
        this.returnValue = new SaLInst(tete, queue) ;
    }
    @Override
    public void caseAInstTantQueInstruction(AInstTantQueInstruction node){
        node.getInstTantQue().apply(this);
    }
    @Override
    public void caseAInstReturnInstruction(AInstReturnInstruction node){
        node.getInstReturn().apply(this);
    }
    @Override
    public void caseAInstSiInstruction(AInstSiInstruction node){
        node.getInstSi().apply(this);
    }
    @Override
    public void caseAInstWriteInstruction(AInstWriteInstruction node){
        node.getInstWrite().apply(this);
    }
    @Override
    public void caseADecVariableListDecVariable2(ADecVariableListDecVariable2 node){
        SaDec tete = null ;
        SaLDec queue = null ;
        if(node.getDecVariable() != null) {
            node.getDecVariable().apply(this);
            tete = (SaDec) this.returnValue;
        }
        if(node.getListDecVariable2() != null) {
            node.getListDecVariable2().apply(this);
            queue = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(tete, queue) ;
    }
    @Override
    public void caseADecVarListDecVariableOpt(ADecVarListDecVariableOpt node){
        SaDec tete = null ;
        SaLDec queue = null ;
        if(node.getDecVariable() != null) {
            node.getDecVariable().apply(this);
            tete = (SaDec) this.returnValue;
        } if(node.getListDecVariableOpt2() != null) {
            node.getListDecVariableOpt2().apply(this);
            queue = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(tete, queue) ;
    }
    @Override
    public void caseAList2ListDecVariableOpt2(AList2ListDecVariableOpt2 node){
        SaDec tete = null ;
        SaLDec queue = null ;
        if(node.getDecVariable() != null) {
            node.getDecVariable().apply(this);
            tete = (SaDec) this.returnValue;
        }
        if(node.getListDecVariableOpt2() != null) {
            node.getListDecVariableOpt2().apply(this);
            queue = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaLDec(tete, queue) ;
    }
    @Override
    public void caseAArrayInstTableau(AArrayInstTableau node){
        String nom = node.getId().getText() ;
        SaExp indice = null ;
        node.getExp().apply(this);
        indice = (SaExp) this.returnValue;
        this.returnValue = new SaVarIndicee(nom, indice) ;
    }
    @Override
    public void caseAInstCallFunctionInstruction(AInstCallFunctionInstruction node){
        node.getInstCallFunction().apply(this);
    }
    @Override
    public void caseAExp1Exp(AExp1Exp node){
        node.getExp1().apply(this);
    }
    @Override
    public void caseAExp2Exp1(AExp2Exp1 node){
        node.getExp2().apply(this);
    }
    @Override
    public void caseAExp3Exp2(AExp3Exp2 node){
        node.getExp3().apply(this);
    }
    @Override
    public void caseAExp4Exp3(AExp4Exp3 node){
        node.getExp4().apply(this);
    }
    @Override
    public void caseAExp5Exp4(AExp5Exp4 node){
        node.getExp5().apply(this);
    }
    @Override
    public void caseAExp6Exp5(AExp6Exp5 node){
        node.getExp6().apply(this);
    }
    @Override
    public void caseAExpExp6(AExpExp6 node){
        node.getExp().apply(this);
    }
    @Override
    public void caseACallFunctionExp6(ACallFunctionExp6 node){
        node.getCallFunction().apply(this) ;
    }
    @Override
    public void caseAEpsilonListDecVariableOpt2(AEpsilonListDecVariableOpt2 node){
        this.returnValue = null ;
    }
    @Override

    public void caseAInstAffectationInstruction(AInstAffectationInstruction node){
        node.getInstAffectation().apply(this) ;
    }
    @Override
    public void caseAOuExp(AOuExp node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2) ;
    }
    @Override
    public void caseAMultiplicationExp4(AMultiplicationExp4 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2) ;
    }
    @Override
    public void caseADivExp4(ADivExp4 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2) ;
    }





    @Override
    public void caseAPlusExp3(APlusExp3 node){

        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp3().apply(this);
        inAPlusExp3(node);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2) ;
        outAPlusExp3(node);
    }
    @Override
    public void caseAMinusExp3(AMinusExp3 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2) ;
    }
    @Override
    public void caseANonExp5(ANonExp5 node){
        SaExp op = null ;
        node.getExp5().apply(this);
        op = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op) ;

    }
    @Override
    public void caseAEqualExp2(AEqualExp2 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2) ;
    }
    @Override
    public void caseAInfExp2(AInfExp2 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2) ;
    }

    @Override
    public void caseAEtExp1(AEtExp1 node){
        SaExp op1 = null ;
        SaExp op2 = null ;
        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAnd(op1, op2) ;
    }
}
