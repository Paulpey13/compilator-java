package c3a;
import java.util.*;
import ts.*;
import sa.*;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
	private C3a c3a;
	int indentation;
	public C3a getC3a(){return this.c3a;}
	Ts tableGlobale ;

	public Sa2c3a(SaNode root, Ts tableGlobale){
		this.tableGlobale = tableGlobale ;
		c3a = new C3a();
		C3aTemp result = c3a.newTemp();
		C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
		c3a.ajouteInst(new C3aInstCall(fct, result, ""));
		c3a.ajouteInst(new C3aInstStop(result, ""));
		indentation = 0;
		root.accept(this);
	}

	public void defaultIn(SaNode node)
	{
		for(int i = 0; i < indentation; i++){System.out.print(" ");}
		indentation++;
		System.out.println("<" + node.getClass().getSimpleName() + ">");
	}

	public void defaultOut(SaNode node)
	{
		indentation--;
		for(int i = 0; i < indentation; i++){System.out.print(" ");}
		System.out.println("</" + node.getClass().getSimpleName() + ">");
	}


	public C3aOperand visit(SaProg node){
		defaultIn(node);

		if(node.getVariables() != null) {
			node.getVariables().accept(this);
		}
		node.getFonctions().accept(this) ;

		defaultOut(node);
		return null ;
	}

	public C3aOperand visit(SaDecTab node){
		defaultIn(node);

		defaultOut(node);
		return null;
	}

	@Override
	public C3aOperand visit(SaInstIncremente node) {
		defaultIn(node);
		C3aOperand result = c3a.newTemp();
		defaultOut(node);
		return result;
	}



	public C3aOperand visit(SaDecFonc node){
		defaultIn(node);

		c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "entree fonction"));

		if(node.getParametres() != null){
			node.getParametres().accept(this) ;
		} else if(node.getVariable() != null){
			node.getVariable().accept(this) ;
		}
		node.getCorps().accept(this) ;

		c3a.ajouteInst(new C3aInstFEnd(""));

		defaultOut(node);
		return null;
	}

	public C3aOperand visit(SaExpInt node){
		defaultIn(node);

		C3aOperand result = new C3aConstant(node.getVal()) ;

		defaultOut(node);
		return result ;
	}

	public C3aOperand visit(SaExpVar node){
		defaultIn(node);

		C3aOperand result = node.getVar().accept(this) ;

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpAppel node){
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getVal().accept(this);

		c3a.ajouteInst(new C3aInstCall((C3aFunction) op1, result, "")); //op1 : C3aFunction

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpAdd node) {
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);

		c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpSub node) {
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;
		c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpMult node) {
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;
		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;
		c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpDiv node) {
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;
		c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));

		defaultOut(node);
		return result;
	}
	public C3aOperand visit(SaExpInf node) {
		defaultIn(node);

		C3aLabel e1 = c3a.newAutoLabel() ;
		C3aLabel e2 = c3a.newAutoLabel() ;
		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;

		c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, e1, ""));

		c3a.ajouteInst(new C3aInstAffect(c3a.False, result , ""));
		c3a.ajouteInst(new C3aInstJump(e2, ""));
		c3a.addLabelToNextInst(e1);
		c3a.ajouteInst(new C3aInstAffect(c3a.True , result, ""));
		c3a.addLabelToNextInst(e2);

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpEqual node) {
		defaultIn(node);

		C3aLabel e1 = c3a.newAutoLabel();
		C3aLabel e2 = c3a.newAutoLabel() ;
		C3aOperand result = c3a.newTemp() ;
		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;
		c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, e1, ""));
		c3a.ajouteInst(new C3aInstAffect( c3a.False,result, ""));
		c3a.ajouteInst(new C3aInstJump(e2, ""));
		c3a.addLabelToNextInst(e1);
		c3a.ajouteInst(new C3aInstAffect(c3a.True ,result , ""));

		c3a.addLabelToNextInst(e2);

		defaultOut(node);
		return result;
	}
	public C3aOperand visit(SaExpAnd node) {
		defaultIn(node);

		C3aOperand op1 = node.getOp1().accept(this) ;
		C3aOperand op2 = node.getOp2().accept(this) ;

		C3aLabel e1 = c3a.newAutoLabel() ;
		C3aLabel e2 = c3a.newAutoLabel() ;

		C3aOperand result = c3a.newTemp() ;
		c3a.ajouteInst(new C3aInstJumpIfEqual(op1 ,c3a.False, e1, ""));

		c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, e1, ""));

		c3a.ajouteInst(new C3aInstAffect(c3a.True , result, ""));

		c3a.ajouteInst(new C3aInstJump(e2, ""));

		c3a.addLabelToNextInst(e1);
		c3a.ajouteInst(new C3aInstAffect(c3a.False ,result, ""));
		c3a.addLabelToNextInst(e2);

		defaultOut(node);
		return result ;
	}

	public C3aOperand visit(SaExpOr node) {
		defaultIn(node);

		C3aLabel e1 = c3a.newAutoLabel() ;
		C3aLabel e2 = c3a.newAutoLabel() ;
		C3aOperand result = c3a.newTemp() ;

		C3aOperand op1 = node.getOp1().accept(this) ;
		c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.True, e1, ""));

		C3aOperand op2 = node.getOp2().accept(this) ;
		c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.True, e1, ""));

		c3a.ajouteInst(new C3aInstAffect(c3a.False , result, ""));

		c3a.ajouteInst(new C3aInstJump(e2, ""));

		c3a.addLabelToNextInst(e1);
		c3a.ajouteInst(new C3aInstAffect(c3a.True, result, ""));

		c3a.addLabelToNextInst(e2);

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpNot node) {
		defaultIn(node);

		C3aLabel e1 = c3a.newAutoLabel() ;
		C3aOperand result = c3a.newTemp() ;
		C3aOperand op1 = node.getOp1().accept(this) ;

		c3a.ajouteInst(new C3aInstAffect(c3a.True ,result , ""));

		c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, e1, ""));

		c3a.ajouteInst(new C3aInstAffect(c3a.False ,result , ""));

		c3a.addLabelToNextInst(e1);

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaExpLire node) {
		defaultIn(node);

		C3aOperand result = c3a.newTemp() ;

		c3a.ajouteInst(new C3aInstRead(result, ""));

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaInstRetour node) {
		defaultIn(node);

		C3aOperand result = node.getVal().accept(this) ;
		c3a.ajouteInst(new C3aInstReturn(result, ""));

		defaultOut(node);
		return null;
	}


	public C3aOperand visit(SaInstEcriture node) {
		defaultIn(node);

		C3aOperand op1 = node.getArg().accept(this) ;
		c3a.ajouteInst(new C3aInstWrite(op1, ""));

		defaultOut(node);
		return null ;
	}

	public C3aOperand visit(SaInstTantQue node){
		defaultIn(node);

		C3aLabel test = c3a.newAutoLabel() ;
		C3aLabel suite = c3a.newAutoLabel() ;

		c3a.addLabelToNextInst(test);

		visit(node.getTest())	 ;

		C3aOperand E = node.getTest().accept(this) ;

		c3a.ajouteInst(new C3aInstJumpIfEqual(E, c3a.False, suite, ""));

		node.getFaire().accept(this) ;

		c3a.ajouteInst(new C3aInstJump(test, ""));

		c3a.addLabelToNextInst(suite);

		defaultOut(node);
		return null;
	}

	public C3aOperand visit(SaInstAffect node) {
		defaultIn(node);

		C3aOperand lhs = node.getLhs().accept(this) ;
		C3aOperand rhs = node.getRhs().accept(this) ;

		c3a.ajouteInst(new C3aInstAffect(rhs, lhs, ""));

		defaultOut(node);
		return null;
	}

	public C3aOperand visit(SaVarSimple node){
		defaultIn(node);

		C3aOperand result = new C3aVar(node.tsItem, null) ;

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaAppel node){
		defaultIn(node);

		if(node.getArguments() != null) {
			C3aOperand argument = node.getArguments().getTete().accept(this);
			c3a.ajouteInst(new C3aInstParam(argument, ""));

			if (node.getArguments().getQueue() != null) {
				C3aOperand argument2 = node.getArguments().getQueue().getTete().accept(this);
				c3a.ajouteInst(new C3aInstParam(argument2, ""));
				if (node.getArguments().getQueue().getQueue() != null) {
					C3aOperand argument3 = node.getArguments().getQueue().getQueue().getTete().accept(this);
					c3a.ajouteInst(new C3aInstParam(argument3, ""));
					if (node.getArguments().getQueue().getQueue().getQueue() != null) {
						C3aOperand argument4 = node.getArguments().getQueue().getQueue().getQueue().getTete().accept(this);
						c3a.ajouteInst(new C3aInstParam(argument4, ""));
						if(node.getArguments().getQueue().getQueue().getQueue().getQueue() != null){
							C3aOperand argument5 = node.getArguments().getQueue().getQueue().getQueue().getQueue().getTete().accept(this) ;
							c3a.ajouteInst(new C3aInstParam(argument5, ""));
						}
					}
				}
			}
		}

		C3aOperand result = c3a.newTemp();
		C3aFunction function = new C3aFunction(node.tsItem) ;
		c3a.ajouteInst(new C3aInstCall(function, result, ""));

		defaultOut(node);
		return result;
	}

	public C3aOperand visit(SaInstSi node){
		defaultIn(node);

		node.getTest().accept(this) ;

		C3aLabel faux = c3a.newAutoLabel() ;
		C3aOperand E = node.getTest().accept(this) ;

		c3a.ajouteInst(new C3aInstJumpIfEqual(E, c3a.False, faux, ""));

		if(node.getAlors() != null){
			node.getAlors().accept(this) ;
		}

		C3aLabel suite = null ;

		if(node.getSinon() != null) {
			suite = c3a.newAutoLabel();
			c3a.ajouteInst(new C3aInstJump(suite, ""));
		} else {
			suite = faux ;
		}

		c3a.addLabelToNextInst(faux);

		if(node.getSinon() != null) {
			node.getSinon().accept(this);
		}
		c3a.addLabelToNextInst(suite);

		defaultOut(node);
		return null ;
	}

	public C3aOperand visit(SaLExp node){
		defaultIn(node);

		node.getTete().accept(this) ;
		if(node.getQueue() != null){
			node.getQueue().accept(this) ;
		}

		defaultOut(node);
		return null;
	}

	public C3aOperand visit(SaVarIndicee node){
		defaultIn(node);

		C3aVar var = new C3aVar(node.tsItem, node.getIndice().accept(this)) ;

		C3aOperand indice = var.index ;

		C3aOperand i = indice;

		if(indice instanceof C3aTemp || indice instanceof C3aConstant){
			i = indice ;
		} else {
			i = c3a.newTemp() ;
			c3a.ajouteInst(new C3aInstAffect(i, indice, ""));
		}
		C3aOperand result = new C3aVar(var.item, i) ;

		defaultOut(node);
		return result ;
	}


}

