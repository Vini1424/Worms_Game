package worms.gui.game.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import worms.gui.game.PlayGameScreen;
import worms.model.IFacade;
import worms.model.World;

public class AddNewFoodTest {
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
	AddNewFood anf = new AddNewFood(facade, screen);
	

	@Test
	public  void testCanStart() {
		assertEquals(true, anf.canStart());
	}
	
	@Test
	public void testDoStartExecution() {
		anf.doStartExecution();
		verify(facade, times(1)).addNewFood(null);
	}

}
