package worms.model;


import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Matchers;
import org.mockito.Mock;

import org.junit.Assert;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

import worms.model.programs.ProgramFactory;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.parser.WormsParserMyListener;
import worms.model.programs.parser.WormsParserParser;
import worms.model.programs.parser.WormsParserParser.ActionContext;
import worms.model.programs.parser.WormsParserParser.AssignContext;
import worms.model.programs.parser.WormsParserParser.BinopContext;
import worms.model.programs.parser.WormsParserParser.CtrlContext;
import worms.model.programs.parser.WormsParserParser.DeclContext;
import worms.model.programs.parser.WormsParserParser.EntityspecContext;
import worms.model.programs.parser.WormsParserParser.EvalContext;
import worms.model.programs.parser.WormsParserParser.ExprContext;
import worms.model.programs.parser.WormsParserParser.ForeachContext;
import worms.model.programs.parser.WormsParserParser.IfthenelseContext;
import worms.model.programs.parser.WormsParserParser.NamedconstContext;
import worms.model.programs.parser.WormsParserParser.TypeContext;
import worms.model.programs.parser.WormsParserParser.UnopContext;
import worms.model.programs.parser.WormsParserParser.WhiledoContext;
import worms.model.part3.*;


public class WormsParserMyListenerTest {

	private WormsParserMyListener wpml;
	@Mock
	private ProgramFactory<Expression<Type>, Statement, Type> factory;
	@Mock
	private MyToken token;

	@BeforeEach
	public void setUp() {
		token = mock(MyToken.class);
		factory = (ProgramFactory<Expression<Type>, Statement, Type>) mock(ProgramFactory.class);
		wpml = new WormsParserMyListener(factory);
		//when(factory.createSequence(anyInt(), anyInt(),
				//Matchers.anyListOf(Statement.class))).thenReturn(new SequenceOfStatements(new ArrayList<Statement>(),5));
		
		
		
	}

	/*public class WPML extends WormsParserMyListener{
		
		public WPML(ProgramFactory<Expression<Type>, Statement, Type> factory) {
			super(factory);
		}
	};*/
	
	public static Collection<Object[]> lista(){
		return Arrays.asList(new Object[][] {
			{null,null,null,null,null,null,null,null,null},//a
			{mock(EvalContext.class), mock(AssignContext.class), mock(CtrlContext.class), mock(ActionContext.class),
				mock(DeclContext.class), mock(TypeContext.class), mock(TerminalNode.class), null, null},//b
			{mock(EvalContext.class),null,null,null,null,null,null,null,null},//c
			//{mock(EvalContext.class), mock(AssignContext.class), mock(CtrlContext.class), mock(ActionContext.class),
				//mock(DeclContext.class), mock(TypeContext.class), mock(TerminalNode.class),mock(ExprContext.class), 
				//null}//d
		});
	}
	
	public static Collection<Object[]> lista2(){
		return Arrays.asList(new Object[][] {
			{null,0},{null,1},{null,2},{null,3}
		});
	}
	
	public static Collection<Object[]> lista3(){
		return Arrays.asList(new Object[][] {
			{null,null},{mock(TerminalNode.class),null}
		});
	}
	
