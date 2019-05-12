package worms.gui.game.commands;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import worms.gui.game.PlayGameScreen;
import worms.gui.game.sprites.WormSprite;
import worms.gui.messages.MessageType;
import worms.model.IFacade;
import worms.model.World;
import worms.model.Worm;

public class MoveTest {

	Move move;
	Worm worm;
	IFacade facade = Mockito.mock(IFacade.class);
	PlayGameScreen screen = Mockito.mock(PlayGameScreen.class);
    World world = Mockito.mock(World.class);
    WormSprite wormSprite = Mockito.mock(WormSprite.class);
    
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		move = Mockito.mock(Move.class);
	}
	
	@Test
	public void testGetWorm() {
		assertEquals(move.getWorm(), worm);
	}
	

	@Test
	public void testCanStartIfTheresNoWorm() {
		Mockito.when(move.getWorm()).thenReturn(null);
		assertEquals(false, move.canStart());
	}	
	
	@Test 
	public void testCanStartIfTheresAWorm() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(move.getWorm()).thenReturn(worm);
		Mockito.when(move.getFacade()).thenReturn(facade);
		Mockito.when(facade.canMove(worm)).thenReturn(false);
		assertEquals(false, move.canStart());
		
	}
	

	@Test 
	public void testCanStartIfTheresAWormAndItCanMove() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(move.getWorm()).thenReturn(worm);
		Mockito.when(move.getFacade()).thenReturn(facade);
		Mockito.when(facade.canMove(worm)).thenReturn(true);
		assertEquals(true, move.canStart());
		
	}
	
	@Test
	public void testCanFall() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(move.getFacade()).thenReturn(facade);
		Mockito.when(facade.canFall(worm)).thenReturn(false);
		assertEquals(false, move.canFall());
	}
	
	@Test
	public void testDoUpdateWhenTheresNoSprite() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(screen.getWormSprite(worm)).thenReturn(null);
		Mockito.doCallRealMethod().when(move).doUpdate(10);
		Mockito.verify(move, Mockito.times(1)).cancelExecution();
		
	}
	
	@Test 
	public void testFallForTrue() {
		Mockito.when(move.isFalling()).thenReturn(true);
		Mockito.doCallRealMethod().when(move).fall(10);
		Mockito.verify(move, Mockito.times(1)).updateFalling();
		
		
	}
	
	
	@Test 
	public void testFallForFalse() {
		Mockito.when(move.isFalling()).thenReturn(false);
		move.fall(10);
		Mockito.verify(move, Mockito.times(1)).startFalling();
		
		
	}
	
	
	
	@Test
	public void testAfterExecutionCompleted() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(screen.getWormSprite(worm)).thenReturn(wormSprite);
		move.afterExecutionCompleted();
		Mockito.verify(wormSprite, Mockito.times(1)).setIsMoving(false);
	}
	
	
	
	@Test
	public void testAfterExecutionCancelled() {
		worm = Mockito.mock(Worm.class);
		Mockito.when(screen.getWormSprite(worm)).thenReturn(null);
		move.afterExecutionCancelled();
		Mockito.verify(screen, Mockito.times(1)).addMessage("This worm cannot move like that :(",
				MessageType.ERROR);
	
	}
	
	
	
}
