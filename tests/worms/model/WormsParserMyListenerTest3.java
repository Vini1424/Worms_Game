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
import java.util.Collection;

import worms.model.programs.ProgramFactory;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.parser.WormsParserMyListener;
import worms.model.programs.parser.WormsParserParser.CtrlContext;
import worms.model.programs.parser.WormsParserParser.EntityspecContext;
import worms.model.programs.parser.WormsParserParser.EvalContext;
import worms.model.programs.parser.WormsParserParser.ForeachContext;
import worms.model.part3.*;

@RunWith(Parameterized.class)
public class WormsParserMyListenerTest3 {

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
	
	@Parameters(name = "Run {index}: terminalNodeMock={0}, statement={1}")
	public static Collection<Object[]> lista3(){
		return Arrays.asList(new Object[][] {
			{null,new Control()},{mock(TerminalNode.class),new Control()}
		});
	}

	// Sajnos ez nem igazán jó, igazából egy lokális változót, a type-ot kellene tesztelni
	@Parameter(value = 0)
	public TerminalNode terminalNodeMock;
	@Parameter(value = 1)
	public Statement statement;
	@Test
	public void StatementOfForeachTest() {
		EntityspecContext entityspecContextMock = mock(EntityspecContext.class);
		TerminalNode terminalNodeMockLocal = mock(TerminalNode.class);
		CtrlContext ctrlContextMock = mock(CtrlContext.class);
		EvalContext evalContextMock = mock(EvalContext.class);
		ForeachContext ForeachContextMock = mock(ForeachContext.class);
		when(evalContextMock.getStart()).thenReturn(myTokenMock);
		when(evalContextMock.ctrl()).thenReturn(ctrlContextMock);
		when(ForeachContextMock.eval()).thenReturn(null);
		when(ctrlContextMock.foreach()).thenReturn(ForeachContextMock);
		when(ForeachContextMock.getStart()).thenReturn(myTokenMock);
		when(myTokenMock.getLine()).thenReturn(0);
		when(ForeachContextMock.entityspec()).thenReturn(entityspecContextMock);
		when(entityspecContextMock.ANY()).thenReturn(terminalNodeMock);
		when(entityspecContextMock.WORM()).thenReturn(terminalNodeMock);
		when(entityspecContextMock.FOOD()).thenReturn(terminalNodeMock);
		when(ForeachContextMock.IDENTIFIER()).thenReturn(terminalNodeMockLocal);
		when(terminalNodeMockLocal.getText()).thenReturn("");
		when(factoryMock.createForeach(anyInt(), anyInt(), any(ForeachType.class), anyString(), any(Statement.class))).thenReturn(new Control());
		wpml.enterEval(evalContextMock);
		Control seq = (Control) statement;
		Control controlStatement = (Control) wpml.getStatement();
		assertEquals(seq.getLine(),controlStatement.getLine());
		assertEquals(seq.getWorm(),controlStatement.getWorm());
	}
}