	public static Collection<Object[]> lista4(){
		return Arrays.asList(new Object[][] {
			{null,0,0,null,null,null,null,null},
			{null,0,1,mock(NamedconstContext.class),null,null,null,null},
			{null,0,2,mock(NamedconstContext.class),null,null,null,null},
			{null,0,3,mock(NamedconstContext.class),null,null,null,null},
			{null,0,4,mock(NamedconstContext.class),null,null,null,null},//5
			{null,0,5,mock(NamedconstContext.class),null,null,null,null},
			{null,0,0,null,mock(TerminalNode.class),null,null,null},
			{null,0,0,null,null,mock(TerminalNode.class),null,null},
			{null,0,0,null,null,null,mock(UnopContext.class),null},
			{null,0,1,null,null,null,mock(UnopContext.class),null},
			{null,0,2,null,null,null,mock(UnopContext.class),null},
			{null,0,3,null,null,null,mock(UnopContext.class),null},
			{null,0,4,null,null,null,mock(UnopContext.class),null},
			{null,0,5,null,null,null,mock(UnopContext.class),null},
			{null,0,6,null,null,null,mock(UnopContext.class),null},
			{null,0,7,null,null,null,mock(UnopContext.class),null},
			{null,0,8,null,null,null,mock(UnopContext.class),null},
			{null,0,9,null,null,null,mock(UnopContext.class),null},
			{null,0,10,null,null,null,mock(UnopContext.class),null},
			{null,0,11,null,null,null,mock(UnopContext.class),null},
			{null,0,12,null,null,null,mock(UnopContext.class),null},
			{null,0,13,null,null,null,mock(UnopContext.class),null},
			{null,0,14,null,null,null,mock(UnopContext.class),null},
			{null,0,15,null,null,null,mock(UnopContext.class),null},
			{null,0,16,null,null,null,mock(UnopContext.class),null},
			{null,1,0,null,null,null,null,null},
			{null,2,1,null,null,null,null,mock(BinopContext.class)},
			{null,2,2,null,null,null,null,mock(BinopContext.class)},
			{null,2,3,null,null,null,null,mock(BinopContext.class)},
			{null,2,4,null,null,null,null,mock(BinopContext.class)},
			{null,2,5,null,null,null,null,mock(BinopContext.class)},
			{null,2,6,null,null,null,null,mock(BinopContext.class)},
			{null,2,7,null,null,null,null,mock(BinopContext.class)},
			{null,2,8,null,null,null,null,mock(BinopContext.class)},
			{null,2,9,null,null,null,null,mock(BinopContext.class)},
			{null,2,10,null,null,null,null,mock(BinopContext.class)},
			{null,2,11,null,null,null,null,mock(BinopContext.class)},
			{null,2,12,null,null,null,null,mock(BinopContext.class)},
			{null,2,13,null,null,null,null,mock(BinopContext.class)},
			{null,2,0,null,null,null,null,null},
			{null,3,0,null,null,null,null,null}
		});
	}

	@Test
	@ParameterizedTest
	@MethodSource("lista")
	public void enterEvalTest(EvalContext eval, AssignContext assign, CtrlContext ctrl, ActionContext action,
			DeclContext decl, TypeContext type, TerminalNode term, ExprContext expr, Statement s) {
		when(factory.createSequence(anyInt(), anyInt(), Matchers.anyListOf(Statement.class))).thenReturn(s);
		//when(factory.createAssignment(anyInt(), anyInt(), anyString(), any(Expression.class))).thenReturn(s);
		if (eval!=null) {
			when(eval.getStart()).thenReturn(token);
			when(eval.assign()).thenReturn(assign);
			when(eval.ctrl()).thenReturn(ctrl);
			when(eval.action()).thenReturn(action);
			when(eval.decl()).thenReturn(decl);
			when(eval.PRINT()).thenReturn(term);
			when(eval.eval()).thenReturn(null);
			if (action != null) {
				when(assign.expr()).thenReturn(expr);
				when(assign.IDENTIFIER()).thenReturn(term);
				when(term.getSymbol()).thenReturn(token);
				when(action.getStart()).thenReturn(token);
				when(decl.type()).thenReturn(type);
			}
			wpml.enterEval(eval);
			Assert.assertEquals(s, wpml.getStatement());
		}else {
			wpml.enterEval(eval);
			Assert.assertEquals(s, wpml.getStatement());			
		}
	}
	
