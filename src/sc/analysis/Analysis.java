/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAListDecVariableOptProgramme(AListDecVariableOptProgramme node);
    void caseAOuExp(AOuExp node);
    void caseAExp1Exp(AExp1Exp node);
    void caseAIncrementeInstIncremente(AIncrementeInstIncremente node);
    void caseATernaireInstTernaire(ATernaireInstTernaire node);
    void caseAListExpCallFunction(AListExpCallFunction node);
    void caseAEpsilonListExp(AEpsilonListExp node);
    void caseAListExpressionsListExp(AListExpressionsListExp node);
    void caseAEpsilonListExp2(AEpsilonListExp2 node);
    void caseAListExpSeparatorListExp2(AListExpSeparatorListExp2 node);
    void caseAAffectationInstAffectation(AAffectationInstAffectation node);
    void caseAAffectationTableauInstAffectation(AAffectationTableauInstAffectation node);
    void caseACallFunctionInstCallFunction(ACallFunctionInstCallFunction node);
    void caseATantQueInstTantQue(ATantQueInstTantQue node);
    void caseAReturnInstReturn(AReturnInstReturn node);
    void caseASiInstSi(ASiInstSi node);
    void caseASinonInstSiSinon(ASinonInstSiSinon node);
    void caseAEpsilonInstSiSinon(AEpsilonInstSiSinon node);
    void caseAWriteInstWrite(AWriteInstWrite node);
    void caseAInstAffectationInstruction(AInstAffectationInstruction node);
    void caseAInstCallFunctionInstruction(AInstCallFunctionInstruction node);
    void caseAInstTantQueInstruction(AInstTantQueInstruction node);
    void caseAInstReturnInstruction(AInstReturnInstruction node);
    void caseAInstSiInstruction(AInstSiInstruction node);
    void caseAInstWriteInstruction(AInstWriteInstruction node);
    void caseAListInstructionsInstBloc(AListInstructionsInstBloc node);
    void caseAInstructionListListInst(AInstructionListListInst node);
    void caseAEpsilonListInst(AEpsilonListInst node);
    void caseATypeDecVariable(ATypeDecVariable node);
    void caseATableauDecVariable(ATableauDecVariable node);
    void caseAArrayInstTableau(AArrayInstTableau node);
    void caseAEntierType(AEntierType node);
    void caseAListDecVariable2ListDecVariable(AListDecVariable2ListDecVariable node);
    void caseAEpsilonListDecVariable(AEpsilonListDecVariable node);
    void caseADecVariableListDecVariable2(ADecVariableListDecVariable2 node);
    void caseAEpsilonListDecVariable2(AEpsilonListDecVariable2 node);
    void caseADecVarListDecVariableOpt(ADecVarListDecVariableOpt node);
    void caseAEpsilonListDecVariableOpt(AEpsilonListDecVariableOpt node);
    void caseAList2ListDecVariableOpt2(AList2ListDecVariableOpt2 node);
    void caseAEpsilonListDecVariableOpt2(AEpsilonListDecVariableOpt2 node);
    void caseAListDecVariableOptDecFunction(AListDecVariableOptDecFunction node);
    void caseADecFunctionListDecFunction(ADecFunctionListDecFunction node);
    void caseAEpsilonListDecFunction(AEpsilonListDecFunction node);
    void caseAEtExp1(AEtExp1 node);
    void caseAExp2Exp1(AExp2Exp1 node);
    void caseAEqualExp2(AEqualExp2 node);
    void caseAInfExp2(AInfExp2 node);
    void caseAExp3Exp2(AExp3Exp2 node);
    void caseAPlusExp3(APlusExp3 node);
    void caseAMinusExp3(AMinusExp3 node);
    void caseAExp4Exp3(AExp4Exp3 node);
    void caseAMultiplicationExp4(AMultiplicationExp4 node);
    void caseADivExp4(ADivExp4 node);
    void caseAExp5Exp4(AExp5Exp4 node);
    void caseANonExp5(ANonExp5 node);
    void caseAExp6Exp5(AExp6Exp5 node);
    void caseAExpExp6(AExpExp6 node);
    void caseANumberExp6(ANumberExp6 node);
    void caseAIdExp6(AIdExp6 node);
    void caseATableauExp6(ATableauExp6 node);
    void caseACallFunctionExp6(ACallFunctionExp6 node);

    void caseTNumber(TNumber node);
    void caseTEt(TEt node);
    void caseTOu(TOu node);
    void caseTPlus(TPlus node);
    void caseTInf(TInf node);
    void caseTMinus(TMinus node);
    void caseTDiv(TDiv node);
    void caseTMultiply(TMultiply node);
    void caseTEqual(TEqual node);
    void caseTNon(TNon node);
    void caseTAccOuv(TAccOuv node);
    void caseTAccFer(TAccFer node);
    void caseTCrochetOuv(TCrochetOuv node);
    void caseTCrochetFer(TCrochetFer node);
    void caseTInterrogation(TInterrogation node);
    void caseTParOuv(TParOuv node);
    void caseTParFer(TParFer node);
    void caseTComma(TComma node);
    void caseTPointVir(TPointVir node);
    void caseTPlusEqual(TPlusEqual node);
    void caseTFaire(TFaire node);
    void caseTTantQue(TTantQue node);
    void caseTRetour(TRetour node);
    void caseTSi(TSi node);
    void caseTAlors(TAlors node);
    void caseTEcrire(TEcrire node);
    void caseTSinon(TSinon node);
    void caseTEntier(TEntier node);
    void caseTId(TId node);
    void caseTBlank(TBlank node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
