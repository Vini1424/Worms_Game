package worms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.mockito.Matchers;
import org.mockito.Mock;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import worms.model.programs.ProgramFactory;
import worms.model.programs.parser.WormsParserMyListener;
import worms.model.programs.parser.WormsParserParser.ActionContext;
import worms.model.programs.parser.WormsParserParser.AssignContext;
import worms.model.programs.parser.WormsParserParser.CtrlContext;
import worms.model.programs.parser.WormsParserParser.DeclContext;
import worms.model.programs.parser.WormsParserParser.EvalContext;
import worms.model.programs.parser.WormsParserParser.ExprContext;
import worms.model.programs.parser.WormsParserParser.TypeContext;
import worms.model.part3.*;

@RunWith(Parameterized.class)
public class WormsParserMyListenerTest1 {

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
	
	@Parameters(name = "Run {index}: evalContextMock={0}, assignContextMock={1}, ctrlContextMock={2}, actionContextMock={3},"
			+ "declContextMock={4}, typeContextMock={5}, terminalNodeMock={6}, exprContextMock={7}, statement={8}")
	public static Collection<Object[]> lista(){
		return Arrays.asList(new Object[][] {
			{null,null,null,null,null,null,null,null,null},
			{mock(EvalContext.class), mock(AssignContext.class), mock(CtrlContext.class), mock(ActionContext.class),
				mock(DeclContext.class), mock(TypeContext.class), mock(TerminalNode.class), null, 
				new SequenceOfStatements(new ArrayList<Statement>(), 1)},
			{mock(EvalContext.class),null,null,null,null,null,null,null,null}
		});
	}

	
	@Parameter(value = 0)
	public EvalContext evalContextMock;
	@Parameter(value = 1)
	public AssignContext assignContextMock;
	@Parameter(value = 2)
	public CtrlContext ctrlContextMock;
	@Parameter(value = 3)
	public ActionContext actionContextMock;
	@Parameter(value = 4)
	public DeclContext declContextMock;
	@Parameter(value = 5)
	public TypeContext typeContextMock;
	@Parameter(value = 6)
	public TerminalNode terminalNodeMock;
	@Parameter(value = 7)
	public ExprContext exprContextMock;
	@Parameter(value = 8)
	public Statement statement;
	@Test
	public void enterEvalTest() {
		if (evalContextMock!=null) {
			when(evalContextMock.getStart()).thenReturn(myTokenMock);
			when(evalContextMock.assign()).thenReturn(assignContextMock);
			when(evalContextMock.ctrl()).thenReturn(ctrlContextMock);
			when(evalContextMock.action()).thenReturn(actionContextMock);
			when(evalContextMock.decl()).thenReturn(declContextMock);
			when(evalContextMock.PRINT()).thenReturn(terminalNodeMock);
			when(evalContextMock.eval()).thenReturn(null);
			if (actionContextMock != null) {
				when(assignContextMock.expr()).thenReturn(exprContextMock);
				when(assignContextMock.IDENTIFIER()).thenReturn(terminalNodeMock);
				when(terminalNodeMock.getSymbol()).thenReturn(myTokenMock);
				when(actionContextMock.getStart()).thenReturn(myTokenMock);
				when(declContextMock.type()).thenReturn(typeContextMock);
				when(evalContextMock.expr()).thenReturn(null);
				when(factoryMock.createPrint(anyInt(), anyInt(),
						(Expression<Type>) any(Expression.class))).thenReturn(new SequenceOfStatements(new ArrayList<Statement>(), 1));
				wpml.enterEval(evalContextMock);
				SequenceOfStatements seq = (SequenceOfStatements) statement;
				SequenceOfStatements sequenceOfStatements = (SequenceOfStatements) wpml.getStatement();
				assertEquals(seq.getLine(),statement.getLine());
				assertEquals(seq.getWorm(),sequenceOfStatements.getWorm());
			}
			else {
				when(factoryMock.createSequence(anyInt(), anyInt(), Matchers.anyListOf(Statement.class))).
					thenReturn(null);
				wpml.enterEval(evalContextMock);
				SequenceOfStatements seq = (SequenceOfStatements) statement;
				SequenceOfStatements sequenceOfStatements = (SequenceOfStatements) wpml.getStatement();
				assertNull(sequenceOfStatements);
			}
		}else {
			when(factoryMock.createSequence(anyInt(), anyInt(), Matchers.anyListOf(Statement.class))).
			thenReturn(null);
			wpml.enterEval(evalContextMock);
			assertSame(statement,wpml.getStatement());			
		}
	}
}