	@Test
	@ParameterizedTest
	@MethodSource("lista2")
	public void StatementOfIfthenelseTest(Statement s, int size) {
		CtrlContext ctrl = mock(CtrlContext.class);
		EvalContext eval = mock(EvalContext.class);
		IfthenelseContext ctx = mock(IfthenelseContext.class);
		List<EvalContext> e = (List<EvalContext>) mock(List.class);
		when(eval.getStart()).thenReturn(token);
		when(eval.ctrl()).thenReturn(ctrl);
		when(ctrl.ifthenelse()).thenReturn(ctx);
		when(ctx.getStart()).thenReturn(token);
		when(token.getLine()).thenReturn(0);
		when(ctx.eval()).thenReturn(e);
		when(e.size()).thenReturn(size);
		wpml.enterEval(eval);
		Assert.assertEquals(s, wpml.getStatement());
	}
	
	@Test
	@ParameterizedTest
	@MethodSource("lista3")
	public void StatementOfForeachTest(TerminalNode term, Statement s) {
		EntityspecContext ent = mock(EntityspecContext.class);
		TerminalNode t = mock(TerminalNode.class);
		CtrlContext ctrl = mock(CtrlContext.class);
		EvalContext eval = mock(EvalContext.class);
		ForeachContext ctx = mock(ForeachContext.class);
		when(eval.getStart()).thenReturn(token);
		when(eval.ctrl()).thenReturn(ctrl);
		when(ctx.eval()).thenReturn(null);
		when(ctrl.foreach()).thenReturn(ctx);
		when(ctx.getStart()).thenReturn(token);
		when(token.getLine()).thenReturn(0);
		when(ctx.entityspec()).thenReturn(ent);
		when(ent.ANY()).thenReturn(term);
		when(ent.WORM()).thenReturn(term);
		when(ent.FOOD()).thenReturn(term);
		when(ctx.IDENTIFIER()).thenReturn(t);
		when(t.getText()).thenReturn("");
		when(factory.createForeach(anyInt(), anyInt(), any(ForeachType.class), anyString(), any(Statement.class))).thenReturn(null);
		wpml.enterEval(eval);
		Assert.assertEquals(s, wpml.getStatement());
	}
	
