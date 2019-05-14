package worms.gui.game.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import worms.gui.game.PlayGameScreen;
import worms.gui.game.sprites.WormSprite;
import worms.gui.messages.MessageType;
import worms.model.*;

public class JumpTest {

	
	Jump j;
	Jump jump;
	Worm worm = mock(Worm.class);
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
    World world = mock(World.class);
    WormSprite wormSprite = mock(WormSprite.class);
	@Before
	public void setUp() throws Exception {
		j = new Jump(facade, worm, screen);
		jump = spy(j);
	}

	@Test
	public void testGetWorm() {
		assertEquals(jump.getWorm(), worm);
	}
	
	@Test
	public void testCanStartIfTheresNoWorm() {
		//when(move.getWorm()).thenReturn(null);
		doReturn(null).when(jump).getWorm();
		assertEquals(false, jump.canStart());
	}	
	
	@Test 
	public void testCanStartIfTheresAWorm() {
		doReturn(worm).when(jump).getWorm();
		assertEquals(true, jump.canStart());	
	}
	
	@Test 
	public void testDoStartExecution() {
		jump.doStartExecution();
		verify(facade, times(1)).getJumpTime(eq(worm), anyDouble());
	
	}
	
	@Test 
	public void testDoStartExecutionExceptionIsThrown() {
		doThrow(new ModelException(""))
			.when(facade)
			.getJumpTime(eq(worm), anyDouble());
		jump.doStartExecution();
		verify(jump, times(1)).cancelExecution();
	
	}
	
	
	@Test
	public void testAfterExecutionCancelled() {
		
		when(screen.getWormSprite(worm)).thenReturn(null);
		jump.afterExecutionCancelled();
		verify(screen, times(1)).addMessage("This worm cannot jump :(",
				MessageType.ERROR);
	
	}
	
	@Test
	public void testAfterExecutionCancelledIfTheresAWorm() {
		
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		jump.afterExecutionCancelled();
		verify(wormSprite, times(1)).setIsJumping(anyBoolean());
		verify(screen, times(1)).addMessage("This worm cannot jump :(",
				MessageType.ERROR);
	
	}
	
	@Test
	public void testAfterExecutionCompleted() {
		
		when(screen.getWormSprite((Worm) any())).thenReturn(wormSprite);
		jump.afterExecutionCompleted();
		verify(wormSprite, times(1)).setIsJumping(false);
	}
	
	@Test
	public void testAfterExecutionCompletedNoWorm() {
		when(screen.getWormSprite((Worm) any())).thenReturn(null);
		jump.afterExecutionCompleted();
	}
	
	@Test
	public void testDoUpdateWhenTheresNoSprite() {
		
		when(screen.getWormSprite(worm)).thenReturn(null);
		jump.doUpdate(anyDouble());
		verify(jump, times(1)).cancelExecution();
		
	}
	
	@Test
	public void testDoUpdateWhenTheresASpriteAndGetElapsedIsLessThanJumpDuration() {
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		doReturn(5.0).when(jump).getElapsedTime();
		jump.setJumpDuration(4.0);
		jump.setHasJumped(false);
		jump.doUpdate(anyDouble());
		verify(jump, times(1)).completeExecution();		
	}
	
	@Test
	public void testDoUpdateWhenTheresASpriteAndGetElapsedIsLessThanJumpDurationAndFacadeIsAlive() {
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		doReturn(5.0).when(jump).getElapsedTime();
		jump.setJumpDuration(4.0);
		jump.setHasJumped(false);
		when(facade.isAlive(worm)).thenReturn(true);
		jump.doUpdate(anyDouble());
		verify(wormSprite, times(1)).setCenterLocation(anyDouble(), anyDouble());
		verify(jump, times(1)).completeExecution();		
	}
	
	
	

}
