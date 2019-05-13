package worms.gui.game.commands;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import worms.gui.game.PlayGameScreen;
import worms.gui.game.sprites.WormSprite;
import worms.gui.messages.MessageType;
import worms.model.IFacade;
import worms.model.World;
import worms.model.Worm;

public class MoveTest {

	Move move = null;
	Worm worm = null;
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
    World world = mock(World.class);
    WormSprite wormSprite = mock(WormSprite.class);
    
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		move = mock(Move.class,
				CALLS_REAL_METHODS);
	}
	
	@Test
	public void testGetWorm() {
		assertEquals(move.getWorm(), worm);
	}
	

	@Test
	public void testCanStartIfTheresNoWorm() {
		when(move.getWorm()).thenReturn(null);
		assertEquals(false, move.canStart());
	}	
	
	@Test 
	public void testCanStartIfTheresAWorm() {
		worm = mock(Worm.class);
		when(move.getWorm()).thenReturn(worm);
		when(move.getFacade()).thenReturn(facade);
		when(facade.canMove(worm)).thenReturn(false);
		assertEquals(false, move.canStart());
		
	}
	

	@Test 
	public void testCanStartIfTheresAWormAndItCanMove() {
		worm = mock(Worm.class);
		when(move.getWorm()).thenReturn(worm);
		when(move.getFacade()).thenReturn(facade);
		when(facade.canMove(worm)).thenReturn(true);
		assertEquals(true, move.canStart());
		
	}
	
	@Test
	public void testCanFall() {
		worm = mock(Worm.class);
		when(move.getFacade()).thenReturn(facade);
		when(facade.canFall(worm)).thenReturn(false);
		assertEquals(false, move.canFall());
	}
	
	@Test
	public void testDoUpdateWhenTheresNoSprite() {
		worm = mock(Worm.class);
		when(screen.getWormSprite(worm)).thenReturn(null);
		doCallRealMethod().when(move).doUpdate(10);
		verify(move, times(1)).cancelExecution();
		
	}
	
	@Test 
	public void testFallForTrue() {
		move.setIsFalling(true);
		doCallRealMethod()
			.when(move)
			.fall(10);
		verify(move, times(1)).updateFalling();
		
		
	}
	
	
	@Test 
	public void testFallForFalse() {
		when(move.isFalling()).thenReturn(false);
		doCallRealMethod().when(move).fall(10.0);
		verify(move, times(1)).startFalling();
		
		
	}
	
	
	
	@Test
	public void testAfterExecutionCompleted() {
		worm = mock(Worm.class);
		when(screen.getWormSprite((Worm) any())).thenReturn(wormSprite);
		doCallRealMethod().when(move).afterExecutionCompleted();
		verify(wormSprite, times(1)).setIsMoving(false);
	}
	
	
	
	@Test
	public void testAfterExecutionCancelled() {
		worm = mock(Worm.class);
		when(screen.getWormSprite(worm)).thenReturn(null);
		doCallRealMethod().when(move).afterExecutionCancelled();
		verify(screen, times(1)).addMessage("This worm cannot move like that :(",
				MessageType.ERROR);
	
	}
	
	@Test
	public void testEnsureFalling() {
		move.setFallingStartTime(-1);
		when(move.getElapsedTime()).thenReturn(10.0);
		doCallRealMethod().when(move).ensureFalling();
		assertEquals(10.0, move.getFallingStartTime());
		//assertEquals(true, move.isFalling());
	}
	
	
	
}
