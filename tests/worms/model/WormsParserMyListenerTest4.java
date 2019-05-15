package worms.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.mockito.Mock;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import worms.model.programs.ProgramFactory;
import worms.model.programs.parser.WormsParserMyListener;
import worms.model.programs.parser.WormsParserParser.BinopContext;
import worms.model.programs.parser.WormsParserParser.CtrlContext;
import worms.model.programs.parser.WormsParserParser.EvalContext;
import worms.model.programs.parser.WormsParserParser.ExprContext;
import worms.model.programs.parser.WormsParserParser.NamedconstContext;
import worms.model.programs.parser.WormsParserParser.UnopContext;
import worms.model.programs.parser.WormsParserParser.WhiledoContext;
import worms.model.part3.*;

@RunWith(Parameterized.class)
public class WormsParserMyListenerTest4 {

	private WormsParserMyListener wpml;
	
	@Mock
	private ProgramFactory<Expression<Type>, Statement, Type> factoryMock;
	@Mock
	private MyToken myTokenMock;
	
	@Before
	public void setUp() {
		myTokenMock = mock(MyToken.class);
		factoryMock = (ProgramFactory<Expression<Type>, Statement, Type>) mock(ProgramFactory.class);
		wpml = new WormsParserMyListener<Expression<Type>, Statement, Type>(factoryMock);	
	}
	
	@Parameters(name = "Run {index}: statement={0}, size={1}, option={2}, namedConstContextMock={3}, terminalNodeMock1={4}," + 
			"terminalNodeMock2={5}, unopContext={6}, binopContext={7}")
	public static Collection<Object[]> lista4(){
		return Arrays.asList(new Object[][] {
			{new Control(),0,0,null,null,null,null,null},
			{new Control(),0,1,mock(NamedconstContext.class),null,null,null,null},
			{new Control(),0,2,mock(NamedconstContext.class),null,null,null,null},
			{new Control(),0,3,mock(NamedconstContext.class),null,null,null,null},
			{new Control(),0,4,mock(NamedconstContext.class),null,null,null,null},
			{new Control(),0,5,mock(NamedconstContext.class),null,null,null,null},
			{new Control(),0,0,null,mock(TerminalNode.class),null,null,null},
			{new Control(),0,0,null,null,mock(TerminalNode.class),null,null},
			{new Control(),0,0,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,1,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,2,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,3,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,4,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,5,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,6,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,7,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,8,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,9,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,10,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,11,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,12,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,13,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,14,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,15,null,null,null,mock(UnopContext.class),null},
			{new Control(),0,16,null,null,null,mock(UnopContext.class),null},
			{new Control(),1,0,null,null,null,null,null},
			{new Control(),2,1,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,2,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,3,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,4,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,5,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,6,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,7,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,8,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,9,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,10,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,11,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,12,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,13,null,null,null,null,mock(BinopContext.class)},
			{new Control(),2,0,null,null,null,null,null},
			{new Control(),3,0,null,null,null,null,null}
		});
	}