	@Test
	@ParameterizedTest
	@MethodSource("lista4")
	public void StatementOfWhiledoTest(Statement s, int size, int opt, NamedconstContext ncc,TerminalNode t,
			TerminalNode term, UnopContext unop, BinopContext binop) {
		EntityspecContext ent = mock(EntityspecContext.class);
		CtrlContext ctrl = mock(CtrlContext.class);
		EvalContext eval = mock(EvalContext.class);
		ExprContext expr = mock(ExprContext.class);
		ExprContext e1 = mock(ExprContext.class);
		ExprContext e2 = mock(ExprContext.class);
		List<ExprContext> e = (List<ExprContext>) mock(List.class);
		WhiledoContext ctx = mock(WhiledoContext.class);
		when(eval.getStart()).thenReturn(token);
		when(eval.ctrl()).thenReturn(ctrl);
		when(ctx.eval()).thenReturn(null);
		when(ctrl.whiledo()).thenReturn(ctx);
		when(ctx.getStart()).thenReturn(token);
		when(token.getLine()).thenReturn(0);
		when(ctx.expr()).thenReturn(expr);
		when(ctx.eval()).thenReturn(null);
		when(factory.createWhile(anyInt(), anyInt(),(Expression<Type>) any(Expression.class),any(Statement.class))).thenReturn(s);
		when(expr.getStart()).thenReturn(token);
		when(expr.expr()).thenReturn(e);
		when(e.size()).thenReturn(size);
		if (ncc!=null) {
			TerminalNode tn1=null;
			TerminalNode tn2=null;
			TerminalNode tn3=null;
			TerminalNode tn4=null;
			when(expr.namedconst()).thenReturn(ncc);
			when(ncc.getStart()).thenReturn(token);
			if (opt==1) tn1 = mock(TerminalNode.class);
			else if (opt==2) tn2 = mock(TerminalNode.class);
			else if (opt==3) tn3 = mock(TerminalNode.class);
			else if (opt==4) tn4 = mock(TerminalNode.class);
			when(ncc.FALSE()).thenReturn(tn1);
			when(ncc.TRUE()).thenReturn(tn2);
			when(ncc.SELF()).thenReturn(tn3);
			when(ncc.NULL()).thenReturn(tn4);
			when(factory.createBooleanLiteral(anyInt(), anyInt(), anyBoolean())).thenReturn(null);
			when(factory.createSelf(anyInt(), anyInt())).thenReturn(null);
			when(factory.createNull(anyInt(), anyInt())).thenReturn(null);
		}
		else if (t!=null) {
			when(expr.NUMBER()).thenReturn(t);
			when(t.getSymbol()).thenReturn(token);
			when(t.getText()).thenReturn("5");
			when(factory.createDoubleLiteral(anyInt(), anyInt(), anyDouble())).thenReturn(null);
		}
		else if (term!=null)
			when(expr.IDENTIFIER()).thenReturn(term);
		else if (unop!=null) {
			when(expr.unop()).thenReturn(unop);
			when(unop.getStart()).thenReturn(token);
			TerminalNode[] tn=new TerminalNode[16];
			for (int i=0;i<16;i++) tn[i]=null;
			for (int i=1;i<17;i++) {
				if (opt==i) tn[i-1]=mock(TerminalNode.class);
			}
			when(unop.GETRADIUS()).thenReturn(tn[0]);
			when(unop.GETDIR()).thenReturn(tn[1]);
			when(unop.GETY()).thenReturn(tn[2]);
			when(unop.GETX()).thenReturn(tn[3]);
			when(unop.GETAP()).thenReturn(tn[4]);
			when(unop.GETMAXAP()).thenReturn(tn[5]);
			when(unop.GETHP()).thenReturn(tn[6]);
			when(unop.GETMAXHP()).thenReturn(tn[7]);
			when(unop.NOT()).thenReturn(tn[8]);
			when(unop.SQRT()).thenReturn(tn[9]);
			when(unop.SIN()).thenReturn(tn[10]);
			when(unop.COS()).thenReturn(tn[11]);
			when(unop.SAMETEAM()).thenReturn(tn[12]);
			when(unop.SEARCHOBJ()).thenReturn(tn[13]);
			when(unop.ISWORM()).thenReturn(tn[14]);
			when(unop.ISFOOD()).thenReturn(tn[15]);
		}
		when(expr.binop()).thenReturn(binop);
		if (opt!=0 && binop!=null) {
			when(e.get(0)).thenReturn(e1);
			when(e.get(1)).thenReturn(e2);
			when(e1.getStart()).thenReturn(token);
			when(e2.getStart()).thenReturn(token);
			TerminalNode[] tn=new TerminalNode[12];
			for (int i=0;i<12;i++) tn[i]=null;
			for (int i=1;i<13;i++) {
				if (opt==i) tn[i-1]=mock(TerminalNode.class);
			}
			when(binop.GT()).thenReturn(tn[0]);
			when(binop.LT()).thenReturn(tn[1]);
			when(binop.SUB()).thenReturn(tn[2]);
			when(binop.NEQ()).thenReturn(tn[3]);
			when(binop.GEQ()).thenReturn(tn[4]);
			when(binop.EQ()).thenReturn(tn[5]);
			when(binop.DIV()).thenReturn(tn[6]);
			when(binop.MUL()).thenReturn(tn[7]);
			when(binop.OR()).thenReturn(tn[8]);
			when(binop.AND()).thenReturn(tn[9]);
			when(binop.LEQ()).thenReturn(tn[10]);
			when(binop.ADD()).thenReturn(tn[11]);
		}
		//when(ctx.entityspec()).thenReturn(ent);
		//when(ent.ANY()).thenReturn(term);
		//when(ent.WORM()).thenReturn(term);
		//when(ent.FOOD()).thenReturn(term);
		//when(ctx.IDENTIFIER()).thenReturn(t);
		//when(t.getText()).thenReturn("");
		wpml.enterEval(eval);
		Assert.assertEquals(s, wpml.getStatement());
	}

}