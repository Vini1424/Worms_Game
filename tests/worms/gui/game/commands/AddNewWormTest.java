package worms.gui.game.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.io.IOException;

import worms.gui.GUIUtils;
import worms.gui.game.IActionHandler;
import worms.gui.game.PlayGameScreen;
import worms.model.IFacade;
import worms.model.World;
import worms.model.programs.ParseOutcome;

public class AddNewWormTest {

	AddNewWorm addWorm;
	AddNewWorm anw; 
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
	World world = mock(World.class);
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public final void testCanStart() {
		addWorm= new AddNewWorm(facade, true, screen);
		anw = spy(addWorm);
		assertEquals(true, anw.canStart());
	}

	@Test
	public final void testDoStartExecutionProgramTextTrueAndNullReturnedString() {
		addWorm= new AddNewWorm(facade, true, screen);
		anw = spy(addWorm);
		doReturn(null).when(anw).readProgramText();
		anw.doStartExecution();
		verify(anw, times(1)).cancelExecution();
	}
	
	@Test
	public final void testDoStartExecutionProgramTextFalse() {
		addWorm= new AddNewWorm(facade, false, screen);
		anw = spy(addWorm);
		doReturn(facade).when(anw).getFacade();
		doReturn(world).when(anw).getWorld();
		doReturn(null).when(anw).readProgramText();
		anw.doStartExecution();
		//getFacade().addNewWorm(getWorld(), null);
		verify(facade, times(1)).addNewWorm(world, null);
	}
	
	@Test
	public final void testDoStartExecutionProgramTextTrueWithSomeStringParsedIsNotNull() {
		addWorm= new AddNewWorm(facade, true, screen);
		IActionHandler handler = mock(IActionHandler.class);
		ParseOutcome<?> parsed = mock(ParseOutcome.class);
		anw = spy(addWorm);
		doReturn("").when(anw).readProgramText();
		//when(facade.parseProgram(anyString(), eq(handler))).thenReturn(parsed);
		anw.doStartExecution();
		
	}
	
	@Test
	public void testReadProgramTest() throws IOException {
		addWorm= new AddNewWorm(facade, true, screen);
		GUIUtils gui = mock(GUIUtils.class);
		anw = spy(addWorm);
		
		//doThrow(new IOException("")).when(gui).openResource(anyString());
		assertEquals(null, anw.readProgramText());
		
	}
	
	

}
