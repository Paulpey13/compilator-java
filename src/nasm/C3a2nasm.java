package nasm;
import ts.*;
import c3a.*;

public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private NasmRegister esp;
    private NasmRegister ebp;
    private NasmRegister ebx;
    private static int currentNb = 0;
    private static final int size = 4;


    public C3a2nasm(C3a c3a, Ts tableGlobale){
        this.c3a = c3a;
        this.tableGlobale = tableGlobale;
        nasm = new Nasm(tableGlobale);
        nasm.setTempCounter(c3a.getTempCounter());
        this.tableGlobale = tableGlobale;
        this.currentFct = null;
        esp = new NasmRegister(Nasm.REG_ESP);
        esp.colorRegister(Nasm.REG_ESP);
        ebp = new NasmRegister(Nasm.REG_EBP);
        ebp.colorRegister(Nasm.REG_EBP);

        ebx = new NasmRegister(Nasm.REG_EBX);
        ebx.colorRegister(Nasm.REG_EBX);
        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmCall(null,new NasmLabel("main"), ""));
        nasm.ajouteInst(new NasmMov(null,ebx,new NasmConstant(0), ""));
        nasm.ajouteInst(new NasmMov(null,eax,new NasmConstant(1), ""));
        nasm.ajouteInst(new NasmInt(null, ""));

        NasmOperand res;
        for(C3aInst c3aInst : c3a.listeInst){
            System.out.println("<" + c3aInst.getClass().getSimpleName() + ">");
            res = c3aInst.accept(this);
        }
    }

    public Nasm getNasm(){return nasm;}

    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label,dest,oper1,""));
        nasm.ajouteInst(new NasmAdd(null,dest,oper2,""));
        return null;
    }

    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) :null;
        NasmOperand op1 =inst.op1.accept( this );
        NasmOperand op2 =inst.op2.accept( this );
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst( new NasmMov(label, dest,op1,"") );
        nasm.ajouteInst( new NasmSub(null, dest, op2, ""));
        return null;
    }

    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand label0 = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label0,dest,op1, ""));
        nasm.ajouteInst(new NasmMul(null,dest,op2, ""));
        return null;
    }

    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) :null;

        NasmOperand op2 = inst.op2.accept( this );
        NasmOperand op1 = inst.op1.accept( this );
        NasmOperand destination = inst.result.accept( this );

        NasmRegister reg_eax = nasm.newRegister();
        reg_eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst( new NasmMov(label, reg_eax,op1,"") );

        if( op2 instanceof NasmConstant){
            NasmRegister temp_register = nasm.newRegister();
            temp_register.colorRegister(Nasm.REG_EBX);
            nasm.ajouteInst(new NasmMov(null,temp_register,op2, ""));
            nasm.ajouteInst(new NasmDiv(null,temp_register, ""));
        }else {
            nasm.ajouteInst(new NasmDiv(null,op2, ""));
        }
        nasm.ajouteInst( new NasmMov(null,destination,reg_eax, ""));
        return null;
    }

    public NasmOperand visit(C3aInstFEnd inst) {
        NasmLabel label = (inst.label != null) ? new NasmLabel(inst.label.toString()): null;
        nasm.ajouteInst( new NasmAdd(label, esp,
                new NasmConstant(size *currentNb),""));
        nasm.ajouteInst( new NasmPop(null,ebp,""));
        nasm.ajouteInst( new NasmRet(null,""));
        return null;
    }

    public NasmOperand visit(C3aInst inst) {
        return inst.accept(this );
    }

    public NasmOperand visit(C3aInstJumpIfLess inst) {
        NasmLabel label = inst.label != null ? new NasmLabel( inst.label.toString() ) : null;
        NasmOperand op1 = inst.op1.accept(this );
        NasmOperand op2 = inst.op2.accept(this );
        NasmOperand destination =  inst.result.accept( this );
        if( inst.op1 instanceof C3aConstant ){
            NasmRegister temp_reg = this.nasm.newRegister();
            nasm.ajouteInst(new NasmMov(label,temp_reg,op1,""  ));
            nasm.ajouteInst(new NasmCmp(null,temp_reg,op2,""));
        }else {
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        }
        nasm.ajouteInst(new NasmJl(null,destination,"" ));
        return null;
    }

    public NasmOperand visit(C3aInstFBegin inst) {
        this.currentNb= inst.val.getTable().nbVar();
        String funcName = inst.val.getIdentif();
        NasmLabel funcLabel = new NasmLabel( funcName );
        nasm.ajouteInst( new NasmPush(funcLabel,ebp,""));
        nasm.ajouteInst( new NasmMov(null,ebp,esp,""));

        NasmOperand shift = new NasmConstant(size*currentNb );
        nasm.ajouteInst( new NasmSub(null,esp,shift,""));
        return null;
    }

    public NasmOperand visit(C3aInstCall inst) {
        NasmLabel label = (inst.label != null) ? new NasmLabel(inst.label.toString()): null;

        NasmConstant returnValuSpace = new NasmConstant(size);
        nasm.ajouteInst( new NasmSub(null,esp,returnValuSpace,""));

        NasmLabel functionName = new NasmLabel(inst.op1.getValue().getIdentif());
        nasm.ajouteInst(new NasmCall(null,functionName, ""));

        NasmOperand returnRegister = inst.result.accept(this);
        nasm.ajouteInst(new NasmPop(null,returnRegister,""));

        if( inst.op1.val.nbArgs > 0 ) {
            NasmConstant space_to_free = new NasmConstant(size*inst.op1.getValue().getNbArgs());
            nasm.ajouteInst(new NasmAdd(label,esp,space_to_free,""));
        }
        return null;
    }

    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) :null;
        NasmRegister reg_eax = nasm.newRegister();
        reg_eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(label, reg_eax, inst.op1.accept(this), "" ));
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"),""));
        return null;
    }

    public NasmOperand visit(C3aInstRead inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) :null;
        NasmRegister reg_eax = nasm.newRegister();
        reg_eax.colorRegister(Nasm.REG_EAX);
        NasmLabel sinput = new NasmLabel("sinput");
        nasm.ajouteInst(new NasmMov(label, reg_eax, sinput,""));
        NasmLabel readline = new NasmLabel("readline");
        nasm.ajouteInst(new NasmCall(null, readline, ""));
        NasmLabel atoi = new NasmLabel("atoi");
        nasm.ajouteInst(new NasmCall(null, atoi, ""));
        nasm.ajouteInst(new NasmMov(null, inst.result.accept(this), reg_eax,""));
        return null;

    }

    public NasmOperand visit(C3aInstReturn inst) {
        NasmLabel label = (inst.label != null) ? new NasmLabel(inst.label.toString()): null;
        NasmOperand return_value = inst.op1.accept(this );
        NasmOperand shift = new NasmConstant(2);
        NasmAddress return_adr = new NasmAddress(ebp,'+', shift);
        nasm.ajouteInst(new NasmMov(label,return_adr,return_value,"") );
        return null;
    }

    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand label = (inst.label !=null) ? inst.label.accept(this) :null;
        NasmOperand result = inst.result.accept(this );
        NasmOperand op1 = inst.op1.accept(this );
        NasmRegister temp_reg = nasm.newRegister();
        if( inst.result instanceof C3aVar && inst.op1 instanceof C3aVar ){
            nasm.ajouteInst(new NasmMov(label,temp_reg,op1,""));
            nasm.ajouteInst(new NasmMov(null,result,temp_reg,""));
        }else {
            nasm.ajouteInst(new NasmMov(label,result,op1,""));
        }
        return null;
    }

    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand param = inst.op1.accept(this );
        nasm.ajouteInst(new NasmPush(null,param,""));
        return null;
    }

    public NasmOperand visit(C3aInstJump inst) {
        NasmLabel label = inst.label != null ? new NasmLabel(inst.label.toString()) : null;
        NasmOperand destination = inst.result.accept(this);
        nasm.ajouteInst(new NasmJmp(label,destination,"") );
        return null;
    }

    public NasmOperand visit(C3aVar oper) {
        if( oper.item.portee == tableGlobale ){
            if( oper.index == null){
                NasmLabel varName = new NasmLabel( oper.item.getIdentif() );
                return new NasmAddress( varName );
            }else{
                NasmLabel array_name = new NasmLabel(oper.item.getIdentif());
                char direction = oper.item.isParam ? '-' : '+';
                return new NasmAddress( array_name, direction, oper.index.accept(this) );
            }
        }
        else if ( oper.item.isParam ){
            NasmConstant offset = new NasmConstant( 2 + oper.item.portee.nbArg() - oper.item.getAdresse() );
            return  new NasmAddress(ebp, '+', offset );
        }else {
            NasmConstant offset = new NasmConstant(1+oper.item.getAdresse());
            return new NasmAddress(ebp, '-', offset);
        }
    }

    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        NasmLabel label = inst.label != null ? new NasmLabel( inst.label.toString() ) : null;
        NasmOperand op1 = inst.op1.accept(this );
        NasmOperand op2 = inst.op2.accept(this );
        NasmOperand destination =  inst.result.accept( this );
        if( inst.op1 instanceof C3aConstant ){
            NasmRegister temp_reg = nasm.newRegister();
            nasm.ajouteInst(new NasmMov(label,temp_reg,op1,""  ));
            nasm.ajouteInst(new NasmCmp(null,temp_reg,op2,""));
        }else {
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        }
        nasm.ajouteInst(new NasmJe(null,destination,"" ));
        return null;
    }

    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        NasmLabel label = inst.label != null ? new NasmLabel( inst.label.toString() ) : null;
        NasmOperand op1 = inst.op1.accept(this );
        NasmOperand op2 = inst.op2.accept(this );
        NasmOperand destination =  inst.result.accept( this );
        if( inst.op1 instanceof C3aConstant ){
            NasmRegister register = nasm.newRegister();
            nasm.ajouteInst(new NasmMov(label,register,op1,""));
            nasm.ajouteInst(new NasmCmp(null,register,op2,"" ));
        }
        else{
            nasm.ajouteInst(new NasmCmp(label,op1,op2,""));
        }
        nasm.ajouteInst(new NasmJne(null,destination,""));
        return null;
    }

    public NasmOperand visit(C3aInstStop inst) {

        return null;
    }

    public NasmOperand visit(C3aLabel oper) {
        return new NasmLabel( oper.toString() );
    }

    public NasmOperand visit(C3aConstant oper) {
        return new NasmConstant( oper.val );
    }

    public NasmOperand visit(C3aTemp oper) {
        return new NasmRegister( oper.num );
    }

    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel( oper.toString() );
    }
}
