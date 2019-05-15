package worms.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import worms.model.programs.ProgramFactory;
import worms.model.programs.parser.WormsParserMyListener;
import worms.model.programs.parser.WormsParserParser.CtrlContext;
import worms.model.programs.parser.WormsParserParser.EvalContext;
import worms.model.programs.parser.WormsParserParser.IfthenelseContext;
import worms.model.part3.*;

@RunWith(Parameterized.class)
public class WormsParserMyListenerTest2 {

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
	
	@Parameters(name = "Run {index}: statement={0}, size={1}")
	public static Collection<Object[]> lista2(){
		return Arrays.asList(new Object[][] {
			{null,0},{new Control(),1},{new Control(),2},{null,3}
		});
	}
	
	@Parameter(value = 0)
	public Statement statement;
	@Parameter(value = 1)
	public int size;
	@Test
	public void StatementOfIfthenelseTest() {
		CtrlContext ctrlContextMock = mock(CtrlContext.class);
		EvalContext evalContextMock = mock(EvalContext.class);
		IfthenelseContext ifthenelseContextMock = mock(IfthenelseContext.class);
		List<EvalContext> evalContextListMock = (List<EvalContext>) mock(List.class);
		when(evalContextMock.getStart()).thenReturn(myTokenMock);
		when(evalContextMock.ctrl()).thenReturn(ctrlContextMock);
		when(ctrlContextMock.ifthenelse()).thenReturn(ifthenelseContextMock);
		when(ifthenelseContextMock.getStart()).thenReturn(myTokenMock);
		when(myTokenMock.getLine()).thenReturn(0);
		when(ifthenelseContextMock.eval()).thenReturn(evalContextListMock);
		when(evalContextListMock.size()).thenReturn(size);
		when(factoryMock.createSequence(anyInt(), anyInt(), Matchers.anyListOf(Statement.class))).
			thenReturn(null); 
		when(factoryMock.createIf(anyInt(), anyInt(), (Expression<Type>) any(Expression.class), any(Statement.class),
			any(SequenceOfStatements.class))).thenReturn(new Control());
		wpml.enterEval(evalContextMock);
		if (statement==null)
			assertEquals(statement, wpml.getStatement());
		else {
			Control seq = (Control) statement;
			Control controlStatement = (Control) wpml.getStatement();
			assertEquals(seq.getLine(),controlStatement.getLine());
			assertEquals(seq.getWorm(),controlStatement.getWorm());
		}
	}
}
