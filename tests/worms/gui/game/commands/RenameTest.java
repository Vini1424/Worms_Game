package worms.gui.game.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import worms.gui.game.PlayGameScreen;
import worms.gui.game.sprites.WormSprite;
import worms.gui.messages.MessageType;
import worms.model.*;

public class RenameTest {

	Worm worm;
	Rename ren;
	Rename rename;
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
    
	
	@Before
	public void setUp() throws Exception {
		worm = mock(Worm.class);
		
	}
	
	@Test
	public void testCanStartIfTheresNoWorm() {
		ren = new Rename(facade, null, "", screen);
		rename = spy(ren);		
		assertEquals(false, rename.canStart());
	}	
	
	@Test 
	public void testCanStartIfTheresAWorm() {
		ren = new Rename(facade, worm, "", screen);
		rename = spy(ren);
		assertEquals(true, rename.canStart());
		
	}
	
	
	@Test
	public void testDoStartExecution() {
		ren = new Rename(facade, worm, "", screen);
		rename = spy(ren);
		rename.doStartExecution();
		verify(facade, times(1)).rename(eq(worm), anyString());
	}
	
	
	@Test
	public void testDoStartExecutionWhenExceptionIsThrown() {
		ren = new Rename(facade, worm, "", screen);
		rename = spy(ren);
		doThrow(new ModelException(""))
			.when(facade)
			.rename(eq(worm), anyString());
		rename.doStartExecution();
		verify(screen, times(1)).addMessage(anyString(), eq(MessageType.ERROR));
	}
	

	

}
