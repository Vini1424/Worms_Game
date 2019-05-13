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

	Move moveClass = null;
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
		worm = mock(Worm.class);
		moveClass = new Move(facade, worm, screen);
		move = spy(moveClass);
		
	}
	
	@Test
	public void testGetWorm() {
		assertEquals(move.getWorm(), worm);
	}
	

	@Test
	public void testCanStartIfTheresNoWorm() {
		//when(move.getWorm()).thenReturn(null);
		doReturn(null).when(move).getWorm();
		assertEquals(false, move.canStart());
	}	
	
	@Test 
	public void testCanStartIfTheresAWorm() {
		doReturn(worm).when(move).getWorm();
		when(facade.canMove(worm)).thenReturn(false);
		assertEquals(false, move.canStart());
		
	}
	

	@Test 
	public void testCanStartIfTheresAWormAndItCanMove() {
		
		doReturn(worm).when(move).getWorm();
		when(facade.canMove(worm)).thenReturn(true);
		assertEquals(true, move.canStart());
		
	}
	
	@Test
	public void testCanFallFalse() {
		
		when(move.getFacade()).thenReturn(facade);
		when(facade.canFall(worm)).thenReturn(false);
		assertEquals(false, move.canFall());
	}
	
	@Test
	public void testCanFallTrue() {
		
		when(move.getFacade()).thenReturn(facade);
		when(facade.canFall(worm)).thenReturn(true);
		assertEquals(true, move.canFall());
	}
	
	@Test
	public void testDoUpdateWhenTheresNoSprite() {
		
		when(screen.getWormSprite(worm)).thenReturn(null);
		move.doUpdate(anyDouble());
		verify(move, times(1)).cancelExecution();
		
	}
	
	@Test
	public void testDoUpdateWhenTheresASpriteAndGetElapsedIsLessThanGetDuration() {
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		doReturn(5.0).when(move).getElapsedTime();
		doReturn(10.0).when(move).getDuration();
		move.doUpdate(anyDouble());
		verify(wormSprite, times(1)).setIsMoving(anyBoolean());
		verify(wormSprite, times(1)).setCenterLocation(anyDouble(), anyDouble());
	}
	
	@Test
	public void testDoUpdateWhenTheresASpriteAndGetElapsedIsGreaterThanGetDuration() {
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		doReturn(10.0).when(move).getElapsedTime();
		doReturn(5.0).when(move).getDuration();
		move.doUpdate(anyDouble());
		verify(wormSprite, times(2)).setIsMoving(anyBoolean());
		verify(move, times(1)).fall(anyDouble());
	}
	
	@Test 
	public void testFallForTrue() {
		move.setIsFalling(true);
		move.fall(anyDouble());
		verify(move, times(1)).updateFalling();
		
		
	}
	
	
	@Test 
	public void testFallForFalse() {
		when(move.isFalling()).thenReturn(false);
		move.fall(anyDouble());
		verify(move, times(1)).startFalling();	
	}
	
	
	
	@Test
	public void testAfterExecutionCompleted() {
		
		when(screen.getWormSprite((Worm) any())).thenReturn(wormSprite);
		move.afterExecutionCompleted();
		verify(wormSprite, times(1)).setIsMoving(false);
	}
	
	
	
	@Test
	public void testAfterExecutionCancelled() {
		
		when(screen.getWormSprite(worm)).thenReturn(null);
		move.afterExecutionCancelled();
		verify(screen, times(1)).addMessage("This worm cannot move like that :(",
				MessageType.ERROR);
	
	}
	
	@Test
	public void testAfterExecutionCancelledIfTheresAWorm() {
		
		when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		move.afterExecutionCancelled();
		verify(wormSprite, times(1)).setIsMoving(anyBoolean());
		verify(screen, times(1)).addMessage("This worm cannot move like that :(",
				MessageType.ERROR);
	
	}
	
	
	@Test
	public void testEnsureFalling() {
		doReturn(10.0).when(move).getElapsedTime();
		move.ensureFalling();
		assertEquals(10.0, move.getFallingStartTime(), 0.1);
		assertEquals(true, move.isFalling());
	}
	
	
	@Test
	public void testEnsureFallingWhenFallingStartTimeIsNotMinusOne() {
		
		move.ensureFalling();
		assertEquals(true, move.isFalling());
	}
	
	
	
}
