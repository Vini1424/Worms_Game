package worms.gui.game.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import worms.gui.GUIUtils;
import worms.gui.WormsGUI;
import worms.gui.game.IActionHandler;
import worms.gui.game.PlayGameScreen;
import worms.model.IFacade;
import worms.model.Program;
import worms.model.World;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ParseOutcome.Failure;
import worms.model.programs.ParseOutcome.Success;

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
		Success parsed = mock(Success.class);
		Program p = mock(Program.class);

		
		anw = spy(addWorm);
		doReturn("").when(anw).readProgramText();
		doReturn(facade).when(anw).getFacade();
		doReturn(world).when(anw).getWorld();
		doReturn(parsed).when(facade).parseProgram(anyString(), any(IActionHandler.class));
		doReturn(true).when(parsed).isSuccess();
		doReturn(p).when(parsed).getResult();
		
		doReturn(true).when(facade).isWellFormed(p);
		doNothing().when(facade).addNewWorm(world, p);
		anw.doStartExecution();
		verify(facade, times(1)).addNewWorm(world, p);
	}
	
	
	@Test
	public final void testDoStartExecutionProgramTextTrueWithSomeStringParsedIsNotNullSuccessIsTrue() {
		addWorm= new AddNewWorm(facade, true, screen);
		Success parsed = mock(Success.class);
		Program p = mock(Program.class);
		WormsGUI mockGUI = mock(WormsGUI.class);
		PlayGameScreen mockScreen = mock(PlayGameScreen.class);
		
		anw = spy(addWorm);
		doReturn("").when(anw).readProgramText();
		doReturn(facade).when(anw).getFacade();
		doReturn(parsed).when(facade).parseProgram(anyString(), any(IActionHandler.class));
		doReturn(true).when(parsed).isSuccess();
		doReturn(p).when(parsed).getResult();
		doNothing().when(anw).cancelExecution();
		doReturn(mockScreen).when(anw).getScreen();
		doReturn(mockGUI).when(mockScreen).getGUI();
		doNothing().when(mockGUI).showError(anyString());
		doReturn(false).when(facade).isWellFormed(p);
		anw.doStartExecution();
		verify(anw, times(1)).cancelExecution();
	}
	
	@Test
	public final void testDoStartExecutionProgramTextTrueWithSomeStringParsedIsNull() {
		addWorm= new AddNewWorm(facade, true, screen);
		Success parsed = mock(Success.class);
		Program p = mock(Program.class);

		
		anw = spy(addWorm);
		doReturn("").when(anw).readProgramText();
		doReturn(facade).when(anw).getFacade();
		doReturn(world).when(anw).getWorld();
		doReturn(null).when(facade).parseProgram(anyString(), any(IActionHandler.class));
		anw.doStartExecution();
	}
	
	@Test
	public final void testDoStartExecutionProgramTextTrueWithSomeStringParsedIsNotNullSuccessIsFalse() {
		addWorm= new AddNewWorm(facade, true, screen);
		Failure parsed = mock(Failure.class);
		Program p = mock(Program.class);
		WormsGUI mockGUI = mock(WormsGUI.class);
		PlayGameScreen mockScreen = mock(PlayGameScreen.class);
		List<String> errors = new ArrayList<String>();
		errors.add("Smthg");
		
		anw = spy(addWorm);
		doReturn("").when(anw).readProgramText();
		doReturn(facade).when(anw).getFacade();
		doReturn(parsed).when(facade).parseProgram(anyString(), any(IActionHandler.class));
		doReturn(false).when(parsed).isSuccess();
		doReturn(errors).when(parsed).getResult();
		doNothing().when(anw).cancelExecution();
		doReturn(mockScreen).when(anw).getScreen();
		doReturn(mockGUI).when(mockScreen).getGUI();
		doNothing().when(mockGUI).showError(anyString());
		doReturn(false).when(facade).isWellFormed(p);
		anw.doStartExecution();
		verify(anw, times(1)).cancelExecution();
	}
	
	

	/*@Test
	public void testReadProgram() throws IOException {
		addWorm= new AddNewWorm(facade, true, screen);
		GUIUtils gui = mock(GUIUtils.class);
		anw = spy(addWorm);
		
		//doThrow(new IOException("")).when(gui).openResource(anyString());
		anw.readProgramText();
		
	}*/
	
	

}