	@Parameter(value=0)
	public Statement statement;
	@Parameter(value=1)
	public int size;
	@Parameter(value=2)
	public int option;
	@Parameter(value=3)
	public NamedconstContext namedConstContextMock;
	@Parameter(value=4)
	public TerminalNode terminalNodeMock1;
	@Parameter(value=5)
	public TerminalNode terminalNodeMock2;
	@Parameter(value=6)
	public UnopContext unopContextMock;
	@Parameter(value=7)
	public BinopContext binopContextMock;
	@Test
	public void StatementOfWhiledoTest() {
		CtrlContext ctrlContextMock = mock(CtrlContext.class);
		EvalContext evalContextMock = mock(EvalContext.class);
		ExprContext exprContextMock = mock(ExprContext.class);
		ExprContext exprContextMock1 = mock(ExprContext.class);
		ExprContext exprContextMock2 = mock(ExprContext.class);
		List<ExprContext> exprContextListMock = (List<ExprContext>) mock(List.class);
		WhiledoContext whiledoContextMock = mock(WhiledoContext.class);
		when(evalContextMock.getStart()).thenReturn(myTokenMock);
		when(evalContextMock.ctrl()).thenReturn(ctrlContextMock);
		when(ctrlContextMock.whiledo()).thenReturn(whiledoContextMock);
		when(whiledoContextMock.getStart()).thenReturn(myTokenMock);
		when(myTokenMock.getLine()).thenReturn(0);
		when(whiledoContextMock.expr()).thenReturn(exprContextMock);
		when(whiledoContextMock.eval()).thenReturn(null);
		when(factoryMock.createWhile(anyInt(), anyInt(),(Expression<Type>) any(Expression.class),any(Statement.class))).thenReturn(new Control());
		when(exprContextMock.getStart()).thenReturn(myTokenMock);
		when(exprContextMock.expr()).thenReturn(exprContextListMock);
		when(exprContextListMock.size()).thenReturn(size);
		if (namedConstContextMock!=null) {
			TerminalNode terminalNodeMockLocal1=null;
			TerminalNode terminalNodeMockLocal2=null;
			TerminalNode terminalNodeMockLocal3=null;
			TerminalNode terminalNodeMockLocal4=null;
			when(exprContextMock.namedconst()).thenReturn(namedConstContextMock);
			when(namedConstContextMock.getStart()).thenReturn(myTokenMock);
			if (option==1) terminalNodeMockLocal1 = mock(TerminalNode.class);
			else if (option==2) terminalNodeMockLocal2 = mock(TerminalNode.class);
			else if (option==3) terminalNodeMockLocal3 = mock(TerminalNode.class);
			else if (option==4) terminalNodeMockLocal4 = mock(TerminalNode.class);
			when(namedConstContextMock.FALSE()).thenReturn(terminalNodeMockLocal1);
			when(namedConstContextMock.TRUE()).thenReturn(terminalNodeMockLocal2);
			when(namedConstContextMock.SELF()).thenReturn(terminalNodeMockLocal3);
			when(namedConstContextMock.NULL()).thenReturn(terminalNodeMockLocal4);
			when(factoryMock.createBooleanLiteral(anyInt(), anyInt(), anyBoolean())).thenReturn(null);
			when(factoryMock.createSelf(anyInt(), anyInt())).thenReturn(null);
			when(factoryMock.createNull(anyInt(), anyInt())).thenReturn(null);
		}
		else if (terminalNodeMock1!=null) {
			when(exprContextMock.NUMBER()).thenReturn(terminalNodeMock1);
			when(terminalNodeMock1.getSymbol()).thenReturn(myTokenMock);
			when(terminalNodeMock1.getText()).thenReturn("5");
			when(factoryMock.createDoubleLiteral(anyInt(), anyInt(), anyDouble())).thenReturn(null);
		}
		else if (terminalNodeMock2!=null)
			when(exprContextMock.IDENTIFIER()).thenReturn(terminalNodeMock2);
		else if (unopContextMock!=null) {
			when(exprContextMock.unop()).thenReturn(unopContextMock);
			when(unopContextMock.getStart()).thenReturn(myTokenMock);
			TerminalNode[] terminalNodeMockArray = new TerminalNode[16];
			for (int i=0;i<16;i++) terminalNodeMockArray[i] = null;
			for (int i=1;i<17;i++) {
				if (option==i) terminalNodeMockArray[i-1] = mock(TerminalNode.class);
			}
			when(unopContextMock.GETRADIUS()).thenReturn(terminalNodeMockArray[0]);
			when(unopContextMock.GETDIR()).thenReturn(terminalNodeMockArray[1]);
			when(unopContextMock.GETY()).thenReturn(terminalNodeMockArray[2]);
			when(unopContextMock.GETX()).thenReturn(terminalNodeMockArray[3]);
			when(unopContextMock.GETAP()).thenReturn(terminalNodeMockArray[4]);
			when(unopContextMock.GETMAXAP()).thenReturn(terminalNodeMockArray[5]);
			when(unopContextMock.GETHP()).thenReturn(terminalNodeMockArray[6]);
			when(unopContextMock.GETMAXHP()).thenReturn(terminalNodeMockArray[7]);
			when(unopContextMock.NOT()).thenReturn(terminalNodeMockArray[8]);
			when(unopContextMock.SQRT()).thenReturn(terminalNodeMockArray[9]);
			when(unopContextMock.SIN()).thenReturn(terminalNodeMockArray[10]);
			when(unopContextMock.COS()).thenReturn(terminalNodeMockArray[11]);
			when(unopContextMock.SAMETEAM()).thenReturn(terminalNodeMockArray[12]);
			when(unopContextMock.SEARCHOBJ()).thenReturn(terminalNodeMockArray[13]);
			when(unopContextMock.ISWORM()).thenReturn(terminalNodeMockArray[14]);
			when(unopContextMock.ISFOOD()).thenReturn(terminalNodeMockArray[15]);
		}
		when(exprContextMock.binop()).thenReturn(binopContextMock);
		if (binopContextMock!=null) {
			when(exprContextListMock.get(0)).thenReturn(exprContextMock1);
			when(exprContextListMock.get(1)).thenReturn(exprContextMock2);
			when(exprContextMock1.getStart()).thenReturn(myTokenMock);
			when(exprContextMock2.getStart()).thenReturn(myTokenMock);
			TerminalNode[] terminalNodeMockArray = new TerminalNode[12];
			for (int i=0;i<12;i++) terminalNodeMockArray[i] = null;
			for (int i=1;i<13;i++) {
				if (option==i) terminalNodeMockArray[i-1] = mock(TerminalNode.class);
			}
			when(binopContextMock.GT()).thenReturn(terminalNodeMockArray[0]);
			when(binopContextMock.LT()).thenReturn(terminalNodeMockArray[1]);
			when(binopContextMock.SUB()).thenReturn(terminalNodeMockArray[2]);
			when(binopContextMock.NEQ()).thenReturn(terminalNodeMockArray[3]);
			when(binopContextMock.GEQ()).thenReturn(terminalNodeMockArray[4]);
			when(binopContextMock.EQ()).thenReturn(terminalNodeMockArray[5]);
			when(binopContextMock.DIV()).thenReturn(terminalNodeMockArray[6]);
			when(binopContextMock.MUL()).thenReturn(terminalNodeMockArray[7]);
			when(binopContextMock.OR()).thenReturn(terminalNodeMockArray[8]);
			when(binopContextMock.AND()).thenReturn(terminalNodeMockArray[9]);
			when(binopContextMock.LEQ()).thenReturn(terminalNodeMockArray[10]);
			when(binopContextMock.ADD()).thenReturn(terminalNodeMockArray[11]);
		}
		wpml.enterEval(evalContextMock);
		Control seq = (Control) statement;
		Control controlStatement = (Control) wpml.getStatement();
		assertEquals(seq.getLine(),controlStatement.getLine());
		assertEquals(seq.getWorm(),controlStatement.getWorm());
	}
}

